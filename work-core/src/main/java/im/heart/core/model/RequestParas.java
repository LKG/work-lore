package im.heart.core.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * 
 * @作者 LKG 
 * @版本 V 1.0 
 * @功能说明：对外接口统一参数 model
 */
@Data
public class RequestParas {
	/**
	 * 产品号
	 */
	@JSONField(name="CP_PRT")
	private String cpPrt;
	/**
	 * 系统唯一用户ID，标记用户身份
	 */
	@JSONField(name="CP_UID")
	private String cpUid;
	/**
	 * 软件平台(标准字符串，参考接入产品列表，全部采用小写)
	 *
	 */
	@JSONField(name="CP_PLTFM")
	private String cpPltfm;

	/**
	 * 是否触摸(1触摸0非触摸)
	 */
	@JSONField(name="CP_TOUCH")
	private String cpTouch;
	/**
	 * 城市编号(公司统一城市ID)
	 */
	@JSONField(name="CP_CITYID")
	private String cpCityId;
	/**
	 * 用户手机IMEI(最长不超过64位)
	 *
	 */
	@JSONField(name="CP_IMEI")
	private String cpImei;
	/**
	 * 渠道号
	 */
	@JSONField(name="CP_CH")
	private String cpCh;
	/**
	 * 手机机型(不要包含空格，如：nokia_n97、iphone4、htc_g7)
	 */
	@JSONField(name="CP_MODEL")
	private String cpModel;

	/**
	 * 手机屏幕分辨率，宽度乘以高度，例240*320
	 */
	@JSONField(name="CP_RATIO")
	private String cpRatio;

	 /**
	 * 模板名
	 */
	@JSONField(name="CP_TPL")
	private String cpTpl;

	/**
	 * 软件版本(标准的三位版本号如：2.2.2、2.3.0)
	 */
	@JSONField(name="CP_VER")
	private String cpVer;

	/**
	 * 资源版本号
	 */
	@JSONField(name="CP_RESVER")
	private String cpResver;

	/**
	 * 用户电话号码
	 */
	@JSONField(name="CP_PHONENUM")
	private String cpPhoneNum;
	/**
	 * 公共资源路径
	 */
	@JSONField(name="CP_PUBRESPATH")
	private String cpPubResPath;
	/**
	 * 经度
	 */
	@JSONField(name="CP_LON")
	private String cpLon;
	/**
	 * 纬度
	 */
	@JSONField(name="CP_LAT")
	private String cpLat;
	/**
	 * 处理请求体
	 */
	private List<RequestBody> actions;
}
