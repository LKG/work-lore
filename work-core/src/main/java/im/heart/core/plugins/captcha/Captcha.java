package im.heart.core.plugins.captcha;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 
 * @功能说明：验证码接口
 * @作者 LKG
 */
public interface Captcha extends Serializable {

    String getQuestion();

    BufferedImage getChallenge();
    
	Boolean validateResponse(String response);

	Boolean hasGetChalengeBeenCalled();
}
