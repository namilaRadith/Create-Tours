/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Reservation;

import ApplicationClasses.DbConnect;
import java.sql.CallableStatement;
import sandbox.GenaralCustomerR;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import sandbox.GetCustomerID;

/**
 *
 * @author Namila Radith
 */
public class Reservation {
    private int reservationID;
    private String customerID;
    private ReservationCustomer coustomer = new ReservationCustomer();
    private ArrayList<ReservationVehical> vehical = new ArrayList<>();


    private ReservationInvoice invoice = new ReservationInvoice();
    private Integer packageTypeID;
    private ReservationStandardPackage standedPackage = new ReservationStandardPackage();
    
    private String reservationDate;
    private String reservationDueDate;
    private String description;
    private Double deposit;
    private Double outStanding;
    private String dayOrNight;
    private String kmOrHour;
    

    public Reservation() {
    
    }
    
     public Reservation(int reservationID) {
         Connection conn = DbConnect.connect();
         PreparedStatement pst = null;
         ResultSet rst = null;
         
         String sql = "SELECT* \n" +
"FROM reservation R, reservation_has_package RHP, reservation_has_vehicle RHV,vehicle V \n" +
"WHERE R.Reservation_ID = RHP.Reservation_Reservation_ID AND R.Reservation_ID = RHV.Reservation_Reservation_ID AND RHV.Vehicle_vehicle_ID = V.vehicle_ID AND R.Reservation_ID = '"+ reservationID+"'";
         
        try {
            pst = conn.prepareStatement(sql);
            rst = pst.executeQuery();
            
            while (rst.next()){
                
                    this.reservationID = rst.getInt("Reservation_ID");
                    this.customerID = rst.getString("customer_ID");
                    this.packageTypeID = rst.getInt("Package_package_ID");
                    this.reservationDate = rst.getString("date");
                    this.reservationDueDate = rst.getString("dueDate");
                    this.description = rst.getString("description");
                    this.deposit = rst.getDouble("amount");
                    this.dayOrNight = rst.getString("day_or_overnight");
                    this.kmOrHour = rst.getString("reservationBasis");
                  
                  this.vehical.add(new ReservationVehical(rst.getString("vehicle_ID"),  
                                    rst.getString("licenceNo"),
                                    rst.getString("vehicalModel"),
                                    rst.getString("vehicleType"), 
                                    rst.getString("currentMilage")));
                 
                  
                  
                  
                  this.coustomer.setCustomer("customer_ID");
                  //outStanding;
                  
                
                
            }
            
                   
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.coustomer.setCustomer(this.customerID);
     
     }
     
     
     

    public Reservation(int reservationID, String customerID,ArrayList<ReservationVehical> vehical , Integer packageTypeID, String description, Double deposit) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.packageTypeID = packageTypeID;
        this.description = description;
        this.deposit = deposit;
        this.vehical = vehical;
        
    }

     public void mainSetter(ReservationCustomer customer,ReservationStandardPackage standedPackage,ArrayList<ReservationVehical> vehical, String reservationDate, String reservationDueDate, String description, Double deposit,ReservationInvoice invoice ) {
        this.coustomer = customer;
       // this.packageTypeID = packageTypeID;
        this.vehical = vehical;
        this.reservationDate = reservationDate;
        this.reservationDueDate = reservationDueDate;
        this.description = description;
        this.deposit = deposit;
        this.invoice = invoice;
        this.standedPackage = standedPackage;
        
        
        //fill invoice object
        this.invoice.setDeposit(this.deposit);
        this.invoice.setReservationStartDate(this.reservationDate);
        this. invoice.setReservationEndDate(this.reservationDueDate);
        this. invoice.getTotalCost();
       
    }
    
    
    
        public void mainSetter( String customerID,ArrayList<ReservationVehical> vehical , ReservationStandardPackage standedPackage, String description, Double deposit) {
        
        this.customerID = customerID;
        this.packageTypeID = packageTypeID;
        this.standedPackage = standedPackage;
        this.description = description;
        this.deposit = deposit;
        this.vehical = vehical;
    }


    public Reservation(String ID,String regNo,String model,String source){
    }
    
       
    
