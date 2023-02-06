package cn.zhihan.framework.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;

/**
 * description: SpringContextUtil
 * date: 2020/5/25 11:14 上午
 * version: 1.0
 * author: suzui
 */
@Component
public class MyContextUtil implements ApplicationContextAware {
    
    private static ApplicationContext context = null;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyContextUtil.context = applicationContext;
    }
    
    /**
     * 获取applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }
    
    /**
     * 通过name获取Bean.
     */
    public static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }
    
    /**
     * 通过class获取Bean.
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
    
    /**
     * 国际化使用
     */
    public static String getMessage(String key) {
        return getApplicationContext().getMessage(key, null, Locale.getDefault());
    }
    
    /**
     * 获取配置文件配置项的值
     */
    public static String getEnvironmentProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }
    
    /**
     * 获取配置文件配置项的值(默认值)
     */
    public static String getEnvironmentProperty(String key, String defaultValue) {
        return getApplicationContext().getEnvironment().getProperty(key, defaultValue);
    }
    
    /**
     * 获取资源文件(默认值)
     */
    public static File getResource(String filePath) {
        try {
            return ResourceUtils.getFile("classpath:" + filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 获取当前环境
     */
    public static String getActiveProfile() {
        return context.getEnvironment().getActiveProfiles()[0];
    }
    
    /**
     * 正式环境
     */
    public static boolean isProd() {
        return "prod".equals(getActiveProfile());
    }
    
    /**
     * test环境
     */
    public static boolean isTest() {
        return "test".equals(getActiveProfile());
    }
    
    /**
     * dev环境
     */
    public static boolean isDev() {
        return "dev".equals(getActiveProfile());
    }
}