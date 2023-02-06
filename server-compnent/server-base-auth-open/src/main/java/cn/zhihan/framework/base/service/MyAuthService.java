package cn.zhihan.framework.base.service;

import cn.zhihan.framework.base.domain.Oauth2Token;

import javax.servlet.http.HttpServletRequest;

/**
 * description: MyAuthService
 * date: 2021/9/9 7:03 下午
 * version: 1.0
 * author: suzui
 */
public interface MyAuthService {
    
    public String genTokenUrl(HttpServletRequest request);
    
    /**
     * description: 获取token
     * date: 2021/9/9 5:26 下午
     * version: 1.0
     * author: suzui
     */
    public Oauth2Token token(String tokenUrl, String client_id, String client_secret);
    
    
}
