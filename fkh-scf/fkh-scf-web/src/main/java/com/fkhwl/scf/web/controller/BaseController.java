package com.fkhwl.scf.web.controller;

import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.UserType;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private String defalutSessionName = "JSESSIONID";


    public static final String SESSION_USER_KEY = "sessionUser";

    public static final String SESSION_WX_TOKEN = "wxtoken";

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.url}")
    private String imageBaseUrl;


    @Resource
    private CompanyProvider companyProvider;
    /**
     * Init binder.
     * 日期转换
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
            true));
    }

    /**
     * ajax 返回 Map 结构使用
     *
     * @param success the success
     * @param message the message
     * @return map map
     */
    protected Map<String, Object> json(boolean success, String message){
        return this.json(success, message, null);
    }

    /**
     * ajax 返回 Map 结构使用
     *
     * @param success the success
     * @param message the message
     * @param data    the data
     * @return map map
     */
    protected Map<String, Object> json(boolean success, String message, Object data) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("success", success);
        jsonMap.put("message", message);
        jsonMap.put("data", data);
        return jsonMap;
    }
    /**
     * 判断是否管理员
     *
     */
    protected boolean isAdmin(ScfUserVO user) {
        return user.getUserType().equals(UserType.ADMIN.getValue());
    }

    /**
     * 判断是否为资金方管理员
     *
     */
    protected boolean isFoundingOwner(ScfUserVO user) {
        return UserType.FUNDING.getValue().equals(user.getUserType());
    }

    /**
     * 判断是否为资金方子账号
     *
     */
    protected boolean isFoundingSub(ScfUserVO user) {
        return UserType.FUNDING_SUB.getValue().equals(user.getUserType());
    }

    /**
     * 判断是否为资金方
     *
     */
    protected boolean isFounding(ScfUserVO user) {
        return isFoundingOwner(user) || isFoundingSub(user);
    }

    /**
     * 判断是否为借款方管理员
     *
     */
    protected boolean isBorrowerOwner(ScfUserVO user) {
        return UserType.BORROWER.getValue().equals(user.getUserType());
    }

    /**
     * 判断是否为借款方子账号
     *
     */
    protected boolean isBorrowerSub(ScfUserVO user) {
        return UserType.BORROWER_SUB.getValue().equals(user.getUserType());
    }

    /**
     * 判断是否为借款方
     *
     */
    protected boolean isBorrower(ScfUserVO user) {
        return isBorrowerOwner(user) || isBorrowerSub(user);
    }
    /**
     * 修正V4中的pageNo和V5中的page转换
     * @param params
     */
    protected void fixPageNo(Map<String, Object> params){
        if(params.get("pageNo")!=null){
            params.put("page",params.get("pageNo"));
        }
    }

    /**
     * 设置session用户
     *
     * @param request the request
     */
    public void setSessionUser(HttpServletRequest request) {
        request.getSession().setAttribute("uploadPath", uploadPath);
        request.getSession().setAttribute("imageBaseUrl", imageBaseUrl);
        request.getSession().setAttribute("sessionUser", getSessionUser());
    }

    @NotNull
    public ScfUserVO getSessionUser() {
        Subject subject = SecurityUtils.getSubject();
//            TemplateUserDTO sessionAttrs = userDubboClient.findInfo(1L);
        final Map principal = (Map) subject.getPrincipal();
        return (ScfUserVO) principal.get("user");
    }
    /**
    * 添加ownerId作为查询条件
     * @param
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: liuan
    * @Date: 2020/3/16 11:01
    */
    public Map<String, Object> initOwnerIdParams() {
       Map<String,Object> params = new HashMap<>();

       ScfUserVO scfUserVO =  getSessionUser();
        List<Long> ownerIds=new ArrayList<>();
        if(isBorrower(scfUserVO)){
           //如果是借款方用户，则根据创建人的负责人账号查询
            ownerIds.add(scfUserVO.getParentId());
           params.put("ownerIds",ownerIds);

       }else if(isFounding(scfUserVO)){
           //如果是资方
          ownerIds = companyProvider.getBorrowerOwnerIdsByCapital(scfUserVO.getCompanyId());
           params.put("ownerIds",ownerIds);

       }

        return  params;
    }
    public Result exportError(HttpServletResponse response, String msg){
        try {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "<script>alert('"+
                msg
                +"');window.close();</script>";
            response.getWriter().write(alert);
        } catch (Exception e) {
        }
        return StandardResult.failed(msg);
    }
}
