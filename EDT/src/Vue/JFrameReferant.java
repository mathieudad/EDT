/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import edt.Model.Enseignant;
import edt.Model.Etudiant;
import edt.Model.Groupe;
import edt.Model.Referant;
import edt.Model.Salle;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author mathi
 */
public class JFrameReferant extends JFrame {
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu accueil;
    private JList etudiantList =new JList();
    private JList enseignantList = new JList();
    private JList groupeList = new JList();
    private JList salleList = new JList();
    
    public JFrameReferant(Referant referant){
        panel = new JPanel();
        menuBar = new JMenuBar();
        accueil = new JMenu("Accueil");
        
        menuBar.add(accueil);
        this.setJMenuBar(menuBar);
        affichageListEns(referant.getEnseignants());
        affichageListEtu(referant.getEtudiants());
        affichageListGroupe(referant.getGroupes());
        affichageListSalle(referant.getSalles());
        etudiantList.setFont(etudiantList.getFont().deriveFont(22.0f));
        enseignantList.setFont(etudiantList.getFont().deriveFont(22.0f));
        groupeList.setFont(etudiantList.getFont().deriveFont(22.0f));
        salleList.setFont(etudiantList.getFont().deriveFont(22.0f));
        panel.add(enseignantList);
        panel.add(etudiantList);
        panel.add(groupeList);
        panel.add(salleList);
        build();
        
    }
    
    
    private void build(){
        this.setTitle("Accueil Visualisation");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
    
    public void affichageListEns(ArrayList<Enseignant> enseignants){
        DefaultListModel dlm = new DefaultListModel();
        for(Enseignant e : enseignants){
            dlm.addElement(e.infosPourList());
        }    
        enseignantList.setModel(dlm);
    }
    
    public void affichageListEtu(ArrayList<Etudiant> etudiants){
        DefaultListModel dlm = new DefaultListModel();
        for(Etudiant e : etudiants){
            dlm.addElement(e.infosPourList());
        }    
        etudiantList.setModel(dlm);
    }
    
    public void affichageListGroupe(ArrayList<Groupe> groupes){
        DefaultListModel dlm = new DefaultListModel();
        for(Groupe e : groupes){
            dlm.addElement(e.infosPourList());
        }    
        groupeList.setModel(dlm);
    }
    
    public void affichageListSalle(ArrayList<Salle> salles){
        DefaultListModel dlm = new DefaultListModel();
        for(Salle e : salles){
            dlm.addElement(e.infosPourList());
        }    
        salleList.setModel(dlm);
    }

    public JList getEtudiantList() {
        return etudiantList;
    }

    public JList getEnseignantList() {
        return enseignantList;
    }

    public JList getGroupeList() {
        return groupeList;
    }

    public JList getSalleList() {
        return salleList;
    }

    public JMenu getAccueil() {
        return accueil;
    }
    
    
    public void AjoutMenu(JMenuBar nouveau){
        this.remove(menuBar);
        this.setJMenuBar(nouveau);
    }
    
    
    
}
