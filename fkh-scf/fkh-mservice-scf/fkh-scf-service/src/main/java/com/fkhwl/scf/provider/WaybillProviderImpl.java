package com.fkhwl.scf.provider;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.WaybillProvider;
import com.fkhwl.scf.entity.dto.CompanyDTO;
import com.fkhwl.scf.entity.dto.ProgramDTO;
import com.fkhwl.scf.entity.dto.ProjectDTO;
import com.fkhwl.scf.entity.dto.PushWaybillDTO;
import com.fkhwl.scf.entity.dto.ScfUserConfigDTO;
import com.fkhwl.scf.entity.dto.WaybillDTO;
import com.fkhwl.scf.entity.dto.WaybillValidateDTO;
import com.fkhwl.scf.entity.po.Counterparty;
import com.fkhwl.scf.enums.ScfConfigEnum;
import com.fkhwl.scf.redis.RedisCachedBaseService;
import com.fkhwl.scf.service.CompanyService;
import com.fkhwl.scf.service.CounterpartyService;
import com.fkhwl.scf.service.ProgramService;
import com.fkhwl.scf.service.ProjectService;
import com.fkhwl.scf.service.ScfConfigService;
import com.fkhwl.scf.service.ScfUserConfigService;
import com.fkhwl.scf.service.WaybillOperationHistoryService;
import com.fkhwl.scf.service.WaybillService;
import com.fkhwl.scf.service.WaybillValidateService;
import com.fkhwl.scf.third.service.CfcaService;
import com.fkhwl.scf.third.service.LoginkService;
import com.fkhwl.scf.utils.CacheKeyScf;
import com.fkhwl.scf.utils.ToolsHelper;
import com.fkhwl.starter.core.support.AssertUtils;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 运单Dubbo接口</p>
 *
 * @author chenli
 * @version 1.0.0
 * @email "mailto:chenli@fkhwl.com" @fkhwl.com
 * @date 2020.01.10 19:24
 */
@Service
@AllArgsConstructor
@Slf4j
public class WaybillProviderImpl implements WaybillProvider {

    private final WaybillService waybillService;
    private final ProjectService projectService;
    private final ProgramService programService;
    private final ScfConfigService scfConfigService;
    private final ScfUserConfigService scfUserConfigService;
    private final CfcaService cfcaService;
    private final LoginkService loginkService;
    private final CounterpartyService counterpartyService;
    private final CompanyService companyService;
    private final WaybillOperationHistoryService waybillOperationHistoryService;
    private final WaybillValidateService waybillValidateService;
    private final RedisCachedBaseService redisCachedBaseService;

    @Override
    public IPage<WaybillDTO> listPage(Map<String, Object> params) {
        return waybillService.listPage(Condition.getPage(params), params);
    }

    @Override
    public IPage<WaybillDTO> listPage(IPage<WaybillDTO> page,Map<String, Object> params) {
        return waybillService.listPage(page, params);
    }
    @Override
    public WaybillDTO getDetail(Long id) {
        return waybillService.getDetail(id).orElse(null);
    }

    @Override
    public Long saveOrUpdate(WaybillDTO waybillDTO) {
        return waybillService.saveOrUpdate(waybillDTO);
    }

    @Override
    public void delete(Long id) {
        waybillService.delete(id);
    }

