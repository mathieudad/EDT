/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import edt.Model.MyCalendar;
import edt.Model.Seance;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 * framme affichant l'emploie du temps pour une liste  de seances données
 * @author mathi
 */
public class JFrameEDT extends JFrame{
    private JPanel panel;
    private JPanel panelButton;
    private JButton semaineSuivante;
    private JButton semainePrecedante;
    private JTextArea numeroWeek;
    private int row;
    private int column;
    
    public JFrameEDT(MyCalendar myCalendar,ArrayList<Seance> seances){
        panel = new JPanel();
        panelButton = new JPanel();
        semaineSuivante = new JButton("semaineSuivante");
        semainePrecedante = new JButton("semainePrecedante");
        numeroWeek = new JTextArea();
         GridLayout grid = new GridLayout(8,6); 
         panel.setLayout(grid);
         panelButton.add(semainePrecedante);
         panelButton.add(semaineSuivante);
         panelButton.add(numeroWeek);
         panel.setFocusable(true);
         panelButton.setFocusable(true);
         afficheEDT(myCalendar,seances);
         build();
    }
    
    
    private void build(){
        this.setTitle("EDT");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        panel.setPreferredSize(new Dimension(800,800));       
        contenu.add(panel);
        contenu.add(panelButton);
        this.add(contenu);
        this.setVisible(true);
    }
    /**
     *affiche dans la premiere colonne les horaires de la seance
     */
    public void afficheHoraires(Calendar heure_debut, Calendar heure_fin){
        
        String time = heure_debut.get(Calendar.HOUR_OF_DAY)+":" + heure_debut.get(Calendar.MINUTE)+ " - " + heure_fin.get(Calendar.HOUR_OF_DAY) +" : " + heure_fin.get(Calendar.MINUTE);
        JTextArea heure = new JTextArea(time);
        Color gris = new Color(241, 241, 241);
        heure.setBackground(gris);
        panel.add(heure);
        heure_debut.add(Calendar.HOUR_OF_DAY,1);
        heure_debut.add(Calendar.MINUTE, 30);           
        heure_fin.add(Calendar.HOUR_OF_DAY,1);
        heure_fin.add(Calendar.MINUTE, 30);
        column ++;
    }
    
    /**
     *Affiche les jours de la seamine donnée en arguments dans la permiere ligne
     * @param myCalendar
     */
    public void afficheDay(MyCalendar myCalendar) {
        
        panel.add(new JTextArea(""));
        for(int i=0;i<5;i++){
            java.sql.Date sqlDate = new java.sql.Date(myCalendar.quelleDate().getTime());
            String str = sqlDate.toString(); 
            JTextArea jour = new JTextArea(str);
            Color gris = new Color(241, 241, 241);
            jour.setBackground(gris);
            panel.add(jour); 
            myCalendar.jourSuivant();
            column++;
        }
        column=0;
        row++;
    }
    
    /**
     * affiche les seances de l'etudiant
     * @param mycalendar
     * @param seances
     */
    public void afficheseance(MyCalendar mycalendar,ArrayList<Seance> seances){
        Time time = getTimeFromRow();
        Date date = getDateFromColumn(mycalendar);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String date1 = sqlDate.toString();
        for(Seance s : seances){    
            String date2 = s.getDate().toString();
            if(date2.equals(date1)&& s.getHeure_debut().equals(time)){
                JTextArea texte = new JTextArea(s.printInfos());
                Color vert = new Color(196,254,145);
                texte.setBackground(vert);
                panel.add(texte);
                column++;
                
                return;
            }
        }
        panel.add(new JTextArea("--------"));    
        column++;
    }
    
    /**
     * appelle les trois fonctions precedentes pour afficher l'ensemble de EDT
     * @param myCalendar
     * @param seances
     */
    public void afficheEDT(MyCalendar myCalendar,ArrayList<Seance> seances){
        row=0;
        column=0;
        numeroWeek.setText("Semaine :" + Integer.toString(myCalendar.getWeek()));
        Calendar heure_debut = Calendar.getInstance();
        Calendar heure_fin = Calendar.getInstance();
        heure_debut.set(Calendar.HOUR_OF_DAY,8);
        heure_debut.set(Calendar.MINUTE,30);
        heure_debut.set(Calendar.SECOND,0);
        
        heure_fin.set(Calendar.HOUR_OF_DAY,10);
        heure_fin.set(Calendar.MINUTE,00);
        heure_fin.set(Calendar.SECOND,0);
        afficheDay(myCalendar);
        for(int i= 0; i<7; i++){
            afficheHoraires(heure_debut, heure_fin);
            
            for(int j=0; j<5;j++){
               afficheseance(myCalendar,seances);
            }
            row++;
            column=0;
        }
        myCalendar.revenirAuLundi();
    }
    
     
    /**
     *recupere le temps a partir de la ligne ou l'on se situe
     * @return
     */
    public Time getTimeFromRow(){
        if(row==1){
            return Time.valueOf("8:30:00");
        }if(row==2){
            return Time.valueOf("10:00:00");
        }if(row==3){
            return Time.valueOf("11:30:00");
        }if(row==4){
            return Time.valueOf("13:00:00");
        }if(row==5){
            return Time.valueOf("14:30:00");
        }if(row==6){
            return Time.valueOf("16:00:00");
        }if(row==7){
            return Time.valueOf("17:30:00");
        }
        return null;
    }
    
    /**
     *recupere la date a partir de la colonne ou l'on se situe
     * @param myCalendar
     * @return
     */
    public Date getDateFromColumn(MyCalendar myCalendar){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR,myCalendar.getWeek());
        if(column==1){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return calendar.getTime();  
        }if(column==2){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
            return calendar.getTime();  
        }if(column==3){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);  
            return calendar.getTime(); 
        }if(column==4){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY); 
            return calendar.getTime();  
        }if(column==5){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY); 
            return calendar.getTime(); 
        }
        return null;
    }
    
    /**
     * ajout du menu a la frame en fonction de qui l'appelle
     * @param menuBar
     */
    public void ajoutMenu(JMenuBar menuBar){
        this.setJMenuBar(menuBar);
    }
    
    public JButton getSemaineSuivante() {
        return semaineSuivante;
    }

    public JButton getSemainePrecedante() {
        return semainePrecedante;
    }

    public JTextArea getNumeroWeek() {
        return numeroWeek;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getPanelButton() {
        return panelButton;
    }

   
    
}
