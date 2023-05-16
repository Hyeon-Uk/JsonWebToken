package com.example.jwt.controller;

import com.example.jwt.dto.MemberDto;
import com.example.jwt.dto.Token;
import com.example.jwt.util.ApiUtils;
import com.example.jwt.util.JwtException;
import com.example.jwt.util.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.jwt.util.ApiUtils.*;

@RestController
@RequestMapping("/api/func")
@RequiredArgsConstructor
@Slf4j
/*
*
* 토큰으로 인증을 통과해야 올 수 있음
*
* */
public class FunctionController {
    private final JwtProvider jwtProvider;

    @GetMapping("/info")
    public ResponseEntity<ApiResult<MemberDto>> getTokenInfo(@RequestHeader(value="Authorization") String token) throws JwtException {

        System.out.println("token.accessToken = "+token);
        return success(jwtProvider.parseInfo(token.split(" ")[1]), HttpStatus.OK);
    }
}
