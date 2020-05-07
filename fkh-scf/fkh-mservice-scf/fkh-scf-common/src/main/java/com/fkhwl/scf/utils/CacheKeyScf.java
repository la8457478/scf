package com.fkhwl.scf.utils;

import com.fkhwl.starter.core.util.StringUtils;

/**
 * <p>Title: 统计缓存的key设置</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0 
 * date: 2019-01-11
 * updatetime:
 * reason:
 */
public class CacheKeyScf {

	/**
	 * 统计数据的key前缀
	 */
	private static final String SCF_KEY ="scf"+getSuffixStr()+":";
    public static final Integer LOCK_TIME =5*60;
	/**
	 * 分布式锁，用于处理并发操作问题
	 */
	private static final String LOCK = "lock:";

    private static String suffix=getSuffixStr();
    /**
     * 获取配置信息
     * 开发和测试环境，自动增加后缀：
     * 1、避免每次发版时，需要手动调整
     * 2、避免和开发人员冲突
     * @return
     */
    private static String getSuffixStr(){
        String envValue = System.getenv("fkh_dev_profile");
        //开发和测试环境，自动增加后缀：1、避免每次发版时，需要手动调整 2、避免和开发人员冲突
        if(StringUtils.isNotBlank(envValue) &&("test".equals(envValue)||"dev".equals(envValue))){
            return "_"+envValue;
        }
        return "";
    }
	/**
	 * 推送运单，锁运单
	 * @param waybillId
	 * @return
	 */
	public static String getPushWaybillLockKey(Object waybillId){
		return SCF_KEY +LOCK+"pubshWaybill:"+waybillId;
	}

	/**
	 * 运单---》应收账款，锁项目
	 * @param waybillId
	 * @return
	 */
	public static String getCreateSorderLockKey( Object waybillId){
        return SCF_KEY +LOCK+"createSorder:" +waybillId;
	}
    /**
     * 运单---》标记异常，锁运单
     * @param waybillId
     * @return
     */
    public static String getSignExceptionLockKey( Object waybillId){
        return SCF_KEY +LOCK+"signException:" +waybillId;
    }
    /**
     * 应收账款---》用款申请，锁应收账款
     * @param creditApplyId
     * @return
     */
    public static String getCreateCreditApplyLockKey( Object creditApplyId){
        return SCF_KEY +LOCK+"createCreditApply:" +creditApplyId;
    }
    /**
     * 用款申请---》审核，同一个审核多人审核，只能同时一个人操作，锁用款申请
     * 从运营，一直到出纳放款，生成账单
     * @param creditApplyId
     * @return
     */
    public static String getCreditOrderLockKey( Object creditApplyId){
        return SCF_KEY +LOCK+"creditOrder:" +creditApplyId;
    }
    /**
     * 用款申请---》运营审核时，对同一个交易对手的运单，同时只能有一人操作
     * @param counterpartyId
     * @return
     */
    public static String getOperatorReviewLockKey( Object counterpartyId){
        return SCF_KEY +LOCK+"operatorReview:" +counterpartyId;
    }
    /**
     * 用户发起还款申请-->生成账单，锁 交易对手id
     * @param counterpartyId
     * @return
     */
    public static String getCreateAccountBillLockKey( Object counterpartyId){
        return SCF_KEY +LOCK+"createAccountBill:" +counterpartyId;
    }
    /**
     * 确认还款，锁 还款申请
     * @param repayBillId
     * @return
     */
    public static String getReviewRepayBillLockKey( Object repayBillId){
        return SCF_KEY +LOCK+"reviewRepayBill:" +repayBillId;
    }
    /**
     * 账单提醒，锁 公司
     * @param accountBillId
     * @return
     */
    public static String getAccountNotifyBillLockKey( Object accountBillId){
        return SCF_KEY +LOCK+"accountNotify:" +accountBillId;
    }

}
