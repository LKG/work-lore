package im.heart.frame.validator;

import im.heart.frame.entity.FrameDict;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * @author: gg
 * @desc :数据校验
 */
@Component
public class FrameDictValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return FrameDict.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
