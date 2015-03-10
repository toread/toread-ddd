package com.toread.ioc.factory;

import com.toread.ioc.provider.InstanceProvider;

import java.lang.annotation.Annotation;

/**
 * 实例提供工厂
 */
public class InstanceFactory {
    private static InstanceProvider instanceProvider;

    public static InstanceProvider getInstanceProvider() {
        return instanceProvider;
    }

    public static void setInstanceProvider(InstanceProvider instanceProvider) {
        InstanceFactory.instanceProvider = instanceProvider;
    }

    private InstanceFactory(InstanceProvider instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public static <T> T getInstance(Class<T> instanceClass){
        return instanceProvider.getInstance(instanceClass);
    }

    public static <T> T getInstance(Class<T> instanceClass,String instanceName){
        return instanceProvider.getInstance(instanceClass,instanceName);
    }

    public static <T> T getInstance(Class<T> instanceClass, Class<? extends Annotation> annotationType){
        return instanceProvider.getInstance(instanceClass,annotationType);
    }
}
