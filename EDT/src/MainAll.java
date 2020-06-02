
import Controlleur.ControlleurEtudiant;
import edt.Model.DAO.DAOEtudiant;
import edt.Model.DAO.DAOUtilisateur;
import edt.Model.Etudiant;
import edt.Model.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mathi
 */
public class MainAll {
    public static void main(String[] args){
         
            try { 
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edt" ,"root",""); //edt est le nom de BD
            
            System.out.println("Connection Established");
     
            DAOUtilisateur DAOU = new DAOUtilisateur(con);
            Utilisateur  utilisateur = DAOU.find(1);
            if(utilisateur.getDroit().equals("Etudiant")){
                Etudiant etu = (new DAOEtudiant(con)).find(utilisateur);
                new ControlleurEtudiant(etu);
            }
            con.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
    }
}