/**
 *
 */

package com.fkhwl.scf.config;


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

    /** cfca 信息保全-存证PDF文件存放位置 */
    private static final String cfcaContractEvidencePath = "file/contract/cfca/evidence/";

    /** cfca 安心签-用款申请签章合同位置/ */
    private static final String cfcaContractAxQianPath = "file/contract/cfca/axqian/";

    @Value("${upload.path}")
    private String uploadPath;
    @Value("${upload.url}")
    private String imageBaseUrl;
    /***************************************************UPLOAD CONFIG**********************************************/

    public String getThumbnailImgUrl() {
        return imageBaseUrl + thumbnailImgPath + "thumbnail_";
    }

    public String getThumbnailImgPath() {
        return uploadPath + thumbnailImgPath;
    }

    public String getProjectContractUrl() {
        return imageBaseUrl + projectContractPath + this.getDateDirName();
    }
    public String getProjectContractPath() {
        return uploadPath + projectContractPath + this.getDateDirName();
    }

    public String getCompanyImgUrl() {
        return imageBaseUrl + companyImgPath + this.getDateDirName();
    }

    public String getCompanyImgPath() {
        return uploadPath + companyImgPath + this.getDateDirName();
    }

    public String getCfcaContractEvidencePathUrl() {
        return imageBaseUrl + cfcaContractEvidencePath + this.getDateDirName();
    }
    public String getCfcaContractEvidencePath() {
        return uploadPath + cfcaContractEvidencePath + this.getDateDirName();
    }
    public String getCfcaContractAxQianPathUrl() {
        return imageBaseUrl + cfcaContractAxQianPath + this.getDateDirName();
    }
    public String getCfcaContractAxQianPath() {
        return uploadPath + cfcaContractAxQianPath + this.getDateDirName();
    }
    public String getCfcaContractAxQianPathUrlNoDate() {
        return imageBaseUrl + cfcaContractAxQianPath;
    }
    public String getCfcaContractAxQianPathNoDate() {
        return uploadPath + cfcaContractAxQianPath;
    }
    /**
     * 日期格式的目录（目录名：20150201）
     * @return
     */
    private String getDateDirName(){
        return  ToolsHelper.formatDate2Str(ToolsHelper.getCurrentDate())+"/";
    }

}
