package com.example.uiservice.Config;

import com.example.uiservice.Config.Handlers.CustomAccessDeniedHandler;
import com.example.uiservice.Config.Handlers.CustomAuthenticationFailureHandler;
import com.example.uiservice.Config.Handlers.CustomLogoutSuccessHandler;
import com.example.uiservice.DATA.Entities.TeacherUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
@Profile("!dev")
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TeacherUserDetailsService userDetailsService;

    public SecSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/course/**").hasRole("TEACHER")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/teachers/registration*").permitAll()
                .antMatchers("/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage", true)
                .failureUrl("/login?error=true")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                //.exceptionHandling().accessDeniedPage("/accessDenied")
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
