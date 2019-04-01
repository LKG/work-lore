package im.heart.usercore.model;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CheckModel {
	private BigInteger id;
	private Boolean isDefault=Boolean.FALSE;
}