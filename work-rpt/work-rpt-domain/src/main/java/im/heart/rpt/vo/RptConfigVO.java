package im.heart.rpt.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.rpt.entity.RptConfig;
import org.springframework.beans.BeanUtils;

public class RptConfigVO extends RptConfig {

	@JSONField(serialize = false)
	private String rptCriCont;
	public RptConfigVO(RptConfig po) {
		BeanUtils.copyProperties(po, this);
	}

}
