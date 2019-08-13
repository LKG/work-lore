package im.heart.core.plugins.concurrent;

public class LimitAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1412466935970502791L;
	private String code;
	private String message;

	public LimitAccessException() {

	}

	/**
	 * 根据自定义信息构建异常对象
	 * 
	 * @param message
	 *            自定义的异常信息
	 */
	public LimitAccessException(String message) {
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
	public LimitAccessException(String code, String message) {
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

	public LimitAccessException(Throwable cause) {
		super(cause);
	}

	public LimitAccessException(String message, Throwable cause) {
		super(message, cause);
	}
}