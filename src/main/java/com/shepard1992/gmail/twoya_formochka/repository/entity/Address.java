package com.shepard1992.gmail.twoya_formochka.repository.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @SequenceGenerator(name = "addressesSeqGen", sequenceName = "address_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressesSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String country;

    @Column
    @NotNull
    private String region;

    @Column
    @NotNull
    private String locality;

    @Column
    @NotNull
    private String street;

    @Column
    private Integer room;

    @Column
    private Integer index;

    @Column
    private String additionalInfo;

    @OneToOne(mappedBy = "address")
    @NotNull
    private Client client;

}
