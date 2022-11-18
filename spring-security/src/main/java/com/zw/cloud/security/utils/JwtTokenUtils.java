package com.zw.cloud.security.utils;


import com.zw.cloud.security.entity.sys.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.util.*;

@Component
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created_time";
    public static final String CLAIM_KEY_ROLE = "roles";

    @Value("${jwt.secret}")
    private String secret = "mySecret";

    @Value("${jwt.expiration}")
    private Long expiration = 10L;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }


    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        List<String> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(grantedAuthority -> {
            roles.add(grantedAuthority.getAuthority());
        });
        claims.put(CLAIM_KEY_ROLE,roles);
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, String username) {
        return (username.equals(getUsernameFromToken(token)) && !isTokenExpired(token));
    }

    private SecretKeySpec getSigningKey() {
        byte[] bytes = DatatypeConverter.parseBase64Binary(secret);
        return new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getFamilyName());
    }

    public static void main(String[] args) {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String usernameFromToken = jwtTokenUtils.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6dyIsImNyZWF0ZWRfdGltZSI6MTY2MzUwNTE5NTEzNiwiZXhwIjoxNjYzNTA1MjA1fQ.DVP1YVw0utSDNCy8QeyosIaNbmVjBGNMhJ4RbHpdU-T4Tug1NNrIbZzQZIqVAqi5iCUeVSG17R_TPd1440cCtQ");
        System.out.println(usernameFromToken);
    }
}