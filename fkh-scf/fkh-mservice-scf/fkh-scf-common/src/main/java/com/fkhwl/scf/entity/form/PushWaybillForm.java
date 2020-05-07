package com.fkhwl.scf.entity.form;

import com.fkhwl.starter.common.base.BaseForm;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 货主运单表+项目信息+计划信息推送的数据   </p>
 *
 * 注释的字段为重复的
 * @author chenli
 * @email chenli#fkhwl.com
 * @since 2020-02-20
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@ApiModel("推送运单入参实体")
public class PushWaybillForm extends BaseForm<Long>{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "运单信息")
    private WaybillForm waybillForm;
    //项目
    @ApiModelProperty(value = "项目信息")
    private ProjectForm projectForm;
    //计划
    @ApiModelProperty(value = "计划信息")
    private ProgramForm programForm;
    //操作历史
    @ApiModelProperty(value = "操作历史")
    private List<WaybillOperationHistoryForm> operationHistoryFormList;
}
