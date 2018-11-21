package com.pkrok.Domain;

import com.pkrok.Entity.FirmEntity;
import com.pkrok.Entity.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PartsDTO {

    private Long id;

    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 1, max = 60, message = "Name length should be between 1 and 60")
    private String name;

    @Min(value = 0, message = "Quantity min value 0")
    @Max(value = 10000)
    private int quantity;

    private String place;

    @DecimalMin(value = "0.0", message = "Min value 0")
    @DecimalMax(value = "99.999", message = "Max value 99.999")
    private BigDecimal price;

    private String description;

    private String image;
    //    private Date lastUpdate;

    @NotNull(message = "Field 'TYPE' can't be null")
    private TypeEntity type;

    @NotNull(message = "Field 'FIRM' can't be null")
    private FirmEntity firm;
}
