package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 用户部门中间表 实体类  </p>
 *
 * @author ASpiralMoon
 * @email ASpiralMoon@fkhwl.com
 * @since 2020-02-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ScfUserDepartment extends BasePO<Long, ScfUserDepartment> {

    public static final String USER_ID = "user_id";
    public static final String DEPT_ID = "dept_id";
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;
    /** 部门id */
    private Long deptId;

}
