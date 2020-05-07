package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.DepartmentProvider;
import com.fkhwl.scf.entity.dto.DepartmentDTO;
import com.fkhwl.scf.entity.form.DepartmentForm;
import com.fkhwl.scf.entity.vo.DepartmentVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.DepartmentViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
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
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 部门controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/department")
@Api(value = "/department", tags = "部门接口")
public class DepartmentController extends BaseController {

    @Resource
    private DepartmentProvider departmentProvider;

	/**
	 * 查询部门结构树
	 *
	 * @return the string
	 */
    @ResponseBody
	@GetMapping(value = "/getTreeData")
    @ApiOperation(value = "查询部门结构树", notes = "查询部门结构树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyName",value = "企业名称", paramType = "query"),
        @ApiImplicitParam(name = "deptId",value = "部门id",paramType = "query")
    })
	public Result<List<Map<String, Object>>> getTreeData(String companyName, Long deptId) {
            ScfUserVO scfUserVO= getSessionUser();
            Map<String, Object> params = new HashMap<>();
            params.put("page",1);
            params.put("limit",Integer.MAX_VALUE);
            params.put("ownerId",scfUserVO.getParentId());
            IPage<DepartmentDTO> departmentPage = departmentProvider.listPage(params);
            if (companyName == null) {
                companyName = scfUserVO.getCompanyName();
            }
            List<Map<String, Object>> result = assembleTreeDeptData(companyName, deptId, departmentPage);
            return StandardResult.succeed(result);
    }

    /**
     * 查询部门详情
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/getDetail/{departmentId}")
    @ApiOperation(value = "查询部门详情", notes = "查询部门详情")
    @ApiImplicitParam(name = "userId",value = "用户id", paramType = "path")
    public Result<DepartmentVO> getDetail(@PathVariable("departmentId")Long departmentId) {
            AssertUtils.isTrue(!BaseValidate.errorLong(departmentId),"参数错误");
            return StandardResult.succeed(DepartmentViewConverterWrapper.INSTANCE.vo(departmentProvider.getDetail(departmentId)));
    }

    /**
     * 新增和更新部门
     *
     * @return the string
     */
    @ResponseBody
    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增和更新部门", notes = "新增和更新部门")
    public Result<Void> saveOrUpdate(@ApiParam(value = "部门form实体", required = true)DepartmentForm departmentForm) {
            ScfUserVO scfUserVO= getSessionUser();
            departmentForm.setOwnerId(scfUserVO.getParentId());
            departmentProvider.saveOrUpdate(DepartmentViewConverterWrapper.INSTANCE.dto(departmentForm));
            return StandardResult.succeed();
    }

    /**
     * 删除部门
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/delete")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @ApiImplicitParam(name = "deptIds",value = "部门id字符串，多个之间使用英文逗号分隔", required = true, paramType = "query")
    public Result<Void> delete(String deptIds) {
            AssertUtils.notNull(deptIds, "参数错误");
            departmentProvider.delete(deptIds);
            return StandardResult.succeed();
    }

    /**
     * 组装树形结构的部门数据
     * @param companyName
     * @param departmentPage
     * @return
     */
    @NotNull
    private List<Map<String, Object>> assembleTreeDeptData(String companyName, Long deptId, IPage<DepartmentDTO> departmentPage) {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0L);
        map.put("pId", 0L);
        map.put("name", companyName);
        map.put("nocheck", true);
        map.put("open", true);
        result.add(map);
        if (departmentPage.getRecords() != null){
            for (DepartmentDTO item : departmentPage.getRecords()) {
                map = new HashMap<>();
                map.put("id", item.getId());
                map.put("pId", item.getParentId());
                map.put("name", item.getDeptName());
                if (item.getId().equals(deptId)) {
                    map.put("checked", true);
                }
                if (item.getParentId() == 0L) {
                    map.put("open", true);
                }
                result.add(map);
            }
        }
        return result;
    }
}
