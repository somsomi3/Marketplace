package org.example.orderapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.orderapi.domain.product.AddProductItemForm;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EnableJpaAuditing
//@AuditOverride(forClass = BaseEntity.class) 왜 안되지,,,,
public class ProductItem extends BaseEntity{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long sellerId;

    private String name;

    private Integer price;

    private  Integer count;//count= 재고(수량), 무한하지 않음.

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public static ProductItem of(Long sellerId, AddProductItemForm form){//2개이상의 파라미터가 되면 from대신 of로 많이사용함
        return ProductItem.builder()
                .sellerId(sellerId)
                .name(form.getName())
                .price(form.getPrice())
                .count(form.getCount())
                .build();

    }
}
