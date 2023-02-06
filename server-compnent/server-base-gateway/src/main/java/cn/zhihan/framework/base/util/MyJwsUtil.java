package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.constant.MyConstant;
import com.nimbusds.jose.JWSObject;

import java.text.ParseException;

/**
 * description: MyJwsUtil
 * date: 2021/9/7 3:58 下午
 * version: 1.0
 * author: suzui
 */
public class MyJwsUtil {
    
    public static JWSObject parse(String token) {
        try {
            String realToken = token.replace(MyConstant.TOKEN_HEAD, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            return jwsObject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
