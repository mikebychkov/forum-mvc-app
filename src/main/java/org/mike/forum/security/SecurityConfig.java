package org.mike.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.authorizeRequests().and().formLogin().permitAll();

        http.authorizeRequests()
//                .antMatchers("/users/edit*").hasRole("ADMIN")
//                .antMatchers("/users/edit/**").hasRole("ADMIN")
//                .antMatchers("/users/save").hasRole("ADMIN")
//                .antMatchers("/users/delete*").hasRole("ADMIN")
//                .antMatchers("/users/delete/**").hasRole("ADMIN")
                .antMatchers("/users/save").permitAll() //hasRole("USER")
                .antMatchers("/registerForm").permitAll() //hasRole("USER")
                .antMatchers("/oops").permitAll() //hasRole("USER")
                .antMatchers("/**").authenticated() //hasRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/showAccessDenied");
    }
}
