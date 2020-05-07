package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

import java.util.Date;

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
 * <p>Description: 运单操作历史 视图实体 (根据业务需求添加字段) </p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.03.21 15:45
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WaybillOperationHistoryVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    /** 运单id */
    @ApiModelProperty(value = "运单id")
    private Long waybillId;
    /** 操作内容 */
    @ApiModelProperty(value = "操作内容", required = true)
    private String handleDesc;
    /** 操作时间 */
    @ApiModelProperty(value = "操作时间", required = true)
    private Date handleTime;
}
