package com.shepard1992.gmail.twoya_formochka.repository.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Discount> discounts;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Order> orders;

}
