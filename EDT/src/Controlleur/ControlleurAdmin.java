/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameAdmin;
import edt.Model.Admin;
import edt.Model.Cours;
import edt.Model.DAO.DAOSeance;
import edt.Model.Enseignant;
import edt.Model.Groupe;
import edt.Model.MyCalendar;
import edt.Model.Salle;
import edt.Model.Seance;
import edt.Model.TypeCours;
import edt.Model.Utilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author mathi
 */
public class ControlleurAdmin implements ActionListener{
    Admin admin;
    JFrameAdmin jFrameAdmin;
    ControlleurReferant cr = null;
    Cours cours = null;
    TypeCours typeCours = null; 
    ArrayList<Enseignant> enseignants = new ArrayList();
    ArrayList<Groupe> groupes = new ArrayList();
    ArrayList<Salle> salles = new ArrayList();
    String dateString;
    Time heure_debut;
    Time heure_fin;
    
    public ControlleurAdmin(Admin admin) {
        this.admin = admin;
        jFrameAdmin = new JFrameAdmin(admin);
        addMenuListener();
        jFrameAdmin.getCoursButton().addActionListener(this);
        jFrameAdmin.getEnseignantButton().addActionListener(this);
        jFrameAdmin.getEnseignantButton2().addActionListener(this);
        jFrameAdmin.getGroupeButton().addActionListener(this);
        jFrameAdmin.getGroupeButton2().addActionListener(this);
        jFrameAdmin.getSkipEnseignantButton().addActionListener(this);
        jFrameAdmin.getSkipGroupeButton().addActionListener(this);
        jFrameAdmin.getGroupeButton3().addActionListener(this);
        jFrameAdmin.getSkipGroupeButton2().addActionListener(this);
        jFrameAdmin.getDateButton().addActionListener(this);
        jFrameAdmin.getTimeButton().addActionListener(this);
        jFrameAdmin.getSalleButton1().addActionListener(this);
        jFrameAdmin.getSalleButton2().addActionListener(this);
    }
    
    
    /**
     * Listener du retour a l'accueil dans le menu referant
     */
    public void addMenuListener(){
        jFrameAdmin.getAccueil().addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent me) {
                 jFrameAdmin.setVisible(false);
                 
                 if(cr !=null){
                     if(cr.getCe()!= null)
                        cr.getCe().getjFrame().setVisible(false);
                     if(cr.getCs()!=null)
                        cr.getCs().getjFrame().setVisible(false);
                     if(cr.getCg()!=null)
                        cr.getCg().getjFrame().setVisible(false);
                     if(cr.getCetu()!= null)
                        cr.getCetu().getjFrame().setVisible(false);
                    cr.getjFrame().setVisible(false);
                 }
                 
                 new ControlleurAdmin(admin);
            }

            @Override
            public void menuDeselected(MenuEvent me) {
                
            }

            @Override
            public void menuCanceled(MenuEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        jFrameAdmin.getConsulter().addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent me) {
               jFrameAdmin.setVisible(false);
                 
                 if(cr !=null){
                     if(cr.getCe()!= null)
                        cr.getCe().getjFrame().setVisible(false);
                     if(cr.getCs()!=null)
                        cr.getCs().getjFrame().setVisible(false);
                     if(cr.getCg()!=null)
                        cr.getCg().getjFrame().setVisible(false);
                     if(cr.getCetu()!= null)
                        cr.getCetu().getjFrame().setVisible(false);
                    cr.getjFrame().setVisible(false);
                 }
                 cr = new ControlleurReferant(admin);
                 cr.getjFrame().AjoutMenu(jFrameAdmin.getJMenuBar());
            }

            @Override
            public void menuDeselected(MenuEvent me) {
               
            }

