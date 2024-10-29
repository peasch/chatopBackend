package com.example.chatopbackend.services;


import com.example.chatopbackend.config.CustomUserDetailsService;
import com.example.chatopbackend.model.Dtos.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class JWTService {

    private final JwtEncoder jwtEncoder;
    private final CustomUserDetailsService userDetailsService;

    @Value("${security.jwt.secret-key}")
    private String jwtKey;

    public String generateToken(UserDto userDto) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1,ChronoUnit.DAYS))
                .subject(userDto.getEmail())
                .build();
        JwtEncoderParameters jwtEncoderParameters =
                JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public String resolveToken(HttpServletRequest req) { // Méthode qui va extraire de la requête Http le token.
        String bearerToken = req.getHeader("Authorization");
        System.out.println(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
