package fr.esiee.application.parking.time;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

public class TimePaid {
	
	@Getter @Setter private long timePlaceTaken ;
	@Getter @Setter private int numberUnit ;
	@Getter @Setter private TimeUnit timeUnit ; 
	
	public TimePaid() {
		setTimePlaceTaken(System.currentTimeMillis());
	}
	
	public TimePaid(int numberPaid, TimeUnit timeUnit) {
		this() ;
		setNumberUnit(numberPaid);
		setTimeUnit(timeUnit);
	}
	
	public boolean isInTheStandards() {
		
		long now = System.currentTimeMillis() ;
		long timePaid = timeUnit.toMillis(numberUnit) ;
		long paidTo = timePlaceTaken + timePaid ;
		
		if(paidTo < now) return false ; 
		return true ;
	}
	
	@Override
	public String toString() {
		return numberUnit+" "+timeUnit.toString()+", isInTheStandards="+this.isInTheStandards() ;
	}

}
