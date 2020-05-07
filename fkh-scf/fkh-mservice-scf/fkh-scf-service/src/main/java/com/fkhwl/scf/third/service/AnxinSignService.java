package com.fkhwl.scf.third.service;

import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.po.Waybill;

import java.util.List;
import java.util.Map;

/**
 * 安心签
 * @author wudq
 * @date 2019/11/26
 */
public interface AnxinSignService {

    /**
     * 创建个人印章，本地实现
     * @param userName
     * @return
     */
    String createPersonalSeal(String userName);

    /**
     * 创建企业印章，本地实现
     * @param companyName
     * @return
     */
    String createCompanySeal(String companyName);


    /**
     * 注册企业用户（身份证号，姓名）
     * @return
     */
    String addCompanyAccount(CompanyDTO signingCompany);


    /**
     * 本地读取模版，生成未盖章合同
     * @param txtFields
     * @return
     */
    String createFileFromTemplate(Map<String, String> txtFields, List<Waybill> waybillList);


    /**
     * 多签
     * @param srcFile
     * @param companyAccountId
     * @param companySeal
     * @param driverAccountId
     * @param driverSeal
     * @param txtFields
     * @return
     */
    Map<String, Object> multiSign(String srcFile, String companyAccountId, String companySeal,
                                   String driverAccountId, String driverSeal, Map<String, Object> txtFields);
}
