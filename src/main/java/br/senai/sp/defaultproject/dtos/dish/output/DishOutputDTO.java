package br.senai.sp.defaultproject.dtos.dish.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DishOutputDTO {
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;
}
