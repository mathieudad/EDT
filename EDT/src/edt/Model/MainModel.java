/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import edt.Model.DAO.DAOCours;
import edt.Model.DAO.DAOEnseignant;
import edt.Model.DAO.DAOEtudiant;
import edt.Model.DAO.DAOSalle;
import edt.Model.DAO.DAOSeance;
import edt.Model.DAO.DAOTypeCours;
import edt.Model.DAO.DAOUtilisateur;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class MainModel {
    
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        MyCalendar calendar = new MyCalendar();
            calendar.semaineSuivante();
        try {
            try { 
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edt" ,"root",""); //edt est le nom de BD
            
            System.out.println("Connection Established");
     
            DAOUtilisateur DAOU = new DAOUtilisateur(con);
            Utilisateur  utilisateur = DAOU.find(8);
            System.out.println("Je suis");
            DAOSalle daos = new DAOSalle(con);
            Salle salle = daos.find(1);
            if(utilisateur.getDroit().equals("Etudiant")){
                Etudiant etu = (new DAOEtudiant(con)).find(utilisateur);
                for(Seance s : etu.getGroupe().getSeances()){
                    s.printInfos();
                }
                
            }
            if(utilisateur.getDroit().equals("Enseignant")){
                Enseignant ens = (new DAOEnseignant(con)).find(utilisateur);
                for(Seance s : ens.getSeances()){
                    s.printInfos();
                }
            }
            if(utilisateur.getDroit().equals("Referant")){
                Referant referant = new Referant(utilisateur, con);
            }
        
            /*
            MyCalendar myCalendar = new MyCalendar();
            
            ArrayList<String> enseignants = new ArrayList();
            ArrayList<String> groupes = new ArrayList();
            ArrayList<String> salles = new ArrayList();
            enseignants.add("Bond,James");
            groupes.add("Gr01,ING3");
            salles.add("P100,Eiffel-02");
            Time heureDebut = Time.valueOf("10:00:00");
            System.out.println(heureDebut);
            Time heureFin = Time.valueOf("11:30:00");
            System.out.println(heureDebut);
            Date date = Date.valueOf("2020-06-03");
            myCalendar.changeDate(date);
            int semaine = myCalendar.getWeek();
            System.out.println(date);
            System.out.println(semaine);
            int etat = 1;
            Cours cours = new DAOCours(con).findFromName("C");
            TypeCours typeCours = new DAOTypeCours(con).findFromName("TD-TP");
            System.out.println(typeCours.getNom());
            Seance create = new Seance(1,semaine,date,heureDebut,heureFin,etat,cours,typeCours,groupes,enseignants,salles);
            create.printInfos();
            new DAOSeance(con).create(create);
            */
        
            con.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        
    }
    
}
