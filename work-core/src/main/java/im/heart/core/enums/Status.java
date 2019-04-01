package im.heart.core.enums;

/**
 *
 * @author gg
 * @desc 常用状态
 */
public enum Status {

	pending("pending",-1, "未激活"),
	disabled( "disabled",0, "禁用"),
	enabled("enabled", 1,"正常");
	public String code;
	public int intValue;
	public final String info;

	Status(String code, int intValue, String info) {
		this.code = code;
		this.intValue = intValue;
		this.info = info;
	}

	public static Status findByIntValue(int intValue) {
		for (Status status : Status.values()) {
			if (status.intValue == intValue) {
				return status;
			}
		}
		return Status.enabled;
	}
}
