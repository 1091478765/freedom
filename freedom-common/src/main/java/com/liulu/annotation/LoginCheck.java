package com.liulu.annotation;

import java.lang.annotation.*;

/**
 * Created by 刘璐 on 2018/8/30.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginCheck {
}

