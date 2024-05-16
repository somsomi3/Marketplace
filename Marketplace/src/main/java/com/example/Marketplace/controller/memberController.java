//API를 이용하여 회원가입과 로그인 기능을 구현함.
//이렇게 구현한다고 해서 바로 사용할 수는 없으므로, 실제로 로그인한사용자만 접근할수 있는 API를 정해주고, 로그인하지 않은 사용자도 접근할 수 있는 API를 지정해주고,, 접근제한, 통제 등을 구현해줘야함.
//--> JWTA~~Filer클래스 구현하기

package com.example.Marketplace.controller;

import com.example.Marketplace.model.Auth;
import com.example.Marketplace.security.TokenProvider;
import com.example.Marketplace.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
@Transactional
public class memberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request){

        var result = this.memberService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request){
        //로그인용 API
        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
        return ResponseEntity.ok(token);
    }
}
