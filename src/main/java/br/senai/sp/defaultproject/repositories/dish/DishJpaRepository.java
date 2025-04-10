package br.senai.sp.defaultproject.repositories.dish;

import br.senai.sp.defaultproject.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DishJpaRepository extends JpaRepository<Dish, UUID> {
}
