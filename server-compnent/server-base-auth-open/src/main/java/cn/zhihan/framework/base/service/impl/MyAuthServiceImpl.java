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
    public Oauth2Token token(String tokenUrl, String client_id, String client_secret) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("client_id", client_id);
        map.add("client_secret", client_secret);
        map.add("grant_type", MyGrantType.CLIENT_CREDENTIALS.intro());
        try {
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map);
            ResponseEntity<Oauth2Token> responseEntity = restTemplate.postForEntity(tokenUrl, entity, Oauth2Token.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            throw new MyResultException(Result.StatusCode.CLIENT_ACCOUNT_ERROR);
        }
    }
    
    
}
