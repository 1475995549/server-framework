package cn.zhihan.framework.base.config;

import cn.zhihan.framework.base.component.AuthUrl;
import cn.zhihan.framework.base.constant.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * description: 白名单路径访问时需要移除JWT请求头
 * date: 2021/9/8 9:24 上午
 * version: 1.0
 * author: suzui
 */
@Component
public class AuthIgnoreUrlsFilter implements WebFilter {
    
    @Autowired
    AuthUrl authUrl;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        //白名单路径移除JWT请求头
        for (String ignoreUrl : authUrl.getIgnore()) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(MyConstant.TOKEN_HEADER, "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }
}
