package br.senai.sp.defaultproject.rest.controllers;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.dtos.order.output.OrderOutputDTO;
import br.senai.sp.defaultproject.rest.specs.OrderControllerSpecs;
import br.senai.sp.defaultproject.usecases.order.CreateOrderUseCase;
import br.senai.sp.defaultproject.usecases.order.FindAllOrdersUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController implements OrderControllerSpecs {
    private final CreateOrderUseCase createOrderUseCase;
    private final FindAllOrdersUseCase findAllOrdersUseCase;

    @PostMapping()
    public void create(@RequestBody @Valid CreateOrderInputDTO request){
        createOrderUseCase.execute(request);
    }

    @GetMapping()
    public Set<OrderOutputDTO> findAll(){
        return findAllOrdersUseCase.execute();
    }

}
