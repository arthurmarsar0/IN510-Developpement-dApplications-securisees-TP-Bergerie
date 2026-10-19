package fr.esisar.bergerie.mouton;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import fr.esisar.bergerie.cloche.*;

@Entity
public class Mouton extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String nom;

    @Column(nullable = false)
    public LocalDate dateNaissance;
    
    @OneToOne
    public Cloche cloche;
}