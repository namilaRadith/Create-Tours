/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Marketing;

import ApplicationClasses.DbConnect;
import ApplicationClasses.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Basuru
 */
public class PromotionsAndDeals {
    private int ID;
    private String name;
    private String description;
    private String fromDate;
    private String toDate;
    private double discount;
    private String picture;
    
    Connection con = DbConnect.connect();
    PreparedStatement pst;
    
    public PromotionsAndDeals()
    {
        //Do nothing, this is the default constructor
    }
    

    public PromotionsAndDeals(String name, String description, String fromDate, String toDate, double discount, String picture) {
        this.name = name;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.discount = discount;
        this.picture = picture;
    }
    
    public PromotionsAndDeals(int ID, String name, String description, String fromDate,String toDate, double discount, String picture)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.discount = discount;
        this.picture = picture;
        
    }
  
    /**
     *@adds : Adds a promotion 
     */
    public void addPromotion()
    {
        try {
            String Sql = "INSERT INTO promotionsanddeals (name,description,discount,fromDate,toDate,picture) VALUES ('"+this.name+"','"+this.description+"','"+this.discount+"','"+this.fromDate+"','"+this.toDate+"','"+this.picture+"')";
            pst = con.prepareStatement(Sql);
            pst.execute();
            
        
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionsAndDeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    
    
       /**
     *@edits : Edits a promotion 
     */
    public boolean editPromotion()
    {
        
        try {
            String sql = "UPDATE promotionsanddeals SET name='"+this.name+"',description='"+this.description+"',discount='"+this.discount+"',fromDate='"+this.fromDate+"',toDate='"+this.toDate+"' ,picture='"+this.picture+"' WHERE PromotionsAndDeals_ID='"+this.ID+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PromotionsAndDeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean deletePromotion(int pID)
    {
        try {
            String sql = "DELETE FROM promotionsanddeals WHERE PromotionsAndDeals_ID='"+pID+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PromotionsAndDeals.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
