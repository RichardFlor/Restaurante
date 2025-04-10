package br.senai.sp.defaultproject.repositories.orderItem;

import br.senai.sp.defaultproject.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemJpaRepository extends JpaRepository<OrderItem, UUID> {
}