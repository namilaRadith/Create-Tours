/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Reservation;

import java.util.ArrayList;

/**
 *
 * @author Namila Radith
 */
public class ReservationPackage {
    
    private ArrayList<Integer> packageIDs;
    private String packageName;
    private String packageType;
    private String packagePrice;    
    private String ac;    

    private String kmLimit;
    private String additionalPerKm;

    public ReservationPackage() {
    
    }

    
    
    public ReservationPackage(ArrayList<Integer> packageIDs , String packageName, String packageTypeID) {
        this.packageType = packageTypeID;
        this.packageIDs = packageIDs;        
        this.packageName = packageName;
        

    }
    
    
    public void  setPackage(ArrayList<Integer> packageIDs, String packageName, String packageType, String ac, String basicAmount, String limit, String additionalCost) {
        this.packageIDs = packageIDs;
        this.packageName = packageName;
        this.packageType = packageType;
        this.packagePrice = basicAmount;             
        this.kmLimit = limit;
        this.additionalPerKm = additionalCost;
        this.ac = ac;
    }

    public String getAc() {
        return ac;
    }
    
    

//    public String getPackageID() {
//        return packageID;
//    }

    public String getPackageName() {
        return packageName;
    }

    public String getPackageType() {
        return packageType;
    }
    
        public String getPackagePrice() {
        return packagePrice;
    }

    public String getKmLimit() {
        return kmLimit;
    }

    public String getAdditionalPerKm() {
        return additionalPerKm;
    }

    public void clear(){
        this.packageIDs = null;
        this.packageName = null;
        this.packageType = null;
        this.packagePrice = null;             
        this.kmLimit = null;
        this.additionalPerKm = null;
    }
    
    public void print(){
        System.out.println("----PACKAGES------");
       // System.out.println(this.packageID);
        System.out.println(this.packageName);
        System.out.println(this.packageType);
        System.out.println(this.packagePrice);
        System.out.println(this.kmLimit);
        System.out.println(this.additionalPerKm);
        System.out.println("----PACKAGES------");
       
    
    }

    
}
