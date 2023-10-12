package fr.esiee.application.vehicule;

import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;

public class Vehicule {
	
	@Getter private final TypeVehicule typeVehicule ;
	@Getter private final String ID ;
	
	public Vehicule(TypeVehicule type, String ID) throws ExceptionRegistrationNotAccepted {
		this.typeVehicule = type ; 
		this.ID = ID ;
		
		if(ID.equalsIgnoreCase(""))
			throw new ExceptionRegistrationNotAccepted() ;
		
	}

}
