package cn.zhihan.framework.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description: MyConfig
 * date: 2020/5/13 2:53 下午
 * version: 1.0
 * author: suzui
 */

@Component
@Data
public class MyConfig {
    
    @Value("${myconfig.model-path:}")
    private String modelPath;
    @Value("${myconfig.entity-path:}")
    private String entityPath;
    @Value("${myconfig.mapper-path:}")
    private String mapperPath;
    @Value("${myconfig.service-path:}")
    private String servicePath;
    @Value("${myconfig.web-path:}")
    private String webPath;
    @Value("${myconfig.permit-path:}")
    private String permitPath;
    @Value("${myconfig.cors:}")
    private Boolean cors;
    
    @Value("${mybatis.config-location:}")
    private String mybatisConfigLocation;
    @Value("${mybatis.mapper-locations:}")
    private String mybatisMapperLocation;
    @Value("${mybatis.type-aliases-package:}")
    private String mybatisTypeAliasesPackage;
    
    @Value("${spring.datasource.driver-class-name:}")
    private String databaseDriver;
    @Value("${spring.datasource.url:}")
    private String databaseUrl;
    @Value("${spring.datasource.username:}")
    private String databaseUsername;
    @Value("${spring.datasource.password:}")
    private String databasePassword;
    
}
