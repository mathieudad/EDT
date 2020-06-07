/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import edt.Model.Admin;
import edt.Model.Cours;
import edt.Model.Enseignant;
import edt.Model.Groupe;
import edt.Model.Salle;
import edt.Model.Seance;
import edt.Model.TypeCours;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.control.DatePicker;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mathi
 */
public class JFrameAdmin extends JFrame{
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu accueil;
    private JMenu consulter;
    private JMenu ajouter;
    private JMenu modifier;
    private JButton coursButton = new JButton("Valider");
    private JButton enseignantButton = new JButton("Valider");
    private JButton enseignantButton2 = new JButton("Valider");
    private JButton groupeButton = new JButton("Valider");
    private JButton groupeButton2 = new JButton("Valider");
    private JButton groupeButton3 = new JButton("Valider");
    private JButton skipEnseignantButton = new JButton("Skip");
    private JButton skipGroupeButton = new JButton("Skip");
    private JButton skipGroupeButton2 = new JButton("Skip");
    private JComboBox boxCours;
    private JComboBox boxTypeCours;
    private JComboBox boxEnseignant1;
    private JComboBox boxEnseignant2;
    private JComboBox boxGroupe1;
    private JComboBox boxGroupe2;
    private JComboBox boxGroupe3;
    private JButton dateButton = new JButton("Valider");
    private JTextField jour = new JTextField(3);
    private JTextField mois = new JTextField(3);
    private JTextField annee = new JTextField(5);  
    private JComboBox boxTime;
    private JButton timeButton = new JButton("Valider");
    private JComboBox boxSalle1;
    private JButton salleButton1 = new JButton("Valider");
    private JComboBox boxSalle2;
    private JButton salleButton2 = new JButton("Valider");
    
    public JFrameAdmin(Admin admin){
        panel = new JPanel();
        menuBar = new JMenuBar();
        accueil = new JMenu("Accueil");
        consulter = new JMenu("Consulter un emploi du temps");
        ajouter = new JMenu("Ajouter une seance");
        modifier = new JMenu("Modifier une seance");
        menuBar.add(accueil);
        menuBar.add(consulter);
        menuBar.add(ajouter);
        menuBar.add(modifier);
        panel.add(new JLabel("Bienvenu vous etes adminitrateur"));
        this.setJMenuBar(menuBar);
        build();
    }
    
