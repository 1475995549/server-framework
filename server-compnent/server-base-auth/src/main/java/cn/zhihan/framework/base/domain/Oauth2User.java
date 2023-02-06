package cn.zhihan.framework.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * description: token-payload中的用户信息封装
 * date: 2021/9/7 4:04 下午
 * version: 1.0
 * author: suzui
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Oauth2User implements Serializable {
    
    public String user_id;
    public String user_name;
    public List<String> scope;
    public Long exp;
    public List<String> authorities;
    public String jti;
    public String client_id;
    
    
}
