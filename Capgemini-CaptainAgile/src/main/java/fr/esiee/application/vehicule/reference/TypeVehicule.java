package fr.esiee.application.vehicule.reference;

import fr.esiee.application.parking.reference.TypePlace;
import lombok.Getter;
import lombok.Setter;

public enum TypeVehicule {
	
	
	SCOOTER(TypePlace.DEUX_ROUES), 
	MOTO(TypePlace.DEUX_ROUES), 
	VOITURE(TypePlace.QUATRE_ROUES), 
	CAMIONNETTE(TypePlace.QUATRE_ROUES) ;
	
	@Getter @Setter private TypePlace typePlace ;
	
	private TypeVehicule(TypePlace typeAccept) {
		setTypePlace(typeAccept);
	}

}
