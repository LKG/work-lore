package im.heart.core.plugins.ip;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 
 * @author gg
 * @desc IP model 类
 */
@Data
public class IPInfo {
	private String country;
	private String country_id;
	private String area;
	private String area_id;
	private String region;
	private String region_id;
	private String county;
	private String county_id;
	private String isp;
	private String isp_id;
	private String ip;
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}