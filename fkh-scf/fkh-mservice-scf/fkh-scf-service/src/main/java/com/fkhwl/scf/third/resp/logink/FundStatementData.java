package com.fkhwl.scf.third.resp.logink;

import java.io.*;
import java.util.List;

/**
 * <p>Title: com.fkhwl.dbmaster.domain</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: 资金流水单</p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2018年03月01日 11点43分
 * updatetime:
 * reason:
 */
public class FundStatementData implements Serializable {
    public static String AES_SALT = "oiwheg&12w1223wf";
    private static final long serialVersionUID = 1506043386009665583L;

    /** 报文唯一标识 */
    private String messageReferenceNumber;
    /** 发送时间 */
    private String messageSendingDateTime;

    /** 本资金流水单号 */
    private String documentNumber;
    /** 实际承运人 */
    private String carrier;
    /** 车牌号 */
    private String vehicleNumber;
    /** 运单号列表 */
    private List<String> waybillNos;
    /** 财务列表 */
    private List<Financial> financials;

    public String getMessageReferenceNumber() {
        return messageReferenceNumber;
    }

    public void setMessageReferenceNumber(String messageReferenceNumber) {
        this.messageReferenceNumber = messageReferenceNumber;
    }

    public String getMessageSendingDateTime() {
        return messageSendingDateTime;
    }

    public void setMessageSendingDateTime(String messageSendingDateTime) {
        this.messageSendingDateTime = messageSendingDateTime;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public List<String> getWaybillNos() {
        return waybillNos;
    }

    public void setWaybillNos(List<String> waybillNos) {
        this.waybillNos = waybillNos;
    }

    public List<Financial> getFinancials() {
        return financials;
    }

    public void setFinancials(List<Financial> financials) {
        this.financials = financials;
    }
}


