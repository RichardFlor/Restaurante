package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.dtos.board.input.UpdateBoardInputDTO;
import br.senai.sp.defaultproject.dtos.board.output.BoardOutputDTO;
import br.senai.sp.defaultproject.rest.specs.BoardControllerSpecs;
import br.senai.sp.defaultproject.usecases.board.CreateBoardUseCase;
import br.senai.sp.defaultproject.usecases.board.FindAllBoardsUseCase;
import br.senai.sp.defaultproject.usecases.board.UpdateBoardUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController implements BoardControllerSpecs {
    private final CreateBoardUseCase createBoardUseCase;
    private final FindAllBoardsUseCase findAllBoardsUseCase;
    private final UpdateBoardUseCase updateBoardUseCase;

    @PostMapping()
    public void create(@RequestBody @Valid CreateBoardInputDTO request){
        createBoardUseCase.execute(request);
    }

    @GetMapping()
    public Set<BoardOutputDTO> findAll(){return findAllBoardsUseCase.execute();}

    @PutMapping("/{id}")
    public void update(@RequestBody @Valid UpdateBoardInputDTO request, @PathVariable UUID id){
        updateBoardUseCase.execute(request, id);
    }
}
