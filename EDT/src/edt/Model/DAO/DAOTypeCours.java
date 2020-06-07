/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.TypeCours;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class DAOTypeCours extends DAO<TypeCours>{

    public DAOTypeCours(Connection con) {
        super(con);
    }

    @Override
    public boolean create(TypeCours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(TypeCours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(TypeCours obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TypeCours find(int id) {
        TypeCours typeCours = new TypeCours();
        ResultSet result = null;
        String requete = "SELECT * FROM TypeCours WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           typeCours = new TypeCours(result.getInt("Id"), result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error ce type de cours n'existe pas");
        }
        return typeCours;
    }

    /**
     * renvoie le type de cours en fonction du nom
     * @param nom
     * @return
     */
    public TypeCours findFromName(String nom) {
       TypeCours typeCours = new TypeCours();
        ResultSet result = null;
        String requete = "SELECT * FROM TypeCours WHERE Nom='" +nom+"';";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           typeCours = new TypeCours(result.getInt("Id"), result.getString("Nom"));
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de type de cours a ce nom");
        }
        return typeCours;
    }

    /**
     * Trouve et renvoie tous les types de cours
     * @return
     */
    public ArrayList<TypeCours> findAll() {
        ArrayList<TypeCours> typesCours = new ArrayList();
        ResultSet result = null;
        String requeteUti = "SELECT * FROM typecours";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requeteUti);
           while(result.next()){
               TypeCours cours = new TypeCours(result.getInt("Id"), result.getString("Nom"));
               typesCours.add(cours);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas du tout de salles");
           e.printStackTrace();
        }
        return typesCours;
    }
    
}
