package org.example.orderapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.orderapi.domain.product.AddProductForm;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@AuditOverride(forClass = BaseEntity.class)
@EnableJpaAuditing// @Audited가 안되서..
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long sellerId;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private List<ProductItem> productItems = new ArrayList<>();


    public static Product of(Long sellerId, AddProductForm form){
        return Product.builder()
                .sellerId(sellerId)
                .name(form.getName())
                .description(form.getDescription())
                .productItems(form.getAddProductItemForms().stream()
                        .map(piForm->ProductItem.of(sellerId,piForm)).collect(Collectors.toList()))
                .build();

    }

}
