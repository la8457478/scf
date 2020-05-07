package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface CreditApplyProvider {

    /**
     * 分页查询用款申请
     *
     * @param params 查询参数
     * @return 返回分页标的用款申请数据 page
     */
    IPage<CreditApplyDTO> listPage(Map<String, Object> params);

    IPage<CreditApplyListDTO> listCreditApplyPage(Map<String, Object> params) ;
    IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page,Map<String, Object> params) ;
    /**
     * 获取用款申请情
     *
     * @param id  用款申请id
     * @return 用款申请详情DTO optional
     */
    CreditApplyDTO getDetail(Long id);

    /**
     * 新增和更新用款申请
     *
     * @param creditApplyDTO 用款申请实体
     */
    void saveOrUpdate(CreditApplyDTO creditApplyDTO);

    /**
     * 生成用款申请
     *  @param applyTransferBalance 申请转让金额
     * @param creditApplyDTO
     * @param subjectClaimsOrderIds 标的债权ids
     */
    void createCreditApply(CreditApplyDTO creditApplyDTO, String subjectClaimsOrderIds);

    /**
     * 删除标的用款申请
     *
     * @param id 标的用款申请id
     */
    void delete(Long id);

    /**
    *  审核用款申请
     * @param loanBalance
     * @param creator
     * @param creditApplyId
     * @param passStatus
     * @param reviewReason
     * @param userId
     * @param isValid
     * @param ownerId
     * @param reviewBillUrl
     * @return: void
     * @Author: liuan
     * @Date: 2020/3/2 19:05
     */
    Map<String, Object> review(Long creditApplyId, Boolean passStatus, String reviewReason, Long userId, Boolean isValid, Long ownerId, Long companyId, String reviewBillUrl);


    /**
    * 审核详情
     * @param creditApplyId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/12 15:56
    */
    CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId);

    /**
     * 重新生成用款申请
     * @param creditApplyDTO
     */
    void reCreateCreditApply(CreditApplyDTO creditApplyDTO);
}
