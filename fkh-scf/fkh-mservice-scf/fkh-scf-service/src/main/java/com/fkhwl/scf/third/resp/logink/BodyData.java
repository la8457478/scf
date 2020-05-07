package com.fkhwl.scf.third.resp.logink;

import java.io.*;

import lombok.Data;

/**
 * logink-车辆查询接口返回数据字段
 */
@Data
public class BodyData implements Serializable {
    private static final long serialVersionUID = 5166931496280206563L;
    /*
     *  "vehicleNumber": "浙CD5921",
     "licensePlateTypeCode": "2",
     "vinNo": null,
     "vehicleClassification": "厢式货车",
     "vehicleLength": null,
     "vehicleWidth": null,
     "vehicleHeight": null,
     "vehicleMaximumTractionWeight": null,
     "vehicleTonnage": "4",
     "businessState": null,
     "businessStateCode": null,
     "roadTransportCertificateNumber": "33032700XXXX",
     "certificationUnit": "苍南县道路运输管理局",
     "periodStartDate": "20161206",
     "periodEndDate": "20191206",
     "businessScope": "货运：普通货运。",
     "checkStateExpireDate": null,
     "vehicleCorporationName": "熊锐",
     "issuingDate": "20161206"
     */
    /**
     * 车辆牌照号
     */
    private String vehicleNumber;
    /**
     * 牌照颜色类型代码
     */
    private String licensePlateTypeCode;
    /**
     * 车辆车架号
     */
    private String vinNo;
    /**
     * 车辆类型
     */
    private String vehicleClassification;
    /**
     * 车辆车长
     */
    private String vehicleLength;
    /**
     * 车辆车宽
     */
    private String vehicleWidth;
    /**
     * 车辆车高
     */
    private String vehicleHeight;
    /**
     * 车辆准牵引总质量
     */
    private String vehicleMaximumTractionWeight;
    /**
     * 核定载质量
     */
    private String vehicleTonnage;
    /**
     * 车辆营运状态
     */
    private String businessState;
    /**
     * 车辆营运状态代码
     */
    private String businessStateCode;
    /**
     * 道路运输证号
     */
    private String roadTransportCertificateNumber;
    /**
     * 发证单位
     */
    private String certificationUnit;
    /**
     * 有效期起
     */
    private String periodStartDate;
    /**
     * 有效期止
     */
    private String periodEndDate;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 车辆年度审验有效期至
     */
    private String checkStateExpireDate;
    /**
     * 业户名称
     */
    private String vehicleCorporationName;
    /**
     * 发证日期
     */
    private String issuingDate;
}
