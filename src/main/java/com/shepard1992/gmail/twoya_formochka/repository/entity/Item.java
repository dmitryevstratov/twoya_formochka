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
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
