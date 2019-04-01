package im.heart.frame.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.frame.entity.FrameDictItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * 数据字典子表包装类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FrameDictItemVO extends FrameDictItem {

	@JSONField(serialize=false)
	private String itemDesc;
	public FrameDictItemVO(FrameDictItem po) {
		BeanUtils.copyProperties(po, this);
	}

}
