/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Marketing;

import ApplicationClasses.DbConnect;
import ApplicationClasses.DbConnect;
import Interfaces.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Basuru
 */
public class MarketingCampaign {
    
    private int ID;
    private String name;
    private String description;
    private String date;
    private String venue;
    private double cost;
    public static ArrayList<ArrayList<Integer>> FinalArray = new ArrayList<ArrayList<Integer>>();
    
    Connection con = DbConnect.connect();
    PreparedStatement pst = null;
    
    public MarketingCampaign()
    {
        //Default Constructor
    }
    
    public MarketingCampaign(String name,String description, String date, String venue, double cost)
    {    
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.cost = cost;
    }
    
    public MarketingCampaign(int ID, String name,String description, String date, String venue, double cost)
    {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;
        this.cost = cost;
    }
    
    public void addCampaign()
    {
        try {
            String Sql = "INSERT INTO mcamp (name,description,date,venue,cost) VALUES ('"+this.name+"','"+this.description+"','"+this.date+"','"+this.venue+"','"+this.cost+"')";
            pst = con.prepareStatement(Sql);
            pst.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaign.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean editCampaign(int ID)
            
    {
        try {
            String Sql = "UPDATE mcamp SET name='"+this.name+"', description='"+this.description+"',date='"+this.date+"',venue='"+this.venue+"',cost='"+this.cost+"' WHERE MCamp_ID = '"+ID+"'";
            pst = con.prepareStatement(Sql);
            pst.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaign.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean deleteCampaing(int ID)
    {
        
        
        try {
            String Sql = "DELETE FROM mitems_has_mcamp WHERE MCamp_MCamp_ID='"+ID+"'";
            pst = con.prepareStatement(Sql);
            pst.execute();
            
            
            
            Sql = "DELETE FROM mcamp WHERE MCamp_ID='"+ID+"'";
            pst = con.prepareStatement(Sql);
            pst.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaign.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
                
    }
    
    public boolean addTomitems_has_mcampTable()
    {
        int ItemID=0,Qty=0,rem=0,rem2=0,strtval=0,endval=0;
        strtval=0;
        endval=1;
        
        
        for(ArrayList<Integer> r : FinalArray )
        { 
            rem=r.size()/2;
            rem2=r.size();
            
            while(rem>=0)
            {
               while(strtval<endval && endval<=rem2)
                {
                   try {
                       ItemID=r.get(strtval);
                       Qty=r.get(endval);
                       
                       String Sql = "CALL insertMitemsHasMcamp('"+ItemID+"','"+Qty+"')";
                       pst = con.prepareStatement(Sql);
                       pst.execute();
                       
                       strtval+=2;
                       endval+=2;
                       
                       
                   } catch (SQLException ex) {
                       Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
               rem--;
            }
        }
        return false;
    }
    
    public boolean Editmitems_has_mcampTable(int ID)
    {
        int ItemID=0,Qty=0,rem=0,rem2=0,strtval=0,endval=0;
        strtval=0;
        endval=1;
        
        for(ArrayList<Integer> r : FinalArray )
        { 
            rem=r.size()/2;
            rem2=r.size();
            
            while(rem>=0)
            {
               while(strtval<endval && endval<=rem2)
                {
                   try {
                       ItemID=r.get(strtval);
                       Qty=r.get(endval);
                       
                       String Sql = "CALL editMitemsHasMcamp('"+ID+"','"+ItemID+"','"+Qty+"')";
                       pst = con.prepareStatement(Sql);
                       pst.execute();
                       
                       strtval+=2;
                       endval+=2;
                       
                       
                   } catch (SQLException ex) {
                       Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
               rem--;
            }
        }
        
        return false;
    }
    
    public void ClearData()
    {
        FinalArray.clear();
    }
    
}
