package com.fkhwl.scf.third.resp.logink;

import java.io.*;

public class RoadTransportCertificateInformationBean implements Serializable {
    private static final long serialVersionUID = -1888646038796452565L;
    /**
             * RoadTransportCertificateNumber : 14088215808
             * VehicleNumber : 晋M86897
             * VehicleClassification : 重型半挂牵引车
             * VehicleLength : 7150 mm
             * VehicleWidth : 2500 mm
             * VehicleHeight : 3425 mm
             * BusinessScope : 道路普通货物运输
             * LicenseInitialCollectionDate : 2013-09-10
             * PeriodStartDate : 2017-04-21
             * PeriodEndDate : 2020-04-21
             * CertificationUnit : 河津市道路运输管理所
             * Photo :
             * BusinessState : 营运
             * BusinessStateCode : 10
             */

            private String RoadTransportCertificateNumber;
            private String VehicleNumber;
            private String VehicleClassification;
            private String VehicleLength;
            private String VehicleWidth;
            private String VehicleHeight;
            private String VehicleTonnage;
            private String BusinessScope;
            private String LicenseInitialCollectionDate;
            private String PeriodStartDate;
            private String PeriodEndDate;
            private String CertificationUnit;
            private String Photo;
            private String BusinessState;
            private String BusinessStateCode;
            private String PermitNumber;

            public String getRoadTransportCertificateNumber() {
                return RoadTransportCertificateNumber;
            }

            public void setRoadTransportCertificateNumber(String RoadTransportCertificateNumber) {
                this.RoadTransportCertificateNumber = RoadTransportCertificateNumber;
            }

            public String getVehicleNumber() {
                return VehicleNumber;
            }

            public void setVehicleNumber(String VehicleNumber) {
                this.VehicleNumber = VehicleNumber;
            }

            public String getVehicleClassification() {
                return VehicleClassification;
            }

            public void setVehicleClassification(String VehicleClassification) {
                this.VehicleClassification = VehicleClassification;
            }

            public String getVehicleLength() {
                return VehicleLength;
            }

            public void setVehicleLength(String VehicleLength) {
                this.VehicleLength = VehicleLength;
            }

            public String getVehicleWidth() {
                return VehicleWidth;
            }

            public void setVehicleWidth(String VehicleWidth) {
                this.VehicleWidth = VehicleWidth;
            }

            public String getVehicleHeight() {
                return VehicleHeight;
            }

            public void setVehicleHeight(String VehicleHeight) {
                this.VehicleHeight = VehicleHeight;
            }

            public String getVehicleTonnage() {
                return VehicleTonnage;
            }

            public void setVehicleTonnage(String vehicleTonnage) {
                VehicleTonnage = vehicleTonnage;
            }

            public String getPermitNumber() {
                return PermitNumber;
            }

            public void setPermitNumber(String permitNumber) {
                PermitNumber = permitNumber;
            }

            public String getBusinessScope() {
                        return BusinessScope;
                    }

            public void setBusinessScope(String BusinessScope) {
                this.BusinessScope = BusinessScope;
            }

            public String getLicenseInitialCollectionDate() {
                return LicenseInitialCollectionDate;
            }

            public void setLicenseInitialCollectionDate(String LicenseInitialCollectionDate) {
                this.LicenseInitialCollectionDate = LicenseInitialCollectionDate;
            }

            public String getPeriodStartDate() {
                return PeriodStartDate;
            }

            public void setPeriodStartDate(String PeriodStartDate) {
                this.PeriodStartDate = PeriodStartDate;
            }

            public String getPeriodEndDate() {
                return PeriodEndDate;
            }

            public void setPeriodEndDate(String PeriodEndDate) {
                this.PeriodEndDate = PeriodEndDate;
            }

            public String getCertificationUnit() {
                return CertificationUnit;
            }

            public void setCertificationUnit(String CertificationUnit) {
                this.CertificationUnit = CertificationUnit;
            }

            public String getPhoto() {
                return Photo;
            }

            public void setPhoto(String Photo) {
                this.Photo = Photo;
            }

            public String getBusinessState() {
                return BusinessState;
            }

            public void setBusinessState(String BusinessState) {
                this.BusinessState = BusinessState;
            }

            public String getBusinessStateCode() {
                return BusinessStateCode;
            }

            public void setBusinessStateCode(String BusinessStateCode) {
                this.BusinessStateCode = BusinessStateCode;
            }
        }
