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
 * reason: 中企云商被运输车辆相关图片类型定义
 */
public class WPEImageConfig {

    /**
     * 图片上传地址配置数组
     */
    private static final String[] UPLOAD_PATH_ARRAY = new String[]{"thumbnail", "images/cargoCar/", "outside","inside","passport","wheel","vin","other"};

    /**
     * 图片上传保存地址
     * @param group
     * @return
     */
    public static String getUploadPath(int group){
        return UPLOAD_PATH_ARRAY[1] + UPLOAD_PATH_ARRAY[group+1] + "/";
    }

    /**
     * 图片上传缩略图保存地址
     * @return
     */
    public static String getThumbnailPath(){
        return UPLOAD_PATH_ARRAY[1] + UPLOAD_PATH_ARRAY[0] + "/";
    }

    /**
     * 汽车照片配置
     * @return
     * @since 2018-09-14
     * @author add by lcy
     */
    public enum CarImgTypeForCar {
        // 1:车前照
        CAR_FRONT(1),
        // 2:左侧车身
        CAR_LEFT(2),
        // 3:车后照
        CAR_REAR(3),
        // 4:右侧车身
        CAR_RIGHT(4),
        // 5:车顶照
        CAR_TOP(5),
        // 6:前排座椅
        CAR_FRONT_SEAT(6),
        // 7:后排座椅
        CAR_REAR_SEAT(7),
        // 8:仪表盘
        CAR_PANEL(8),
        // 9:购车合同
        CAR_CONTRACT(9),
        // 10:购车发票
        CAR_INVOICE(10),
        // 11:快递单
        CAR_TRACKING_NUMBER(11),
        // 12:左侧前轮
        CAR_LEFT_WHEEL_FRONT(12),
        // 13:右侧前轮
        CAR_RIGHT_WHEEL_FRONT(13),
        // 14:左侧后轮
        CAR_LEFT_WHEEL_REAR(14),
        // 15:右侧后轮
        CAR_RIGHT_WHEEL_REAR(15),
        // 16:铭牌nameplate
        CAR_NAMEPLATE(16),
        // 17:车架号
        CAR_FRAME_NUM(17),
        // 18:合格证
        CAR_QUALIFICATION(18),
        // 19:钥匙
        CAR_KEY(19),
        // 20:交接单
        TRANSFER(20);

        public int type;

        CarImgTypeForCar(int type) {
            this.type = type;
        }

        /**
         * 验证图片类型是否有效
         * @param type
         * @return
         */
        public static boolean isValid(Integer type){
            if(null == type || type < 1){
                return Boolean.FALSE;
            }
            CarImgTypeForCar[] typeArray = CarImgTypeForCar.values();
            for(CarImgTypeForCar item : typeArray){
                if(item.type == type){
                    return Boolean.TRUE;
                }
            }

            return Boolean.FALSE;
        }
    }

    public enum ProgramCarImgCfg{
        CAR_FRONT(1, "车前照", 1, CarImgTypeForCar.CAR_FRONT),
        CAR_LEFT(1, "左侧车身", 2, CarImgTypeForCar.CAR_LEFT),
        CAR_REAR(1, "车后照", 3, CarImgTypeForCar.CAR_REAR),
        CAR_RIGHT(1, "右侧车身", 4, CarImgTypeForCar.CAR_RIGHT),
        CAR_TOP(1, "车顶照", 5, CarImgTypeForCar.CAR_TOP),
        CAR_FRONT_SEAT(2, "前排座椅", 6, CarImgTypeForCar.CAR_FRONT_SEAT),
        CAR_REAR_SEAT(2, "后排座椅", 7, CarImgTypeForCar.CAR_REAR_SEAT),
        CAR_PANEL(2, "仪表盘", 8, CarImgTypeForCar.CAR_PANEL),
        CAR_CONTRACT(3, "购车合同", 9, CarImgTypeForCar.CAR_CONTRACT),
        CAR_INVOICE(3, "购车发票", 10, CarImgTypeForCar.CAR_INVOICE),
        CAR_TRACKING_NUMBER(3, "快递单", 11, CarImgTypeForCar.CAR_TRACKING_NUMBER),
        CAR_LEFT_WHEEL_FRONT(4, "左侧前轮", 12, CarImgTypeForCar.CAR_LEFT_WHEEL_FRONT),
        CAR_RIGHT_WHEEL_FRONT(4, "右侧前轮", 13, CarImgTypeForCar.CAR_RIGHT_WHEEL_FRONT),
        CAR_LEFT_WHEEL_REAR(4, "左侧后轮", 14, CarImgTypeForCar.CAR_LEFT_WHEEL_REAR),
        CAR_RIGHT_WHEEL_REAR(4, "右侧后轮", 15, CarImgTypeForCar.CAR_RIGHT_WHEEL_REAR),
        CAR_NAMEPLATE(5, "铭牌", 16, CarImgTypeForCar.CAR_NAMEPLATE),
        CAR_FRAME_NUM(5, "车架号", 17, CarImgTypeForCar.CAR_FRAME_NUM),
        CAR_QUALIFICATION(6, "合格证", 18, CarImgTypeForCar.CAR_QUALIFICATION),
        CAR_KEY(6, "钥匙", 19, CarImgTypeForCar.CAR_KEY),
        TRANSFER(-1, "交接单", 20, CarImgTypeForCar.TRANSFER);

        public int group;
        public String name;
        int index;
        public int type;



        /**
         * 初始化车辆图片上传的配置
         * @param group 分组
         * @param name 图片所属名称
         * @param index 配置索引
         * @param type 图片类型
         */
        ProgramCarImgCfg(int group, String name, int index, CarImgTypeForCar type){
            this.group = group;
            this.name = name;
            this.index = index;
            this.type = type.type;
        }

        /**
         * 根据图片类型初始化配置项，并返回，主要用于上传图片时获取存储路径
         * @param type
         * @return
         */
        public static ProgramCarImgCfg initByType(Integer type){
            if(null == type || type < 1){
                return null;
            }
            ProgramCarImgCfg[] typeArray = ProgramCarImgCfg.values();
            for(ProgramCarImgCfg item : typeArray){
                if(item.type == type){
                    return item;
                }
            }

            return null;
        }

    }
}
