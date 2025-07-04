package br.senai.sp.defaultproject.dtos.board.input;

import br.senai.sp.defaultproject.enums.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoardInputDTO {
    private BoardStatus boardStatus;
}
