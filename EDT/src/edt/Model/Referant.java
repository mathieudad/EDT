/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import edt.Model.DAO.DAOEnseignant;
import edt.Model.DAO.DAOEtudiant;
import edt.Model.DAO.DAOGroupe;
import edt.Model.DAO.DAOSalle;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class Referant extends Utilisateur{
    protected ArrayList<Enseignant> enseignants; //contient l'ensemble des enseignants de la DB
    protected ArrayList<Etudiant> etudiants; // contient l'ensemble des etudiants de la DB
    protected ArrayList<Salle> salles; //contient l'ensemble des salles de la DB
    protected ArrayList<Groupe> groupes; //contient l'ensemble des groupes de la DB
    /**
     * Constructeur du referant
     * @param utilisateur
     * @param con
     */
    public Referant(Utilisateur utilisateur, Connection con) {
        super(utilisateur.getId(), utilisateur.getEmail(), utilisateur.getPasswd(), utilisateur.getNom(),utilisateur.getPrenom(), utilisateur.getDroit());
        enseignants = new DAOEnseignant(con).findAll();
        etudiants = new DAOEtudiant(con).findAll();
        salles = new DAOSalle(con).findAll();
        groupes = new DAOGroupe(con).findAll();  
    }
    

    public ArrayList<Enseignant> getEnseignants() {
        return enseignants;
    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }

    public ArrayList<Salle> getSalles() {
        return salles;
    }

    public ArrayList<Groupe> getGroupes() {
        return groupes;
    }
    
    /**
     * retourne la liste de seance en fonction de l'objet donné
     * @param obj
     * @return
     */
    public ArrayList<Seance> seanceFromObject(Object obj){
        if(obj instanceof Groupe)
            return ((Groupe) obj).getSeances();
        else if(obj instanceof Etudiant)
            return ((Etudiant)obj).getGroupe().getSeances();
        else if (obj instanceof Enseignant)
            return ((Enseignant) obj).getSeances();
        else if (obj instanceof Salle)
            return ((Salle) obj).getSeances();
        return null;
    }

    
}
