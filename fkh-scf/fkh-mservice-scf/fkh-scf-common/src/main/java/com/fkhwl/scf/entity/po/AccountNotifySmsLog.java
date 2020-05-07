package com.fkhwl.scf.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fkhwl.starter.common.base.BasePO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 短信提醒记录 实体类  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.04.08 16:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccountNotifySmsLog extends BasePO<Long, AccountNotifySmsLog> {

    public static final String MOBILENO = "mobileNo";
    public static final String COMPANY_BORROWER_ID = "company_borrower_id";
    public static final String COMPANY_BORROWER_NAME = "company_borrower_name";
    public static final String COMPANY_CAPITAL_ID = "company_capital_id";
    public static final String COMPANY_CAPITAL_NAME = "company_capital_name";
    public static final String CREATE_USER_ID = "create_user_id";
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
