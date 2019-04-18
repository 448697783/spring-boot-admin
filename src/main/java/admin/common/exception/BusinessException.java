package admin.common.exception;
import admin.common.baseenum.BaseEnum.BusinessExceptionEnum;

/**
 * 业务异常
 * @date: 2016年10月12日 上午10:40:11
 */
@SuppressWarnings("serial")
public class BusinessException extends BaseException {
	
	protected Exception orgException;//原始异常

	public BusinessException() {
		super();
	}

	public BusinessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BusinessException(String arg0) {
		super(arg0);
	}
	
	public BusinessException(Throwable arg0) {
		super(arg0);
	}
	
	public BusinessException(BusinessExceptionEnum businessExceptionEnum){
		super(businessExceptionEnum.msg());
		this.code = businessExceptionEnum.Code();
	}
	
	public BusinessException(BusinessExceptionEnum businessExceptionEnum,Exception orgException){
		super(businessExceptionEnum.msg());
		this.code = businessExceptionEnum.Code();
		this.orgException = orgException;
	}
	
	@SuppressWarnings("rawtypes")
	public BusinessException(BusinessExceptionEnum businessExceptionEnum,Class clazz){
		super(businessExceptionEnum.msg());
		this.code = businessExceptionEnum.Code();
		this.clazz = clazz;
		this.className = clazz.getName();
	}
	
	@SuppressWarnings("rawtypes")
	public BusinessException(BusinessExceptionEnum businessExceptionEnum,Class clazz,Exception orgException){
		super(businessExceptionEnum.msg());
		this.code = businessExceptionEnum.Code();
		this.clazz = clazz;
		this.className = clazz.getName();
		this.orgException = orgException;
	}
	
	@SuppressWarnings("rawtypes")
	public BusinessException(String msg,Class clazz){
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

	public Exception getOrgException() {
		return orgException;
	}

	public void setOrgException(Exception orgException) {
		this.orgException = orgException;
	}
}
