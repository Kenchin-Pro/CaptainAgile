package fr.esiee.application.vehicule;

import lombok.Getter;
import lombok.Setter;

public class Client {

	@Getter @Setter private String name ; 
	@Getter @Setter private Vehicule vehicule ;
	
	public Client(String name, Vehicule vehicule) {
		setName(name);
		setVehicule(vehicule);
	}
	
}
