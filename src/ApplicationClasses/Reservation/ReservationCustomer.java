/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Reservation;

import ApplicationClasses.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Namila Radith
 */
public class ReservationCustomer {
    private String CustomerID;
    private String CustomerName;
    private String CustomerType;

    public ReservationCustomer(String CustomerID, String CustomerName, String CustomerType) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerType = CustomerType;
    }
    
    public void setCustomer(String customerID){
        Connection conn = DbConnect.connect();
        PreparedStatement pst = null;
        ResultSet rst = null;
        
        
        String sql = "SELECT * FROM createtours.customer_whole WHERE customer_ID = '"+ customerID +"'";
        
        
        try {
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            while(rst.next()){
                this.CustomerID = rst.getString("customer_ID");
                this.CustomerName = rst.getString("type");
                this.CustomerType = rst.getString("Customer_Name");
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservationCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        
        
    }
    
    public void setter(String CustomerID, String CustomerName, String CustomerType) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerType = CustomerType;
    }
    public ReservationCustomer() {
    }
    
    public String getCustomerID() {
        return CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getCustomerType() {
        return CustomerType;
    }

   public void clear(){
       this.CustomerID = null;
       this.CustomerName = null;
       this.CustomerType = null;
   }
    
    public boolean isEmpty(){
       
       return this.CustomerID!= null;
   }
    
   public void print(){
       System.out.println("+++Customer++++");
       System.out.println(this.CustomerID);
       System.out.println(this.CustomerName);
       System.out.println(this.CustomerType);
       System.out.println("+++Customer++++");
   } 
    
}
