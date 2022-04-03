package com.shepard1992.gmail.twoya_formochka.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
