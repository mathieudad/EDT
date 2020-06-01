/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Site;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mathi
 */
public class DAOSite extends DAO<Site>{

    public DAOSite(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Site obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Site obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Site obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Site find(int id) {
        Site site = new Site();
        ResultSet result = null;
        String requete = "SELECT * FROM Site WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           site = new Site(result.getInt("Id"), result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error ce site n'existe pas");
        }
        
        return site;
    }

    String findString(int id) {
        String str = null;
        ResultSet result = null;
        String requete = "SELECT * FROM Site WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           str = result.getString("Nom");
          
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas site a cet id");
           e.printStackTrace();
        }
        return str;
    }
    
    public Site findFromNom(String nom){
        Site site = new Site();
        ResultSet result = null;
        String requete = "SELECT * FROM Site WHERE Nom=" +nom+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           site = new Site(result.getInt("Id"),result.getString("Nom"));
          
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de promo a ce nom");
           e.printStackTrace();
        }
        return site;
    }
    
    
}
