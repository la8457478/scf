package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.vo.ReviewPageVo;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 债权标的订单dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface SubjectClaimsOrderProvider {

    /**
     * 分页查询标的债权订单
     *
     * @param params 查询参数
     * @return 返回分页标的债权订单数据 page
     */
    IPage<SubjectClaimsOrderDTO> listPage(Map<String, Object> params);
    /**
     * 分页查询标的债权订单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页标的债权订单数据 page
     */
    IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page, Map<String, Object> params);
    /**
     * 获取标的债权订单情
     *
     * @param id  标的债权订单id
     * @return 标的债权订单详情DTO optional
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
    * 审核页面
     * @param params
    * @return: java.util.List<com.fkhwl.scf.entity.vo.ReviewPageVo>
    * @Author: liuan
    * @Date: 2020/3/1 13:41
    */
    List<ReviewPageVo> reviewPage(Map<String, Object> params);
/**
* 计算发起用款申请的应收账款到期日 融资利率
 * @param subjectClaimsOrderIds
* @param counterpartyId
 * @return: java.util.List<com.fkhwl.scf.entity.vo.ReviewPageVo>
* @Author: liuan
* @Date: 2020/3/24 11:39
*/
Map<String,Object> calculate(String subjectClaimsOrderIds, Long counterpartyId);
}
