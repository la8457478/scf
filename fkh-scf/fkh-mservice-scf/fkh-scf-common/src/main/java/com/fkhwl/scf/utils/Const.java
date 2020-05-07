package com.fkhwl.scf.utils;

public class Const {

    public static final String AJAX_RESULT_TIMEOUT = "TIMEOUT";
    public static final String AJAX_RESULT_SUCCESS = "success";
    public static final String AJAX_RESULT_ERROR = "error";

    public final static int SYSMGMT_SESSION_TIMEOUT = 600;
    public final static String SYSMGMT_SESSION_ADMIN= "sysAdmin";
    public final static String DATASHOW_SESSION_ADMIN= "sessionUser";
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";

    public static final String USER_SALT = "^scf_user^";
    public static final String ADMIN_SALT = "^scf_admin_user^";

    public static final String COMMA_CHAR = ",";
    public static final String SEMICOLON_CHAR = ";";
    public static final String DOUBLE_LINE_CHAR = "--";
    public static final String COLON_CHAR = ":";
    public static final String UNDERLINE_CHAR = "_";

    // 生成用户登陆验证token的key
    public static final String GENERATE_TOKEN_KEY = "abcdef+bUYkvs8scQHo+UUwWknxABCDE";

    public static final int VALIDATE_CODE_TIMEOUT = 10; //用户用户验证码过期时间(分钟)
    public static final int RANDOM_PASSWORD_LENGTH = 6; //随机密码位数
    public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(forgetPassword)|(code)|(file)|(ajax)|(openApi)).*"; // 不对匹配该值的访问路径拦截（正则）

    public static final String INTERCEPTOR_PATH = ".*/((user)|(profile)).*"; // 不对匹配该值的访问路径拦截（正则）

    public static final int DEFAULT_PAGE_SIZE = 10; //默认分页大小


    // 上传图片缩放大小
    public static final int UPLOAD_IMAGE_SIZE = 1024 * 1024 * 10; // 10MB
    public static final int UPLOAD_IMAGE_WIDTH = 200; // 200*200
    public static final int UPLOAD_IMAGE_COMPRESS_WIDTH = 1024;
    public static final int UPLOAD_IMAGE_COMPRESS_HEIGHT = 768;

    // 需要压缩图片的阈值
    public static final int COMPRESS_IMAGE = 1024 * 1024 * 1; // 1MB

    // 上传PDF文件大小
    public static final int UPLOAD_FILE_SIZE = 1024 * 1024 * 10; // 10MB


   public static final String JY_NOTIFY_SMS_TEMPLATE=  "【空港保理{companyCapitalName}】尊敬的{companyBorrowerName}客户，您编号为{creditApplyNo}（用款申请编号）的应收账款收购款于{date}到期，金额{balance}元，请不迟于{date}回购。为避免收购款逾期，请注意及时回购。如已还款请忽略此短信。详询{smsConsultPhoneNo}。";
}
