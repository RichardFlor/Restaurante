package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.board.input.CreateBoardInputDTO;
import br.senai.sp.defaultproject.rest.specs.BoardControllerSpecs;
import br.senai.sp.defaultproject.usecases.board.CreateBoardUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements BoardControllerSpecs {
    private final CreateBoardUseCase createBoardUseCase;

    @PostMapping()
    public void create(@RequestBody @Valid CreateBoardInputDTO request){
        createBoardUseCase.execute(request);
    }
}
