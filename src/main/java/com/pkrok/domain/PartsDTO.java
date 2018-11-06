package com.pkrok.domain;

import com.pkrok.entity.FirmEntity;
import com.pkrok.entity.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class PartsDTO {
    private Long id;
    private String name;
    private int quantity;
    private String place;
    private BigDecimal price;
    private String description;
    private String image;
//    private Date lastUpdate;
    private TypeEntity type;
    private FirmEntity firm;
}
