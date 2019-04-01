package im.heart.frame.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.enums.Status;
import im.heart.frame.entity.FrameArea;
import org.springframework.beans.BeanUtils;

/**
 * 
 */
public class FrameAreaVO extends FrameArea {
	public FrameAreaVO(FrameArea po){
		BeanUtils.copyProperties(po, this);
	}
	@JSONField (serialize=false)
	private Status status;
}
