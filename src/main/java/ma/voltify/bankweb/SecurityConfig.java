package ma.voltify.bankweb;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
@Data
public class SecurityConfig {
    // @Autowired
    private PasswordEncoder passwordencoder;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(withDefaults());
        // http.anonymous();
        // http.csrf().disable();
        // http.headers().frameOptions().disable();
        http.headers(headers -> headers.frameOptions().disable());
        http.authorizeHttpRequests().requestMatchers("/accounts/**").hasRole("ADMIN");
        http.authorizeHttpRequests().requestMatchers("/customers/**").hasAnyRole("ADMIN", "USER");
        http.authorizeHttpRequests().requestMatchers("/users/**").hasRole("ADMIN");
        // .hasRole("ADMIN");
        // http.authorizeHttpRequests().anyRequest().authenticated();
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // http.exceptionHandling(handling -> handling.accessDeniedPage("/authorized"));
        return http.build();
    }

    // @Bean
    public UserDetailsService loadUserByUserName(String username)
            throws Exception {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Collection<GrantedAuthority> authorites = new ArrayList<>();
                authorites.add(new SimpleGrantedAuthority("ADMIN"));
                return new User("ANAS", "1234", authorites);
            }

        };

    }

    // @Bean
    // public AuthenticationManager authenticationManager(HttpSecurity http,
    // BCryptPasswordEncoder bCryptPasswordEncoder,
    // UserDetailsService userDetailService)
    // throws Exception {
    // return http.getSharedObject(AuthenticationManagerBuilder.class)
    // .userDetailsService(userDetailService)
    // .passwordEncoder(bCryptPasswordEncoder)
    // .and()
    // .build();
    // }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        System.out.println("Called inMemoryUserDetailsManager");
        return new InMemoryUserDetailsManager(
                User.withUsername("Anas").password(passwordencoder.encode("1234")).roles("USER").build(),
                User.withUsername("Hamza").password(passwordencoder.encode("1234")).roles("ADMIN").build());
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**",
                "/webjars/**");
    }
}
