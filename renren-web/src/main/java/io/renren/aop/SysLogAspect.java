package io.renren.aop;

import com.alibaba.fastjson.JSON;
import io.renren.annotation.SysLog;
import io.renren.entity.SysLogEntity;
import io.renren.service.SysLogService;
import io.renren.utils.HttpContextUtils;
import io.renren.utils.IPUtils;
import io.renren.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;


/**
 * 系统日志，切面处理类
 * 
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(io.renren.annotation.SysLog)")
	public void logPointCut() {
        System.out.println("啦啦啦奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥奥");
    }


	//匹配该包下的所有类
	//io.renren.controller
	//    @Before( "execution(* org.service.imp.*.*(..))")
	//@Before( "logPointCut()"),controller层，通过注解的操作日志控制
	//service层的控制
//	@Before( "execution(* io.renren.service.impl.*(..))")
//	public void authority()
//	{
//		System.out.println("模拟执行权限检查") ;
//	}
//
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();

		Method method = signature.getMethod();



		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);

		SysLog sysLog1 = method.getAnnotation(SysLog.class);


		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		String params = JSON.toJSONString(args[0]);
		sysLog.setParams(params);

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户名
		String username = ShiroUtils.getUserEntity().getUsername();
		sysLog.setUsername(username);

		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}

	
}
