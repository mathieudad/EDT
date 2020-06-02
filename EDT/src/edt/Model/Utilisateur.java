/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

/**
 *
 * @author mathi
 */
public class Utilisateur {
    protected int id;
    protected String email;
    protected String passwd;
    protected String nom;
    protected String prenom;
    protected String droit; 
    
    /**
     *
     * @param id
     * @param email
     * @param passwd
     * @param nom
     * @param prenom
     * @param droit
     */
    public Utilisateur(int id, String email, String passwd, String nom, String prenom, String droit){
        this.id = id;
        this.email = email;
        this.passwd = passwd;
        this.nom = nom;
        this.prenom = prenom;
        this.droit = droit;
    }
    
    /**
     *
     */
    public Utilisateur(){
        id = -1;
    }
    
    public void printInfos(){
        System.out.println("bonjour "+ nom +" " + prenom);
        System.out.println("infos perso :" + email + " " + passwd);
        System.out.println(" vous etes un :" + droit + " #id " + id);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDroit() {
        return droit;
    }
    
    
}
