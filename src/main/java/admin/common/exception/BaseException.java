package admin.common.exception;


/**
 * 基础异常
 * @date: 2016年10月12日 上午10:40:11
 */
@SuppressWarnings("serial")
public class BaseException extends RuntimeException {
	
	protected int code;
	@SuppressWarnings("rawtypes")
	protected Class clazz;
	protected String className;

	public BaseException() {
		super();
	}

	public BaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BaseException(String arg0) {
		super(arg0);
	}
	
	public BaseException(Throwable arg0) {
		super(arg0);
	}
	
	@SuppressWarnings("rawtypes")
	public BaseException(String msg,Class clazz){
		super(msg);
		this.clazz = clazz;
		this.className = clazz.getName();
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
