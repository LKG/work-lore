package im.heart.core.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OptLog {
	/**

	 * 类型
	 * @return
	 */
	public String type();
	
	/**
	 * 操作说明
	 * @return
	 */
	public String detail();
}
