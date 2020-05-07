package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.dao.WaybillCheckRecordDao;
import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.po.WaybillCheckRecord;
import com.fkhwl.scf.service.WaybillCheckRecordRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单查阅记录表 服务接口实现类 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 21:49
 */
@Slf4j
@Service
public class WaybillCheckRecordRepositoryServiceImpl extends BaseServiceImpl<WaybillCheckRecordDao, WaybillCheckRecord> implements WaybillCheckRecordRepositoryService {

    @Override
    public Integer countByCreditApplyId(Long creditApplyId) {
        return baseMapper.countByCreditApplyId(creditApplyId);
    }

    @Override
    public WaybillCheckRecordDTO getByWaybillId(Long waybillId) {
        return baseMapper.getByWaybillId(waybillId);
    }
}
