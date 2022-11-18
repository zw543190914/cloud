package com.zw.cloud.security.config;

import com.zw.cloud.security.filter.JwtAccessDeniedHandler;
import com.zw.cloud.security.filter.JwtAuthenticationEntryPoint;
import com.zw.cloud.security.filter.JwtAuthenticationTokenFilter;
import com.zw.cloud.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class TokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler()).authenticationEntryPoint(jwtAuthenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/doLogin").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
                .anyRequest().authenticated()       // 剩下所有的验证都需要验证
                .and()
                .csrf().disable()                      // 禁用 Spring Security 自带的跨域处理
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 定制我们自己的 session 策略：调整为让 Spring Security 不创建和使用 session

        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter(authenticationManagerBean());
    }

    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler(){
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint(){
        return new JwtAuthenticationEntryPoint();
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.encode("123456"));

    }
}
