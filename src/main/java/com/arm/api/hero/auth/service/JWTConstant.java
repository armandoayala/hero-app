package com.arm.api.hero.auth.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JWTConstant
{
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final long EXPIRATION_DATE=86400000;
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_AUTH="Authorization";
}
