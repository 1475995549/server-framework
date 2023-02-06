package cn.zhihan.framework.base.util;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description: MyJasyptUtil
 * date: 2020/6/15 11:53 上午
 * version: 1.0
 * author: suzui
 */
@Component
public class MyJasyptUtil {
    
    @Autowired
    private StringEncryptor stringEncryptor;
    
    public String encrypt(String value) {
        return stringEncryptor.encrypt(value);
    }
    
    public String decrypt(String value) {
        return stringEncryptor.decrypt(value);
    }
    
}
