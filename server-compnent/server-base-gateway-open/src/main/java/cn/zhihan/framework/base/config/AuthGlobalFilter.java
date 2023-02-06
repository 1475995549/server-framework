package cn.zhihan.framework.base.config;

import cn.hutool.core.util.StrUtil;
import cn.zhihan.framework.base.constant.MyConstant;
import cn.zhihan.framework.base.util.MyJwsUtil;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: 将登录用户的JWT转化成用户信息的全局过滤器
 * date: 2021/9/8 9:24 上午
 * version: 1.0
 * author: suzui
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(MyConstant.TOKEN_HEADER);
        if (StrUtil.isEmpty(token) || !token.startsWith(MyConstant.TOKEN_HEAD)) {
            return chain.filter(exchange);
        }
        //从token中解析用户信息并设置到Header中去
        JWSObject jwsObject = MyJwsUtil.parse(token);
        String clientStr = jwsObject.getPayload().toString();
        String client_id = jwsObject.getPayload().toJSONObject().getAsString(MyConstant.CLIENT_ID_JWT);
        ServerHttpRequest request = exchange.getRequest().mutate().header(MyConstant.CLIENT_HEADER, clientStr).header(MyConstant.CLIENT_ID_HEADER, client_id).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
