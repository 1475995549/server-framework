package cn.zhihan.framework.base.config;

import cn.hutool.core.util.ArrayUtil;
import cn.zhihan.framework.base.component.AuthUrl;
import cn.zhihan.framework.base.component.ExtensionErrorAttributes;
import cn.zhihan.framework.base.component.RestAuthenticationEntryPoint;
import cn.zhihan.framework.base.component.RestfulAccessDeniedHandler;
import cn.zhihan.framework.base.constant.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

/**
 * description: 资源服务器配置
 * date: 2021/9/8 9:24 上午
 * version: 1.0
 * author: suzui
 */
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    
    @Autowired
    AuthUrl authUrl;
    
    @Autowired
    AuthorizationManager authorizationManager;
    @Autowired
    RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    AuthIgnoreUrlsFilter ignoreUrlsRemoveJwtFilter;
    
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
        //对白名单路径，直接移除JWT请求头
        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.authorizeExchange()
                .pathMatchers( // 允许对于网站静态资源的无授权访问
                        "/", "/favicon.ico",
                        "/api/**/**.html", "/api/**/**.css", "/api/**/**.js", "/api/**/**.png", "/api/**/**.jpg", "/api/**/**.svg",
                        "/api/**/swagger-resources/**", "/api/**/v2/**", "/api/**/webjars/**", "/api/**/configuration/**"
                )
                .permitAll()
                .pathMatchers("/api/**/instances", "/api/**/instances/**", "/api/**/assets/**", "/api/**/actuator/**", "/api/**/applications", "/api/**/wallboard", "/api/**/journal")
                .permitAll()
                .pathMatchers(ArrayUtil.toArray(authUrl.getIgnore(), String.class)).permitAll()//白名单配置
                .anyExchange().access(authorizationManager)//鉴权管理器配置
                //.anyExchange().permitAll()
                .and().exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)//处理未授权
                .authenticationEntryPoint(restAuthenticationEntryPoint)//处理未认证
                .and().csrf().disable();
        return http.build();
    }
    
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(MyConstant.AUTH_AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(MyConstant.AUTH_AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
    
    /**
     * 自定义 ExtensionErrorAttributes Bean注册
     */
    @Bean
    public ExtensionErrorAttributes errorAttributes() {
        return new ExtensionErrorAttributes(false);
    }
    
    /**
     * 允许跨域
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);
        
        return new CorsWebFilter(source);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
