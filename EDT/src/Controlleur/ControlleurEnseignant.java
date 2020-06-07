/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEDT;
import edt.Model.Enseignant;
import edt.Model.MyCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mathi
 */
public class ControlleurEnseignant implements ActionListener{
     MyCalendar myCalendar;
    Enseignant enseignant;
    JFrameEDT jFrameEnseignant;
    
    /**
     * Constructeur du controlleur d'enseignant
     * @param enseignant
     */
    public ControlleurEnseignant(Enseignant enseignant){
        this.enseignant =enseignant;
        myCalendar = new MyCalendar();
        jFrameEnseignant = new JFrameEDT(myCalendar, enseignant.getSeances(myCalendar.getWeek()));
        jFrameEnseignant.getSemainePrecedante().addActionListener(this);
        jFrameEnseignant.getSemaineSuivante().addActionListener(this);
    }
    
    /**
     * Affiche l'emploie du temps dans la FrameEDT
     */
    public void afficherEDT(){
        jFrameEnseignant.afficheEDT(myCalendar,enseignant.getSeances(myCalendar.getWeek()));
    }

    /**
     * Gere les evenements des boutons semaines suivantes et precedentes dans la Frame
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if(actionEvent.getSource() == jFrameEnseignant.getSemaineSuivante()) {
            myCalendar.semaineSuivante();
            jFrameEnseignant.getPanel().removeAll();
            afficherEDT();
                
        }
        if(actionEvent.getSource() == jFrameEnseignant.getSemainePrecedante()) {
            myCalendar.semainePrecedente();
            jFrameEnseignant.getPanel().removeAll();
            afficherEDT();
        } 
     }

    public JFrameEDT getjFrame() {
        return jFrameEnseignant;
    }
    
    
}
