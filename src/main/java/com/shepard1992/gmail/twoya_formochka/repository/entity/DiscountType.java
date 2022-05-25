package com.shepard1992.gmail.twoya_formochka.repository.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discountType")
public class DiscountType {

    @Id
    @SequenceGenerator(name = "discountTypeSeqGen", sequenceName = "discount_types_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountTypeSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Discount> discounts;

    public void deleteDiscount(Discount discount) {
        if (discounts != null) {
            discounts.remove(discount);
        }
    }

}
