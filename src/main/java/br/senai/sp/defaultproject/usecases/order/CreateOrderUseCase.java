package br.senai.sp.defaultproject.usecases.order;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.entities.Order;
import br.senai.sp.defaultproject.repositories.board.BoardJpaRepository;
import br.senai.sp.defaultproject.repositories.dish.DishJpaRepository;
import br.senai.sp.defaultproject.repositories.order.OrderJpaRepository;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class CreateOrderUseCase {

    private final OrderJpaRepository orderJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final DishJpaRepository dishJpaRepository;
    private final BoardJpaRepository boardJpaRepository;

    @Transactional
    public void execute(CreateOrderInputDTO input) {
        var user = userJpaRepository.findById(input.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var dish = dishJpaRepository.findById(input.getDishId())
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));

        var board = boardJpaRepository.findById(input.getBoardId())
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));

        var order = new Order();
        order.setUser(user);
        order.setDish(dish);
        order.setBoard(board);
        order.setCreatedAt(LocalDateTime.now());

        log.warn("Creating order for board with the number: {}", input.getBoardId());

        orderJpaRepository.save(order);
    }
}