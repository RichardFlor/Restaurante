package br.senai.sp.defaultproject.usecases.order;

import br.senai.sp.defaultproject.dtos.order.input.CreateOrderInputDTO;
import br.senai.sp.defaultproject.entities.Board;
import br.senai.sp.defaultproject.entities.Order;
import br.senai.sp.defaultproject.entities.User;
import br.senai.sp.defaultproject.errors.ExceptionCode;
import br.senai.sp.defaultproject.errors.exceptions.BusinessRuleException;
import br.senai.sp.defaultproject.errors.exceptions.EntityNotFoundException;
import br.senai.sp.defaultproject.repositories.board.BoardJpaRepository;
import br.senai.sp.defaultproject.repositories.dish.DishJpaRepository;
import br.senai.sp.defaultproject.repositories.order.OrderJpaRepository;
import br.senai.sp.defaultproject.repositories.user.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

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
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        var dishes = dishJpaRepository.findAllById(input.getDishIds());

        if (dishes.size() != input.getDishIds().size()) {
            throw new BusinessRuleException(ExceptionCode.SOME_DISH_REPORTED_WAS_NOT_FOUND);
        }

        var board = boardJpaRepository.findById(input.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException(Board.class));

        var order = new Order();
        order.setUser(user);
        order.setDishes(dishes);
        order.setBoard(board);
        order.setCreatedAt(LocalDateTime.now());

        log.warn("Creating order for board with the number: {}", input.getBoardId());

        orderJpaRepository.save(order);
    }
}