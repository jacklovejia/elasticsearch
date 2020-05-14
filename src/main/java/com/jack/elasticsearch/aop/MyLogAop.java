package com.jack.elasticsearch.aop;

import com.jack.elasticsearch.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Slf4j
public class MyLogAop {
    @Pointcut("@annotation(com.jack.elasticsearch.aop.MyLog)")
    public void myLogPoint(){}

    @Before("myLogPoint()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("前端请求接口 : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("客户端IP : " + request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        Thread thread = Thread.currentThread();
        log.info("当前线程ID:"+thread.getId()+","+thread.toString());
        Map<String, Object> map = new HashMap< >();
        map.put("1","2");
        ThreadLocalUtils.setUserInfoLocalParam(map);
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            log.info("name:{}, value:{}", name, request.getParameter(name));
        }
    }
}
