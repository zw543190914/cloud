package com.zw.cloud.security.filter;

import com.zw.cloud.security.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.zw.cloud.security.utils.JwtTokenUtils.CLAIM_KEY_ROLE;

public class JwtAuthenticationTokenFilter extends BasicAuthenticationFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public JwtAuthenticationTokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (Objects.isNull(authHeader) || !authHeader.startsWith(tokenHead)) {
            chain.doFilter(request, response);
            return;
        }
        // The part after "Bearer "
        final String authToken = authHeader.substring(tokenHead.length());
        try {
            Claims claimsFromToken = jwtTokenUtils.getClaimsFromToken(authToken);
            String username = claimsFromToken.getSubject();
            logger.info("checking authentication " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (!jwtTokenUtils.isTokenExpired(authToken)) {
                    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
                    Object roleString = claimsFromToken.get(CLAIM_KEY_ROLE);
                    if (Objects.nonNull(roleString)) {
                        List<String> roleStrings = (List<String>) roleString;
                        roleStrings.forEach(role -> {
                            GrantedAuthority grantedAuthority = () -> role;
                            roles.add(grantedAuthority);
                        });
                    }
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            username, null, roles);
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("token过期,请重新登陆");
            //response.sendError(HttpServletResponse.SC_FORBIDDEN,"token过期,请重新登陆");
        } catch (Exception e) {
            throw new RuntimeException("非法的token");
            //response.sendError(HttpServletResponse.SC_FORBIDDEN,"非法的token");
        }
    }
}

