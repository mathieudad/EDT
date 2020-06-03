/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEDT;
import edt.Model.Enseignant;
import edt.Model.MyCalendar;
import edt.Model.Seance;
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
    
    public ControlleurEnseignant(Enseignant enseignant) throws InterruptedException{
        this.enseignant =enseignant;
        myCalendar = new MyCalendar();
        for(Seance e : enseignant.getSeances(myCalendar.getWeek())){
            System.out.println(e.printInfos());
        }
        jFrameEnseignant = new JFrameEDT(myCalendar, enseignant.getSeances(myCalendar.getWeek()));
        jFrameEnseignant.getSemainePrecedante().addActionListener(this);
        jFrameEnseignant.getSemaineSuivante().addActionListener(this);
    }
    
    
    public void afficherEDT(){
        jFrameEnseignant.afficheEDT(myCalendar,enseignant.getSeances(myCalendar.getWeek()));
    }

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
    
}
