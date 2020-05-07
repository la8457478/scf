package com.fkhwl.scf.compress;

/**
 * <p>Title: com.fkhwl.domain.rest.resp</p>
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Copyright © 2014 返空汇 All Rights Reserved</p>
 * <p>Description: </p>
 * author: liaohong
 * emali: liaohong@fkhwl.com
 * version: 1.0
 * date: 2018-9-14 10:58
 * updatetime:
 * reason: 图片访问路径转换
 */
public class ImageURLConvert {

    /**
     * 图片文件名后缀，默认为JPG格式
     */
    public static final String IMAGE_SUFFIX = ".jpg";
    /**
     * 缩略图名称前缀
     */
    public static final String THUMBNAIL_PREFIX = "thumbnail_";

    /**
     * 转换图片路径，返回访问地址
     * @param imageName
     * @return 访问地址:[0=原图地址，1=缩略图地址]
     */
    public static String[] compressWpe(String imageName){
        String imageType = imageName.split("_")[1];
        imageType = imageType.replace(IMAGE_SUFFIX,"");
        WPEImageConfig.ProgramCarImgCfg imageConfig = WPEImageConfig.ProgramCarImgCfg.initByType(Integer.parseInt(imageType));
        String imagePath = WPEImageConfig.getUploadPath(imageConfig.group);
        String thumbnailPath = WPEImageConfig.getThumbnailPath();
        return new String[]{imagePath + imageName,
            thumbnailPath + THUMBNAIL_PREFIX + imageName};
    }

    /**
     * 转换图片路径，返回访问地址
     * @param imageName
     * @param imageBaseUrl
     * @return 访问地址:[0=原图地址，1=缩略图地址]
     */
    public static String[] compressWpe(String imageName, String imageBaseUrl){
        String[] urlArray = compressWpe(imageName);
        urlArray[0] = imageBaseUrl + urlArray[0];
        urlArray[1] = imageBaseUrl + urlArray[1];
        return urlArray;
    }
}
