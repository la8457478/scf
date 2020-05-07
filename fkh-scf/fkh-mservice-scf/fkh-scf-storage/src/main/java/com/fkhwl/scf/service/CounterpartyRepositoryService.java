package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  服务接口 </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.02.26 11:21
 */
public interface CounterpartyRepositoryService extends BaseService<Counterparty> {
    /**
     * List by company id list.
     * @param companyId the company id
     * @return the list
     */
    List<CounterpartyDTO> listByCompanyId( Long companyId);

    /**
     * 分页查询企业
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页部门数据 page
     */
    IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params);

    /**
     * 获取交易对手信息列表
     *
     * @param params ids
     * @return the list
     */
    List<CounterpartyDTO> listByParams(Map<String, Object> params);

    /**
     * 修改分项额度和融资比例
     * @param counterparty
     */
    int updateBaseConfig(Counterparty counterparty);

    /**
     * 审核
     * @param params( id = 交易对手ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    int review(Map<String, Object> params);

    /**
     * 获取交易对手信息
     *
     * @param params ids
     * @return the counterpartyDTO
     */
    CounterpartyDTO getByParams(Map<String, Object> params);

    /**
    * 放款时交易对手变化
     * @param id
     * @param applyBalance
     * @param transferBalance
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 10:12
    */
    int updateLoanBalance(Long id, BigDecimal applyBalance, BigDecimal transferBalance);

    /**
    * 还款时交易对手金额变化
     * @param counterpartyId
     * @param repayBalance
     * @param repayTransferBalance
    * @return: int
    * @Author: liuan
    * @Date: 2020/4/13 11:26
    */
    int updateRepayBalance(Long counterpartyId, BigDecimal repayBalance, BigDecimal repayTransferBalance);
}

