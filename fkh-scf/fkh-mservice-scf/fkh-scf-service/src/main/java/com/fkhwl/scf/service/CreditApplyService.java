package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.entity.po.ScfUser;

import java.io.*;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用款申请 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface CreditApplyService {
    /**
     * 分页查询标的债权订单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页用款申请数据 page
     */
    IPage<CreditApplyDTO> listPage(IPage<CreditApplyDTO> page, Map<String, Object> params);

    IPage<CreditApplyListDTO> listCreditApplyPage(IPage<CreditApplyListDTO> page, Map<String, Object> params);

    /**
     * 获取用款申请详情
     *
     * @param id  用款申请id
     * @return 角色详情DTO optional
     */
    CreditApplyDTO getDetail(Long id);

    /**
     * 新增和更新用款申请
     *
     * @param creditApplyDTO 用款申请实体
     */
    void saveOrUpdate(CreditApplyDTO creditApplyDTO);

    /**
     * 删除用款申请
     *
     * @param id 用款申请id
     */
    void delete(Long id);

    /**
     * 功能描述:生成用款申请
     *
     * @param creditApplyDTO
     * @param subjectClaimsOrderIds
     * @return: void
     * @Author: liuan
     * @Date: 2020/2/27 13:27
     */
    void createCreditApply(CreditApplyDTO creditApplyDTO, String subjectClaimsOrderIds);

    /**
    * 功能描述:审核用款申请
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
    * @Date: 2020/3/2 19:07
    */
    Map<String, Object> review(Long creditApplyId, Boolean passStatus, String reviewReason, Long userId, Boolean isValid, Long ownerId, Long companyId, String reviewBillUrl);


    /**
    * 审核详情
     * @param creditApplyId
    * @return: com.fkhwl.scf.entity.dto.CreditApplyListDTO
    * @Author: liuan
    * @Date: 2020/3/12 15:59
    */
    CreditApplyReviewDTO getReviewDetail(Long creditApplyId, Long companyCapitalId);

    /**
     * 重新生成用款申请
     * @param creditApplyDTO
     */
    void reCreateCreditApply(CreditApplyDTO creditApplyDTO);
}

