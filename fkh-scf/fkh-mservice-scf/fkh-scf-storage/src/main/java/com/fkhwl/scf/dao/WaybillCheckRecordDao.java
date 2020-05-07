package com.fkhwl.scf.dao;

import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.po.WaybillCheckRecord;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单查阅记录表 Dao 接口  </p>
 *
 * @author hezhiming
 * @version 1.0.0
 * @email "mailto:hezhiming@fkhwl.com"
 * @date 2020.03.21 21:49
 */
@Mapper
public interface WaybillCheckRecordDao extends BaseDao<WaybillCheckRecord> {

    /**
     * 查询条数
     *
     * @param creditApplyId 用款申请id
     * @return 返回运单操作历史数据
     */
    Integer countByCreditApplyId(@Param("creditApplyId")Long creditApplyId);

    /**
     * 根据运单id查询
     *
     * @param waybillId 运单id
     * @return 返回运单操作历史数据
     */
    WaybillCheckRecordDTO getByWaybillId(@Param("waybillId")Long waybillId);
}
