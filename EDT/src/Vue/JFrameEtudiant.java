/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

/**
 *
 * @author mathi
 */
public class JFrameEtudiant extends JFrame{
    private JPanel panel;
    private JPanel panelButton;
    private JButton semaineSuivante;
    private JButton semainePrecedante;
    private JTextArea numeroWeek;
    
    public JFrameEtudiant(){
         panel = new JPanel();
         panelButton = new JPanel();
         semaineSuivante = new JButton("semaineSuivante");
         semainePrecedante = new JButton("semainePrecedante");
         numeroWeek = new JTextArea();
         panel.setLayout(new GridLayout(8,6));
         panelButton.add(semainePrecedante);
         panelButton.add(semaineSuivante);
         panelButton.add(numeroWeek);
         panel.setFocusable(true);
         panelButton.setFocusable(true);
         build();
    }
    
    
    private void build(){
        this.setTitle("EDT Etudiant");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
       panel.setPreferredSize(new Dimension(800,800));       
        contenu.add(panel);
        contenu.add(panelButton);
        this.add(contenu);
        this.setVisible(true);
    }
    

    public JButton getSemaineSuivante() {
        return semaineSuivante;
    }

    public JButton getSemainePrecedante() {
        return semainePrecedante;
    }

    public JTextArea getNumeroWeek() {
        return numeroWeek;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JPanel getPanelButton() {
        return panelButton;
    }
    
    
    
}
