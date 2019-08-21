package im.heart.core.model;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 *
 * @author gg
 * @desc 客户端请求内容RequestBody model
 */
@Data
public class RequestBody {
	/**
	 * //请求地址
	 */
	private String action;
	/**
	 * 请求uuid
	 */
	private String requuid;

	Map<String, String> paras = Maps.newHashMap();

}
