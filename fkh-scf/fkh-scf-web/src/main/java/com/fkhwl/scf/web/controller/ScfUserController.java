package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfUserProvider;
import com.fkhwl.scf.entity.form.ModifyLoginPwdForm;
import com.fkhwl.scf.entity.form.ScfUserForm;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.UserType;
import com.fkhwl.scf.wrapper.ScfUserViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.api.BaseCodes;
import com.fkhwl.starter.core.support.AssertUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 账号管理
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Controller
@RequestMapping("/scfUser")
@Slf4j
@Api(value = "/scfUser", tags = "用户接口")
public class ScfUserController extends BaseController{

    @Resource
    private ScfUserProvider scfUserProvider;


	/**
	 * 修改密码
	 *
	 * @param passwordForm the modify req
	 * @param req       the req
	 * @return map map
	 */
	@ResponseBody
	@PostMapping(value = "/modifyLoginPwd")
    @ApiOperation(value = "修改密码", notes = "修改密码")
	public Map<String, Object> modifyLoginPwd(@ApiParam(value = "修改密码实体", required = true)ModifyLoginPwdForm passwordForm, HttpServletRequest req) {
        Subject subject = SecurityUtils.getSubject();
        final Map principal = (Map) subject.getPrincipal();
        ScfUserVO user = (ScfUserVO) principal.get("user");
        BaseCodes.PARAM_VERIFY_ERROR.isFalse(passwordForm.getNewPassword().equals(passwordForm.getOldPassword()), "新密码不能和旧密码一致");
        BaseCodes.PARAM_VERIFY_ERROR.notNull(passwordForm.getId(), "[userId]不能为空");
			this.scfUserProvider.modifyLoginPwd(user.getId(),passwordForm.getOldPassword(),passwordForm.getNewPassword());
		return this.json(true, "修改成功！");
	}


    /**
     * 分页获取用户列表
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/listPage")
    @ApiOperation(value = "分页查询用户列表", notes = "分页查询用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keyword",value = "关键字", paramType = "query"),
        @ApiImplicitParam(name = "deptId",value = "部门id",paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "ownerId",value = "企业主账号id", paramType = "query"),
        @ApiImplicitParam(name = "userType",value = "用户类型", paramType = "query")
    })
    public Result<IPage<ScfUserVO>> listPage(String keyword,Long deptId, Integer pageNo, Long ownerId, Integer userType) {
            ScfUserVO scfUserVO= getSessionUser();
            Map<String, Object> params = new HashMap<>();
            params.put("keyword",keyword);
            params.put("deptId",deptId);
            params.put("userType",isFounding(scfUserVO) ? UserType.FUNDING_SUB.getValue() : UserType.BORROWER_SUB.getValue());
            params.put("ownerId",ownerId == null ? scfUserVO.getParentId() : ownerId);
            params.put("page",pageNo == null ? 1 : pageNo);
            IPage<ScfUserVO> result = scfUserProvider.listPage(params).convert(ScfUserViewConverterWrapper.INSTANCE::vo);
            return StandardResult.succeed(result);
    }

    /**
     * 新增和更新用户
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增和更新用户", notes = "新增和更新用户")
    public Result<Void> saveOrUpdate(@ApiParam(value = "用户form实体", required = true)ScfUserForm scfUserForm) {
            ScfUserVO scfUserVO= getSessionUser();
            scfUserForm.setOwnerId(scfUserVO.getParentId());
            if (isFounding(scfUserVO)) {
                scfUserForm.setUserType(UserType.FUNDING_SUB.getValue());
            } else if (isBorrower(scfUserVO)) {
                scfUserForm.setUserType(UserType.BORROWER_SUB.getValue());
            }
            scfUserProvider.saveOrUpdate(ScfUserViewConverterWrapper.INSTANCE.dto(scfUserForm));
            return StandardResult.succeed();
    }

    /**
     * 删除用户
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{userId}")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "userId",value = "用户id", paramType = "path")
    public Result<Void> delete(@PathVariable("userId") Long userId) {
            AssertUtils.notNull(userId, "参数错误");
            scfUserProvider.delete(userId);
            return StandardResult.succeed();
    }

}
