package cn.zhihan.framework.base.enums;

import cn.zhihan.framework.base.annotation.MyEnumClass;
import cn.zhihan.framework.base.util.MyEnumUtil;
import org.apache.commons.lang3.StringUtils;

@MyEnumClass(name = "是否")
public enum MyBooleanStatus implements MyEnum {
    FALSE(0, "false"), TRUE(1, "true");
    private int code;
    private String intro;
    
    MyBooleanStatus(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }
    
    public static MyBooleanStatus convert(Integer code) {
        return MyEnumUtil.convert(code, MyBooleanStatus.class);
    }
    
    public static MyBooleanStatus convert(String code) {
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