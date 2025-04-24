package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.dish.input.CreateDishInputDTO;
import br.senai.sp.defaultproject.dtos.dish.input.UpdateDishInputDTO;
import br.senai.sp.defaultproject.dtos.dish.output.DishOutputDTO;
import br.senai.sp.defaultproject.rest.specs.DishControllerSpecs;
import br.senai.sp.defaultproject.usecases.dish.CreateDishUseCase;
import br.senai.sp.defaultproject.usecases.dish.FindAllDishesUseCase;
import br.senai.sp.defaultproject.usecases.dish.UpdateDishUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishController implements DishControllerSpecs {
    private final CreateDishUseCase createDishUseCase;
    private final FindAllDishesUseCase findAllDishesUseCase;
    private final UpdateDishUseCase updateDishUseCase;

    @PostMapping()
    public void create(@RequestBody @Valid CreateDishInputDTO request){
        createDishUseCase.execute(request);
    }

    @GetMapping()
    public Set<DishOutputDTO> findAll(){
        return findAllDishesUseCase.execute();
    }

    @PutMapping("/{id}")
    public void update(@RequestBody @Valid UpdateDishInputDTO request, @PathVariable UUID id){
        updateDishUseCase.execute(request, id);
    }
}
