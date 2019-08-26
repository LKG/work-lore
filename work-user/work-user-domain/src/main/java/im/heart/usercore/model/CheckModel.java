package im.heart.usercore.model;

import lombok.Data;

import java.math.BigInteger;

/**
 *
 * @author gg
 * @Desc : 多选模型
 */
@Data
public class CheckModel {
	private BigInteger id;
	private Boolean isDefault=Boolean.FALSE;
}