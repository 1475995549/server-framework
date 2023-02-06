package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.enums.MyEnum;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: MyEnumUtil
 * date: 2019/9/9 7:28 PM
 * version: 1.0
 * author: suzui
 */
public class MyEnumUtil {
    
    public static <T extends MyEnum<T>> T convert(Integer code, Class<T> enumClass) {
        return code == null ? null :
                Arrays.stream(enumClass.getEnumConstants()).filter(c -> c.code() == code).findFirst().orElse(null);
    }
    
    public static <T extends MyEnum<T>> T convert(String intro, Class<T> enumClass) {
        return StringUtils.isBlank(intro) ? null :
                Arrays.stream(enumClass.getEnumConstants()).filter(c -> c.intro().equalsIgnoreCase(intro)).findFirst().orElse(null);
    }
    
    public static List<Class<?>> getAllEnumClass() throws ClassNotFoundException {
        Class superClass = MyEnum.class;
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : getClasses(superClass)) {
            if (superClass.isAssignableFrom(c) && !superClass.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }
    
    public static List<Class<?>> getClasses(Class<?> cls) throws ClassNotFoundException {
        String pk = cls.getPackage().getName().replace(".base", "");
        String path = pk.replace(".", "/");
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL url = classloader.getResource(path);
        return getClasses(new File(url.getFile()), pk);
    }
    
    private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                classes.addAll(getClasses(file, pk + "." + file.getName()));
            }
            String fileName = file.getName();
            if (fileName.endsWith(".class")) {
                classes.add(Class.forName(pk + "." + fileName.substring(0, fileName.length() - 6)));
            }
        }
        return classes;
    }
    
}
