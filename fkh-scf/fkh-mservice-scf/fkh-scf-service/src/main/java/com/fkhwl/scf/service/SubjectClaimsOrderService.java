package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.vo.ReviewPageVo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 债权订单 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface SubjectClaimsOrderService {
    /**
     * 分页查询标的债权订单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页标的债权订单数据 page
     */
    IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page, Map<String, Object> params);

    /**
     * 获取标的债权订单详情
     *
     * @param id  标的债权订单id
     * @return 角色详情DTO optional
     */
    SubjectClaimsOrderDTO getDetail(Long id);

    /**
     * 新增和更新标的债权订单
     *
     * @param subjectClaimsOrderDTO 标的债权订单实体
     */
    void saveOrUpdate(SubjectClaimsOrderDTO subjectClaimsOrderDTO);

    /**
     * 删除标的债权订单
     *
     * @param id 标的债权订单id
     */
    void delete(Long id);
    /**
    * 审核页面数据
     * @param params
    * @return: java.util.List<com.fkhwl.scf.entity.vo.ReviewPageVo>
    * @Author: liuan
    * @Date: 2020/3/1 13:42
    */
    List<ReviewPageVo> reviewPage(Map<String, Object> params);

    /**
     * 批量保存
     *
     * @param list 标的债权订单实体集合
     */
    void saveBatch(Collection<SubjectClaimsOrder> list);

    /**
    * 计算发起用款申请信息
     * @param subjectClaimsOrderIds
    * @param counterpartyId
     * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: liuan
    * @Date: 2020/3/24 11:42
    */
    Map<String, Object> calculate(String subjectClaimsOrderIds, Long counterpartyId);

    /**
    * 保存:
     * @param subjectClaimsOrder
    * @return: void
    * @Author: liuan
    * @Date: 2020/4/21 14:00
    */
    void save(SubjectClaimsOrder subjectClaimsOrder);
}

