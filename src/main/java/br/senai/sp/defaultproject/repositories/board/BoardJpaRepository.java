package br.senai.sp.defaultproject.repositories.board;

import br.senai.sp.defaultproject.entities.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BoardJpaRepository extends JpaRepository<Board, UUID> {
}