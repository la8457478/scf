package com.fkhwl.scf.entity.po;

import com.fkhwl.scf.entity.base.ScfBasePO;
import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 审核记录 实体类  </p>
 *
 * @author 36424
 * @version 1.0.0
 * @email "mailto:36424@fkhwl.com"
 * @date 2020.03.02 12:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ReviewRecord extends ScfBasePO<Long, ReviewRecord> {

    public static final String REVIEW_RESULT = "review_result";
    public static final String REVIEW_REASON = "review_reason";
    public static final String CREDIT_APPLY_ID = "credit_apply_id";
    public static final String REVIEW_USER_ID = "review_user_id";
    private static final long serialVersionUID = 1L;

    /** 审核结果 */
    private Integer reviewResult;
    /** 审核意见 */
    private String reviewReason;
    /** 同意or拒绝 */
    private Boolean passStatus;

    /** 关联的审批业务id */
    private Long businessId;
    /** 流程id */
    private Long flowId;
    /** 节点id */
    private Long flowNodeId;
    /** 节点名称 */
    private String flowNodeName;


}
