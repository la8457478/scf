package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.starter.mybatis.service.BaseService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 货主运单表 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-20
 */
public interface WaybillRepositoryService extends BaseService<Waybill> {

    /**
     * 分页查询运单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<WaybillDTO> listPage(IPage<WaybillDTO> page, Map<String, Object> params);

    /**
     * 查询需要生成标的债券运单列表信息
     *
     * @param params 查询参数：waybillNo/projectId/subjectClaimsOrderId/creditApplyId
     * @return 返回运单数据
     */
    List<WaybillDTO> listSubjectClaimsOrderWaybill(Map<String, Object> params);

    /**
     * 更新运单标的债券id字段
     *
     * @param subjectClaimsOrderId 标的债券id
     * @param ids 运单id集合
     * @param updateTime 更新时间
     */
    int updateSubjectClaimsOrderId(Boolean isNewCreate,Long subjectClaimsOrderId, List<Long> ids, Date updateTime);

    /**
     * 根据参数更新运单
     */
    void updateByParams(Map<String, Object> params);

    /**
     * 查询运单id
     *
     * @param params 查询参数
     * @return 返回运单id
     */
    List<Long> listIdsByParams(Map<String, Object> params);
}