    public void insertThisReservationToDB(){
       Connection con = DbConnect.connect();
        try {
            
            
            CallableStatement cs = null;
            cs = con.prepareCall("{call createtours.add_reservation(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, this.description);
            cs.setString(2, this.reservationDate);
            cs.setString(3, this.reservationDueDate);
            cs.setDouble(4, this.deposit);
            cs.setString(5, this.coustomer.getCustomerID());
            cs.setInt(6,this.standedPackage.getStdPackageTypeID());
            cs.setDouble(7, this.invoice.getTotalPrice());
            cs.setString(8, this.standedPackage.getDayOrNight());
            cs.setString(9, this.standedPackage.getKmOrHour());
            cs.registerOutParameter(10,  java.sql.Types.INTEGER);
            cs.registerOutParameter(11,  java.sql.Types.VARCHAR);
            
            cs.execute();
            
            this.reservationID = cs.getInt(10);
            this.invoice.setInvoiceNo(cs.getString(11));
            System.out.println(cs.getString(11));
            cs = null;
            
                for(ReservationVehical VEH : this.vehical){
                    cs = con.prepareCall("{call createtours.add_reserved_vehicle(?,?,?)}");
                    cs.setInt(1, this.reservationID);
                    cs.setString(2, VEH.getVehicleID());
                    cs.setInt(3, VEH.getCurrentMilage());
                    cs.execute();
                }
           invoice.generatePDFinvoice(this.coustomer);
           JOptionPane.showMessageDialog(null, "Reservation sucessfully added to the database"); 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problem occred "); 
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public ArrayList<ReservationVehical> getVehicals() {
        return vehical;
    }
    
    
    //edit exsisting reservation 
    public void updateReservation(){
         Connection con = DbConnect.connect();
        try {
            
            
            CallableStatement cs = null;
            cs = con.prepareCall("{call createtours.update_reservation(?,?,?,?,?,?,?,?,?,?)}");
            cs.setInt(1, this.reservationID);
            cs.setString(2, this.description);
            cs.setString(3, this.reservationDate);
            cs.setString(4, this.reservationDueDate);
            cs.setDouble(5, this.deposit);
            cs.setString(6, this.coustomer.getCustomerID());
            cs.setInt(7, this.standedPackage.getStdPackageTypeID());
            cs.setDouble(8, invoice.getTotalPrice());
            cs.setString(9, this.dayOrNight);
            cs.setString(10,this.kmOrHour);
            cs.execute();
            
        
            cs = null;
            
                for(ReservationVehical VEH : this.vehical){
                    cs = con.prepareCall("{call createtours.update_reserved_vehicle(?,?,?,?,?)}");
                    cs.setInt(1, this.reservationID);
                    cs.setString(2, VEH.getVehicleID());
                    cs.setInt(3, VEH.getCurrentMilage());
                    cs.setString(4, VEH.getOparation());
                    cs.setString(5, VEH.getReasonToDelete());
                    cs.execute();
                }
           //invoice.generatePDFinvoice();
           JOptionPane.showMessageDialog(null, "Reservation sucessfully Updated in the database"); 
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problem occred "); 
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //delete exsisting reservation 
    public void deleteReservation(){
        
    }
    
    //check if vehical already reserved 
    public boolean isVehicalReserved(){
        
        return false;
    }
    //check if vehicle ID is correct
    public boolean isVehicalIDValid(){
        
        return false;
    }
    //check if customer ID is correct
    public boolean isCustomerIDValid(){
        
        return false;
    }
    //check if package ID is correct
    public boolean isPackageIDValid(){
        
        return false;
    }

    public ReservationCustomer getCoustomer() {
        return coustomer;
    }

    public Integer getPackageTypeID() {
        return packageTypeID;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getReservationDueDate() {
        return reservationDueDate;
    }

    public String getDescription() {
        return description;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getReservationID() {
        return reservationID;
    }
    
    
    
    public void print(){
        System.out.println("VEHICALS :");
        for (ReservationVehical vehical1 : vehical) {
           vehical1.print();
        }
        
        System.out.println("customer");
        this.coustomer.print();
        
        System.out.println("Package");
        System.out.println(this.packageTypeID);
        invoice.getTotalCost();
        invoice.print();
        
       // this.standedPackage.print();
        
        
       
    }
    
    //selected vehical table loader 
    public void currentVehicalTableLoad(JTable jt){      
        
        String q = "SELECT * FROM createtours.reseved_vehicle_view  WHERE Reservation_Reservation_ID = '"+this.reservationID+"'";        
        tabelLoad(q, jt);
        
    }
    
    public void selectedPackageTableLoad(JTable jt){
        String q = "SELECT * FROM standardpackage,standardpackagetype WHERE std_package_type_ID_ref = std_package_type_ID AND  std_package_type_ID = '"+this.packageTypeID+"' ";        
        tabelLoad(q, jt);
        
    }
    
    
    
    //my universal table loader :P
    public void tabelLoad(String q,JTable jt) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null; 
        
        con = DbConnect.connect();
        
        try {
            pst = con.prepareStatement(q);
            rst = pst.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rst));
            
        } catch (SQLException ex) {
            Logger.getLogger(GetCustomerID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   public void invoicePrint() throws Exception{
//       invoice.setInvoiceNo(1000);
//       invoice.setDeposit(this.deposit);
//       invoice.setReservationStartDate(this.reservationDate);
//       invoice.setReservationEndDate(this.reservationDueDate);
//       invoice.getTotalCost();
       
       invoice.print();
      // invoice.generatePDFinvoice();
       
       
       
   }
    
}


