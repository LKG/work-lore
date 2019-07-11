package im.heart.core.web.enums;

/**
 *
 * @author gg
 * @desc 错误码说明  后两位业务代码
 */
public enum WebError {
    /**
     * ########################result############################
     #       返回信息规则配置
     # 第一位：
     #	1：后台管理 (系统级别错误)
     #	2：前台门户 (服务级错误)
     # 第二三位：服务模块代码
     # 第四五位：具体错误代码
     #
     #########################################################
     */
    REQUEST_PARAMETER_MISSING("request_parameter_missing", "21101","缺少请求参数,或者参数错误"),
    REQUEST_PARAMETER_EXIT("request_parameter_exit", "21102","重复操作"),
    REQUEST_EXCEPTION("request_exception", "21103","处理请求异常"),
    REQUEST_AUTH_VERIFY("request_auth_verify", "21104","需要验证码"),
    REQUEST_VERIFY_EXCEPTION("request_verify_exception", "21105","验证发送异常"),
    /**普通登录认证错误*/
    AUTH_ACCOUNT_UNKNOWN("auth_account_unknown", "20101","账号不存在"),
    AUTH_CREDENTIALS_INCORRECT("auth_credentials_incorrect", "20102","登录密码错误"),
    AUTH_ACCOUNT_DISABLED("auth_account_disabled", "20103","账号被禁用"),
    AUTH_ACCOUNT_LOCKED("auth_account_locked", "20104","账号未激活或被锁定"),
    AUTH_EXCESSIVE_ATTEMPTS("auth_excessive_attempts", "20105","尝试次数过多,账号被锁定"),
    AUTH_CREDENTIALS_EXPIRED("auth_credentials_expired", "20106","用户凭证过期"),
    AUTH_CAPTCHA_INCORRECT("auth_captcha_incorrect", "20107","验证码错误"),
    AUTH_PHONE_CODE_INCORRECT("auth_phone_code_incorrect", "20108","短信码错误"),
    AUTH_EXCEPTION("auth_exception", "20109","未知认证错误，请联系管理员"),
    AUTH_ACCOUNT_FORCE("auth_account_force", "20110","账号被踢出"),
    AUTH_ACCOUNT_REPEAT("auth_account_repeat", "20111","账号已登录"),
    /***/

    USER_SGIN_EXIT("user_sgin_exit", "20210","用户已签到"),

    /**auth2 认证错误**/
    REDIRECT_URI_MISMATCH("redirect_uri_mismatch", "21322","重定向地址不匹配"),
    INVALID_REQUEST("invalid_request", "21323","请求不合法"),
    INVALID_CLIENT("invalid_client", "21324","client_id或client_secret参数无效"),
    INVALID_GRANT("invalid_grant", "21325","提供的Access Grant是无效的、过期的或已撤销的"),
    UNAUTHORIZED_CLIENT("expired_token", "21326","客户端没有权限"),
    EXPIRED_TOKEN("expired_token", "21327","token过期"),
    UNSUPPORTED_GRANT_TYPE("unsupported_grant_type", "21328","不支持的 GrantType"),
    UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type", "21329","不支持的 ResponseType"),
    ACCESS_DENIED("access_denied", "21330","用户或授权服务器拒绝授予数据访问权限"),
    TEMPORARILY_UNAVAILABLE("temporarily_unavailable", "21331","服务暂时无法访问"),
    APPKEY_PERMISSION_DENIED("appkey_permission_denied ", "21337","应用权限不足"),
    ;
    private WebError(String name, String code,String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description=description;
    }

    private  String name;
    private  String code;
    private  String description;

    public static WebError getExamValue(String value) {
        for(WebError error: WebError.values()){
            if(error.getName().equals(value)||error.getCode().equals(value)){
                return error;
            }
        }
        return null;
    }

}