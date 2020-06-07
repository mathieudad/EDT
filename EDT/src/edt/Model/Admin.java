/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import edt.Model.DAO.DAOCours;
import edt.Model.DAO.DAOTypeCours;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author mathi
 */
public class Admin extends Referant{
    private ArrayList<Cours> cours;
    private ArrayList<TypeCours> typesCours;
    
    public Admin(Utilisateur utilisateur, Connection con) {
        super(utilisateur, con);
        cours = new DAOCours(con).findAll();
        typesCours = new DAOTypeCours(con).findAll();
    }
    
    /**
     * Cherche le cours correpondant a ce nom
     * @param name
     * @return
     */
    public Cours findCoursFromName(String name){
        for(Cours c : cours){
            if(c.getNom().equals(name))
                return c;
        }
        return null;
    }
    
    /**
     * retourne l'enseignant avec ce nom et prenom
     * @param name
     * @return
     */
    public Enseignant findEnseignantFromName(String name){
        for(Enseignant e : enseignants){
            String ens = e.getNom()+" "+e.getPrenom();
            if(ens.equals(name))
                return e;
        }
        return null;
    }
    
    /**
     * Trouve le groupe a ce nom
     * @param name
     * @return
     */
    public Groupe findGroupeFromName(String name){
        for(Groupe g : groupes){
            String gr = g.getNom() +" "+ g.getPromo().getNom();
            if(gr.equals(name))
                return g;
        }
        return null;
    }
    
    /**
     * trouve le type de cours a ce nom
     * @param name
     * @return
     */
    public TypeCours findTypeCoursFromName(String name) {
        for(TypeCours tc : typesCours){
            if(tc.getNom().equals(name))
                return tc;
        }
        return null;
    }
     
    /**
     * Selectionne la salle a ce nom
     * @param name
     * @return
     */
    public Salle findSalleFromName(String name) {
       for(Salle s : salles){
            String sa = s.getNom() +" "+ s.getSite().getNom();
            if(sa.equals(name))
                return s;
        }
        return null;
        }
    
    /**
     * Selectionne les seances dispo en fonction des profs eleves et de la date
     * @param dateString
     * @param enseignants
     * @param groupes
     * @return
     */
    public ArrayList<String> selectSeances(String dateString, ArrayList<Enseignant> enseignants, ArrayList<Groupe> groupes) {
        ArrayList<String> times = new ArrayList();
        ArrayList<String> mauvaisTemps = new ArrayList();
        for(Enseignant e : enseignants){
            for(Seance s : e.getSeances(dateString)){
                
                for(Groupe g : groupes){
                    for(Seance sg : g.getSeances(dateString)){
                        
                        if(s.getHeure_debut().toString().equals("08:30:00") || sg.getHeure_debut().toString().equals("08:30:00"))
                            mauvaisTemps.add("08:30:00");
                        if(s.getHeure_debut().toString().equals("10:00:00") || sg.getHeure_debut().toString().equals("10:00:00"))
                            mauvaisTemps.add("10:00:00");
                        if(s.getHeure_debut().toString().equals("11:30:00") || sg.getHeure_debut().toString().equals("11:30:00"))
                            mauvaisTemps.add("11:30:00");
                        if(s.getHeure_debut().toString().equals("13:00:00") || sg.getHeure_debut().toString().equals("13:00:00"))
                            mauvaisTemps.add("13:00:00");
                        if(s.getHeure_debut().toString().equals("14:30:00") || sg.getHeure_debut().toString().equals("14:30:00"))
                            mauvaisTemps.add("14:30:00");
                        if(s.getHeure_debut().toString().equals("16:00:00") || sg.getHeure_debut().toString().equals("16:00:00"))
                            mauvaisTemps.add("16:00:00");
                        if(s.getHeure_debut().toString().equals("17:30:00") || sg.getHeure_debut().toString().equals("17:30:00"))
                            mauvaisTemps.add("17:30:00"); 
                    }
                }
            }
        }
        times.add("08:30:00");
        times.add("10:00:00");
        times.add("11:30:00");
        times.add("13:00:00");
        times.add("14:30:00");
        times.add("16:00:00");
        times.add("17:30:00");
        for(String s : mauvaisTemps){
            Iterator<String> timesI = times.iterator();
            while(timesI.hasNext()){
                String t = timesI.next();
                if(t.equals(s))
                    timesI.remove();
                }
            }  
        return times;
    }
    
    /**
     * Selectionne les salles dispo en fonction de la date et de l'heure de debut
     * @param date
     * @param heureDebut
     * @return
     */
    public ArrayList<Salle> salleDispo(String date, Time heureDebut){
        ArrayList<Salle> sallesDispo = new ArrayList();
        ArrayList<Salle> mauvaisesSalles = new ArrayList();
        for(Salle s : salles){
            sallesDispo.add(s);
        }
        
        for(Salle s : salles){
            for(Seance se : s.getSeances(date)){
                    if(se.getHeure_debut().equals(heureDebut))
                        mauvaisesSalles.add(s);
            }
       }
        
    Iterator<Salle> salleI = sallesDispo.iterator();
    for(Salle s : mauvaisesSalles){
         while(salleI.hasNext()){
                Salle t = salleI.next();
                if(t.equals(s))
                    salleI.remove();
                }
    }
    return sallesDispo;
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
        System.out.println(capaTotaleSalle +" ");
        System.out.println(nombreTotalEtu +" ");
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

    public ArrayList<Cours> getCours() {
        return cours;
    }

    public ArrayList<TypeCours> getTypesCours() {
        return typesCours;
    }

    
  /*
    ArrayList<Salle> sallesDispo = new ArrayList();
        ArrayList<Salle> mauvaisesSalles = new ArrayList();
        for(Salle s : salles){
            sallesDispo.add(s);
        }
        
        for(Salle s : salles){
            for(Seance se : s.getSeances(date)){
                    if(se.getHeure_debut().equals(heureDebut))
                        mauvaisesSalles.add(s);
            }
       }
        
    Iterator<Salle> salleI = sallesDispo.iterator();
    for(Salle s : mauvaisesSalles){
         while(salleI.hasNext()){
                Salle t = salleI.next();
                if(t.equals(s))
                    salleI.remove();
                }
    }
    return sallesDispo;
    }
    */

    
    
 
}
