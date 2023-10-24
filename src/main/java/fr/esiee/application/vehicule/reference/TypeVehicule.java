package fr.esiee.application.vehicule.reference;

import fr.esiee.application.parking.reference.TypePlace;
import lombok.Getter;
import lombok.Setter;

public enum TypeVehicule {
	
	
	SCOOTER(TypePlace.DEUX_ROUES, "S"), 
	MOTO(TypePlace.DEUX_ROUES, "M"), 
	VOITURE(TypePlace.QUATRE_ROUES, "V"), 
	CAMIONNETTE(TypePlace.QUATRE_ROUES, "C"),
	TROTTINETTE(TypePlace.DEUX_ROUES, "T"),
	VELO(TypePlace.DEUX_ROUES, "VE"),
	CARAVANE(TypePlace.QUATRE_ROUES, "CV"),
	;
	
	@Getter @Setter private TypePlace typePlace ;
	@Getter @Setter private String letter ;
	
	private TypeVehicule(TypePlace typeAccept, String letter) {
		setTypePlace(typeAccept);
		setLetter(letter);
	}

	public static String[] valuesToString() {
		String[] values = new String[values().length] ;
		for(int i=0 ; i< values().length ; i++)
			values[i] = values()[i].name() ;
			
		return values;
	}

}
