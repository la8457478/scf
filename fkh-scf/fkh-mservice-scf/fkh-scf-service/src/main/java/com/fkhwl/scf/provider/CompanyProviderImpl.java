package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.CompanyProvider;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 企业Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
public class CompanyProviderImpl implements CompanyProvider {

    private final CompanyService companyService;

    @Override
    public IPage<CompanyDTO> listPage(Map<String, Object> params) {
        return companyService.listPage(Condition.getPage(params), params);
    }

    @Override
    public IPage<CompanyDTO> findPage(IPage<CompanyDTO> page, Map<String, Object> params) {
        return companyService.listPage(page, params);
    }
    @Override
    public CompanyDTO getDetail(Long id) {
        return companyService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(CompanyDTO companyDTO) {
        companyService.saveOrUpdate(companyDTO);
    }

    @Override
    public void delete(Long id) {
        companyService.delete(id);
    }

    @Override
    public List<Long> getBorrowerOwnerIdsByCapital(Long ownerId) {
        return companyService.getBorrowerOwnerIdsByCapital(ownerId);
    }

    @Override
    public CompanyDTO getByOwnerId(Long ownerId) {
        return companyService.getByOwnerId(ownerId);
    }

    @Override
    public void updateAccountMobileNos(Long companyId, String mobileNos) {
         companyService.updateAccountMobileNos(companyId,mobileNos);
    }
}
