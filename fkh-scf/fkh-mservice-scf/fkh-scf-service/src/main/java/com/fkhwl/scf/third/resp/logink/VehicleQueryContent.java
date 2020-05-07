package com.fkhwl.scf.third.resp.logink;

import java.io.*;

import lombok.Data;

/**
 * logink-车辆查询接口响应
 */
@Data
public class VehicleQueryContent implements Serializable {
    private static final long serialVersionUID = 2296497534943884529L;
    private String resultCode;
    private String resultMsg;
    private String traceId;
    private BodyData data;

    /**
     * 将车辆查询接口返回的数据转换为老接口返回的数据结构，保证在logink服务里面升级接口不影响调用方
     * @return
     */
    public BizContent toBizContent() {
        BizContent content = new BizContent();
        if(data != null) {
            HeaderBean headerBean = new HeaderBean();
            content.setHeader(headerBean);

            BodyBean bodyBean = new BodyBean();
            bodyBean.setVehicleNumber(data.getVehicleNumber());
            bodyBean.setLicensePlateTypeCode(data.getLicensePlateTypeCode());
            bodyBean.setOwner(data.getVehicleCorporationName());

            DrivingLicenceInformationBean licenceInfo = new DrivingLicenceInformationBean();
            licenceInfo.setVehicleNumber(data.getVehicleNumber());
            licenceInfo.setVehicleMaximumTractionWeight(data.getVehicleMaximumTractionWeight());

            RoadTransportCertificateInformationBean transportInfo = new RoadTransportCertificateInformationBean();
            transportInfo.setRoadTransportCertificateNumber(data.getRoadTransportCertificateNumber());
            transportInfo.setVehicleNumber(data.getVehicleNumber());
            transportInfo.setVehicleClassification(data.getVehicleClassification());
            transportInfo.setVehicleLength(data.getVehicleLength());
            transportInfo.setVehicleWidth(data.getVehicleWidth());
            transportInfo.setVehicleHeight(data.getVehicleHeight());
            transportInfo.setBusinessScope(data.getBusinessScope());
            transportInfo.setLicenseInitialCollectionDate(data.getIssuingDate());
            transportInfo.setPeriodStartDate(data.getPeriodStartDate());
            transportInfo.setPeriodEndDate(data.getPeriodEndDate());
            transportInfo.setCertificationUnit(data.getCertificationUnit());
            transportInfo.setBusinessState(data.getBusinessState());
            transportInfo.setBusinessStateCode(data.getBusinessStateCode());
            if (!"0".equals(data.getVehicleTonnage())){
                transportInfo.setVehicleTonnage(data.getVehicleTonnage());
            }

            bodyBean.setDrivingLicenceInformation(licenceInfo);
            bodyBean.setRoadTransportCertificateInformation(transportInfo);
            content.setBody(bodyBean);
        }
        return content;
    }
}
