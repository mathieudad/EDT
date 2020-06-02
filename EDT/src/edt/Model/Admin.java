/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edt.Model;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mathi
 */
public class Admin extends Referant{
    
    public Admin(Utilisateur utilisateur, Connection con) {
        super(utilisateur, con);
    }
    
    
}
