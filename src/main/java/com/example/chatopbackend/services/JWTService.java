package com.example.chatopbackend.services;


import com.example.chatopbackend.model.Dtos.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;


@Service
public class JWTService {

    private JwtEncoder jwtEncoder;
    public JWTService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;

    }

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

}
