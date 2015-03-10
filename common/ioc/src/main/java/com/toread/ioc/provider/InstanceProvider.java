package com.toread.ioc.provider;

import java.lang.annotation.Annotation;

/**
 * Created by toreadli on 2015-03-06.
 */
public interface InstanceProvider {
    <T> T getInstance(Class<T> instanceClass);

    <T> T getInstance(Class<T> instanceClass,String instanceName);

    <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType);
}
