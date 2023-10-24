package fr.esiee.application.vehicule;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;

class VehiculeTest {

	@Test
	void testCreationVehiculeWithIDNull() {
		try {
			Vehicule v = new Vehicule(TypeVehicule.MOTO, null) ;
			fail("Creation vehicule with ID null not failed") ;
		} catch (NullPointerException e) {
			
		} catch (ExceptionRegistrationNotAccepted e) {
			fail("Creation vehicule with ID null : bad exception") ;
		}
	}

	@Test
	void testCreationVehiculeWithIDVierge() {
		try {
			Vehicule v = new Vehicule(TypeVehicule.MOTO, "") ;
			fail("Creation vehicule with ID null not failed") ;
		} catch (NullPointerException e) {
			fail("Creation vehicule with ID null : bad exception") ;
		} catch (ExceptionRegistrationNotAccepted e) {
			
		}
	}
	
	
}
