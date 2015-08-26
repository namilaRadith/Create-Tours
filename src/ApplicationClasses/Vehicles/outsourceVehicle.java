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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Malruchi
 */
public class outsourceVehicle extends vehicle
{
    int owner_ID;
    String oName;
    String oAddress;
    int oPhone;

    public outsourceVehicle(String vehicleID,String vType, String vModel, String lNo, String tType, String fType, String vColour, String vNoOfSeats, String vAStatus, String type,int ownerID,String oName,String oAddress,int oPhone) 
    {
        super(vehicleID,vType, vModel, lNo, tType, fType, vColour, vNoOfSeats, vAStatus, type);
        this.oName = oName;
        this.oAddress = oAddress;
        this.oPhone = oPhone;
        this.owner_ID = ownerID;
    }
    
    //inserting to outsorce vehicle table & owner table
    @Override
    public void insertVehicles()
    {
        Connection conn= null;
        PreparedStatement pst =null;
        
        
        try 
        {
             conn = (Connection) DbConnect.connect();
            String outVehi_Query = "CALL AddOutSourceVehical('"+this.vehicleType+"','"+this.vehicleModel+"','"+this.licenceNo+"','"+this.transmitType+"','"+this.fuelType+"','"+this.colour+"','"+this.noOfSeats+"','"+this.vstatus+"','"+this.type+"','"+this.oName+"','"+this.oAddress+"','"+this.oPhone+"')";
            pst = conn.prepareStatement(outVehi_Query);
            pst.execute();
            
            
        } 
         
        catch (Exception ex)
                    {
                        //throw new MySQLIntegrityConstraintViolationException(ex);
                        if (ex instanceof MySQLIntegrityConstraintViolationException) 
                        {
                            JOptionPane.showMessageDialog(null, "This vehicle is already existing");
                            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
                            
                        }
                            
                    
                    }
        
    }
    
    @Override
    public void UpdateVehicles()
        {
            Connection conn = null;
            PreparedStatement pst =null;
            Statement pst1 =null;
            ResultSet rs = null;
            
            try
            {
                 conn = (Connection) DbConnect.connect();
                String vehicle_QueryUpd = "UPDATE vehicle SET vehicleType='"+this.vehicleType+"',vehicalModel='"+this.vehicleModel+"',licenceNo='"+this.licenceNo+"',transmisionType='"+this.transmitType+"',fuelType='"+this.fuelType+"',color='"+this.colour+"',noOfSeats='"+this.noOfSeats+"',status='"+this.vstatus+"',type='"+this.type+"' WHERE vehicle_ID='"+this.vehicle_ID+"'";
                String ownerID_Query = "SELECT Owner_owner_ID FROM outsourcevehicle WHERE vehicle_ID='"+this.vehicle_ID+"'";
                pst1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                rs = pst1.executeQuery(ownerID_Query);
                while(rs.next())
                {
                String Owner = rs.getString("Owner_owner_ID");
                String owner_QueryUpd = "UPDATE owner SET ownerName='"+this.oName+"',address='"+this.oAddress+"',phoneNo='"+this.oPhone+"' WHERE owner_ID='"+Owner+"'";
                pst = conn.prepareStatement(owner_QueryUpd);
                pst.execute();
                }
                
                pst = conn.prepareStatement(vehicle_QueryUpd);
                pst.execute();
                
                
                
            }
            
            catch (Exception e)
            {
             JOptionPane.showMessageDialog(null, "This vehicle is already existing");
             Logger.getLogger(vehicle.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
    @Override
        public void RemoveVehicles()
        {
            Connection conn = null;
            PreparedStatement pst =null;
            
            try
            {
                conn = (Connection) DbConnect.connect();
                String vehicle_QueryRemove = "CALL DeleteOutSourceVehical('"+this.vehicle_ID+"')";
                pst = conn.prepareStatement(vehicle_QueryRemove);
                pst.execute();
                
            }
            
            catch (Exception e)
            {
             Logger.getLogger(vehicle.class.getName()).log(Level.SEVERE, null, e);
            }
            
        }
    
}
