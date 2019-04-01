package im.heart.security;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc 扩展密码验证token 添加验证码校验
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountToken extends UsernamePasswordToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6052412220330012704L;
	private BigInteger userId;
	private String tokenType;
    private String validateCode; 

	public AccountToken(String username, String digestPassword,
			boolean rememberMe, String host) {
		super(username, digestPassword, rememberMe, host);
	}
	public AccountToken(String username, String digestPassword,
			boolean rememberMe, String host, String validateCode) {
		super(username, digestPassword, rememberMe, host);
		this.validateCode=validateCode;
	}
	
	public AccountToken(String username, String digestPassword,
			boolean rememberMe, String host, String validateCode,BigInteger userId,String tokenType) {
		super(username, digestPassword, rememberMe, host);
		this.validateCode=validateCode;
		this.tokenType=tokenType;
		this.userId=userId;	
		
	}
}
