package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.RepayBillDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface RepayBillService {

    IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params);
    /**
     * 获取账单详情
     *
     * @param id  账单id
     * @return 角色详情DTO optional
     */
    RepayBillDTO getDetail(Long id);


     boolean checkHadRepay(Long counterpartyId);

    Map<String,Object> checkHadRepayAndRemainBalance(Long counterpartyId);

    /**
     * 删除账单
     *
     * @param id 账单id
     */
    void delete(Long id);

    /**
    * 还款确认审核
     * @param repayBillId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/22 16:12
    */

    void reviewRepayBill(Long repayBillId, Boolean passStatus, Long companyId,String reviewReason);
}

