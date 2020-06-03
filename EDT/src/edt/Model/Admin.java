/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class Admin extends Referant{
    
    public Admin(Utilisateur utilisateur, Connection con) {
        super(utilisateur, con);
    }
    
    public boolean checkTimeGroupe(ArrayList<Groupe> grs, Time heure_debut, Time heure_fin, Date date){
        for(Groupe g : grs){
            for(Seance s : g.getSeances()){
                if(s.getDate() == date && s.getHeure_debut() == heure_debut && s.getHeure_fin() == heure_fin){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkTimeEnseignant(ArrayList<Enseignant> ens, Time heure_debut, Time heure_fin, Date date){
        for(Enseignant e : ens){
            for(Seance s : e.getSeances()){
                if(s.getDate() == date && s.getHeure_debut() == heure_debut && s.getHeure_fin() == heure_fin){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkTimeSalle(ArrayList<Salle> sls, Time heure_debut, Time heure_fin, Date date){
        for(Salle e : sls){
            for(Seance s : e.getSeances()){
                if(s.getDate() == date && s.getHeure_debut() == heure_debut && s.getHeure_fin() == heure_fin){
                    return false;
                }
            }
        }
        return true;
    }
    
    //verifie que pour les groupe et les salles données la capacité d'accueil soit suffisante
    public boolean checkSalleCapacite(ArrayList<Salle> sa, ArrayList<Groupe> gr){
        int nombreTotalEtu=0;
        for(Groupe g :gr){
            nombreTotalEtu += nombreEtudiantParGroupe(g);
        }
        int capaTotaleSalle =0;
        for (Salle s : sa)
            capaTotaleSalle += s.getCapacite();
        if(capaTotaleSalle>= nombreTotalEtu)
            return true;
        return false;
            
    }
    
    //renvoie le nombre d'etudiant pour un groupe donné
    public int nombreEtudiantParGroupe(Groupe groupe){
        int nombreEtu=0;
        for(Etudiant etu : etudiants){
            if(etu.getGroupe().getId() == groupe.getId())
                nombreEtu++;
        }
        return nombreEtu;
    }
}
