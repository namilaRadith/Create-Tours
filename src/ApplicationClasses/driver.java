/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses;


import Interfaces.main;
import static Interfaces.main.driver_view_all;
import static Interfaces.main.driver_view_jtable;
import com.mysql.jdbc.Connection;


//import static create.tours.main.jp;
import java.awt.Image;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author ravi
 */
public class driver {
    
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet re=null;
    int nic_count;
    int driv_id;
    public static String driver_id=" ";   
    public String driver_fname,driver_lname,driver_nic,driver_address,driver_phone,driver_drivingLiceneNo,driver_availalibity,driver_picture="";
    JFileChooser jFileChooser1 = new JFileChooser();
    public driver()
    {
        con=(Connection) DbConnect.connect();
      
        
    }
    
    
    // start of add_driver method 
    
    public void add_driver(String fname,String lname,String nic,String address,String phone,String drivingLicenceNo,String picture,int availabilty )
    {
        
        
        this.driver_fname=fname;
        this.driver_lname=lname;
        this.driver_nic=nic;
        this.driver_phone=phone;
        this.driver_drivingLiceneNo=drivingLicenceNo;
        
        this.driver_picture=picture;
        
        
        if(availabilty==0)
            this.driver_availalibity="available";
        else if(availabilty==1)
            this.driver_availalibity="Not available";
            
           
        
        
        
        
        //sending driver details to database
        try {
            
        String add_query="insert into driver(fname,lname,NIC,address,phone,drivingLicenseNo,availability,picture,status)"
                + "values ('"+driver_fname+"','"+driver_lname+"','"+driver_nic+"','"+driver_address+"','"+driver_phone+"','"+driver_drivingLiceneNo+"','"+driver_availalibity+"','"+driver_picture+"',1)";
        pst=con.prepareStatement(add_query);
        pst.execute();
        
        JOptionPane.showMessageDialog(null,"Congrats, you did it");
        
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"ups,Error occured ");
        }
        
        
        
       
        
        

       
    }
    // End of add_driver method 
    
    
    
    
    //start of remove_driver method
    public void remove_driver(String pdriver_id)
    {   
        int x=JOptionPane.showConfirmDialog(null,"Do you want to continue");
        
        if(x==0)
        {
        
            try {
                String query="update driver set status='0' where driver_id='"+pdriver_id+"'";
                pst=con.prepareStatement(query);
                pst.execute();
                
                JOptionPane.showMessageDialog(null,"Driver removed");
                
            } catch (Exception e) {
            }
        
        }
    }
    
    //end of remove_driver method
   
    
    
    //start of update_driver
    
    public void update_driver(String driverId,String fname,String lname,String nic,String address,String phone,String drivingLicenceNo,String picture,int availabilty )
    {
        driver_id=driverId;
        this.driver_fname=fname;
        this.driver_lname=lname;
        this.driver_nic=nic;
        this.driver_phone=phone;
        this.driver_drivingLiceneNo=drivingLicenceNo;
        
        this.driver_picture=picture;
        
       if(driver_id !="")
       {
       
        if(availabilty==0)
            this.driver_availalibity="available";
        else if(availabilty==1)
            this.driver_availalibity="Not available";
            
        try {
              String query="update driver set fname='"+driver_fname+"',lname='"+driver_lname+"',NIC='"+driver_nic+"',address='"+driver_address+"',phone='"+driver_phone+"',drivingLicenseNo='"+driver_drivingLiceneNo+"',availability='"+driver_availalibity+"',picture='"+driver_picture+"',status='1' where driver_id='"+driverId+"'";
              pst=con.prepareStatement(query);
              pst.execute();
        } catch (Exception e) {
        }
       }
       else
       {
           JOptionPane.showMessageDialog(null,"Select a driver to update");
       }
    }
    
    //end of update_driver
 
    //start of driver table load  method
    public void Driver_jtable_view(JCheckBox cb)
    {
        if(!cb.isSelected())
        {
             
        try {
                
            String query="select driver_ID as 'Driver ID',fname as 'First Name',lname as 'Last Name',NIC as 'NIC',address as 'Address',phone as 'Phone',drivingLicenseNo as 'Driving Licence No',availability as 'Availability' from driver where status='1' ";
                    pst=con.prepareStatement(query);
                    
                    re=pst.executeQuery();
                  
                   // driver_view_jtable        
                   driver_view_jtable.setModel(DbUtils.resultSetToTableModel(re));
                    

        } catch (Exception e) {
        }
        }
        else
        {
            
            try {
                    String query="select driver_ID as 'Driver ID',fname as 'First Name',lname as 'Last Name',nic as 'NIC',address as 'Address',phone as 'Phone',drivingLicenseNo as 'Driving Licence No',availability as 'Availability' from driver";
                    pst=con.prepareStatement(query);
                    re=pst.executeQuery();
                  
                            
                   driver_view_jtable.setModel(DbUtils.resultSetToTableModel(re));
                    

        } catch (Exception e) {
        }
            
        }
        
    } 
    
    
    
    
    
    //end of driver table load  method
        
         
    //start of driver search method
  
     public void driver_search(String f,JCheckBox cb)
    {
        if(!cb.isSelected())
        {
        //using nic
        try {
                    String query="select driver_ID as 'Driver ID',fname as 'First Name',lname as 'Last Name',nic as 'NIC',address as 'Address',phone as 'Phone',drivingLicenseNo as 'Driving Licence No',availability as 'Availability' from driver where NIC like ? and status='1'";
                    pst=con.prepareStatement(query);
                    pst.setString(1,f+"%");
                    re=pst.executeQuery();
                  
                       System.out.println("secrhh jeyy");     
                   driver_view_jtable.setModel(DbUtils.resultSetToTableModel(re));
        } catch (Exception e) {
        }
        }
        else
        {
            
            
            try {
                    String query="select driver_id,fname,lname,nic,address,phone,drivingLicenseNo,availability from driver where NIC like ? ";
                    pst=con.prepareStatement(query);
                    pst.setString(1,f+"%");
                    re=pst.executeQuery();
                  
                       System.out.println("secrhh jeyy");     
                   driver_view_jtable.setModel(DbUtils.resultSetToTableModel(re));
        } catch (Exception e) {
        }
            
            
        }
        
    }
     public String get_pic()
     {
             jFileChooser1.showOpenDialog(jFileChooser1);
    File f = jFileChooser1.getSelectedFile();
    String path = f.getAbsolutePath();
 //  driver_picture.setText(null);
     return path;
     }
     
     public void view_pic()
     {
         
     //    jFileChooser1.showOpenDialog(jFileChooser1);
    //File f = jFileChooser1.getSelectedFile();
    String path ="C:/Users/Public/Pictures/Sample Pictures/Hydrangeas.jpg";
    
    File f1 = new File(path);
         System.out.println(f1);
    try {
        Image im = ImageIO.read(f1);
     //   Image im1 = im.getScaledInstance(jp.getWidth(), jp.getHeight(), Image.SCALE_SMOOTH);
      //  jp.setIcon(new ImageIcon(im1));
       //jLabel15.setText(path);
    } 
    catch (Exception e) {
        System.out.println("image" + e);
    }
     }
    
    public void driver_reset_fields(JTextField j1,JTextField j2,JTextField j3,JTextField j4,JTextField j5,JTextField j6,JTextField j7,JTextField j8,JComboBox c1)
    {
        j1.setText("");
        j2.setText("");
        j3.setText("");
        j4.setText("");
        j5.setText("");
        j6.setText("");
        j7.setText("");
        j8.setText("");
        c1.setSelectedIndex(0);
        
        Driver_jtable_view(main.driver_view_all);
        
    }
    
    
    //validations
    
    
    
    
    public boolean driver_nic_validate(JTextField nic)
    {
        String query="select count(nic) as NIC from driver where nic='"+nic.getText()+"'";
          try {
              pst=con.prepareStatement(query);
              re=pst.executeQuery();
              
              while(re.next())
              {
                  nic_count=re.getInt("NIC");
              }
              
              
              
          } catch (Exception e) {
              
          }
        
        
        
        
        Pattern pattern = Pattern.compile("\\d{9}[Vv]");
      Matcher matcher = pattern.matcher(nic.getText());
      if(!matcher.matches())
      {
         JOptionPane.showMessageDialog(null,"Please provide valid nic details");
          return false;
          
          
      }
      else if(nic_count>0)
      {
          
   
             JOptionPane.showMessageDialog(null,"NIC number already exist");
              return false;
               
          
      }
      else
      {
          return true;
      }
     
      
    }
    
    public boolean driver_phone_validate(JTextField phone)
    {
        Pattern pattern = Pattern.compile("\\d{10}");
      Matcher matcher = pattern.matcher(phone.getText());
      if(!matcher.matches())
      {
          JOptionPane.showMessageDialog(null,"please enter valid phone number");
          return false;
          
          
      }
      else
          return true;
      
    }
    public boolean driver_validate(JTextField fname,JTextField lname,JTextField drivingLiNo,JTextField nic,JTextField phone)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z,. ]+$");
        Pattern pattern_drivingLiNo = Pattern.compile("\\[A-Z]d{6}");
        
      Matcher matcher_fname = pattern.matcher(fname.getText());
      Matcher matcher_lname = pattern.matcher(lname.getText());
      Matcher matcher_drivingLiNo = pattern.matcher(drivingLiNo.getText());
      
      
      
      
                
        
        if(!matcher_fname.matches() || !matcher_lname.matches() )
        {
            JOptionPane.showMessageDialog(null,"Only lettes can be entered to the name");
            return false;
        }
        else if(!!matcher_drivingLiNo.matches())
        {
            JOptionPane.showMessageDialog(null,"Please enter a valid Driving Licence Number");
            return false;
        }
               
        else if(!driver_nic_validate(nic))
        {
           // JOptionPane.showMessageDialog(null,"please enter valid NIC");
            return false;
      
        }
        else if(!driver_phone_validate(phone))
        {
         
         return false;
        }
         else
            return true;
    }
    
    
    public boolean driver_nic_validate_update(String pdriv_id,JTextField nic)
    {
        String query="select *,count(nic) as NICc from driver where nic='"+nic.getText()+"'";
          try {
              pst=con.prepareStatement(query);
              re=pst.executeQuery();
              
              while(re.next())
              {
                  nic_count=re.getInt("NICc");
                  driv_id=re.getInt("driver_ID");
                  
                  
              }
              
              
              
          } catch (Exception e) {
              
          }
        
        
        
        
        Pattern pattern = Pattern.compile("\\d{9}[Vv]");
      Matcher matcher = pattern.matcher(nic.getText());
      if(!matcher.matches())
      {
         JOptionPane.showMessageDialog(null,"Please provide valid nic details");
          return false;
          
          
      }
      else if(nic_count>0 && driv_id != Integer.parseInt(pdriv_id))
      {
          
   
             JOptionPane.showMessageDialog(null,"NIC number already exist");
              return false;
               
          
      }
      else
      {
          return true;
      }
     
      
    }
    
    
    
    
    
    
    
    public boolean driver_validate_update(String id,JTextField fname,JTextField lname,JTextField drivingLiNo,JTextField nic,JTextField phone)
    {
        Pattern pattern = Pattern.compile("^[a-zA-Z,. ]+$");
        Pattern pattern_drivingLiNo = Pattern.compile("\\[A-Z]d{6}");
        
      Matcher matcher_fname = pattern.matcher(fname.getText());
      Matcher matcher_lname = pattern.matcher(lname.getText());
      Matcher matcher_drivingLiNo = pattern.matcher(drivingLiNo.getText());
      
      
      
      
                
        
        if(!matcher_fname.matches() || !matcher_lname.matches() )
        {
            JOptionPane.showMessageDialog(null,"Only lettes can be entered to the name");
            return false;
        }
        else if(!!matcher_drivingLiNo.matches())
        {
            JOptionPane.showMessageDialog(null,"Please enter a valid Driving Licence Number");
            return false;
        }
               
        else if(!driver_nic_validate_update(id,nic))
        {
           // JOptionPane.showMessageDialog(null,"please enter valid NIC");
            return false;
      
        }
        else if(!driver_phone_validate(phone))
        {
         
         return false;
        }
         else
            return true;
    }
    
    
    
    
    
    //get all available drivers
    
    public void avalable_drivers(JComboBox jc)
    {
        String sql="select * from driver where status='1' and availability='available' order by nic";
        try {
            pst=con.prepareStatement(sql);
            re=pst.executeQuery();
           
            while(re.next())
            { 
                jc.addItem(re.getString("nic"));
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro loading available drivers");
        }
    }
    
    
    
}// driver class ends

