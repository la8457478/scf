package com.fkhwl.scf.service.impl;

import com.fkhwl.scf.entity.dto.WaybillCheckRecordDTO;
import com.fkhwl.scf.entity.po.CreditApply;
import com.fkhwl.scf.entity.po.WaybillCheckRecord;
import com.fkhwl.scf.service.CreditApplyRepositoryService;
import com.fkhwl.scf.service.WaybillCheckRecordRepositoryService;
import com.fkhwl.scf.service.WaybillCheckRecordService;
import com.fkhwl.scf.validate.BaseValidate;
import com.fkhwl.scf.wrapper.WaybillCheckRecordServiceConverterWrapper;
import com.fkhwl.starter.common.enums.DeleteEnum;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.core.util.DateUtils;

import org.springframework.stereotype.Service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 权限表 服务接口实现类 </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Slf4j
@Service
@AllArgsConstructor
public class WaybillCheckRecordServiceImpl implements WaybillCheckRecordService {

    private final WaybillCheckRecordRepositoryService waybillCheckRecordRepositoryService;

    private final CreditApplyRepositoryService creditApplyRepositoryService;

    @Override
    public void save(WaybillCheckRecordDTO waybillCheckRecordDTO) {
//        WaybillCheckRecordDTO dbWaybillCheckRecordDTO = waybillCheckRecordRepositoryService.getByWaybillId(waybillCheckRecordDTO.getWaybillId());
//        if (dbWaybillCheckRecordDTO != null) {
//            waybillCheckRecordDTO.setId(dbWaybillCheckRecordDTO.getId());
//        }
        Date currentDate = DateUtils.now();
        WaybillCheckRecord waybillCheckRecord = WaybillCheckRecordServiceConverterWrapper.INSTANCE.po(waybillCheckRecordDTO);
        waybillCheckRecord.setUpdateTime(currentDate);
        if (BaseValidate.errorLong(waybillCheckRecord.getId())) {
            waybillCheckRecord.setDeleted(DeleteEnum.N);
            waybillCheckRecord.setCreateTime(currentDate);
        }
        waybillCheckRecordRepositoryService.saveOrUpdate(waybillCheckRecord);

        //更新用款申请"已查阅运单数量"
        CreditApply creditApply = creditApplyRepositoryService.getById(waybillCheckRecordDTO.getCreditApplyId());
        AssertUtils.notNull(creditApply, "用款申请数据不存在");
        creditApply.setCheckedWaybillCount(waybillCheckRecordRepositoryService.countByCreditApplyId(waybillCheckRecordDTO.getCreditApplyId()));
        creditApplyRepositoryService.updateById(creditApply);
    }
}
