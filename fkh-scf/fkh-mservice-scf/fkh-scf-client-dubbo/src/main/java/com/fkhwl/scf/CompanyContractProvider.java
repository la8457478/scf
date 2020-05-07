package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf用户Dubbo Service</p>
 *
 * @author liuan
 * @version 1.0.1
 * @email "mailto:liuan@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface CompanyContractProvider {

    CompanyContractDTO getByUsername(String username);

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情DTO optional
     */
    CompanyContractDTO findInfo(Long userId);

    /**
     * 获取用户列表
     *
     * @return 用户列表 list
     */
    List<CompanyContractDTO> findList();

    /**
     * 用户分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用户数据 page
     */
    IPage<CompanyContractDTO> findPage(IPage<CompanyContractDTO> page, Map<String, Object> params);

    /**
     * 用户新增和更新
     *
     * @param userDTO 用户实体
     */
    void save(CompanyContractDTO userDTO);

    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    void updateBaseConfig(CompanyContractDTO userDTO);
    /**
     * 按照userId批量删除用户信息，用户ID用逗号隔开
     *
     * @param ids ids
     */
    void delete(List<Long> ids);

    /**
    * 根据借款方id和资方id查询合同:
     * @param companyId
     * @param companyBorrowerId
    * @return: com.fkhwl.scf.entity.dto.CompanyContractDTO
    * @Author: liuan
    * @Date: 2020/3/12 13:07
    */
    CompanyContractDTO getByCompanyId(Long companyId, Long companyBorrowerId);

    CompanyContractDTO getByBorrowCompanyId(Long companyBorrowerId);

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
