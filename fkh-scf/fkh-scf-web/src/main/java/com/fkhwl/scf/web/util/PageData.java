package com.fkhwl.scf.web.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>Title: com.fkhwl.scf.web.util</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年04月10日 17点29分
 * updatetime:
 * reason:
 */
@Data
@EqualsAndHashCode(callSuper=true)
public class PageData<T> extends Page<T> implements IPageData<T> {

    private Object data;
}
