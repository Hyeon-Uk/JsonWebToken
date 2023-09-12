package com.example.jwt;

import com.example.jwt.dto.MemberDto;
import com.example.jwt.util.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UnitTest {
    @Value("${jwt.secret_key}")
    private String SECRET_KEY;

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("can't parse with origin jwtParser")
    void canNotParseWithOriginJwtParser() throws Exception {
        String token = jwtProvider.createToken(MemberDto.builder()
                .id(1l)
                .email("test@gmail.com")
                .build()
        );

        assertThrows(Exception.class,()->{
            Jwts.parserBuilder().setSigningKey(SECRET_KEY)
                    .build().parse(token);
        });
    }

}
