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
@Table(name = "reports")
public class Report {

    @Id
    @SequenceGenerator(name = "reportsSeqGen", sequenceName = "reports_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportsSeqGen")
    private Integer id;

    @Column
    @NotNull
    private Integer year;

    @Column
    @NotNull
    private Integer quarter;

    @Column
    @NotNull
    private Double income;

    @Column
    @NotNull
    private Integer tax;

    @Column
    @NotNull
    private Double sumForTax;

    @Column
    @NotNull
    private Double clearIncome;

}
