package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ProgramProvider;
import com.fkhwl.scf.ProjectProvider;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.form.ProgramForm;
import com.fkhwl.scf.entity.vo.ProgramVO;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.ProgramViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.mybatis.support.Condition;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;


/**
 * 计划 控制器
 *
 * @author sj
 * @since 2020-02-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/program")
@Api(value = "计划", tags = "计划接口")
public class ProgramController extends BaseController {

    private ProgramProvider programProvider;


    private ProjectProvider projectProvider;
    /**
     * 通过 id 查询数据, 如果数据不存在, 应该直接抛出异常
     *
     * @param userId 计划ID
     * @return 计划详情 result
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "计划详情", notes = "传入 id")
    @ApiParam(value = "计划ID", required = true)
    @ResponseBody
    public Result<ProgramVO> info(@PathVariable("id") Long userId) {
        ProgramDTO infoOptional = programProvider.getDetail(userId);
        return StandardResult.succeed(ProgramViewConverterWrapper.INSTANCE.vo(infoOptional));
    }
    /**
     * 计划详情
     *
     * @param params 分页参数
     * @return 分页查询计划列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询计划列表")
    @ResponseBody
    public Result<IPage<ProgramVO>> page(@RequestParam Map<String, Object> params) {
        super.fixPageNo(params);
        IPage<ProgramDTO> page = programProvider.listPage(Condition.getPage(params), params);
        return StandardResult.succeed(page.convert(ProgramViewConverterWrapper.INSTANCE::vo));
    }

    /**
     * 新增
     *
     * @param scfConfigForm 参数
     * @return void
     */
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "新增或修改计划", notes = "")
    @ResponseBody
    public Result<Void> save(@ApiParam(value = "计划form实体", required = true)ProgramForm scfConfigForm) {
        // 当字段没有加唯一索引但是业务上需要唯一时, 你也可以使用这样方式来手动验证数据是否重复
        //        BaseCodes.FAILURE.isTrue(userService.usernameCheck(userForm.getUsername()));
        programProvider.saveOrUpdate(ProgramViewConverterWrapper.INSTANCE.dto(scfConfigForm));
        return StandardResult.succeed(null);
    }

    /**
     * 根据ID删除计划数据
     *
     * @param id 计划ID组成的字符串，多个ID用逗号隔开
     * @return void result
     */
    @DeleteMapping
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @ResponseBody
    public Result<Void> remove(@ApiParam(value = "主键集合", required = true) @RequestParam Long id) {
        programProvider.delete(id);
        return StandardResult.succeed();
    }


    //    /**
    //     * 父类列表
    //     * @return 计划详情 result
    //     */
    //    @GetMapping("/findAllParentList")
    //    @ApiOperation(value = "父类列表")
    //    public Result<List<ProgramVO>> findAllParentList() {
    //        List<ProgramDTO> infoOptional = programProvider.findAllParentList();
    //        return StandardResult.succeed(infoOptional.stream().map(ProgramViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
    //    }

    /**
     * 查看计划
     *
     * @param model     the model
     * @param projectId the project id
     * @param programId    the plan id
     * @param req       the req
     * @return string
     */
    @RequestMapping(value = "/showPlanDetail")
    public String showPlanDetail(Model model, Long projectId, Long programId,
                                 HttpServletRequest req) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("programId", programId);

        ProjectDTO project = this.projectProvider.getDetail(projectId);
        //        DataValidateException.notNull(project,RespCode.PARAMETER_ERROR);
        model.addAttribute("project", project);
        model.addAttribute("projectMaterialType", project.getMaterialType());

        //        AppConfig appConfig = appConfigService.getConfigByConfigKey(AppConfig.FENCE_RANGE_SIZE);
        //        model.addAttribute("appConfig", appConfig);
        //        model.addAttribute("defaultRadius", Const.DEFAULT_USER_DICTIONARY_RADIUS);

        //        List<ProjectLoadLine> projectLoadLines=projectLoadLineService.listByProjectId(projectId);
        //        model.addAttribute("projectLoadLines",projectLoadLines);

        //        boolean messageSwitch=appConfigService.containsValue(AppConfigEnum.PROJECT_MESSAGE_SWITCH.getCacheKey(),projectId,Const.JOIN_CHAR);
        //        model.addAttribute("messageSwitch", messageSwitch);

        return "includes/planDetailDialog";
    }
    /**
     * 跳转项目-计划列表
     *
     * @return the string
     */
    @GetMapping(value = "/programList")
    public String toProgramPageMgmt(Long projectId, Model model ,HttpServletRequest req) {
        model.addAttribute("projectId", projectId);
        return Views.PROGRAM_LIST_PAGE;
    }

    /**
     * 跳转项目-计划详情
     *
     * @return the string
     */
    @GetMapping(value = "/programDetail")
    public String toProgramDetail(Model model, Long projectId, Long programId , HttpServletRequest req) {
        model.addAttribute("projectId", projectId);
        model.addAttribute("planId", programId);

        ProjectDTO project = this.projectProvider.getDetail(projectId);
        //        DataValidateException.notNull(project,RespCode.PARAMETER_ERROR);
        model.addAttribute("project", project);
        ProgramDTO programDTO = this.programProvider.getDetail(programId);
        //        DataValidateException.notNull(project,RespCode.PARAMETER_ERROR);
        model.addAttribute("program", programDTO);
        model.addAttribute("projectMaterialType", project.getMaterialType());
        return Views.PROGRAM_DETAIL_PAGE;
    }
}
