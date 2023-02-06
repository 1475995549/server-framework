package cn.zhihan.framework.base.enums;

import cn.zhihan.framework.base.annotation.MyEnumClass;
import cn.zhihan.framework.base.util.MyEnumUtil;
import org.apache.commons.lang3.StringUtils;

@MyEnumClass(name = "按钮类型")
public enum MyButtonType implements MyEnum {
    CLOSE(101, "关闭窗口"), REPOST(102, "重新请求"), LINK(103, "链接跳转"), BACK(104, "返回上级");
    private int code;
    private String intro;
    
    MyButtonType(int code, String intro) {
        this.code = code;
        this.intro = intro;
    }
    
    public static MyButtonType convert(Integer code) {
        return MyEnumUtil.convert(code, MyButtonType.class);
    }
    
    public static MyButtonType convert(String code) {
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
