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
@Table(name = "clients")
public class Client {

    @Id
    @SequenceGenerator(name = "clientsSeqGen", sequenceName = "clients_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientsSeqGen")
    private Integer id;

    @Column
    @NotNull
    private String firstName;

    @Column
    @NotNull
    private String lastName;

    @Column
    private String secondName;

    @Column
    @NotNull
    private String birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @NotNull
    private Address address;

    @Column
    private String email;

    @Column
    private String telephone;

    @ManyToMany
    @JoinTable(
            joinColumns = {@JoinColumn(name = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "discount_id")}
    )
    private List<Discount> discounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Order> orders;

}
