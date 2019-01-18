package admin.common.exception;

import admin.common.baseenum.BaseEnum.CheckExceptionEnum;

/**
 * 业务异常
 * @date: 2016年10月12日 上午10:40:11
 */
@SuppressWarnings("serial")
public class AutoRetryException extends BaseException {
	

	public AutoRetryException(int code,String msg){
		super(msg);
		this.code = code;
	}
	public AutoRetryException(){
		super("触发乐观锁异常");
		this.code = CheckExceptionEnum.CHECK_EXCEPTION.getCode();
	}
	
	@Override
	public Throwable fillInStackTrace() {
		return null;
	}
	
	public int getCode() {
		return code;
	}

	@SuppressWarnings("rawtypes")
	public Class getClazz() {
		return clazz;
	}

	public String getClassName() {
		return className;
	}

}
