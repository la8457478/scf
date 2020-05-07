package com.fkhwl.scf.third.anxinsign.converter;

import java.io.*;

import lombok.Data;

/**
 * @author yuanxu
 * @Title: SigningCompany
 * @ProjectName fkh-parent
 * @Description: 签约公司
 * @date 2018-12-2710:11
 */
@Data
public class SigningCompanyDTO implements Serializable {
    private Long id;
    private String companyName;
    /**
     * 简称
     */
    private String companyShortName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 法定代表人姓名
     */
    private String registerName;
    /**
     * 法定代表人身份证号
     */
    private String idCardNo;
    /**
     * 营业执照号码
     */
    private String businessLicenseNo;
    /**
     * E签宝账户id
     */
    private String esignAccountId;
    /**
     * 印章图片base64
     */
    private String esignSealData;

    /** 安心签账户id*/
    private String anxinSignAccountId;

    /** 企业法人手机号*/
    private String mobileNo;

    /** 公司座机号*/
    private String landlinePhone;
}
