package br.senai.sp.defaultproject.dtos.order.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderInputDTO {

    private UUID userId;

    private UUID dishId;

    private UUID boardId;

}
