package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDetailDTO;
import com.fkhwl.scf.entity.dto.ReviewPageDTO;
import com.fkhwl.scf.entity.dto.SubjectClaimsOrderDTO;
import com.fkhwl.scf.entity.vo.ReviewPageVo;

import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  用信详情 审核列表 dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface CreditApplyDetailProvider {

    /**
     * 分页查询用信详情
     *
     * @param params 查询参数
     * @return 返回分页标的用信详情数据 page
     */
    IPage<CreditApplyDetailDTO> listPage(Map<String, Object> params);

    /**
     * 获取用信详情情
     *
     * @param id  用信详情id
     * @return 用信详情详情DTO optional
     */
    CreditApplyDetailDTO getDetail(Long id);

    /**
     * 新增和更新标的用信详情
     *
     * @param creditApplyDetailDTO 用信详情实体
     */
    void saveOrUpdate(CreditApplyDetailDTO creditApplyDetailDTO);

    /**
     * 删除用信详情
     *
     * @param id 标的用信详情id
     */
    void delete(Long id);

    /**
    * 审核页面
     * @param params
    * @return: java.util.List<com.fkhwl.scf.entity.vo.ReviewPageVo>
    * @Author: liuan
    * @Date: 2020/3/1 13:41
    */
    List<ReviewPageDTO> reviewPage(Map<String, Object> params);
}
