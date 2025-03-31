package com.udemy.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.udemy.value.object.TokenVO;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class TokenHandler {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire.length:3600000}")
    private Long validityInMilliseconds;

    @Autowired
    UserDetailsService userDetailsService;

    Algorithm algorithm;
    JWTVerifier verifier;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
        verifier = JWT.require(algorithm).build();
    }

    public TokenVO createTokenVO(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String accessToken = getAccessToken(username, roles, now, validity);
        String refreshToken = getRefreshToken(username, roles, now);

        TokenVO tokenVO = new TokenVO();
        tokenVO.setUsername(username);
        tokenVO.setAccessToken(accessToken);
        tokenVO.setRefreshToken(refreshToken);
        tokenVO.setCreated(now);
        tokenVO.setExpiration(validity);
        tokenVO.setAuthenticated(true);

        return tokenVO;
    }

    public TokenVO renewToken(String refreshToken){
        String tokenWithoutBearer = refreshToken.substring("Bearer ".length());
        DecodedJWT decoded = verifier.verify(tokenWithoutBearer);

        return createTokenVO(decoded.getSubject(),decoded.getClaim("roles").asList(String.class));
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decoded = verifier.verify(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(decoded.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest req) {
        try {
            String bearer = req.getHeader("Authorization");
            return bearer.substring("Bearer ".length());

        } catch (NullPointerException e) {
            System.out.println("Usuario ainda nao autenticado");
        }

        return "";
    }

    public Boolean validateToken(String token){
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getExpiresAt().after(new Date());

        } catch (JWTVerificationException jwtVerificationException) {
            return false;
        }
    }
    
    private String getAccessToken(String username, List<String> roles, Date now, Date validate) {
        String issuerURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles",roles)
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(validate)
                .withIssuer(issuerURL)
                .sign(algorithm)
                .trim();
    }

    private String getRefreshToken(String username, List<String> roles, Date now) {
        Date validityRefreshToken = new Date(now.getTime() + validityInMilliseconds);
        return JWT.create()
                .withClaim("roles", roles)
                .withExpiresAt(validityRefreshToken)
                .withSubject(username)
                .sign(algorithm)
                .trim();
    }

}
