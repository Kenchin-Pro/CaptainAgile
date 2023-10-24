package fr.esiee.application.parking;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFound;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.exceptions.ExceptionRegistrationNotAccepted;
import fr.esiee.application.vehicule.reference.TypeVehicule;

class ParkingTest {

	@Test
	void testParkingVehiculeNullOnPlace() throws NullPointerException, ExceptionRegistrationNotAccepted {
		
		Parking p = new Parking("Parking 1", 1, 10, false) ;
		Vehicule v0 = null ;
		
		try {
			p.setVehiculeOnPlaceID(v0, 0);
			fail("Aucune exception") ;
		} catch (NullPointerException e) {
			
		} catch (ExceptionPlaceNotFound | ExceptionPlaceIsOccuped | ExceptionUnsuitablePlaceForThisVehicule e) {
			fail("Bad exception") ;
		}
		
	}

	@Test
	void testParkingPlaceNotFound() throws NullPointerException, ExceptionRegistrationNotAccepted {
		
		Parking p = new Parking("Parking 1", 1, 10, false) ;
		Vehicule v1 = new Vehicule(TypeVehicule.MOTO, "M1") ;
		
		try {
			p.setVehiculeOnPlaceID(v1, 150);
			fail("Aucune exception") ;
		} catch (ExceptionPlaceNotFound e) {
			
		} catch (NullPointerException | ExceptionPlaceIsOccuped | ExceptionUnsuitablePlaceForThisVehicule e) {
			fail("Bad exception") ;
		}
		
	}
	

	@Test
	void testParkingPlaceIsOccuped() throws NullPointerException, ExceptionRegistrationNotAccepted {
		
		Parking p = new Parking("Parking 1", 1, 10, false) ;
		Vehicule v1 = new Vehicule(TypeVehicule.MOTO, "M1") ;
		
		try {
			p.setVehiculeOnPlaceID(v1, 0);
			p.setVehiculeOnPlaceID(v1, 0);
			fail("Aucune exception") ;
		} catch (ExceptionPlaceIsOccuped e) {
			
		} catch (NullPointerException | ExceptionPlaceNotFound  | ExceptionUnsuitablePlaceForThisVehicule e) {
			fail("Bad exception") ;
		}
		
	}
	


	@Test
	void testParkingPlaceUnsuitable() throws NullPointerException, ExceptionRegistrationNotAccepted {
		
		Parking p = new Parking("Parking 1", 1, 10, false) ;
		Vehicule v1 = new Vehicule(TypeVehicule.MOTO, "M1") ;
		
		try {
			p.setVehiculeOnPlaceID(v1, 3);
			fail("Aucune exception") ;
		} catch (ExceptionUnsuitablePlaceForThisVehicule e) {
			
		} catch (NullPointerException | ExceptionPlaceNotFound  | ExceptionPlaceIsOccuped e) {
			fail("Bad exception") ;
		}
		
	}
	
}
