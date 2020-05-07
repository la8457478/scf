package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface WaybillCheckRecordService {

    /**
     *保存运单查阅记录
     *
     * @param waybillCheckRecordDTO 运单查阅记录
     */
    void save(WaybillCheckRecordDTO waybillCheckRecordDTO);

}

