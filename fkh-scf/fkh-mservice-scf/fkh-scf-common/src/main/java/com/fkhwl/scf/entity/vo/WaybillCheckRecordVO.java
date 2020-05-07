package com.fkhwl.scf.entity.vo;

import com.fkhwl.starter.common.base.BaseVO;

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
 * <p>Description: 运单查阅记录表 视图实体 (根据业务需求添加字段) </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 21:47
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WaybillCheckRecordVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    /** 运单id */
    @ApiModelProperty(value = "运单id")
    private Long waybillId;
    /** 查阅人id */
    @ApiModelProperty(value = "查阅人id")
    private Long checkUserId;
    /** 查阅人主账号id */
    @ApiModelProperty(value = "查阅人主账号id")
    private Long checkOwnerId;
    /** 用款申请id */
    @ApiModelProperty(value = "用款申请id")
    private Long creditApplyId;
}
