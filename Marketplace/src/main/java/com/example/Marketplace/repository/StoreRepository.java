package com.example.Marketplace.repository;

import com.example.Marketplace.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Transactional
    void deleteById(Long id);

    Page<Store> findByMemberId(Long memberId, Pageable pageable);


    Optional<Store> findByName(String name);


}

