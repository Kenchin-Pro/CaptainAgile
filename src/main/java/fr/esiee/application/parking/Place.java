package fr.esiee.application.parking;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import fr.esiee.application.parking.exceptions.ExceptionPlaceIsAlreadyFree;
import fr.esiee.application.parking.exceptions.ExceptionPlaceIsOccuped;
import fr.esiee.application.parking.exceptions.ExceptionUnsuitablePlaceForThisVehicule;
import fr.esiee.application.parking.reference.TypePlace;
import fr.esiee.application.parking.time.TimePaid;
import fr.esiee.application.service.ServiceLogger;
import fr.esiee.application.vehicule.Vehicule;
import fr.esiee.application.vehicule.reference.TypeVehicule;
import lombok.Getter;
import lombok.Setter;

public class Place {

	@Getter @Setter private int idPlace ;
	@Getter @Setter private TypePlace type ; 
	@Getter private Vehicule vehicule ;
	
	@Getter @Setter private TimePaid timePaid ; 
	@Getter @Setter private boolean needToPayBefore ;
	@Getter @Setter private boolean notificationNotPermission ;
	
	public Place(int idPlace, TypePlace type) {
		setIdPlace(idPlace);
		setType(type);
		setNotificationNotPermission(false);
	}
	
	public Place(int idPlace, TypePlace type, boolean needToPayBefore) {
		this(idPlace, type) ;
		setNeedToPayBefore(needToPayBefore);
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
	
	public void setVehiculeWithTime(Vehicule v, int number, TimeUnit unit) throws ExceptionPlaceIsOccuped, ExceptionUnsuitablePlaceForThisVehicule {
		this.setVehicule(v);
		setTimePaid(new TimePaid(number, unit));
	}
	
	public void libererPlace() throws ExceptionPlaceIsAlreadyFree {
		
		if(this.vehicule == null)
			throw new ExceptionPlaceIsAlreadyFree() ;
		
		this.vehicule = null ;
		this.notificationNotPermission = false ;
		
	}
	
	@Override
	public String toString() {
	
		String text = "" ;
		
		String typePlace = this.getType() == TypePlace.DEUX_ROUES ? "2R" : "4R" ;
		String isOccuped = this.isOccupe() ? "X" : "V" ; 
		
		text += this.getIdPlace()+". "+"("+ typePlace+") "+isOccuped+"\n";
		
		return text ; 
		
	}

	public Color getColorGUI() {
		
		if(!this.isOccupe()) return Color.GREEN ;
		
		if(!this.isNeedToPayBefore()) return Color.YELLOW ;
		if(timePaid != null && timePaid.isInTheStandards()) return Color.ORANGE ;
		
		if(!notificationNotPermission) {
			notificationNotPermission = true ; 
			ServiceLogger.logVehiculeNotInTheRules(this);
		}
		
		return Color.RED ;
		
	
	}
	
	
}
