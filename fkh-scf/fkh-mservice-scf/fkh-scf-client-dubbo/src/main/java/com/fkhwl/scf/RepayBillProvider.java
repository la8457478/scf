package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.RepayBillDTO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 发起的还款订单 dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface RepayBillProvider {


    /**
     * 获取账单情
     *
     * @param id  账单id
     * @return 账单详情DTO optional
     */
    RepayBillDTO getDetail(Long id);

    /**
     * 检查是否已经有该交易对手的
     * @param id
     * @return
     */
    boolean checkHadRepay(Long counterpartyId);

    /**
    * 检查是否已有改交易对手和所有账单总金额
     * @param counterpartyId
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Author: liuan
    * @Date: 2020/4/7 10:45
    */
    Map<String,Object> checkHadRepayAndRemainBalance(Long counterpartyId);

    /**
     * 新增和更新账单
     *
     * @param repayBillDTO 账单实体
     */
    void saveOrUpdate(RepayBillDTO repayBillDTO);


    /**
     * 删除标的账单
     *
     * @param id 标的账单id
     */
    void delete(Long id);

    /**
    * 还款确认审核
     * @param repayBillId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/22 16:08
    */


    void reviewRepayBill(Long repayBillId, Boolean passStatus, Long companyId,String reviewReason);

    IPage<RepayBillDTO> listPage(IPage<RepayBillDTO> page, Map<String, Object> params);
}
