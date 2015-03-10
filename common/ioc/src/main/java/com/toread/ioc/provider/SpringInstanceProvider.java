package com.toread.ioc.provider;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;

/**
 * SpringInstanceProvider 具有以下两种功能
 * 1:初始化容器对象 并且提供对象的服务。
 * 2:设置容器对象 提供对象服务
 */
public class SpringInstanceProvider implements InstanceProvider{
    private ApplicationContext applicationContext;

    public SpringInstanceProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public SpringInstanceProvider(Class<?>... annotatedClasses) {
        applicationContext =  new AnnotationConfigApplicationContext(annotatedClasses);
    }

    public SpringInstanceProvider(String... locations) {
        applicationContext =  new ClassPathXmlApplicationContext(locations);
    }

    @Override
    public <T> T getInstance(Class<T> instanceClass) {
        return applicationContext.getBean(instanceClass);
    }

    @Override
    public <T> T getInstance(Class<T> instanceClass, String instanceName) {
        return applicationContext.getBean(instanceClass,instanceName);
    }

    @Override
    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        return applicationContext.getBean(beanType,annotationType);
    }
}
