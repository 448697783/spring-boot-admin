package admin.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import admin.common.aspect.IAspectCode;
import admin.common.utils.HttpContextUtils;
import admin.common.utils.R;

/**
 * 
 * @ClassName: RRExceptionHandler 
 * @Description: ExceptionHandlerExceptionResolver
 * @author honghuiwang 
 * @date 2017年9月2日 下午8:15:18 
 *
 */
@RestControllerAdvice
public class ShiroExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}
}
