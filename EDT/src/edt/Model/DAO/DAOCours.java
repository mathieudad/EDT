/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Cours;
import edt.Model.Salle;
import edt.Model.Site;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class DAOCours extends DAO<Cours> {

    public DAOCours(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Cours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cours find(int id) {
       Cours cours = new Cours();
       ResultSet result = null;
        String requete = "SELECT * FROM Cours WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           cours = new Cours(result.getInt("Id"), result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de cours a cet Id");
        }
        return cours;
    }
    
    /**
     * Retourne de cours en fonction du nom
     * @param nom
     * @return
     */
    public Cours findFromName(String nom){
        Cours cours = new Cours();
       ResultSet result = null;
        String requete = "SELECT * FROM Cours WHERE Nom='" +nom+"';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           cours = new Cours(result.getInt("Id"), result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de cours a ce nom");
        }
        return cours;
    }

    public ArrayList<Cours> findAll() {
       ArrayList<Cours> courss = new ArrayList();
        ResultSet result = null;
        String requeteUti = "SELECT * FROM Cours";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requeteUti);
           while(result.next()){
               Cours cours = new Cours(result.getInt("Id"), result.getString("Nom"));
               courss.add(cours);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas du tout de salles");
           e.printStackTrace();
        }
        return courss;
    }
    
}
