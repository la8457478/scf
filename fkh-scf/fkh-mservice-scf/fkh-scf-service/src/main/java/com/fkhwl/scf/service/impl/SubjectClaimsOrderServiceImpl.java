package com.fkhwl.scf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.entity.po.SubjectClaimsOrder;
import com.fkhwl.scf.entity.po.Waybill;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.scf.service.CounterpartyRepositoryService;
import com.fkhwl.scf.service.SubjectClaimsOrderRepositoryService;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.scf.service.WaybillRepositoryService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.SubjectClaimsOrderServiceConverterWrapper;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;
import com.fkhwl.starter.core.util.StringUtils;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
@Service
@AllArgsConstructor
public class SubjectClaimsOrderServiceImpl implements SubjectClaimsOrderService {
    private final SubjectClaimsOrderRepositoryService subjectClaimsOrderRepositoryService;

    private final WaybillRepositoryService waybillRepositoryService;

    private final CounterpartyRepositoryService counterpartyRepositoryService;
    @Override
    public IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page, Map<String, Object> params) {
//        if (!StringUtils.isEmpty(params.get("counterpartyId"))) {
//            IPage<SubjectClaimsOrderDTO> subjectClaimsOrderIPage = subjectClaimsOrderRepositoryService.listPage(page, params);
//            Counterparty counterparty = counterpartyRepositoryService.getById(Long.valueOf(params.get("counterpartyId").toString()));
//            List<SubjectClaimsOrder> subjectClaimsOrders = new ArrayList<>();
//            subjectClaimsOrderIPage.getRecords().stream().forEach(subjectClaimsOrderDTO -> {
//                if(!subjectClaimsOrderDTO.getReviewStatus().equals(1)){
//                    subjectClaimsOrderDTO.setCanApplyBalance(subjectClaimsOrderDTO.getTransferBalance().multiply(counterparty.getRuleRatio()).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP));
//                    SubjectClaimsOrder subjectClaimsOrder = new SubjectClaimsOrder();
//                    subjectClaimsOrder.setId(subjectClaimsOrderDTO.getId());
//                    subjectClaimsOrder.setCanApplyBalance(subjectClaimsOrderDTO.getCanApplyBalance());
//                    subjectClaimsOrders.add(subjectClaimsOrder);
//                }
//            });
//            if(!subjectClaimsOrders.isEmpty()){
//                subjectClaimsOrderRepositoryService.saveIgnoreBatch()
//            }
//            return subjectClaimsOrderIPage;
//        } else {
            return subjectClaimsOrderRepositoryService.listPage(page, params);
//        }
    }

    @Override
    public SubjectClaimsOrderDTO getDetail(Long id) {
        return SubjectClaimsOrderServiceConverterWrapper.INSTANCE.dto(subjectClaimsOrderRepositoryService.getById(id));
    }

    @Override
    public void saveOrUpdate(SubjectClaimsOrderDTO departmentDTO) {
        Date currentDate = new Date();
        SubjectClaimsOrder department = SubjectClaimsOrderServiceConverterWrapper.INSTANCE.po(departmentDTO);
        department.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(departmentDTO.getId())) {
            department.setCreateTime(currentDate);
        }
        subjectClaimsOrderRepositoryService.saveOrUpdate(department);
    }

    @Override
    public void delete(Long id) {
        //逻辑删除部门
        subjectClaimsOrderRepositoryService.removeById(id);
        //todo add-by chenli 是否逻辑删除对应的“用户-部门”中间表
    }

    @Override
    public List<ReviewPageVo> reviewPage(Map<String, Object> params) {
       return subjectClaimsOrderRepositoryService.reviewPage(params);
    }

    @Override
    public void saveBatch(Collection<SubjectClaimsOrder> list) {
        subjectClaimsOrderRepositoryService.saveBatch(list);
    }

    @Override
    public Map<String, Object> calculate(String subjectClaimsOrderIds, Long counterpartyId) {
      List<Waybill> waybills =   waybillRepositoryService.list(new LambdaQueryWrapper<Waybill>().in(Waybill::getSubjectClaimsOrderId,subjectClaimsOrderIds.split(",")).orderByAsc(Waybill::getBillPassTime));
        AssertUtils.isTrue(!waybills.isEmpty(),"未查询到运单");
        Waybill firstWaybill = waybills.get(0);
        Counterparty counterparty =  counterpartyRepositoryService.getById(counterpartyId);
        Integer paymentDays=  counterparty.getPaymentDays();
        //waybillDate  第一个运单日期
        Date waybillDate = firstWaybill.getBillPassTime();
       Date  paymentDate = DateUtils.plusDays(waybillDate,paymentDays);
       Map<String,Object> map = new HashMap<>();
       //融资比例
        map.put("interestRate",counterparty.getInterestRate());
        map.put("dueDate",paymentDate);
        map.put("manageRate",counterparty.getManageRate());

        return map;
    }

    @Override
    public void save(SubjectClaimsOrder subjectClaimsOrder) {
        subjectClaimsOrderRepositoryService .save(subjectClaimsOrder);
    }
}
