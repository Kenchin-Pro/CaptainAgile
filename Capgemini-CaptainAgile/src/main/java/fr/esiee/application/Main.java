package fr.esiee.application;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFree;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.service.ServiceParkingManager;
import fr.esiee.application.vehicule.Client;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.reference.TypeVehicule;

public class Main {
	
	public static void main(String[] args) {
		
		ServiceParkingManager manager = new ManagerParking();
		System.out.println(manager.toString());
		
		Client client1 = new Client("Mathieu Client1", new Vehicule(TypeVehicule.VOITURE)) ;
		
		Parking parking1 = manager.getParking("Parking 1") ;
		
		try {
			Place place = parking1.getFirstPlaceDisponibleForClient(client1) ;
			place.setVehicule(client1.getVehicule());
			
			place = parking1.getFirstPlaceDisponibleForClient(client1) ;
			place.setVehicule(client1.getVehicule());
			
			place = parking1.getFirstPlaceDisponibleForClient(client1) ;
			place.setVehicule(client1.getVehicule());
			
			place = parking1.getFirstPlaceDisponibleForClient(client1) ;
			place.setVehicule(client1.getVehicule());
			
		} catch (ExceptionPlaceNotFree | ExceptionPlaceIsOccuped | ExceptionUnsuitablePlaceForThisVehicule e) {
			e.printStackTrace();
		}
		
		System.out.println(manager.toString());
		
		
		
	}

}
