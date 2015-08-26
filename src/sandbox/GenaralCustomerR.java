/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import ApplicationClasses.DbConnect;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Namila Radith
 */
public class GenaralCustomerR extends ReservationCustomerT{
    private String firstName;
    private String lastName;
    private String NIC;
    
    public GenaralCustomerR(){ }
    
    public GenaralCustomerR(String NIC){
            
        
       con = DbConnect.connect(); 
       sql = "SELECT* FROM customer C , genaralcustomer GC WHERE C.customer_ID = GC.customer_ID AND GC.NIC = '"+ NIC +"'";
        try {
            pst = con.prepareStatement(sql);
            rst = pst.executeQuery();
                
               while(rst.next()){ 
                super.customerID = rst.getString("customer_ID");
                super.customerEmail = rst.getString("email");
                super.customerAddress = rst.getString("address");
                super.customerType = rst.getString("type");
//                super.customerPhone[0] = rst.getInt("phone1");
//                super.customerPhone[1] = rst.getInt("phone2");
                this.firstName = rst.getString("fname");
                this.lastName = rst.getString("lname");
                this.NIC = rst.getString("NIC");
               }  
            
//            System.out.println(this.firstName);
//            System.out.println(this.lastName);
//            System.out.println(this.NIC);
        } catch (SQLException ex) {
            Logger.getLogger(GenaralCustomerR.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
        
    }
    
    public ArrayList<String> print(){
        ArrayList<String> temp  = new ArrayList<>();
        temp.add(super.customerID);
        temp.add(super.customerEmail);
        temp.add(super.customerAddress);
        temp.add(super.customerType);
        return temp;
        
    }
}
