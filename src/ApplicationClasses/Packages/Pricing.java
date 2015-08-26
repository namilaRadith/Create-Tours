/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Packages;

/**
 *
 * @author Ayesh Dilan
 */
public class Pricing {
    private String vehicleType ;
    private Integer initialPrice;
    private Integer additionalRatePerKm;
    private Integer additionRatePerHour;

    public Pricing(String vehicleType, Integer initialPrice, Integer additionalRatePerKm, Integer additionRatePerHour) {
        this.vehicleType = vehicleType;
        this.initialPrice = initialPrice;
        this.additionalRatePerKm = additionalRatePerKm;
        this.additionRatePerHour = additionRatePerHour;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getInitialPrice() {
        return initialPrice;
    }
    
    public Integer getInitialPrice(String basis) {
        
        return initialPrice;
    }

    public void setInitialPrice(Integer initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Integer getAdditionalRatePerKm() {
        return additionalRatePerKm;
    }

    public void setAdditionalRatePerKm(Integer additionalRatePerKm) {
        this.additionalRatePerKm = additionalRatePerKm;
    }

    public Integer getAdditionRatePerHour() {
        return additionRatePerHour;
    }

    public void setAdditionRatePerHour(Integer additionRatePerHour) {
        this.additionRatePerHour = additionRatePerHour;
    }
    
    public void print()
    {
        System.out.println("---------PricingPrint--------");
        System.out.println(this.vehicleType);
        System.out.println(this.initialPrice);
        System.out.println(this.additionalRatePerKm);
        System.out.println(this.additionRatePerHour);
    }
    
    public void clear(){
        
    }
    
}
