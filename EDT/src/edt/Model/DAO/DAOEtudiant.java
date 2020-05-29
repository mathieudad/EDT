/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Etudiant;
import edt.Model.Groupe;
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
public class DAOEtudiant extends DAO<Etudiant>{

    public DAOEtudiant(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Etudiant etu) {
         try {
            String requete = "INSERT INTO Etudiant(Id_utilisateur,Numero,IdGroupe) VALUES (NULL,'" + etu.getNumero() +"','"+etu.getGroupe().getId_groupe()+"');";
            System.out.println(requete);
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Requete d'ajout effectuée ");
            
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Etudiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Etudiant obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etudiant find(int id) {
        return new Etudiant();
    }
    
    public Etudiant find(Utilisateur uti){
        Etudiant etudiant = new Etudiant();
        ResultSet result = null;
        String requete = "SELECT * FROM Etudiant WHERE Id_utilisateur=" +uti.getId()+";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           result.next();
           Groupe gr = (new DAOGroupe(con)).find(result.getInt("Id_Groupe"));
           etudiant = new Etudiant(uti.getId(),uti.getEmail(), uti.getPasswd(), uti.getNom() , uti.getPrenom(),uti.getDroit(),result.getInt("Numero"), gr);
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error pas de user a cet id");
           e.printStackTrace();
        }
        
        return etudiant;
    }

    private Object DAOGroupe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
