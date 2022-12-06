package com.zw.cloud.common.utils;

import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JjwtUtils {
    public static final String SECRET_KEY = "secret";

    public static void main(String[] args) throws Exception{
        String jwt = createJwt("001","zw", Lists.newArrayList("seller"));
        System.out.println(jwt);
        parseJwt(jwt);
        //Thread.sleep(1000);
        //parseJwt(jwt); // 过期抛出异常 ExpiredJwtException
    }

    public static String createJwt(String userId, String username, List<String> roleList) {
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000 * 60 * 60 * 24;//过期时间为24H
        JwtBuilder builder = Jwts.builder().setId(userId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .setExpiration(new Date(exp))//设置过期时间
                .claim("role",roleList);
        return builder.compact();
    }

    public static SecretKeySpec getSigningKey() {
        byte[] bytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getFamilyName());
    }

    public static Claims parseJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
