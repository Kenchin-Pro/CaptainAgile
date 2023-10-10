package fr.esiee.application.service;

import java.util.HashMap;
import java.util.Map;

import fr.esiee.application.parking.Parking;

public interface ServiceParkingManager {

	public static Map<String, Parking> mapParkings = new HashMap<String, Parking>() ;
	
	public default Parking getParking(String nameParking) {
		return ServiceParkingManager.mapParkings.get(nameParking) ;
	}
	
	public default boolean haveParking(String nameParking) {
		return ServiceParkingManager.mapParkings.containsKey(nameParking) ;
	}
	
	public default void addParking(Parking park) {
		ServiceParkingManager.mapParkings.put(park.getName(), park) ;
	}
	
	
	
}
