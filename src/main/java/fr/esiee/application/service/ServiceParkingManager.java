package fr.esiee.application.service;

import java.util.HashMap;
import java.util.Map;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.exceptions.ExceptionParkingNotFound;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsAlreadyFree;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFound;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.vehicule.Vehicule;

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
	
	public static void vehiculeTakePlace(Vehicule v, String parkingName, int placeId) throws ExceptionParkingNotFound, ExceptionPlaceNotFound, ExceptionPlaceIsOccuped, ExceptionUnsuitablePlaceForThisVehicule {
		
		Parking p = getParking(parkingName) ;
		if(p == null) throw new ExceptionParkingNotFound() ;
		
		ServiceParkingManager.vehiculeTakePlace(v, p, placeId);
	}
	
	public static void vehiculeTakePlace(Vehicule v, Parking p, int placeId) throws ExceptionPlaceNotFound, ExceptionPlaceIsOccuped, ExceptionUnsuitablePlaceForThisVehicule {
		p.setVehiculeOnPlaceID(v, placeId);
	}
	
	public static void vehiculeFreeUpPlace(String parkingName, int placeId) throws ExceptionParkingNotFound, ExceptionPlaceIsAlreadyFree, ExceptionPlaceNotFound {
		
		Parking p = getParking(parkingName) ;
		if(p == null) throw new ExceptionParkingNotFound() ;
		
		ServiceParkingManager.vehiculeFreeUpPlace(p, placeId);
		
	}
	
	public static void vehiculeFreeUpPlace(Parking p, int placeId) throws ExceptionPlaceIsAlreadyFree, ExceptionPlaceNotFound {
		p.clearVehiculeOnPlaceID(placeId);
	}
	
	
	
	
	
}
