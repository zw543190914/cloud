package com.zw.cloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {
    
    public static final String SUBJECT = "common";

    //过期时间，毫秒，一天
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    //秘钥
    public static final  String APPSECRET = "secret";

    /**
     * 生成jwt
     * @param workId
     * @return
     */
    public static String geneJsonWebToken(String workId,String workName){

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("workId",workId)
                .claim("workName",workName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();
        return token;
    }


    /**
     * 校验token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        final Claims claims = Jwts.parser().setSigningKey(APPSECRET).
                parseClaimsJws(token).getBody();
        if (claims.getExpiration().getTime() < System.currentTimeMillis()){
            throw new RuntimeException("身份已过期");
        }
        return claims;
    }


}
