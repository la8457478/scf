package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.WaybillDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单 服务接口 </p>
 *
 * @author chenli
 * @email chenli@fkhwl.com
 * @since 2020-02-18
 */
public interface WaybillService  {
    /**
     * 分页查询运单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<WaybillDTO> listPage(IPage<WaybillDTO> page, Map<String, Object> params);

    /**
     * 获取运单详情
     *
     * @param id  运单id
     * @return 运单详情DTO optional
     */
    Optional<WaybillDTO> getDetail(Long id);

    /**
     * 新增和更新运单
     *
     * @param waybillDTO 运单实体
     */
    Long saveOrUpdate(WaybillDTO waybillDTO);

    /**
     * 删除运单
     *
     * @param id 主键id
     */
    void delete(Long id);

    /**
     * 新增和更新运单
     *
     * @param params waybillNo/projectId/subjectClaimsOrderId/creditApplyId
     */
    void generateSubjectClaimsOrder(Map<String, Object> params);

    /**
     * 根据参数更新运单
     */
    void updateByParams(Map<String, Object> params);

    /**
     * 根据参数更新运单
     */
    void reCreateCreditApply(Map<String, Object> params);

    /**
     * 根据用款申请id查询发起用款申请页面的信息
     * @param creditApplyId 用款申请id
     * @param counterpartyId 交易对手id
     */
    Map<String,Object> getReStartCreditApplyData(Long creditApplyId, Long counterpartyId);

    /**
     * 查询运单id
     *
     * @param params 查询参数
     * @return 返回运单id
     */
    List<Long> listIdsByParams(Map<String, Object> params);
}

