package com.arm.api.hero.auth.service;

import com.arm.api.hero.model.support.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Component
public class JWTServiceImpl implements JWTService {
    @Override
    public String create(Authentication auth) throws JsonProcessingException {
        String username = auth.getName();

        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

        Claims claims = Jwts.claims();
        claims.put("authorities", new ObjectMapper().writeValueAsString(roles));

        //Con signWith firmo el token
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .signWith(JWTConstant.SECRET_KEY)
                .setIssuedAt(new Date())//Fecha creacion
                .setExpiration(new Date(System.currentTimeMillis() + JWTConstant.EXPIRATION_DATE))//Fecha expiracion Token (3600000 => 1 hora)
                .compact();

        return token;
    }

    @Override
    public boolean validate(String token) {

        try {
            //Con esto validamos si el token es valido (Jwts.parser())
            getClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }


    }

    @Override
    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWTConstant.SECRET_KEY)
                .parseClaimsJws(resolve(token))
                .getBody();

        return claims;
    }

    @Override
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {
        Object roles = getClaims(token).get("authorities");

        Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
                .readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

        return authorities;
    }

    @Override
    public String resolve(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.replace(JWTConstant.TOKEN_PREFIX, "");
        }

        return null;
    }
}


