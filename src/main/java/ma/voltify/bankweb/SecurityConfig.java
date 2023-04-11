package ma.voltify.bankweb;

// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Configuration
@EnableWebSecurity
// @AllArgsConstructor
// @Data
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordencoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin();
        // http.anonymous();
        // http.csrf().disable();
        // http.headers().frameOptions().disable();
        http.authorizeHttpRequests().requestMatchers("/accounts/**").hasRole("ADMIN");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/authorized");
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("Anas").password(passwordencoder.encode("1234")).roles("USER").build(),
                User.withUsername("Hamza").password(passwordencoder.encode("1234")).roles("USER", "ADMIN").build());
    }
    // @Bean
    // public WebSecurityCustomizer webSecurityCustomizer() {

    // }
}
