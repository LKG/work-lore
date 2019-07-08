package im.heart.core.support.handler;

import com.google.common.collect.Maps;
import im.heart.core.CommonConst.RequestResult;
import im.heart.core.utils.BaseUtils;
import im.heart.core.validator.BeanValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author: gg
 * 统一异常处理
 */
@ControllerAdvice
public class RestExceptionHandler{
	protected static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

	protected Map<String, Object> error(HttpServletRequest request,HttpStatus httpStatus) {
		Map<String, Object> errorMap = Maps.newHashMap();
		errorMap.put(RequestResult.HTTP_STATUS, httpStatus.value());
		errorMap.put(RequestResult.SUCCESS, false);
		errorMap.put(RequestResult.REQUEST_URL, request.getRequestURL());
		return errorMap;
	}
	protected Map<String, Object> error(HttpServletRequest request, Exception ex) {
		Map<String, Object> errorMap = this.error(request,HttpStatus.INTERNAL_SERVER_ERROR);
		errorMap.put(RequestResult.EXCEPTION, ex.getMessage());
		return errorMap;
	}
	protected ModelAndView chooseView(HttpServletRequest request,Map<String, Object> errorMap){
		if(BaseUtils.isAjaxRequest(request)){
			return new ModelAndView(RequestResult.PAGE_ERROR, errorMap);
		};
		return new ModelAndView(RequestResult.PAGE_ERROR, errorMap);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ModelAndView handleDataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException e) {
		logger.error("操作数据库出现异常：字段重复、有外键关联等:", e);
		return this.chooseView(request,this.error(request, e));
	}


	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ModelAndView handleHttpMediaTypeNotSupportedException(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
		logger.error("handleHttpMediaTypeNotSupportedException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request, ex));
	}
	@ExceptionHandler(ServletException.class)
	public ModelAndView handleServletException(HttpServletRequest request, ServletException ex) {
		logger.error("handleServletException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request, ex));
	}
	/**
	 * 处理SQL 异常
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(SQLException.class)
	public ModelAndView handleSQLException(HttpServletRequest request, SQLException ex) {
		logger.error("handleSQLException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request, ex));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ModelAndView handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
		logger.error("handleHttpMessageNotReadableException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request, ex));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ModelAndView handleIllegalArgumentException(HttpServletRequest request, IllegalArgumentException ex) {
		logger.error("handleIllegalArgumentException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,this.error(request, ex));
	}
	/**
	 * ConstraintViolationException 校验 异常
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ModelAndView handleConstraintViolationException(HttpServletRequest request,
			ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		Map<String, String> messages = BeanValidators.extractPropertyAndMessage(constraintViolations);
		Map<String, Object> errorMap = this.error(request,ex);
		errorMap.put(RequestResult.MESSAGE, messages);
		logger.error("handleConstraintViolationException:"+ex.getStackTrace()[0].getMethodName(), ex);
		return this.chooseView(request,errorMap);
	}
}
