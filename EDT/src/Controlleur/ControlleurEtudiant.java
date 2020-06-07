/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEDT;
import edt.Model.Etudiant;
import edt.Model.MyCalendar;
import edt.Model.Seance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author mathi
 */
public class ControlleurEtudiant implements ActionListener{
    MyCalendar myCalendar;
    Etudiant etudiant;
    JFrameEDT jFrameEtudiant;
    
    /**
     * Constructeur de controlleur d'un affichage d'emploi du temps etudiant 
     * @param etudiant
     */
    public ControlleurEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
        myCalendar = new MyCalendar();
        jFrameEtudiant = new JFrameEDT(myCalendar,etudiant.getGroupe().getSeances(myCalendar.getWeek()));
        jFrameEtudiant.getSemainePrecedante().addActionListener(this);
        jFrameEtudiant.getSemaineSuivante().addActionListener(this);
    }
    
    /**
     * Appelle la Jframe de l'emploie du temps d'un etudiant
     */
    public void afficherEDT(){
        jFrameEtudiant.afficheEDT(myCalendar,etudiant.getGroupe().getSeances(myCalendar.getWeek()));
    }

    /**
     * Gere les evenements des boutons semaines suivantes et precedentes dans la Frame
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if(actionEvent.getSource() == jFrameEtudiant.getSemaineSuivante()) {
            myCalendar.semaineSuivante();
            jFrameEtudiant.getPanel().removeAll();
            afficherEDT();
                
        }
        if(actionEvent.getSource() == jFrameEtudiant.getSemainePrecedante()) {
            myCalendar.semainePrecedente();
            jFrameEtudiant.getPanel().removeAll();
            afficherEDT();
        } 
     }

    public JFrameEDT getjFrame() {
        return jFrameEtudiant;
    }
    
}
    
