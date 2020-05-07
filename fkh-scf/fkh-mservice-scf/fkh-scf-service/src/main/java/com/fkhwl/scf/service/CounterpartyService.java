package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.po.Counterparty;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 交易对手 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface CounterpartyService {
    /**
     * 根据公司id获取交易对手
     *
     * @param companyId 查询参数
     * @return 返回分页运单数据 page
     */
    List<CounterpartyDTO> listByCompanyId(Long companyId);


    /**
     * 获取企业合同详情
     *
     * @param companyId 企业合同ID
     * @return 企业合同详情DTO optional
     */
    CounterpartyDTO findInfo(Long companyId);

    /**
     * 获取企业合同列表
     *
     * @return 企业合同列表 list
     */
    List<CounterpartyDTO> findList();

    /**
     * 企业合同分页查询
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页企业合同数据 page
     */
    IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params);

    /**
     * 企业合同新增和更新
     *
     * @param userDTO 企业合同实体
     */
    void save(CounterpartyDTO userDTO);
    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    int updateBaseConfig(CounterpartyDTO userDTO);
    /**
     * 按照Id批量删除企业合同信息，企业合同ID用逗号隔开
     *
     * @param ids ids
     */
    void delete(List<Long> ids);

    /**
     * 获取交易对手信息：“交易对手id”-“交易对手信息”方式返回
     *
     * @param ids 交易对手ID集合
     * @return “交易对手id”-“交易对手信息”map数据
     */
    Map<Long, CounterpartyDTO> getIdAndDataMap(Set<Long> ids);

    Counterparty findByName(String consigneeName);
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
}

