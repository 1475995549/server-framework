package cn.zhihan.framework.base.service.impl;

import cn.zhihan.framework.base.domain.Oauth2Token;
import cn.zhihan.framework.base.enums.MyGrantType;
import cn.zhihan.framework.base.exception.MyResultException;
import cn.zhihan.framework.base.service.MyAuthService;
import cn.zhihan.framework.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * description: MyAuthServiceImpl
 * date: 2021/9/9 5:00 下午
 * version: 1.0
 * author: suzui
 */
@Service("myAuthServiceImpl")
public class MyAuthServiceImpl implements MyAuthService {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Override
    public String genTokenUrl(HttpServletRequest request) {
        return request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/oauth/token";
    }
    
    /**
     * description: 获取token
     * date: 2021/9/9 5:26 下午
     * version: 1.0
     * author: suzui
     */
    @Override
    public Oauth2Token token(String tokenUrl, String client_id, String client_secret, String username, String password) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("username", username);
        map.add("password", password);
        map.add("grant_type", MyGrantType.PASSWORD.intro());
        try {
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map);
            ResponseEntity<Oauth2Token> responseEntity = restTemplate.postForEntity(tokenUrl, entity, Oauth2Token.class);
            //TODO token个性化策略
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new MyResultException(Result.StatusCode.PERSON_ACCOUNT_ERROR);
        }
    }
    
    /**
     * description: 更新token
     * date: 2021/9/9 5:26 下午
     * version: 1.0
     * author: suzui
     */
    @Override
    public Oauth2Token refresh(String tokenUrl, String client_id, String client_secret, String refresh_token) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("refresh_token", refresh_token);
        map.add("grant_type", MyGrantType.FRESH_TOKEN.intro());
        try {
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map);
            ResponseEntity<Oauth2Token> responseEntity = restTemplate.postForEntity(tokenUrl, entity, Oauth2Token.class);
            //TODO token个性化策略
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new MyResultException(Result.StatusCode.SYSTEM_TOKEN_UNVALID);
        }
    }
    
    /**
     * description: 失效token 用于用户登出
     * date: 2021/9/9 5:28 下午
     * version: 1.0
     * author: suzui
     */
    @Override
    public void invalid(String jti) {
        //TODO
    }
    
    /**
     * description: 销毁token 用于删除用户
     * date: 2021/9/9 5:28 下午
     * version: 1.0
     * author: suzui
     */
    @Override
    public void destroy(String user_id) {
        //TODO
    }
    
    
}
