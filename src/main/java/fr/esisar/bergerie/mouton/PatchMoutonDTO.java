package fr.esisar.bergerie.mouton;

import java.time.LocalDate;


public record PatchMoutonDTO( String nom, 
		LocalDate dateNaissance) {}