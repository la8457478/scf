package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  Dao 接口  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
@Mapper
public interface CounterpartyDao extends BaseDao<Counterparty> {

     List<CounterpartyDTO> listByCompanyId(@Param("companyBorrowerId") Long companyId);
    /**
     * 分页查询用户配置列表
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 用户配置列表 page
     */
    IPage<CounterpartyDTO> findPage(@Param("page") IPage<CounterpartyDTO> page,
                                       @Param("map") Map<String, Object> params);
    /**
     * 获取交易对手信息列表
     *
     * @param params ids
     * @return the list
     */
    List<CounterpartyDTO> listByParams(@Param("map") Map<String, Object> params);
    /**
     * 修改分项额度和融资比例
     * @param counterparty
     */
    int updateBaseConfig(Counterparty counterparty);

    /**
     * 审核
     * @param params( id = 交易对手ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    int review(@Param("map") Map<String, Object> params);

    /**
     * 获取交易对手信息
     *
     * @param params ids
     * @return the counterpartyDTO
     */
    CounterpartyDTO getByParams(@Param("map") Map<String, Object> params);

    int updateLoanBalance(@Param("map") Map<String, Object> params);

    /**
    * 还款时金额变化
     * @param map
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 11:27
    */
    int updateRepayBalance(@Param("map") Map<String, Object> params);
}