    private void build(){
        this.setTitle("Accueil ");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * Ajout d'un cours
     * @param cours
     */
    public void etape1Ajout(ArrayList<Cours> cours, ArrayList<TypeCours> typeCours){
        panel.removeAll();
        repaint();
        Vector<String> strings = affichageBoxCours(cours);
        Vector<String> str2 = affichageBoxTypeCours(typeCours);
        boxCours = new JComboBox(strings); 
        boxTypeCours = new JComboBox(str2);
        panel.add(boxCours);
        panel.add(boxTypeCours);
        panel.add(coursButton);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * affichage des cours possibles
     * @param cours
     * @return
     */
    public Vector<String> affichageBoxCours(ArrayList<Cours> cours){
        Vector<String> strings = new Vector();
        int i =0;
        for(Cours c : cours){
            strings.add(c.getNom());
        }    
       return strings;
    }
    
    /**
     * renvoi au format pour afficher les types de cours
     * @param typeCours
     * @return
     */
    public Vector<String> affichageBoxTypeCours(ArrayList<TypeCours> typeCours){
        Vector<String> strings = new Vector();
        int i =0;
        for(TypeCours c : typeCours){
            strings.add(c.getNom());
        }    
       return strings;
    }
    
    
    /**
     * ajout d'un enseignant au cours
     * @param enseignants
     */
    public void etape2Ajout(ArrayList<Enseignant> enseignants){
        panel.removeAll();
        repaint();
        Vector<String> str = affichageBoxEnseignant(enseignants);
        boxEnseignant1 = new JComboBox(str);
        panel.add(boxEnseignant1);
        panel.add(enseignantButton);
        this.add(panel);
        this.setVisible(true);
        
    }
    
    /**
     * ajout d'un deuxieme enseignant au cours (facultatif)
     * @param enseignants
     */
    public void etape2BisAjout(ArrayList<Enseignant> enseignants){
        panel.removeAll();
        repaint();
        Vector<String> str = affichageBoxEnseignant(enseignants);
        boxEnseignant2 = new JComboBox(str);
        panel.add(boxEnseignant2);
        panel.add(enseignantButton2);
        panel.add(skipEnseignantButton);
        this.add(panel);
        this.setVisible(true);
    }
    
    public Vector<String> affichageBoxEnseignant(ArrayList<Enseignant> enseignants){
        Vector<String> strings = new Vector();
        int i =0;
        for(Enseignant e : enseignants){
            strings.add(e.getNom()+" "+ e.getPrenom());
        }    
       return strings;
    }
    
    /**
     * Affiche les groupes a selectionner
     * @param groupes
     */
    public void etape3Ajout(ArrayList<Groupe> groupes){
        panel.removeAll();
        repaint();
        Vector<String> str = affichageBoxGroupe(groupes);
        boxGroupe1 = new JComboBox(str);
        panel.add(boxGroupe1);
        panel.add(groupeButton);
        this.add(panel);
        this.setVisible(true);
        
    }
    
    /**
     * posssibilité dans selectionner un deuxieme
     * @param groupes
     */
    public void etape3BisAjout(ArrayList<Groupe> groupes){
        panel.removeAll();
        repaint();
        Vector<String> str = affichageBoxGroupe(groupes);
        boxGroupe2 = new JComboBox(str);
        panel.add(boxGroupe2);
        panel.add(groupeButton2);
        panel.add(skipGroupeButton2);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * Possibilité de selectionner un troisieme groupe
     * @param groupes
     */
    public void etape3BisBisAjout(ArrayList<Groupe> groupes) {
        panel.removeAll();
        repaint();
        Vector<String> str = affichageBoxGroupe(groupes);
        boxGroupe3 = new JComboBox(str);
        panel.add(boxGroupe3);
        panel.add(groupeButton3);
        panel.add(skipGroupeButton2);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * Renvoie au format pour afficher les groupes
     * @param groupes
     * @return
     */
    public Vector<String> affichageBoxGroupe(ArrayList<Groupe> groupes){
        Vector<String> strings = new Vector();
        int i =0;
        for(Groupe g : groupes){
            strings.add(g.getNom()+" "+ g.getPromo().getNom());
        }    
       return strings;
    }
    
    /**
     * Affichage des dates pour en selectionner une 
     */
    public void etape4Ajout(){
        panel.removeAll();
        repaint();
        JLabel j = new JLabel("Jour (ex 01): ");
        JLabel m = new JLabel("Mois (ex 01): ");
        JLabel a = new JLabel("Année (ex 2020): ");
        panel.add(j);
        panel.add(jour);
        panel.add(m);
        panel.add(mois);
        panel.add(a);
        panel.add(annee);
        panel.add(dateButton);
        this.add(panel);
        this.setVisible(true);
        
    }

    /**
     * Si la date est mauvaise cette fonction est appelée 
     */
    public void etape4AjoutMauvaiseDate(){
        panel.removeAll();
        repaint();
        panel.add(new JLabel("Date invalide"));
        JLabel j = new JLabel("Jour (ex 01): ");
        JLabel m = new JLabel("Mois (ex 01): ");
        JLabel a = new JLabel("Année (ex 2020): ");
        jour.setText("");
        mois.setText("");
        annee.setText("");
        panel.add(j);
        panel.add(jour);
        panel.add(m);
        panel.add(mois);
        panel.add(a);
        panel.add(annee);
        panel.add(dateButton);
        this.add(panel);
        this.setVisible(true);
        
    }
    
    /**
     * affichage des creneaux disponibles pour la seance a la date d
     * @param times
     */
    public void etape5Ajout(ArrayList<String> times) {
        Vector<String> str = affichageBoxTime(times);
        panel.removeAll();
        repaint();
        boxTime = new JComboBox(str);
        panel.add(new JLabel("Selectionnner l'heure du debut de la seance qui durera 1h30"));
        panel.add(boxTime);
        panel.add(timeButton);
        this.add(panel);
        this.setVisible(true);
    }
    
    /**
     * renvoi au format pour afficher les creneaux horaires
     * @param times
     * @return
     */
    public Vector<String> affichageBoxTime(ArrayList<String> times){
        Vector<String> strings = new Vector();
        int i =0;
        for(String g : times){
            strings.add(g);
        }    
       return strings;
    }
   
    /**
     * affichage des salles pour les ajouter a la seance
     * @param salles
     */
    public void etape6Ajout(ArrayList<Salle> salles){
         Vector<String> str = affichageBoxSalle(salles);
        panel.removeAll();
        repaint();
        boxSalle1 = new JComboBox(str);
        panel.add(boxSalle1);
        panel.add(salleButton1);
        this.add(panel);
        this.setVisible(true);
     }
     
    /**
     * Ajout d'une deuxieme salle si une seule suffit pas
     * @param salles
     */
    public void etape6BisAjout(ArrayList<Salle> salles){
        Vector<String> str = affichageBoxSalle(salles);
        panel.removeAll();
        repaint();
        boxSalle2 = new JComboBox(str);
        panel.add(boxSalle2);
        panel.add(salleButton2);
        this.add(panel);
        this.setVisible(true);
     }
     
    /**
     * renvoie au format pour afficher les salles
     * @param salles
     * @return
     */
    public Vector<String> affichageBoxSalle(ArrayList<Salle> salles){
        Vector<String> strings = new Vector();
        int i =0;
        for(Salle g : salles){
            strings.add(g.getNom()+" "+ g.getSite().getNom());
        }    
       return strings;
     }
     
    /**
     * est appelé quand la seance a ete ajoutée
     */
    public void affichageOK() {
       panel.removeAll();
       repaint();
       panel.add(new JLabel("seance Ajoutée!"));
       this.add(panel);
       this.setVisible(true);
    }
    
    /**
     * Affiche lorsque la capacité est inferieurs aux peronnes pour le gr choisi
     */
    public void problemeCapacite(){
       panel.removeAll();
       repaint();
       panel.add(new JLabel("Ajout impossible essayez encore"));
       this.add(panel);
       this.setVisible(true);
    }
    
    public JMenu getAccueil() {
        return accueil;
    }

    public JMenu getConsulter() {
        return consulter;
    }

    public JMenu getAjouter() {
        return ajouter;
    }

    public JMenu getModifier() {
        return modifier;
    }

    public JButton getCoursButton() {
        return coursButton;
    }

    public JButton getEnseignantButton() {
        return enseignantButton;
    }

    public JComboBox getBoxCours() {
        return boxCours;
    }

    public JPanel getPanel() {
        return panel;
    }


    public JButton getEnseignantButton2() {
        return enseignantButton2;
    }

    public JButton getGroupeButton() {
        return groupeButton;
    }

    public JButton getGroupeButton2() {
        return groupeButton2;
    }

    public JComboBox getBoxEnseignant1() {
        return boxEnseignant1;
    }

    public JComboBox getBoxEnseignant2() {
        return boxEnseignant2;
    }

    public JComboBox getBoxGroupe1() {
        return boxGroupe1;
    }

    public JComboBox getBoxGroupe2() {
        return boxGroupe2;
    }

    public JButton getSkipEnseignantButton() {
        return skipEnseignantButton;
    }

    public JButton getSkipGroupeButton() {
        return skipGroupeButton;
    }

    public JButton getGroupeButton3() {
        return groupeButton3;
    }

    public JButton getSkipGroupeButton2() {
        return skipGroupeButton2;
    }

    public JComboBox getBoxGroupe3() {
        return boxGroupe3;
    }

    public JTextField getJour() {
        return jour;
    }

    public JTextField getMois() {
        return mois;
    }

    public JTextField getAnnee() {
        return annee;
    }

    public JButton getDateButton() {
        return dateButton;
    }

    public JComboBox getBoxTime() {
        return boxTime;
    }

    public JButton getTimeButton() {
        return timeButton;
    }

    public JComboBox getBoxTypeCours() {
        return boxTypeCours;
    }

    public JComboBox getBoxSalle1() {
        return boxSalle1;
    }

    public JButton getSalleButton1() {
        return salleButton1;
    }

    public JComboBox getBoxSalle2() {
        return boxSalle2;
    }

    public JButton getSalleButton2() {
        return salleButton2;
    }
}
