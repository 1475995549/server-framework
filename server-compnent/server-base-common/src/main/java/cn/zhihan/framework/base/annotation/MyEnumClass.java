package cn.zhihan.framework.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface MyEnumClass {
    
    String name() default "";
    
    String comment() default "";
    
    boolean visible() default true;
    
}
