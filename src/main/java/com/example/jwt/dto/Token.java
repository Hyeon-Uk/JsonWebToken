package com.example.jwt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    private String accessToken;
}
