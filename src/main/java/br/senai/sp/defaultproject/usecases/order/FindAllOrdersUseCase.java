package br.senai.sp.defaultproject.usecases.order;

import br.senai.sp.defaultproject.dtos.order.output.OrderOutputDTO;
import br.senai.sp.defaultproject.mappers.order.OrderStructMapper;
import br.senai.sp.defaultproject.repositories.order.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAllOrdersUseCase {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderStructMapper orderStructMapper;

    @Transactional
    public Set<OrderOutputDTO> execute(){
        return new HashSet<>(orderStructMapper.toOrderOutputDTO(
                orderJpaRepository.findAll()
        ));
    }
}
