package fr.esisar.bergerie.mouton;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record MoutonResponseDTO(Long id, 
		@NotBlank String nom, @NotBlank LocalDate dateNaissance) {}
