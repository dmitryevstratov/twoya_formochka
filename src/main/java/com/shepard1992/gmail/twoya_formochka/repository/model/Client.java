package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Data
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

    //private Address address;

    @Column
    private String email;

    @Column
    private String telephone;

    //private Set<Discount> discounts;

    //private List<Order> orders;

}
