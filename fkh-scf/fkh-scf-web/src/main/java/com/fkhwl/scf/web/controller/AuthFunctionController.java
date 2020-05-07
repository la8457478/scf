package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.AuthFunctionProvider;
import com.fkhwl.scf.AuthRoleProvider;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.ScfUserProvider;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.dto.AuthRoleDTO;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.form.AuthFunctionForm;
import com.fkhwl.scf.entity.vo.AuthFunctionVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.enums.AuthRoleTypeEnum;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.AuthFunctionViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.support.AssertUtils;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限资源controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/authFunction")
@Api(value = "/authFunction", tags = "菜单权限接口")
public class AuthFunctionController extends BaseController {

    @Resource
    private AuthFunctionProvider authFunctionProvider;

    @Resource
    private ScfUserProvider scfUserProvider;

    @Resource
    private CompanyProvider companyProvider;

    @Resource
    private AuthRoleProvider authRoleProvider;

	/**
	 * 分页查询权限资源列表
	 *
	 * @return the string
	 */
    @ResponseBody
	@PostMapping(value = "/listPage")
    @ApiOperation(value = "分页查询权限资源列表", notes = "分页查询权限资源列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleName",value = "角色名称", paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "pageSize",value = "分页大小", paramType = "query")
    })
	public Result<IPage<AuthFunctionVO>> listPage(String funcName, Integer pageNo) {
            Map<String, Object> params = new HashMap<>();
            params.put("funcName",funcName);
            params.put("page",pageNo);
            IPage<AuthFunctionVO> result = authFunctionProvider.listPage(params).convert(AuthFunctionViewConverterWrapper.INSTANCE::vo);
            return StandardResult.succeed(result);
    }

    /**
     * 获取权限资源详情
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/getDetail/{funcId}")
    @ApiOperation(value = "获取菜单权限详情", notes = "获取菜单权限详情")
    @ApiImplicitParam(name = "funcId",value = "权限id", paramType = "path")
    public Result<AuthFunctionVO> getDetail(@PathVariable("funcId")Long funcId) {
            AssertUtils.isTrue(!BaseValidate.errorLong(funcId),"参数错误");
            return StandardResult.succeed(AuthFunctionViewConverterWrapper.INSTANCE.vo(authFunctionProvider.getDetail(funcId)));
    }

    /**
     * 新增和更新权限资源
     *
     * @return the string
     */
    @ResponseBody
    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增和更新菜单权限", notes = "新增和更新菜单权限")
    public Result<Void> saveOrUpdate(@ApiParam(value = "菜单权限form实体", required = true)AuthFunctionForm authFunctionForm) {
            AuthFunctionDTO funcDTO = AuthFunctionViewConverterWrapper.INSTANCE.dto(authFunctionForm);
            authFunctionProvider.saveOrUpdate(funcDTO);
            return StandardResult.succeed();
    }

    /**
     * 删除权限资源
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/delete/{funcId}")
    @ApiOperation(value = "删除权限资源", notes = "删除权限资源")
    @ApiImplicitParam(name = "funcId",value = "权限id", paramType = "path")
    public Result<Void> delete(@PathVariable("funcId") Long funcId) {
        AssertUtils.isTrue(!BaseValidate.errorLong(funcId), "参数错误");

        authFunctionProvider.delete(funcId);
        return StandardResult.succeed();
    }

    /**
     * 查询菜单权限结构树
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/getTreeData")
    @ApiOperation(value = "查询菜单权限结构树", notes = "查询菜单权限结构树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleId",value = "角色id", paramType = "query"),
        @ApiImplicitParam(name = "roleType",value = "角色类型", paramType = "query")
    })
    public Result<List<Map<String, Object>>> getTreeData(Long roleId, Integer roleType) {
            ScfUserVO scfUserVO= getSessionUser();
            //查询主账号
            ScfUserDTO ownerUser = scfUserProvider.getDetail(scfUserVO.getParentId());
            AssertUtils.notNull(ownerUser, "操作的数据不存在");
            Map<String, Object> params = new HashMap<>();
            params.put("page",1);
            params.put("limit",Integer.MAX_VALUE);
            params.put("userType",scfUserVO.getUserType());
            if (!isAdmin(scfUserVO)){
                if (AuthRoleTypeEnum.NORMAL.getValue().equals(roleType)) {
                    if (isBorrower(scfUserVO)) {//当前为新增或者编辑借款方普通角色：查询借款方所有权限
                        CompanyDTO companyDTO = companyProvider.getByOwnerId(scfUserVO.getParentId());
                        AssertUtils.notNull(companyDTO, "借款方公司不存在!");
                        AuthRoleDTO borrowerAllFuncRole = authRoleProvider.getByOwnerIdAndRoleType(companyDTO.getCreateOwnerId(),
                            AuthRoleTypeEnum.BORROWER_ALL_FUNC.getValue());
                        AssertUtils.notNull(borrowerAllFuncRole, "操作的数据不存在!");
                        params.put("roleId", borrowerAllFuncRole.getId());
                    } else {
                        params.put("roleId", ownerUser.getRoleId());
                    }
                } else {
                    params.put("roleId", roleId);
                }
            }
            IPage<AuthFunctionDTO> funcPage = authFunctionProvider.listPage(params);

            //编辑时：角色已经关联的权限id
            Set<Long> roleRelatedIds = new HashSet<>();
            if (AuthRoleTypeEnum.NORMAL.getValue().equals(roleType) && roleId != null && roleId > 0) {
//            if (roleId != null && roleId > 0) {
                params.remove("userType");
                params.put("roleId", roleId);
                params.put("deleted", DeleteEnum.N.getValue());
                IPage<AuthFunctionDTO> roleRelatedFuncPage = authFunctionProvider.listPage(params);
                if (roleRelatedFuncPage.getRecords() != null){
                    for (AuthFunctionDTO item : roleRelatedFuncPage.getRecords()) {
                        roleRelatedIds.add(item.getId());
                    }
                }
            }
            List<Map<String, Object>> result = assembleTreeFuncData(roleType, funcPage, roleRelatedIds);
            return StandardResult.succeed(result);
    }

    /**
     * 组装树形结构的权限数据
     * @param funcPage
     * @param funcIds
     * @return
     */
    @NotNull
    private List<Map<String, Object>> assembleTreeFuncData(Integer roleType, IPage<AuthFunctionDTO> funcPage,
                                                           Set<Long> funcIds) {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0L);
        map.put("pId", 0L);
        map.put("name", "全部功能");
        map.put("nocheck", true);
        map.put("open", true);
        result.add(map);
        if (funcPage.getRecords() != null){
            for (AuthFunctionDTO item : funcPage.getRecords()) {
//                if (item.getFuncName().endsWith("审核操作") && (AuthRoleTypeEnum.NORMAL.getValue().equals(roleType))) {//普通类角色不能包含"审核操作"
//                    continue;
//                }
                map = new HashMap<>();
                map.put("id", item.getId());
                map.put("pId", item.getParentId());
                map.put("name", item.getFuncName());
//                if (item.getParentId() == 0L) {
//                    map.put("open", true);
//                }

                if (AuthRoleTypeEnum.NORMAL.getValue().equals(roleType)) {
                    if (funcIds.contains(item.getId())) {
                        map.put("checked", true);
                    }
                } else {
                    map.put("checked", true);
                    map.put("chkDisabled", true);
                }

                if (funcIds.contains(item.getId())) {
                    map.put("checked", true);
                }

                result.add(map);
            }
        }
        return result;
    }
}
