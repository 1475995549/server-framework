package cn.zhihan.framework.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * description: MyApplication
 * date: 2020/6/10 2:00 PM
 * version: 1.0
 * author: suzui
 */
@Slf4j
public class MyApplication {

    protected static void startUp(Class clazz, String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(clazz, args);
        Environment env = applicationContext.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Doc: \t\thttp://{}:{}/doc.html\n\t" +
                        "active: \t{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port")+ env.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress() ,
                env.getProperty("server.port")+ env.getProperty("server.servlet.context-path"),
                InetAddress.getLocalHost().getHostAddress() ,
                env.getProperty("server.port")+ env.getProperty("server.servlet.context-path"),
                env.getProperty("spring.profiles.active"));
    }
}
