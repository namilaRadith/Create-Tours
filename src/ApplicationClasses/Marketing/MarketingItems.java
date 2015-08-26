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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basuru
 */
public class MarketingItems {
    
    private int ID;
    private String description;
    private String type;
    private double cost;
    private int qtyAtHand;
    
    Connection con = DbConnect.connect();
    PreparedStatement pst = null;
    
    public MarketingItems()
    {
    //Default Constructor
    }
    
    public MarketingItems(String type, String desc, double cost, int qty)
    {
        
        this.description = desc;
        this.type = type;
        this.qtyAtHand = qty;
        this.cost = cost;
    }
    
    public MarketingItems(int ID, String type, String desc, double cost, int qty)
    {
        this.ID = ID;
        this.description = desc;
        this.type = type;
        this.qtyAtHand = qty;
        this.cost = cost;
        
    }
    
    
    
    public void addItems()
    {
        try {
            String sql = "INSERT INTO mitems (type, description, cost, qtyAtHand) VALUES ('"+this.type+"','"+this.description+"','"+this.cost+"','"+this.qtyAtHand+"')";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            

        } catch (SQLException ex) {
            Logger.getLogger(MarketingItems.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean editItems(int ID)
    {
        try {
            String sql = "UPDATE mitems SET type='"+this.type+"', description='"+this.description+"', cost='"+this.cost+"',qtyAtHand='"+this.qtyAtHand+"' WHERE MItems_ID='"+this.ID+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MarketingItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteItem(int ID)
    {
        try {
            
            String sql = "DELETE FROM mitems WHERE MItems_ID='"+ID+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            return true;
        
        } catch (SQLException ex) {
            Logger.getLogger(MarketingItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
