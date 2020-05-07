package com.fkhwl.scf.web.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springframework.beans.BeanUtils;

/**
 * <p>Title: com.fkhwl.scf.web.util</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020年04月10日 17点28分
 * updatetime:
 * reason:
 */
public interface IPageData<T> extends IPage<T>  {

    Object getData();
    void setData(Object data);

    default IPageData<T> convertIPage(IPage<T> iPage){
        IPageData<T> iPageData=new PageData<T>();
        BeanUtils.copyProperties(iPage,iPageData);
        return iPageData;
    }
}
