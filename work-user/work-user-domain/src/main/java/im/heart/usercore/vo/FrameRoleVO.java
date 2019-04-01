package im.heart.usercore.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import im.heart.usercore.entity.FrameRole;
import im.heart.usercore.entity.FrameRoleResource;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 *
 * @author gg
 * 角色VO
 */
@Data
public class FrameRoleVO extends FrameRole {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1248662944155577393L;
	@JSONField(serialize = false)
	private Date modifyTime;
	private List<FrameRoleResource> roleResources= Lists.newArrayList();
	public FrameRoleVO(FrameRole po){
		BeanUtils.copyProperties(po, this);
	}

	public void addRoleResources(FrameRoleResource frameRoleResource) {
		this.roleResources.add(frameRoleResource);
	}
	
}
