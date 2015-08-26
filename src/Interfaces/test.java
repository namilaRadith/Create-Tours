/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import sandbox.ReservationCustomerT;
import ApplicationClasses.DbConnect;
import sandbox.GenaralCustomerR;
import ApplicationClasses.customFunctions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Namila Radith
 */
public class test {
    
        
    public static void main (String [] a){
        
        
//            Connection con = null;
//            con = DbConnect.connect();
//            
//            PreparedStatement pst = null;
//        try {
//            String sql = "INSERT INTO customer (email,address,type) values('n.radith@gmail.com','bandarawela','online')"; 
//            pst = con.prepareStatement(sql);
//            pst.execute();
//        } catch (SQLException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            
        
//      GenaralCustomerR gc = new GenaralCustomerR("940051548V");
//      ArrayList<String> data = new ArrayList<>(gc.print());
//      
//        for (String data1 : data) {
//            System.out.println(data1);
//        }
        
        customFunctions cf = new customFunctions();
        System.out.println(cf.getCustomerID("sandy", "bandara"));
      

    }
    
}
