package com.fkhwl.scf.third.resp.logink;

import java.io.*;

public class BodyBean implements Serializable {
    private static final long serialVersionUID = 3330955329879674782L;
    /**
     * VehicleNumber : 晋M86897
     * LicensePlateTypeCode : 黄色
     * Owner : 河津市久运汽车运输有限公司
     * DrivingLicenceInformation : {"VehicleNumber":"晋M86897","VehicleBrandModel":"大运牌",
     * "VehicleEngineNumber":"1613F102156","VehicleMaximumTractionWeight":"37 吨","Photo":""}
     * RoadTransportCertificateInformation : {"RoadTransportCertificateNumber":"14088215808",
     * "VehicleNumber":"晋M86897","VehicleClassification":"重型半挂牵引车","VehicleLength":"7150 mm","VehicleWidth":"2500
     * mm","VehicleHeight":"3425 mm","BusinessScope":"道路普通货物运输","LicenseInitialCollectionDate":"2013-09-10",
     * "PeriodStartDate":"2017-04-21","PeriodEndDate":"2020-04-21","CertificationUnit":"河津市道路运输管理所","Photo":"",
     * "BusinessState":"营运","BusinessStateCode":"10"}
     */

        private String VehicleNumber;
        private String LicensePlateTypeCode;
        private String Owner;
        private DrivingLicenceInformationBean DrivingLicenceInformation;
        private RoadTransportCertificateInformationBean RoadTransportCertificateInformation;

        public String getVehicleNumber() {
            return VehicleNumber;
        }

        public void setVehicleNumber(String VehicleNumber) {
            this.VehicleNumber = VehicleNumber;
        }

        public String getLicensePlateTypeCode() {
            return LicensePlateTypeCode;
        }

        public void setLicensePlateTypeCode(String LicensePlateTypeCode) {
            this.LicensePlateTypeCode = LicensePlateTypeCode;
        }

        public String getOwner() {
            return Owner;
        }

        public void setOwner(String Owner) {
            this.Owner = Owner;
        }

        public DrivingLicenceInformationBean getDrivingLicenceInformation() {
            return DrivingLicenceInformation;
        }

        public void setDrivingLicenceInformation(DrivingLicenceInformationBean DrivingLicenceInformation) {
            this.DrivingLicenceInformation = DrivingLicenceInformation;
        }

        public RoadTransportCertificateInformationBean getRoadTransportCertificateInformation() {
            return RoadTransportCertificateInformation;
        }

        public void setRoadTransportCertificateInformation(RoadTransportCertificateInformationBean
                                                               RoadTransportCertificateInformation) {
            this.RoadTransportCertificateInformation = RoadTransportCertificateInformation;
        }
    }
