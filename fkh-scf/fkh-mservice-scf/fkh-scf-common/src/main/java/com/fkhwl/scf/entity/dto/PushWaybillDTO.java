package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;


/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 货主运单表+项目信息+计划信息推送的dto数据   </p>
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
@EqualsAndHashCode(callSuper = true)
public class PushWaybillDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "运单")
    private WaybillDTO waybillDTO;
    @ApiModelProperty(value = "项目")
    private ProjectDTO projectDTO;
    @ApiModelProperty(value = "计划")
    private ProgramDTO programDTO;
    //操作历史
    @ApiModelProperty(value = "操作历史")
    private List<WaybillOperationHistoryDTO> operationHistoryDTOList;
}
