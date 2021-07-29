package com.my.admin.config.security;

//import com.my.admin.NotificationListener;
//import de.codecentric.boot.admin.server.config.AdminServerProperties;
//import io.netty.handler.codec.http.HttpMethod;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.util.*;
//import java.util.function.Consumer;
//import java.util.function.Function;

//@Configuration
public class ActuatorSecurity /*extends WebSecurityConfigurerAdapter*/ {

//    private final AdminServerProperties adminServer;
//
//    public ActuatorSecurity(AdminServerProperties adminServer) {
//        this.adminServer = adminServer;
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
//                .and()
//                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
//                .anyRequest()               // 任何请求,登录后可以访问
//                .authenticated();
////        http
////                .authorizeRequests()
////                .antMatchers("/", "/home").permitAll()
////                .antMatchers("/login").permitAll()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//    }
//
//    //代表所有权限
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().anyRequest().permitAll()
////                .and().csrf().disable();
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.userDetailsService(userDetailsService())
////                .requestMatcher(EndpointRequest.toAnyEndpoint())
////                .authorizeRequests()
////                .anyRequest().hasRole("ADMIN")
////                .and()
////                .httpBasic();
////    }
//
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
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user);
//    }

}
