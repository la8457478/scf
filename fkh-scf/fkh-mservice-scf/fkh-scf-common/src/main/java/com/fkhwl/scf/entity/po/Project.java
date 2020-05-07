package com.fkhwl.scf.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fkhwl.starter.common.base.BasePO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 项目 实体类  </p>
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
public class Project extends BasePO<Long, Project> {

    public static final String THIRD_ID = "third_id";
    public static final String PROJECT_NAME = "project_name";
    public static final String CREATE_OWNER_ID = "create_owner_id";
    public static final String CREATE_USER_ID = "create_user_id";
    public static final String CREATE_USER_NAME = "create_user_name";
    public static final String SEND_USER_ID = "send_user_id";
    public static final String SEND_COMPANY_NAME = "send_company_name";
    public static final String TRANSPORT_USER_ID = "transport_user_id";
    public static final String TRANSPORT_COMPANY_NAME = "transport_company_name";
    public static final String CONSIGNEE_USERID = "consignee_userId";
    public static final String CONSIGNEE_COMPANY_NAME = "consignee_company_name";
    public static final String PAYMENT_USER_ID = "payment_user_id";
    public static final String PROJECT_STATUS = "project_status";
    public static final String IS_SELF = "is_self";
    public static final String NET_WEIGHT_ONLY = "net_weight_only";
    public static final String MATERIAL_TYPE = "material_type";
    public static final String SIGNING_COMPANYID = "signing_companyId";
    public static final String SIGNING_COMPANY_NAME = "signing_company_name";
    public static final String PROJECT_CREATE_TIME = "project_create_time";
    private static final long serialVersionUID = 1L;

    /** 第三方项目id */
    private Long thirdId;
    /** 项目名称 */
    private String projectName;
    /** 创建者的主账号ID */
    private String createOwnerName;
    /** 创建者用户ID */
    private Long createUserId;
    /** 创建者用户名称 */
    private String createUserName;
    /** 发货方Id */
    private Long sendUserId;
    /** 发货方公司名称 */
    private String sendCompanyName;
    /** 运输方Id */
    private Long transportUserId;
    /** 运输方公司名称 */
    private String transportCompanyName;
    /** 收货方Id */
    @TableField("consignee_userId")
    private Long consigneeUserId;
    /** 收货方公司名称 */
    private String consigneeCompanyName;
    /** 支付方 id */
    private Long paymentUserId;
    /** 项目状态：-1=删除,1=新建，2=启动,3=结束 */
    private Integer projectStatus;
    /** 0=对外货主,1=无车承运平台货主 */
    private Boolean isSelf;
    /** 是否只完善净重信息 0：否；1：是 */
    private Boolean netWeightOnly;
    /** 物资类型：0普通物资，1汽车,2土石方 */
    private Integer materialType;
    /** 签约主体id */
    @TableField("signing_companyId")
    private Long signingCompanyid;
    /** 签约主体名称 */
    private String signingCompanyName;
    /** 项目创建时间 */
    private Date projectCreateTime;

    //监管服务的企业id
    private Long companyId;

    //项目所属借款方企业的某个交易对手的ID
    private Long counterpartyId;

    //项目所属借款方企业的某个交易对手的名称
    private String counterpartyName;

    //未转让应收账款转让订单数
    private Integer subjectClaimsOrderCount;

    private String sendDutyUserName;
    private String transportDutyUserName;
    private String consigneeDutyUserName;

}
