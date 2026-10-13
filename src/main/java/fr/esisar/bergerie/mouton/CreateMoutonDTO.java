package fr.esisar.bergerie.mouton;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CreateMoutonDTO( @NotBlank String nom, 
		@NotBlank LocalDate dateNaissance) {}