package com.fkhwl.scf.third.resp.logink;

import java.io.*;

/**
 * <p>Title: com.fkhwl.core.thried.test</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2017年11月24日 12点08分
 * updatetime:
 * reason:
 */
public class BizContent implements Serializable {
    private static final long serialVersionUID = 1755378774662773883L;

    //    {
    //        "Header": {
    //        "MessageReferenceNumber": "",
    //            "DocumentName": "",
    //            "DocumentVersionNumber": "",
    //            "SenderCode": "",
    //            "MessageSendingDateTime": ""
    //    },
    //        "Body": {
    //        "VehicleNumber": "吉A6D720",
    //            "LicensePlateTypeCode": "黄色",
    //            "Owner": "崔小梅",
    //            "DrivingLicenceInformation": {
    //            "VehicleNumber": "吉A6D720",
    //                "VehicleBrandModel": "解放",
    //                "VehicleEngineNumber": "10000997",
    //                "VehicleMaximumTractionWeight": "16 吨",
    //                "Photo": ""
    //        },
    //        "RoadTransportCertificateInformation": {
    //            # 道路运输许可证
    //            "RoadTransportCertificateNumber": "220122201158",
    //            # 车牌号
    //            "VehicleNumber": "吉A6D720",
    //            # 车型
    //            "VehicleClassification": "重型普通货车",
    //            # 车长
    //            "VehicleLength": "9000 mm",
    //            # 车宽
    //            "VehicleWidth": "2494 mm",
    //            # 车高
    //            "VehicleHeight": "2805 mm",
    //            # 运输类型
    //            "BusinessScope": "道路普通货物运输",
    //            # 车辆收集时间
    //            "LicenseInitialCollectionDate": "2014-10-17",
    //            # 驾驶证颁发日期
    //            "PeriodStartDate": "2014-10-17",
    //            # 驾驶证有效时间
    //            "PeriodEndDate": "2017-10-16",
    //            # 车管所
    //            "CertificationUnit": "农安县运输管理所",
    //                "Photo": "",
    //            # 状态
    //            "BusinessState": "注销",
    //                "BusinessStateCode": "80"
    //        }
    //    }
    //    }


    private HeaderBean Header;
    private BodyBean Body;

    public HeaderBean getHeader() {
        return Header;
    }

    public void setHeader(HeaderBean Header) {
        this.Header = Header;
    }

    public BodyBean getBody() {
        return Body;
    }

    public void setBody(BodyBean Body) {
        this.Body = Body;
    }
}
