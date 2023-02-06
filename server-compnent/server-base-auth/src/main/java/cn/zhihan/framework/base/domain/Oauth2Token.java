package cn.zhihan.framework.base.domain;

import lombok.*;

import java.io.Serializable;

/**
 * description: 返回token信息封装
 * date: 2021/9/7 4:04 下午
 * version: 1.0
 * author: suzui
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Oauth2Token implements Serializable {
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;
}
