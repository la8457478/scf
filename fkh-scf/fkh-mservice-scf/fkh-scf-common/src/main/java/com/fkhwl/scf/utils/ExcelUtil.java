package com.fkhwl.scf.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fkhwl.starter.core.util.StringUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.*;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by:      dong4j
 * Date:            2017-07-26
 * Time:            下午 9:26
 * Description:     通用 excel 导出工具类, 使用注解标记需要被导出的字段和列名
 * 使用方式:
 * 使用 @Excel 标识需要被导出的字段, name: 列名;
 * convert 默认为 false, 当需要根据 db 中的值转换成 excel 显示的值时需要设置为 true
 * 并且写一个 需要被转换的字段名 + ToExcel 方法
 * 比如 OldUserLoginInfo 实体中 driverType 需要被转换, @Excel convert 设置为 true
 * 新建一个 driverTypeToExcel(), 无参, 返回 String, 实现转换逻辑
 * 具体使用参考 GradeRecallOldUsersProjectController.export 方法 和 OldUserLoginInfo 实体类设置
 * 一个实体多种导出方式
 * 使用 owner 标识某个字段是属于哪个方法需要被导出的数据
 * 2017-07-27
 * 新增根据标题和字段关系导出 Map 中的数据
 */
public class ExcelUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);
    private static final String TYPE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    // 需要被转换的字段的自定义转换方法的方法名后缀  字段名 + ToExcel
    private static final String METHOD_POSTFIX = "ToExcel";

    private static CellStyle titleStyle;        // 标题行样式
    private static Font titleFont;              // 标题行字体
    private static CellStyle dateStyle;         // 日期行样式
    private static Font dateFont;               // 日期行字体
    private static CellStyle headStyle;         // 表头行样式
    private static Font headFont;               // 表头行字体
    //private static CellStyle contentStyle;     // 内容行样式
    private static Font contentFont;            // 内容行字体

    //region 设置样式

    /********************************************* 设置样式 *********************************************/
    public static void initWorkbook(HSSFWorkbook workbook) {
        titleFont = workbook.createFont();
        titleStyle = workbook.createCellStyle();
        dateStyle = workbook.createCellStyle();
        dateFont = workbook.createFont();
        headStyle = workbook.createCellStyle();
        headFont = workbook.createFont();
        contentFont = workbook.createFont();

        initTitleCellStyle();
        initTitleFont();
        initDateCellStyle();
        initDateFont();
        initHeadCellStyle();
        initHeadFont();
        initContentFont();
    }
    public static void initWorkbook(Workbook workbook) {
        titleFont = workbook.createFont();
        titleStyle = workbook.createCellStyle();
        dateStyle = workbook.createCellStyle();
        dateFont = workbook.createFont();
        headStyle = workbook.createCellStyle();
        headFont = workbook.createFont();
        contentFont = workbook.createFont();

        initTitleCellStyle();
        initTitleFont();
        initDateCellStyle();
        initDateFont();
        initHeadCellStyle();
        initHeadFont();
        initContentFont();
    }

    /**
     * @Description: 初始化标题行样式
     */
    private static void initTitleCellStyle() {
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        titleStyle.setFont(titleFont);
        //titleStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        //titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    }

    /**
     * @Description: 初始化日期行样式
     */
    private static void initDateCellStyle() {
        dateStyle.setAlignment(CellStyle.ALIGN_CENTER_SELECTION);
        dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dateStyle.setFont(dateFont);
    }

    /**
     * @Description: 初始化表头行样式
     */
    private static void initHeadCellStyle() {
        headStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headStyle.setFont(headFont);
        headStyle.setFillBackgroundColor(IndexedColors.YELLOW.index);
        headStyle.setBorderTop(CellStyle.BORDER_MEDIUM);
        headStyle.setBorderBottom(CellStyle.BORDER_THIN);
        headStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headStyle.setBorderRight(CellStyle.BORDER_THIN);
    }

    /**
     * @Description: 初始化标题行字体
     */
    private static void initTitleFont() {
        titleFont.setFontName("宋体");
        titleFont.setFontHeightInPoints((short) 15);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setCharSet(Font.DEFAULT_CHARSET);
    }

    /**
     * @Description: 初始化日期行字体
     */
    private static void initDateFont() {
        dateFont.setFontName("宋体");
        dateFont.setFontHeightInPoints((short) 14);
        dateFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        dateFont.setCharSet(Font.DEFAULT_CHARSET);
    }

    /**
     * @Description: 初始化表头行字体
     */
    private static void initHeadFont() {
        headFont.setFontName("宋体");
        headFont.setFontHeightInPoints((short) 10);
        headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headFont.setCharSet(Font.DEFAULT_CHARSET);
    }

    /**
     * @Description: 初始化内容行字体
     */
    private static void initContentFont() {
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 10);
        contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        contentFont.setCharSet(Font.DEFAULT_CHARSET);
    }

    private static CellStyle setStyle(HSSFWorkbook workbook, int index) {
        CellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setAlignment(CellStyle.ALIGN_CENTER);
        contentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        contentStyle.setFont(contentFont);
        contentStyle.setBorderTop(CellStyle.BORDER_THIN);
        contentStyle.setBorderBottom(CellStyle.BORDER_THIN);
        contentStyle.setBorderLeft(CellStyle.BORDER_THIN);
        contentStyle.setBorderRight(CellStyle.BORDER_THIN);
        if (index % 2 == 0) {
            contentStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            contentStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        } else {
            contentStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            contentStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        }
        return contentStyle;
    }
    /********************************************* 设置样式 *********************************************/
    //endregion

    /**
     * Export int.
     * 将被 @Excel 标识的实体导出到 excel
     * @param <T>            the type parameter
     * @param cl             被 @Excel 标识的实体
     * @param listContent    需要导出的内容
     * @param response       the response
     * @param exportFileName 导出的文件名
     * @param sheetName      sheet 名
     * @param methodName     用于区分同一个实体多种导出
     * @return the int
     */
    public static <T> Boolean export(Class<T> cl,
                                     List<T> listContent,
                                     HttpServletResponse response,
                                     String exportFileName,
                                     String sheetName,
                                     String methodName,
                                     HttpServletRequest request) {

        long startTime = ToolsHelper.getCurrentDate().getTime();
        // 使用 try-with-resources 自动关闭资源
        try (OutputStream outputStream = response.getOutputStream();
             HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response,exportFileName,request);

            // 数据多余2000行之后使用文件格式导出
            if (listContent.size() > 20000) {
                return exportFile(cl, listContent, outputStream, methodName);
            }

            LinkedHashMap<String, String> keyMap = getMappedFiled(cl, null, methodName);

            // 创建名为sheet的工作表
            HSSFSheet sheet = workbook.createSheet(sheetName);
            HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            //创建标题行
            HSSFRow row = sheet.createRow(0);


            // 设置标题,标题内容为keyMap中的value值
            int titleIndex = 0;
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                HSSFCell cell = row.createCell(titleIndex++);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(entry.getValue());
            }

            // 保存 field 与 字段名的关系
            Map<String, Field> relationMap = new HashMap<>();
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                Object key = entry.getKey();
                // 递归获取被 @Excel 标识的字段, 包括父类中被 @Excel 标识的字段
                Field field = getField(cl, key.toString(), null);
                field.setAccessible(true);
                relationMap.put(key.toString(), field);
            }

            // 设置正文内容
            HSSFCellStyle textStyle = workbook.createCellStyle();
            HSSFDataFormat format = workbook.createDataFormat();
            textStyle.setDataFormat(format.getFormat("@"));
            for (int i = 0; i < listContent.size(); i++) {
                row = sheet.createRow(i + 1);
                int colIndex = 0;
                for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                    HSSFCell cell = row.createCell(colIndex++);
                    String contentStr = convertValue(cl, listContent, relationMap, i, entry);
                    cell.setCellStyle(textStyle);//设置单元格格式为"文本"
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(contentStr);
                }
            }
            workbook.write(outputStream);
            long endTime = ToolsHelper.getCurrentDate().getTime();
            LOGGER.debug("time consuming: {}", endTime - startTime);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("reflect error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 针对历史单据导出定制化方法
     * 动态增加显示磅单号的列数
     * @param cl
     * @param listContent
     * @param response
     * @param exportFileName
     * @param sheetName
     * @param methodName
     * @param request
     * @param <T>
     * @return
     */
    public static <T> Boolean exportForHisWaybill(Class<T> cl,
                                                  List<T> listContent,
                                                  HttpServletResponse response,
                                                  String exportFileName,
                                                  String sheetName,
                                                  String methodName,
                                                  HttpServletRequest request) {

        long startTime = ToolsHelper.getCurrentDate().getTime();
        // 使用 try-with-resources 自动关闭资源
        try (OutputStream outputStream = response.getOutputStream();
             HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response,exportFileName,request);

            // 数据多余2000行之后使用文件格式导出
            if (listContent.size() > 20000) {
                return exportFile(cl, listContent, outputStream, methodName);
            }

            LinkedHashMap<String, String> keyMap = getMappedFiled(cl, null, methodName);

            // 创建名为sheet的工作表
            HSSFSheet sheet = workbook.createSheet(sheetName);
            HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中


            // 保存 field 与 字段名的关系
            Map<String, Field> relationMap = new HashMap<>();
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                Object key = entry.getKey();
                // 递归获取被 @Excel 标识的字段, 包括父类中被 @Excel 标识的字段
                Field field = getField(cl, key.toString(), null);
                field.setAccessible(true);
                relationMap.put(key.toString(), field);
            }

            //1、设置正文内容
            HSSFCellStyle textStyle = workbook.createCellStyle();
            HSSFDataFormat format = workbook.createDataFormat();
            textStyle.setDataFormat(format.getFormat("@"));

            HSSFRow row = null;
            //磅单号最大个数
            int weightNoteNoMaxCount = 0;
            for (int i = 0; i < listContent.size(); i++) {
                row = sheet.createRow(i + 1);
                int colIndex = 0;

                //非磅单号
                for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                    String key = entry.getKey();
                    if(!key.equals("weightNoteNo")){
                        HSSFCell cell = row.createCell(colIndex++);
                        String contentStr = convertValue(cl, listContent, relationMap, i, entry);
                        cell.setCellStyle(textStyle);//设置单元格格式为"文本"
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(contentStr);
                    }
                }

                //磅单号单独处理
                Field field = relationMap.get("weightNoteNo");
                Object content = field.get(listContent.get(i));
                if(null != content && StringUtils.isNotBlank(content.toString())){
                    JSONArray array = null;
                    if (!content.toString().contains("[")) {
                        List<String> contents = new ArrayList<>();
                        contents.add(content.toString());
                        array= JSONArray.parseArray(JSON.toJSONString(contents));
                    } else {
                        try{
                            array = JSONArray.parseArray(content.toString());
                        }catch (Exception e){
                            System.out.println("weightNoteNo parseJSONArray is error, data: "+content.toString());
                            e.printStackTrace();
                        }
                    }

                    if(null != array){
                        for(int j=0; j<array.size(); j++){
                            HSSFCell cell = row.createCell(colIndex++);
                            cell.setCellStyle(textStyle);//设置单元格格式为"文本"
                            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(array.getString(j));
                        }
                        if(array.size()>weightNoteNoMaxCount){
                            weightNoteNoMaxCount = array.size();
                        }
                    }
                }

            }

            //2、创建标题行
            HSSFRow rowHead = sheet.createRow(0);
            // 设置标题,标题内容为keyMap中的value值
            int titleIndex = 0;
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                String key = entry.getKey();
                //非榜单号列的标题头
                if(!key.equals("weightNoteNo")){
                    HSSFCell cell = rowHead.createCell(titleIndex++);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(entry.getValue());
                }
            }
            //如果存在磅单号，则创建磅单号的列
            if(weightNoteNoMaxCount>0){
                for(int j=0; j<weightNoteNoMaxCount; j++){
                    HSSFCell cell = rowHead.createCell(titleIndex++);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(keyMap.get("weightNoteNo") + (j+1));
                }
            }

            workbook.write(outputStream);
            long endTime = ToolsHelper.getCurrentDate().getTime();
            LOGGER.debug("time consuming: {}", endTime - startTime);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("reflect error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Export int.
     * 将被 @Excel 标识的实体导出到 excel
     * 多组数据，多个工作表
     * @param <T>            the type parameter
     * @param cl             被 @Excel 标识的实体
     * @param listContentMap key：sheet 名，value:需要导出的内容
     * @param response       the response
     * @param exportFileName 导出的文件名
     * @param methodName     用于区分同一个实体多种导出
     * @param selfNamesMap   key：sheet 名，value:（自定义头部增加名称（key:位置，value：字符串））
     * @param request        the request
     * @return the int
     */
    public static <T> Boolean export(Class<T> cl,
                                     LinkedHashMap<String, List<T>> listContentMap,
                                     HttpServletResponse response,
                                     String exportFileName,
                                     String methodName,
                                     Map<String, Map<Integer, String>> selfNamesMap,
                                     HttpServletRequest request) {
        // 使用 try-with-resources 自动关闭资源
        try (OutputStream outputStream = response.getOutputStream();
             HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response,exportFileName,request);
            LinkedHashMap<String, String> keyMap = getMappedFiled(cl, null, methodName);

            for (Map.Entry<String, List<T>> listContentTmp : listContentMap.entrySet()) {
                // 创建名为sheet的工作表
                HSSFSheet sheet = workbook.createSheet(listContentTmp.getKey());
                HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

                //创建标题行
                HSSFRow row = sheet.createRow(0);
                // 设置标题,标题内容为keyMap中的value值
                int titleIndex = 0;
                Map<Integer, String> selfNames=selfNamesMap!=null?selfNamesMap.get(listContentTmp.getKey()):null;
                for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                    HSSFCell cell = row.createCell(titleIndex);
                    //cell.setCellStyle(cellStyle);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    if(selfNames!=null&&selfNames.containsKey(titleIndex)){
                        cell.setCellValue(entry.getValue()+selfNames.get(titleIndex));
                    }else{
                        cell.setCellValue(entry.getValue());
                    }
                    // 自适应
                    sheet.autoSizeColumn(titleIndex++, true);
                }

                List<T> listContent=listContentTmp.getValue();
                // 设置正文内容
                for (int i = 0; i < listContent.size(); i++) {
                    row = sheet.createRow(i + 1);
                    // 设置交叉变色
                    int colIndex = 0;
                    for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                        HSSFCell cell = row.createCell(colIndex);
                        Object key = entry.getKey();
                        // 递归获取被 @Excel 标识的字段, 包括父类中被 @Excel 标识的字段
                        Field field = getField(listContent.get(i).getClass(), key.toString(), null);
                        field.setAccessible(true);
                        Object content = field.get(listContent.get(i));
                        String contentStr = null != content ? content.toString() : "";
                        // 如果是时间类型。格式化成标准时间格式
                        String timeStr = getTimeFormatValue(field, content);
                        if (null != timeStr && !timeStr.trim().equals("")) {
                            contentStr = timeStr;
                        }
                        // 是否转换
                        Excel attr = field.getAnnotation(Excel.class);
                        if (attr.convert()) {
                            // 调用转换的方法
                            Method m = cl.getMethod(field.getName() + METHOD_POSTFIX);
                            contentStr = (String) m.invoke(listContent.get(i));// 调用getter方法获取属性值
                        }
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(contentStr);
                        colIndex++;
                    }
                }
                for(int i = 0; i < keyMap.size(); i++) {
                    sheet.autoSizeColumn(i, true);
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("reflect error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    /**
     * Export int.
     * 将被 @Excel 标识的实体导出到 excel
     * 多种class对象，多组数据，多个工作表
     * @param <T>            the type parameter
     * @param classMap             被 @Excel 标识的实体,,key:sheet 名,value：内容对应的class对象
     * @param listContentMap key：sheet 名，value:需要导出的内容
     * @param response       the response
     * @param exportFileName 导出的文件名
     * @param methodName     用于区分同一个实体多种导出
     * @param selfNamesMap   key：sheet 名，value:（自定义头部增加名称（key:位置，value：字符串））
     * @param request        the request
     * @return the int
     */
    public static <T> Boolean export(Map<String, Class> classMap,
                                     LinkedHashMap<String, List<T>> listContentMap,
                                     HttpServletResponse response,
                                     String exportFileName,
                                     String methodName,
                                     Map<String, Map<Integer, String>> selfNamesMap,
                                     HttpServletRequest request) {
        // 使用 try-with-resources 自动关闭资源
        try (OutputStream outputStream = response.getOutputStream();
             HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response,exportFileName,request);

            for (Map.Entry<String, List<T>> listContentTmp : listContentMap.entrySet()) {
                Class cl=classMap.get(listContentTmp.getKey());
                LinkedHashMap<String, String> keyMap = getMappedFiled(cl, null, methodName);
                // 创建名为sheet的工作表
                HSSFSheet sheet = workbook.createSheet(listContentTmp.getKey());
                HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

                //创建标题行
                HSSFRow row = sheet.createRow(0);
                // 设置标题,标题内容为keyMap中的value值
                int titleIndex = 0;
                Map<Integer, String> selfNames=selfNamesMap.get(listContentTmp.getKey());
                for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                    HSSFCell cell = row.createCell(titleIndex);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    if(selfNames!=null&&selfNames.containsKey(titleIndex)){
                        cell.setCellValue(entry.getValue()+selfNames.get(titleIndex));
                    }else{
                        cell.setCellValue(entry.getValue());
                    }
                    // 自适应
                    sheet.autoSizeColumn(titleIndex++, true);
                }
                List<T> listContent=listContentTmp.getValue();
                // 设置正文内容
                for (int i = 0; i < listContent.size(); i++) {
                    row = sheet.createRow(i + 1);
                    // 设置交叉变色
                    int colIndex = 0;
                    for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                        HSSFCell cell = row.createCell(colIndex);
                        Object key = entry.getKey();
                        // 递归获取被 @Excel 标识的字段, 包括父类中被 @Excel 标识的字段
                        Field field = getField(listContent.get(i).getClass(), key.toString(), null);
                        field.setAccessible(true);
                        Object content = field.get(listContent.get(i));
                        String contentStr = null != content ? content.toString() : "";
                        // 如果是时间类型。格式化成标准时间格式
                        String timeStr = getTimeFormatValue(field, content);
                        if (null != timeStr && !timeStr.trim().equals("")) {
                            contentStr = timeStr;
                        }
                        // 是否转换
                        Excel attr = field.getAnnotation(Excel.class);
                        if (attr.convert()) {
                            // 调用转换的方法
                            Method m = cl.getMethod(field.getName() + METHOD_POSTFIX);
                            contentStr = (String) m.invoke(listContent.get(i));// 调用getter方法获取属性值
                        }
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(contentStr);
                        colIndex++;
                    }
                }
                for(int i = 0; i < keyMap.size(); i++) {
                    sheet.autoSizeColumn(i, true);
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            LOGGER.error("reflect error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Export file.
     * 导出数据量大时的解决方法
     * 将数据全部导出到txt中, 后缀为 xls, 直接打开就可以了
     * 使用 \t 代表一列 使用 \n 表示一行
     *
     * windows 下, 使用 , 代替 \t, 使用 \r\n 代替 \n
     * 在 utf-8 的文件前面添加 bom
     * new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}
     * @param <T>          the type parameter
     * @param cl           the cl
     * @param listContent  the list content
     * @param outputStream the output stream
     * @param methodName   the method name
     * @throws IOException               the io exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws NoSuchMethodException     the no such method exception
     * @throws InvocationTargetException the invocation target exception
     */
    private static <T> Boolean exportFile(Class<T> cl, List<T> listContent, OutputStream outputStream, String
        methodName) throws IOException,
        IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        OutputStreamWriter osw = new OutputStreamWriter(outputStream, "UTF-8");
        BufferedWriter buff = new BufferedWriter(osw);

        buff.write(new String(new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
        LinkedHashMap<String, String> keyMap = getMappedFiled(cl, null, methodName);

        // 保存 field 与 字段名的关系
        Map<String, Field> relationMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : keyMap.entrySet()) {
            Object key = entry.getKey();
            // 递归获取被 @Excel 标识的字段, 包括父类中被 @Excel 标识的字段
            Field field = getField(cl, key.toString(), null);
            field.setAccessible(true);
            relationMap.put(key.toString(), field);
            buff.write(entry.getValue() + ',');
        }
        buff.write("\r\n");

        for (int i = 0; i < listContent.size(); i++) {
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                String contentStr = convertValue(cl, listContent, relationMap, i, entry);
                buff.write(contentStr + ',');
            }
            buff.write("\r\n");
            buff.flush();
        }
        buff.close(); //关闭文件操作
        return Boolean.TRUE;
    }

    /**
     * Export boolean.
     * 根据 Map 导出数据
     * @param relation       关系数组
     * @param listContent    数据
     * @param response       the response
     * @param exportFileName the export file name
     * @param sheetName      the sheet name
     * @return the boolean
     */

    public static Boolean export(Map<String, String> relation, List<Map<String, Object>> listContent,
                                 HttpServletResponse
        response, String exportFileName, String sheetName, HttpServletRequest request) {
        try (OutputStream outputStream = response.getOutputStream();
             HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response,exportFileName,request);

            // 创建名为sheet的工作表
            HSSFSheet sheet = workbook.createSheet(sheetName);
            HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

            //创建标题行
            HSSFRow row = sheet.createRow(0);
            // 设置标题,标题内容为keyMap中的value值
            int titleIndex = 0;
            for (Map.Entry<String, String> entry : relation.entrySet()) {
                HSSFCell cell = row.createCell(titleIndex++);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(entry.getKey());
            }

            // 设置正文内容
            for (int i = 0; i < listContent.size(); i++) {
                row = sheet.createRow(i + 1);
                // 设置交叉变色
                int colIndex = 0;
                Map<String, Object> contentMap = listContent.get(i);
                for (Map.Entry<String, String> entry : relation.entrySet()) {
                    HSSFCell cell = row.createCell(colIndex++);
                    Object content = contentMap.get(entry.getValue());
                    String contentStr = null != content ? content.toString() : "";
                    // 如果是时间类型。要格式化成标准时间格式
                    String timeStr = getTimeFormatValue(content);
                    if (null != timeStr && !timeStr.trim().equals("")) {
                        contentStr = timeStr;
                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(contentStr);
                }
            }
            //宽度自适应调整为导出完成后执行
            for(int i = 0; i < relation.size(); i++) {
                sheet.autoSizeColumn(i, true);
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    public static Boolean exportHashMap(Map<String, String> relation, List<HashMap<String, Object>> listContent,
                                        HttpServletResponse
                                            response, String exportFileName, String sheetName, HttpServletRequest req) {
        try (OutputStream outputStream = response.getOutputStream(); HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);
            initResponse(response, exportFileName, req);
            // 创建名为sheet的工作表
            HSSFSheet sheet = workbook.createSheet(sheetName);
            HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            //            HSSFFont font = workbook.createFont();
            //            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
            //            cellStyle.setFont(font);
            //创建标题行
            HSSFRow row = sheet.createRow(0);
            // 设置标题,标题内容为keyMap中的value值
            int titleIndex = 0;
            for (Map.Entry<String, String> entry : relation.entrySet()) {
                HSSFCell cell = row.createCell(titleIndex++);
                cell.setCellStyle(cellStyle);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(entry.getKey());
                int width=3080;
                if(entry.getKey().getBytes().length*256>width){
                    width=entry.getKey().getBytes().length*256+20;
                }
                sheet.setColumnWidth(titleIndex, width);
            }

            // 设置正文内容
            for (int i = 0; i < listContent.size(); i++) {
                row = sheet.createRow(i + 1);
                // 设置交叉变色
                int colIndex = 0;
                Map<String, Object> contentMap = listContent.get(i);
                for (Map.Entry<String, String> entry : relation.entrySet()) {
                    HSSFCell cell = row.createCell(colIndex++);
                    Object content = contentMap.get(entry.getValue());
                    String contentStr = null != content ? content.toString() : "";
                    // 如果是时间类型。要格式化成标准时间格式
                    String timeStr = getTimeFormatValue(content);
                    if (null != timeStr && !timeStr.trim().equals("")) {
                        contentStr = timeStr;
                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(contentStr);
                }
            }
            for(int i = 0; i < relation.size(); i++) {
                sheet.autoSizeColumn(i, true);
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    /**
     * 设置响应头信息，已解决火狐导出乱码问题
     * @param response
     * @param exportFileName
     * @param request
     * @return void
     * @author add by sj
     * @since 2018-06-12
     */
    public static void initResponse(HttpServletResponse response, String exportFileName, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=" + isMSBrowser(request, exportFileName) + ".xls");
    }

    /**
     * 设置响应头信息，已解决火狐导出乱码问题
     * @param response
     * @param exportFileName
     * @param request
     * @return void
     * @author add by sj
     * @since 2018-06-12
     */
    public static void initResponseXlsx(HttpServletResponse response, String exportFileName, HttpServletRequest request) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-disposition", "attachment;filename=" + isMSBrowser(request, exportFileName) + ".xlsx");
    }

    public static void createCell(HSSFRow row, HSSFSheet sheet, HSSFCellStyle cellStyle, String value, int titleIndex){
        HSSFCell cell = row.createCell(titleIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
        int width=3080;
        if(value.getBytes().length*256>width){
            width=value.getBytes().length*256+20;
        }
        sheet.setColumnWidth(titleIndex, width);
    }
    public static void createCell(Row row, Sheet sheet, CellStyle cellStyle, String value, int titleIndex){
        Cell cell = row.createCell(titleIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(value);
        int width=3080;
        if(value.getBytes().length*256>width){
            width=value.getBytes().length*256+20;
        }
        sheet.setColumnWidth(titleIndex, width);
    }

    /**
     * 已经设置标题，只需填充数据行
     * @param listContent
     * @param response
     * @param exportFileName
     * @param workbook
     * @param sheet
     * @param rowIndex
     * @param request
     * @return
     */
    public static Boolean exportHashMapStepLast(List<HashMap<String, Object>> listContent,
                                                HttpServletResponse response, String exportFileName,
                                                HSSFWorkbook workbook , HSSFSheet sheet, int rowIndex, HttpServletRequest request) {

        try (OutputStream outputStream = response.getOutputStream(); ) {
            initResponse(response,exportFileName,request);
            // 设置正文内容
            for (int i = 0; i < listContent.size(); i++) {
                //创建行
                HSSFRow row = sheet.createRow(i + rowIndex);
                Map<String, Object> contentMap = listContent.get(i);
                for (int j = 0; j < contentMap.size(); j++) {
                    HSSFCell cell = row.createCell(j);
                    Object content  = contentMap.get(j+"");
//                    Object content = contentMap.get(entry.getValue());
                    String contentStr = null != content ? content.toString() : "";
                    // 如果是时间类型。要格式化成标准时间格式
                    String timeStr = getTimeFormatValue(content);
                    if (null != timeStr && !timeStr.trim().equals("")) {
                        contentStr = timeStr;
                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(contentStr);
                    //cell.setCellStyle(contentStyle);
                    //sheet.autoSizeColumn(colIndex, true);
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Is ms browser boolean.
     * 以下为服务器端判断客户端浏览器类型的方法
     * @param request the request
     * @return the boolean
     */
    private static String isMSBrowser(HttpServletRequest request, String exportFileName) throws
        UnsupportedEncodingException {
        List<String> IEBrowserSignals = Arrays.asList("msie", "trident", "edge");
        boolean isUtf8=false;
        String userAgent = request.getHeader("User-Agent").toLowerCase();
        for (String signal : IEBrowserSignals) {
            if (userAgent.contains(signal)){
                isUtf8= true;
            }
        }
        // 进行转码，使其支持中文文件名
        if (isUtf8) {
            exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
        }else {
            exportFileName = new String(exportFileName.getBytes(), "ISO-8859-1");
        }
        return exportFileName;
    }

    /**
     * Convert value string.
     * 转换实体对象的值
     * @param <T>         the type parameter
     * @param cl          the cl
     * @param listContent the list content
     * @param relationMap the relation map
     * @param i           the
     * @param entry       the entry
     * @return the string
     * @throws IllegalAccessException    the illegal access exception
     * @throws NoSuchMethodException     the no such method exception
     * @throws InvocationTargetException the invocation target exception
     */
    private static <T> String convertValue(Class<T> cl, List<T> listContent, Map<String, Field> relationMap, int i,
                                           Map.Entry<String, String> entry)
                                                    throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object key = entry.getKey();

        Field field = relationMap.get(key.toString());
        Object content = null;
        if(field.getType().toString().equals("class java.lang.Double")){
            content = Tools.formatDoubleToString(2,(Double)field.get(listContent.get(i)));
        }else if(field.getType().toString().equals("class java.lang.Integer")){
            content = 0;
            Object obj = field.get(listContent.get(i));
            if(obj != null){
                content = Integer.parseInt(obj.toString());
            }
        }else {
            content = field.get(listContent.get(i));
        }

        String contentStr = null != content ? content.toString() : "";
        // 如果是时间类型。格式化成标准时间格式
        String timeStr = getTimeFormatValue(field, content);
        if (null != timeStr && !timeStr.trim().equals("")) {
            contentStr = timeStr;
        }
        // 是否转换
        Excel attr = field.getAnnotation(Excel.class);
        if (attr.convert()) {
            // 调用转换的方法
            Method m = cl.getMethod(field.getName() + METHOD_POSTFIX);
            Object obj = listContent.get(i);
            if(obj != null){
                contentStr = (String) m.invoke(obj);// 调用getter方法获取属性值
            }

        }
        return contentStr;
    }


    /**
     * Import excel list.
     * 从 excel 导入数据
     * @param fileName the file name
     * @param is       the is
     * @return the list
     */
    public static List<String> importExcel(String fileName, InputStream is) {
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        List<String> list = new ArrayList<>();
        Workbook workbook;
        try {
            switch (fileType) {
                case "xls":
                    workbook = new HSSFWorkbook(is);
                    break;
                case "xlsx":
                    workbook = new XSSFWorkbook(is);
                    break;
                default:
                    throw new Exception("读取的不是excel文件");
            }
        } catch (Exception e) {
            LOGGER.error("读取的不是excel文件", e);
            return null;
        }
        // 如果传入的sheet名不存在则默认指向第1个sheet.
        Sheet sheet = workbook.getSheetAt(0);
        // 得到数据的行数
        int rows = sheet.getLastRowNum() + 1;
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {//略过空行
                continue;
            }
            Cell cell = row.getCell(0);
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    list.add(cell.getNumericCellValue() + "");
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    list.add(cell.getStringCellValue());
                    break;
                default:
                    list.add("");
            }
        }
        return list;
    }

    /**
     * 获取格式化后的时间串
     * @param field
     * @param content
     * @return
     */
    private static String getTimeFormatValue(Field field, Object content) {
        // 修复老数据为空的问题
        if (content == null) {
            return "";
        }
        String timeFormatVal = "";
        if (field.getType().getName().equals(java.sql.Timestamp.class.getName())) {
            Timestamp time = (Timestamp) content;
            timeFormatVal = longTimeTypeToStr(time.getTimestamp().getTime(), TYPE_YYYY_MM_DD_HH_MM_SS);
        } else if (field.getType().getName().equals(Date.class.getName())) {
            Date time = (Date) content;
            timeFormatVal = longTimeTypeToStr(time.getTime(), TYPE_YYYY_MM_DD_HH_MM_SS);
        }
        return timeFormatVal;
    }

    /**
     * Gets time format value.
     * @param content the content
     * @return the time format value
     */
    public static String getTimeFormatValue(Object content) {
        if (content == null) {
            return "";
        }
        String timeFormatVal = "";
        if (content instanceof Date) {
            Date time = (Date) content;
            timeFormatVal = longTimeTypeToStr(time.getTime(), TYPE_YYYY_MM_DD_HH_MM_SS);
        }
        return timeFormatVal;
    }

    /**
     * 格式化时间
     * @param time
     * @param formatType
     * @return
     */
    private static String longTimeTypeToStr(long time, String formatType) {
        String strTime = "";
        if (time >= 0) {
            SimpleDateFormat sDateFormat = new SimpleDateFormat(formatType);
            strTime = sDateFormat.format(new Date(time));
        }
        return strTime;
    }

    /**
     * Gets mapped filed.
     * 获取所有具有 @Excel 标识的字段名和注解名
     * @param clazz  the clazz
     * @param keyMap the key map
     * @return the mapped filed
     */
    private static LinkedHashMap<String, String> getMappedFiled(Class clazz, LinkedHashMap<String, String> keyMap,
                                                                String methodName) {
        if (keyMap == null) {
            keyMap = new LinkedHashMap<>();
        }
        // 得到所有定义字段
        Field[] allFields = clazz.getDeclaredFields();
        // 得到所有被@Excel 标识的 field 并存放到一个list中.
        for (Field field : allFields) {
            if (field.isAnnotationPresent(Excel.class)) {
                Excel attr = field.getAnnotation(Excel.class);
                // 判断 owner 是否属于当前方法需要导出的字段
                if (attr.owner().length == 0 || ArrayUtils.contains(attr.owner(), methodName)) {
                    keyMap.put(field.getName(), field.getAnnotation(Excel.class).name());
                }
            }
        }
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(Object.class)) {
            getMappedFiled(clazz.getSuperclass(), keyMap, methodName);
        }
        return keyMap;
    }

    /**
     * Get field field.
     * 递归获取没有在子类中的被 @Excel 标识的字段
     * @param cl    the cl
     * @param key   the key
     * @param field the field
     * @return the field
     */
    private static Field getField(Class cl, String key, Field field) {
        try {
            field = cl.getDeclaredField(key);
        } catch (NoSuchFieldException e) {
            // 若抛异常 则取父类的字段
            if (cl.getSuperclass() != null && !cl.getSuperclass().equals(Object.class)) {
                field = getField(cl.getSuperclass(), key, field);
            }
        }
        return field;
    }

    /**
     * 处理创建导出表格的标题
     * @param arrayTtitles
     * @param cell
     * @param row
     * @param sheet
     */
    private void addCellTitles(String[] arrayTtitles, HSSFCell cell,
                               HSSFRow row, HSSFSheet sheet) {
        for (int i = 0; i < arrayTtitles.length; i++) {
            this.addCellTitle(i, arrayTtitles[i], cell, row, sheet);
        }
    }

    /**
     * 处理创建导出表格的标题
     * @param index
     * @param title
     * @param cell
     * @param row
     * @param sheet
     */
    private void addCellTitle(int index, String title, HSSFCell cell,
                              HSSFRow row, HSSFSheet sheet) {
        this.addCellTitle(index, title.length() * 1000, title, cell, row, sheet);
    }

    /**
     * 处理创建导出表格的标题
     * @param index
     * @param width
     * @param title
     * @param cell
     * @param row
     * @param sheet
     */
    private void addCellTitle(int index, int width, String title,
                              HSSFCell cell, HSSFRow row, HSSFSheet sheet) {
        cell = row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(title);
        sheet.setColumnWidth(index, width);
    }

    /**
     * 处理创建导出表格的内容
     * @param index
     * @param content (string obj)
     * @param cell
     * @param row
     */
    private void addCellContent(int index, Object content, HSSFCell cell,
                                HSSFRow row) {
        cell = row.createCell(index);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(null == content ? "" : content.toString());
    }

    /**
     * 适用于第一行是标题行的excel，例如
     * 姓名   年龄  性别  身高
     * 张三   25  男   175
     * 李四   22  女   160
     * 每一行构成一个map，key值是列标题，value是列值。没有值的单元格其value值为null
     * 返回结果最外层的list对应一个excel文件，第二层的list对应一个sheet页，第三层的map对应sheet页中的一行
     * @param is
     * @param fileName
     * @return the list
     * @throws Exception the exception
     */
    public static List<List<Map<String, Object>>> readExcelWithTitle(InputStream is, String fileName) throws Exception {
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Workbook wb = null;
        try {
            if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
                throw new Exception("读取的不是excel文件");
            }
            if(fileName.endsWith("xls")){
                wb = new HSSFWorkbook(is);
            }else{
                wb=new XSSFWorkbook(is);
            }

            List<List<Map<String, Object>>> result = new ArrayList<>();//对应excel文件

            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                List<Map<String, Object>> sheetList = new ArrayList<>();//对应sheet页

                List<String> titles = new ArrayList<>();//放置所有的标题

                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    Row row = sheet.getRow(j);
                    if (row == null) {//略过空行
                        continue;
                    }
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    if (j == 0) {//第一行是标题行
                        for (int k = 0; k < cellSize; k++) {
                            Cell cell = row.getCell(k);
                            String cellStr = cell == null ? "" : String.valueOf(cell);
                            titles.add(cellStr);
//                            System.out.print(cell.toString() + "\t");
                        }
                    } else {//其他行是数据行
                        Map<String, Object> rowMap = new HashMap<>();//对应一个数据行
                        for (int k = 0; k < titles.size(); k++) {
                            Cell cell = row.getCell(k);
                            String key = titles.get(k);
                            String value = null;
                            if (cell != null) {
                                value = cell.toString();
                                switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                                    case HSSFCell.CELL_TYPE_NUMERIC:
//                                        System.out.print(cell.getNumericCellValue() + "\t");
                                        if(HSSFDateUtil.isCellDateFormatted(cell)) {
                                            //用于转化为日期格式
                                            Date d = cell.getDateCellValue();
                                            rowMap.put(key, formater.format(d));
                                            break;
                                        }
                                        double doubleValue = cell.getNumericCellValue();
                                        //大于7位时 会采用科学计数法
                                        if (doubleValue >= 10000000) {
                                            BigDecimal cellValue = new BigDecimal(doubleValue);
                                            if (cellValue.scale() > 2) {
                                                rowMap.put(key, cellValue.setScale(2, BigDecimal.ROUND_HALF_UP));
                                                break;
                                            }
                                            rowMap.put(key, cellValue);
                                            break;
                                        }
                                        //整数时直接取整形
                                        if(doubleValue == (long)doubleValue){
                                            rowMap.put(key, (long)doubleValue);
                                            break;
                                        }
                                        rowMap.put(key, doubleValue);
                                        break;
                                    case HSSFCell.CELL_TYPE_STRING:
//                                        System.out.print(cell.getStringCellValue() + "\t");
                                        rowMap.put(key, cell.getStringCellValue());
                                        break;
                                    case HSSFCell.CELL_TYPE_BOOLEAN:
//                                        System.out.print(cell.getBooleanCellValue() + "\t");
                                        rowMap.put(key, cell.getBooleanCellValue());
                                        break;
                                    case HSSFCell.CELL_TYPE_FORMULA:
//                                        System.out.print(cell.getCellFormula() + "\t");
                                        rowMap.put(key, cell.getCellFormula());
                                        break;
                                    default:
//                                        System.out.println("unsuported sell type");
                                        rowMap.put(key, "");
                                        break;
                                }
                            }
                        }
                        sheetList.add(rowMap);
                    }
                }
                result.add(sheetList);
            }

            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * 读取excel的标题
     * 适用于第一行是标题行的excel，例如
     * 姓名   年龄  性别  身高
     * 张三   25  男   175
     * 李四   22  女   160
     * 返回每个sheet页的第一行
     * 返回结果最外层的list对应一个excel文件，第二层的list对应一个sheet页，第三层的map对应sheet页中的第一行
     * @param is
     * @param fileName
     * @return the list
     * @throws Exception the exception
     */
    public static List<List<String>> readExcelTitle(InputStream is, String fileName) throws Exception {
        Workbook wb = null;
        try {
            if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
                throw new Exception("读取的不是excel文件");
            }
            if(fileName.endsWith("xls")){
                wb = new HSSFWorkbook(is);
            }else{
                wb=new XSSFWorkbook(is);
            }

            List<List<String>> result = new ArrayList<>();//对应excel文件

            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);
                List<String> sheetTitlesList = new ArrayList<>();//对应sheet页,放置所有的标题
                //第一行必须是标题
                Row row = sheet.getRow(0);
                if (row == null) {//略过空行
                    continue;
                }
                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                for (int k = 0; k < cellSize; k++) {
                    Cell cell = row.getCell(k);
                    String cellStr = cell == null ? "" : String.valueOf(cell);
                    sheetTitlesList.add(cellStr);
                }
                result.add(sheetTitlesList);
            }

            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    public static Boolean exportFile(Map<String, String> relation, List<Map<String, Object>> listContent,
                                     OutputStream out, String sheetName) {
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            initWorkbook(workbook);

            // 创建名为sheet的工作表
            HSSFSheet sheet = workbook.createSheet(sheetName);
            HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle();
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

            //创建标题行
            HSSFRow row = sheet.createRow(0);
            // 设置标题,标题内容为keyMap中的value值
            int titleIndex = 0;
            for (Map.Entry<String, String> entry : relation.entrySet()) {
                HSSFCell cell = row.createCell(titleIndex++);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(entry.getKey());
            }

            // 设置正文内容
            for (int i = 0; i < listContent.size(); i++) {
                row = sheet.createRow(i + 1);
                // 设置交叉变色
                int colIndex = 0;
                Map<String, Object> contentMap = listContent.get(i);
                for (Map.Entry<String, String> entry : relation.entrySet()) {
                    HSSFCell cell = row.createCell(colIndex++);
                    Object content = contentMap.get(entry.getValue());
                    String contentStr = null != content ? content.toString() : "";
                    // 如果是时间类型。要格式化成标准时间格式
                    String timeStr = getTimeFormatValue(content);
                    if (StringUtils.isNotBlank(timeStr)) {
                        contentStr = timeStr;
                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(contentStr);
                }
            }
            //宽度自适应调整为导出完成后执行
            for(int i = 0; i < relation.size(); i++) {
                sheet.autoSizeColumn(i, true);
            }
            workbook.write(out);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 获取单元格各类型值，返回字符串类型
     * @param cell
     * @return
     */
    public static String getCellValueByCell(Cell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //判断是否为null或空串
        if (cell == null) {
            return "";
        }
        String cellValue = "";
        switch (cell.getCellType()) {   //根据cell中的类型来输出数据
            case HSSFCell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    //用于转化为日期格式
                    Date d = cell.getDateCellValue();
                    cellValue = sdf.format(d);
                }else{
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setGroupingUsed(false);//不用科学记数法
                    cellValue = nf.format(cell.getNumericCellValue());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = "" + cell.getBooleanCellValue();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                cellValue = cell.getCellFormula();
                break;
            default:
                break;
        }
        return cellValue;
    }

    /**
     * 创建行
     * 如果存在则沿用，如果不存在则创建
     * @param sheet
     * @param rowIndex
     * @return
     */
    public static Row createRowIfNull(Sheet sheet,int rowIndex){
        Row row = sheet.getRow(rowIndex);
        if(row==null){
            row = sheet.createRow(rowIndex);
        }
        return row;
    }
    /**
     * 创建单元格
     * 如果存在则沿用，如果不存在则创建
     * @param row
     * @param cellIndex
     * @return
     */
    public static Cell createCellIfNull(Row row,int cellIndex){
        Cell cell = row.getCell(cellIndex);
        if(cell==null){
            cell = row.createCell(cellIndex);
        }
        return cell;
    }
    /**
     * 根据数据对象类型，分别设置不同格式的表格类型和数据
     * @param cell
     * @param value
     * @param wb
     */
    public static void setCellValueByObject(Cell cell, Object value, Workbook wb){
        if(value==null || "NaN".equals(value.toString())){
            // TODO-SJ: 2019.11.18 运营平台P0：排查
            return;
        }
        if(value instanceof String){
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(value.toString());
        }else if(value instanceof Date){//时间格式
            CellStyle style=cell.getCellStyle();
            if(style==null){
                style=wb.createCellStyle();
                style.setDataFormat(wb.createDataFormat().getFormat("MM/dd"));
                cell.setCellStyle(style);
            }
            //用于转化为日期格式
            Date d = (Date)value;
            cell.setCellValue(d);
        }else if(value instanceof BigDecimal){
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            BigDecimal cellValue = (BigDecimal)value;
            if (cellValue.scale() > 2) {
                int newScale =2;
                cell.setCellValue(cellValue.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue());
            }else{
                cell.setCellValue(cellValue.doubleValue());
            }
        }else if( value instanceof Double){
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            BigDecimal cellValue = new BigDecimal((Double)value);
            if (cellValue.scale() > 2) {
                int newScale =2;
                if((-1<cellValue.doubleValue() && cellValue.doubleValue()<0)||(0<cellValue.doubleValue() && cellValue.doubleValue()<1)){
                    newScale =4;
                }
                cell.setCellValue(cellValue.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue());
            }else{
                cell.setCellValue(cellValue.doubleValue());
            }
        }else if(value instanceof Long || value instanceof Integer){
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(Double.valueOf(value.toString()));
        }else{
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            cell.setCellValue(Double.valueOf(value.toString()));
        }
    }


    public static Boolean export(HttpServletResponse response, HttpServletRequest request, String exportFileName, Workbook workbook) {
        long startTime = ToolsHelper.getCurrentDate().getTime();
        // 使用 try-with-resources 自动关闭资源
        OutputStream outputStream =null;
        try  {
            outputStream = response.getOutputStream();
            initWorkbook(workbook);
            initResponseXlsx(response,exportFileName,request);
            workbook.write(outputStream);
            long endTime = ToolsHelper.getCurrentDate().getTime();
            LOGGER.debug("time consuming: {}", endTime - startTime);
        } catch (IOException e) {
            LOGGER.error("get OutputStream error", e);
            return Boolean.FALSE;
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("export closeOutputStream error",e);
                }
            }
            if(workbook!=null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    LOGGER.error("export closeWorkbook error",e);
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 隐藏列
     * @param sheet
     * @param start 包含
     * @param end 包含
     */
    public static void setColHidden(Sheet sheet,int start,int end) {
        for (int i = start; i < end; i++) {
            sheet.setColumnHidden(i,true);//隐藏列
        }
    }
    /**
     * 隐藏行
     * @param sheet
     * @param start 包含
     * @param end 包含
     */
    public static void setRowHidden(Sheet sheet,int start,int end) {
        for (int i = start; i < end; i++) {
            sheet.getRow(i).setZeroHeight(true);
        }
    }
}
