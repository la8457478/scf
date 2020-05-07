package com.fkhwl.scf;


import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface WaybillCheckRecordProvider {

    /**
     *保存运单查阅记录
     *
     * @param waybillCheckRecordDTO 运单查阅记录
     */
    void save(WaybillCheckRecordDTO waybillCheckRecordDTO);
}
