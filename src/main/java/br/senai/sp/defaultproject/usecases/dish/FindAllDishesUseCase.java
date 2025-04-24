package br.senai.sp.defaultproject.usecases.dish;

import br.senai.sp.defaultproject.dtos.dish.output.DishOutputDTO;
import br.senai.sp.defaultproject.mappers.dish.DishStructMapper;
import br.senai.sp.defaultproject.repositories.dish.DishJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllDishesUseCase {
    private final DishJpaRepository dishJpaRepository;
    private final DishStructMapper dishStructMapper;

    @Transactional
    public Set<DishOutputDTO> execute(){
        return new HashSet<>(dishStructMapper.toDishOutputDTO(
                dishJpaRepository.findAll()
        ));
    }
}
