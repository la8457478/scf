package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.dto.AccountBillRateDTO;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface AccountBillProvider {

    /**
     * 分页查询账单
     *
     * @param params 查询参数
     * @return 返回分页标的账单数据 page
     */
    IPage<AccountBillDTO> listPage(Map<String, Object> params);

    /**
     * 获取账单情
     *
     * @param id  账单id
     * @return 账单详情DTO optional
     */
    AccountBillDTO getDetail(Long id);

    /**
     * 新增和更新账单
     *
     * @param accountBillDTO 账单实体
     */
    void saveOrUpdate(AccountBillDTO accountBillDTO);


    /**
     * 删除标的账单
     *
     * @param id 标的账单id
     */
    void delete(Long id);

    /**
     * 提交还款申请
     * @param accountBillRateDTO
     */
    void repayBill(RepayBillDTO accountBillRateDTO);
    /**
     * 还款管理 账单还款
     *
     * @param accountBillId
     * @param repayBalance
     * @return: void
     * @Author: liuan
     * @Date: 2020/2/29 16:17
     */
    IPage<Map<String, Object>> listRepayPage(Map<String, Object> params);
    /**
    * 账单提醒
     * @param accountBillId
    * @param scfUserVO
     * @return: void
    * @Author: liuan
    * @Date: 2020/3/8 15:41
    */
    String notify(Long accountBillId, ScfUserVO scfUserVO);

    /**
     * 账单还款时，通过还款本金，计算需要还款的利息
     *
     * @param repayBalance
     * @param counterpartyId 交易对手id
     * @param userId
     * @param ownerId
     */
    List<AccountBillRateDTO> calculateRate(BigDecimal repayBalance,String repayDateStr, Long counterpartyId, Long userId, Long ownerId);

    /**
    * 根据用款申请获取账单
     * @param creditApplyId
    * @return: void
    * @Author: liuan
    * @Date: 2020/3/31 20:06
    */
    AccountBillDTO getByCreditApplyId(Long creditApplyId);

    /**
    * 账单管理列表
     * @param params
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fkhwl.scf.entity.vo.AccountBillListVO>
    * @Author: liuan
    * @Date: 2020/4/1 20:35
    */
    IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params);
}
