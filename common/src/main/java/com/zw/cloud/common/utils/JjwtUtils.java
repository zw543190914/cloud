package com.zw.cloud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JjwtUtils {
    public static final String SECRET_KEY = "secret";

    public static void main(String[] args) throws Exception{
        String jwt = createJwt("001","zw","seller");
        System.out.println(jwt);
        parseJwt(jwt);
        //Thread.sleep(1000);
        //parseJwt(jwt); // 过期抛出异常 ExpiredJwtException
    }

    public static String createJwt(String userId,String username,String role) {
        //为了方便测试，我们将过期时间设置为5分钟
        long now = System.currentTimeMillis();//当前时间
        long exp = now + 1000 * 60 * 5;//过期时间为5分钟
        JwtBuilder builder = Jwts.builder().setId(userId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, getSigningKey())
                .setExpiration(new Date(exp))//设置过期时间
                .claim("role",role);
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
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy‐MM‐dd hh:mm:ss");
        System.out.println("签发时间:" + sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:" + sdf.format(claims.getExpiration()));
        System.out.println("当前时间:" + sdf.format(new Date()));
        System.out.println("角色:" + claims.get("role"));
        return claims;
    }
}
