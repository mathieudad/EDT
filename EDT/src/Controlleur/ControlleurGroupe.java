/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEDT;
import edt.Model.Groupe;
import edt.Model.MyCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mathi
 */
public class ControlleurGroupe implements ActionListener{
    MyCalendar myCalendar;
    Groupe groupe;
    JFrameEDT jFrame;
    
    public ControlleurGroupe(Groupe groupe){
        this.groupe = groupe;
        myCalendar = new MyCalendar();
        jFrame = new JFrameEDT(myCalendar,groupe.getSeances(myCalendar.getWeek()));
        jFrame.getSemainePrecedante().addActionListener(this);
        jFrame.getSemaineSuivante().addActionListener(this);
    }
    
    /**
     * Appelle la JFrame pour afficher l'edt de ce groupe
     */
    public void afficherEDT(){
        jFrame.afficheEDT(myCalendar,groupe.getSeances(myCalendar.getWeek()));
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
