package br.senai.sp.defaultproject.entities;

import br.senai.sp.defaultproject.enums.BoardStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "boards")
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String boardNumber;

    @Column(nullable = false)
    private BoardStatus status;

    private LocalDateTime createdAt;
}