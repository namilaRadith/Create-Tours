/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Packages;


import ApplicationClasses.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aish
 */
public class SpecialPackage extends packages {
    private ArrayList<Integer> spclPackageID = new ArrayList<>();
    
    double basicAmount;
    int noOfpassengers;
    int noOfdays;
    double costPerday;
    int noOfvehicles;
    double costPervehicle;
    double totCost;
    String customerid;
    String description;
  
    
    public SpecialPackage(int packageID,String pName,String pType,double basicAmount,int noOfPassengers,int noOfDays,double costPerDay,int noOfVehicles,double costPerVehicle, String custID,String descript,double totalCost){
    
     super(packageID , pName , pType);   
        
    this.basicAmount=basicAmount;
    this.noOfpassengers=noOfPassengers;
    this.noOfdays= noOfDays;   
    this.costPerday= costPerDay  ;
    this.noOfvehicles=noOfVehicles;
    this.costPervehicle=costPerVehicle;
    this.customerid=custID;
    this.totCost=totalCost;
    this.description=descript;
    }
   
            
    Connection conn = null;   
    PreparedStatement pst = null;
    ResultSet rs = null;
    
public void insertPackage()
    {
    Connection conn =null;
    PreparedStatement pst =null;
                
    conn = DbConnect.Connect();
    String SQL_queryadd;
    
   
     
    try {
    
    {
    
       
            SQL_queryadd = "CALL createtours.AddSpecialPackage('"+this.type+"','"+this.packageName+"','"+this.basicAmount+"', '"+this.description+"', ,'"+this.noOfpassengers+"', '"+this.noOfdays+"','"+this.noOfvehicles+"','"+this.costPerday+"','"+this.totCost+"' )";
            pst = conn.prepareStatement(SQL_queryadd);
            pst.execute();
        
        }
    
    
    } catch (SQLException ex) {
            Logger.getLogger(StandardPackage2.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @Override
    public void insertPackages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePackages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}  



     
