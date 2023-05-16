package com.example.jwt.dto;

import com.example.jwt.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long id;
    private String email;

    public MemberDto(Member entity){
        setId(entity.getId());
        setEmail(entity.getEmail());
    }
}
