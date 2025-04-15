package br.senai.sp.defaultproject.usecases.board;

import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.mappers.board.BoardStructMapper;
import br.senai.sp.defaultproject.repositories.board.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllBoardsUseCase {
    private final BoardJpaRepository boardJpaRepository;
    private final BoardStructMapper boardStructMapper;

    @Transactional
    public Set<BoardOutputDTO> execute() {
        return new HashSet<>(boardStructMapper.toBoardOutputDTO(
                boardJpaRepository.findAll()
        ));
    }
}
