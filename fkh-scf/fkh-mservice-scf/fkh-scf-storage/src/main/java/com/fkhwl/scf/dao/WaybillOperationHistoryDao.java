package com.fkhwl.scf.dao;

import com.fkhwl.scf.entity.dto.WaybillOperationHistoryDTO;
import com.fkhwl.scf.entity.po.WaybillOperationHistory;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单操作历史 Dao 接口  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 15:48
 */
@Mapper
public interface WaybillOperationHistoryDao extends BaseDao<WaybillOperationHistory> {
    /**
     * 查询运单操作历史
     *
     * @param waybillId 运单id
     * @return 返回运单操作历史数据
     */
    List<WaybillOperationHistoryDTO> listByWaybillId(@Param("waybillId")Long waybillId);
}
