package br.senai.sp.defaultproject.dtos.board.input;

import br.senai.sp.defaultproject.enums.BoardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardInputDTO {
    @NotBlank
    private String boardNumber;

    @NotNull
    private BoardStatus boardStatus;

}
