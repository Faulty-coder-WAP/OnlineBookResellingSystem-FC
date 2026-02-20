package OnlineBookResellingSystem.OBRS_BackEnd.security;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.Filters.JwtFilter;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.FilterExceptionHandlers.customAccessDenaiedHandler;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.FilterExceptionHandlers.customAuthenticationEntryPoinyHandler;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

    @Autowired
    private customAuthenticationEntryPoinyHandler authenticationEntryPoinyHandler;

    @Autowired
    private customAccessDenaiedHandler accessDenaiedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity request)
    {
        return request
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/auth/login", "/api/register", "/books/getbooks").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/swagger-ui/**", "/v3/**", "/swagger-ui.html").hasRole("ADMIN")
                                .requestMatchers("/books/addbook", "/api/update_user/**").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/api/greet/**", "/api/get_users").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoinyHandler)
                        .accessDeniedHandler(accessDenaiedHandler))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
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
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(userDetails);
        dao.setPasswordEncoder(passwordEncoder());
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception
    {
        return configuration.getAuthenticationManager();
    }
}
