package br.senai.sp.defaultproject.mappers.order;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.dtos.order.output.OrderOutputDTO;
import br.senai.sp.defaultproject.entities.Order;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStructMapper {
    OrderOutputDTO toOrderOutputDTO(Order entity);

    List<OrderOutputDTO> toOrderOutputDTO(List<Order> entities);

    Order toEntity(CreateOrderInputDTO dto);
}
