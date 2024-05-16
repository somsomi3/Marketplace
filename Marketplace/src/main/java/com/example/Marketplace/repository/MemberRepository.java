package com.example.Marketplace.repository;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Member안에다가 Entity내용을 만듬.
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String userName);
    //아이디를 기준으로 회원정보를 찾기위해서 사용

    boolean existsByUsername(String username);//회원가입을 할때 이미 존재하는 아이디인지 여부를 확인
}

