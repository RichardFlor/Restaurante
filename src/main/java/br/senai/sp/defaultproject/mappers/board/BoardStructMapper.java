package br.senai.sp.defaultproject.mappers.board;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.entities.Board;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface BoardStructMapper {
    BoardOutputDTO toBoardOutputDTO(Board entity);
    @Mapping(source = "boardStatus", target = "status")
    Board toEntity(CreateBoardInputDTO dto);
}

