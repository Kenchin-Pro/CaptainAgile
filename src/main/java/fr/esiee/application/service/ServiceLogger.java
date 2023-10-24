package fr.esiee.application.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import fr.esiee.application.vehicule.Vehicule;

public interface ServiceLogger {

	public static Logger logger = LogManager.getLogger(ServiceLogger.class);
	
	public static void info(Object message) {
		logger.info(message);
	}
	public static void debug(Object message) {
		logger.debug(message);
	}
	
	public static void logVehiculeTakePlace(Parking park, Place place) {
		Vehicule v = place.getVehicule() ;
		ServiceLogger.info(park.getName()+" : "+"Véhicule "+v.getID()+" ("+v.getTypeVehicule().getLetter()+") take place #"+place.getIdPlace());
	}
	
	public static void logVehiculeFreeUpPlace(Parking park, Vehicule v, Place place) {
		ServiceLogger.info(park.getName()+" : "+"Véhicule "+v.getID()+" ("+v.getTypeVehicule().getLetter()+") free up place #"+place.getIdPlace());
	}
	
	public static void logVehiculeNotInTheRules(Place place) {
		ServiceLogger.info("[!] Véhicule "+place.getVehicule().getID()+" ("+place.getVehicule().getTypeVehicule().getLetter()+") didn't pay PlaceID#"+place.getIdPlace());
	}
	
}
