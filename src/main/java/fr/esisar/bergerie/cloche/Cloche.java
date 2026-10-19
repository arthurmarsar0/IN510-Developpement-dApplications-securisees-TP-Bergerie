package fr.esisar.bergerie.cloche;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import fr.esisar.bergerie.mouton.*;

@Entity
public class Cloche extends PanacheEntity {
	
	@OneToOne(mappedBy = "cloche")
	public Mouton mouton;
}
