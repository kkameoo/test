package com.example.ch16.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity (
        prePostEnabled = true, securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/sign-api/sign-in","/sign-api/sign-up","sign-api/exception")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/product/**").permitAll()
                .antMatchers(HttpMethod.GET, "/board/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/order/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/order").hasRole("USER")
                .anyRequest().hasAnyRole("USER","ADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenicationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers("/v2/api-docs", "/v3/api-docs/**", "/swagger-resources/**", "/swagger-ui/**","/swagger-ui/index.html", "/webjars/**", "/swagger/**", "/sign-api/exception");
    }
}
