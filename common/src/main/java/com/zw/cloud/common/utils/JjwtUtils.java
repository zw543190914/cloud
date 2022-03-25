package com.zw.cloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JjwtUtils {
    public static final String SIGN = "secret";

    public static void main(String[] args) {
        String jwt = createJwt("001","zw");
        System.out.println(jwt);
        parseJwt(jwt);
    }

    public static String createJwt(String userId,String username) {
        //为了方便测试，我们将过期时间设置为5分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 5000 * 60;//过期时间为5分钟
        JwtBuilder builder = Jwts.builder().setId(userId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SIGN)
                .setExpiration(new Date(exp));//设置过期时间
        return builder.compact();
    }

    public static Claims parseJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SIGN)
                .parseClaimsJws(token).getBody();
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:" + sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
        return claims;
    }
}
