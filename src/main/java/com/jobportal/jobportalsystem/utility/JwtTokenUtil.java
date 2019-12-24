package com.jobportal.jobportalsystem.utility;

import com.jobportal.jobportalsystem.dto.JwtResponse;
import com.jobportal.jobportalsystem.dto.login.LoginDetailDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("secretkey")
    private String secret;

    private String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(LoginDetailDTO userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", userDetails);
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 10))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, LoginDetailDTO userDetails, HttpServletResponse response) {
        final String username = getUsernameFromToken(token);
        Date expiration = getExpirationDateFromToken(token);
        System.out.println("expiration==" + expiration);
        System.out.println("current date==" + new Date());
        long diff = expiration.getTime() - new Date().getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        System.out.println("=diffMinutes==" + diffMinutes);
        if (diffMinutes == 1) {
            System.out.println("diff consider");
            String refreshToken = generateToken(userDetails);
            Cookie cookie = new Cookie("refreshToken1",refreshToken);
            response.setHeader("token1",refreshToken);
//            Map<String, javax.ws.rs.core.Cookie> cookieHashMap=new HashMap<>();
//            cookieHashMap.put("refreshToken",cookie);
            JwtResponse jwtResponse=new JwtResponse();
            jwtResponse.setToken(refreshToken);
        }
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}

