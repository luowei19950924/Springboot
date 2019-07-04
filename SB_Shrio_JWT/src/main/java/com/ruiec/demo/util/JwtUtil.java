package com.ruiec.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import static java.security.KeyRep.Type.SECRET;

/**
 *  jwt-token工具类
 * @author luowei
 * @date 2019/5/28 14:49
 */
public class JwtUtil {

    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token 秘钥
     * @param secret 用户密码
     * @author luowei
     * @date 2019/5/28 15:19
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            //校验token
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     * @author luowei
     * @date 2019/5/28 15:28
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名，5分钟过期
     * @author luowei
     * @date 2019/5/28 15:32
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm=Algorithm.HMAC256(secret);
        //附带username信息
        return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);
    }

}