package cn.zhihan.framework.base.enums;

import cn.zhihan.framework.base.annotation.MyEnumClass;
import cn.zhihan.framework.base.util.MyEnumUtil;
import org.apache.commons.lang3.StringUtils;

@MyEnumClass(name = "oauth2授权模式")
public enum MyGrantType implements MyEnum {
    AUTHORIZATION_CODE(101, "authorization_code"), IMPLICIT(102, "implicit"), PASSWORD(103, "password"), CLIENT_CREDENTIALS(104, "client_credentials"), FRESH_TOKEN(105, "refresh_token");
    private int code;
    private String intro;
    
    MyGrantType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }
    
    public static MyGrantType convert(Integer code) {
        return MyEnumUtil.convert(code, MyGrantType.class);
    }
    
    public static MyGrantType convert(String code) {
        return StringUtils.isBlank(code) ? null : convert(Integer.parseInt(code));
    }
    
    @Override
    public int code() {
        return this.code;
    }
    
    @Override
    public String intro() {
        return this.intro;
    }
    
    @Override
    public String toString() {
        return this.code + ":" + this.intro;
    }
}