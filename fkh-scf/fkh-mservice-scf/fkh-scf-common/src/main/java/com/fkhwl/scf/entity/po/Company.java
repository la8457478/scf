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
 * <p>Description: 企业表 实体类  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Company extends BasePO<Long, Company> {

    public static final String COMPANY_TYPE = "company_type";
    public static final String COMPANY_NAME = "company_name";
    public static final String PROVINCE_ID = "province_id";
    public static final String PROVINCE_NAME = "province_name";
    public static final String CITY_ID = "city_id";
    public static final String CITY_NAME = "city_name";
    public static final String AREA_ID = "area_id";
    public static final String AREA_NAME = "area_name";
    public static final String COMPANY_ADDRESS = "company_address";
    public static final String COMPANY_TEL = "company_tel";
    public static final String OWNER_ID = "owner_id";
    public static final String OWNER_LOGIN_NAME = "owner_login_name";
    public static final String OWNER_NAME = "owner_name";
    public static final String USER_MOBILE_NO = "user_mobile_no";
    public static final String LEGAL_PERSON = "legal_person";
    public static final String ID_CARD_PICTURE = "id_card_picture";
    public static final String ID_CARD_PICTURE_BACK = "id_card_picture_back";
    public static final String REGISTER_TYPE = "register_type";
    public static final String ESTABLISHING_TIME = "establishing_time";
    public static final String BUSINESS_SCOPE = "business_scope";
    public static final String REGISTERED_CAPITAL = "registered_capital";
    public static final String OPERATION_PERIOD = "operation_period";
    public static final String BUSINESS_LICENSE_NO = "business_license_no";
    public static final String BUSINESS_LICENSE_PICTURE = "business_license_picture";
    public static final String COMPANY_STATUS = "company_status";
    private static final long serialVersionUID = 1L;

    /** 企业类型：1.资方，2.借款方 */
    private Integer companyType;
    /** 企业名称 */
    private String companyName;
    /** 企业简称 */
    private String shortCompanyName;
    /** 省id */
    private Long provinceId;
    /** 省 */
    private String provinceName;
    /** 市id */
    private Long cityId;
    /** 市 */
    private String cityName;
    /** 区县id */
    private Long areaId;
    /** 区县 */
    private String areaName;
    /** 企业地址 */
    private String companyAddress;
    /** 公司座机号 */
    private String companyTel;
    /** 创建者的主账号ID */
    private Long createOwnerId;
    /** 企业登录账号 */
    private String ownerLoginName;
    /** 负责人姓名 */
    private String ownerName;
    /** 负责人手机号码 */
    private String userMobileNo;
    /** 法人代表 */
    private String legalPerson;
    /** 法人代表身份证号码*/
    private String idCardNo;
    /** 法人代表身份证照片 */
    private String idCardPicture;
    /** 法人代表身份证照片背面 */
    private String idCardPictureBack;
    /** 企业注册类型 */
    private String registerType;
    /** 公司成立时间 */
    private Date establishingTime;
    /** 经营范围 */
    private String businessScope;
    /** 注册资本 */
    private String registeredCapital;
    /** 营业执照号码 */
    private String businessLicenseNo;
    /** 营业执照照片 */
    private String businessLicensePicture;
    /** 备注 */
    private String remark;
    /** 企业资质：0.无效，1.有效 */
    private Integer companyStatus;

    private String accountMobileNos;

    /** 安心签账户id */
    private String signAccountId;
    /** 电子签章的印章图片base64 */
    private String signSealData;
    private Long ownerId;
    private Long createUserId;
    private String smsConsultPhoneNo;

}
