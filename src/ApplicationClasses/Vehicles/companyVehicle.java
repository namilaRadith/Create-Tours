/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Vehicles;

import ApplicationClasses.DbConnect;
import ApplicationClasses.Reservation.Reservation;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Malruchi
 */
public class companyVehicle extends vehicle {

    public companyVehicle(String vehicleID, String vType, String vModel, String lNo, String tType, String fType, String vColour, String vNoOfSeats, String vAStatus, String type) {
        super(vehicleID, vType, vModel, lNo, tType, fType, vColour, vNoOfSeats, vAStatus, type);
    }

    @Override
    public void insertVehicles() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = (Connection) DbConnect.connect();

            
            

            

            String companyVehicle_Query = "CALL AddCompanyVehicle('" + this.vehicleType + "','" + this.vehicleModel + "','" + this.licenceNo + "','" + this.transmitType + "','" + this.fuelType + "','" + this.colour + "','" + this.noOfSeats + "','" + this.vstatus + "','" + this.type + "')";
            pst = conn.prepareStatement(companyVehicle_Query);
            pst.execute();
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problem occred "); 
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        catch (MySQLIntegrityConstraintViolationException ex)
//                    {
//                        //throw new MySQLIntegrityConstraintViolationException(ex);
//                        
//                            
//                            Logger.getLogger(vehicle.class.getName()).log(Level.SEVERE, null, ex);
//                       
//                        
//                            
//                    
//                    }
        
    }

    @Override
    public void UpdateVehicles() {
        Connection conn = null;
        PreparedStatement pst = null;

        try 
        {
             conn = (Connection) DbConnect.connect();
            String vehicle_QueryUpd = "UPDATE vehicle SET vehicleType='" + this.vehicleType + "',vehicalModel='" + this.vehicleModel + "',licenceNo='" + this.licenceNo + "',transmisionType='" + this.transmitType + "',fuelType='" + this.fuelType + "',color='" + this.colour + "',noOfSeats='" + this.noOfSeats + "',status='" + this.vstatus + "',type='" + this.type + "' WHERE vehicle_ID='" + this.vehicle_ID + "'AND licenceNo='"+this.licenceNo+"'";
            pst = conn.prepareStatement(vehicle_QueryUpd);
            pst.execute();
        } 
        catch (Exception e) 
        {
            if (e instanceof MySQLIntegrityConstraintViolationException) 
                        {
                            JOptionPane.showMessageDialog(null, "This vehicle is already existing  ", "Cannot Update",JOptionPane.WARNING_MESSAGE);
                            Logger.getLogger(vehicle.class.getName()).log(Level.SEVERE, null, e);
                        }
        }

    }

    @Override
    public void RemoveVehicles() {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
             conn = (Connection) DbConnect.connect();
            String vehicle_QueryRemove1 = "UPDATE vehicle SET deleteStatus = 0 WHERE vehicle_ID='" + this.vehicle_ID + "'";
            pst = conn.prepareStatement(vehicle_QueryRemove1);
            pst.execute();
            String vehicle_QueryRemove2 = "UPDATE companyvehicle SET deleteStatus = 0 WHERE vehicle_ID='" + this.vehicle_ID + "'";
            pst = conn.prepareStatement(vehicle_QueryRemove2);
            pst.execute();
        } 
        catch (Exception e) 
        {
            Logger.getLogger(vehicle.class.getName()).log(Level.SEVERE, null, e);
        }

    }

}
