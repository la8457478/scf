package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单查阅记录表 实体类  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 21:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WaybillCheckRecord extends BasePO<Long, WaybillCheckRecord> {

    public static final String WAYBILL_ID = "waybill_id";
    public static final String CHECK_USER_ID = "check_user_id";
    public static final String CHECK_OWNER_ID = "check_owner_id";
    public static final String CREDIT_APPLY_ID = "credit_apply_id";
    private static final long serialVersionUID = 1L;

    /** 运单id */
    private Long waybillId;
    /** 查阅人id */
    private Long checkUserId;
    /** 查阅人主账号id */
    private Long checkOwnerId;
    /** 用款申请id */
    private Long creditApplyId;

}
