package br.senai.sp.defaultproject.mappers.order;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.dtos.order.output.OrderOutputDTO;
import br.senai.sp.defaultproject.entities.Order;
import br.senai.sp.defaultproject.mappers.board.BoardStructMapper;
import br.senai.sp.defaultproject.mappers.dish.DishStructMapper;
import br.senai.sp.defaultproject.mappers.user.UserStructMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        UserStructMapper.class,
        BoardStructMapper.class,
        DishStructMapper.class
})
public interface OrderStructMapper {
    OrderOutputDTO toOrderOutputDTO(Order entity);

    List<OrderOutputDTO> toOrderOutputDTO(List<Order> entities);

    Order toEntity(CreateOrderInputDTO dto);
}
