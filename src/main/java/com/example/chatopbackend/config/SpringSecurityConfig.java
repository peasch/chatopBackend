package com.example.chatopbackend.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;


@Configuration
@EnableWebSecurity

public class SpringSecurityConfig {

    private String jwtKey = "93def1f172b52fcc228e435f3e2d9af748f97fcb64a96d2dda901f134fe7f9bf8e501489978a6ba60642d646e6619e7f17de29bee187ba6165d4553caeee9a4946cb4712c92cb4a5f371bf25e90dc99f1929fcc6b067706268c7710b44f3bf4d187dfc37144aefac2dafb6ec77521711ae38354cccc5a2350ef4dd1f4a487db79ccb2e3127b9fd63f57035a431867790f58d11bb7b606a23083d121fc49fb08143411a50314fb0fe779e3681621bd60003f23774019b21f3b4a4dd6892a224c89cae0696cc754183ee76cf4eca19df93d9902839e5bc6a65c16e337c4cfb73227b52cdbe44bf64205478f04b0132d06266b928b12ff0088299acb483304dde37";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                            auth.requestMatchers("/admin").hasRole("ADMIN")
                                    .requestMatchers("/user").hasRole("USER")
                                    .requestMatchers("/login").permitAll()
                                    .requestMatchers("/register").permitAll()
                                    .anyRequest().authenticated();
                        }
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())).build();

    }


    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder().username("user@user")
                .password(passwordEncoder().encode("password"))
                .roles("USER").build();
        UserDetails admin = User.builder().username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN", "USER").build();
        return new InMemoryUserDetailsManager(user, admin);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret(this.jwtKey.getBytes()));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();

    }
}
