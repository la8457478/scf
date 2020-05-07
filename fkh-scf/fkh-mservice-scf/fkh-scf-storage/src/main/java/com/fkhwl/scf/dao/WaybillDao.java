package com.fkhwl.scf.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.starter.common.base.BaseDao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 货主运单表 Dao 接口  </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
@Mapper
public interface WaybillDao extends BaseDao<Waybill> {

    /**
     * 分页查询运单
     *
     * @param page   分页参数
     * @param params 查询参数
     * @return 运单列表 page
     */
    IPage<WaybillDTO> listPage(@Param("page") IPage<WaybillDTO> page,
                               @Param("map") Map<String, Object> params);

    /**
     * 查询需要生成标的债券运单列表信息
     *
     * @param params 查询参数：waybillNo/projectId/subjectClaimsOrderId/creditApplyId
     * @return 返回运单数据
     */
    List<WaybillDTO> listSubjectClaimsOrderWaybill(@Param("map") Map<String, Object> params);

    /**
     * 更新运单标的债券id字段
     *
     * @param subjectClaimsOrderId 标的债券id
     * @param ids 运单id集合
     */
    int updateSubjectClaimsOrderId(
        @Param("isNewCreate")Boolean isNewCreate,
                                @Param("subjectClaimsOrderId")Long subjectClaimsOrderId,
                                   @Param("ids")List<Long> ids,
                                   @Param("updateTime")Date updateTime);

    /**
     * 根据参数更新运单
     */
    void updateByParams(@Param("map")Map<String, Object> params);

    /**
     * 查询运单id
     *
     * @param params 查询参数
     * @return 返回运单id
     */
    List<Long> listIdsByParams(@Param("map")Map<String, Object> params);
}
