package cn.zhihan.framework.base.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * description: MyClassUtil
 * date: 2019/9/10 10:08 AM
 * version: 1.0
 * author: suzui
 */
public class MyClassUtil {
    
    /**
     * 获取泛型类型
     */
    public static Class getEntityClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        Class<?> entityClass = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType t = (ParameterizedType) type;
                //得到泛型类型
                entityClass = (Class<?>) t.getActualTypeArguments()[0];
                break;
            }
        }
        return entityClass;
    }
    
    
    public static Field getField(Class clazz, String name) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            try {
                field = clazz.getSuperclass().getDeclaredField(name);
            } catch (NoSuchFieldException e1) {
            
            }
        }
        return field;
    }
}
