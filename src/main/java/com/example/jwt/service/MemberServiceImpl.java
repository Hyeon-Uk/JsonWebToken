package com.example.jwt.service;

import com.example.jwt.dto.LoginDto;
import com.example.jwt.dto.MemberDto;
import com.example.jwt.dto.RegistDto;
import com.example.jwt.dto.Token;
import com.example.jwt.entity.Member;
import com.example.jwt.repository.MemberRepository;
import com.example.jwt.util.JwtProvider;
import com.example.jwt.util.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    //비밀번호 암호화는 했다고 가정하고 진행. jwt사용에 집중
    @Override
    public Token login(LoginDto dto) throws Exception {
        //email검증
        Member member = memberRepository
                .findByEmail(dto.getEmail())
                .orElseThrow(()->new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        //password검증
        if(!member.getPassword().equals(dto.getPassword())){
            throw new UserNotFoundException("해당 유저가 존재하지 않습니다.");
        }
        //어찌저찌 로그인 성공
        else{
            return Token.builder()
                    .accessToken(jwtProvider.createToken(new MemberDto(member)))
                    .build();
        }
    }


    //대충 회원가입 로직을 거쳐서 안전하게 저장된다고 가
    @Override
    public void register(RegistDto dto) {
        memberRepository.save(dto.toEntity());
    }
}
