package fr.esiee.application.service;

import java.util.HashMap;
import java.util.Map;

import fr.esiee.application.parking.Parking;

public interface ServiceParkingManager {

	public static Map<String, Parking> mapParkings = new HashMap<String, Parking>() ;
	
	public static Parking getParking(String nameParking) {
		return ServiceParkingManager.mapParkings.get(nameParking) ;
	}
	
	public static boolean haveParking(String nameParking) {
		return ServiceParkingManager.mapParkings.containsKey(nameParking) ;
	}
	
	public static void addParking(Parking park) {
		ServiceParkingManager.mapParkings.put(park.getName(), park) ;
	}
	
	
	
	
}
