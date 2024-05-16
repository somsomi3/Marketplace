package com.example.Marketplace.service;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.model.Auth;
import com.example.Marketplace.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional

public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));

    }

    public Member register(Auth.SignUp member) {//회원가입에대한메서드
        boolean exists = this.memberRepository.existsByUsername(member.getUsername());
        if (exists) {//이미 존재한다면,
            throw new RuntimeException("이미 사용중인 아이디 입니다.");
        }
        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        var reasult = this.memberRepository.save(member.toEntity());

        return reasult;
    }

    public Member authenticate(Auth.SignIn member) {//로그인 할때, 검증을 하기위한 메서드
        var user = this.memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new RuntimeException("존재 하지 않는 아이디야~"));
        if (!this.passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }
}
