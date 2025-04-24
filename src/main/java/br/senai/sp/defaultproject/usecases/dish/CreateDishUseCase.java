package br.senai.sp.defaultproject.usecases.dish;

import br.senai.sp.defaultproject.dtos.dish.input.CreateDishInputDTO;
import br.senai.sp.defaultproject.mappers.dish.DishStructMapper;
import br.senai.sp.defaultproject.repositories.dish.DishJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateDishUseCase {
    private final DishJpaRepository dishJpaRepository;
    private final DishStructMapper dishStructMapper;

    @Transactional
    public void execute(CreateDishInputDTO input){
        var dish = this.dishStructMapper.toEntity(input);

        dish.setCreatedAt(LocalDateTime.now());

        log.warn("Creating dish with name: {}", input.getName());
        dishJpaRepository.save(dish);
    }
}
