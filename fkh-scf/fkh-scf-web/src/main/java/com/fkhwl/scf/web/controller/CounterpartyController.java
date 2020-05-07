package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.CounterpartyProvider;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.form.CounterpartyForm;
import com.fkhwl.scf.entity.vo.CounterpartyVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.CompanyContractViewConverterWrapper;
import com.fkhwl.scf.wrapper.CounterpartyViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.api.BaseCodes;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 控制器
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
@Slf4j
@Controller
@RequestMapping("/counterparty")
@Api(value = "/counterparty", tags = "交易对手信息相关接口")
public class CounterpartyController extends BaseController {

    @Resource
    private CounterpartyProvider counterpartyProvider;

    @Resource
    private CompanyContractProvider companyContractProvider;
    /**
     * 交易对手下拉列表
     *
     * @return 用户详情
     */
    @ResponseBody
    @PostMapping(value = "/listByCompanyId")
    @ApiOperation(value = "查询登录用户所在公司的所有交易对手下拉列表", notes = "查询交易对手下拉列表")
    public Result<List<CounterpartyVO>> listByCompanyId() {
            ScfUserVO scfUserVO= getSessionUser();
            List<CounterpartyVO> result = counterpartyProvider.listByCompanyId(scfUserVO.getCompanyId()).stream().map(CounterpartyViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList());
            return StandardResult.succeed(result);
    }


    /**
     * 通过 id 查询数据, 如果数据不存在, 应该直接抛出异常
     *
     * @param id 交易对手ID
     * @return 交易对手详情 result
     */
    @GetMapping("/getDetail")
    @ApiOperation(value = "交易对手详情", notes = "传入 id")
    @ApiParam(value = "交易对手ID", required = true)
    @ResponseBody
    public Result<CounterpartyVO> info( Long id) {
        CounterpartyDTO infoOptional = counterpartyProvider.findInfo(id);
        return StandardResult.succeed(CounterpartyViewConverterWrapper.INSTANCE.vo(infoOptional));
    }
    /**
     * 不带条件的查询交易对手全部数据
     *
     * @return 交易对手列表 result
     * @see CounterpartyController#page(Map)
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询全部交易对手列表, 不分页")
    @ResponseBody
    public Result<List<CounterpartyVO>> list() {
        IPage<CounterpartyDTO> page = counterpartyProvider.findPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), Collections.emptyMap());
        return StandardResult.succeed(page.convert(CounterpartyViewConverterWrapper.INSTANCE::vo).getRecords());
    }

    /**
     * 分页查询交易对手列表
     *
     * @param params 分页参数
     * @return 分页查询交易对手列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询交易对手列表")
    @ResponseBody
    public Result<Map<String, Object>> page(@RequestParam Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        super.fixPageNo(params);
        IPage<CounterpartyDTO> page = counterpartyProvider.findPage(Condition.getPage(params), params);
        result.put("counterpartyPage", page.convert(CounterpartyViewConverterWrapper.INSTANCE::vo));

        ScfUserVO scfUserVO= getSessionUser();
        CompanyContractDTO companyContractDTO=companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),Long.valueOf(params.get("companyBorrowerId").toString()));
        AssertUtils.notNull(companyContractDTO, "参数错误");
        result.put("companyContract", companyContractDTO);

        return StandardResult.succeed(result);
    }

    /**
     * 新增
     * @param counterpartyForm 参数
     * @return void
     */
    @PostMapping("/save")
    @ApiOperation(value = "新增交易对手", notes = "传入counterpartyForm")
    @ResponseBody
    public Result<Void> save(@ApiParam(value = "交易对手form实体", required = true)CounterpartyForm counterpartyForm) {
            ScfUserVO scfUserVO=super.getSessionUser();
            counterpartyForm.setCompanyCapitalId(scfUserVO.getCompanyId());
            // 当字段没有加唯一索引但是业务上需要唯一时, 你也可以使用这样方式来手动验证数据是否重复
            //        BaseCodes.FAILURE.isTrue(userService.usernameCheck(userForm.getUsername()));
            counterpartyProvider.save(CounterpartyViewConverterWrapper.INSTANCE.dto(counterpartyForm));
            return StandardResult.succeed(null);
    }

    /**
     * 修改交易对手
     *
     * @param counterpartyForm 用户实体
     * @return void result
     */
    @PostMapping("/updateBaseConfig")
    @ApiOperation(value = "修改交易对手", notes = "传入counterpartyForm")
    @ResponseBody
    public Result<Void> updateBaseConfig(@ApiParam(value = "交易对手form实体", required = true)CounterpartyForm counterpartyForm) {
        // 更新操作需要单独验证 id 是否存在, 因为 id 字段在 AbstractBaseEntity 中, 且在子类中不能重复定义
        BaseCodes.PARAM_VERIFY_ERROR.notNull(counterpartyForm.getId(), "counterpartyForm.id: 不能为空");
        //修改，需要上传新的合同文件
        counterpartyProvider.updateBaseConfig(CounterpartyViewConverterWrapper.INSTANCE.dto(counterpartyForm));
        return StandardResult.succeed();
    }

