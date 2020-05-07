package com.fkhwl.scf.third.resp.logink;

import java.io.*;

/**
 * @author dong4j.
 * email dong4j@gmail.com
 * date 2017年10月24日 上午10点:34分
 * describe
 */
public class CarInfoEntity implements Serializable {
    private static final long serialVersionUID = -6394083817738485768L;

    private Long id;
    private String messageReferenceNumber = "";
    private String documentName = "";
    private String senderCode = "";
    private String documentVersionNumber = "";
    private String messageSendingDateTime = "";

    private String vehicleNumber = "";
    private String licensePlateTypeCode = "";
    private String owner = "";
    private String vehicleBrandModel = "";
    private String vehicleEngineNumber = "";
    private String vehicleMaximumTractionWeight = "";
    private String vehiclePhoto = "";
    private String roadTransportCertificateNumber = "";
    private String vehicleClassification = "";
    private String vehicleLength = "";
    private String vehicleWidth = "";
    private String vehicleHeight = "";
    private String businessScope = "";
    private String licenseInitialCollectionDate = "";
    private String periodStartDate = "";
    private String periodEndDate = "";
    private String certificationUnit = "";
    private String businessState = "";
    private String businessStateCode = "";
    private String transportPhoto = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageReferenceNumber() {
        return messageReferenceNumber;
    }

