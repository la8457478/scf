package com.fkhwl.scf;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fkhwl.scf.entity.dto.CreditApplyDTO;
import com.fkhwl.scf.entity.dto.CreditApplyListDTO;
import com.fkhwl.scf.entity.dto.CreditApplyReviewDTO;
import com.fkhwl.scf.entity.dto.ReviewRecordDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 审核记录dubbo接口 </p>
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/2/25
 */
public interface ReviewRecordProvider {


    /**
    * 审核意见
     * @param params
    * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.fkhwl.scf.entity.dto.CreditApplyListDTO>
    * @Author: liuan
    * @Date: 2020/3/16 14:16
    */
    List<ReviewRecordDTO> listReviewHistory(Map<String, Object> params);
}
