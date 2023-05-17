package com.example.jwt.service;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.RegistDto;
import com.example.jwt.dto.Token;
import com.example.jwt.util.UserNotFoundException;

public interface MemberService {
    Token login(LoginDto dto) throws Exception;
    void register(RegistDto dto);
}
