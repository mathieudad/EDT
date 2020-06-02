/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameEtudiant;
import edt.Model.Etudiant;
import edt.Model.MyCalendar;
import edt.Model.Seance;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author mathi
 */
public class ControlleurEtudiant implements ActionListener{
    MyCalendar myCalendar;
    Etudiant etudiant;
    JFrameEtudiant jFrameEtudiant;
    
    public ControlleurEtudiant(Etudiant etudiant){
        this.etudiant = etudiant;
        myCalendar = new MyCalendar();
        jFrameEtudiant = new JFrameEtudiant();
        jFrameEtudiant.getSemainePrecedante().addActionListener(this);
        jFrameEtudiant.getSemaineSuivante().addActionListener(this);
        afficherEDT();
    }
    
    
    public ArrayList<Seance> recupererSeances(){
        int week = myCalendar.getWeek();
        ArrayList<Seance> seancesUtiles = etudiant.getGroupe().seancesFromWeek(week);
        return seancesUtiles;
    }
    
    public void afficherEDT(){
        jFrameEtudiant.getNumeroWeek().setText("Semaine :" + Integer.toString(myCalendar.getWeek()));
        jFrameEtudiant.getPanel().removeAll();
        ArrayList<Seance> seances = recupererSeances();
        for(int i= 0; i< 48; i++)
            //for(int j= 0; j<8;j++)
            jFrameEtudiant.getPanel().add(new JTextArea("ok")); 
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         if(actionEvent.getSource() == jFrameEtudiant.getSemaineSuivante()) {
            myCalendar.semaineSuivante();
            afficherEDT();
        }
        if(actionEvent.getSource() == jFrameEtudiant.getSemainePrecedante()) {
            myCalendar.semainePrecedente();
            afficherEDT();
        } 
     }
    
}
    