    public void setMessageReferenceNumber(String messageReferenceNumber) {
        this.messageReferenceNumber = messageReferenceNumber;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getDocumentVersionNumber() {
        return documentVersionNumber;
    }

    public void setDocumentVersionNumber(String documentVersionNumber) {
        this.documentVersionNumber = documentVersionNumber;
    }

    public String getMessageSendingDateTime() {
        return messageSendingDateTime;
    }

    public void setMessageSendingDateTime(String messageSendingDateTime) {
        this.messageSendingDateTime = messageSendingDateTime;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getLicensePlateTypeCode() {
        return licensePlateTypeCode;
    }

    public void setLicensePlateTypeCode(String licensePlateTypeCode) {
        this.licensePlateTypeCode = licensePlateTypeCode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVehicleBrandModel() {
        return vehicleBrandModel;
    }

    public void setVehicleBrandModel(String vehicleBrandModel) {
        this.vehicleBrandModel = vehicleBrandModel;
    }

    public String getVehicleEngineNumber() {
        return vehicleEngineNumber;
    }

    public void setVehicleEngineNumber(String vehicleEngineNumber) {
        this.vehicleEngineNumber = vehicleEngineNumber;
    }

    public String getVehicleMaximumTractionWeight() {
        return vehicleMaximumTractionWeight;
    }

    public void setVehicleMaximumTractionWeight(String vehicleMaximumTractionWeight) {
        this.vehicleMaximumTractionWeight = vehicleMaximumTractionWeight;
    }

    public String getVehiclePhoto() {
        return vehiclePhoto;
    }

    public void setVehiclePhoto(String vehiclePhoto) {
        this.vehiclePhoto = vehiclePhoto;
    }

    public String getRoadTransportCertificateNumber() {
        return roadTransportCertificateNumber;
    }

    public void setRoadTransportCertificateNumber(String roadTransportCertificateNumber) {
        this.roadTransportCertificateNumber = roadTransportCertificateNumber;
    }

    public String getVehicleClassification() {
        return vehicleClassification;
    }

    public void setVehicleClassification(String vehicleClassification) {
        this.vehicleClassification = vehicleClassification;
    }

    public String getVehicleLength() {
        return vehicleLength;
    }

    public void setVehicleLength(String vehicleLength) {
        this.vehicleLength = vehicleLength;
    }

    public String getVehicleWidth() {
        return vehicleWidth;
    }

    public void setVehicleWidth(String vehicleWidth) {
        this.vehicleWidth = vehicleWidth;
    }

    public String getVehicleHeight() {
        return vehicleHeight;
    }

    public void setVehicleHeight(String vehicleHeight) {
        this.vehicleHeight = vehicleHeight;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getLicenseInitialCollectionDate() {
        return licenseInitialCollectionDate;
    }

    public void setLicenseInitialCollectionDate(String licenseInitialCollectionDate) {
        this.licenseInitialCollectionDate = licenseInitialCollectionDate;
    }

    public String getPeriodStartDate() {
        return periodStartDate;
    }

    public void setPeriodStartDate(String periodStartDate) {
        this.periodStartDate = periodStartDate;
    }

    public String getPeriodEndDate() {
        return periodEndDate;
    }

    public void setPeriodEndDate(String periodEndDate) {
        this.periodEndDate = periodEndDate;
    }

    public String getCertificationUnit() {
        return certificationUnit;
    }

    public void setCertificationUnit(String certificationUnit) {
        this.certificationUnit = certificationUnit;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessStateCode() {
        return businessStateCode;
    }

    public void setBusinessStateCode(String businessStateCode) {
        this.businessStateCode = businessStateCode;
    }

    public String getTransportPhoto() {
        return transportPhoto;
    }

    public void setTransportPhoto(String transportPhoto) {
        this.transportPhoto = transportPhoto;
    }

    /**
     * 传入参数的JSON格式为
     * {
     *     "body":{
     *         "vehicleNumber":"川L76100",
     *         "licensePlateTypeCode":"黄色",
     *         "drivingLicenceInformation":{
     *             "vehicleNumber":"川L76100",
     *             "vehicleBrandModel":"斯达-斯太尔",
     *             "vehicleEngineNumber":"170317750827",
     *             "photo":""
     *         },
     *         "roadTransportCertificateInformation":{
     *             "vehicleClassification":"重型普通货车",
     *             "vehicleWidth":"2496 mm",
     *             "vehicleHeight":"3600 mm",
     *             "businessScope":"普通货运",
     *             "licenseInitialCollectionDate":"2017-04-17",
     *             "periodStartDate":"2018-04-04",
     *             "periodEndDate":"2018-08-03",
     *             "certificationUnit":"峨眉山市道路运输管理所",
     *             "businessState":"营运",
     *             "businessStateCode":"10",
     *             "vehicleNumber":"川L76100",
     *             "photo":"",
     *             "vehicleLength":"6895 mm",
     *             "roadTransportCertificateNumber":"511100071026"
     *         },
     *         "owner":"峨眉山市全平汽车加气补胎店 张全平."
     *     },
     *     "header":{
     *         "messageReferenceNumber":"",
     *         "documentName":"",
     *         "documentVersionNumber":"",
     *         "senderCode":"",
     *         "messageSendingDateTime":""
     *     }
     * }
     * @param content
     * @return
     */
    public static CarInfoEntity conversion(BizContent content){
        CarInfoEntity carInfoEntity = new CarInfoEntity();
        if(content != null){
            if(content.getHeader() != null){
                HeaderBean headerBean = content.getHeader();
                carInfoEntity.setMessageReferenceNumber(headerBean.getMessageReferenceNumber());
                carInfoEntity.setDocumentName(headerBean.getDocumentName());
                carInfoEntity.setSenderCode(headerBean.getSenderCode());
                carInfoEntity.setDocumentVersionNumber(headerBean.getDocumentVersionNumber());
                carInfoEntity.setMessageSendingDateTime(headerBean.getMessageSendingDateTime());
            }
            if(content.getBody() != null){
                BodyBean bodyBean = content.getBody();
                carInfoEntity.setVehicleNumber(bodyBean.getVehicleNumber());
                carInfoEntity.setLicensePlateTypeCode(bodyBean.getLicensePlateTypeCode());
                carInfoEntity.setOwner(bodyBean.getOwner());
                if(bodyBean.getDrivingLicenceInformation() != null){
                    DrivingLicenceInformationBean licenceInfo = bodyBean.getDrivingLicenceInformation();
                    carInfoEntity.setVehicleNumber(licenceInfo.getVehicleNumber());
                    carInfoEntity.setVehicleBrandModel(licenceInfo.getVehicleBrandModel());
                    carInfoEntity.setVehicleEngineNumber(licenceInfo.getVehicleEngineNumber());
                    carInfoEntity.setVehicleMaximumTractionWeight(licenceInfo.getVehicleMaximumTractionWeight());
                    carInfoEntity.setVehiclePhoto(licenceInfo.getPhoto());
                }
                if(bodyBean.getRoadTransportCertificateInformation() != null){
                    RoadTransportCertificateInformationBean transportInfor = bodyBean.getRoadTransportCertificateInformation();
                    carInfoEntity.setRoadTransportCertificateNumber(transportInfor.getRoadTransportCertificateNumber());
                    carInfoEntity.setVehicleNumber(transportInfor.getVehicleNumber());
                    carInfoEntity.setVehicleClassification(transportInfor.getVehicleClassification());
                    carInfoEntity.setVehicleLength(transportInfor.getVehicleLength());
                    carInfoEntity.setVehicleWidth(transportInfor.getVehicleWidth());
                    carInfoEntity.setVehicleHeight(transportInfor.getVehicleHeight());
                    carInfoEntity.setBusinessScope(transportInfor.getBusinessScope());
                    carInfoEntity.setLicenseInitialCollectionDate(transportInfor.getLicenseInitialCollectionDate());
                    carInfoEntity.setPeriodStartDate(transportInfor.getPeriodStartDate());
                    carInfoEntity.setPeriodEndDate(transportInfor.getPeriodEndDate());
                    carInfoEntity.setCertificationUnit(transportInfor.getCertificationUnit());
                    carInfoEntity.setTransportPhoto(transportInfor.getPhoto());
                    carInfoEntity.setBusinessState(transportInfor.getBusinessState());
                    carInfoEntity.setBusinessStateCode(transportInfor.getBusinessStateCode());
                }
            }
        }
        return carInfoEntity;
    }

    @Override
    public String toString() {
        return "CarInfoEntity{" +
            "id=" + id +
            ", messageReferenceNumber='" + messageReferenceNumber + '\'' +
            ", documentName='" + documentName + '\'' +
            ", senderCode='" + senderCode + '\'' +
            ", documentVersionNumber='" + documentVersionNumber + '\'' +
            ", messageSendingDateTime='" + messageSendingDateTime + '\'' +
            ", vehicleNumber='" + vehicleNumber + '\'' +
            ", licensePlateTypeCode='" + licensePlateTypeCode + '\'' +
            ", owner='" + owner + '\'' +
            ", vehicleBrandModel='" + vehicleBrandModel + '\'' +
            ", vehicleEngineNumber='" + vehicleEngineNumber + '\'' +
            ", vehicleMaximumTractionWeight='" + vehicleMaximumTractionWeight + '\'' +
            ", vehiclePhoto='" + vehiclePhoto + '\'' +
            ", roadTransportCertificateNumber='" + roadTransportCertificateNumber + '\'' +
            ", vehicleClassification='" + vehicleClassification + '\'' +
            ", vehicleLength='" + vehicleLength + '\'' +
            ", vehicleWidth='" + vehicleWidth + '\'' +
            ", vehicleHeight='" + vehicleHeight + '\'' +
            ", businessScope='" + businessScope + '\'' +
            ", licenseInitialCollectionDate='" + licenseInitialCollectionDate + '\'' +
            ", periodStartDate='" + periodStartDate + '\'' +
            ", periodEndDate='" + periodEndDate + '\'' +
            ", certificationUnit='" + certificationUnit + '\'' +
            ", businessState='" + businessState + '\'' +
            ", businessStateCode='" + businessStateCode + '\'' +
            ", transportPhoto='" + transportPhoto + '\'' +
            '}';
    }
}