            @Override
            public void menuCanceled(MenuEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        jFrameAdmin.getAjouter().addMenuListener(new MenuListener(){
            @Override
            public void menuSelected(MenuEvent me) {
                enseignants = new ArrayList();
                groupes = new ArrayList();
                salles = new ArrayList();
                jFrameAdmin.etape1Ajout(admin.getCours(),admin.getTypesCours());
            }

            @Override
            public void menuDeselected(MenuEvent me) {
               
            }

            @Override
            public void menuCanceled(MenuEvent me) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //on affiche les enseignants dispos pour ce cours
        if(actionEvent.getSource() == jFrameAdmin.getCoursButton()) {
           cours = admin.findCoursFromName((String) jFrameAdmin.getBoxCours().getSelectedItem());
           typeCours = admin.findTypeCoursFromName((String) jFrameAdmin.getBoxTypeCours().getSelectedItem());
           ArrayList<Enseignant> ens = new ArrayList();
           //on cherche les enseignants du cours selectionné
           for(Enseignant e : admin.getEnseignants())
               if(e.estEnseignant(cours))
                   ens.add(e);
           jFrameAdmin.etape2Ajout(ens);     
        }
        //on afiche les autres enseignants dispo pour ce cours
        if(actionEvent.getSource() == jFrameAdmin.getEnseignantButton()) {
            enseignants.add(admin.findEnseignantFromName((String) jFrameAdmin.getBoxEnseignant1().getSelectedItem()));
            ArrayList<Enseignant> ensbis = new ArrayList();
            //on cherche les autres enseignants du cours selectionné
            for(Enseignant e : admin.getEnseignants()){
                if(e.getId() != enseignants.get(0).getId() && e.estEnseignant(cours)){
                    ensbis.add(e);
                }
            }
            if(ensbis.isEmpty())
                jFrameAdmin.etape3Ajout(admin.getGroupes());
            else jFrameAdmin.etape2BisAjout(ensbis);
        }
        // on lance les groupes 
        if(actionEvent.getSource() == jFrameAdmin.getEnseignantButton2()) {
            enseignants.add(admin.findEnseignantFromName((String) jFrameAdmin.getBoxEnseignant2().getSelectedItem()));
            jFrameAdmin.etape3Ajout(admin.getGroupes());
        }
        //on skip pour lancer les groupes
        if(actionEvent.getSource() == jFrameAdmin.getSkipEnseignantButton()){
            jFrameAdmin.etape3Ajout(admin.getGroupes());
        }
        //on lance la deuxieme fenetre de groupe
        if(actionEvent.getSource() == jFrameAdmin.getGroupeButton()){
            groupes.add(admin.findGroupeFromName((String) jFrameAdmin.getBoxGroupe1().getSelectedItem()));
             ArrayList<Groupe> grbis = new ArrayList();
            for(Groupe e : admin.getGroupes()){
                if(e.getId() != groupes.get(0).getId()){
                    grbis.add(e);
                }
            }
            
            jFrameAdmin.etape3BisAjout(grbis);
        }
        //on skip pour passer aux salles
        if(actionEvent.getSource() == jFrameAdmin.getSkipGroupeButton()){
            jFrameAdmin.etape4Ajout();
        }
        // on lance la 3eme fenetre de groupe
        if(actionEvent.getSource() == jFrameAdmin.getGroupeButton2()){
            groupes.add(admin.findGroupeFromName((String) jFrameAdmin.getBoxGroupe2().getSelectedItem()));
             ArrayList<Groupe> grbis = new ArrayList();
            for(Groupe e : admin.getGroupes()){
                    if(e.getId() != groupes.get(0).getId() && e.getId() != groupes.get(1).getId()){
                        grbis.add(e);
                }
            }
            
            jFrameAdmin.etape3BisBisAjout(grbis);
        }
        //on skip pour passer aux salles dans le cas ou on a deux groupes
        if(actionEvent.getSource() == jFrameAdmin.getSkipGroupeButton2()){
            jFrameAdmin.etape4Ajout();
        }
        // on ajoute un troisieme groupe et on passe a l'etape 4
        if(actionEvent.getSource() == jFrameAdmin.getGroupeButton3()){
            groupes.add(admin.findGroupeFromName((String) jFrameAdmin.getBoxGroupe3().getSelectedItem()));
             ArrayList<Groupe> grbis = new ArrayList();
            for(Groupe e : admin.getGroupes()){
                for(Groupe e2 : groupes)
                    if(e.getId() != e2.getId()){
                        grbis.add(e);
                }
            }
          jFrameAdmin.etape4Ajout();
        }
        //on ajoute la date de la seance
        if(actionEvent.getSource() == jFrameAdmin.getDateButton()){
            String jour = jFrameAdmin.getJour().getText();
            String mois = jFrameAdmin.getMois().getText();
            String annee = jFrameAdmin.getAnnee().getText();
            dateString = annee +"-"+mois+"-"+jour;
            if(mauvaiseDate(dateString)){
                jFrameAdmin.etape4AjoutMauvaiseDate();
            }else{
                ArrayList<String> timePourSeance = admin.selectSeances(dateString, enseignants, groupes);   
                jFrameAdmin.etape5Ajout(timePourSeance);
            }
        }
        // on ajoute l'heure du debut de la sance et calcule l'heure de fin
        if(actionEvent.getSource() == jFrameAdmin.getTimeButton()){
            String heureDebut = (String) jFrameAdmin.getBoxTime().getSelectedItem();
            String heureFin = trouverHeureFin(heureDebut);
            heure_debut = Time.valueOf(heureDebut);
            heure_fin = Time.valueOf(heureFin);
            ArrayList<Salle> dispo = admin.salleDispo(dateString,heure_debut);
            jFrameAdmin.etape6Ajout(dispo);
        }
        
        if(actionEvent.getSource() == jFrameAdmin.getSalleButton1()){
            salles.add(admin.findSalleFromName((String) jFrameAdmin.getBoxSalle1().getSelectedItem()));
            if(admin.checkSalleCapacite(salles,groupes)){
                ajoutDansBDD();  
                jFrameAdmin.affichageOK();
            }
            else{
                ArrayList<Salle> dispo = admin.salleDispo(dateString,heure_debut);
                ArrayList<Salle> sabis = new ArrayList();
                for(Salle s : dispo){
                    for(Salle s2 : salles)
                        if(s.getId() != s2.getId()){
                            sabis.add(s);
                    }
                }
                jFrameAdmin.etape6BisAjout(salles);
            }
        }
        if(actionEvent.getSource() == jFrameAdmin.getSalleButton2()){
            salles.add(admin.findSalleFromName((String) jFrameAdmin.getBoxSalle2().getSelectedItem()));
            if(admin.checkSalleCapacite(salles,groupes)){
                ajoutDansBDD();  
                jFrameAdmin.affichageOK();
            }
            else{
                jFrameAdmin.problemeCapacite();
            }
        }
    }
    
    /**
     * Ajout de la seance dans la base de donnée
     */
    public void ajoutDansBDD(){
        Date date = Date.valueOf(dateString);
        MyCalendar mc = new MyCalendar();
        mc.changeDate(date);
        int semaine = mc.getWeek();
        ArrayList<String> ensStr = new ArrayList();
        ArrayList<String> grStr = new ArrayList();
        ArrayList<String> salleStr = new ArrayList();
        for(Enseignant e : enseignants){
            ensStr.add(e.getNom()+","+e.getPrenom());
        }
        for(Groupe e : groupes){
            grStr.add(e.getNom()+","+e.getPromo().getNom());
        }
        for(Salle e : salles){
            salleStr.add(e.getNom()+","+e.getSite().getNom());
        }
        Seance nouvelleSeance = new Seance(1, semaine, date, heure_debut, heure_fin,1, cours, typeCours, grStr, ensStr, salleStr);

        Connection con = connecterPourAjouter();
        new DAOSeance(con).create(nouvelleSeance);
        Utilisateur adm = new Utilisateur(admin.getId(),admin.getEmail(),admin.getPasswd(),admin.getNom(),admin.getPrenom(),admin.getDroit());
        admin = new Admin(adm, con);
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControlleurAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Retourne true si la date entrée est invalide
     * @param dateString
     * @return
     */
    public boolean mauvaiseDate(String dateString){
        try{
            Date d = Date.valueOf(dateString);
            
            if(d.compareTo(Calendar.getInstance().getTime())<0){
                return true;
            }
                
        }catch(IllegalArgumentException e){
            return true;
        }
        return false;
    }
    
    /**
     * renvoie leure de fin d'un cours a partir de celle du debut
     * @param heureDebut
     * @return
     */
    public String trouverHeureFin(String heureDebut){
        if(heureDebut.equals("08:30:00"))
            return "10:00:00";
       if(heureDebut.equals("10:00:00"))
            return "11:30:00";
       if(heureDebut.equals("11:30:00"))
            return "13:00:00";
       if(heureDebut.equals("13:00:00"))
            return "14:30:00";
       if(heureDebut.equals("14:30:00"))
            return "16:00:00";
       if(heureDebut.equals("16:00:00"))
            return "17:30:00";
       if(heureDebut.equals("17:30:00"))
            return "19:00:00";
       return null;
    }
      
    /**
     * Lance une une connexion a la base de donée
     * @return
     */
    public Connection connecterPourAjouter(){
        Connection con=null;
        try { 
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
         try {
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edt" ,"root",""); //edt est le nom de BD 
            System.out.println("Connection Established");
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         return con;
    }
}

    
    

