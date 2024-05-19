package com.example.Marketplace.service;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.model.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    Company save(String ticker);

    Company storeCompanyAndDividend(String ticker);

    Page<Member> getAllCompany(Pageable pageable);

    //자동 완성 Like 추가
    List<String> getCompanyNamesByKeyword(String keyword);

    //자동 완성 추가
    void addAutocompleteKeyword(String keyword);

    //자동 완성 조회
    List<String> autocomplete(String keyword);

    String deleteCompany(String ticker);
}

