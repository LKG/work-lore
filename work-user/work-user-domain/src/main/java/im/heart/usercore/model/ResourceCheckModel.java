package im.heart.usercore.model;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ResourceCheckModel {
	private BigInteger resourceId;
	private String resourceCode;
	private List<BigInteger> permissionIds= Lists.newArrayList();
}
