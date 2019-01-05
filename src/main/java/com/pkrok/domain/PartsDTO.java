package com.pkrok.domain;

import com.pkrok.entity.FirmEntity;
import com.pkrok.entity.TypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


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

    private MultipartFile file;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate lastChanges;

    private String userChange;
}
