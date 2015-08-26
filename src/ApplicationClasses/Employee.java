/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses;

/**
 *
 * @author rameshiPC
 */

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Employee {
    
    String jeid_Employee;
    String jnic_Employee;
    String jfname_Employee;
    String jlname_Employee;
    String jadd_Employee;      
    String jphn_Employee;     
    String jacc_Employee;   
    String jdes_Employee;     
    String jpost_Employee;    
            
    public Employee(String EID,String NIC,String Fname,String Lname,String Address,String phoneNo,String Account,String Description,String post)
            
    {
      this.jeid_Employee = EID;
      this.jnic_Employee =NIC;
      this.jfname_Employee =Fname;
      this.jlname_Employee =Lname;
      this.jadd_Employee =Address;
      this.jphn_Employee =phoneNo;
      this.jacc_Employee =Account;
      this.jdes_Employee =Description;
      this.jpost_Employee =post;
      
      
      
    }
    
    public void insertEmployee()
      {
          Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = (Connection) DbConnect.connect();
                     
             String employeeAddQuery = "INSERT INTO employee (NIC,fname,lname,address,phone,bankAccountNo,description,post) VALUES ('"+this.jnic_Employee+"' ,'"+this.jfname_Employee+"' , '"+this.jlname_Employee+"' , '"+this.jadd_Employee+"' , '"+this.jphn_Employee+"' , '"+this.jacc_Employee+"' , '"+this.jdes_Employee+"' , '"+this.jpost_Employee+"')";
            

            

            pst = conn.prepareStatement(employeeAddQuery);
            pst.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    public void updateEmployee()
    {
           Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = (Connection) DbConnect.Connect();
                     
              String employeeUpdateQuery = "UPDATE employee SET NIC = '"+ this.jnic_Employee +"' , fname = '"+ this.jfname_Employee +"' , lname = '"+jlname_Employee +"' , address = '"+ this.jadd_Employee +"' , phone = '"+this.jphn_Employee +"',bankAccountNo = '"+this.jacc_Employee+"' ,description = '"+this.jdes_Employee +"' ,post = '"+jpost_Employee +"' WHERE employee_ID = '"+this.jeid_Employee+"' AND deleteStatusEmp = 1";
          

            

            pst = conn.prepareStatement(employeeUpdateQuery);
            pst.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
     public void removeEmployee()
    {
           Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = (Connection) DbConnect.Connect();
                     
       String employeeUpdateQuery = "UPDATE employee SET deleteStatusEmp = 0 WHERE employee_ID = '"+this.jeid_Employee+"'";
          
         
            

            pst = conn.prepareStatement(employeeUpdateQuery);
            pst.execute();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
    }
      
      }
    
    
    
    
    
    
    
    
    

