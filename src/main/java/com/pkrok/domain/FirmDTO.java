package com.pkrok.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class FirmDTO {
    private Long id;
    @NotNull(message = "Field 'NAME' can't be null")
    @Size(min = 1, max = 60, message = "Name length should be between 1 and 60")
    private String name;
}
