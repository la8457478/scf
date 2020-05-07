package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.Project;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.scf.service.ProjectRepositoryService;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.service.ScfSchedulerService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.scf.service.WaybillRepositoryService;
import com.fkhwl.scf.service.WaybillService;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.Tools;
import com.fkhwl.starter.mybatis.support.Condition;
import com.fkhwl.starter.mybatis.support.Query;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 定时任务
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/4/10
 */
@Service
@AllArgsConstructor
@Slf4j
public class ScfScheduleServiceImpl implements ScfSchedulerService {
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private WaybillRepositoryService waybillRepositoryService;
    @Autowired
    private ProjectRepositoryService projectRepositoryService;
    @Autowired
    private CounterpartyService counterpartyService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void generateSubitemClaimsOrder() {
        log.error("schedule generateSubjectClaimsOrder start");
        List<Project> projectList = projectRepositoryService.list();
        projectList.stream().forEach(project -> {
            Map<String, Object> map = new HashMap<>();
            map.put("isTransferred", false);
            map.put("page", 1);
            map.put("projectId", project.getId());
            map.put("limit", Integer.MAX_VALUE);
//            IPage<WaybillDTO> waybills = waybillService.listPage(Condition.getPage(new Query(1, Integer.MAX_VALUE)), map);and(Wrapper->Wrapper.eq(Waybill::getProjectId,project.getId())).and
            List<Waybill> waybills = waybillRepositoryService.list(new LambdaQueryWrapper<Waybill>().eq(Waybill::getProjectId,project.getId()).ne(Waybill::getWaybillStatus,-2).and(Wrapper->Wrapper.isNull(Waybill::getSubjectClaimsOrderId).or().eq(Waybill::getSubjectClaimsOrderId,0)));
            log.error("schedule generateSubjectClaimsOrder start projectId:{}",project.getId());
            if(waybills.size()!=0){
                String waybillIdList = waybills.stream().map(w -> w.getId().toString()).collect(Collectors.joining(","));
                Map<String, Object> params = new HashMap<>();
                params.put("waybillIds", Tools.removeReplaceLong(waybillIdList, Const.COMMA_CHAR));
                params.put("projectId", project.getId());
                Long ownerId = companyService.getDetail(counterpartyService.findInfo(project.getCounterpartyId()).getCompanyBorrowerId()).getOwnerId();
                params.put("ownerId", ownerId);
                params.put("userId", ownerId);
                params.put("isNewCreate",true);

                waybillService.generateSubjectClaimsOrder(params);
                log.error("schedule generateSubjectClaimsOrder success projectId:{},waybillCount:{}",project.getId(),waybills.size());
            }else{
                log.error("schedule generateSubjectClaimsOrder fail projectId:{},reason:{}",project.getId(),"in condition waybills not exist ");
            }
        });
        log.error("schedule generateSubjectClaimsOrder success");
        //        waybillService.generateSubjectClaimsOrder();
    }
}
