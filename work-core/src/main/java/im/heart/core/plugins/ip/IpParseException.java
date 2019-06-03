package im.heart.core.plugins.ip;

/**
 * 
 * @author gg
 * @desc  自定义IP 解析异常
 */
public class IpParseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5301265969225673368L;

	public IpParseException(String msg) {
		super(msg);
	}

	public IpParseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}