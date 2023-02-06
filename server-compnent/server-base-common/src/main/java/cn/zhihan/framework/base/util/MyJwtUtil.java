package cn.zhihan.framework.base.util;

import cn.zhihan.framework.base.constant.MyConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description: JwtTokenUtil
 * date: 2020/5/17 10:55 下午
 * version: 1.0
 * author: suzui
 * JwtToken生成的工具类
 * JWT token的格式：header.payload.signature
 * header的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 */

@Component
@Slf4j
@Data
public class MyJwtUtil {
    
    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(genExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, MyConstant.SECRET)
                    .compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(MyConstant.SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            //失效后会抛出异常 从抛出的这个异常ExpiredJwtException 中有一个参数claims 就是解析后的token
            claims = e.getClaims();
        } catch (Exception e) {
            log.info("JWT格式验证失败 token:{} e:{}", token, e.getMessage());
        }
        return claims;
    }
    
    /**
     * 生成token的过期时间
     */
    private Date genExpirationDate() {
        return new Date(System.currentTimeMillis() + MyConstant.EXPIRATION);
    }
    
    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDate(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }
    
    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDate(token);
        return expiredDate.before(new Date());
    }
    
    /**
     * 验证token格式是否有效
     */
    public boolean validateToken(String token) {
        if (MyUtil.isBlank(token)) {
            return false;
        }
        return getClaimsFromToken(token) != null;
    }
    
    /**
     * 从token中获取登录用户id
     */
    public Long getUserId(String token) {
        Long userId;
        try {
            Claims claims = getClaimsFromToken(token);
            userId = Long.valueOf(claims.getSubject());
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }
    
    /**
     * 从token中获取登录用户类型
     */
    public String getType(String token) {
        String type;
        try {
            Claims claims = getClaimsFromToken(token);
            type = (String) claims.get(MyConstant.CLAIM_KEY_TYPE);
        } catch (Exception e) {
            type = null;
        }
        return type;
    }
    
    /**
     * 从token中获取登录客户端类型
     */
    public String getClient(String token) {
        String client;
        try {
            Claims claims = getClaimsFromToken(token);
            client = (String) claims.get(MyConstant.CLAIM_KEY_CLIENT);
        } catch (Exception e) {
            client = null;
        }
        return client;
    }
    
    /**
     * 从token中获取登录用户名_类型_客户端
     */
    public String getUserId_type_client(String token) {
        String userId_type_client;
        try {
            Claims claims = getClaimsFromToken(token);
            String userId = claims.getSubject();
            String type = (String) claims.get(MyConstant.CLAIM_KEY_TYPE);
            String client = (String) claims.get(MyConstant.CLAIM_KEY_CLIENT);
            userId_type_client = userId + "_" + type + "_" + client;
        } catch (Exception e) {
            userId_type_client = null;
        }
        return userId_type_client;
    }
    
    /**
     * 从token中获取自定义信息
     */
    public String getData(String token, String dataKey) {
        String data;
        try {
            Claims claims = getClaimsFromToken(token);
            data = (String) claims.get(dataKey);
        } catch (Exception e) {
            data = null;
        }
        return data;
    }
    
    /**
     * 根据用户信息生成token
     */
    public String generateToken(Long userId, String type) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(MyConstant.CLAIM_KEY_USERID, userId);
        claims.put(MyConstant.CLAIM_KEY_TYPE, type);
        claims.put(MyConstant.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    /**
     * 根据用户信息生成token
     */
    public String generateToken(Long userId, String type, String client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(MyConstant.CLAIM_KEY_USERID, userId);
        claims.put(MyConstant.CLAIM_KEY_TYPE, type);
        claims.put(MyConstant.CLAIM_KEY_CLIENT, client);
        claims.put(MyConstant.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }
    
    /**
     * 刷新token 更新有效期
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(MyConstant.CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
    
    /**
     * 注销token
     */
    public String expireToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(MyConstant.CLAIM_KEY_CREATED, new Date(System.currentTimeMillis() - MyConstant.EXPIRATION));
        return generateToken(claims);
    }
    
}