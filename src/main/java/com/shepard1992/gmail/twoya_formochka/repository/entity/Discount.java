package com.shepard1992.gmail.twoya_formochka.repository.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @SequenceGenerator(name = "discountsSeqGen", sequenceName = "discounts_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discountsSeqGen")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "discount_type_id")
    private DiscountType type;

    @Column
    @NotNull
    private Integer value;

    @OneToMany(mappedBy = "discount")
    private List<Order> orders;

    @ManyToMany(mappedBy = "discounts")
    private List<Client> clients;

}
