package com.pkrok.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "firm")
public class FirmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, unique = true, nullable = false)
    private String name;

}
