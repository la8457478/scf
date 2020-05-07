package com.fkhwl.scf.entity.po;

import com.fkhwl.starter.common.base.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 部门表 实体类  </p>
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
public class Department extends BasePO<Long, Department> {

    public static final String OWNER_ID = "owner_id";
    public static final String DEPT_NAME = "dept_name";
    public static final String DEPT_TITLE = "dept_title";
    public static final String PARENT_ID = "parent_id";
    private static final long serialVersionUID = 1L;

    /** 所属企业主账号id或者0(系统) */
    private Long ownerId;
    /** 部门名称 */
    private String deptName;
    /** 部门层级名称 */
    private String deptTitle;
    /** 父级id */
    private Long parentId;

}
