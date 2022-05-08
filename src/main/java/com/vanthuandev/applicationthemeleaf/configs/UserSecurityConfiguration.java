package com.vanthuandev.applicationthemeleaf.configs;

import com.vanthuandev.applicationthemeleaf.constants.ApplicationRole;
import com.vanthuandev.applicationthemeleaf.service.UserService;
import com.vanthuandev.applicationthemeleaf.utils.ApplicationFormatRole;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;

import static com.vanthuandev.applicationthemeleaf.constants.ApplicationRole.*;
import static com.vanthuandev.applicationthemeleaf.utils.ApplicationFormatRole.*;
import static org.springframework.http.HttpMethod.*;


@Configuration
@EnableWebSecurity
@Order(1)
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter  {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("userService")
    @Autowired
    private UserDetailsService userService;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler logoutHandler;





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/user/**")
                .authorizeRequests()
                .antMatchers("/user/**")
                .access(hasRole(ROLE_USER.name()))
                .and()
                .formLogin()
                .loginPage("/user-panel/login")
                .loginProcessingUrl("/user/process-login")
                .defaultSuccessUrl("/home")
                .failureUrl("/user-panel/login?error")
                .successHandler(loginSuccessHandler)
                .usernameParameter("username").passwordParameter("password")
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().logout().logoutUrl("/user/process-logout")
                .logoutSuccessHandler(logoutHandler);
        http.cors().and().csrf().disable();
    }
}
