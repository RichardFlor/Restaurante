package br.senai.sp.defaultproject.usecases.board;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.mappers.board.BoardStructMapper;
import br.senai.sp.defaultproject.repositories.board.BoardJpaRepository;
import br.senai.sp.defaultproject.usecases.user.FindUserByIdUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateBoardUseCase {
    private final BoardJpaRepository boardJpaRepository;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final BoardStructMapper boardStructMapper;

    @Transactional
    public void execute(CreateBoardInputDTO input){
        var board = this.boardStructMapper.toEntity(input);

        board.setCreatedAt(LocalDateTime.now());

        log.warn("Creating board with number: {}", input.getBoardNumber());
        boardJpaRepository.save(board);
    }
}
