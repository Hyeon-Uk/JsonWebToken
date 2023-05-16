package com.example.jwt.dto;

import com.example.jwt.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistDto {
    private String email;
    private String password;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
