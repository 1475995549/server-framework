package cn.zhihan.framework.base.annotation;

import cn.zhihan.framework.base.enums.MyEnum;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface MyDataField {
    
    String name() default "";
    
    String demo() default "";
    
    String comment() default "";
    
    Class<? extends MyEnum>[] enums() default {};
    
    boolean enable() default true;
}
