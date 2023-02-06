package cn.zhihan.framework.base.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * description: MyPwdUtil
 * date: 2020/5/21 12:21 上午
 * version: 1.0
 * author: suzui
 */
@Component
@Slf4j
@Data
public class MyPwdUtil {
    
    public static final String DEFAULT = "zh123456";//默认密码 md5:741a627471982d84d67177464d006a01
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    /**
     * description: 密码校验
     * date: 2020/5/21 12:23 上午
     * version: 1.0
     * author: suzui
     *
     * @param inputPassword
     * @param encodePassword
     * @return boolean
     */
    public boolean matches(String inputPassword, String encodePassword) {
        return passwordEncoder.matches(inputPassword.toLowerCase(), encodePassword);
    }
    
    /**
     * description: 密码加密
     * date: 2020/5/21 12:23 上午
     * version: 1.0
     * author: suzui
     *
     * @param md5Password
     * @return java.lang.String
     */
    public String encode(String md5Password) {
        return passwordEncoder.encode(md5Password);
    }
    
    /**
     * description: 密码md5 32位 字母小写
     * date: 2020/5/21 12:26 上午
     * version: 1.0
     * author: suzui
     *
     * @param password
     * @return java.lang.String
     */
    public String md5(String password) {
        return MyCodeUtil.md5(password).toLowerCase();
    }
    
    /**
     * description: 默认初始化密码
     * date: 2020/5/21 12:30 上午
     * version: 1.0
     * author: suzui
     *
     * @param
     * @return java.lang.String
     */
    public String gen() {
        return encode(md5(DEFAULT));
    }
    
  
}
