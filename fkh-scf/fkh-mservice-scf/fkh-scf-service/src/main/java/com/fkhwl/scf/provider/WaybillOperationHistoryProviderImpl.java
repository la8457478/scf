package com.fkhwl.scf.provider;

import com.fkhwl.scf.WaybillOperationHistoryProvider;
import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.service.WaybillOperationHistoryService;

import org.apache.dubbo.config.annotation.Service;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
@Slf4j
public class WaybillOperationHistoryProviderImpl implements WaybillOperationHistoryProvider {

    private final WaybillOperationHistoryService waybillOperationHistoryService;


    @Override
    public List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId) {
        return waybillOperationHistoryService.listByWaybillId(waybillId);
    }
}
