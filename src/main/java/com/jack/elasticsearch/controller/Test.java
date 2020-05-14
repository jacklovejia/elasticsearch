package com.jack.elasticsearch.controller;

import com.jack.elasticsearch.aop.MyLog;
import com.jack.elasticsearch.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test implements BeanFactoryAware , BeanNameAware, BeanPostProcessor {
    @Autowired

//    @Qualifier(value = "ser")
    private service serveiceAImpl;

    @PostConstruct
    public void init(){
        System.out.println("初始化");
        serveiceAImpl.a();
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @MyLog
    @RequestMapping("/name/{name}")
    public String test(@PathVariable String name){
        Thread thread = Thread.currentThread();
        Map<String, Object> userInfoLocalParam = ThreadLocalUtils.getUserInfoLocalParam();
        log.info("取出来:"+userInfoLocalParam.get("1"));
        log.info("当前线程ID:"+thread.getId()+","+thread.toString());
        return "你好"+name+",么么哒";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("factory:" + beanFactory.getBean("test"));
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("name:"+s);
    }
}
