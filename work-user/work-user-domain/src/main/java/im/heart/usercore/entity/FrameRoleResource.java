package im.heart.usercore.entity;

import com.alibaba.fastjson.annotation.JSONField;
import im.heart.core.entity.AbstractEntity;
import im.heart.core.utils.StringUtilsEx;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author gg
 * 角色资源关联表
 */
@Entity()
@Table(name = "dic_frame_role_resource")
@DynamicUpdate()
@DynamicInsert()
@Data
public class FrameRoleResource implements AbstractEntity<BigInteger> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6778091815595393868L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JSONField(serialize = false)
	@Column(length = 20, name = "RELATE_ID", nullable = false, unique = true, updatable = false)
	private BigInteger relateId;

	@NotNull
	@Column(length = 20, name = "RESOURCE_ID", nullable = false, updatable = false)
	private BigInteger resourceId;
	
	@NotBlank
	@Column(length = 32, name = "RESOURCE_CODE", nullable = false, updatable = false)
	private String resourceCode;
	
	@NotNull
	@Column(length = 20, name = "ROLE_ID", nullable = false, updatable = false)
	private BigInteger roleId;
	
	@Length(max = 32)
	@Column(length = 32, name = "ROLE_CODE", nullable = false, updatable = false)
	private String roleCode;

	/**
	 * //权限ids
	 */
	@NotBlank
	@Length(max=512)
	@Column(length=512, name = "PERMISSION_IDS" )
	private String permissionIds;
	
	@JSONField(serialize = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATE_TIME", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@PrePersist
	protected void onCreate() {
		createTime = new Date();
	}
	@JSONField(serialize = false)
	@Transient
	Set<BigInteger> permissions=new HashSet<BigInteger>();


	public Set<BigInteger> getPermissions() {
		if(StringUtilsEx.isNotBlank(permissionIds)){
			String[] ids = StringUtilsEx.split(permissionIds,",");
			for(String permissionId:ids){
				this.permissions.add(new BigInteger(permissionId));
			}
		}
		return permissions;
	}
}
