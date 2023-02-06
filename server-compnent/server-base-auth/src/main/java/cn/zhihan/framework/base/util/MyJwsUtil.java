package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.domain.Oauth2User;
import com.nimbusds.jose.JWSObject;

import java.text.ParseException;

/**
 * description: MyJwsUtil
 * date: 2021/9/7 3:58 下午
 * version: 1.0
 * author: suzui
 */
public class MyJwsUtil {
    
    public static String parse(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String userStr = jwsObject.getPayload().toString();
            return userStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Oauth2User getUser(String userStr) {
        if (MyUtil.isBlank(userStr)) {
            return null;
        }
        return MyJsonUtil.read(userStr, Oauth2User.class);
    }
}
