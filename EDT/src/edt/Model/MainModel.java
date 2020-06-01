/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import edt.Model.DAO.DAOEnseignant;
import edt.Model.DAO.DAOEtudiant;
import edt.Model.DAO.DAOSalle;
import edt.Model.DAO.DAOUtilisateur;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author mathi
 */
public class MainModel {
    
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        try {
            try { 
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edt" ,"root",""); //edt est le nom de BD
            
            System.out.println("Connection Established");
            DAOUtilisateur DAOU = new DAOUtilisateur(con);
            Utilisateur  utilisateur = DAOU.find(4);
            utilisateur.printinfos();
            DAOSalle daos = new DAOSalle(con);
            Salle salle = daos.find(1);
            salle.printInfos();
            if(utilisateur.getDroit().equals("Etudiant")){
                Etudiant etu = (new DAOEtudiant(con)).find(utilisateur);
                etu.getGroupe().printInfos();
                for(Seance s : etu.getGroupe().getSeances()){
                    s.printInfos();
                }
                
            }
            MyCalendar calendar = new MyCalendar();
            System.out.println(calendar.quelleDate());
            System.out.println(calendar.getWeek());
            calendar.semaineSuivante();
            System.out.println(calendar.getWeek());
            System.out.println(calendar.quelleDate());
            
            if(utilisateur.getDroit().equals("Enseignant")){
                Enseignant ens = (new DAOEnseignant(con)).find(utilisateur);
                for(Seance s : ens.getSeances()){
                    s.printInfos();
                }
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
