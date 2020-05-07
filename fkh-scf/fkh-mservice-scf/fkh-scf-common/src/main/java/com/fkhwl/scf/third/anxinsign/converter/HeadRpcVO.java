package com.fkhwl.scf.third.anxinsign.converter;

import java.io.*;

/**
 * @author wudq
 * @date 2019/12/10
 */
public class HeadRpcVO implements Serializable {
    public static final String CODE_SUCCESS = "60000000";
    public static final String MESSAGE_SUCCESS = "OK";
    private String txTime;
    private String locale;
    private String remark;
    private String retCode;
    private String retMessage;

    public HeadRpcVO() {
    }

    public String getTxTime() {
        return this.txTime;
    }

    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRetCode() {
        return this.retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return this.retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public String toString() {
        return "HeadVO [\ntxTime=" + this.txTime + "\nlocale=" + this.locale + "\nremark=" + this.remark + "\nretCode=" + this.retCode + "\nretMessage=" + this.retMessage + "\n]";
    }
}
