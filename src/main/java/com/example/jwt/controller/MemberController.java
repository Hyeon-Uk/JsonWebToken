package com.example.jwt.controller;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.RegistDto;
import com.example.jwt.dto.Token;
import com.example.jwt.service.MemberService;
import com.example.jwt.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.jwt.util.ApiUtils.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResult<Boolean>> signUp(@RequestBody RegistDto registDto){
        memberService.register(registDto);
        return success(true, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResult<Token>> signIn(@RequestBody LoginDto loginDto) throws UserNotFoundException {
        return success(memberService.login(loginDto),HttpStatus.OK);
    }
}
