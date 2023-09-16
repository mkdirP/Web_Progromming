package application.configuration;

import application.configuration.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Spring Security是一个基于Spring框架的安全认证和访问控制框架，提供了一套完整的安全性解决方案
// Spring Security的配置类，用于配置应用程序的web安全性
// Configuration：声明该类为配置类   EnableWebSecurity 开启web安全性
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

//  传入的http对象，用于设置不同的安全选项，如身份验证，授权等
//  .antMatchers("").permitAll()以下接口可以被所有人访问
//  .anyRequest().authenticated() 其他所有的请求都需要进行身份验证
//  .addFilterBefore 添加jwt过滤器，并将其放置在UsernamePasswordAuthenticationFilter之前

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/points").permitAll()
                .antMatchers("/api/points/*").permitAll()
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/user/*").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}