/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

/**
 *
 * @author mathi
 */
public class Frame extends JFrame{
    JFrameEDT edt;

    public Frame(JFrameEDT edt) {
        this.edt = edt;
        this.add(edt);
        build();
    }
    
    private void build(){
        this.setTitle("EDT");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    
    
    
}
