package com.haoyong.admin.aspect;

import com.alibaba.fastjson.JSON;
import com.haoyong.admin.annotation.MyLogAnno;
import com.haoyong.admin.log.domain.ExcLog;
import com.haoyong.admin.log.domain.OperLog;
import com.haoyong.admin.log.repository.SysExcLogRepository;
import com.haoyong.admin.log.repository.SysOperLogRepository;
import com.haoyong.admin.secrity.security.TokenManager;
import com.haoyong.admin.util.IpAddrUtil;
import com.haoyong.admin.util.UUIDUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: admin
 * @description:
 * @author: ywd
 * @contact:1371690483@qq.com
 * @create: 2020-12-21 17:09
 **/
@Aspect
@Component
@Slf4j
public class MyLogAspect {
    @Autowired
    SysOperLogRepository sysOperLogRepository;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    SysExcLogRepository sysExcLogRepository;

    @Pointcut("@annotation(com.haoyong.admin.annotation.MyLogAnno)")
    public  void myLog(){}

    @Pointcut("execution(* com.haoyong.admin.sys.controller..*.*(..))")
    public void myExcLog(){}

    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行， 如果连接点抛出异常，则不会执行
     * @param joinPoint 切入点
     * @param keys      返回结果
     */
    @AfterReturning(value = "myLog()",returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request= (HttpServletRequest) attributes
                                        .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperLog operLog = new OperLog();

        try {
            operLog.setId(UUIDUtil.getUUID());
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            MyLogAnno logAnno = method.getAnnotation(MyLogAnno.class);
            if (logAnno != null) {
                operLog.setOperModule(logAnno.operModule());//操作模块
                operLog.setOperType(logAnno.operType());//操作类型
                operLog.setOperDesc(logAnno.operDesc());//操作描述
            }
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            methodName = className+"."+methodName;
            operLog.setOperMethod(methodName);//请求方法
            Object[] args = joinPoint.getArgs();//获取行参
            String username = "";
            String userid = "";
            for (int i = 0; i < args.length; i++) {
                if(args[i] instanceof Authentication) {
                    Authentication authentication = (Authentication) args[i];
                    username = authentication.getName();
                    String credentials = (String) authentication.getCredentials();
                    Claims claims = tokenManager.parseJWT(credentials);
                    userid = claims.getId();
                    break;
                }
            }
            operLog.setOperUserName(username);
            operLog.setOperUserId(userid);
            //请求参数
            Map<String, String> stringMap = converMap(request.getParameterMap());

            //将参数所在的数组转成json
            String params = JSON.toJSONString(stringMap);
            operLog.setOperRequParam(params);
            operLog.setOperRespParam(JSON.toJSONString(keys));
            operLog.setOperUri(request.getRequestURI());
            operLog.setOperIp(IpAddrUtil.getIpAddress(request));

            sysOperLogRepository.saveAndFlush(operLog);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     *  转换request 请求参数
     */

    public Map<String,String> converMap(Map<String,String[]> paramap) {
        HashMap<String, String> map = new HashMap<>();
        for (String key : paramap.keySet()) {

            map.put(key,paramap.get(key)[0]);
        }
        return map;
    }

    /**
     *异常返回通知，用于拦截异常日志信息，连接点抛出异常后执行
     *
     */
    @AfterThrowing(pointcut = "myExcLog()",throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint,Throwable e) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request= (HttpServletRequest) attributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ExcLog excLog = new ExcLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();

            excLog.setId(UUIDUtil.getUUID());
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            methodName = className+"."+methodName;
            //请求参数
            Map<String, String> stringMap = converMap(request.getParameterMap());

            //将参数所在的数组转成json
            String params = JSON.toJSONString(stringMap);

            excLog.setExcRequParam(params);
            excLog.setOperMethod(methodName);
            excLog.setExcName(e.getClass().getName());
            excLog.setExcMessage(stackTraceToString(e.getClass().getName(),
                    e.getMessage(),e.getStackTrace()));
            excLog.setOperUri(request.getRequestURI());
            excLog.setOperIp(IpAddrUtil.getIpAddress(request));
            Object[] args = joinPoint.getArgs();//获取行参
            String username = "";
            String userid = "";
            for (int i = 0; i < args.length; i++) {
                if(args[i] instanceof Authentication) {
                    Authentication authentication = (Authentication) args[i];
                    username = authentication.getName();
                    String credentials = (String) authentication.getCredentials();
                    Claims claims = tokenManager.parseJWT(credentials);
                    userid = claims.getId();
                    break;
                }
            }
            excLog.setOperUserName(username);
            excLog.setOperUserId(userid);
            sysExcLogRepository.saveAndFlush(excLog);
        }catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     *
     *转换异常信息为字符串
     */
    public String stackTraceToString(String exceptionName,
                                     String exceptionMessage,
                                     StackTraceElement[] elements) {
        StringBuffer buffer = new StringBuffer();
        for (StackTraceElement element : elements) {
            buffer.append(element+"\n");
        }
        return exceptionName + ":" + exceptionMessage + "\n\t" +buffer.toString();
    }


}
