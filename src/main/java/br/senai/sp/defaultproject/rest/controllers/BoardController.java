package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.rest.specs.BoardControllerSpecs;
import br.senai.sp.defaultproject.usecases.board.CreateBoardUseCase;
import br.senai.sp.defaultproject.usecases.board.FindAllBoardsUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements BoardControllerSpecs {
    private final CreateBoardUseCase createBoardUseCase;
    private final FindAllBoardsUseCase findAllBoardsUseCase;

    @PostMapping()
    public void create(@RequestBody @Valid CreateBoardInputDTO request){
        createBoardUseCase.execute(request);
    }

    @GetMapping()
    public Set<BoardOutputDTO> findAll(){return findAllBoardsUseCase.execute();}
}
