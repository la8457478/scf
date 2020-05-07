package com.fkhwl.scf.provider;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.SubjectClaimsOrderProvider;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.vo.ReviewPageVo;
import com.fkhwl.scf.service.SubjectClaimsOrderService;
import com.fkhwl.starter.mybatis.support.Condition;

import org.apache.dubbo.config.annotation.Service;

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
public class SubjectClaimsOrderProviderImpl implements SubjectClaimsOrderProvider {

    private final SubjectClaimsOrderService subjectClaimsOrderService;

    /**
     *
     * @param params
     * @return
     */
    @Override
    public IPage<SubjectClaimsOrderDTO> listPage(Map<String, Object> params) {
        return subjectClaimsOrderService.listPage(Condition.getPage(params), params);
    }

    @Override
    public IPage<SubjectClaimsOrderDTO> listPage(IPage<SubjectClaimsOrderDTO> page,Map<String, Object> params) {
        return subjectClaimsOrderService.listPage(page, params);
    }
    @Override
    public SubjectClaimsOrderDTO getDetail(Long id) {
        return subjectClaimsOrderService.getDetail(id);
    }

    @Override
    public void saveOrUpdate(SubjectClaimsOrderDTO subjectClaimsOrderDTO) {
         subjectClaimsOrderService.saveOrUpdate(subjectClaimsOrderDTO);
    }

    @Override
    public void delete(Long id) {
         subjectClaimsOrderService.delete(id);
    }

    @Override
    public List<ReviewPageVo> reviewPage(Map<String, Object> params) {
       return  subjectClaimsOrderService.reviewPage(params);
    }

    @Override
    public Map<String,Object> calculate(String subjectClaimsOrderIds, Long counterpartyId) {
        return subjectClaimsOrderService.calculate(subjectClaimsOrderIds,counterpartyId);
    }

}
