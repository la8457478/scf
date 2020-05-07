package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.dao.WaybillDao;
import com.fkhwl.scf.service.WaybillRepositoryService;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 货主运单表 服务接口实现类 </p>
 *
 * @author hezhiming
 * @email hezhiming@fkhwl.com
 * @since 2020-02-20
 */
@Slf4j
@Service
public class WaybillRepositoryServiceImpl extends BaseServiceImpl<WaybillDao, Waybill> implements WaybillRepositoryService {

    @Override
    public IPage<WaybillDTO> listPage(IPage<WaybillDTO> page, Map<String, Object> params) {
        return baseMapper.listPage(page, params);
    }

    @Override
    public List<WaybillDTO> listSubjectClaimsOrderWaybill(Map<String, Object> params) {
        return baseMapper.listSubjectClaimsOrderWaybill(params);
    }

    @Override
    public int updateSubjectClaimsOrderId(Boolean isNewCreate,Long subjectClaimsOrderId, List<Long> ids, Date updateTime) {
        return baseMapper.updateSubjectClaimsOrderId(isNewCreate,subjectClaimsOrderId, ids, updateTime);
    }

    @Override
    public void updateByParams(Map<String, Object> params) {
        params.put("updateTime", DateUtils.now());
        baseMapper.updateByParams(params);
    }

    @Override
    public List<Long> listIdsByParams(Map<String, Object> params) {
        return baseMapper.listIdsByParams(params);
    }
}
