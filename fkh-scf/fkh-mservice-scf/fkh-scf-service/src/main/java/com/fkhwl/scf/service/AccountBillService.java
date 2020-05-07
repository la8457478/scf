package com.fkhwl.scf.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.AccountBillDTO;
import com.fkhwl.scf.entity.dto.RepayBillDTO;
import com.fkhwl.scf.entity.po.AccountBill;
import com.fkhwl.scf.entity.vo.AccountBillListVO;
import com.fkhwl.scf.entity.vo.ScfUserVO;

import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 账单 服务接口 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
public interface AccountBillService {
    /**
     * 分页查询标的债权订单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页账单数据 page
     */
    IPage<AccountBillDTO> listPage(IPage<AccountBillDTO> page, Map<String, Object> params);

    /**
     * 分页查询标的债权订单
     *
     * @param page   分页信息
     * @param params 查询参数
     * @return 返回分页账单数据 page
     */
    IPage<AccountBillDTO> listPageForRepay(IPage<AccountBillDTO> page, Map<String, Object> params);
    /**
     * 获取账单详情
     *
     * @param id  账单id
     * @return 角色详情DTO optional
     */
    AccountBillDTO getDetail(Long id);

    /**
     * 新增和更新账单
     *
     * @param accountBillDTO 账单实体
     */
    void saveOrUpdate(AccountBillDTO accountBillDTO);

    /**
     * 删除账单
     *
     * @param id 账单id
     */
    void delete(Long id);

    /**
     * 提交还款申请
     * @param accountBillRateDTO
     */
    void repayBill(RepayBillDTO accountBillRateDTO);
    /**
     * 还款管理列表
     * @param
     * @return: void
     * @Author: liuan
     * @Date: 2020/2/29 16:18
     */
    IPage<Map<String, Object>> listRepayPage(IPage<Map<String, Object>> page, Map<String, Object> params);

    /**
    * 用款申请获取账单:
     * @param creditApplyId
    * @return: com.fkhwl.scf.entity.dto.AccountBillDTO
    * @Author: liuan
    * @Date: 2020/3/31 20:07
    */
    AccountBillDTO getByCreditApplyId(Long creditApplyId);

    /**
    * 账单管理列表:
     * @param params
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fkhwl.scf.entity.vo.AccountBillListVO>
    * @Author: liuan
    * @Date: 2020/4/1 20:36
    */
    IPage<AccountBillListVO> listAccountBillListPage(IPage<AccountBillListVO> page,Map<String, Object> params);


    void updateById(AccountBill accountBill);

    /**
    * 账单提醒
     * @param accountBillId
     * @param scfUserVO
    * @return: java.lang.String
    * @Author: liuan
    * @Date: 2020/4/20 9:46
    */
    String notify(Long accountBillId, ScfUserVO scfUserVO);
}

