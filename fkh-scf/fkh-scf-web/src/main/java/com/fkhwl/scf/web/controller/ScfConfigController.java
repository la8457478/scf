package com.fkhwl.scf.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.ScfConfigProvider;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.form.ScfConfigForm;
import com.fkhwl.scf.entity.vo.ScfConfigVO;
import com.fkhwl.scf.wrapper.ScfConfigViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;
import com.fkhwl.starter.common.base.AbstractBaseEntity;
import com.fkhwl.starter.core.api.BaseCodes;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

/**
 * 系统配置 控制器
 *
 * @author sj
 * @since 2020-02-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/scfConfig")
@Api(value = "系统配置", tags = "系统配置接口")
public class ScfConfigController extends BaseController {

	private ScfConfigProvider scfConfigService;


    /**
     * 通过 id 查询数据, 如果数据不存在, 应该直接抛出异常
     *
     * @param userId 系统配置ID
     * @return 系统配置详情 result
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "详情", notes = "传入 id")
    @ApiParam(value = "系统配置ID", required = true)
    @ResponseBody
    public Result<ScfConfigVO> info(@PathVariable("id") Long userId) {
        Optional<ScfConfigDTO> infoOptional = scfConfigService.findInfo(userId);
        return infoOptional.map(infoDto -> StandardResult.succeed(ScfConfigViewConverterWrapper.INSTANCE.vo(infoDto)))
            .orElseThrow(() -> BaseCodes.DATA_ERROR.newException("[{}]", userId));
    }
    /**
     * 不带条件的查询系统配置全部数据
     *
     * @return 系统配置列表 result
     * @see ScfConfigController#page(java.util.Map)
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询全部系统配置列表, 不分页")
    @ResponseBody
    public Result<List<ScfConfigVO>> list() {
        IPage<ScfConfigDTO> page = scfConfigService.findPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), Collections.emptyMap());
        return StandardResult.succeed(page.convert(ScfConfigViewConverterWrapper.INSTANCE::vo).getRecords());
    }

    /**
     * 系统配置详情
     *
     * @param params 分页参数
     * @return 分页查询系统配置列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询系统配置列表")
    @ResponseBody
    public Result<IPage<ScfConfigVO>> page(@RequestParam Map<String, Object> params) {
        super.fixPageNo(params);
        IPage<ScfConfigDTO> page = scfConfigService.findPage(Condition.getPage(params), params);
        return StandardResult.succeed(page.convert(ScfConfigViewConverterWrapper.INSTANCE::vo));
    }

    /**
     * 新增
     *
     * @param scfConfigForm 参数
     * @return void
     */
    @PostMapping("/saveOrUpdate")
	@ApiOperation(value = "新增或修改", notes = "传入scfConfigForm")
    @ResponseBody
	public Result<Void> save(ScfConfigForm scfConfigForm) {
        // 当字段没有加唯一索引但是业务上需要唯一时, 你也可以使用这样方式来手动验证数据是否重复
//        BaseCodes.FAILURE.isTrue(userService.usernameCheck(userForm.getUsername()));
        scfConfigService.saveOrUpdate(ScfConfigViewConverterWrapper.INSTANCE.dto(scfConfigForm));
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
//        scfConfigService.updateById(ScfConfigViewConverterWrapper.INSTANCE.from(scfConfig));
//        return ok(null);
//	}
    /**
     * 修改系统配置
     *
     * @param userForm 用户实体
     * @return void result
     * @see AbstractBaseEntity
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改系统配置", notes = "传入templateUser")
    @ResponseBody
    public Result<Void> update(@RequestBody @Validated(ScfConfigForm.UpdateGroup.class)@ApiParam(value = "系统参数", required = true) ScfConfigForm userForm) {
        // 更新操作需要单独验证 id 是否存在, 因为 id 字段在 AbstractBaseEntity 中, 且在子类中不能重复定义
        BaseCodes.PARAM_VERIFY_ERROR.notNull(userForm.getId(), "userForm.id: 不能为空");
        scfConfigService.saveOrUpdate(ScfConfigViewConverterWrapper.INSTANCE.dto(userForm));
        return StandardResult.succeed();
    }

    /**
     * 根据ID删除系统配置数据
     *
     * @param configId
     * @return void result
     */
    @GetMapping(value = "/delete/{configId}")
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @ResponseBody
    public Result<Void> remove(@PathVariable(value = "configId", required = true) Long configId) {
        scfConfigService.delete(configId);
        return StandardResult.succeed();
    }


    /**
     * 父类列表
     * @return 系统配置详情 result
     */
    @GetMapping("/findAllParentList")
    @ApiOperation(value = "查询父类配置列表")
    @ResponseBody
    public Result<List<ScfConfigVO>> findAllParentList() {
        List<ScfConfigDTO> infoOptional = scfConfigService.findAllParentList();
        return StandardResult.succeed(infoOptional.stream().map(ScfConfigViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
    }
}
