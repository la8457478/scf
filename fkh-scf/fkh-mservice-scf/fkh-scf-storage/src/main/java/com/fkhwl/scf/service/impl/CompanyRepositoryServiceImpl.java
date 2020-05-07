package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.CompanyDao;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.po.Company;
import com.fkhwl.scf.service.CompanyRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 企业表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class CompanyRepositoryServiceImpl extends BaseServiceImpl<CompanyDao, Company> implements CompanyRepositoryService {

    @Override
    public IPage<CompanyDTO> listPage(IPage<CompanyDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public List<Long> getBorrowerOwnerIdsByCapital(Long ownerId) {
        return baseMapper.getBorrowerOwnerIdsByCapital(ownerId);
    }

    @Override
    public CompanyDTO getByOwnerId(Long ownerId) {
        return baseMapper.getByOwnerId(ownerId);
    }

    @Override
    public CompanyDTO getByParams(Map<String, Object> params) {
        return baseMapper.getByParams(params);
    }
}
