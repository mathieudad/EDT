/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Groupe;
import edt.Model.Promotion;
import edt.Model.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            int id_promo = gr.getPromo().getId_promo();
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
           groupe = new Groupe(result.getInt("Id"), result.getString("Nom"), promo);
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de groupe a cet id");
           e.printStackTrace();
        }
        
        return groupe;
    }
    
}
