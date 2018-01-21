package com.example.xs.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RPC  增加客户端引用服务的注解
 * 这里只是为了简单，只实现通过注解方式获取远程接口的实例。先定义引用注解,isSync属性是为了支持同步接口以及异步接口，默认为同步接口。
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target(ElementType.FIELD)
public @interface RpcReference {
	boolean isSync() default true;
	
}