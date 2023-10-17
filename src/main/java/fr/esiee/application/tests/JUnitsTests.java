package fr.esiee.application.tests;

import org.junit.Test;

import fr.esiee.application.ManagerParking;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class JUnitsTests {

	@Getter @Setter private ManagerParking manager = new ManagerParking() ;
	
	@Test
	public void testCreateVehicule() {
		
		System.out.println("test");
				
		try {
			Vehicule v = new Vehicule(TypeVehicule.VOITURE, null) ;
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ExceptionRegistrationNotAccepted e) {
			e.printStackTrace();
		}
		
	}
	
	
}
