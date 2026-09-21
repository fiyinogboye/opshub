package dev.fiyin.opshub.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
    @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
    @Bean UserDetailsService users(PasswordEncoder encoder){
        UserDetails staff=User.withUsername("staff").password(encoder.encode("demo1234")).roles("STAFF").build();
        UserDetails lead=User.withUsername("lead").password(encoder.encode("demo1234")).roles("LEAD").build();
        UserDetails admin=User.withUsername("admin").password(encoder.encode("demo1234")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(staff,lead,admin);
    }
    @Bean SecurityFilterChain security(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(a->a.requestMatchers("/login","/css/**","/js/**").permitAll().anyRequest().authenticated())
            .formLogin(f->f.loginPage("/login").defaultSuccessUrl("/",true).permitAll())
            .logout(l->l.logoutSuccessUrl("/login?logout"));
        return http.build();
    }
}
