package cn.zhihan.framework.base.vo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * 网络请求结果
 *
 * @author Su
 */
@Slf4j
@lombok.Data
@ApiModel(value = "Result", description = "接口统一返回result")
public class Result<T extends Serializable> implements Serializable {
    
    @ApiModelProperty(value = "状态", notes = "succ|fail")
    private String status;
    
    @ApiModelProperty(value = "code")
    private Integer code;
    
    @ApiModelProperty(value = "提示文案")
    private String message;
    
    @ApiModelProperty(value = "数据")
    private T data;
    
    @ApiModelProperty(value = "提示validation")
    private Validation validation;
    
    @ApiModelProperty(value = "系统时间")
    private Long systemTime;
    
    public static class Status {
        public final static String SUCC = "succ";
        public final static Integer SUCCCODE = 20000;
        public final static String SUCCTEXT = "-请求成功";
        public final static String FAIL = "fail";
        public final static Integer FAILCODE = 50000;
        public final static String FAILTEXT = "系统异常";
    }
    
    public static class StatusCode {
        public static final Object[] SUCC = {Status.SUCCCODE, Status.SUCCTEXT};
        public static final Object[] FAIL = {Status.FAILCODE, Status.FAILTEXT};
        public static final Object[] BACK_UPDATE_FORBIDDEN = {30001, "无更新权限"};
        public static final Object[] BACK_UPDATE_FAILED = {30002, "更新失败"};
        public static final Object[] BACK_RESTART_FAILED = {30003, "重启失败"};
        public static final Object[] BACK_START_FAILED = {30004, "有项目正在启动"};
        
        public static final Object[] SYSTEM_APP_UPDATE = {40000, "-有版本更新"};
        public static final Object[] SYSTEM_TOKEN_UNVALID = {40001, "-token已失效"};
        public static final Object[] SYSTEM_TOKEN_FRESH = {40002, "-token需更新"};
        public static final Object[] SYSTEM_REQUEST_REPEAT = {40003, "重复请求"};
        public static final Object[] SYSTEM_NOTFOUND = {40004, "地址不存在"};
        public static final Object[] SYSTEM_ACCESS_FOBIDDEN = {40005, "无相应权限"};
        public static final Object[] SYSTEM_PARAM_ERROR = {40006, "参数不合法"};
        public static final Object[] SYSTEM_FILE_ERROR = {40007, "文件格式错误"};
        public static final Object[] SYSTEM_IP_FOBIDDEN = {40008, "ip地址无访问权限"};
        public static final Object[] SYSTEM_FOBIDDEN = {40009, "无访问权限"};
        public static final Object[] SYSTEM_INFO_FRESH = {40010, "帐户信息已更新，请重新登录"};
        public static final Object[] PERSON_USERNAME_UNVALID = {40101, "用户名格式错误"};
        public static final Object[] PERSON_PHONE_UNVALID = {40102, "手机号码格式错误"};
        public static final Object[] PERSON_EMAIL_UNVALID = {40103, "邮箱格式错误"};
        public static final Object[] PERSON_PASSWORD_UNVALID = {40104, "密码格式错误"};
        public static final Object[] PERSON_USERNAME_EXIST = {40105, "该用户名已被使用"};
        public static final Object[] PERSON_PHONE_EXIST = {40106, "该手机号码已被使用"};
        public static final Object[] PERSON_EMAIL_EXIST = {40107, "该邮箱已被使用"};
        public static final Object[] PERSON_PASSWORD_ERROR = {40108, "密码错误"};
        public static final Object[] PERSON_CAPTCHA_ERROR = {40109, "验证码错误"};
        public static final Object[] PERSON_ACCOUNT_NOTEXIST = {40110, "用户不存在"};
        public static final Object[] PERSON_ACCOUNT_BINDED = {40111, "账号已被绑定"};
        public static final Object[] PERSON_ACCOUNT_ERROR = {40112, "用户名或密码错误"};
        public static final Object[] PERSON_ACCOUNT_UNVALID = {40113, "账户不可用"};
        public static final Object[] CLIENT_ACCOUNT_NOTEXIST = {40114, "应用不存在"};
        public static final Object[] CLIENT_ACCOUNT_ERROR = {40115, "appKey或appSecret错误"};
        
