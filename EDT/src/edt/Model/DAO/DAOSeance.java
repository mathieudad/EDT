/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model.DAO;

import edt.Model.Cours;
import edt.Model.Seance;
import edt.Model.TypeCours;
import edt.Model.Utilisateur;
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
public class DAOSeance extends DAO<Seance>{

    public DAOSeance(Connection con) {
        super(con);
    }

    @Override
    public boolean create(Seance obj) {
        ResultSet result = null;
        try {
            String requete = "INSERT INTO `seance` (`Id`, `Semaine`, `Date`, `Heure_debut`, `Heure_fin`, `Etat`, `Id_cours`, `Id_type`) VALUES (NULL, '"+obj.getSemaine()+"', '"+obj.getDate()+"', '"+obj.getHeure_debut()+"', '"+obj.getHeure_fin()+"', '"+obj.getEtat()+"', '"+obj.getCours().getId()+"', '"+obj.getTypeCours().getId()+"');";
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Requete d'ajout effectuée ");
            String requeteid = "SELECT MAX(Id) FROM Seance;";   
            result = st.executeQuery(requeteid);
            result.next();
            int id_seance = result.getInt("MAX(Id)");
            System.out.println(id_seance);
            createSeanceEnseignant(id_seance,obj,st);
            createSeanceSalle(id_seance,obj,st);
            createSeanceGroupe(id_seance,obj,st);
            st.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAOUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean createSeanceEnseignant(int id_seance, Seance seance, Statement st){
        
            try {
                for(String str : seance.getEnseignants()){
                    String[] s = str.split(",");
                    int id = new DAOUtilisateur(con).findFromName(s[0],s[1]).getId();
                    String result = "INSERT INTO seance_enseignants(Id_seance, Id_enseignant) VALUES('"+id_seance+"','"+id+"');";
                    st.executeUpdate(result);
                }
                return true;
            } catch (SQLException ex) {
               System.out.println("error creation seance ens");
            }
        return false;
    }
        
    public boolean createSeanceGroupe(int id_seance, Seance seance, Statement st){
        
            try {
                for(String str : seance.getGroupes()){
                    String[] s = str.split(",");
                    int id = new DAOGroupe(con).findFromName(s[0],s[1]).getId();
                    String result = "INSERT INTO seance_groupe(Id_seance, Id_groupe) VALUES('"+id_seance+"','"+id+"');";
                    st.executeUpdate(result);
                }
                return true;
            } catch (SQLException ex) {
               System.out.println("error creation seance groupe");
            }
        return false;
    }
    
    public boolean createSeanceSalle(int id_seance, Seance seance, Statement st){
        
            try {
                for(String str : seance.getSalles()){
                    String[] s = str.split(",");
                    int id = new DAOSalle(con).findFromName(s[0],s[1]).getId();
                    String result = "INSERT INTO seance_salles(Id_seance, Id_salle) VALUES('"+id_seance+"','"+id+"');";
                    st.executeUpdate(result);
                }
                return true;
            } catch (SQLException ex) {
               System.out.println("error creation seance salle");
            }
        return false;
    }
    
    @Override
    public boolean delete(Seance obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Seance obj) {
        try{
            String requete = "UPDATE Seance SET Semaine="+obj.getSemaine()+", Date = "+ obj.getDate()+", Heure_debut=" + obj.getHeure_debut()+", Heure_fin="+obj.getHeure_fin()+", Etat="+obj.getEtat()+" WHERE Id="+obj.getId();
            Statement st = con.createStatement();
            st.executeUpdate(requete);
            System.out.println("Requete de modif effectuée");
            return true;
        }catch(SQLException e){
            System.err.println("error mise a jour de la seance");
        }
        return false;
    }

    @Override
    public Seance find(int id) {
        
        Seance seance = new Seance();
        ResultSet result = null;
        String requete = "SELECT * FROM Seance WHERE Id ="+ id+ ";";
        try {    
            Statement stmt = con.createStatement();
            result = stmt.executeQuery(requete);    
            result.next();
            ArrayList<String> enseignants = findEnseignants(id);
            ArrayList<String> groupes = findGroupes(id);
            ArrayList<String> salles = findSalles(id);
            Cours cours = new DAOCours(con).find(result.getInt("Id_cours"));
            TypeCours typeCours = new DAOTypeCours(con).find(result.getInt("Id_type"));
            seance = new Seance(id, result.getInt("semaine"), result.getDate("Date"), result.getTime("Heure_Debut"), result.getTime("Heure_fin"), result.getInt("Etat"), cours, typeCours , groupes, enseignants, salles);
            stmt.close();
        } catch (SQLException e) {
            System.err.println("error pas de seance a cet id");
            e.printStackTrace();
        }
        return seance;
    }
    
    /**
     * retourne la liste des noms prenoms des enseignants de la seance
     * @param id_seance
     * @return
     */
    public ArrayList<String> findEnseignants(int id_seance){
        ArrayList<String> enseignants = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_enseignants WHERE Id_seance ="+ id_seance+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               Utilisateur uti = new DAOUtilisateur(con).find(result.getInt("Id_enseignant"));
               String str = uti.getNom() + "," + uti.getPrenom();
               enseignants.add(str);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list enseignant dans seance");
           e.printStackTrace();
        }
        return enseignants;
    }
    
    /**
     * retourne la liste des groupes et promos de la seance
     * @param id_seance
     * @return
     */
    public ArrayList<String> findGroupes(int id_seance){
        ArrayList<String> groupes = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_groupe WHERE Id_seance ="+ id_seance+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               String gr = new DAOGroupe(con).findString(result.getInt("Id_groupe"));
               groupes.add(gr);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list groupe dans seance");
           e.printStackTrace();
        }
        return groupes;
    }
    
    /**
     * retourne la liste des salles et site ou la seance ce deroule
     * @param id_seance
     * @return
     */
    public ArrayList<String> findSalles(int id_seance){
        ArrayList<String> salles = new ArrayList<>();
        ResultSet result = null;
        String requete = "SELECT * FROM seance_salles WHERE Id_seance ="+ id_seance+ ";";
        try {
           Statement stmt = con.createStatement();
           result = stmt.executeQuery(requete);
           while(result.next()){
               String salle = new DAOSalle(con).findString(result.getInt("Id_salle"));          
               salles.add(salle);
            }
           stmt.close();
        } catch (SQLException e) {
           System.err.println("error creation array list salle dans seance");
           e.printStackTrace();
        }
        return salles;
    }
    
}
