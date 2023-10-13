package fr.esiee.application.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ParkingGUI extends JFrame {
    private static final long serialVersionUID = -3008351773128140647L;
    
    public ParkingGUI() {
        super("Parking Simulator");
        
        // Configuration de la fenetre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        

        JPanelParkingManager parkingManagerPanel = new JPanelParkingManager(null);
        add(parkingManagerPanel, BorderLayout.CENTER);
        
        JPanelSelectionParkings jpanelSelectionParkings = new JPanelSelectionParkings(parkingManagerPanel) ;
        add(jpanelSelectionParkings, BorderLayout.WEST) ;
        
        
        setVisible(true);
    }
    
}