        //statuscode转换三种validation说明
        //"-请求成功" type:100 content:请求成功
        //"系统异常"  type:102 content:系统异常
        //"标题|内容|取消|确定:102" type:101 title:标题 content:内容 cancelText:取消 cancelType:101 submitText:确定 submitType:102
        
        //动态提示语
        public static Object[] format(Object[] sc, String... os) {
            if (sc.length != 2) {
                return sc;
            }
            sc[1] = String.format((String) sc[1], os);
            return sc;
        }
        
        //message转换object[]
        private static Object[] convert(String key) {
            //String message = Messages.get(key);
            String message = null;
            if (StringUtils.isBlank(message)) {
                return null;
            }
            String[] messages = StringUtils.split(message, ",");
            if (messages.length != 2) {
                return null;
            }
            Object[] objects = new Object[2];
            objects[0] = Integer.valueOf(messages[0]);
            objects[1] = messages[1];
            return objects;
        }
        
        public static Object[] conv(String key) {
            return convert(key);
        }
        
        public static Object[] format(String key) {
            return convert(key);
        }
    }
    
    public Result() {
        systemTime = System.currentTimeMillis();
    }
    
    public static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        // mapper.setSerializationInclusion(Include.NON_NULL);
    }
    
    public static <T extends Serializable> Result<T> failed() {
        return result(Status.FAIL, Status.FAILCODE, Status.FAILTEXT, null);
    }
    
    public static <T extends Serializable> Result<T> failed(String message) {
        return result(Status.FAIL, Status.FAILCODE, message, null);
    }
    
    public static <T extends Serializable> Result<T> failed(Object[] codemessage) {
        return result(Status.FAIL, (int) codemessage[0], (String) codemessage[1], null);
    }
    
    public static <T extends Serializable> Result<T> failed(Object[] codemessage, String message) {
        return result(Status.FAIL, (int) codemessage[0], message, null);
    }
    
    public static <T extends Serializable> Result<T> failed(Object[] codemessage, T data) {
        return result(Status.FAIL, (int) codemessage[0], (String) codemessage[1], data);
    }
    
    public static <T extends Serializable> Result<T> succeed() {
        return result(Status.SUCC, Status.SUCCCODE, Status.SUCCTEXT, null);
    }
    
    public static <T extends Serializable> Result<T> succeed(String message) {
        return result(Status.SUCC, Status.SUCCCODE, message, null);
    }
    
    public static <T extends Serializable> Result<T> succeed(Object[] codemessage) {
        return result(Status.SUCC, (int) codemessage[0], (String) codemessage[1], null);
    }
    
    public static <T extends Serializable> Result<T> succeed(T data) {
        return result(Status.SUCC, Status.SUCCCODE, Status.SUCCTEXT, data);
    }
    
    public static <T extends Serializable> Result<T> succeed(T data, String message) {
        return result(Status.SUCC, Status.SUCCCODE, message, data);
    }
    
    public static <T extends Serializable> Result<T> succeed(T data, Object[] codemessage) {
        return result(Status.SUCC, (int) codemessage[0], (String) codemessage[1], data);
    }
    
    private static <T extends Serializable> Result<T> result(String status, int code, String message, T data) {
        Result<T> result = new Result<T>();
        result.status = status;
        result.code = code;
        result.validation = new Validation(message);
        result.message = result.validation.getContent();
        result.data = data;
        return result;
    }
    
    private static <T extends Serializable> String convert(Result<T> result) {
        try {
            return mapper.writeValueAsString(result);
        } catch (IOException e) {
            log.info("[result failed]:{}", e.getMessage());
        }
        return null;
    }
    
}