package org.suger.winter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ComponentScan 用于标示需要加载进容器中的class path
 * Created on 2021-02-03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Componet {

    //标示Bean的名称
    String value() default "";
}
