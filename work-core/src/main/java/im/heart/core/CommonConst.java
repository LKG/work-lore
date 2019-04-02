package im.heart.core;

/**
 *
 * @author gg
 * @desc 系统常量
 */
public class CommonConst {
    public static final String BEAN_SUFFIX = "Service";
    public static final String SESSION_USER_ID = "USER_ID";
    public static final String SESSION_USER_NAME = "USER_NAME";
    public static final String SESSION_LOGIN_TYPE = "LOGIN_TYPE";
    public static final String SESSION_LOGIN_TIMES = "_login_times";
    public static final String STATIC_UPLOAD_ROOT="uploads";
    public static final String CACHE_MANAGER_NAME = "cacheManager";
    /**
     *
     * @author： gg
     * 常用活跃状态枚举
     */
    public static enum Active{
        WAITING(-1, "waiting", "等待"),  RUNNING(0,	"running", "运行中");
        public String code;
        public int value;
        public final String info;
        Active(int value, String code, String info) {
            this.code = code;
            this.value = value;
            this.info = info;
        }
    }
    public static enum FlowStatus{
        initial("initial","初始化"),
        waiting("waiting","处理中"),
        success("success","成功"),
        fail("fail","失败"),
        acceded("acceded","同意"),
        refused("refused","拒绝"),
        processed("processed","已处理");
        private FlowStatus(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        public String code;
        public String desc;
    }
    /**
     * 默认分页参数
     * @author： gg
     */
    public static class Page {
        public static final int DEFAULT_PAGE = 1;
        public static final int DEFAULT_SIZE = 10;
        public static final int MAX_SIZE = 500;
        public static final String ORDER_ASC = "ASC";
        public static final String ORDER_DESC = "DESC";
        public static final String DEFAULT_ORDER = ORDER_ASC;
    }
    /**
     * Request返回结果对象
     * @author LKG
     */
    public static class RequestResult {
        public static final String RESULT = "result";
        public static final String EXCEPTION = "exception";
        public static final String MESSAGE = "message";
        /**
         * 上个页面地址
         */
        public static final String BACK_URL = "back_url";
        public static final String HTTP_STATUS = "httpStatus";
        /**
         * 上个页面地址
         */
        public static final String IGNORE_BACK_URL = "ignore_back_url";
        /**
         * 当前请求的地址 带参数
         */
        public static final String CURRENT_URL = "current_url";
        public static final String SUCCESS = "success";
        public static final String REQUEST_URL = "requestUrl";
        public static final String START_TIME = "startTime";
        public static final String EXECUTE_TIME = "executeTime";
        /**
         * 回调
         */
        public static final String CALLBACK = "callback";
        /**
         * 提交令牌
         */
        public static final String ACCESS_TOKEN = "access_token";
        /**
         * 提交令牌
         */
        public static final String SUB_TOKEN = "sub_token";

        /**
         * 回调jsonp
         */
        public static final String JSON_CALLBACK = "jsoncallback";

        public static final String OPEN_VIEW = "lazy";
        /**
         *  公共结果页面
         */
        public static final String PAGE = "common/result";
        /**
         * 公共结果页子页面
         */
        public static final String PAGE_IN = "common/result_in";

        /**
         * 成功结果页
         */
        public static final String PAGE_SUCCESS = "common/success";

        /**
         * 错误页
         */
        public static final String PAGE_ERROR = "errors/error";
        /**
         * 错误页子页面
         */
        public static final String PAGE_ERROR_IN = "errors/error_in";
        /**
         * 无权限操作页面
         */
        public static final String PAGE_ERROR_UNAUTHORIZED = "errors/unauthorized";

        /**
         * 无权限页子页面
         */
        public static final String PAGE_ERROR_UNAUTHORIZED_IN = "errors/unauthorized_in";
    }
}
