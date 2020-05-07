package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AuthRoleProvider;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.form.AuthRoleForm;
import com.fkhwl.scf.entity.vo.AuthRoleVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.UserType;
import com.fkhwl.scf.wrapper.AuthRoleViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.support.AssertUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 角色controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/authRole")
@Api(value = "/authRole", tags = "角色信息相关接口")
public class AuthRoleController extends BaseController {

    @Resource
    private AuthRoleProvider authRoleProvider;

    @Resource
    private CompanyProvider companyProvider;

	/**
	 * 分页查询角色列表
	 *
	 * @return the string
	 */
    @ResponseBody
	@PostMapping(value = "/listPage")
    @ApiOperation(value = "分页查询角色列表", notes = "分页查询角色列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleName",value = "角色名称", paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "pageSize",value = "分页大小", paramType = "query")
    })
	public Result<IPage<AuthRoleVO>> listPage(String roleName, Integer pageNo, Integer pageSize) {
//        try {
            ScfUserVO scfUserVO= getSessionUser();
            Map<String, Object> params = new HashMap<>();
            params.put("roleName",roleName);
            params.put("page",pageNo);
            params.put("limit",pageSize == null ? 10 : pageSize);
            params.put("ownerId",scfUserVO.getParentId());
            if (isFounding(scfUserVO)) {
                params.put("userType", UserType.FUNDING.getValue());
            } else if (isBorrower(scfUserVO)){
                params.put("userType", UserType.BORROWER.getValue());
                CompanyDTO companyDTO = companyProvider.getByOwnerId(scfUserVO.getParentId());
                AssertUtils.notNull(companyDTO, "借款方公司不存在!");
                params.put("fundingOwnerId", companyDTO.getCreateOwnerId());
            }
            IPage<AuthRoleVO> result = authRoleProvider.listPage(params).convert(AuthRoleViewConverterWrapper.INSTANCE::vo);
            return StandardResult.succeed(result);
//        } catch (Exception e) {
//            log.error("AuthRoleController listPage error: {}", e.getMessage());
//            return StandardResult.failed(e.getMessage());
//        }
    }

    /**
     * 新增和更新角色
     *
     * @return the string
     */
    @ResponseBody
    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增和更新角色", notes = "新增和更新角色")
    public Result<Void> saveOrUpdate(@ApiParam(value = "角色form实体", required = true)AuthRoleForm authRoleForm) {
        try {
            ScfUserVO scfUserVO= getSessionUser();
            AuthRoleDTO authRoleDTO = AuthRoleViewConverterWrapper.INSTANCE.dto(authRoleForm);
            authRoleDTO.setOwnerId(scfUserVO.getParentId());
            authRoleProvider.saveOrUpdate(authRoleDTO);
            return StandardResult.succeed();
        } catch (Exception e) {
            log.error("AuthRoleController saveOrUpdate error: {}", e.getMessage());
            return StandardResult.failed(e.getMessage());
        }
    }

    /**
     * 删除角色
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/delete/{roleId}")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "roleId",value = "角色id", paramType = "path")
    public Result<Void> delete(@PathVariable("roleId")Long roleId) {
        try {
            AssertUtils.notNull(roleId, "参数错误");
            authRoleProvider.delete(roleId);
            return StandardResult.succeed();
        } catch (Exception e) {
            log.error("AuthRoleController saveOrUpdate error: {}", e.getMessage());
            return StandardResult.failed(e.getMessage());
        }
    }
}
