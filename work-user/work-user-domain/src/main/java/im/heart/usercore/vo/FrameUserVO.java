package im.heart.usercore.vo;

import im.heart.core.utils.StringUtilsEx;
import im.heart.core.validator.ValidatorUtils;
import im.heart.usercore.entity.FrameUser;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author gg
 * 用户VO
 */
public class FrameUserVO extends FrameUser {


	public FrameUserVO(FrameUser po){
		this(po,true);
	}
	public FrameUserVO(FrameUser po, boolean lazy){
		BeanUtils.copyProperties(po, this);
		String userEmail=po.getUserEmail();
		String userPhone=po.getUserPhone();
		String realName=po.getRealName();
		String idCard=po.getIdCard();
		if(ValidatorUtils.isPhone(userPhone)){
			userPhone=userPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"); 
			setUserPhone(userPhone);
		}
		if(ValidatorUtils.isEmail(userEmail)){
			userEmail=userEmail.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
			setUserEmail(userEmail);
		}
		if(StringUtilsEx.isNotBlank(realName)){
			realName=StringUtilsEx.maskingStrBetween(1,realName.length(),realName,"**");
			setRealName(realName);
		}
		if(StringUtilsEx.isNotBlank(idCard)){
			idCard=StringUtilsEx.maskingStrBetween(6,idCard.length()-3,idCard,"********");
			setIdCard(idCard);
		}
	}
}
