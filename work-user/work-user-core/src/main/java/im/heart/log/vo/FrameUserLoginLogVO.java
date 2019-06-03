package im.heart.log.vo;

import com.alibaba.fastjson.JSON;
import im.heart.core.plugins.ip.IpInfo;
import im.heart.log.entity.FrameLogLogin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;


public class FrameUserLoginLogVO extends FrameLogLogin {

	private static final long serialVersionUID = 4682427064421749044L;

	private IpInfo ipInfo;// 根据IP 信息

	public FrameUserLoginLogVO(FrameLogLogin po) {
		BeanUtils.copyProperties(po, this);
		if (StringUtils.isNotBlank(po.getUserIpInfo())) {
			ipInfo = JSON.parseObject(po.getUserIpInfo(), IpInfo.class);
		}
	}

	public IpInfo getIpInfo() {
		return ipInfo;
	}

	public void setIpInfo(IpInfo ipInfo) {
		this.ipInfo = ipInfo;
	}

}
