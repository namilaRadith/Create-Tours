/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Reservation;

/**
 *
 * @author Namila Radith
 */
public class ReservationVehical {
    private String vehicleID;
    private String vehicleRegNo;
    private String vehicleType;
    private String vehicleModel;
    private String vehicleSource;
    private Integer currentMilage;
    private String resvEndMilage;
    private String oparation = "";
    private String reasonToDelete = "NONE";

    public ReservationVehical(String vehicleID, String vehicleRegNo, String vehicleModel,String vehicleType, String currentMilage) {
        this.vehicleID = vehicleID;
        this.vehicleRegNo = vehicleRegNo;
        this.vehicleModel = vehicleModel;       
        this.currentMilage = Integer.parseInt(currentMilage);
        this.vehicleType = vehicleType;
    }
    
    public ReservationVehical(String vehicleID, String vehicleRegNo, String vehicleModel,String vehicleType, String currentMilage,String oparation,String reasonToDelete ) {
        this.vehicleID = vehicleID;
        this.vehicleRegNo = vehicleRegNo;
        this.vehicleModel = vehicleModel;       
        this.currentMilage = Integer.parseInt(currentMilage);
        this.vehicleType = vehicleType;
        this.oparation = oparation;
        this.reasonToDelete = reasonToDelete;
    }
    
    public ReservationVehical(String ResvationID){
        
    }

    
    public void print(){
        
        System.out.println("==============Vehicles==============");
        
        System.out.println("ID " + this.vehicleID);
        System.out.println( "Regno " + this.vehicleRegNo);
        System.out.println("Modle " + this.vehicleModel);
        System.out.println("Type " + this.vehicleType);
        System.out.println("CM " + this.currentMilage);
        System.out.println("OPERATION " + this.oparation);
        System.out.println("RESON "+this.reasonToDelete);
   
        
        System.out.println("=============Vehicles================");
    }
    


    public String getVehicleID() {
        return vehicleID;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleSource() {
        return vehicleSource;
    }

    public Integer getCurrentMilage() {
        return currentMilage;
    }

    public String getResvEndMilage() {
        return resvEndMilage;
    }

    public String getOparation() {
        return oparation;
    }

    public String getReasonToDelete() {
        return reasonToDelete;
    }
    
    
    
}
