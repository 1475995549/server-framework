package cn.zhihan.framework.base.constant;

import cn.zhihan.framework.base.util.MyDateUtil;

/**
 * description: MyConstant
 * date: 2021/7/30 6:10 下午
 * version: 1.0
 * author: suzui
 */
public class MyConstant {
    
    //默认form、query、id param后缀名
    public static final String PARAM_FORM = "form";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_ID = "Id";
    
    //默认field字段名
    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    
    //默认header信息
    public static final String apivo = "apivo";
    public static final String appversion = "appversion";
    public static final String apptype = "apptype";
    public static final String osversion = "osversion";
    public static final String clienttype = "clienttype";
    public static final String devicetoken = "devicetoken";
    public static final String deviceinfo = "deviceinfo";
    
    //默认jwt相关字信息
    public static final String CLAIM_KEY_USERID = "sub";
    public static final String CLAIM_KEY_TYPE = "type";
    public static final String CLAIM_KEY_CLIENT = "client";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String TOKEN_HEADER = "Authorization";//JWT存储的请求头
    public static final String TOKEN_HEAD = "Bearer ";//JWT负载中拿到默认开头 注意空格 默认为 Bearer tokenxxxxx
    public static final String SECRET = "MySecret";//JWT加解密使用的密钥
    public static final Integer EXPIRATION = MyDateUtil.DAY;//token有效期（非oauth 仅jwt+redis）
    
    //oauth默认信息
    public static final String AUTH_SCOPE = "all";
    public static final String KEY_PAIR_ALIAS = "jwt";
    public static final String KEY_PAIR_PATH = "jwt.jks";//jwt.jks证书文件路径
    public static final String KEY_PAIR_PASSWORD = "zhihan";//jwt.jks证书密码
    public static final String AUTH_RESOURCE_MAP = "AUTH:RESOURCE_MAP";//权限在redis的key
    public static final String AUTH_AUTHORITY_PREFIX = "ROLE:";//网关检验角色权限时的role前缀
    public static final String AUTH_AUTHORITY_CLAIM_NAME = "authorities";
    public static final String USER_HEADER = "USER";//经网关转发的user信息请求头
    public static final String USER_ID_HEADER = "USER_ID";//经网关转发的user_id请求头
    public static final String USER_ID_JWT = "user_id";//jwt中补充的user_id字段
    public static final String CLIENT_HEADER = "CLIENT";//经网关转发的client信息请求头
    public static final String CLIENT_ID_HEADER = "CLIENT_ID";//经网关转发的client_id请求头
    public static final String CLIENT_ID_JWT = "client_id";//jwt中默认的client_id字段
    public static final Integer TOKEN_VALIDITY_SECONDS = MyDateUtil.HOUR / 1000 * 2;//token有效期 2小时
    public static final Integer TOKEN_REFRESH_SECONDS = MyDateUtil.DAY / 1000 * 30;//token刷新有效期
    
}
