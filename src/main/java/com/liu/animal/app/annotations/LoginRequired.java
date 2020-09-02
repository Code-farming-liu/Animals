package com.liu.animal.app.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//方法上的注释
@Retention(RetentionPolicy.RUNTIME)//注解的生效范围
public @interface LoginRequired {
    //注解的方法
    boolean loginSuccess() default true;
}
