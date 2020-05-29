/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            String requete = "INSERT INTO utilisateur(Id,Email,Passwd,Nom,Prenom,Droit) VALUES (NULL,'" + uti.getEmail() +"','"+uti.getPasswd()+"','"+uti.getNom()+"','"+uti.getPrenom()+"','"+uti.getDroit()+"');";
            System.out.println(requete);
            Statement st = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            st.executeUpdate(requete);
            System.out.println("Requete d'ajout effectuée de " +uti.getNom()+uti.getPrenom());
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        Utilisateur Utilisateur = new Utilisateur();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Utilisateur WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           System.out.println(result.getString("Email"));
           Utilisateur = new Utilisateur(result.getInt("Id"), result.getString("Email"), result.getString("Passwd"),result.getString("Nom"),result.getString("Prenom"),result.getString("Droit"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de user a cet id");
           e.printStackTrace();
        }
        
        return Utilisateur;
    }

       
}
