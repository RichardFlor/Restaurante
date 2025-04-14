package br.senai.sp.defaultproject.dtos.board.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class BoardOutputDTO {
    private UUID id;

    @Schema(example = "Mesa numero 1")
    private String boardNumber;
}
