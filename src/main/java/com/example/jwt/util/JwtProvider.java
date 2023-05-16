package com.example.jwt.util;

import com.example.jwt.dto.MemberDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Value("${jwt.exp}")
    private long exp;

    public String createToken(MemberDto member) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject("userToken")
                .setExpiration(new Date(System.currentTimeMillis()+exp))
                .claim("id",member.getId())
                .claim("email",member.getEmail())
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY.getBytes())
                .compact();
    }

    public MemberDto parseInfo(String accessToken) throws JwtException {
        Jws<Claims> claims = null;
        try{
            MemberDto info = new MemberDto();
            claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(accessToken);
            info.setId(claims.getBody().get("id",Long.class));
            info.setEmail(claims.getBody().get("email",String.class));
            return info;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            //만료시간 지남
            //jwt가 예상하는 형식과 다른 형식이거나 구성
            //잘못된 jwt구조
            //JWT의 서명실패(변조 데이터)
            throw new JwtException("다시 로그인 해주세요");
        }
    }

    public boolean isValidToken(String accessToken){
        try{
            Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(accessToken);
            return true;
        }catch(SignatureException e){
            log.error("Invalid JWT Signature",e);
        }catch(MalformedJwtException e){
            log.error("Invalid JWT Token",e);
        }catch(ExpiredJwtException e){
            log.error("Expired JWT token",e);
        }catch(UnsupportedJwtException e){
            log.error("Unsupported JWT token",e);
        }catch(IllegalArgumentException e){
            log.error("JWT claims string is empty",e);
        }
        return false;
    }
}
