package com.fkhwl.scf.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fkhwl.starter.common.base.BasePO;
import com.fkhwl.starter.common.enums.DeleteEnum;

import java.io.*;
import java.util.Date;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/3/15
 */
public class ScfBasePO<T extends Serializable, M extends Model<M>  > extends BasePO<T,M> {

    @TableField(
        value = "owner_id",
        fill = FieldFill.INSERT_UPDATE
    )
    private Long ownerId;
    @TableField(
        value = "create_user_id",
        fill = FieldFill.INSERT_UPDATE
    )
    private Long createUserId;

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }
}
