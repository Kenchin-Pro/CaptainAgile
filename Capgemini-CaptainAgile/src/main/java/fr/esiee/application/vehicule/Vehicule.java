package fr.esiee.application.vehicule;

import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class Vehicule {
	
	@Getter @Setter private TypeVehicule typeVehicule ;
	
	public Vehicule(TypeVehicule type) {
		setTypeVehicule(type); 
	}

}
