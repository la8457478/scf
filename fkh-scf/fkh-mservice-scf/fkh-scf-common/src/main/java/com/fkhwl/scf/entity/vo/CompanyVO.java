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
 * <p>Description: 企业表 视图实体  </p>
 * 根据业务需求添加字段
 *
 * @author ASpiralMoon
 * @email ASpiralMoon#fkhwl.com
 * @since 2020-02-19
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CompanyVO extends BaseVO<Long> {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业类型：1.资方，2.借款方")
    private Integer companyType;
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "省id")
    private Long provinceId;
    @ApiModelProperty(value = "省")
    private String provinceName;
    @ApiModelProperty(value = "市id")
    private Long cityId;
    @ApiModelProperty(value = "市")
    private String cityName;
    @ApiModelProperty(value = "区县id")
    private Long areaId;
    @ApiModelProperty(value = "区县")
    private String areaName;
    @ApiModelProperty(value = "企业地址")
    private String companyAddress;
    @ApiModelProperty(value = "公司座机号")
    private String companyTel;
    @ApiModelProperty(value = "负责人用户ID")
    private Long ownerId;
    @ApiModelProperty(value = "企业登录账号")
    private String ownerLoginName;
    @ApiModelProperty(value = "负责人姓名")
    private String ownerName;
    @ApiModelProperty(value = "负责人手机号码")
    private String userMobileNo;
    @ApiModelProperty(value = "法人代表")
    private String legalPerson;
    @ApiModelProperty(value = "法人代表身份证号码")
    private String idCardNo;
    @ApiModelProperty(value = "法人代表身份证照片")
    private String idCardPicture;
    @ApiModelProperty(value = "法人代表身份证照片背面")
    private String idCardPictureBack;
    @ApiModelProperty(value = "企业注册类型")
    private String registerType;
    @ApiModelProperty(value = "公司成立时间")
    private Date establishingTime;
    @ApiModelProperty(value = "经营范围")
    private String businessScope;
    @ApiModelProperty(value = "注册资本")
    private String registeredCapital;
    @ApiModelProperty(value = "营业执照号码")
    private String businessLicenseNo;
    @ApiModelProperty(value = "营业执照照片")
    private String businessLicensePicture;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "企业资质：0.无效，1.有效")
    private Integer companyStatus;
    @ApiModelProperty(value = " 安心签账户id ")
    private String signAccountId;
    @ApiModelProperty(value = "电子签章的印章图片base64 ")
    private String signSealData;
    private Long createUserId;
    private String accountMobileNos;
    /** 企业简称 */
    private String shortCompanyName;
//    短信详询电话
    private String smsConsultPhoneNo;
}
