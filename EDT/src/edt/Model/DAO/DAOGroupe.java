/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Groupe;
import edt.Model.Promotion;
import edt.Model.Seance;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class DAOGroupe extends DAO<Groupe>{

    public DAOGroupe(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Groupe gr) {
         try {
            int id_promo = gr.getPromo().getId();
            String requete = "INSERT INTO Groupe(Id,Nom,Id_Promo) VALUES (NULL,'" + gr.getNom() +"','"+id_promo+"');";
            System.out.println(requete);
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Requete d'ajout de groupe effectuée"); 
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Groupe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Groupe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Groupe find(int id) {
        Groupe groupe = new Groupe();
       
        ResultSet result = null;
        String requete = "SELECT * FROM Groupe WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           Promotion promo = new DAOPromotion(con).find(result.getInt("Id_Promo"));
           ArrayList<Seance> seances = findSeances(id);
           groupe = new Groupe(result.getInt("Id"), result.getString("Nom"), promo, seances);
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de groupe a cet id");
           e.printStackTrace();
        }
        
        return groupe;
    }
    
    /**
     * trouve toutes les seances pour un id Groupe donné
     * @param id_groupe
     * @return
     */
    public ArrayList<Seance> findSeances(int id_groupe){
        ArrayList<Seance> seances = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_groupe WHERE Id_groupe ="+ id_groupe+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               Seance seance = new DAOSeance(con).find(result.getInt("Id_seance"));
               seances.add(seance);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list seance dans groupe");
           e.printStackTrace();
        }
        return seances;
        
    }
    
    public String findString(int id){
        String str = null;
        ResultSet result = null;
        String requete = "SELECT * FROM Groupe WHERE Id=" +id+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           str = result.getString("Nom");
           String promo = new DAOPromotion(con).findString(result.getInt("Id_Promo"));
           str = str + "," + promo;
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de groupe a cet id");
           e.printStackTrace();
        }
        return str;
    }
    
    /**
     * recherche le groupe en fonction du nom et de la promo
     * @param nomGr
     * @param nomProm
     * @return
     */
    public Groupe findFromName(String nomGr, String nomProm){
        Groupe groupe = new Groupe();
        ResultSet result = null;
        try {
           Statement stmt = con.createStatement();
           Promotion promo = new DAOPromotion(con).findFromNom(nomProm);
           String requete = "SELECT * FROM Groupe WHERE Nom='"+nomGr+"' AND Id_Promo="+promo.getId()+";";
           result = stmt.executeQuery(requete);
           result.next();
           ArrayList<Seance> seances = findSeances(result.getInt("Id")); 
           groupe = new Groupe(result.getInt("Id"),nomGr,promo, seances);      
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de groupe a ce nom");
           e.printStackTrace();
        }
        return groupe;
    }
    
    /**
     * cherche tous les groupes de la base et les renvoie sous forme d'ArrayList
     * @return
     */
    public ArrayList<Groupe> findAll(){
        ArrayList<Groupe> groupes = new ArrayList();
        ResultSet result = null;
        String requeteUti = "SELECT * FROM Groupe";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requeteUti);
           while(result.next()){
               Promotion promo = (new DAOPromotion(con)).find(result.getInt("Id_promo"));
               ArrayList<Seance> seances = findSeances(result.getInt("Id"));
               Groupe groupe = new Groupe(result.getInt("Id"), result.getString("Nom"), promo, seances);
               groupes.add(groupe);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas du tout de salles");
           e.printStackTrace();
        }
        return groupes;
    }
    
}
