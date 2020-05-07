package com.fkhwl.scf.third.resp.logink;

import java.io.*;

import lombok.Data;

/**
 * <p>Title: com.fkhwl.fkhserver.domain.logink.entity</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: dong4j
 * emali: dongshijie@fkhwl.com
 * version: 1.0
 * date: 2018年05月04日 17点28分
 * updatetime:
 * reason:
 */
@Data
public class FundStatementDataWrap implements Serializable {
    private FundStatementData data;
    private String licNo;
    private String fromSite;
    private String sendSite;
}
