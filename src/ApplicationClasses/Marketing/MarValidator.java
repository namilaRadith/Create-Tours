/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Marketing;

import com.mysql.jdbc.StringUtils;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Basuru
 */
public class MarValidator {
    
    // Items Attributes 
    
    private String type;
    private String description;
    private String cost;
    private String qty;
    
    //Campaign Attributes
    
    private String name;
    private Date date;
    private String venue;
    
    // Promotions & Deals Attributes
    
    private Date toDate;
    private String picture;
    private String discount;
    
    
    //Regular Expressions
    
    private static String costRegex = "[0-9].*+";
    private static String qtyRegex = "[0-9]+";
    
    
    
    
    
    //Marketing Items Validator
    
    public MarValidator(String type, String description, String cost, String qty)
    {
        this.type = type;
        this.description = description;
        this.cost = cost;
        this.qty = qty;
    }
    
    //Marleting Campaign Validtor
    
    public MarValidator(String name,String description, String venue, Date date, String cost)
    {
        this.name = name;
        this.description = description;
        this.venue = venue;
        this.date = date;
        this.cost = cost;
                
    }
    
    //Promotions & Deals Validator
    
    public MarValidator(String name, String desctription, String discount, Date FromDate, Date ToDate, String picture)
    {
        this.name = name;
        this.description = desctription;
        this.discount = discount;
        this.date = FromDate;
        this.toDate = ToDate;
        this.picture = picture;

    }
    
    public boolean MItemsValidate()
    {
        if(type.equals("Please Select"))
        {
            JOptionPane.showMessageDialog(null, "Please select a type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (StringUtils.isEmptyOrWhitespaceOnly(description))
        {
            JOptionPane.showMessageDialog(null, "Please Fill the Description text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(cost))
        {
            JOptionPane.showMessageDialog(null, "Please fill the cost text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!cost.matches(costRegex)) 
        {
            JOptionPane.showMessageDialog(null, "Only numbers can be inserted for cost text box", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(qty))
        {
            JOptionPane.showMessageDialog(null, "Please fill the quantity text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!qty.matches(qtyRegex))
        {
            JOptionPane.showMessageDialog(null, "Only numbers can be inserted for quantity text box", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
        
    }   
    
    public boolean MCampValidate()
    {
        
        if(StringUtils.isEmptyOrWhitespaceOnly(name))
        {
           JOptionPane.showMessageDialog(null, "Name field cannot be empty", "Field Empty",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(description))
        {
           JOptionPane.showMessageDialog(null, "Description field cannot be empty", "Field Empty",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(venue))
        {
           JOptionPane.showMessageDialog(null, "Venue field cannot be empty", "Field Empty",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(date == null)
        {
           JOptionPane.showMessageDialog(null, "Date field cannot be empty and \nshould be in date format", "Field Empty or Input mismatch",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(MarketingCampaign.FinalArray.isEmpty())
        {
           JOptionPane.showMessageDialog(null, "You must select atleast one item", "Item set is empty",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(cost))
        {
           JOptionPane.showMessageDialog(null, "Cost field cannot be empty", "Field Empty",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(!cost.matches(costRegex))
        {
           JOptionPane.showMessageDialog(null, "Date format is wrong", "Field mismatch",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else
        {
            return true;
        }
        
    }
    
    public boolean PDValidate()
    {
        if (StringUtils.isEmptyOrWhitespaceOnly(name))
        {
            JOptionPane.showMessageDialog(null, "Please Fill the name text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(description))
        {
            JOptionPane.showMessageDialog(null, "Please fill the Description text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(discount))
        {
            JOptionPane.showMessageDialog(null, "Please fill the Description text box", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!discount.matches(costRegex)) 
        {
            JOptionPane.showMessageDialog(null, "Only numbers can be inserted for discount text box", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(date == null)
        {
           JOptionPane.showMessageDialog(null, "From Date field cannot be empty and \nshould be in date format", "Field Empty or Input mismatch",JOptionPane.WARNING_MESSAGE);
           return false;
        }
        else if(toDate == null)
        {
           JOptionPane.showMessageDialog(null, "From Date field cannot be empty and \nshould be in date format", "Field Empty or Input mismatch",JOptionPane.WARNING_MESSAGE);
           return false; 
        }
        else if(StringUtils.isEmptyOrWhitespaceOnly(picture))
        {
            JOptionPane.showMessageDialog(null, "Please upload a picture", "Field is empty",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public boolean SearchValidator()
    {
        if(StringUtils.isEmptyOrWhitespaceOnly(name))
        {
            JOptionPane.showMessageDialog(null, "Please enter a Promotion name to Search ", "Field not selected",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else 
        {
            return true;
        }
    }
    
}
