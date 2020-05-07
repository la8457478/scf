package com.fkhwl.scf.third.service;

import com.fkhwl.scf.entity.dto.WaybillValidateDTO;

/**
 * <p>Title: com.fkhwl.scf.third.service</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: CFCA数据保全</p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年03月05日 15点33分
 * updatetime:
 * reason:
 */
public interface CfcaService {

    /**
     * 存证服务
     * @param waybillValidateDTO
     * @return 0为单号，1为文件地址
     */
    String[] server1101(WaybillValidateDTO waybillValidateDTO);

    /**
     * 下载PDF存证文件
     * @param stubNo
     */
    String server1103(String stubNo);
}
