package im.heart.core.plugins.forbidden;

import im.heart.core.utils.StringUtilsEx;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 *
 * @author gg
 * @desc 敏感词过滤
 */
public class ForbiddenValidator implements	ConstraintValidator<Forbidden, String> {
	private String[] forbiddenWords = { "admin" };

	@Override
	public void initialize(Forbidden constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		 if(StringUtilsEx.isBlank(value)) {  
	            return true;  
	        }  

	        for(String word : forbiddenWords) {  
	            if(value.contains(word)) {  
	                return false;
	            }  
	        }  
	        return true;  
	}

}
