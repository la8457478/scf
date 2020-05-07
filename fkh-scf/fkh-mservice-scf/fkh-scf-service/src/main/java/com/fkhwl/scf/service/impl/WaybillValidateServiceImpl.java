package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.WaybillValidateDTO;
import com.fkhwl.scf.entity.po.WaybillValidate;
import com.fkhwl.scf.service.WaybillValidateRepositoryService;
import com.fkhwl.scf.service.WaybillValidateService;
import com.fkhwl.scf.wrapper.WaybillValidateServiceConverterWrapper;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单表 服务接口实现类 </p>
 *
 * @author Administrator
 * @version 1.0.0
 * @email "mailto:Administrator@fkhwl.com"
 * @date 2020.03.29 11:43
 */
@Slf4j
@Service
@AllArgsConstructor
public class WaybillValidateServiceImpl implements WaybillValidateService {

    private final WaybillValidateRepositoryService waybillValidateRepositoryService;
    /**
     * Save or update *
     *
     */
    @Override
    public void save(WaybillValidateDTO waybillValidateDTO) {
        WaybillValidate scfConfig = WaybillValidateServiceConverterWrapper.INSTANCE.po(waybillValidateDTO);
        waybillValidateRepositoryService.save(scfConfig);
    }
}
