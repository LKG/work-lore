package im.heart.usercore.vo;

import im.heart.usercore.entity.FrameOrg;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author gg
 * 机构关联VO
 */
@Data
public class FrameOrgRelateVO extends FrameOrg {

	private Boolean isDefault=Boolean.FALSE;
	
	private Boolean isRelated=Boolean.FALSE;
	
	public FrameOrgRelateVO(FrameOrg po){
		BeanUtils.copyProperties(po, this);
	}

}
