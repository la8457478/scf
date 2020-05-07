package com.fkhwl.scf.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AuthFunctionProvider;
import com.fkhwl.scf.ScfConfigProvider;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.vo.AuthFunctionVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.AuthFuncType;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.AuthFunctionViewConverterWrapper;
import com.fkhwl.starter.autoconfigure.exception.ValidationException;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.api.BaseCodes;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Controller
@Slf4j
@Api(value = "", description = "登录相关接口")
public class LoginController extends BaseController{


//    @org.apache.dubbo.config.annotation.Reference(version = "1.0.1")

    @Resource
    private AuthFunctionProvider authFunctionProvider;

    @Resource
    private ScfConfigProvider scfConfigService;
    /**
     * To login string.
     *
     * @return the string
     */
    @GetMapping(value = "/login")
    public String toLogin() {
        return Views.LOGIN_PAGE;
    }

    /**
     * 点击登录按钮进行登录操作
     *
     * @param loginAccount the login account
     * @param loginPasswd      the login password
     * @param randomCode   the random code
     * @param model        the model
     * @param request      the request
     * @return string
     */
    @PostMapping(value = "/login")
    @ApiOperation(value = "", notes = "查询部门结构树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "loginAccount",value = "登录账号", paramType = "query"),
        @ApiImplicitParam(name = "loginPasswd",value = "登录密码",paramType = "query"),
        @ApiImplicitParam(name = "randomCode",value = "验证码",paramType = "query"),
    })
    public String doLogin(String loginAccount, String loginPasswd, String randomCode,
                          Model model, HttpServletRequest request) {
        final String host = request.getRemoteHost();
        String message;
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(loginAccount, loginPasswd, host);
        try {
            // 从SecurityUtils里边创建一个 subject
            Subject subject = SecurityUtils.getSubject();
            //todo chenli 2020-03-18应测试要求，暂时屏蔽验证码
//              String secCode = (String) request.getSession().getAttribute("randomCode");
//              if(StringUtils.isNotBlank(secCode)&&StringUtils.isNotBlank(randomCode)&&!secCode.equals(randomCode)){
//                  request.getSession().removeAttribute(Views.LOGIN_RANDOM_CODE);
//                  throw new ValidationException(BaseCodes.SERVER_ERROR,new Object[]{randomCode},"验证码错误");
//              }
            // 执行认证登陆
            subject.login(token);
            //set session attribute
//            SecurityProperties.User user = (SecurityProperties.User) principal.get("user");
//             loginService.buildSessionAttr方法生成了包含 List<String> permissions，key为"permissions";
//            principal.putAll(sessionAttrs);
            return Views.REDIRECT_INDEX_PAGE;
        }  catch (ValidationException de) {
            message = de.getMessage();
            model.addAttribute("message", message);
            return Views.LOGIN_PAGE;
        }catch (UnknownAccountException e) {
            message = e.getMessage();
            model.addAttribute("message", message);
            return Views.LOGIN_PAGE;
        } catch (AuthenticationException e) {
            message = BaseCodes.SERVER_ERROR.getMessage();
            log.error("doLogin error message...",e);
            model.addAttribute("message", message);
            return Views.LOGIN_PAGE;
        }
    }

    /**
     * 处理菜单权限相关
     * @param model
     */
    private void handleAuthFunc(Model model) {
        //处理菜单
        ScfUserVO sessionUser = getSessionUser();
        Map<String, Object> params = new HashMap<>();
        params.put("page",1);
        params.put("funcType",AuthFuncType.MENU.getValue());
        params.put("limit",Integer.MAX_VALUE);
        params.put("roleId",sessionUser.getRoleId());
        params.put("deleted", DeleteEnum.N.getValue());
        IPage<AuthFunctionVO> menuPage = authFunctionProvider.listPage(params).convert(AuthFunctionViewConverterWrapper.INSTANCE::vo);

        List<AuthFunctionVO> parentMenus = new ArrayList<>();
        if (!menuPage.getRecords().isEmpty()) {
            for (AuthFunctionVO item : menuPage.getRecords()) {
                if (item.getParentId() == 0L) {
                    parentMenus.add(item);
                }
            }
            for (AuthFunctionVO item : menuPage.getRecords()) {
                if (item.getParentId() != 0L) {
                    for (AuthFunctionVO function : parentMenus) {
                        if (function.getId().equals(item.getParentId())) {
                            if (function.getSubList() == null) {
                                List<AuthFunctionVO> menus = new ArrayList<>();
                                menus.add(item);
                                function.setSubList(menus);
                            } else {
                                function.getSubList().add(item);
                            }
                            break;
                        }
                    }
                }
            }
        }
        model.addAttribute("menus", parentMenus);

        //处理权限
        //查询所有的权限
        List<String> allFuncKeys = authFunctionProvider.listFuncKey(null);
        //查询用户角色的权限
        List<String> funcKeys = authFunctionProvider.listFuncKey(sessionUser.getRoleId());
        Map<String, Boolean> authMap = new HashMap<>();
        if (!allFuncKeys.isEmpty()) {
            for (String funcKeyItem : allFuncKeys) {
                if (funcKeys.contains(funcKeyItem)) {
                    authMap.put(funcKeyItem, Boolean.TRUE);
                } else {
                    authMap.put(funcKeyItem, Boolean.FALSE);
                }
            }
        }
        sessionUser.setAuthMap(authMap);
    }


    /**
     * 登录成功后跳转到首页
     *
     * @return string
     */
    @RequestMapping(value = "/index")
    public String index(Model model, HttpServletRequest request) {
//        model.addAttribute("baiduMapUrl", systemConfig.getBaiduMapUrl());
        //处理菜单权限相关
        handleAuthFunc(model);
        //设置sessionUser
        setSessionUser(request);
        //设置百度地图url
        ScfConfigDTO baiduMapConfig = scfConfigService.getConfigByConfigKey(ScfConfigEnum.BAIDU_MAP_URL.getCacheKey());
        if (baiduMapConfig != null) {
            model.addAttribute("baiduMapUrl",baiduMapConfig.getConfigValue());
        }
        ScfUserVO user = super.getSessionUser();
        model.addAttribute("companyType",user.getCompanyType());
        return Views.INDEX_PAGE;
    }

    /**
     * 退出登录
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Views.LOGIN_PAGE;
    }



}