    /**
     * 根据ID删除交易对手数据
     *
     * @param ids 交易对手ID组成的字符串，多个ID用逗号隔开
     * @return void result
     */
    @DeleteMapping
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @ResponseBody
    public Result<Void> remove(@ApiParam(value = "主键集合", required = true) @RequestParam List<Long> ids) {
        counterpartyProvider.delete(ids);
        return StandardResult.succeed();
    }

    /**
     * 跳转客户管理-交易对手列表
     *
     * @return the string
     */
    @GetMapping(value = "/counterpartyList")
    public String toCounterpartyPageMgmt(Long companyBorrowerId, Model model , HttpServletRequest req) {
        model.addAttribute("companyBorrowerId", companyBorrowerId);
        ScfUserVO scfUserVO= getSessionUser();
        CompanyContractDTO companyContractDTO=companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),companyBorrowerId);
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContractDTO));
        return Views.COUNTERPARTY_LIST_PAGE;
    }

    /**
     * 跳转客户合同-新增交易对手
     *
     * @return the string
     */
    @GetMapping(value = "/counterpartyDetail")
    public String toCounterpartyDetail(Model model, Long companyBorrowerId, Long companyContractId) {
        ScfUserVO scfUserVO= getSessionUser();
        CompanyContractDTO companyContractDTO=companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),companyBorrowerId);
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContractDTO));
        model.addAttribute("companyBorrowerId", companyBorrowerId);
        model.addAttribute("companyContractId", companyContractId);
        model.addAttribute("todo", 1);//去界面新增
        return Views.COUNTERPARTY_DETAIL_PAGE;
    }
    /**
     * 跳转客户合同-修改交易对手
     *
     * @return the string
     */
    @GetMapping(value = "/counterpartyUpdate")
    public String toCounterpartyUpdate(Model model, Long id, Integer todo) {
        CounterpartyDTO counterpartyDTO=counterpartyProvider.findInfo(id);
        ScfUserVO scfUserVO= getSessionUser();
        CompanyContractDTO companyContractDTO=companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),counterpartyDTO.getCompanyBorrowerId());
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContractDTO));
        model.addAttribute("companyBorrowerId", counterpartyDTO.getCompanyBorrowerId());
        model.addAttribute("counterpartyDTO", CounterpartyViewConverterWrapper.INSTANCE.vo(counterpartyDTO));
        model.addAttribute("todo", todo);//去界面修改或者查看
        return Views.COUNTERPARTY_DETAIL_PAGE;
    }
    /**
     * 跳转审核交易对手
     *
     * @return the string
     */
    @RequestMapping(value = "/counterpartyReview", method = RequestMethod.GET)
    @ApiOperation(value = "跳转审核交易对手",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "交易对手id", paramType = "query")
    })
    public String counterpartyReview(Model model , Long id, HttpServletRequest req) {
        CounterpartyDTO counterpartyDTO=counterpartyProvider.findInfo(id);
        ScfUserVO scfUserVO= getSessionUser();
        CompanyContractDTO companyContractDTO=companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),counterpartyDTO.getCompanyBorrowerId());
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContractDTO));
        model.addAttribute("companyBorrowerId", counterpartyDTO.getCompanyBorrowerId());
        model.addAttribute("counterpartyDTO", CounterpartyViewConverterWrapper.INSTANCE.vo(counterpartyDTO));
        model.addAttribute("todo", 3);//去界面审核
        return Views.COUNTERPARTY_DETAIL_PAGE;
    }
    /**
     * 审核交易对手
     *
     * @return void result
     */
    @PostMapping("/review")
    @ApiOperation(value = "审核客户合同", notes = "传入counterpartyForm",httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "交易对手ID", paramType = "query"),
        @ApiImplicitParam(name = "status",value = "审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）", paramType = "query"),
    })
    @ResponseBody
    public Result<Void> review(@RequestParam Map<String, Object> params) {
        // 更新操作需要单独验证 id 是否存在, 因为 id 字段在 AbstractBaseEntity 中, 且在子类中不能重复定义
        BaseCodes.PARAM_VERIFY_ERROR.notNull(params.get("id"), "companyContractForm.id: 不能为空");
        // 新增或修改后，都需要审核
        counterpartyProvider.review(params);
        return StandardResult.succeed();
    }
}
