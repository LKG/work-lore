package im.heart.core.validator;



import lombok.Data;

import java.util.Map;

/**
 * 公用校验结果类
 * @author gg
 */
@Data
public class ValidationResult {
	/**
	 *  校验结果是否有错
	 */
	private boolean hasErrors;
	/**
	 * 校验错误信息
	 */
	private Map<String, String> errorMsg;
}
