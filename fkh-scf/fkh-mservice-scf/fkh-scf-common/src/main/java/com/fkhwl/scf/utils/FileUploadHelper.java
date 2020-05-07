/**
 *
 */
package com.fkhwl.scf.utils;

import com.fkhwl.scf.compress.ImageURLConvert;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * @ClassName: FileUploadHelper
 * @Description: 文件上传帮助类
 * @author http://liaohong.me
 * @date 2015年1月30日 下午3:38:57
 */
public class FileUploadHelper {


	private static final Logger log = LoggerFactory.getLogger(FileUploadHelper.class);

	/**
	 * 保存web上传的文件
	 * @param saveFileName 保存文件名
	 * @param saveFilePath 保存文件路径
	 * @param waterFile 水印图片(null 为不加水印)
	 * @param thumbnailPath 缩略图路径
	 * @param bytes
	 * @return void
	 */
	public static void saveUploadFile(String saveFileName, String saveFilePath, String waterFile, String thumbnailPath, byte[] bytes) throws Exception {
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			if(!fileDir.mkdirs()){
//				throw new DataValidateException(RespCode.FILE_MKDIRS_ERROR);
			}
		}

		File saveFile = new File(saveFilePath + saveFileName);
		if(saveFile.exists()){ saveFile.delete(); }
		String originPrefix = saveFileName.substring(0, saveFileName.lastIndexOf("."));
		String originSuffix = saveFileName.substring(saveFileName.lastIndexOf("."));
		//超过1M的图片，需要进行图片压缩处理 。xlsx文件不压缩
		if(bytes.length > Const.COMPRESS_IMAGE&&!originSuffix.equals(".xlsx")&&!originSuffix.equals(".html")&&!originSuffix.equals(".pdf")){
			// 1.保存上传的原图
			String originFileName = originPrefix + "_orig"+ originSuffix;
			log.error("1 save origin image path: "+saveFilePath+originFileName);

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(saveFilePath + originFileName));
			stream.write(bytes);
			stream.close();

			// 2.生成一张新的压缩图
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            Thumbnails.of(byteArrayInputStream)
                .size(Const.UPLOAD_IMAGE_COMPRESS_WIDTH , Const.UPLOAD_IMAGE_COMPRESS_HEIGHT)
                .toFile(saveFile);
            byteArrayInputStream.close();
		}else{
			// 如果未超过上传大小限制，则只保存原图
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(saveFile));
			stream.write(bytes);
			stream.close();
		}

		File watermarkFile = null;
		if(null != waterFile){
			watermarkFile = new File(waterFile);
			if(!watermarkFile.exists()){
				log.error("upload image file, not found watermark image file.");
//				throw new DataValidateException(RespCode.BAD_REQUEST, "上传图片失败!");
			}
		}

		if(thumbnailPath != null) {
			File thumbnail = new File(thumbnailPath);
			if(!thumbnail.exists()){ thumbnail.mkdirs(); }
			thumbnailPath += ImageURLConvert.THUMBNAIL_PREFIX + saveFileName;

			if(null != watermarkFile){ //加水印
				BufferedImage watermark = ImageIO.read(new File(waterFile));
				Thumbnails.of(saveFilePath + saveFileName)
					.size(Const.UPLOAD_IMAGE_WIDTH, Const.UPLOAD_IMAGE_WIDTH)
					.watermark(Positions.CENTER, watermark, 0.5f)
					.toFile(thumbnailPath);
			}else{ //不加水印
				Thumbnails.of(saveFilePath + saveFileName)
					.size(Const.UPLOAD_IMAGE_WIDTH, Const.UPLOAD_IMAGE_WIDTH)
					.toFile(thumbnailPath);
			}
		}
	}
}
