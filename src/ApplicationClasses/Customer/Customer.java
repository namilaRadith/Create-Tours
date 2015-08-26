/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Customer {
    
    String Email;
    String Address;
    String Type;
    
    public Customer(String email,String address,String type)
    {
        this.Email=email;
        this.Address=address;
        this.Type=type;
    }
    
        public Customer(String address,String type)
    {
        this.Email="Not given";
        this.Address=address;
        this.Type=type;
    }
    
}


     
     

