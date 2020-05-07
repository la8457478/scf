package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.form.CompanyForm;
import com.fkhwl.scf.entity.vo.CompanyVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.wrapper.CompanyViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * 企业controller
 * @author: chenli
 * @emali: chenli@fkhwl.com
 * @Date: 2018年08月09日 10:03
 * @Description:
 */
@Slf4j
@Controller
@RequestMapping("/company")
@Api(value = "/company", tags = "企业接口")
public class CompanyController extends BaseController {

    @Resource
    private CompanyProvider companyProvider;
    /**
     * 不带条件的查询企业合同全部数据
     *
     * @return 企业合同列表 result
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询全部企业合同列表, 不分页")
    @ResponseBody
    public Result<List<CompanyVO>> list(@RequestParam Map<String, Object> params) {
        if(params==null){
            params= new HashMap<>();
        }
        ScfUserVO scfUserVO=super.getSessionUser();
        if(!isAdmin(scfUserVO)){//非平台管理员
            params.put("createOwnerId",scfUserVO.getParentId());
        }
        IPage<CompanyDTO> page = companyProvider.findPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), params);
        return StandardResult.succeed(page.convert(CompanyViewConverterWrapper.INSTANCE::vo).getRecords());
    }
	/**
	 * 分页获取企业列表
	 *
	 * @return the string
	 */
    @ResponseBody
	@PostMapping(value = "/listPage")
    @ApiOperation(value = "分页查询企业列表", notes = "分页查询企业列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "companyName",value = "企业名称", paramType = "query"),
        @ApiImplicitParam(name = "companyType",value = "企业类型",paramType = "query"),
        @ApiImplicitParam(name = "pageNo",value = "页码", paramType = "query")
    })
	public Result<IPage<CompanyVO>> listPage(String companyName, Integer companyType, Integer pageNo) {
            ScfUserVO sessionUser = super.getSessionUser();
            Map<String, Object> params = new HashMap<>();
            params.put("companyName",companyName);
            params.put("companyType",companyType);
            params.put("page",pageNo);
            params.put("ownerId",sessionUser.getParentId());
            params.put("createOwnerId",sessionUser.getId());
            IPage<CompanyVO> result = companyProvider.listPage(params).convert(CompanyViewConverterWrapper.INSTANCE::vo);
            return StandardResult.succeed(result);
    }

    /**
     * 查询企业详情
     *
     * @return the string
     */
    @ResponseBody
    @GetMapping(value = "/getDetail/{companyId}")
    @ApiOperation(value = "查询企业详情", notes = "查询企业详情")
    @ApiImplicitParam(name = "companyId",value = "企业id", paramType = "path")
    public Result<CompanyVO> getDetail(@PathVariable("companyId")Long companyId) {
            AssertUtils.notNull(companyId, "参数错误");
            return StandardResult.succeed(CompanyViewConverterWrapper.INSTANCE.vo(companyProvider.getDetail(companyId)));
    }

    /**
     * 新增和更新企业
     *
     * @return the string
     */
    @ResponseBody
    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增和更新企业", notes = "新增和更新企业")
    public Result<Void> saveOrUpdate(@ApiParam(value = "企业form实体", required = true)CompanyForm companyForm) {
            ScfUserVO sessionUser = super.getSessionUser();
            companyForm.setEstablishingTime(DateUtils.parse(companyForm.getEstablishingTimeStr(),DateUtils.DATETIME_FORMAT));
            CompanyDTO companyDTO = CompanyViewConverterWrapper.INSTANCE.dto(companyForm);
            companyDTO.setCreateUserId(sessionUser.getId());
            companyDTO.setCreateOwnerId(sessionUser.getParentId());
            companyProvider.saveOrUpdate(companyDTO);
            return StandardResult.succeed();
    }

    /**
     * 删除企业
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{companyId}")
    @ApiOperation(value = "删除企业", notes = "删除企业")
    @ApiImplicitParam(name = "companyId",value = "企业id", paramType = "path")
    public Result<Void> delete(@PathVariable("companyId")Long companyId) {
            AssertUtils.notNull(companyId, "参数错误");
            companyProvider.delete(companyId);
            return StandardResult.succeed();
    }
    /**
     * 删除企业
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/saveAccountMobileNos")
    @ApiOperation(value = "添加账单短信设置手机号", notes = "添加账单短信设置手机号")
    @ApiImplicitParam(name = "accountMobileNos",value = "账单手机号", paramType = "string")
    public Result<Void> saveMobiles(String accountMobileNos) {
           ScfUserVO scfUserVO =  getSessionUser();
           AssertUtils.isTrue(accountMobileNos.split("\n").length<=10,"最多输入10个电话号码！");
            companyProvider.updateAccountMobileNos(scfUserVO.getCompanyId(),accountMobileNos);
            return StandardResult.succeed();
    }
}