    @Override
    public void pushWaybill(PushWaybillDTO dto) {
        WaybillDTO waybillDTO= dto.getWaybillDTO();
        ProjectDTO projectDTO=dto.getProjectDTO();
        log.error("WaybillProviderImpl pushWaybill:{},counterpartyName:{}",waybillDTO.getWaybillNo(),projectDTO.getCounterpartyName());

        boolean isLocked=false;
        String key=CacheKeyScf.getPushWaybillLockKey(waybillDTO.getWaybillNo());
        try {
            isLocked= redisCachedBaseService.tryGetDistributedLock(key,waybillDTO.getWaybillNo(),CacheKeyScf.LOCK_TIME);
            log.error("pushWaybill locked:{},{}",waybillDTO.getWaybillNo(),isLocked);
            AssertUtils.isTrue(isLocked,"运单处理中");
        Counterparty counterparty= counterpartyService.findByName(projectDTO.getCounterpartyName());
        if(counterparty!=null){
            //存入关联的交易对手
            projectDTO.setCounterpartyId(counterparty.getId());
            projectDTO.setCounterpartyName(counterparty.getCounterpartyName());
            projectDTO.setCompanyId(counterparty.getCompanyBorrowerId());
        }else{
            log.error("not find counterparty ");
            throw new IllegalArgumentException("交易对手: " + projectDTO.getCounterpartyName() + "不存在!");
        }
        projectService.saveOrUpdate(projectDTO);
        ProgramDTO programDTO=dto.getProgramDTO();
        programDTO.setProjectId(projectDTO.getId());
        programService.saveOrUpdate(programDTO);
        waybillDTO.setProgramId(programDTO.getId());
        waybillDTO.setProjectId(projectDTO.getId());

        WaybillValidateDTO waybillValidateDTO=new WaybillValidateDTO();
        BeanUtils.copyProperties(waybillDTO,waybillValidateDTO);
        CompanyDTO company=companyService.getDetail(counterparty.getCompanyCapitalId());
        Map<String,Integer> resultList=this.validateWaybillCredit(company.getOwnerId(),waybillValidateDTO);
        waybillValidateDTO.setWaybillStatus(resultList.get("IS_OK"));
        waybillValidateDTO.setWaybillValidate(JSONObject.toJSONString(resultList));
        if(0==waybillValidateDTO.getWaybillStatus()){
            waybillDTO.setWaybillStatus(0);
            //默认生成存证
            String[] cfcaPdf=cfcaService.server1101(waybillValidateDTO);
            AssertUtils.isTrue(cfcaPdf!=null,"cfca存证失败");
            waybillDTO.setCfcaNo(cfcaPdf[0]);
            waybillDTO.setCfcaPdfPath(cfcaPdf[1]);
            waybillValidateDTO.setCfcaPdfPath(cfcaPdf[1]);
        }else{
            waybillDTO.setWaybillStatus(-2);
        }
        waybillValidateService.save(waybillValidateDTO);
        Long waybillId = this.saveOrUpdate(waybillDTO);
        waybillOperationHistoryService.saveBatch(waybillId, dto.getOperationHistoryDTOList());

        } catch (Exception e) {
            log.error("Bad things", e);
            throw e;
        }finally {
            if(isLocked){
                redisCachedBaseService.del(key);
            }
        }
    }

    @Override
    public void generateSubjectClaimsOrder(Map<String, Object> params) {
        params.put("isNewCreate",true);
        waybillService.generateSubjectClaimsOrder(params);
    }

