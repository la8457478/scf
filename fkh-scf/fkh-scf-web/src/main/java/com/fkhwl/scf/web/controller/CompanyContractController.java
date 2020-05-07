package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.form.CompanyContractForm;
import com.fkhwl.scf.entity.vo.CompanyContractVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.web.util.Views;
import com.fkhwl.scf.wrapper.CompanyContractViewConverterWrapper;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 企业合同 控制器
 *
 * @author sj
 * @since 2020-02-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/companyContract")
@Api(value = "企业合同", tags = "企业合同接口")
@Slf4j
public class CompanyContractController extends BaseController {

	private CompanyContractProvider companyContractProvider;


    /**
     * 通过 id 查询数据, 如果数据不存在, 应该直接抛出异常
     *
     * @param userId 企业合同ID
     * @return 企业合同详情 result
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "详情", notes = "传入 id")
    @ApiParam(value = "企业合同ID", required = true)
    @ResponseBody
    public Result<CompanyContractVO> info(@PathVariable("id") Long userId) {
        CompanyContractDTO infoOptional = companyContractProvider.findInfo(userId);
        return StandardResult.succeed(CompanyContractViewConverterWrapper.INSTANCE.vo(infoOptional));
    }
    /**
     * 不带条件的查询企业合同全部数据
     *
     * @return 企业合同列表 result
     * @see CompanyContractController#page(Map)
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询全部企业合同列表, 不分页",httpMethod = "GET")
    @ResponseBody
    public Result<List<CompanyContractVO>> list() {
        IPage<CompanyContractDTO> page = companyContractProvider.findPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), Collections.emptyMap());
        return StandardResult.succeed(page.convert(CompanyContractViewConverterWrapper.INSTANCE::vo).getRecords());
    }

    /**
     * 企业合同详情
     *
     * @param params 分页参数
     * @return 分页查询企业合同列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询企业合同列表",httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page",value = "页码", paramType = "query"),
        @ApiImplicitParam(name = "companyBorrowerName",value = "客户公司名称", paramType = "query"),
    })
    @ResponseBody
    public Result<IPage<CompanyContractVO>> page(@RequestParam Map<String, Object> params) {
        ScfUserVO scfUserVO=super.getSessionUser();
        params.put("companyCapitalId",scfUserVO.getCompanyId());
        super.fixPageNo(params);
        IPage<CompanyContractDTO> page = companyContractProvider.findPage(Condition.getPage(params), params);
        return StandardResult.succeed(page.convert(CompanyContractViewConverterWrapper.INSTANCE::vo));
    }

    /**
     * 新增
     *
     * @param scfConfigForm 参数
     * @return void
     */
    @PostMapping("/saveOrUpdate")
	@ApiOperation(value = "新增", notes = "传入scfConfigForm",httpMethod = "GET")
    @ResponseBody
	public Result<Void> save(@ApiParam(value = "保存参数(按界面数据传入)", required = true)CompanyContractForm scfConfigForm) {
        ScfUserVO scfUserVO=super.getSessionUser();
        //设置资方企业ID
        scfConfigForm.setCompanyCapitalId(scfUserVO.getCompanyId());
        //借款方企业ID,由界面选择设置project/page
        //保存
        companyContractProvider.save(CompanyContractViewConverterWrapper.INSTANCE.dto(scfConfigForm));
		return StandardResult.succeed(null);
	}

//    /**
//     * 修改
//     *
//     * @param scfConfig 参数
//     * @return void
//     */
//	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	@ApiOperation(value = "修改", notes = "传入scfConfig")
//	public Result<Void> update(@Valid @RequestBody ScfConfig scfConfig) {
//        companyContractProvider.updateById(CompanyContractViewConverterWrapper.INSTANCE.from(scfConfig));
//        return ok(null);
//	}
    /**
     * 修改客户合同
     *
     * @param companyContractForm 用户实体
     * @return void result
     */
    @PostMapping("/updateBaseConfig")
    @ApiOperation(value = "修改合同", notes = "传入companyContractForm")
    @ResponseBody
    public Result<Void> updateBaseConfig(@ApiParam(value = "修改合同信息参数", required = true) CompanyContractForm companyContractForm) {
        // 更新操作需要单独验证 id 是否存在, 因为 id 字段在 AbstractBaseEntity 中, 且在子类中不能重复定义
        BaseCodes.PARAM_VERIFY_ERROR.notNull(companyContractForm.getId(), "companyContractForm.id: 不能为空");
            companyContractProvider.updateBaseConfig(CompanyContractViewConverterWrapper.INSTANCE.dto(companyContractForm));
            return StandardResult.succeed();
    }

