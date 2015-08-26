/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Customer;

import ApplicationClasses.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
 public class GeneralCustomer extends Customer
{
     
     
     PreparedStatement pst=null;
     Connection con=DbConnect.connect();
     

     String Fname;
     String Sname;
     String NIC;
     String AddressG;
     int Phone1;
     int Phone2;
     
     public GeneralCustomer(String email,String address,String type, String fname,String sname,String nIC,int phone1,int phone2)
             {
                 super(email,address,type);
                 this.Sname=sname;
                 this.Fname=fname;
                 this.NIC=nIC;
                 this.Phone1=phone1;
                 this.Phone2=phone2;
                 
             }
      public GeneralCustomer(String email,String address,String type, String fname,String sname,String nIC,int phone1)
             {
                 super(email,address,type);
                 this.Sname=sname;
                 this.Fname=fname;
                 this.NIC=nIC;
                 this.Phone1=phone1;
                 this.Phone2=0;
                 
             }
      
       public GeneralCustomer(String address,String type, String fname,String sname,String nIC,int phone1,int phone2)
             {
                 super(address,type);
                 this.Sname=sname;
                 this.Fname=fname;
                 this.NIC=nIC;
                 this.Phone1=phone1;
                 this.Phone2=phone2;
                 
             }
           public GeneralCustomer(String address,String type, String fname,String sname,String nIC,int phone1)
             {
                 super(address,type);
                 this.Sname=sname;
                 this.Fname=fname;
                 this.NIC=nIC;
                 this.Phone1=phone1;
                 this.Phone2=0;
                 
             }
      
      public void AddGeneralCustomer()
      {
          String Query1="call AddGeneralCustomer('"+this.Email+"','"+this.Address+"','"+ this.Type+"','"+ this.Fname+"','"+this.Sname+"','"+this.NIC+"','"+this.Phone1+"','"+this.Phone2+"')";
         try {
             pst=con.prepareStatement(Query1);
         } catch (SQLException ex) {
             Logger.getLogger(GeneralCustomer.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             pst.execute();
         } catch (SQLException ex) {
             Logger.getLogger(GeneralCustomer.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
      
      public void EditGeneralCustomer(String GenCusID) throws SQLException
      {
          String Query2="call updateGenCustomers('"+GenCusID+"','"+this.Fname+"','"+this.Sname+"','"+this.Email+"','"+this.Phone1+"','"+this.Phone2+"','"+this.Address+"','"+this.NIC+"')";
          pst=con.prepareStatement(Query2);
          pst.execute();
      }
      
      public void DeleteGeneralCustomer(String GenCusID) throws SQLException
      {
          String Query3="call DeleteGeneralCustomer('"+GenCusID+"')";
          pst=con.prepareStatement(Query3);
          pst.execute();
      }
 }
     
