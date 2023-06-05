package com.sr.chatpanel.config.auth;

import com.sr.chatpanel.rest.auth.AuthenticationRequest;
import com.sr.chatpanel.rest.auth.AuthenticationResponse;
import com.sr.chatpanel.services.AuthenticationService;
import com.sr.chatpanel.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // TODO: fix cors
                .cors().disable()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/auth/**", "/v3/api-docs/**", "/docs",
                        "/docs/**", "/api/docs",
                        "/swagger-ui/**", "/actuator/**","/wssrv" , "/wssrv/**", "/api/wssrv/**", "/api/wssrv"
                )
                .permitAll()

                .anyRequest()
                .authenticated()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).cors()

                .and()
                .oauth2Login() //http://localhost:8000/api/oauth2/authorization/google
                .userInfoEndpoint()
                .userService(new DefaultOAuth2UserService())


                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                        userService.processOAuthPostLogin(oAuth2User);

                        AuthenticationResponse token = authenticationService.authenticate(new AuthenticationRequest(oAuth2User.getAttribute("email"), oAuth2User.getAttribute("sub")));

                        response.sendRedirect("http://localhost:3000/login?t=" + token.getToken() + "&e=" + token.getExpiresIn());

                    }
                });


        return http.build();
    }
}
