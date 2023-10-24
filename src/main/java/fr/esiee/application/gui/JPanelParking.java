package fr.esiee.application.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.esiee.application.parking.Parking;
import fr.esiee.application.parking.Place;
import lombok.Getter;
import lombok.Setter;

public class JPanelParking extends JPanel {

	private static final long serialVersionUID = 4936687528211044199L;
	@Getter @Setter private String errorMessage ;
	@Getter @Setter private Parking parking ;
    
	public JPanelParking(Parking parking) {
		setParking(parking);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.removeAll();
        super.revalidate();
        
        if(parking != null) drawParkingSpots(g);
    }
	

    private void drawParkingSpots(Graphics g) {
    	g.drawString(this.parking.getName(), 200, 20);
        int spotWidth = 20;
        int x = 50;
        int y = 100;
        
        if(errorMessage != null) {
        	g.drawString(errorMessage, 200, 40);
        	setErrorMessage(null);
        }
        
        g.drawString("Places 2 roues :", x, y-20);

        for (Place p : parking.getListDeuxRoues()) { 
        	this.drawPlace(g, p, x, y, spotWidth);
        	x += spotWidth + 10;
        }
        
        
        y += 80 ;
        x = 50 ;
        
        g.drawString("Places 4 roues :", x, y-20);

        for (Place p : parking.getListQuatreRoues()) {
        	this.drawPlace(g, p, x, y, spotWidth);
        	x += spotWidth + 10;
        }

        
    }
    
    private void drawPlace(Graphics g, Place p, int x, int y, int spotWidth) {

    	Color color = p.getColorGUI() ;
    	
    	g.setColor(color);
        g.fillRect(x, y, spotWidth, 30);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, spotWidth, 30);
        g.drawString(p.getIdPlace()+"", x+5, y+12);
        
        if(p.isOccupe()) g.drawString(p.getVehicule().getTypeVehicule().getLetter(), x+5, y+25);
        

        
    }
    
	
}
