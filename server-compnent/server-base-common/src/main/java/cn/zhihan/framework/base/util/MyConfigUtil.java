package cn.zhihan.framework.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description: MyConfigUtil
 * date: 2020/7/9 6:06 下午
 * version: 1.0
 * author: suzui
 */
@Component
public class MyConfigUtil {
    
    @Autowired
    private ConfigurableApplicationContext _configurableApplicationContext;
    
    private static ConfigurableApplicationContext configurableApplicationContext;
    
    
    @PostConstruct
    public void beforeInit() {
        configurableApplicationContext = _configurableApplicationContext;
    }
    
    /**
     * 获取配置文件配置项的值
     */
    public static String getProperty(String key) {
        return configurableApplicationContext.getEnvironment().getProperty(key);
    }
    
    /**
     * 获取配置文件配置项的值(默认值)
     */
    public static String getProperty(String key, String defaultValue) {
        return configurableApplicationContext.getEnvironment().getProperty(key, defaultValue);
    }
    
}
