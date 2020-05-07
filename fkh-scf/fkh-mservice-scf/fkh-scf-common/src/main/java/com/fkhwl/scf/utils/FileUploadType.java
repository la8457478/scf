/**
 *
 */
package com.fkhwl.scf.utils;

/**
 * @ClassName: FileUploadType
 * @Description: 非图片类文件上传类型
 * @author http://liaohong.me
 * @date 2014年9月25日 上午11:54:19
 */
public enum FileUploadType {

	//pdf合同文件
	PORJECT_CONTRACT(1),
	//驾驶员签章合同文件
	PORJECT_DRIVER_SIGN_CONTRACT(2),
    //企业营业执照图片
    BUSINESS_LICENSE_PICTURE(3),
    //企业法人身份证照片
    COMPANY_ID_CARD_PICTURE(4),
    //企业法人身份证照片反面
    COMPANY_ID_CARD_PICTURE_BACK(5),
    //审批单
    REVIEW_BILL(6);
	private int type;
	FileUploadType(int type){
		this.type = type;
	}

	public int getValue(){
		return this.type;
	}

	@Override
	public String toString(){
		return String.valueOf(this.type);
	}
}
