package im.heart.core.plugins.sms;

/**
 *
 */
public class SmsSendException extends RuntimeException {

    private String code;
    private String message;

    public SmsSendException() {

    }

    /**
     * 根据自定义信息构建异常对象
     *
     * @param message
     *            自定义的异常信息
     */
    public SmsSendException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * 根据错误代码和自定义信息构建异常对象
     *
     * @param code
     *            错误代码
     * @param message
     *            自定义的异常信息
     */
    public SmsSendException(String code, String message) {
        super(code + "@" + message);
        this.code = code;
        this.message = message;
    }

    /**
     * 获取异常状态码
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取异常信息
     */
    @Override
    public String getMessage() {
        return message;
    }

    public SmsSendException(Throwable cause) {
        super(cause);
    }

    public SmsSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