    /**
     * 验证运单的各项用信规则
     * @param userId 资方登录用户ID
     * @param waybillValidateDTO
     * @return 状态 1有效 0无效
     */
    public Map<String,Integer> validateWaybillCredit(Long userId,WaybillValidateDTO waybillValidateDTO){
        Map<String,Integer> resultList=this.validateWaybillCreditReturnList( userId, waybillValidateDTO);
        boolean isRight=true;
        for (Integer value : resultList.values()) {
            if (value==0){
                isRight= false;
                break;
            }
        }
        resultList.put("IS_OK",isRight?0:-2);
        return resultList;
    }
    /**
     * 验证运单的各项用信规则
     * @param userId 资方登录用户ID
     * @return map:key-value，，返回各规则对应结果状态 1有效 0无效
     */
    public Map<String,Integer> validateWaybillCreditReturnList(Long userId,WaybillValidateDTO waybillValidateDTO){
        if(waybillValidateDTO==null){
            return null;
        }
        final int OPENING=1;//1为开启状态
        //查询该资方用户设置的用信规则。
        List<ScfUserConfigDTO> scfConfigList=scfUserConfigService.getUserConfigsByConfigIdList(ScfConfigEnum.CREDIT_REGULATION_CHECK_LIST.getCacheKey(),userId);
        Map<String,Integer > openStatusMap=new HashMap<>();
        for (ScfUserConfigDTO record : scfConfigList) {
            openStatusMap.put(record.getConfigKey(),record.getConfigStatus());
        };
//        String licensePlateNo="渝D3219";
//        String li=waybillValidateDTO.getLicensePlateNo();
//        //车辆是否入网正常
//        boolean isNetInOk=loginkService.checkPlatformNetInIsOk(licensePlateNo);
//
//        //查询车辆信息，，用于检查车辆各信息的匹配情况
//        BizContent bizContent=loginkService.getPlatformCarInfo(licensePlateNo);
//        //# 道路运输许可证 ,是否匹配
//        String roadTransportCertificateNumber=waybillValidateDTO.getLicensePlateNo();
//        if(roadTransportCertificateNumber!=null && bizContent!=null && roadTransportCertificateNumber.equals( bizContent.getBody().getRoadTransportCertificateInformation().getRoadTransportCertificateNumber())){
//
//        }

        Map<String,Integer > resultStatusMap=new HashMap<>();
        //            /**是否验证实际承运人*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.TRANSPORTER_CREDIT_INVESTIGATION_CHECK.getCacheKey())){
            int isOk=1;
            if(StringUtils.isBlank(waybillValidateDTO.getDriverName())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getDriverMobileNo())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getLicenceoPicture())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.TRANSPORTER_CREDIT_INVESTIGATION_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证实际承运车辆*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.TRANSPORTER_VEHICLE_CHECK.getCacheKey())){
            int isOk=1;
            if(StringUtils.isBlank(waybillValidateDTO.getLicensePlateNo())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getVehicleOperatingNo())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.TRANSPORTER_VEHICLE_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证运单发货方*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_SEND_CHECK.getCacheKey())){
            int isOk=1;
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardNoSend())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardPictureSend())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardPictureBackSend())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getBusinessLicenseNoSend())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getLegalPersonSend())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getBusinessLicensePictureSend())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_SEND_CHECK.getCacheKey(),isOk);
        }

        //            /**是否验证运单收货方*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_CONSIGNEE_CHECK.getCacheKey())){

            int isOk=1;
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardNoConsignee())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardPictureConsignee())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getIdCardPictureBackConsignee())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getBusinessLicenseNoConsignee())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getLegalPersonConsignee())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getBusinessLicensePictureConsignee())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_CONSIGNEE_CHECK.getCacheKey(),isOk);
        }

        //            /**是否验证运单起止点*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_LOCATION_CHECK.getCacheKey())){
            int isOk=1;

            if(StringUtils.isBlank(waybillValidateDTO.getLoadLocation())){ isOk = 0;}
            if(StringUtils.isBlank(waybillValidateDTO.getArrivalLocation())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_LOCATION_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证运单运输轨迹*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_TRACK_CHECK.getCacheKey())){

            int isOk=1;
            if(waybillValidateDTO.getGpsCount()==null){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_TRACK_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证运单时间节点*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_TIME_CHECK.getCacheKey())){

            int isOk=1;
            if(waybillValidateDTO.getWaybillCreateTime()==null || waybillValidateDTO.getWaybillCreateTime().compareTo(ToolsHelper.formatStr2Date("2000-01-01"))<0){ isOk = 0;}
            if(waybillValidateDTO.getLoadingTime()==null || waybillValidateDTO.getLoadingTime().compareTo(ToolsHelper.formatStr2Date("2000-01-01"))<0){ isOk = 0;}
            if(waybillValidateDTO.getReceiveTime()==null || waybillValidateDTO.getReceiveTime().compareTo(ToolsHelper.formatStr2Date("2000-01-01"))<0){ isOk = 0;}
            if(waybillValidateDTO.getBillPassTime()==null || waybillValidateDTO.getBillPassTime().compareTo(ToolsHelper.formatStr2Date("2000-01-01"))<0){ isOk = 0;}
//            if(waybillValidateDTO.getPayFreightTime()==null){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_TIME_CHECK.getCacheKey(),isOk);
        }

        //            /**是否验证运单单据数据*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_DATA_CHECK.getCacheKey())){

            int isOk=1;
            if(waybillValidateDTO.getSendGrossWeight()==null){ isOk = 0;}
            if(waybillValidateDTO.getSendTareWeight()==null){ isOk = 0;}
            if(waybillValidateDTO.getSendNetWeight()==null){ isOk = 0;}
            if(waybillValidateDTO.getReceiveGrossWeight()==null){ isOk = 0;}
            if(waybillValidateDTO.getReceiveTareWeight()==null){ isOk = 0;}
            if(waybillValidateDTO.getReceiveNetWeight()==null){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_DATA_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证运单合同*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_CONTRACT_CHECK.getCacheKey())){

            int isOk=1;
            if(StringUtils.isBlank(waybillValidateDTO.getPdfPath())){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_CONTRACT_CHECK.getCacheKey(),isOk);
        }
        //            /**是否验证运价信息*/
        if(OPENING==openStatusMap.get(ScfConfigEnum.WAYBILL_PRICE_CHECK.getCacheKey())){

            int isOk=1;
            if(waybillValidateDTO.getUnitPrice()==null){ isOk = 0;}
            if(waybillValidateDTO.getValuePrice()==null){ isOk = 0;}
            if(waybillValidateDTO.getTotalPrice()==null){ isOk = 0;}
            if(waybillValidateDTO.getIncomePrice()==null){ isOk = 0;}
            if(waybillValidateDTO.getInvoiceMoney()==null){ isOk = 0;}
            resultStatusMap.put(ScfConfigEnum.WAYBILL_PRICE_CHECK.getCacheKey(),isOk);
        }
        return resultStatusMap;
    }

    @Override
    public void updateByParams(Map<String, Object> params) {
        waybillService.updateByParams(params);
    }


    @Override
    public Map<String, Object> getReStartCreditApplyData(Long creditApplyId, Long counterpartyId) {
        return waybillService.getReStartCreditApplyData(creditApplyId, counterpartyId);
    }

    @Override
    public List<Long> listIdsByParams(Map<String, Object> params) {
        return waybillService.listIdsByParams(params);
    }
}
