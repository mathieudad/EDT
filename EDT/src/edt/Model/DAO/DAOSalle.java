/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Salle;
import edt.Model.Seance;
import edt.Model.Site;
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
public class DAOSalle extends DAO<Salle>{

    public DAOSalle(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Salle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Salle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Salle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Salle find(int id) {
        Salle salle = new Salle();
        ResultSet result = null;
        String requete = "SELECT * FROM Salle WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           Site site = (new DAOSite(con)).find(result.getInt("Id_site"));
           ArrayList<Seance> seances = findSeances(id);
           salle = new Salle(result.getInt("Id"), result.getString("Nom"), result.getInt("Capacite"), site, seances);
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de salle a cet id");
        }
        return salle;
    }
    
     public ArrayList<Seance> findSeances(int id_salle){
        ArrayList<Seance> seances = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_salles WHERE Id_salle ="+ id_salle+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               Seance seance = new DAOSeance(con).find(result.getInt("Id_seance"));
               seances.add(seance);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list seance dans salle");
           e.printStackTrace();
        }
        return seances;
        
    }

    String findString(int id) {
        String str = null;
        ResultSet result = null;
        String requete = "SELECT * FROM Salle WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           str = result.getString("Nom");
           String site = new DAOSite(con).findString(result.getInt("Id_site"));
           str = str + "," + site;
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de groupe a cet id");
           e.printStackTrace();
        }
        return str;
    }
    
    public Salle findFromName(String nomSalle, String nomSite){
        Salle salle = new Salle();
        ResultSet result = null;
        try {
           Statement stmt = con.createStatement();
           Site site = new DAOSite(con).findFromNom(nomSite);
           String requete = "SELECT * FROM Salle WHERE Nom='" +nomSalle+"' AND Id_site="+site.getId()+";";
           result = stmt.executeQuery(requete);
           result.next();
           ArrayList<Seance> seances = findSeances(result.getInt("Id")); 
           salle = new Salle(result.getInt("Id"),nomSalle,result.getInt("Capacite"),site, seances);      
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de salle a ce nom");
           e.printStackTrace();
        }
        return salle;
    }
    
}
