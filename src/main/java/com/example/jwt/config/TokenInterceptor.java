package com.example.jwt.config;

import com.example.jwt.util.Encrypt;
import com.example.jwt.util.JwtException;
import com.example.jwt.util.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;
    private final Encrypt encrypt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //bearer 검증
        String authorization = request.getHeader("Authorization");
        if(authorization == null){
            throw new JwtException("안됩니다1.");
        }

        String[] tokens = authorization.split(" ");
        if(tokens.length!=2){
            throw new JwtException("안됩니다2.");
        }
        if(!jwtProvider.isValidToken(tokens[1])){
            throw new JwtException("안됩니다3.");
        }

        return true;
    }
}
