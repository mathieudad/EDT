/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlleur;

import Vue.JFrameConnexion;
import edt.Model.Admin;
import edt.Model.DAO.DAOEnseignant;
import edt.Model.DAO.DAOEtudiant;
import edt.Model.DAO.DAOUtilisateur;
import edt.Model.Enseignant;
import edt.Model.Etudiant;
import edt.Model.MauvaisEmailOuMdp;
import edt.Model.Referant;
import edt.Model.Utilisateur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class ControleurConnexion implements ActionListener{
    JFrameConnexion jFrameConnexion;
    Connection con = null;
    
    /**
     * Constructeur de connexion
     */
    public ControleurConnexion(){
        jFrameConnexion = new JFrameConnexion();
        jFrameConnexion.getButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == jFrameConnexion.getButton()){
            String email = jFrameConnexion.getEmail().getText();
            String passWd = jFrameConnexion.getPassword().getText();
            System.out.println(passWd);
            con = connexionBDD();
            try {
                Utilisateur utilisateur = new DAOUtilisateur(con).findFromConnexion(email, passWd);
                lancementBonControleur(utilisateur);
            } catch (MauvaisEmailOuMdp ex) {
                jFrameConnexion.essayerEncore();
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ControleurConnexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * Se connecte a la base de donnee
     * @return
     */
    public Connection connexionBDD(){
        Connection con = null;
        try { 
                Class.forName("com.mysql.jdbc.Driver");
            }catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edt" ,"root",""); //edt est le nom de BD 
            System.out.println("Connection Established");
             } catch (SQLException ex) {
               ex.printStackTrace();
            }
            return con;
    }
    
    /**
     * Lance le controleur correspondant au droit de l'utilisateur
     * @param utilisateur
     */
    public void lancementBonControleur(Utilisateur utilisateur){
        jFrameConnexion.setVisible(false);
         if(utilisateur.getDroit().equals("Etudiant")){
                Etudiant etu = (new DAOEtudiant(con)).find(utilisateur);
                new ControlleurEtudiant(etu);
            }
            if(utilisateur.getDroit().equals("Enseignant")){
                Enseignant etu = (new DAOEnseignant(con)).find(utilisateur);
                new ControlleurEnseignant(etu);
            }
            if(utilisateur.getDroit().equals("Referant")){
                Referant referant = new Referant(utilisateur,con);
                new ControlleurReferant(referant);
            }
            if(utilisateur.getDroit().equals("Admin")){
                Admin admin = new Admin(utilisateur,con);
                new ControlleurAdmin(admin);
            }
    }
}
