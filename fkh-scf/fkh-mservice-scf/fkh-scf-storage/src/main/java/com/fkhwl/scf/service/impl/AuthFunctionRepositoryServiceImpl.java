package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.AuthFunctionDao;
import com.fkhwl.scf.entity.dto.AuthFunctionDTO;
import com.fkhwl.scf.entity.po.AuthFunction;
import com.fkhwl.scf.service.AuthFunctionRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限系统资源表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class AuthFunctionRepositoryServiceImpl extends BaseServiceImpl<AuthFunctionDao, AuthFunction> implements AuthFunctionRepositoryService {

    @Override
    public IPage<AuthFunctionDTO> listPage(IPage<AuthFunctionDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public List<String> listFuncKey(Long roleId) {
        return baseMapper.listFuncKey(roleId);
    }
}
