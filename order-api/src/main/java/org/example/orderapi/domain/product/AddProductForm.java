package org.example.orderapi.domain.product;

import lombok.*;

import java.util.List;

@Getter
@Builder//getter만 쓰고 나머지는 직접 짜보자.
@NoArgsConstructor
@AllArgsConstructor
public class AddProductForm {

    private String name;
    private String description;

    private List<AddProductItemForm> addProductItemForms;
}
