package com.shepard1992.gmail.twoya_formochka.repository.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discount_type_id")
    @NotNull
    private DiscountType type;

    @Column
    @NotNull
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
