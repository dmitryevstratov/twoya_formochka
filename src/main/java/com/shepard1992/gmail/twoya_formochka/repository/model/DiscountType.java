package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "discountType")
public class DiscountType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Discount> discount;

}
