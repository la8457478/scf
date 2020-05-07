package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 服务接口 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
public interface CompanyContractRepositoryService extends BaseService<CompanyContract> {

    /**
     * 分页查询企业
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params);
    /**
     * 查找借款方的合同信息
     * @param companyBorrowerId
     * @return
     */
    CompanyContract findInfoByCompanyId(Long companyCapitalId,Long companyBorrowerId);
    /**
     * 修改分项额度和融资比例
     * @param companyContract
     */
    int updateBaseConfig(CompanyContract companyContract);
    /**
     * 修改客户的已分配分项额度，和剩余可分配分项额度
     * @param companyContract
     */
    int updateSubitemBalance(CompanyContract companyContract);

    /**
     * 审核
     * @param params( id = 合同ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    int review(Map<String, Object> params);

    /**
    * 放款时更新合同的金额
     * @param id
     * @param applyBalance
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 9:43
    */
    int updateLoanBalance(Long id, BigDecimal applyBalance);

    /**
    * 放款时更新客户合同金额
     * @param id
     * @param repayBalance
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 12:08
    */
    int updateRepayBalance(Long id, BigDecimal repayBalance);
}

