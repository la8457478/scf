package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.PushWaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: scf权限 Dubbo Service</p>
 *
 * @author chenli
 * @version 1.0.1
 * @email "mailto:chenli@fkhwl.com"@fkhwl.com
 * @date 2020.02.20 10:20
 */
public interface WaybillProvider {

    /**
     * 分页查询运单
     *
     * @param params 查询参数
     * @return 返回分页运单数据 page
     */
    IPage<WaybillDTO> listPage(Map<String, Object> params);
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
    WaybillDTO getDetail(Long id);

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
  * 推送运单，创建运单，计划，项目
   * @param dto
  * @return: void
  * @Author: liuan
  * @Date: 2020/3/3 14:48
  */
    void pushWaybill(PushWaybillDTO dto);

    /**
     * 生成应收款转让
     *
     * @param params waybillNo/projectId/subjectClaimsOrderId/creditApplyId
     */
    void generateSubjectClaimsOrder(Map<String, Object> params);

    /**
     * 根据参数更新运单
     */
    void updateByParams(Map<String, Object> params);


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
