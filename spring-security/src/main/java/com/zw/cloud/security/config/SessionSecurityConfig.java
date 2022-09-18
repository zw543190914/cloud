package com.zw.cloud.security.config;

import com.zw.cloud.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class SessionSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService userDetailService;
    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * remember-me 持久化  mysql
     * redis:https://blog.csdn.net/qq_33999481/article/details/118972808
     */
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 自动创建表结构，第一次后可以注释
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

   /* @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //定义那些url需要被保护,那些不需要进行保护,通常这个出来在配置的第一行,
        // 其中 antMatchers是设置路径,通常这里设置的是控制器(controller)上的请求路径，后面的permitAll是允许任何人访问,没有任何限制,
        httpSecurity
                .authorizeRequests()
                .antMatchers("/500").permitAll()
                .antMatchers("/403").permitAll()
                .antMatchers("/404").permitAll()
                //任何其它请求
                .anyRequest()
                //都需要身份认证
                .authenticated()
                .and()
                    //使用表单认证方式
                    .formLogin()
                    .loginPage("/mylogin.html")
                    //配置默认登录入口 默认 /login
                    .loginProcessingUrl("/doLogin")
                    .defaultSuccessUrl("/success.html")
                    // 服务端跳转
                    //.successForwardUrl("/index")
                    //.successHandler(successHandler())
                    // 登陆成功返回JSON
                   /* .successHandler((req, resp, auth) -> {
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "登录成功");
                        resp.setContentType("application/json;charset=utf-8");
                        resp.getWriter().write(new ObjectMapper().writeValueAsString(map));
                    })*/
                    //配置登录失败的跳转页面，注意，这种跳转方式是一个客户端跳转（重定向）
                    //.failureUrl("/mylogin.html")
                    //配置登录失败的跳转页面，注意，这个是服务端跳转
                    .failureForwardUrl("/mylogin.html")
                    //.failureHandler(simpleUrlAuthenticationFailureHandler())
                    // 默认登录页面的key,默认username
                    .usernameParameter("uname")
                    .passwordParameter("passwd")
                    .permitAll()
                .and()
                    //开启注销登录的配置
                    .logout()
                    //注销登录的 URL 地址，这是一个 GET 请求
                    //.logoutUrl("/logout")
                    //可以配置两个注销地址，第一个 logout 是 get 请求，第二个 logout2 是 post 请求
                    .logoutRequestMatcher(new OrRequestMatcher(
                            new AntPathRequestMatcher("/logout", "GET"),
                            new AntPathRequestMatcher("/logout2", "POST")))
                    //注销登录时，使 HttpSession 失效，默认为 true
                    .invalidateHttpSession(true)
                    //清除认证信息，默认为 true
                    .clearAuthentication(true)
                    //注销成功后的跳转地址
                    .logoutSuccessUrl("/mylogin.html")
                    //注销成功后，返回一段 JSON
                    /*.logoutSuccessHandler((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销登录成功");
                        resp.getWriter().write(new ObjectMapper().writeValueAsString(map));
                    })*/
                    /*.defaultLogoutSuccessHandlerFor((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "使用 GET /logout 注销登录成功");
                        resp.getWriter().write(new ObjectMapper().writeValueAsString(map));
                    }, new AntPathRequestMatcher("/logout", "GET"))*/
                    /*.defaultLogoutSuccessHandlerFor((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "使用 POST /logout2 注销登录成功");
                        resp.getWriter().write(new ObjectMapper().writeValueAsString(map));
                    }, new AntPathRequestMatcher("/logout2", "POST"))*/
                .and()
                    .rememberMe()
                    // rememberMe 持久化
                    .tokenRepository(tokenRepository)

                .and()
                    // 关闭csrf防御策略
                    .csrf().disable();
                /*.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)*/

    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /*@Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }*/


    ExceptionMappingAuthenticationFailureHandler exceptionMappingAuthenticationFailureHandler() {
        ExceptionMappingAuthenticationFailureHandler handler = new ExceptionMappingAuthenticationFailureHandler();
        Map<String, String> map = new HashMap<>();
        map.put("", "/mylogin.html");
        map.put("", "/mylogin2.html");
        handler.setExceptionMappings(map);
        return handler;
    }

    SimpleUrlAuthenticationFailureHandler simpleUrlAuthenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler();
        handler.setDefaultFailureUrl("/mylogin.html");
        handler.setUseForward(true);
        return handler;
    }

    SavedRequestAwareAuthenticationSuccessHandler successHandler(){
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/index");
        //handler.setAlwaysUseDefaultTargetUrl(true);
        handler.setTargetUrlParameter("target");
        return handler;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.encode("123456"));

    }
}
