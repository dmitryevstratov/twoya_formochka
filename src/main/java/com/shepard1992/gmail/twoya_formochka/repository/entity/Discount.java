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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "discount_type_id")
    @NotNull
    private DiscountType type;

    @Column
    @NotNull
    private Integer value;

    @OneToMany(mappedBy = "discount")
    private List<Order> orders;

    @ManyToMany(mappedBy = "discounts")
    private List<Client> clients;

}
