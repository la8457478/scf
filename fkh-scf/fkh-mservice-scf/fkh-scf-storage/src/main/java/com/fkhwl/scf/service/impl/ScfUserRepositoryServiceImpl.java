package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.dao.ScfUserDao;
import com.fkhwl.scf.entity.dto.ScfUserDTO;
import com.fkhwl.scf.entity.po.ScfUser;
import com.fkhwl.scf.service.ScfUserRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 管理用户表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
public class ScfUserRepositoryServiceImpl extends BaseServiceImpl<ScfUserDao, ScfUser> implements ScfUserRepositoryService {

    @Override
    public IPage<ScfUserDTO> listPage(IPage<ScfUserDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public IPage<ScfUserDTO> listPageCustomer(IPage<ScfUserDTO> page, Map<String, Object> params) {
        return baseMapper.listPageCustomer(page, params);
    }

    @Override
    public void updateByParams(Map<String, Object> params) {
        baseMapper.updateByParams(params);
    }
}
