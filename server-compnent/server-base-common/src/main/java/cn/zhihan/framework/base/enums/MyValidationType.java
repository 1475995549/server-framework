package cn.zhihan.framework.base.enums;

import cn.zhihan.framework.base.annotation.MyEnumClass;
import cn.zhihan.framework.base.util.MyEnumUtil;
import org.apache.commons.lang3.StringUtils;

@MyEnumClass(name = "提醒类型")
public enum MyValidationType implements MyEnum {
    HIDDEN(100, "隐藏"), DIALOG(101, "对话框"), TOAST(102, "提示框");
    
    private int code;
    private String intro;
    
    MyValidationType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }
    
    public static MyValidationType convert(Integer code) {
        return MyEnumUtil.convert(code, MyValidationType.class);
    }
    
    public static MyValidationType convert(String code) {
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
