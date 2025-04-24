package br.senai.sp.defaultproject.usecases.dish;

import br.senai.sp.defaultproject.dtos.dish.input.UpdateDishInputDTO;
import br.senai.sp.defaultproject.entities.Dish;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.dish.DishJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateDishUseCase {
    private final DishJpaRepository dishJpaRepository;

    public void execute(UpdateDishInputDTO input, UUID id){
        var dish = dishJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Dish.class));

        dish.setName(input.getName());
        dish.setDescription(input.getDescription());
        dish.setPrice(input.getPrice());

        log.info("Editing dish infos with id: {}", dish.getId());

        dishJpaRepository.save(dish);
    }
}
