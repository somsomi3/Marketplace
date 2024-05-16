package com.example.Marketplace.model;
////회원가입을 했을때, 멤버엔티티를 기준으로 해서 회원정보를 저장할 수 있다.
////로그인 할때도, 멤버엔티티에서 정보를 가져올 수 있다.-> 멤버 레포짓토리 만들기

import com.example.Marketplace.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MEMBER")

public class MemberEntity implements UserDetails {
    //implements UserDetails을 써줘라.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;

    private String username;

    private String password;
    @OneToMany(mappedBy = "member")
    private List<String> roles;//오류나서 member안에 엔티티 내용도 넣음

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}
