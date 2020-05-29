/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Salle;
import edt.Model.Site;
import edt.Model.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
           salle = new Salle(result.getInt("Id"), result.getString("Nom"), result.getInt("Capacite"), site);
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de user a cet id");
        }
        return salle;
    }
    
    
}
