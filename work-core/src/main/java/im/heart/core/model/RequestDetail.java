package im.heart.core.model;

import lombok.Data;

/**
 * 
 * @作者 LKG 
 * @版本 V 1.0 
 * @功能说明：请求明细 model
 */
@Data
public class RequestDetail {
	/**
	 * 压缩方法
	 */
	private String compressMethod;
	private String shopid;
	/**
	 * 本地资源版本
	 */
	private String version;
	/**
	 * 应用的产品号
	 */
	private String pid; //

	/**
	 * 纬度
	 */
	private String lat;
	/**
	 * 经度
	 */
	private String lon;
	/**
	 * 缩放级别
	 */
	private String zoom;

	/**
	 * 要请求的资源的ID
	 */
	private String id;
	/**
	 * 资源关联的类型
	 */
	private String refClass;
}
