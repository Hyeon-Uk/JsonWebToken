package com.example.jwt.controller;

import com.example.jwt.util.ApiUtils;
import com.example.jwt.util.JwtException;
import com.example.jwt.util.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.jwt.util.ApiUtils.error;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiUtils.ApiResult<?>> userNotFoundException(UserNotFoundException e){
        return error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiUtils.ApiResult<?>> jwtException(JwtException e){
        return error(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
