package admin.common.exception;


import com.fasterxml.jackson.core.JsonParseException;
//import com.jd.jsf.gd.error.NoAliveProviderException;
//import com.jd.jsf.gd.error.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import admin.common.aspect.ContextLog;
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
public class BaseExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(CheckException.class)
	public R handleCheckException(CheckException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		ContextLog.error(r.toString(), e);
		return r;
	}
	/**
	 * 自定义异常
	 */
//	@ExceptionHandler(RpcException.class)
//	public R handleRpcException(RpcException e){
//		R r = new R();
//		r.put("code", e.getErrorCode());
//		r.put("msg", e.getMessage());
//		ContextLog.error(r.toString(), e);
//		return r;
//	}

//	@ExceptionHandler(NoAliveProviderException.class)
//	public R handleNoAliveProviderException(NoAliveProviderException e){
//		R r = new R();
//		r.put("code", "500");
//		r.put("msg", e.toString());
//		ContextLog.error(r.toString(), e);
//		return r;
//	}
	@ExceptionHandler(JsonParseException.class)
	public R handleJsonParseException(JsonParseException e){
		R r = new R();
		r.put("code","500");
		r.put("msg", "json格式错误"+e.toString());
		ContextLog.error(r.toString(), e);
		return r;
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		R r = new R();
		r.put("code", "500");
		r.put("msg", e.toString());
		ContextLog.error(r.toString(), e);
		return r;
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
		R r = new R();
		r.put("code", "500");
		r.put("msg", e.getMessage());
		ContextLog.error(r.toString(), e);
		return r;
	}
	
	@ExceptionHandler(BindException.class)
	public R handleBindException(Exception e){
		R r = new R();
		r.put("code", "500");
		r.put("msg", "检查参数格式是否正确");
		ContextLog.error(r.toString(), e);
		return r;
	}
	
	@ExceptionHandler(BusinessException.class)
	public R handleBusinessException(BusinessException e){
		R r = new R();
		r.put("code", "500");
		r.put("msg", e.getMessage());
		ContextLog.error(r.toString(), e);
		return r;
	}
}
