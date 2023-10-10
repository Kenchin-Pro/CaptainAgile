package fr.esiee.application.parking;

import java.util.ArrayList;
import java.util.List;

import fr.esiee.application.parking.exceptions.ExceptionPlaceNotFree;
import fr.esiee.application.parking.reference.TypePlace;
import fr.esiee.application.vehicule.Client;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class Parking {

	@Getter @Setter private String name ;
	@Getter @Setter private List<Place> listDeuxRoues ;
	@Getter @Setter private List<Place> listQuatreRoues ;
	@Getter @Setter private int nbPlacesDeuxRoues, nbPlacesQuatreRoues ;
	
	public Parking(String name, int nb_places_deux_roues, int nb_places_quatre_roues) {
		setName(name);
		setNbPlacesDeuxRoues(nb_places_deux_roues);
		setNbPlacesQuatreRoues(nb_places_quatre_roues);
		setListDeuxRoues(new ArrayList<Place>(nb_places_deux_roues));
		setListQuatreRoues(new ArrayList<Place>(nb_places_quatre_roues));
		
		int id = 0 ;
		
		for(int i = 0 ; i < this.nbPlacesDeuxRoues ; i++, id++) 
			this.listDeuxRoues.add(new Place(id, TypePlace.DEUX_ROUES)) ;
		
		for(int i = 0 ; i < this.nbPlacesQuatreRoues ; i++, id++) 
			this.listQuatreRoues.add(new Place(id, TypePlace.QUATRE_ROUES)) ;
		
			 
	}
	
	public Place getFirstPlaceDisponible(TypeVehicule typeVehicule) throws ExceptionPlaceNotFree {
		
		
		if(typeVehicule.getTypePlace() == TypePlace.DEUX_ROUES) 			
			for(Place p : this.listDeuxRoues) 
				if(!p.isOccupe()) return p ; 

		if(typeVehicule.getTypePlace() == TypePlace.QUATRE_ROUES) 
			for(Place p : this.listQuatreRoues)
				if(!p.isOccupe()) return p ; 
		
		
		throw new ExceptionPlaceNotFree() ; 
		
	}
	
	@Override
	public String toString() {
		
		String text = this.name+"\n" ;
		
		for(Place p : listDeuxRoues) text+= p.toString() ;
		for(Place p : listQuatreRoues) text+= p.toString() ;
		
		return text ;
		
		
	}

	public Place getFirstPlaceDisponibleForClient(Client client) throws ExceptionPlaceNotFree {
		return this.getFirstPlaceDisponible(client.getVehicule().getTypeVehicule()) ;
	}
	
	
}
