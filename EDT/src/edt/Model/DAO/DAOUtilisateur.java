/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.MauvaisEmailOuMdp;
import edt.Model.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mathi
 */
public class DAOUtilisateur extends DAO<Utilisateur>{

    public DAOUtilisateur(Connection con) {
        super(con);
    }
    
   

    @Override
    public boolean create(Utilisateur uti) {
        
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
       return false;
    }

    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = new Utilisateur();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Utilisateur WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           utilisateur = new Utilisateur(result.getInt("Id"), result.getString("Email"), result.getString("Passwd"),result.getString("Nom"),result.getString("Prenom"),result.getString("Droit"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de user a cet id");
        }
        
        return utilisateur;
    }
    
    public Utilisateur findFromName(String nom, String prenom){
        Utilisateur utilisateur = new Utilisateur();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Utilisateur WHERE Nom='" +nom+"' AND Prenom ='"+prenom+"';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           utilisateur = new Utilisateur(result.getInt("Id"), result.getString("Email"), result.getString("Passwd"),result.getString("Nom"),result.getString("Prenom"),result.getString("Droit"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de user a ce nom");
        }
        
        return utilisateur;
    }
    
    public Utilisateur findFromConnexion(String email, String passWd) throws MauvaisEmailOuMdp{
        Utilisateur utilisateur = new Utilisateur();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Utilisateur WHERE Email='" +email+"' AND Passwd ='"+passWd+"';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           utilisateur = new Utilisateur(result.getInt("Id"), result.getString("Email"), result.getString("Passwd"),result.getString("Nom"),result.getString("Prenom"),result.getString("Droit"));
           stmt.close();
        } catch (SQLException e) {
           throw new MauvaisEmailOuMdp("Pas les bonnes infos");
        }
        
        return utilisateur;
    }
       
}
