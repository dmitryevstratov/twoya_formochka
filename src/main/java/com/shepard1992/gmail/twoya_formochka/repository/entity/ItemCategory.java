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
@Table(name = "item_categories")
public class ItemCategory {

    @Id
    @SequenceGenerator(name = "itemCategoriesSeqGen", sequenceName = "item_categories_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemCategoriesSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Item> items;

}
