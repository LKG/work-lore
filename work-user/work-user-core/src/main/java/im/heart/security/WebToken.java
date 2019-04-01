package im.heart.security;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 
 * @author gg
 * @desc  登录成功返回model
 */
@Data
public class WebToken {

	public WebToken() {
	}
	public WebToken(String token,long expires) {
		this.token = token;
		this.expires = expires;
	}
	@JSONField(name="refresh_token")
	private String refreshToken;

	@JSONField(name="access_token")
	private String token;
	
	@JSONField(name="expires_in")
	private long expires;

	@JSONField(name="scope")
	private String scope;
	
	@JSONField(name="uid")
	private String uid;

}
