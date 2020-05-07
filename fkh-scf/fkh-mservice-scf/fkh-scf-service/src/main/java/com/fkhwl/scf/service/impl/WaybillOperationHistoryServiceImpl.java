package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.po.WaybillOperationHistory;
import com.fkhwl.scf.service.WaybillOperationHistoryRepositoryService;
import com.fkhwl.scf.service.WaybillOperationHistoryService;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.util.DateUtils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class WaybillOperationHistoryServiceImpl implements WaybillOperationHistoryService {

    private final WaybillOperationHistoryRepositoryService operationHistoryRepositoryService;

    @Override
    public List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId) {
        return operationHistoryRepositoryService.listByWaybillId(waybillId);
    }

    @Override
    public void saveBatch(Long waybillId, List<WaybillOperationHistoryDTO> operationHistoryDTOList) {
        if (operationHistoryDTOList != null && operationHistoryDTOList.size() > 0) {
            List<WaybillOperationHistory> operationHistoryList = new ArrayList<>(operationHistoryDTOList.size());
            Date now = DateUtils.now();
            for (WaybillOperationHistoryDTO item : operationHistoryDTOList) {
                WaybillOperationHistory operationHistory = new WaybillOperationHistory();
                operationHistory.setWaybillId(waybillId);
                operationHistory.setHandleDesc(item.getHandleDesc());
                operationHistory.setHandleTime(item.getHandleTime());
                operationHistory.setDeleted(DeleteEnum.N);
                operationHistory.setCreateTime(now);
                operationHistory.setUpdateTime(now);
                operationHistoryList.add(operationHistory);
            }

            operationHistoryRepositoryService.saveBatch(operationHistoryList);
        }
    }
}
