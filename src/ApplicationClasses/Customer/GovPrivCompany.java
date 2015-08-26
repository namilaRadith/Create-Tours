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

/**
 *
 * @author hp
 */
public class GovPrivCompany extends Customer {
    
    PreparedStatement pst=null;
     Connection con=DbConnect.connect();
    
    String CName;
    String Ctype;
    String CCPerson;
    int PhoneCmpany1;
     int PhoneCmpany2;
    
    public GovPrivCompany(String email,String address,String type,String cname,String ccperson,String ctype,int phonec1,int phonec2)
    {
        super(email,address,type);
        this.CName=cname;
        this.CCPerson=ccperson;
        this.Ctype=ctype;
        this.PhoneCmpany1=phonec1;
        this.PhoneCmpany2=phonec2;
    }
    public GovPrivCompany(String email,String address,String type,String cname,String ccperson,String ctype,int phonec1)
    {
        super(email,address,type);
        this.CName=cname;
        this.CCPerson=ccperson;
        this.Ctype=ctype;
        this.PhoneCmpany1=phonec1;
        this.PhoneCmpany2=0;
    }
        public GovPrivCompany(String address,String type,String cname,String ccperson,String ctype,int phonec1,int phonec2)
    {
        super(address,type);
        this.CName=cname;
        this.CCPerson=ccperson;
        this.Ctype=ctype;
        this.PhoneCmpany1=phonec1;
        this.PhoneCmpany2=phonec2;
    }
        public GovPrivCompany(String address,String type,String cname,String ccperson,String ctype,int phonec1)
    {
        super(address,type);
        this.CName=cname;
        this.CCPerson=ccperson;
        this.Ctype=ctype;
        this.PhoneCmpany1=phonec1;
        this.PhoneCmpany2=0;
    }
    
    public void AddGovPrivCompany() throws SQLException
    {
       String Query2="call AddGovOrPvtCompany('"+this.Email+"','"+this.Address+"','"+this.Type+"','"+this.CName+"','"+this.CCPerson+"','"+this.Ctype+"','"+this.PhoneCmpany1+"','"+this.PhoneCmpany2+"')" ;
       pst=con.prepareStatement(Query2);
       pst.execute();
    }
    
    public void EditPrivGovCompany(String CcusID) throws SQLException
    {
        String Query5="call updatePrivGovCompany('"+CcusID+"','"+this.CName+"','"+this.CCPerson+"','"+this.Email+"','"+this.PhoneCmpany1+"','"+this.PhoneCmpany2+"','"+this.Address+"','"+this.Ctype+"')";
        pst=con.prepareStatement(Query5);
        pst.execute();
    }
    
    public void DeletePrivGovCompany(String CcusID) throws SQLException
    {
         String Query6="call DeleteCompanyCustomer('"+CcusID+"')";
         pst=con.prepareStatement(Query6);
         pst.execute();
    }
    
}
