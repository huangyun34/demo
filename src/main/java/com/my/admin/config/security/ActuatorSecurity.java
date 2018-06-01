package com.my.admin.config.security;

import com.my.admin.NotificationListener;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

//@Configuration
public class ActuatorSecurity /*extends WebSecurityConfigurerAdapter*/ {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
//                .anyRequest().hasRole("ADMIN")
//                .and()
//                .httpBasic();
//    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
////        UserDetails uu = new User("huangyun", "123456", Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
////        UserDetails user =
////                User.withUsername("huangyun")
////                        .password("123456")
////                        .passwordEncoder(passwordEncoder())
////                        .roles("ADMIN")
////                        .build();
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("huangyun")
//                        .password("123456")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}
