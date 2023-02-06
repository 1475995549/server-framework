package cn.zhihan.framework.base.config;

import cn.hutool.core.util.StrUtil;
import cn.zhihan.framework.base.component.AuthUrl;
import cn.zhihan.framework.base.constant.MyConstant;
import cn.zhihan.framework.base.util.MyRedisUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 鉴权管理器，用于判断是否有资源的访问权限
 * date: 2021/9/8 9:24 上午
 * version: 1.0
 * author: suzui
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    
    // ignore -> AuthIgnoreUrlsFilter -> AuthGlobalFilter
    // basic  -> AuthIgnoreUrlsFilter -> AuthorizationManager -> AuthGlobalFilter
    // access -> AuthIgnoreUrlsFilter -> AuthorizationManager -> RestAuthenticationEntryPoint|RestfulAccessDeniedHandler
    
    @Autowired
    AuthUrl authUrl;
    
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        //基础url放行
        if (authUrl.getBasic().contains("/**") || authUrl.getBasic().contains(uri.getPath())) {
            String token = authorizationContext.getExchange().getRequest().getHeaders().getFirst(MyConstant.TOKEN_HEADER);
            if (StrUtil.isEmpty(token) || !token.startsWith(MyConstant.TOKEN_HEAD)) {
                return Mono.just(new AuthorizationDecision(false));
            }
            return Mono.just(new AuthorizationDecision(true));
        }
        //TODO token个性化策略
        
        //从Redis中获取当前路径可访问角色列表
        Map<String, List<String>> resourceMap = MyRedisUtil.get(MyConstant.AUTH_RESOURCE_MAP);
        List<String> authorities = resourceMap == null ? null : resourceMap.get(uri.getPath());
        if (authorities == null) {
            authorities = Lists.list();
        }
        authorities = authorities.stream().map(i -> i = MyConstant.AUTH_AUTHORITY_PREFIX + i).collect(Collectors.toList());
        
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
    
}
