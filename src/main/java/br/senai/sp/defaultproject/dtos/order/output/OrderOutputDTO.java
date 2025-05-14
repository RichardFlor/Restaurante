package br.senai.sp.defaultproject.dtos.order.output;

import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.dtos.dish.output.DishOutputDTO;
import br.senai.sp.defaultproject.dtos.user.output.UserDetailedOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class OrderOutputDTO {
    private UUID id;
    private UserDetailedOutputDTO user;
    private BoardOutputDTO board;
    private List<DishOutputDTO> dishes;

}
