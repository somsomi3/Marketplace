package com.example.Marketplace.domain;

import com.example.Marketplace.status.MemberStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "MEMBER")
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<Member> roles = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "member")
    private List<Store> store = new ArrayList<>();


}
