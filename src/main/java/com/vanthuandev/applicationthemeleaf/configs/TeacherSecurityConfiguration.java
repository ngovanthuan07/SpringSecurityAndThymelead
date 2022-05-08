package com.vanthuandev.applicationthemeleaf.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static com.vanthuandev.applicationthemeleaf.constants.ApplicationRole.ROLE_TEACHER;
import static com.vanthuandev.applicationthemeleaf.utils.ApplicationFormatRole.hasRole;


@Configuration
@EnableWebSecurity
@Order(2)
public class TeacherSecurityConfiguration extends WebSecurityConfigurerAdapter  {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("teacherService")
    @Autowired
    private UserDetailsService teacherService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(teacherService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/teacher/**")
                .authorizeRequests()
                .antMatchers("/teacher/**")
                .access(hasRole(ROLE_TEACHER.name()))
                .and()
                .formLogin()
                .loginPage("/teacher-panel/login")
                .loginProcessingUrl("/teacher/process-login")
                .defaultSuccessUrl("/home")
                .failureUrl("/teacher-panel/login?error")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutUrl("/teacher-panel/process-logout")
                .logoutSuccessHandler(logoutHandler);
        http.cors().and().csrf().disable();
    }
}
