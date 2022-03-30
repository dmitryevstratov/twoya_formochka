package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private DiscountType type;

    @Column
    private Integer value;

}
