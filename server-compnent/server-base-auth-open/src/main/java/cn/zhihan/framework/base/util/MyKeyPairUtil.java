package cn.zhihan.framework.base.util;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * description: MyKeyPairUtil
 * date: 2021/8/20 6:10 下午
 * version: 1.0
 * author: suzui
 */
@Component
public class MyKeyPairUtil {
    
    
    @Autowired
    KeyPair keyPair;
    
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
    
}
