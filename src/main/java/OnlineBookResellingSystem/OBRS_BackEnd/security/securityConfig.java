package OnlineBookResellingSystem.OBRS_BackEnd.security;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.Filters.JwtFilter;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig
{
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private CustomUserDetailsService userDetails;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity request)
    {
        return request
                .csrf(csrf-> csrf.disable())
                .sessionManagement(ses->ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req->
                        req.requestMatchers("/auth/login","/api/register").permitAll()
                                .requestMatchers("/api/greet","/books/addbook").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form-> form.disable())
                .httpBasic(basic-> basic.disable())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider dao=new DaoAuthenticationProvider(userDetails);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
        return  configuration.getAuthenticationManager();
    }

}
