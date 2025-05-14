package br.senai.sp.defaultproject.repositories.order;

import br.senai.sp.defaultproject.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, UUID> {
    @EntityGraph(attributePaths = {"user", "board", "dishes"})
    List<Order> findAll();
}
