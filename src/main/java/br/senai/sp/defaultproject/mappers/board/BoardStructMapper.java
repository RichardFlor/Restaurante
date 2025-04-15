package br.senai.sp.defaultproject.mappers.board;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.entities.Board;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BoardStructMapper {
    //@Mapping(source = "status", target = "boardStatus")
    BoardOutputDTO toBoardOutputDTO(Board entity);

    List<BoardOutputDTO> toBoardOutputDTO(List<Board> entities);

   // @Mapping(source = "boardStatus", target = "status")
    Board toEntity(CreateBoardInputDTO dto);
}

