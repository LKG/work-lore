package im.heart.core.model;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * 
 * @作者 LKG 
 * @版本 V 1.0 
 * @功能说明：客户端请求内容boby model 
 */
@Data
public class RequestBody {
	private String action;//请求地址
	private String requuid;//请求uuid
	Map<String, String> paras = Maps.newHashMap();

}
