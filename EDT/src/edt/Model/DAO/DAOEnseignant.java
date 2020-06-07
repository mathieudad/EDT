/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Cours;
import edt.Model.Enseignant;
import edt.Model.Etudiant;
import edt.Model.Groupe;
import edt.Model.Seance;
import edt.Model.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class DAOEnseignant extends DAO<Enseignant>{

    public DAOEnseignant(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Enseignant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Enseignant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Enseignant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enseignant find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Cherche l'enseignant associé a l'utilisateur donné
     * @param uti
     * @return
     */
    public Enseignant find(Utilisateur uti){
        Enseignant enseignant = new Enseignant();
        ArrayList<Cours> cours = new ArrayList();
        ResultSet result = null;
        String requete = "SELECT * FROM Enseignant WHERE Id_utilisateur=" +uti.getId()+";";
        
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           ArrayList<Seance> seances = findSeances(uti.getId());
           cours.add(new DAOCours(con).find(result.getInt("Id_cours")));
           while(result.next()){
               cours.add(new DAOCours(con).find(result.getInt("Id_cours")));
           }
           enseignant = new Enseignant(cours, seances, uti.getId(), uti.getEmail(), uti.getPasswd(), uti.getNom() , uti.getPrenom(),uti.getDroit());
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas d'enseignant à cet id");
           e.printStackTrace();
        }
        
        return enseignant; 
       
    }
    
    /**
     * Trouve toutes les seances d'un enseignant donné
     * @param id_enseignant
     * @return
     */
    public ArrayList<Seance> findSeances(int id_enseignant){
        ArrayList<Seance> seances = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_enseignants WHERE Id_enseignant ="+ id_enseignant+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               Seance seance = new DAOSeance(con).find(result.getInt("Id_seance"));
               seances.add(seance);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list seance dans enseignant");
           e.printStackTrace();
        }
        return seances;
        
    }
    
    /**
     * cherche tous les enseignants de la BDD
     * @return
     */
    public ArrayList<Enseignant> findAll(){
        ArrayList<Enseignant> enseignants = new ArrayList();
        ResultSet result = null;
        String requeteUti = "SELECT * FROM Utilisateur WHERE Droit ='Enseignant';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requeteUti);
           while(result.next()){
               Utilisateur utilisateur = new Utilisateur(result.getInt("Id"), result.getString("Email"), result.getString("Passwd"),result.getString("Nom"),result.getString("Prenom"),result.getString("Droit"));
               Enseignant ens = find(utilisateur);
               enseignants.add(ens);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list seance dans enseignant");
           e.printStackTrace();
        }
        return enseignants;
    }
    
}
