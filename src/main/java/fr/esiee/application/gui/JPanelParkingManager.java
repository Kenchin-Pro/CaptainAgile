package fr.esiee.application.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import fr.esiee.application.parking.Parking;
import lombok.Getter;
import lombok.Setter;

public class JPanelParkingManager extends JPanel {

	private static final long serialVersionUID = -5333592248665612070L;
	
	@Getter @Setter private Parking parking ;
	
	@Getter private JPanelParking parkingPanel ;
	@Getter private JPanelManagerPlace jpanelManagerPlace  ;

	public JPanelParkingManager(Parking parking) {
		setParking(parking);
		
		// Creation d'un panneau pour afficher les places de parking
        parkingPanel = new JPanelParking(parking) ;
        parkingPanel.setPreferredSize(new Dimension(700, 250));
        add(parkingPanel, BorderLayout.CENTER);

        jpanelManagerPlace = new JPanelManagerPlace(parkingPanel, parking) ;
        add(jpanelManagerPlace, BorderLayout.SOUTH) ;
	}
	
	@Override
	public void repaint() {
		super.repaint();
		
		if(parking == null) return ;
        
		parkingPanel.setParking(parking);
		jpanelManagerPlace.setParking(parking);
		
        parkingPanel.repaint();
        jpanelManagerPlace.repaint();
        
	}
	
	public void create() {
		

	}
	
	
	
}
