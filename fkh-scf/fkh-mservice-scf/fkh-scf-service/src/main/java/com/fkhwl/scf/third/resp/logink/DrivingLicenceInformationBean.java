package com.fkhwl.scf.third.resp.logink;

import java.io.*;

public class DrivingLicenceInformationBean implements Serializable {
    private static final long serialVersionUID = 3631617946059101656L;
            /**
             * VehicleNumber : 晋M86897
             * VehicleBrandModel : 大运牌
             * VehicleEngineNumber : 1613F102156
             * VehicleMaximumTractionWeight : 37 吨
             * Photo :
             */
            private String VehicleNumber;
            private String VehicleBrandModel;
            private String VehicleEngineNumber;
            private String VehicleMaximumTractionWeight;
            private String Photo;
            private String VehicleFrameNumber;

            public String getVehicleNumber() {
                return VehicleNumber;
            }

            public void setVehicleNumber(String VehicleNumber) {
                this.VehicleNumber = VehicleNumber;
            }

            public String getVehicleBrandModel() {
                return VehicleBrandModel;
            }

            public void setVehicleBrandModel(String VehicleBrandModel) {
                this.VehicleBrandModel = VehicleBrandModel;
            }

            public String getVehicleEngineNumber() {
                return VehicleEngineNumber;
            }

            public void setVehicleEngineNumber(String VehicleEngineNumber) {
                this.VehicleEngineNumber = VehicleEngineNumber;
            }

            public String getVehicleMaximumTractionWeight() {
                return VehicleMaximumTractionWeight;
            }

            public void setVehicleMaximumTractionWeight(String VehicleMaximumTractionWeight) {
                this.VehicleMaximumTractionWeight = VehicleMaximumTractionWeight;
            }

            public String getPhoto() {
                return Photo;
            }

            public void setPhoto(String Photo) {
                this.Photo = Photo;
            }

            public String getVehicleFrameNumber() {
                return VehicleFrameNumber;
            }

            public void setVehicleFrameNumber(String vehicleFrameNumber) {
                VehicleFrameNumber = vehicleFrameNumber;
    }
}
