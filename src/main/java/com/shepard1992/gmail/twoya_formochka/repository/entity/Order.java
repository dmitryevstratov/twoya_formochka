package com.shepard1992.gmail.twoya_formochka.repository.entity;

import com.shepard1992.gmail.twoya_formochka.repository.entity.enums.StatusOrder;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "ordersSeqGen", sequenceName = "orders_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordersSeqGen")
    private Integer id;

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

    public void deleteDiscount(Discount discount) {
        if (this.discount != null) {
            if (this.discount.getId().equals(discount.getId())) this.discount = null;
        }
    }

    public void deleteItem(Item item) {
        if (this.items != null) {
            Optional<Item> optionalItem = items.stream().filter(it -> it.getId().equals(item.getId())).findFirst();
            optionalItem.ifPresent(value -> items.remove(value));
        }
    }
}
