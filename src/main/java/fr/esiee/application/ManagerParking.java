package fr.esiee.application;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.service.ServiceParkingManager;

public class ManagerParking implements ServiceParkingManager {
	
	public ManagerParking() {

	}

	public void loadDefault() {

		ServiceParkingManager.mapParkings.clear();
		
		Parking parking1 = new Parking("Parking 1", 1, 5) ;  
		Parking parking2 = new Parking("Parking 2", 4, 10) ; 
		Parking parking3 = new Parking("Parking 3", 0, 4) ; 
		Parking parking4 = new Parking("Parking 4", 10, 2) ; 
		
		ServiceParkingManager.addParking(parking1);
		ServiceParkingManager.addParking(parking2);
		ServiceParkingManager.addParking(parking3);
		ServiceParkingManager.addParking(parking4);
		
	}
	
	@Override
	public String toString() {
		
		String text = "=== ManagerParking ===\n\n" ;
		
		for(Parking p : mapParkings.values()) {
			text += ServiceParkingManager.getParking(p.getName()).toString() +"\n\n";
		}
		
		return text ;
		
	
	}
	
}