    /**
     * 审核客户合同
     *
     * @return void result
     */
    @PostMapping("/review")
    @ApiOperation(value = "审核客户合同", notes = "传入counterpartyForm",httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "合同ID", paramType = "query"),
        @ApiImplicitParam(name = "status",value = "审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）", paramType = "query"),
    })
    @ResponseBody
    public Result<Void> review(@RequestParam Map<String, Object> params) {
        // 更新操作需要单独验证 id 是否存在, 因为 id 字段在 AbstractBaseEntity 中, 且在子类中不能重复定义contractNumber
        BaseCodes.PARAM_VERIFY_ERROR.notNull(params.get("id"), "companyContractForm.id: 不能为空");
        // 新增的才审核，审核通过前，可再次修改，审核通过后，不可再修改
        companyContractProvider.review(params);
        return StandardResult.succeed();
    }

    /**
     * 根据ID删除企业合同数据
     *
     * @param ids 企业合同ID组成的字符串，多个ID用逗号隔开
     * @return void result
     */
    @DeleteMapping
    @ApiOperation(value = "逻辑删除", notes = "传入ids",httpMethod = "GET")
    @ResponseBody
    public Result<Void> remove(@ApiParam(value = "主键集合", required = true) @RequestParam List<Long> ids) {
        companyContractProvider.delete(ids);
        return StandardResult.succeed();
    }


    /**
     * 跳转新增客户合同
     *
     * @return the string
     */
    @GetMapping(value = "/companyContractDetail")
    @ApiOperation(value = "跳转新增客户合同",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyContractId",value = "合同id", paramType = "query")
    })
    public String toCompanyContractDetail(Model model , Integer from, HttpServletRequest req) {
        model.addAttribute("todo", 1);//去界面新增
        model.addAttribute("from", from);//来源
        return Views.COMPANY_CONTRACT_DETAIL_PAGE;
    }
    /**
     * 跳转修改客户合同
     *
     * @return the string
     */
    @RequestMapping(value = "/companyContractUpdate", method = RequestMethod.GET)
    @ApiOperation(value = "跳转新增客户合同",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "合同id", paramType = "query")
    })
    public String toCompanyContractUpdate(Model model , Long id, Integer todo,HttpServletRequest req) {
        CompanyContractDTO companyContract = this.companyContractProvider.findInfo(id);
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContract));
        model.addAttribute("todo", todo==null?2:todo);//去界面修改
        model.addAttribute("from", 2);//来源
        return Views.COMPANY_CONTRACT_DETAIL_PAGE;
    }

    /**
     * 跳转审核客户合同
     *
     * @return the string
     */
    @RequestMapping(value = "/companyContractReview", method = RequestMethod.GET)
    @ApiOperation(value = "跳转审核客户合同",httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",value = "合同id", paramType = "query")
    })
    public String companyContractReview(Model model , Long id, HttpServletRequest req) {
        CompanyContractDTO companyContract = this.companyContractProvider.findInfo(id);
        model.addAttribute("companyContract", CompanyContractViewConverterWrapper.INSTANCE.vo(companyContract));
        model.addAttribute("todo", 3);//去界面审核
        model.addAttribute("from", 2);//来源
        return Views.COMPANY_CONTRACT_DETAIL_PAGE;
    }
    /**根据借款方companyId合同详情
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/getByCompanyId")
    @ApiOperation(value = "根据借款方companyId合同详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyBorrowerId",value = "借款方公司id", paramType = "query")
    })
    public Result<CompanyContractVO> getByCompanyId(Long companyBorrowerId) {
            ScfUserVO scfUserVO = getSessionUser();
            AssertUtils.notNull(companyBorrowerId, "参数错误");
            CompanyContractDTO companyContractDTO=  companyContractProvider.getByCompanyId(scfUserVO.getCompanyId(),companyBorrowerId);
            return StandardResult.succeed(CompanyContractViewConverterWrapper.INSTANCE.vo(companyContractDTO));
    }
}
