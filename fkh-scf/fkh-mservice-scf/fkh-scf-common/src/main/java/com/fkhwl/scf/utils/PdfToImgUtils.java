package com.fkhwl.scf.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Created by Administrator on 2017/6/6 0006.
 *
 */
public class PdfToImgUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfToImgUtils.class);
    private static final String PAGE_INDEX_SEPARATOR = ",";
    public static  final String JPG="JPG";
    public static  final String PNG="PNG";

    public static void test(){
        File file = new File("D:\\716打印\\111.pdf");
        try {
        PDDocument doc = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(doc);
         int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                BufferedImage image = renderer.renderImageWithDPI(i, 123);
                //          BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, PNG, new File("D:\\716打印\\pdfbox_image.png"));
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    /**
     *
     * @param sourceBytes PDF源文件 转的byte字节数组
     * @return 图片的base64编码串，可能多张
     */
    public static List<String> toPng(byte[] sourceBytes){
        try {
            PDDocument doc = PDDocument.load(sourceBytes);
            List<String> imgList=toPngList(doc);
            return imgList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param srcFile PDF源文件 地址
     * @return 图片的base64编码串，可能多张
     */
    public static List<byte[]> pdfToPngByte(String srcFile){
        try {
            PDDocument doc = PDDocument.load(new File(srcFile));
            List<byte[]> imgList=toPngByteList(doc);
            return imgList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param pdfValue PDF源文件 的base64串
     * @return 图片的base64编码串，可能多张
     */
    public static List<String> toPng(String pdfValue){
        InputStream pdfStream = new ByteArrayInputStream(
                PinganTopdfTool.decode(pdfValue));
        try {
            PDDocument doc = PDDocument.load(pdfStream);
            List<String> imgList=toPngList(doc);
            return imgList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param doc
     * @return 图片的base64编码串，可能多张
     */
    private  static List<String> toPngList(PDDocument doc){
        List<String> imgList=new ArrayList<String>();
        try {
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                //rederImageWithDPI的第二个参数为dpi分辨率单位，可根据需求调节大小，
//                BufferedImage image = renderer.renderImageWithDPI(i, 296);
//                BufferedImage image = renderer.renderImageWithDPI(i, 188);
                BufferedImage image = renderer.renderImageWithDPI(i, 123);

                // 下面为提供了架包里另一种转图片的方法，第二个参数为缩放比。
                //  BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                boolean flag = ImageIO.write(image, "PNG", out);
                byte[] data = out.toByteArray();
                //对字节数组Base64编码
                BASE64Encoder encoder = new BASE64Encoder();
                imgList.add(encoder.encode(data)); ;//返回Base64编码过的字节数组字符串
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgList;
    }

    /**
     *
     * @param doc
     * @return 图片的byte[]，可能多张
     */
    private  static List<byte[]> toPngByteList(PDDocument doc){
        List<byte[]> imgList=new ArrayList<>();
        try {
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                //rederImageWithDPI的第二个参数为dpi分辨率单位，可根据需求调节大小，
                //                BufferedImage image = renderer.renderImageWithDPI(i, 296);
                //                BufferedImage image = renderer.renderImageWithDPI(i, 188);
                BufferedImage image = renderer.renderImageWithDPI(i, 123);

                // 下面为提供了架包里另一种转图片的方法，第二个参数为缩放比。
                //  BufferedImage image = renderer.renderImage(i, 2.5f);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                boolean flag = ImageIO.write(image, "PNG", out);
                byte[] data = out.toByteArray();
                imgList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgList;
    }



    /**
     * pdf转图片
     * @param srcFile pdf转换源文件
     * @param destPath 图片输出目标路径，为空时默认源文件所在的文件夹
     * @param format 待输出图片的格式，默认jpg
     * @param dpi 分辨率，默认96
     * @param merge 是否合并为一张，默认为每页生成一张图片
     * @throws IOException
     */
    public void pdfToImage(String srcFile, String destPath
        , String format, Float dpi, Boolean merge) throws IOException {
        File file = new File(srcFile);
        //默认输出路径为源文件所在文件夹
        if(destPath == null || destPath.isEmpty()){
            destPath = file.getParent();
        }
        List<BufferedImage> images = pdfToImage(file, dpi == null ? 96f : dpi);
        if(images == null || images.isEmpty()){
            return ;
        }
        //默认为jpg
        format = format == null || format.isEmpty() ? "jpg" : format;
        String pdfFileName = file.getName();
        pdfFileName = pdfFileName.substring(0, pdfFileName.indexOf('.'));
        StringBuilder sb = new StringBuilder();
        //合并多张图片为一张
        merge = merge == null ? false : merge;
        if(merge) {
            BufferedImage image = mergeImages(images);
            images.clear();
            images.add(image);
        }
        //保存到本地
        for (int i = 0, len = images.size(); i < len; i++) {
            //输出格式: [文件夹路径]/[pdf文件名]_0001.jpg
            ImageIO.write(images.get(i), format, new File(
                sb.append(destPath).append(File.separator)
                    //                            .append(pdfFileName).append("_").append(String.format("%04d", i + 1))
                    .append(pdfFileName)
                    .append(".").append(format).toString()));
            sb.setLength(0);
        }
    }

    private BufferedImage mergeImages(List<BufferedImage> images){
        int width = 0, height = 0;
        for(BufferedImage image : images){
            width = image.getWidth() > width ? image.getWidth() : width;
            height += image.getHeight();
        }
        BufferedImage pdfImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = pdfImage.createGraphics();
        height = 0;
        for(BufferedImage image :images){
            g2d.drawImage(image, (width - image.getWidth()) / 2, height, image.getWidth(), image.getHeight(), null);
            height += image.getHeight();
        }
        g2d.dispose();
        return pdfImage;
    }

    private List<BufferedImage> pdfToImage(File file, float dpi) throws IOException {
        List<BufferedImage> imgList = null;
        PDDocument pdDocument = null;
        BufferedImage image;
        try {
            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            int numPages = pdDocument.getNumberOfPages();
            imgList = new ArrayList<BufferedImage>();
            for (int i = 0; i < numPages; i++) {
                image = renderer.renderImageWithDPI(i, dpi);
                if (null != image) {
                    imgList.add(image);
                }
            }
        } catch (IOException e) {
            LOGGER.error("convert pdf pages to images failed.", e);
            //FIXME
            throw e;
        } finally {
            try {
                if (null != pdDocument) {
                    pdDocument.close();
                }
            } catch (IOException e) {
                LOGGER.error("close IO failed when convert pdf pages to images.", e);
                //FIXME
                throw e;
            }
        }
        return imgList;
    }

    public static void  main (String[] args)throws IOException{
        String srcFile="D:\\test\\1534422407179_3870_8952_2_.pdf";
        //        String srcFile = "./data/input/测试30M.pdf";
        //        String destPath = "./data/output/";
        new PdfToImgUtils().pdfToImage(srcFile, null,  "png", 123f, true);
      }
}
