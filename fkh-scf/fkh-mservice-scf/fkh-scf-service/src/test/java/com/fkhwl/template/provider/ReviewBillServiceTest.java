package com.fkhwl.template.provider;

import com.fkhwl.scf.entity.po.CompanyContract;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.service.CompanyContractService;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.scf.service.CreditApplyService;
import com.fkhwl.scf.third.service.impl.CfcaServiceImpl;
import com.fkhwl.starter.test.FkhBootTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

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
@FkhBootTest
public class ReviewBillServiceTest {
    @Autowired
    private CreditApplyService service;
    @Autowired
    private CreditApplyRepositoryService repositoryService;
    @Autowired
    private CounterpartyRepositoryService counterpartyRepositoryService;
    @Autowired
    private CompanyContractService companyContractRepositoryService;
    @Test
    public void test1() throws IOException {
        CreditApply creditApply = repositoryService.getById(2L);
        Counterparty counterparty = counterpartyRepositoryService.getById(creditApply.getCounterpartyId());
        CompanyContract companyContract = companyContractRepositoryService.getByBorrowCompanyId(counterparty.getCompanyBorrowerId());
    }
    @Test
    public void test2(){
//        //有效的运单，进行存证操作
        CfcaServiceImpl cfcaService=new CfcaServiceImpl();
        String response=cfcaService.server1103("20200328115911934873826025054582");
        System.out.println(response);
    }
}
