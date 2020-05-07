package com.fkhwl.scf.entity.dto;

import com.fkhwl.starter.common.base.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 短信提醒记录 数据传输实体 (根据业务需求添加字段) </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.04.08 16:01
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountNotifySmsLogDTO extends BaseDTO<Long> {
    private static final long serialVersionUID = 1L;


    /** 提醒电话号码 */
    private String mobileNo;
    /** 借款方Id */
    private Long companyBorrowerId;
    /** 资方id */
    private Long companyCapitalId;
    /** 创建人 */
    private Long createUserId;
    /** 发送状态 */
    private Integer sendStatus;
}
