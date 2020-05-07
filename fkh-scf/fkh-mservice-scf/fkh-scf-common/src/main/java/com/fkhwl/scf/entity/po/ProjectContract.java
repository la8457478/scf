package com.fkhwl.scf.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目合同 实体类  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProjectContract extends BasePO<Long, ProjectContract> {

    public static final String THIRD_ID = "third_id";
    public static final String PROJECT_ID = "project_id";
    public static final String CONTRACT = "contract";
    public static final String CONSIGN_NAME = "consign_name";
    public static final String TRANSPORT_NAME = "transport_name";
    public static final String TRANSPORT_COMPANY_ID = "transport_company_id";
    public static final String GOOD_NAME = "good_name";
    public static final String GOOD_PRICE = "good_price";
    public static final String TAX_NUMBER = "tax_number";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String BANK_NAME = "bank_name";
    public static final String BANK_NCCOUNT_NUMBER = "bank_nccount_number";
    public static final String CONTRACT_NUMBER = "contract_number";
    public static final String CALCULATION_TYPE = "calculation_type";
    public static final String PRICEHANDLE_TYPE = "priceHandle_type";
    public static final String EXECUTION_START_TIME = "execution_start_time";
    public static final String EXECUTION_END_TIME = "execution_end_time";
    public static final String SUPPLEMENTARY_AGREEMENT = "supplementary_agreement";
    public static final String TAX_RATE = "tax_rate";
    public static final String CALC_TIME_TYPE = "calc_time_type";
    public static final String PRESET_RECORD = "preset_record";
    private static final long serialVersionUID = 1L;

    /** 第三方合同id */
    private Long thirdId;
    /** 项目Id */
    private Long projectId;
    /** 合同 */
    private String contract;
    /** 托运人 */
    private String consignName;
    /** 承运人 */
    private String transportName;
    /** 合同乙方对应公司id(即项目中的签约主体id) */
    private Long transportCompanyId;
    /** 托运货物名称 */
    private String goodName;
    /** 货物价值 */
    private Double goodPrice;
    /** 税号 */
    private String taxNumber;
    /** 地址 */
    private String address;
    /** 电话 */
    private String phoneNumber;
    /** 开户行名称 */
    private String bankName;
    /** 开户行账号 */
    private String bankNccountNumber;
    /** 合同编号 */
    private String contractNumber;
    /** 开票方式 */
    private Integer calculationType;
    /** 运费抹零 */
    @TableField("priceHandle_type")
    private Integer pricehandleType;
    /** 执行周期开始时间 */
    private Date executionStartTime;
    /** 执行周期结束时间 */
    private Date executionEndTime;
    /** 补充协议 */
    private String supplementaryAgreement;
    /** 运费差 */
    private BigDecimal taxRate;
    /** 结算时间类型 （0=以派车时间计算运费，1=以发货时间计算运费，2=以收货时间计算运费） */
    private Integer calcTimeType;
    /** 预设信息 */
    private String presetRecord;

}
