package com.example.Marketplace.controller;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.model.Company;
import com.example.Marketplace.service.CompanyService;

import lombok.AllArgsConstructor;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    // CompanyService안에 @Service를 안적어줘서, 두번째 companyService에 밑줄이 그였었음.

    @GetMapping
    @PreAuthorize("hasRole('READ')")
    public ResponseEntity<?> searchCompany(final Pageable pageable) {
        Page<Member> companyEntities = this.companyService.getAllCompany(pageable);
        return ResponseEntity.ok(companyEntities);
    }


    @PostMapping
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> addCompany( @Validated @RequestBody Company request) {
        String ticker = request.getTicker().trim();
        if (ObjectUtils.isEmpty(ticker)) {
            throw new RuntimeException("ticker is empty");
        }

        Company savedCompany = this.companyService.save(ticker);
        this.companyService.addAutocompleteKeyword(savedCompany.getName());

        return ResponseEntity.ok(savedCompany);
    }

    @DeleteMapping("/{ticker}")
    @PreAuthorize("hasRole('WRITE')")
    public ResponseEntity<?> deleteCompany(@PathVariable String ticker) {
        String companyName = this.companyService.deleteCompany(ticker);

        return ResponseEntity.ok(companyName);
    }


}