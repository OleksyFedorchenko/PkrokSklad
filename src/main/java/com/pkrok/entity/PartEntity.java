package com.pkrok.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Entity
@Table(name = "parts")
public class PartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @Column
    private String place;

    @Column(columnDefinition = "DECIMAL(5,3)")
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image;

//    @Column(nullable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToOne
    @JoinColumn(name = "firm_id")
    private FirmEntity firm;

    @Column
    private LocalDateTime lastChanges;

    @Column
    private String userChange;

}
