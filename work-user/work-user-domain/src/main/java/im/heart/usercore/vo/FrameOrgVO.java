package im.heart.usercore.vo;

import im.heart.usercore.entity.FrameOrg;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author gg
 * 机构VO
 */
@Data
public class FrameOrgVO extends FrameOrg {

	private Boolean isRelated = Boolean.FALSE;
	private Boolean isDefault=Boolean.FALSE;
	public FrameOrgVO(FrameOrg po){
		BeanUtils.copyProperties(po, this);
	}

	
}
