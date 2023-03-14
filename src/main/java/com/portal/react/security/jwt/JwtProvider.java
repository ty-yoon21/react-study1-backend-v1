package com.portal.react.security.jwt;

import com.portal.react.persistence.entity.app.system.CustomUserDetails;
import com.portal.react.persistence.entity.app.system.SystemAuth;
import com.portal.react.security.redis.RedisUtil;
import com.portal.react.service.auth.JpaUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String salt;

    private Key secretKey;

    private final RedisUtil redisUtill;

    // 만료시간 : 1Hour
    //private final long jwtAccessExpire = 1000L * 60 * 60;
    // test 1분
    private final long jwtAccessExpire = 1000L * 60 * 2;

    public final static long jwtRefreshExpire = 1000L * 60 * 60 * 24;
    final static public String REFRESH_TOKEN_NAME = "jwtRefreshToken";

    private final JpaUserDetailsService userDetailsService;

    //JWT secret key는
    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractAllClaims(token).get("username", String.class);
    }

    public String getAccount(String token) {
        return extractAllClaims(token).get("account", String.class);
    }

    public String createAccessToken(String account, List<SystemAuth> roles) {
        return createToken(account, roles, jwtAccessExpire);
    }

    public String createRefreshToken(String account, List<SystemAuth> roles) {
        return createToken(account, roles, jwtRefreshExpire);
    }


    // 토큰 생성
    public String createAccessTokenByRefreshToken(String jwtRefreshToken) {
        String refreshRedisAccount = null;
        String refreshJwtAccount = null;
        if(jwtRefreshToken != null){
            refreshRedisAccount = redisUtill.getData(jwtRefreshToken);
            refreshJwtAccount = getAccount(jwtRefreshToken);
            if(refreshRedisAccount.equals(refreshJwtAccount)){
                CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByAccount(refreshRedisAccount);
                return createAccessToken(customUserDetails.getSystemUser().getAccount(), customUserDetails.getSystemUser().getRoles());
            }else {
                return null;
            }
        }
        return null;


            //String account, List<SystemAuth> roles, Long jwtExpire) {
/*        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpire))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();*/
    }

    // 토큰 생성
    public String createToken(String account, List<SystemAuth> roles, Long jwtExpire) {
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);
        claims.put("account", account);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpire))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 권한정보 획득
    // Spring Security 인증과정에서 권한확인을 위한 기능
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByAccount(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에 담겨있는 유저 account 획득
    public String getAccount2(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    // Authorization Header를 통해 인증을 한다.
    public String resolveToken(HttpServletRequest request) {return request.getHeader("Authorization");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            // Bearer 검증
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                return false;
            } else {
                token = token.split(" ")[1].trim();
            }
            System.out.println("######### token : "+token);
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰 검증
    public boolean validateRefreshToken(String token) {
        try {
            System.out.println("######### token : "+token);
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            // 만료되었을 시 false
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}