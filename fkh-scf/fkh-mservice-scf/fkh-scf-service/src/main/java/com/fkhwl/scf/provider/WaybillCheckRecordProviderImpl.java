package com.fkhwl.scf.provider;

import com.fkhwl.scf.WaybillCheckRecordProvider;
import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.service.WaybillCheckRecordService;

import org.apache.dubbo.config.annotation.Service;

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
public class WaybillCheckRecordProviderImpl implements WaybillCheckRecordProvider {

    private final WaybillCheckRecordService waybillCheckRecordService;


    @Override
    public void save(WaybillCheckRecordDTO waybillCheckRecordDTO) {
        waybillCheckRecordService.save(waybillCheckRecordDTO);
    }
}
