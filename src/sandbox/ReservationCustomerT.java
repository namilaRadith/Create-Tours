/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

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
public class ReservationCustomerT {
    Connection con = null;
    String sql = null;
    PreparedStatement pst = null;
    ResultSet rst = null;
    
    
    protected String customerID;
    protected String customerEmail;
    protected String customerAddress;
    protected String customerType;
    protected int[] customerPhone = new int[2];
    
    public ReservationCustomerT (){ }
    public ReservationCustomerT(String custID,String custEmail,String custAdd,String custType,int custPhone1,int custPhone2){
        this.customerID = custID;
        this.customerEmail = custEmail;
        this.customerAddress = custAdd;
        this.customerType = custType;
        this.customerPhone[0] = custPhone1;
        this.customerPhone[1] = custPhone2;
                
        
    }
    
    
    
}

class OnlineCustomerR extends GenaralCustomerR{
    private int onlineCustomerID;
    //private String firstName;
   // private String lastName;
    private String userName;
    private String password;
    //private String NIC;
    private String picture;
    
    
    
    
}



class GovAndPrivateCompaniesR extends ReservationCustomerT{
    private String companyName;
    private String contactPersonName;
    private String companyType;
    private double totalAmount; 
}