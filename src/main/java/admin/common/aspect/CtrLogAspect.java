package admin.common.aspect;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import admin.common.annotation.CtrlLog;
import admin.common.baseenum.LogTypeEnum;
import admin.common.utils.IPUtils;
import admin.config.StatsdAutoConfiguration.StatsdUtil;


/**
 * 
 * @ClassName: CtrLogAspect 
 * @Description: 系统日志，切面处理类，主要用于controller方法上<br> 1、生成入口唯一参数 <br/> 2、记录参数  <br> 3、记录方法执行时间  <br> 4、可发送graphics记录
 * @author honghuiwang 
 * @date 2017年9月2日 上午12:01:47 
 *
 */
@Aspect
@Component
public class CtrLogAspect  {
	
	private Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired(required=false)
	private StatsdUtil statsdUtil;
	
	@Pointcut("@annotation(admin.common.annotation.CtrlLog)")
	public void logPointCut() { 
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable  {
		long beginTime = System.currentTimeMillis();
		Method method = ((MethodSignature) point.getSignature()).getMethod();
		CtrlLog syslog = method.getAnnotation(CtrlLog.class);
		String[] keys = syslog.keys();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();		
		int number = request.getAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue())!=null?Integer.valueOf(request.getAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue()).toString())+1:(request.getHeader(LogTypeEnum.WHICH_NUMBER_KEY.getValue())==null?1:Integer.valueOf(request.getHeader(LogTypeEnum.WHICH_NUMBER_KEY.getValue()))+1);
		request.setAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue(),number);
		String str = point.getTarget().getClass().getName()+"."+method.getName();
		for (int i = 0; i < keys.length; i++) {
			if(StringUtils.isEmpty(request.getParameter(keys[i]))) {
				continue;
			}
			str+="."+request.getParameter(keys[i]);
		}

		//记录请求前日志
		intiCtrLog(point,beginTime,number);
		//执行方法
		Object result = null;
		try {
			result = point.proceed();
			//执行时长(毫秒)
			long time = System.currentTimeMillis() - beginTime;
			if(statsdUtil!=null) {
				statsdUtil.sendAgent(str,(int) time);
			}
		}catch (Exception e) {
			long time = System.currentTimeMillis() - beginTime;
			if(statsdUtil!=null) {
				statsdUtil.sendAgent(str,(int) time);
			}
			throw e;
		}
		finally {
			long time = System.currentTimeMillis() - beginTime;
			//记录执行后返回日志
			resultCtrLog(point, result,time,number);
			
		}
		return result;
	}
	private void resultCtrLog(ProceedingJoinPoint joinPoint,Object result,long time,long number){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();	
		request.setAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue(),request.getAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue())!=null?Integer.valueOf(request.getAttribute(LogTypeEnum.WHICH_NUMBER_KEY.getValue()).toString())+1:1);
		log.info("请求唯一标识->{}-{} 返回结果->{} 方法执行时间->{}",request.getAttribute(LogTypeEnum.WHICH_MARK_KEY.getValue()),number,result, time);
	}
	
	private void intiCtrLog(ProceedingJoinPoint joinPoint,long beginTime, int number){
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		CtrlLog syslog = method.getAnnotation(CtrlLog.class);
		String methodInfo = syslog.value();
		Object[] args = joinPoint.getArgs();
		//获取request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();	
		String header = request.getHeader(LogTypeEnum.WHICH_MARK_KEY.getValue());
	    request.setAttribute(LogTypeEnum.WHICH_MARK_KEY.getValue(),request.getAttribute(LogTypeEnum.WHICH_MARK_KEY.getValue())!=null?request.getAttribute(LogTypeEnum.WHICH_MARK_KEY.getValue()).toString():(request.getHeader(LogTypeEnum.WHICH_MARK_KEY.getValue())==null?UUID.randomUUID().toString().replaceAll("-", ""):request.getHeader(LogTypeEnum.WHICH_MARK_KEY.getValue())));
		//设置IP地址
		log.info("请求唯一标识->{}-{} 方法描述->{} 请求参数->{} 方法位置->{} 请求IP地址->{}",request.getAttribute(LogTypeEnum.WHICH_MARK_KEY.getValue()),number,methodInfo, args,joinPoint,IPUtils.getIpAddr(request));
	}
}
