package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.ProjectListDTO;
import com.fkhwl.scf.entity.form.ProjectForm;
import com.fkhwl.scf.entity.vo.ProjectVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.ProjectViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.mybatis.support.Condition;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

/**
 * 项目 控制器
 *
 * @author sj
 * @since 2020-02-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/project")
@Api(value = "项目", tags = "项目接口")
public class ProjectController extends BaseController {

	private ProjectProvider projectProvider;

    /**
     * 通过 id 查询数据, 如果数据不存在, 应该直接抛出异常
     *
     * @param userId 项目ID
     * @return 项目详情 result
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "详情", notes = "传入 id")
    @ApiParam(value = "项目ID", required = true)
    @ResponseBody
    public Result<ProjectVO> info(@PathVariable("id") Long userId) {
        ProjectDTO infoOptional = projectProvider.getDetail(userId);
        return StandardResult.succeed(ProjectViewConverterWrapper.INSTANCE.vo(infoOptional));
    }
    /**
     * 项目详情
     *
     * @param params 分页参数
     * @return 分页查询项目列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询项目列表")
    @ResponseBody
    public Result<IPage<ProjectListDTO>> page(@RequestParam Map<String, Object> params) {
        super.fixPageNo(params);
        ScfUserVO sessionUser = getSessionUser();
        params.put("companyId", sessionUser.getCompanyId());
        IPage<ProjectListDTO> page = projectProvider.listPage(Condition.getPage(params), params);
        return StandardResult.succeed(page);
    }

    /**
     * 新增
     *
     * @param projectForm 参数
     * @return void
     */
    @PostMapping("/saveOrUpdate")
	@ApiOperation(value = "新增或修改项目", notes = "传入projectForm")
    @ResponseBody
	public Result<Void> save(@ApiParam(value = "项目form实体", required = true)ProjectForm projectForm) {
        // 当字段没有加唯一索引但是业务上需要唯一时, 你也可以使用这样方式来手动验证数据是否重复
//        BaseCodes.FAILURE.isTrue(userService.usernameCheck(userForm.getUsername()));
        projectProvider.saveOrUpdate(ProjectViewConverterWrapper.INSTANCE.dto(projectForm));
		return StandardResult.succeed(null);
	}

    /**
     * 根据ID删除项目数据
     *
     * @param ids 项目ID组成的字符串，多个ID用逗号隔开
     * @return void result
     */
    @DeleteMapping
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @ResponseBody
    public Result<Void> remove(@ApiParam(value = "主键集合", required = true) @RequestParam List<Long> ids) {
        projectProvider.delete(ids);
        return StandardResult.succeed();
    }

    /**
     * 跳转项目详情
     *
     * @return the string
     */
    @RequestMapping(value = "/projectDetail", method = RequestMethod.GET)
    public String toProjectDetail(Model model , Long projectId,HttpServletRequest req) {

        ProjectDTO project = this.projectProvider.getDetail(projectId);
        AssertUtils.notNull(project,"项目不存在");
        model.addAttribute("project", project);
        if(project.getTransportDutyUserName().equals(project.getCreateOwnerName())){
            //运输方
            model.addAttribute("createPart","承运方");
        }else if (project.getSendDutyUserName().equals(project.getCreateOwnerName())){
            //发货方
            model.addAttribute("createPart","发货方");
        }else {
            //收货方
            model.addAttribute("createPart","收货方");
        }

        return Views.PROJECT_DETAIL_PAGE;
    }
}
