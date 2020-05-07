package com.fkhwl.scf.web.controller;

import com.fkhwl.scf.ScfConfigProvider;
import com.fkhwl.scf.ScfUserConfigProvider;
import com.fkhwl.scf.entity.dto.ScfConfigDTO;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.form.ScfUserConfigForm;
import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.wrapper.ScfConfigViewConverterWrapper;
import com.fkhwl.scf.wrapper.ScfUserConfigViewConverterWrapper;
import com.fkhwl.starter.basic.Result;
import com.fkhwl.starter.basic.StandardResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

/**
 * 用户配置 控制器
 *
 * @author chenli
 * @since 2020-02-19
 */
@Controller
@AllArgsConstructor
@RequestMapping("/scfUserConfig")
@Api(value = "loanReviewConfig", tags = "用户配置")
public class ScfUserConfigController extends BaseController {

	private ScfUserConfigProvider scfUserConfigProvider;

    private ScfConfigProvider scfConfigProvider;
    /**
     * 查看用户配置列表
     *
     * @return 用户配置列表 result
     * @see ScfConfigController#page(Map)
     */
    @GetMapping("/listConfig")
    @ApiOperation(value = "查看用户配置列表")
    @ResponseBody
    public Result listConfig(String configKey) {
        ScfUserVO scfUserVO=super.getSessionUser();
        if(super.isAdmin(scfUserVO)){
            List<ScfConfigDTO> list= scfConfigProvider.getConfigsByParentConfigKey(configKey);
            return StandardResult.succeed(list.stream().map(ScfConfigViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
        }else{
            List<ScfUserConfigDTO> list= scfUserConfigProvider.getUserConfigsByConfigIdList(configKey,scfUserVO.getParentId());
            return StandardResult.succeed(list.stream().map(ScfUserConfigViewConverterWrapper.INSTANCE::vo).collect(Collectors.toList()));
        }
    }


    /**
     * 查看用户配置详情
     *
     * @return 用户配置详情 result
     * @see ScfConfigController#page(Map)
     */
    @GetMapping("/getConfig")
    @ApiOperation(value = "查看用户配置详情")
    @ResponseBody
    public Result getConfig(String configKey) {
        ScfUserVO scfUserVO=super.getSessionUser();
        if(super.isAdmin(scfUserVO)){
            return StandardResult.succeed(ScfConfigViewConverterWrapper.INSTANCE.vo(scfConfigProvider.getConfigByConfigKey(configKey)));
        }else{
            return StandardResult.succeed(ScfUserConfigViewConverterWrapper.INSTANCE.vo(scfUserConfigProvider.getByConfigKey(configKey,scfUserVO.getParentId())));
        }
    }

    /**
     * 新增或修改
     *
     * @param scfUserConfigForm 参数
     * @return void
     */
    @PostMapping("/saveOrUpdate")
	@ApiOperation(value = "新增或修改", notes = "传入scfUserConfigForm")
    @ResponseBody
	public Result<Void> save(@ApiParam(value = "用户配置参数") ScfUserConfigForm scfUserConfigForm) {
        ScfUserVO scfUserVO=super.getSessionUser();
        if(super.isAdmin(scfUserVO)){
            // TODO-SJ: 2020.03.12 金融监管平台：修改用信规则
            scfConfigProvider.updateByIdAndStatus(scfUserConfigForm.getIdAndConfigIdAndConfigStatus());
            return StandardResult.succeed();
        }else{
            ScfUserConfigDTO scfUserConfigDTO = new ScfUserConfigDTO();
            scfUserConfigDTO.setOwnerId(scfUserVO.getParentId());
            scfUserConfigDTO.setIdAndConfigIdAndConfigStatus(scfUserConfigForm.getIdAndConfigIdAndConfigStatus());
            scfUserConfigProvider.saveOrUpdate(scfUserConfigDTO);
            return StandardResult.succeed();
        }
	}

}
