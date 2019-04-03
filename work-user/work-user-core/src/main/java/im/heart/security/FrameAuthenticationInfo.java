package im.heart.security;

import im.heart.usercore.entity.FrameUser;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

import java.math.BigInteger;

/**
 * 
 * @author gg
 * @desc 扩展认证信息信息认证
 */
public class FrameAuthenticationInfo extends SimpleAuthenticationInfo{
	public FrameAuthenticationInfo(FrameUser user, String passWord,
                                   ByteSource bytes, String name, BigInteger userId) {
		super(user,passWord,bytes,name);
	}
	public FrameAuthenticationInfo(FrameUser user, String passWord,
                                   ByteSource bytes, String name) {
		super(user,passWord,bytes,name);
	}
	

}
