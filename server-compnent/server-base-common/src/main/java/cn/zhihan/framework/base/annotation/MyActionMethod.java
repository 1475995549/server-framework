package cn.zhihan.framework.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface MyActionMethod {
    
    String name() default "";//接口名称
    
    String param() default "";//接口参数形式1 所需参数 取自vo field
    
    String except() default "";//接口参数形式2 不需要参数 其它vo参数都需要
    
    String required() default "";//接口参数形式2 其它vo参数中 必填参数
    
    boolean repeat() default true;//是否允许重复提交
    
    boolean withcode() default true;//文档是否显示code
    
    boolean withenum() default true;//文档是否显示enum
    
    boolean withipfilter() default false;//是否开启ip白名单
    
    String ipwhitelist() default "";//ip白名单列表
    
    //Class<? extends DataVo>[] clazz() default {};
    
}
