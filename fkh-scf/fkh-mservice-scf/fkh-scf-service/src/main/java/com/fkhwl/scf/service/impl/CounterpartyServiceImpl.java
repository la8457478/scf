package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CounterpartyDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.scf.wrapper.CounterpartyServiceConverterWrapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 交易对手 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepositoryService counterpartyRepositoryService;

    @Override
    public List<CounterpartyDTO> listByCompanyId(Long companyBorrowerId) {
        return counterpartyRepositoryService.listByCompanyId(companyBorrowerId);
    }

    /**
     * Find info optional
     *
     * @param userId user id
     * @return the optional
     */
    @Override
    public CounterpartyDTO findInfo(Long userId) {
        return CounterpartyServiceConverterWrapper.INSTANCE.dto(counterpartyRepositoryService.getById(userId));
    }

    /**
     * Find list list
     *
     * @return the list
     */
    @Override
    public List<CounterpartyDTO> findList() {
        List<Counterparty> list = counterpartyRepositoryService.list();
        return list.stream().map(CounterpartyServiceConverterWrapper.INSTANCE::dto).collect(Collectors.toList());
    }
    /**
     * Find page page
     *
     * @param page   page
     * @param params params
     * @return the page
     */
    @Override
    public IPage<CounterpartyDTO> findPage(IPage<CounterpartyDTO> page, Map<String, Object> params) {
        return counterpartyRepositoryService.findPage(page, params);
    }

    /**
     * Save or update *
     *
     * @param counterpartyDTO user dto
     */
    @Override
    public void save(CounterpartyDTO counterpartyDTO) {
        Counterparty scfConfig = CounterpartyServiceConverterWrapper.INSTANCE.po(counterpartyDTO);
        scfConfig.setSubitemRemainBalance(scfConfig.getSubitemLimitBalance());
        scfConfig.setStatus(0);
        counterpartyRepositoryService.saveOrUpdate(scfConfig);
    }
    /**
     * 修改分项额度和融资比例
     * @param counterpartyDTO
     */
    @Override
    public int updateBaseConfig(CounterpartyDTO counterpartyDTO){
        Counterparty counterparty = CounterpartyServiceConverterWrapper.INSTANCE.po(counterpartyDTO);
        return counterpartyRepositoryService.updateBaseConfig(counterparty);
    }
    /**
     * Delete *
     *
     * @param ids ids
     */
    @Override
    public void delete(List<Long> ids) {
        counterpartyRepositoryService.removeByIds(ids);
    }

    @Override
    public Map<Long, CounterpartyDTO> getIdAndDataMap(Set<Long> ids) {
        Map<Long, CounterpartyDTO> result = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        List<CounterpartyDTO> counterpartyList = counterpartyRepositoryService.listByParams(params);
        if (counterpartyList != null && counterpartyList.size() > 0) {
            for (CounterpartyDTO item : counterpartyList) {
                result.put(item.getId(), item);
            }
        }

        return result;
    }

    @Override
    public Counterparty findByName(String consigneeName) {
        return  counterpartyRepositoryService.getOne(new LambdaQueryWrapper<Counterparty>().eq(Counterparty::getCounterpartyName,consigneeName).eq(Counterparty::getStatus,1));
    }
    @Override
    public int  review(Map<String, Object> params){
        return counterpartyRepositoryService.review(params);
    }

    @Override
    public CounterpartyDTO getByParams(Map<String, Object> params) {
        return counterpartyRepositoryService.getByParams(params);
    }
}
