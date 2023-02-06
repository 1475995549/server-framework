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
    public Oauth2Token token(String tokenUrl, String client_id, String client_secret, String username, String password);
    
    /**
     * description: 更新token
     * date: 2021/9/9 5:26 下午
     * version: 1.0
     * author: suzui
     */
    public Oauth2Token refresh(String tokenUrl, String client_id, String client_secret, String refresh_token);
    
    /**
     * description: 失效token 用于用户登出
     * date: 2021/9/9 5:28 下午
     * version: 1.0
     * author: suzui
     */
    public void invalid(String jti);
    
    /**
     * description: 销毁token 用于删除用户
     * date: 2021/9/9 5:28 下午
     * version: 1.0
     * author: suzui
     */
    public void destroy(String user_id);
}
