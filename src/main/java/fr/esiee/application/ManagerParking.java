package fr.esiee.application;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.service.ServiceParkingManager;

public class ManagerParking implements ServiceParkingManager {
	
	public ManagerParking() {
		

		Parking parking1 = new Parking("Parking 1", 1, 5) ; // 10 Moto & 50 Cars 
		Parking parking2 = new Parking("Parking 2", 4, 10) ;  // 40 Moto & 5 Cars
		Parking parking3 = new Parking("Parking 3", 0, 4) ; // 0 Moto & 100 Cars
		
		
		ServiceParkingManager.addParking(parking1);
		ServiceParkingManager.addParking(parking2);
		ServiceParkingManager.addParking(parking3);
		
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
