package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyContractProvider;
import com.fkhwl.scf.CounterpartyProvider;
import com.fkhwl.scf.entity.dto.CompanyContractDTO;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.starter.core.support.AssertUtils;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 交易对手Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class CounterpartyProviderImpl implements CounterpartyProvider {

    private final CounterpartyService counterpartyService;
    private final CompanyContractProvider companyContractProvider;

//    private final CfcaService cfcaService;
//    private final WaybillService waybillService;
    @Override
    public List<CounterpartyDTO> listByCompanyId(Long companyBorrowerId) {
        return counterpartyService.listByCompanyId(companyBorrowerId);
    }

    @Override
    public CounterpartyDTO findInfo(Long userId) {
        return counterpartyService.findInfo(userId);
    }

    @Override
    public List<CounterpartyDTO> findList() {
        return counterpartyService.findList();
    }

    @Override
    public IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params) {
        return counterpartyService.findPage(page, params);
    }

    @Override
    @Transactional
    public void save(CounterpartyDTO userDTO) {
        //更新客户的可分配分项额度
        CompanyContractDTO contractUpdateDTO=new CompanyContractDTO();
        Map<String, Object> params = new HashMap<>();
        params.put("counterpartyName", userDTO.getCounterpartyName());
        CounterpartyDTO counterparty = counterpartyService.getByParams(params);
        AssertUtils.isNull(counterparty, userDTO.getCounterpartyName() + "已存在");
        contractUpdateDTO.setId(userDTO.getCompanyContractId());
        contractUpdateDTO.setHadSubitemBalance(userDTO.getSubitemLimitBalance());
        int result=companyContractProvider.updateSubitemBalance(contractUpdateDTO);
        if(result<=0){
            throw new IllegalArgumentException("请设置交易对手分项限额!");
        }
        //设置默认的可分配金额
        userDTO.setSubitemRemainBalance(userDTO.getSubitemLimitBalance());
        counterpartyService.save(userDTO);
    }

    /**
     * 修改分项额度和融资比例
     * @param userDTO
     */
    @Override
    @Transactional
    public void updateBaseConfig(CounterpartyDTO userDTO){
        // 需要校验分项额度是否小于已经使用额度
        CounterpartyDTO counterpartyDTOBD=counterpartyService.findInfo(userDTO.getId());
        //获取差值，修改客户合同中的已分配分项额度和可分配分项额度
        BigDecimal tmp=userDTO.getSubitemLimitBalance().subtract(counterpartyDTOBD.getSubitemLimitBalance());
        CompanyContractDTO contractUpdateDTO=new CompanyContractDTO();
        contractUpdateDTO.setId(counterpartyDTOBD.getCompanyContractId());
        contractUpdateDTO.setHadSubitemBalance(tmp);
        int result=companyContractProvider.updateSubitemBalance(contractUpdateDTO);
        AssertUtils.isTrue(result>0,"修改失败，超过客户总额度");

        result= counterpartyService.updateBaseConfig(userDTO);
        AssertUtils.isTrue(result>0,"修改失败");
    }
    @Override
    public void delete(List<Long> ids) {
        counterpartyService.delete(ids);
    }
    /**
     * 审核
     * @param params( id = 交易对手ID,status= 审核状态 0：初始化（待审核），1：正常（审核通过），2：异常（审核不通过）)
     */
    @Override
    public int review(Map<String, Object> params) {
       return counterpartyService.review(params);
    }
}
