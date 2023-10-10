package fr.esiee.application.parking;

import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.parking.reference.TypePlace;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class Place {

	@Getter @Setter private int idPlace ;
	@Getter @Setter private TypePlace type ; 
	@Getter private Vehicule vehicule ;
	
	public Place(int idPlace, TypePlace type) {
		setIdPlace(idPlace);
		setType(type);
	}
	
	public boolean isAcceptTypeVehicule(TypeVehicule type) {
		return (type.getTypePlace() == this.getType()) ;
	}
	
	public boolean isOccupe() {
		return vehicule != null ; 
	}
	
	public void setVehicule(Vehicule vehicule) throws ExceptionPlaceIsOccuped, ExceptionUnsuitablePlaceForThisVehicule {
		
		if(this.vehicule != null)
			throw new ExceptionPlaceIsOccuped() ;
		
		if(this.type != vehicule.getTypeVehicule().getTypePlace())
			throw new ExceptionUnsuitablePlaceForThisVehicule() ;
		
		this.vehicule = vehicule ;
	}
	
	@Override
	public String toString() {
	
		String text = "" ;
		
		String typePlace = this.getType() == TypePlace.DEUX_ROUES ? "2R" : "4R" ;
		String isOccuped = this.isOccupe() ? "X" : "V" ; 
		
		text += this.getIdPlace()+". "+"("+ typePlace+") "+isOccuped+"\n";
		
		return text ; 
		
	}
	
	
}
