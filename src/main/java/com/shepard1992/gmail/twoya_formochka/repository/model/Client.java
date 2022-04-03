package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String secondName;

    @Column
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="address_id")
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
