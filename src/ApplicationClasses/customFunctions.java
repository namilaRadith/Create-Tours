/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Namila Radith
 */
public  class customFunctions {
    Connection con = null;
    String sql = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    
    public String getCustomerID(String fname,String lname){
        String customerID = null;
        sql = null; pst = null; rst = null;
        
        con = DbConnect.connect(); 
        sql = "SELECT * FROM createtours.general_or_online_customer_view  WHERE fname = '"+fname+"' AND lname = '"+lname+"'";
        try {
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
            
            while(rst.next()){ 
                customerID = rst.getString("customer_ID");
            }  
            
            if(customerID.isEmpty()){
                JOptionPane.showMessageDialog(null, "Not Found");
            }
                
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Not Found");
            Logger.getLogger(customFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
            

        
        return customerID;
    }
    
    public String getCustomerID(String companyName){
        
        
        return null;
    }
    
    
}
