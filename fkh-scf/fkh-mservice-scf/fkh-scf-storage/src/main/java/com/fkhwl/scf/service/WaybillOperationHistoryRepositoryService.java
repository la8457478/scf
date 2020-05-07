package com.fkhwl.scf.service;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.po.WaybillOperationHistory;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 服务接口 </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 15:48
 */
public interface WaybillOperationHistoryRepositoryService extends BaseService<WaybillOperationHistory> {

    /**
     * 查询运单操作历史
     *
     * @param waybillId 运单id
     * @return 返回运单操作历史数据
     */
    List<WaybillOperationHistoryDTO> listByWaybillId(Long waybillId);
}

