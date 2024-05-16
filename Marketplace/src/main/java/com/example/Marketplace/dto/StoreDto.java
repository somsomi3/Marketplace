package com.example.Marketplace.dto;

import com.example.Marketplace.domain.Member;
import com.example.Marketplace.domain.Store;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;
    private Long memberId;
    private String name;
    private String location;
    private String description;
    private double x;
    private double y;

    public static Store toStoreEntity(Member member, StoreDto storeDto) {

        return new Store().builder()
                .member(member)
                .name(storeDto.getName())
                .location(storeDto.getLocation())
                .description(storeDto.getDescription())
                .x(storeDto.getX())
                .y(storeDto.getY())
                .createdAt(LocalDateTime.now())
                .build();
    }
}