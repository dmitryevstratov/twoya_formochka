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
@Table(name = "item_types")
public class ItemType {

    @Id
    @SequenceGenerator(name = "itemTypesSeqGen", sequenceName = "item_types_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemTypesSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private List<Item> items;

}
