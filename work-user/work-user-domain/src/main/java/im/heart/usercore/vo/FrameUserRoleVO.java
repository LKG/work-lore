package im.heart.usercore.vo;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.usercore.entity.FrameRole;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author gg
 * 用户角色VO
 */
@Data
public class FrameUserRoleVO extends FrameRole {


	@JSONField (serialize=false)
	private String roleDesc;
	
	@JSONField (serialize=false)
	private Date createTime;
	
	@JSONField (serialize=false)
	private Date modifyTime;
	
	private BigInteger userId;
	private String userName;
	
	private Boolean hasRole=Boolean.FALSE;
	public FrameUserRoleVO(FrameRole po){
		BeanUtils.copyProperties(po, this);
	}
}
