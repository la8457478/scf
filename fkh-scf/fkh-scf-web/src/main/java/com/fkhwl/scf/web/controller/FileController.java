package com.fkhwl.scf.web.controller;


import com.fkhwl.scf.entity.vo.ScfUserVO;
import com.fkhwl.scf.utils.Const;
import com.fkhwl.scf.utils.FileUploadHelper;
import com.fkhwl.scf.utils.FileUploadType;
import com.fkhwl.scf.web.config.SystemConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

/**
 * The type File controller.
 */
@Controller
@RequestMapping(value = "/file")
@AllArgsConstructor
@Api(value = "/file", tags = "文件上传接口")
public class FileController extends BaseController {

	private SystemConfig systemConfig;


    /**
     * Handle file upload.
     *
     * @param fileType the file type
     * @param file      the file
     * @param request   the request
     * @param writer    the writer
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam("fileType") int fileType,
                                 @RequestParam("file") MultipartFile file, HttpServletRequest request, PrintWriter writer) {
        String imageUrl = "";
        String result = Const.AJAX_RESULT_SUCCESS;
        try {
            ScfUserVO user = super.getSessionUser();
//            if(null == user ){
//                writer.print("error");
//                return;
//            }

            if (!file.isEmpty()) {
//                if (file.getSize() <= 0) {
//                    throw new DataValidateException("上传文件大小不正确!");
//                }
//                if (file.getSize() > Const.UPLOAD_FILE_SIZE) {
//                    throw new DataValidateException("上传文件大小超过限制("+(Const.UPLOAD_FILE_SIZE/1024000)+"Mb).");
//                }

                byte[] bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                String contentType = file.getContentType().toLowerCase();
                if (contentType.equals("application/pdf")) {
                    fileName = ".pdf";
                }else if (contentType.equals("image/jpeg")) {
                    fileName = ".jpg";
                } else if (contentType.equals("image/png") || contentType.equals("image/x-png")) {
                    fileName = ".png";
                } else if (contentType.equals("image/bmp")) {
                    fileName = ".bmp";
                }

                //管理员后台上传图片，id传0
                imageUrl = handlerUploadFile(fileType, user.getId(), fileName, bytes);
                result = result + imageUrl;
            } else {
                writer.print("error");
            }
        }catch (Exception e) {
            if(e.getLocalizedMessage().contains("No suitable ImageReader found")){
                result = "上传失败，请稍后重试! e:文件格式可能异常";
            }else{
                result = "上传失败，请稍后重试! e:"+e.getLocalizedMessage();
            }
        } finally {
            writer.print(result);
            writer.flush();
            writer.close();
        }
    }
    /**
     * 处理文件上传及分拼装HTTP URL
     *
     * @return String
     */
    private String handlerUploadFile(int fileType, Long id, String fileName, byte[] bytes) throws Exception {
        String fileHttpUrl = null;
        long timestamp = System.currentTimeMillis();
        fileName = timestamp+"_"+id+"_"+fileType+"_"+fileName;
        if (fileType == FileUploadType.PORJECT_CONTRACT.getValue()) {
            fileHttpUrl = systemConfig.getProjectContractUrl() + fileName;
            FileUploadHelper.saveUploadFile(fileName,
                systemConfig.getProjectContractPath(),
                null, null, bytes);
        } else if (fileType == FileUploadType.BUSINESS_LICENSE_PICTURE.getValue() ||
                fileType == FileUploadType.COMPANY_ID_CARD_PICTURE.getValue() ||
                fileType == FileUploadType.COMPANY_ID_CARD_PICTURE_BACK.getValue()) {
            fileHttpUrl = systemConfig.getThumbnailImgUrl() + fileName;
            FileUploadHelper.saveUploadFile(fileName,
                systemConfig.getCompanyImgPath(),
                null, systemConfig.getThumbnailImgPath(), bytes);
        }

        return fileHttpUrl;
    }
}
