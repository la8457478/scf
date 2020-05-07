/**
 *
 */
package com.fkhwl.scf.enums;

import java.io.*;

/**
 * <p>Title: ScfConfigEnum</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020-03-05
 * updatetime:
 * reason:
 */
public enum ScfConfigEnum implements Serializable{

	/**用信规则列表*/
    CREDIT_REGULATION_CHECK_LIST(0, "CREDIT_REGULATION_CHECK_LIST"),

    /** 是否验证实际承运人*/
    TRANSPORTER_CREDIT_INVESTIGATION_CHECK(0, "TRANSPORTER_CREDIT_INVESTIGATION_CHECK"),
    /** 是否验证实际承运车辆*/
    TRANSPORTER_VEHICLE_CHECK(0, "TRANSPORTER_VEHICLE_CHECK"),
    /** 是否验证运单发货方*/
    WAYBILL_SEND_CHECK(0, "WAYBILL_SEND_CHECK"),
    /** 是否验证运单收货方*/
    WAYBILL_CONSIGNEE_CHECK(0, "WAYBILL_CONSIGNEE_CHECK"),
    /** 是否验证运单起止点*/
    WAYBILL_LOCATION_CHECK(0, "WAYBILL_LOCATION_CHECK"),
    /** 是否验证运单运输轨迹*/
    WAYBILL_TRACK_CHECK(0, "WAYBILL_TRACK_CHECK"),
    /** 是否验证运单时间节点*/
    WAYBILL_TIME_CHECK(0, "WAYBILL_TIME_CHECK"),

    /** 是否验证运单单据数据*/
    WAYBILL_DATA_CHECK(0, "WAYBILL_DATA_CHECK"),
    /** 是否验证运单合同*/
    WAYBILL_CONTRACT_CHECK(0, "WAYBILL_CONTRACT_CHECK"),
    /** 是否验证运价信息*/
    WAYBILL_PRICE_CHECK(0, "WAYBILL_PRICE_CHECK"),


	/**放款审核配置*/
    LOAN_REVIEW_CONFIG_CHECK(0,"LOAN_REVIEW_CONFIG_CHECK"),
    /**百度地图URL*/
    BAIDU_MAP_URL(0,"BAIDU_MAP_URL"),
    /**单条应收款转让运单包含的最大运单数量*/
    MAX_WAYBILL_COUNT_IN_SUBJECT_CLAIMS_ORDER(0,"MAX_WAYBILL_COUNT_IN_SUBJECT_CLAIMS_ORDER");

	private long id;
	private String cacheKey;

	ScfConfigEnum(long id, String cacheKey){
		this.id = id;
		this.cacheKey = cacheKey;
	}

	public long getId() {
		return id;
	}

	public String getCacheKey(){
		return this.cacheKey;
	}
}
