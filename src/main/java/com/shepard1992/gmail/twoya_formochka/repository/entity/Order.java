package com.shepard1992.gmail.twoya_formochka.repository.entity;

import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column
    @NotNull
    private ZonedDateTime dateCreate;

    @Column
    private ZonedDateTime dateClosed;

    @Column
    @NotNull
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")}
    )
    private List<Item> items = new ArrayList<>();

    @Column
    private Integer count = items.size();
}
