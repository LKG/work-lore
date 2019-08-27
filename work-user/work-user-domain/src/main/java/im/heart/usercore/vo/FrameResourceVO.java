package im.heart.usercore.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import im.heart.usercore.entity.FramePermission;
import im.heart.usercore.entity.FrameResource;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gg
 * 资源VO
 */
@Data
public class FrameResourceVO extends FrameResource {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5560379410382708216L;
	private Boolean ischecked = Boolean.FALSE;
	@JSONField(serialize = false)
	private Date modifyTime;
	@JSONField (serialize=false)
	private String resourceDesc;
	
	List<FramePermissionVO> permissions= Lists.newArrayList();
	
	
	public FrameResourceVO(){
	}
	public FrameResourceVO(FrameResource po){
		BeanUtils.copyProperties(po, this);
	}

	public FrameResourceVO(FrameResource po, Map<BigInteger, Set<BigInteger>> roleResourceMap){
		this(po);
		BigInteger key = po.getResourceId();
		if(StringUtils.isNotBlank(po.getPermissionStr())){
			List<FramePermission> permissions=JSON.parseArray(po.getPermissionStr(), FramePermission.class);
			for(FramePermission permission:permissions){
				FramePermissionVO vo = new FramePermissionVO(permission);
				if(roleResourceMap.containsKey(key)){
					setIschecked(Boolean.TRUE);
					Set<BigInteger> roleResourcePermissions=roleResourceMap.get(key);
					if(roleResourcePermissions.contains(permission.getPermissionId())){
						vo.setIsChecked(Boolean.TRUE);
					}
				}
				addPermission(vo);
			}
		}
	}

	public void addPermission(FramePermissionVO permissionVO) {
		this.permissions.add(permissionVO);
	}
}
