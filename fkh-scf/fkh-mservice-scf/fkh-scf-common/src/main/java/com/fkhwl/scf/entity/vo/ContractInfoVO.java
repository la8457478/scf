package com.fkhwl.scf.entity.vo;

import java.io.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>Title: com.fkhwl.fkhserver.domain.basic.pojo</p>
 * <p>Company: 成都返空汇网络技术有限公</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: ${TODO}</p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2018年08月09日 16点02分
 * updatetime:
 * reason:
 */
@Data
public class ContractInfoVO implements Serializable {
    @ApiModelProperty(value = "地址")
    private String path;
    @ApiModelProperty(value = "文件名")
    private String name;
}
