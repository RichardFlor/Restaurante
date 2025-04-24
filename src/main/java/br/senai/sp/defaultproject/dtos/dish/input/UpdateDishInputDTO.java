package br.senai.sp.defaultproject.dtos.dish.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDishInputDTO {
    private String name;

    private String description;

    private BigDecimal price;

}
