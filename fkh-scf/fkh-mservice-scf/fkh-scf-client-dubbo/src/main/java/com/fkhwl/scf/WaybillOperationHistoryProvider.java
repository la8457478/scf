package com.fkhwl.scf;


import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface WaybillOperationHistoryProvider {

    /**
     * 查询运单操作历史
     *
     * @param waybillId 运单id
     * @return 返回运单操作历史数据
     */
    List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId);
}
