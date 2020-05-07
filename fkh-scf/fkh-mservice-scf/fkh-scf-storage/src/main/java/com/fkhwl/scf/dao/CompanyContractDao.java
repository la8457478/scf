package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 主体合同：资方与借款方签订的合同 Dao 接口  </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.03 15:12
 */
@Mapper
public interface CompanyContractDao extends BaseDao<CompanyContract> {
    /**
     * 分页查询用户配置列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 用户配置列表 page
     */
    IPage<CompanyContractDTO> findPage(@Param("page") IPage<CompanyContractDTO> page,
                                     @Param("map") Map<String, Object> params);

    /**
     * 查找借款方的合同信息
     * @param companyBorrowerId
     * @return
     */
    CompanyContract findInfoByCompanyId( Long companyCapitalId,Long companyBorrowerId);
    /**
     * 修改基本信息
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
    int review(@Param("map") Map<String, Object> params);

    /**
    * 放款时更新金额
     * @param id
     * @param applyBalance
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 9:44
    */

    int updateLoanBalance(@Param("map")Map<String, Object> map);

    /**
    * 还款时更新金额
     * @param map
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 12:10
    */
    int updateRepayBalance(@Param("map")Map<String, Object> map);
}
