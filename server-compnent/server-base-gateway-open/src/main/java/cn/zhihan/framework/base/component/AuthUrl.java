package cn.zhihan.framework.base.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * description: AuthUrlConfig
 * date: 2021/9/10 11:09 上午
 * version: 1.0
 * author: suzui
 */
@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "authurl")
public class AuthUrl {
    
    private List<String> ignore;
    
    private List<String> basic;
    
}
