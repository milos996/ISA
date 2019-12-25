package com.example.ISAums.security;

import com.example.ISAums.config.CustomProperties;
import com.example.ISAums.exception.CustomException;
import com.example.ISAums.model.User;
import com.example.ISAums.model.enumeration.Role;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final CustomProperties customProperties;

    public JwtTokenUtil(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    public String generateAuthToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + customProperties.getJwtExpirationInMs());
        Claims claims = Jwts.claims().setSubject(user.getId().toString());
        claims.put("role", user.getAuthorities()
                .stream()
                .findFirst()
                .orElseThrow(() -> new CustomException("Role not found!"))
                .getAuthority());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, customProperties.getJwtSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(customProperties.getJwtSecret()).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

    public UUID getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(customProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        return UUID.fromString(claims.getSubject());
    }

    public Role getRoleFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(customProperties.getJwtSecret())
                .parseClaimsJws(token)
                .getBody();

        return Role.valueOf((String)claims.get("role"));
    }
}

