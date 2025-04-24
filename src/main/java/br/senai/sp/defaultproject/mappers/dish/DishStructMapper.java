package br.senai.sp.defaultproject.mappers.dish;

import br.senai.sp.defaultproject.dtos.dish.input.CreateDishInputDTO;
import br.senai.sp.defaultproject.dtos.dish.output.DishOutputDTO;
import br.senai.sp.defaultproject.entities.Dish;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DishStructMapper {
    DishOutputDTO toDishOutputDTO(Dish entity);

    List<DishOutputDTO> toDishOutputDTO(List<Dish> entities);

    Dish toEntity(CreateDishInputDTO dto);
}
