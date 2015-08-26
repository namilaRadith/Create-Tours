/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Vehicles;



import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Malruchi
 */
public abstract class vehicle {
    
      
        
    
        String vehicle_ID;
        String vehicleType;
        String vehicleModel;
        String licenceNo;
        String transmitType;
        String fuelType;
        String colour;
        String noOfSeats;
        String vstatus;
        String type;
        
        
        public vehicle(String vehicleID,String vType,String vModel,String lNo,String tType,String fType,String vColour,String vNoOfSeats,String vAStatus,String type)
        {
            this.vehicle_ID = vehicleID;
            this.vehicleType = vType;
            this.vehicleModel = vModel;
            this.licenceNo = lNo;
            this.transmitType = tType;
            this.fuelType = fType;
            this.colour = vColour;
            this.noOfSeats = vNoOfSeats;
            this.vstatus = vAStatus;
            this.type = type;
            
        }
        
         //inserting to vehicle table
       public abstract void insertVehicles();
        
        //updating vehicles
       public abstract void UpdateVehicles();
       
       //removing vehicles
       public abstract void RemoveVehicles();
       
        
    
}
