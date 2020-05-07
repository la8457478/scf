package com.fkhwl.scf.third.resp.logink;

import java.io.*;

/**
 * <p>Title: com.fkhwl.dbmaster.domain</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: 资金流水单财务信息</p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2018年03月01日 12点01分
 * updatetime:
 * reason:
 */
public class Financial implements Serializable {
    private static final long serialVersionUID = -2936324314686750573L;

    public static final String BankDraft = "33"; // 银行汇票
    public static final String BankTransfer = "39"; // 银行转账
    public static final String CashPayty = "42"; // 现金支付
    public static final String ThirdParty = "7"; // 第三方平台支付
    public static final String Alipay = "71"; // 支付宝支付
    public static final String WeChatPay = "72"; // 微信支付
    public static final String OtherPay = "9"; // 其他方式支付
    public static final String OilCardPay = "91"; // 油卡支付
    public static final String PikePay = "92"; // 道路桥闸通行费支付

    /** 支付类型 */
    private String paymentMeansCode;
    /** 银行代码 */
    private String bankCode;
    /** 流水单号 */
    private String sequenceCode;
    /** 流水金额 */
    private String monetaryAmount;
    /** 支付时间 */
    private String dateTime;

    public Financial(){}

    public Financial(String paymentMeansCode, String bankCode, String sequenceCode, String monetaryAmount, String dateTime) {
        this.paymentMeansCode = paymentMeansCode;
        this.bankCode = bankCode;
        this.sequenceCode = sequenceCode;
        this.monetaryAmount = monetaryAmount;
        this.dateTime = dateTime;
    }

    public String getPaymentMeansCode() {
        return paymentMeansCode;
    }

    public void setPaymentMeansCode(String paymentMeansCode) {
        this.paymentMeansCode = paymentMeansCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSequenceCode() {
        return sequenceCode;
    }

    public void setSequenceCode(String sequenceCode) {
        this.sequenceCode = sequenceCode;
    }

    public String getMonetaryAmount() {
        return monetaryAmount;
    }

    public void setMonetaryAmount(String monetaryAmount) {
        this.monetaryAmount = monetaryAmount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
