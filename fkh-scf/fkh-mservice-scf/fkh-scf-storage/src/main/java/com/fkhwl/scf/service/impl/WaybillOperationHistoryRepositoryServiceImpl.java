package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.po.WaybillOperationHistory;
import com.fkhwl.scf.dao.WaybillOperationHistoryDao;
import com.fkhwl.scf.service.WaybillOperationHistoryRepositoryService;
import com.fkhwl.starter.mybatis.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 服务接口实现类 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 15:48
 */
@Slf4j
@Service
public class WaybillOperationHistoryRepositoryServiceImpl extends BaseServiceImpl<WaybillOperationHistoryDao, WaybillOperationHistory> implements WaybillOperationHistoryRepositoryService {

    @Override
    public List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId) {
        return baseMapper.listByWaybillId(waybillId);
    }
}
