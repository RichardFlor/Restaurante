package br.senai.sp.defaultproject.usecases.board;

import br.senai.sp.defaultproject.dtos.board.input.UpdateBoardInputDTO;
import br.senai.sp.defaultproject.entities.Board;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.board.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateBoardUseCase {
    private final BoardJpaRepository boardJpaRepository;

    public void execute(UpdateBoardInputDTO input, UUID id){
        var board = boardJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Board.class));

        board.setBoardStatus(input.getBoardStatus());

        log.info("Editing board status with id: {}", board.getId());

        boardJpaRepository.save(board);
    }
}
