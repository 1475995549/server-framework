package cn.zhihan.framework.base.enums;

/**
 * description: 短信类型
 * date: 2019/8/27 19:41
 * version: 1.0
 * author: liuzhenjun
 */
public interface MySmsType extends MyEnum {

//    格式如：
//    TEST(115, "test", "274208", "#personName#,#name#");
//
//    private int code;
//    private String intro;
//    private String templateId;
//    private String params;

    /**
     * description: 模板ID
     * date: 2019/8/27 19:44
     * version: 1.0
     * author: liuzhenjun
     *
     * @param
     * @return java.lang.String
     */
    String templateId();

    /**
     * description: 变量值（格式：#name#，多个逗号隔开）
     * date: 2019/8/27 19:46
     * version: 1.0
     * author: liuzhenjun
     *
     * @param
     * @return java.lang.String
     */
    String params();
}
