package cn.zhihan.framework.base.domain;

import cn.zhihan.framework.base.util.MyUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * 授权客户端信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityClient implements ClientDetails {
    
    private String client_id;
    
    private String client_secret;
    
    private String scopes;
    
    private String authorizedGrantTypes;
    
    private Integer accessTokenValiditySeconds;
    
    private Integer refreshTokenValiditySeconds;
    
    @Override
    public String getClientId() {
        return this.client_id;
    }
    
    @Override
    public Set<String> getResourceIds() {
        return null;
    }
    
    @Override
    public boolean isSecretRequired() {
        return false;
    }
    
    @Override
    public String getClientSecret() {
        return this.client_secret;
    }
    
    @Override
    public boolean isScoped() {
        return false;
    }
    
    @Override
    public Set<String> getScope() {
        return new HashSet<>(MyUtil.strToList(this.scopes));
    }
    
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return new HashSet<>(MyUtil.strToList(this.authorizedGrantTypes));
    }
    
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }
    
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }
    
    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }
    
    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }
    
    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }
    
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
