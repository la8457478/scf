package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 实体类  </p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com"
 * @date 2020.03.21 15:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WaybillOperationHistory extends BasePO<Long, WaybillOperationHistory> {

    public static final String WAYBILL_ID = "waybill_id";
    public static final String HANDLE_DESC = "handle_desc";
    private static final long serialVersionUID = 1L;

    /** 运单id */
    private Long waybillId;
    /** 操作内容 */
    private String handleDesc;
    /** 操作时间 */
    private Date handleTime;

}
