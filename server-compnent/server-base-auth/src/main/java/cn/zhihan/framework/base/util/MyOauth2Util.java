package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.constant.MyConstant;
import cn.zhihan.framework.base.domain.Oauth2Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.security.Principal;
import java.util.Map;

/**
 * description: MyOauth2Util
 * date: 2021/8/20 6:10 下午
 * version: 1.0
 * author: suzui
 */
@Component
public class MyOauth2Util {
    
    @Autowired
    TokenEndpoint tokenEndpoint;
    
    public Oauth2Token postAccessToken(Principal principal, Map<String, String> parameters) {
        try {
            OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
            Oauth2Token oauth2Token = Oauth2Token.builder()
                    .token(oAuth2AccessToken.getValue())
                    .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                    .expiresIn(oAuth2AccessToken.getExpiresIn())
                    .tokenHead(MyConstant.TOKEN_HEAD).build();
            return oauth2Token;
        } catch (HttpRequestMethodNotSupportedException e) {
        
        }
        return null;
    }
    
}
