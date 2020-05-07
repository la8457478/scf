/**
 *
 */

package com.fkhwl.scf.web.config;


import com.fkhwl.scf.utils.ToolsHelper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <p>Title: 系统配置</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright  2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: suj
 * emali: suj@fkhwl.com
 * version: 1.0
 * date: 2020-03-05
 * updatetime:
 * reason:
 */
@Data
@Component
public class SystemConfig {
    /***************************************************UPLOAD CONFIG**********************************************/
    /** uploadPath + file/contract/ */
    private static final String projectContractPath = "file/contract/";
    /** uploadPath + thumbnail/thumbnail_ */
    private static final String thumbnailImgPath = "thumbnail/";
    /** uploadPath + images/company */
    private static final String companyImgPath = "images/company/";

    /** scf 存证PDF文件存放位置 */
    private static final String cfcaContractPath = "file/contract/cfca/";
    private static final String reviewBillPath = "file/excel/review/";
    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.url}")
    private String imageBaseUrl;
    /***************************************************UPLOAD CONFIG**********************************************/

    public String getThumbnailImgUrl() {
        return imageBaseUrl + thumbnailImgPath + this.getDateDirName() + "thumbnail_";
    }

    public String getThumbnailImgPath() {
        return uploadPath + thumbnailImgPath + this.getDateDirName();
    }

    public String getProjectContractUrl() {
        return imageBaseUrl + projectContractPath + this.getDateDirName();
    }
    public String getReviewBillUrl() {
        return imageBaseUrl + reviewBillPath + this.getDateDirName();
    }
    public String getProjectContractPath() {
        return uploadPath + projectContractPath + this.getDateDirName();
    }
    public String getReviewBillPath() {
        return uploadPath + reviewBillPath + this.getDateDirName();
    }

    public String getCompanyImgUrl() {
        return imageBaseUrl + companyImgPath + this.getDateDirName();
    }

    public String getCompanyImgPath() {
        return uploadPath + companyImgPath + this.getDateDirName();
    }

    public String getCfcaContractUrl() {
        return imageBaseUrl + cfcaContractPath + this.getDateDirName();
    }
    public String getCfcaContractPath() {
        return uploadPath + cfcaContractPath + this.getDateDirName();
    }

    /**
     * 日期格式的目录（目录名：20150201）
     * @return
     */
    private String getDateDirName(){
        return  ToolsHelper.formatDate2Str(ToolsHelper.getCurrentDate())+"/";
    }

}
