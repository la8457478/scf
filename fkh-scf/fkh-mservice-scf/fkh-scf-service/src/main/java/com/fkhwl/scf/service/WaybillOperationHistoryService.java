package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface WaybillOperationHistoryService {
    /**
     * 查询运单操作历史
     *
     * @param waybillId 运单id
     * @return 返回运单操作历史数据
     */
    List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId);

    /**
     * 批量保存运单操作历史
     *
     * @param waybillId 运单id
     * @param operationHistoryDTOList 操作历史集合
     */
    void saveBatch(Long waybillId, List<WaybillOperationHistoryDTO> operationHistoryDTOList);

}

