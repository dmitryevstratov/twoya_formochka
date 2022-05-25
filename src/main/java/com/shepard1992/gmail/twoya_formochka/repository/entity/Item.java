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
@Table(name = "items")
public class Item {

    @Id
    @SequenceGenerator(name = "itemsSeqGen", sequenceName = "items_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemsSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "item_type_id")
    private ItemType type;

    @ManyToOne
    @JoinColumn(name = "item_category_id")
    private ItemCategory category;

    @Column
    @NotNull
    private Double size;

    @Column
    @NotNull
    private Double price;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;

}
