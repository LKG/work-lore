package im.heart.security.session;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.session.mgt.SimpleSession;

import java.math.BigInteger;

@Data
@EqualsAndHashCode(callSuper = true)
public class OnlineSession extends SimpleSession {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7291989300676876442L;

	public static enum OnlineStatus {
		on_line("在线"), hidden("隐身"), force_logout("强制退出");
		private final String info;

		private OnlineStatus(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	/**
	 * 	当前登录的用户Id
	 */
	private transient BigInteger userId = BigInteger.ZERO;

	private transient String username;

	/**
	 * 用户浏览器类型
	 */
	private String userAgent;

	/**
	 * 在线状态
	 */
	private OnlineStatus status = OnlineStatus.on_line;

	/**
	 * 用户登录时系统IP
	 */
	private String systemHost;
	/**
	 * 属性是否改变 优化session数据同步
	 */
	private transient boolean attributeChanged = false;

	public void markAttributeChanged() {
		setAttributeChanged(true) ;
	}

	public void resetAttributeChanged() {
		setAttributeChanged(false) ;
	}
}
