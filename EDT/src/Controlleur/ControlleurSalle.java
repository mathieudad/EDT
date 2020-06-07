/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEDT;
import edt.Model.Etudiant;
import edt.Model.MyCalendar;
import edt.Model.Salle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mathi
 */
public class ControlleurSalle implements ActionListener {
    MyCalendar myCalendar;
    Salle salle;
    JFrameEDT jFrame;
    
    /**
     * Constructeur du controlleur de l'affichage d'une salle
     * @param salle
     */
    public ControlleurSalle(Salle salle){
        this.salle = salle;
        myCalendar = new MyCalendar();
        jFrame = new JFrameEDT(myCalendar,salle.getSeances(myCalendar.getWeek()));
        jFrame.getSemainePrecedante().addActionListener(this);
        jFrame.getSemaineSuivante().addActionListener(this);
    }
    
    /**
     * Appelle la JFrame pour afficher l'edt de ce groupe
     */
    public void afficherEDT(){
        jFrame.afficheEDT(myCalendar,salle.getSeances(myCalendar.getWeek()));
    }

    /**
     * Gere les evenements des boutons semaines suivantes et precedentes dans la Frame
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if(actionEvent.getSource() == jFrame.getSemaineSuivante()) {
            myCalendar.semaineSuivante();
            jFrame.getPanel().removeAll();
            afficherEDT();
                
        }
        if(actionEvent.getSource() == jFrame.getSemainePrecedante()) {
            myCalendar.semainePrecedente();
            jFrame.getPanel().removeAll();
            afficherEDT();
        } 
     }

    public JFrameEDT getjFrame() {
        return jFrame;
    }
    
}
