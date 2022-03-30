package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String country;

    @Column
    private String region;

    @Column
    private String locality;

    @Column
    private String street;

    @Column
    private Integer room;

    @Column
    private Integer index;

}
