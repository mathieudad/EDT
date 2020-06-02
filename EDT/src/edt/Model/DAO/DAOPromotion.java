/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Promotion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mathi
 */
public class DAOPromotion extends DAO<Promotion> {

    public DAOPromotion(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Promotion obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion find(int id) {
        Promotion promo = new Promotion();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Promotion WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           promo = new Promotion(result.getInt("Id"),result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de promotion a cet id");
        }
        
        return promo;
    }
    
    
    public String findString(int id){
        String str = null;
        ResultSet result = null;
        String requete = "SELECT * FROM Promotion WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           str = result.getString("Nom");
          
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de promo a cet id");
           e.printStackTrace();
        }
        return str;
    }
    
    public Promotion findFromNom(String nom){
        Promotion promo = new Promotion();    
        ResultSet result = null;
        String requete = "SELECT * FROM Promotion WHERE Nom='" +nom+"';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           promo = new Promotion(result.getInt("Id"),result.getString("Nom"));
          
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de promo a ce nom");
           e.printStackTrace();
        }
        return promo;
    }
}
  
