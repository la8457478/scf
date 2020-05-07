package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.CompanyContract;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 交易对手 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface CompanyContractService {

    /**
     * 获取企业合同详情
     *
     * @param id 企业合同ID
     * @return 企业合同详情DTO optional
     */
    CompanyContractDTO findInfo(Long id);
    /**
     * 查找借款方的合同信息
     * @param companyBorrowerId
     * @return
     */
    CompanyContractDTO findInfoByCompanyId(Long companyCapitalId,Long companyBorrowerId);
    /**
     * 获取企业合同列表
     *
     * @return 企业合同列表 list
     */
    List<CompanyContractDTO> findList();

    /**
     * 企业合同分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页企业合同数据 page
     */
    IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params);

    /**
     * 企业合同新增和更新
     *
     * @param userDTO 企业合同实体
     */
    void save(CompanyContractDTO userDTO);
    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    int updateBaseConfig(CompanyContractDTO userDTO);
    /**
     * 按照Id批量删除企业合同信息，企业合同ID用逗号隔开
     *
     * @param ids ids
     */
    void delete(List<Long> ids);

    /**
    * 根据资方id和借款方id查询合同
     * @param companyCapitalId
     * @param companyBorrowerId
    * @return: com.fkhwl.scf.entity.dto.CompanyContractDTO
    * @Author: liuan
    * @Date: 2020/3/12 13:12
    */
    CompanyContract getByCompanyId(Long companyCapitalId, Long companyBorrowerId);

    CompanyContract getByBorrowCompanyId( Long companyBorrowerId);

    /**
     * 修改客户的已分配分项额度，和剩余可分配分项额度
     * @param companyContract
     */
    int updateSubitemBalance(CompanyContractDTO companyContract);

    /**
     * 审核
     * @param params( id = 合同ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    int review(Map<String, Object> params);
}

