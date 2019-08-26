package im.heart.usercore.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author gg
 * @Desc : 资源权限多选模型
 */
@Data
public class ResourceCheckModel {
	private BigInteger resourceId;
	private String resourceCode;
	private List<BigInteger> permissionIds= Lists.newArrayList();
}
