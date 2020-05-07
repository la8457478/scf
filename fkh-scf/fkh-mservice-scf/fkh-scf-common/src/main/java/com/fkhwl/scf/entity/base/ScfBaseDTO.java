package com.fkhwl.scf.entity.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fkhwl.starter.common.base.BaseDTO;
import com.fkhwl.starter.common.base.BasePO;

import java.io.*;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:   </p>
 * 根据业务需求添加字段
 *
 * @author liuan
 * @email liuan#fkhwl.com
 * @since 2020/3/15
 */
public class ScfBaseDTO<T extends Serializable> extends BaseDTO<T> {

    private Long ownerId;
    private Long createUserId;

    public Long getCreateUserId() {
        return createUserId;
    }

    protected ScfBaseDTO(final ScfBaseDTO.ScfBaseDTOBuilder<T, ?, ?> b) {
        super(b);
    }
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public Long getOwnerId() {
        return this.ownerId;
    }
    public ScfBaseDTO() {
    }
    public void setOwnerId(final Long ownerId) {
        this.ownerId = ownerId;
    }

    public abstract static class ScfBaseDTOBuilder<T extends Serializable, C extends ScfBaseDTO<T>, B extends ScfBaseDTO.ScfBaseDTOBuilder<T, C, B>> extends BaseDTOBuilder<T, C, B> {
        public ScfBaseDTOBuilder() {
        }

        protected abstract B self();

        public abstract C build();

        public String toString() {
            return "BaseDTO.BaseDTOBuilder(super=" + super.toString() + ")";
        }
    }

}
