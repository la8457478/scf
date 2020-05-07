package com.fkhwl.template.provider;

import com.fkhwl.scf.entity.dto.WaybillValidateDTO;
import com.fkhwl.scf.third.service.impl.CfcaServiceImpl;
import com.fkhwl.scf.utils.ToolsHelper;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>Title: com.fkhwl.template.provider</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年03月05日 17点17分
 * updatetime:
 * reason:
 */
@Slf4j
public class CfcaServiceTest  {

    @Test
    public void test1(){
        CfcaServiceImpl cfcaService=new CfcaServiceImpl();
        WaybillValidateDTO waybillDTO=new WaybillValidateDTO();
        waybillDTO.setDriverName("李浩鸣专用");
        waybillDTO.setWaybillNo("20200310113027297");
        waybillDTO.setBillPassTime(ToolsHelper.formatStr2DateTime("2020-03-10 11:36:37"));
        cfcaService.server1101(waybillDTO);
    }
    @Test
    public void test2(){
//        //有效的运单，进行存证操作
        CfcaServiceImpl cfcaService=new CfcaServiceImpl();
        String response=cfcaService.server1103("20200328115911934873826025054582");
        System.out.println(response);
    }
}
