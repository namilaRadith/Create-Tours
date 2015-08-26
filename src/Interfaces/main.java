/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import sandbox.mainsand;
import ApplicationClasses.DbConnect;
import ApplicationClasses.Employee;
import ApplicationClasses.Marketing.MarValidator;
import ApplicationClasses.Marketing.MarketingCampaign;
import ApplicationClasses.Marketing.MarketingItems;
import ApplicationClasses.Marketing.PromotionsAndDeals;
import ApplicationClasses.Packages.StandardPackage2;
import ApplicationClasses.Vehicles.companyVehicle;
import ApplicationClasses.Vehicles.outsourceVehicle;
import ApplicationClasses.driver;
import com.mysql.jdbc.StringUtils;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.awt.event.ItemEvent;
import java.io.File;
//import ApplicationClasses.vehicle;
//import ApplicationClasses.companyVehicle;
//import ApplicationClasses.outsourceVehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Namila Radith
 */
public class main extends javax.swing.JFrame {

    Connection conn = null;   
    PreparedStatement pst = null;
    ResultSet rs = null;
    int r = -1;
    int undex[] = {2,4,6,8};
    ArrayList<JTextField> JTL = new ArrayList<>();
    driver d1=new driver();
  
    
    public main() {
        //SyntheticaSilverMoonLookAndFeel load
        try {
            UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(mainsand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(mainsand.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();
        
        //vehicle table loads
        //outSourceVehicleTableLoad();
        //companyVehicleTableLoad();
        //vehicle table loads end
        
        //reservation table load
        tabelLoad("SELECT * FROM createtours.reservation_main_view",tableReservation);        
        jpanAdvancedSearch.setVisible(false);
        
        //package table load
        String slq = "SELECT* FROM standardpackage,standardpackagetype WHERE  std_package_type_ID_ref = std_package_type_ID AND standardpackagetype.deleteStatus = 1";
        tabelLoad(slq, packageTable);
        
        tableFilter(txt_reservation_serarch, tableReservation);
        tableFilter(lbl_StdPakQuickSearch,packageTable );
        
  
       
        initialComponentsVehicle();
        
           //Load the tables @basuru
        promotionTableLoad();
        MarketingItemsTableLoad();
        MarketingCampgaignTableLoad();
        HideItemsPromotoionAndMarketing();
        
        
        
        d1.Driver_jtable_view(driver_view_all);
       driver_remove_jbutton.setEnabled(false);
         driver_update_jbutton.setEnabled(false);
        
        
        
        tableload1();
         tableload2();
         hideFields();
        
        employeeTableLoad();
        
        
       
        
        

    }

    public void employeeTableLoad()
    {
        
        try 
        {
            String vehicleSql = "SELECT employee_ID AS employeeID,NIC AS NIC,fname AS Fname,lname AS Lname,address AS Address,phone AS Phone,bankAccountNo AS Bankaccount ,description AS Descriptio,post AS Post FROM employee WHERE deleteStatusEmp = 1";
            pst = conn.prepareStatement(vehicleSql);
            rs = pst.executeQuery();
            tbl_Employee.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
        
    public void tableload1()
    {
        try
        {
            String sql = "SELECT * from utility";
            pst = conn.prepareStatement(sql); 
            rs = pst.executeQuery();
            
            jtutility.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } 
        catch (Exception e)
        {
        }
        
    }
    
     public void tableload2()
    {
        try
        {
           String sql = "SELECT * from cheque";
          
            pst = conn.prepareStatement(sql); 
            rs = pst.executeQuery();
            
            jtcheque.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } 
        catch (Exception e)
        {
        }
        
        
    }
        
    
     
     
     public void hideFields()
        {
            uIDlbl.setVisible(false);
        }
     
   
    
   /*====================================== CUSTOMER START ========================================*/  
       //Search GenCus With name
        public void GenCusSearch1(String GenCusN )
    {
          conn = DbConnect.connect();
        try {
                String resultGenCus="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where g.fname LIKE '%"+GenCusN+"%' OR g.lname LIKE '%"+GenCusN+"%' and g.status=1";
                pst=conn.prepareStatement(resultGenCus);
                rs=pst.executeQuery();
               
              
            SrchRGenTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
                 //Search GenCus With NIC
        public void GenCusSearch2(String GenCusNIC )
    {
        conn = DbConnect.connect();
        try {
                String resultGenCus="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where g.NIC LIKE '"+GenCusNIC+"'  and g.status=1";
                pst=conn.prepareStatement(resultGenCus);
                 rs=pst.executeQuery();
               
              
            SrchRGenTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
        //genCus Search with address
        public void GenCusSearch3(String GenCusAdd )
    {
        conn = DbConnect.connect();
        try {
                String resultGenCus="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where g.address LIKE '%"+GenCusAdd+"%'  and g.status=1";
                pst=conn.prepareStatement(resultGenCus);
                 rs=pst.executeQuery();
               
              
            SrchRGenTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
        //method to search company customers by name
        public void CompanySearch1(String Cname) throws SQLException
        {
            conn = DbConnect.connect();
           String sql="SELECT t.customer_ID,t.companyName,t.contactPerson,t.type,c.address,c.email,p.phone1,p.phone2,t.totalAmount FROM govandprivatecompanies t inner join customer c on t.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where t.companyName LIKE '%"+Cname+"%' AND t.status=1";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            SrchRCmpanyTable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        
        //method to search company customer by type
         public void CompanySearch2(String Ctype) throws SQLException
        {
            conn = DbConnect.connect();
           String sql="SELECT t.customer_ID,t.companyName,t.contactPerson,t.type,c.address,c.email,p.phone1,p.phone2,t.totalAmount FROM govandprivatecompanies t inner join customer c on t.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where t.type LIKE '%"+Ctype+"%' AND t.status=1";
            pst = conn.prepareStatement(sql);
             rs = pst.executeQuery();
            SrchRCmpanyTable.setModel(DbUtils.resultSetToTableModel(rs));
        }

         
 /*====================================== CUSTOMER END ========================================*/
         
   
 /*======================================RESERVATION START ========================================*/   
      
 //tableload for this class
    public void tabelLoad(String q,JTable jt) {
        
        PreparedStatement pst = null;
        ResultSet rst = null; 
        conn = DbConnect.connect();
        
        
        try {
            pst = conn.prepareStatement(q);
            rst = pst.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rst));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
 //table filter  
    public void tableFilter(JTextField tfild,JTable jt ) {
        TableRowSorter<TableModel> sorter  = new TableRowSorter<>(jt.getModel());
        jt.setRowSorter(sorter);
        
        
        tfild.getDocument().addDocumentListener(new DocumentListener(){
            
            
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tfild.getText();
              
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    //System.out.println(jt.getRowCount());                 
                  
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tfild.getText();
                
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    System.out.println(jt.getRowCount());
                    
//                    if(jt.getRowCount() == 0){
//                        JOptionPane.showMessageDialog(rootPane, "Search not found ");
//                    }
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
 
  /*======================================RESERVATION END========================================*/     
    
  /*======================================VEHICLE START========================================*/  
    
   public void initialComponentsVehicle() {
        //vehicle
        lbl_VehicleID.setEnabled(false);
        lbl_Vehicle_ID_Vehicle.setEnabled(false);
        lbl_VehicleType.setEnabled(false);
        lbl_vehicleMode_Vehiclel.setEnabled(false);
        txt_Vehicle_Model_Vehicle.setEnabled(false);
        lbl_LicenceNo_Vehiclel.setEnabled(false);
        txt_licence_No_Vehicle.setEnabled(false);
        cmb_Vehicle_Type_Vehicle.setEnabled(false);
        lbl_TransmitType_Vehiclel.setEnabled(false);
        cmb_transmit_Type_Vehicle.setEnabled(false);
        lbl_FuelType_Vehiclel.setEnabled(false);
        cmb_fuel_Type_Vehicle.setEnabled(false);
        lbl_VehicleColor_Vehiclel.setEnabled(false);
        txt_vehicle_Colour_Vehicle.setEnabled(false);
        lbl_NoOfSeats_Vehiclel.setEnabled(false);
        txt_no_Of_Seats_Vehicle.setEnabled(false);
        lbl_VehicleStatus_Vehiclel.setEnabled(false);
        cmb_vehicle_Status_Vehicle.setEnabled(false);                
        lbl_wner_ID_Vehicle.setVisible(false);
        lbl_ownerID_Vehicle.setEnabled(false);
        txt_owner_Name_Vehicle.setEnabled(false);
        txt_owner_Address_Vehicle.setEnabled(false);
        txt_owner_PhoneNo_Vehicle.setEnabled(false);
        lbl_OwnerName_Vehiclel.setEnabled(false);
        lbl_OwnerAddress_Vehiclel.setEnabled(false);
        lbl_OwnerPhone_Vehiclel.setEnabled(false);
        tbl_OutVehicleTable_Vehicle.setVisible(false);
        lbl_outVehicleTable.setEnabled(false);
        tbl_CompanyVehiTable_Vehicle.setVisible(false);
        lbl_companyVehicleTable.setEnabled(false);
        btn_vehicleAdd_Vehicle.setEnabled(false);
        btn_vehicleEdit_Vehicle.setEnabled(false);
        btn_vehicleRemove_Vehicle.setEnabled(false);
        btn_Reset_Vehicle.setEnabled(false);
        btn_vehicleSearch_Vehicle.setEnabled(false);
        btn_vehicleMaintain_Vehicle.setEnabled(false);
        //vehicle end
    }
   //outsource vehicle table load
   public void outSourceVehicleTableLoad()
    {
        
        try 
        {
            String vehicleSql = "SELECT v.vehicle_ID AS vehicleID,v.vehicleType AS Type,v.vehicalModel AS Model,v.licenceNo AS LicenceNo,v.transmisionType AS Transmission,v.fuelType AS Fuel,v.color AS Colour,v.noOfSeats AS Seats,v.status AS Availability,o.owner_ID AS Owner,o.ownerName AS Name,o.address AS Address,o.phoneNo AS Phone FROM vehicle v,owner o,outsourcevehicle ov WHERE o.owner_ID = ov.Owner_owner_ID AND v.vehicle_ID = ov.vehicle_ID AND o.deleteStatus = 1 AND v.deleteStatus = 1";
            pst = conn.prepareStatement(vehicleSql);
            rs = pst.executeQuery();
            tbl_OutVehicleTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //company vehicle table load  
   public void companyVehicleTableLoad()
    {
        
        try 
        {
            String vehicleSql = "SELECT v.vehicle_ID AS vehicleID,v.vehicleType AS Type,v.vehicalModel AS Model,v.licenceNo AS LicenceNo,v.transmisionType AS Transmission,v.fuelType AS Fuel,v.color AS Colour,v.noOfSeats AS Seats,v.status AS Availability FROM vehicle v  WHERE type = 'IN' AND deleteStatus = 1 ";
            pst = conn.prepareStatement(vehicleSql);
            rs = pst.executeQuery();
            tbl_CompanyVehiTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    /*======================================VEHICLE END========================================*/    
   
   public void promotionTableLoad() {
        try {
            String sql = "SELECT * FROM promotionsanddeals";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            PjTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Marketing Items Tables

    public void MarketingItemsTableLoad() {
        try {
            String sql = "SELECT * FROM mitems";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            MIjTable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void MarketingCampgaignTableLoad()
    {
        try {
            String Sql = "SELECT * FROM mcamp";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();
            
            
            MCampTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }

    public void HideItemsPromotoionAndMarketing() {
        MIID.setVisible(false);
        MCID.setVisible(false);
        PjLableID.setVisible(false);
        

    }

    public void ClearPromotionFeilds() {
        PNameText.setText("");
        PDescriotionText.setText("");
        PDiscountText.setText("");
        PPicture.setText("");
        PJDateChooser.setDate(null);
        PJDateChooserTo.setDate(null);

       
        MIComboBox.setSelectedIndex(0);
        MICostText.setText("");
        MIQtyText.setText("");
        MItemsDesc.setText("");
        MIID.setText("");
        MIjTable.clearSelection();
        MarketingItemsTableLoad();

        MCNameText.setText("");
        MCDescriptionText.setText("");
        MCDateChooser.setDate(null);
        MCCost.setText("");
        MVenueText.setText("");
        MCID.setText("");
        MCSearchText.setText("");
        MarketingCampgaignTableLoad();
        
       

    }
    public void DeleteDataBeforeEdit(int ID)
    {
        try {
            String Sql = "CALL DeleteWholeWhileEdit('"+ID+"')";
            pst = conn.prepareStatement(Sql);
            pst.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Clears all the Arrays used after pressing done or edit button
     */
    public void ClearDataArrays()
    {
        MarketingCampaign MC = new MarketingCampaign();
        MC.ClearData();
        MarketingCampaignAddItems.TempHoldThis.clear();
        MarketingCampaignAddItems.AR.clear();
        MarketingCampaignAddItems.row.clear();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        cusType = new javax.swing.ButtonGroup();
        companyType = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        SearchOnlineCus = new javax.swing.ButtonGroup();
        SearchCmpany = new javax.swing.ButtonGroup();
        SearchGenCusR = new javax.swing.ButtonGroup();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Marketing_mgt = new javax.swing.JTabbedPane();
        HOME = new javax.swing.JPanel();
        VehicleMgtSystem = new javax.swing.JPanel();
        JpanalVMS = new javax.swing.JPanel();
        lbl_VehicleID = new javax.swing.JLabel();
        lbl_VehicleType = new javax.swing.JLabel();
        lbl_LicenceNo_Vehiclel = new javax.swing.JLabel();
        lbl_vehicleMode_Vehiclel = new javax.swing.JLabel();
        lbl_TransmitType_Vehiclel = new javax.swing.JLabel();
        lbl_FuelType_Vehiclel = new javax.swing.JLabel();
        lbl_NoOfSeats_Vehiclel = new javax.swing.JLabel();
        lbl_VehicleStatus_Vehiclel = new javax.swing.JLabel();
        lbl_VehicleColor_Vehiclel = new javax.swing.JLabel();
        txt_licence_No_Vehicle = new javax.swing.JTextField();
        txt_Vehicle_Model_Vehicle = new javax.swing.JTextField();
        txt_vehicle_Colour_Vehicle = new javax.swing.JTextField();
        txt_no_Of_Seats_Vehicle = new javax.swing.JTextField();
        cmb_vehicle_Status_Vehicle = new javax.swing.JComboBox();
        cmb_transmit_Type_Vehicle = new javax.swing.JComboBox();
        lbl_Vehicle_ID_Vehicle = new javax.swing.JLabel();
        btn_vehicleAdd_Vehicle = new javax.swing.JButton();
        btn_vehicleEdit_Vehicle = new javax.swing.JButton();
        btn_vehicleRemove_Vehicle = new javax.swing.JButton();
        cmb_fuel_Type_Vehicle = new javax.swing.JComboBox();
        VehicleScrollPane2 = new javax.swing.JScrollPane();
        tbl_OutVehicleTable_Vehicle = new javax.swing.JTable();
        btn_vehicleMaintain_Vehicle = new javax.swing.JButton();
        lbl_OwnerAddress_Vehiclel = new javax.swing.JLabel();
        VehicleScrollPane3 = new javax.swing.JScrollPane();
        txt_owner_Address_Vehicle = new javax.swing.JTextArea();
        lbl_OwnerPhone_Vehiclel = new javax.swing.JLabel();
        txt_owner_PhoneNo_Vehicle = new javax.swing.JTextField();
        lbl_OwnerName_Vehiclel = new javax.swing.JLabel();
        txt_owner_Name_Vehicle = new javax.swing.JTextField();
        cmb_Vehicle_Type_Vehicle = new javax.swing.JComboBox();
        rdb_companyVehicle = new javax.swing.JRadioButton();
        rdb_OutSourceVehicle = new javax.swing.JRadioButton();
        Vehicle_ScrollPane4 = new javax.swing.JScrollPane();
        tbl_CompanyVehiTable_Vehicle = new javax.swing.JTable();
        lbl_outVehicleTable = new javax.swing.JLabel();
        lbl_companyVehicleTable = new javax.swing.JLabel();
        lbl_wner_ID_Vehicle = new javax.swing.JLabel();
        lbl_ownerID_Vehicle = new javax.swing.JLabel();
        btn_vehicleSearch_Vehicle = new javax.swing.JButton();
        btn_Reset_Vehicle = new javax.swing.JButton();
        lbl_Vehicle_Vehicle = new javax.swing.JLabel();
        Packages = new javax.swing.JPanel();
        jLabel9_package = new javax.swing.JLabel();
        jPanel11_package = new javax.swing.JPanel();
        jPanel19_package = new javax.swing.JPanel();
        jButton6_package = new javax.swing.JButton();
        jButton5_package = new javax.swing.JButton();
        jScrollPane4_package = new javax.swing.JScrollPane();
        packageTable = new javax.swing.JTable();
        jLabel25__package = new javax.swing.JLabel();
        stdPakAdvanceSearchJPanel = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        chk_StdPakAdvSearchPackName = new javax.swing.JCheckBox();
        chk_StdPakAdvSearchVehiType = new javax.swing.JCheckBox();
        chk_StdPakAdvSearchPakPrice = new javax.swing.JCheckBox();
        txt_StdPakAdvSearchPakName = new javax.swing.JTextField();
        txt_StdPakAdvSearchPrice = new javax.swing.JTextField();
        txt_StdPakAdvSearchKmLimit = new javax.swing.JTextField();
        btn_StdPakAdvSearch = new javax.swing.JButton();
        chk_StdPakAdvSearchKmLimit = new javax.swing.JCheckBox();
        p_cmb_vehicleType = new javax.swing.JComboBox();
        cmb_stdPakSearchType = new javax.swing.JComboBox();
        stdPakQuickSearchJPanel = new javax.swing.JPanel();
        lbl_StdPakQuickSearch = new javax.swing.JTextField();
        jButton7__package = new javax.swing.JButton();
        btn_PakReset = new javax.swing.JButton();
        CustomerMgtSystem = new javax.swing.JPanel();
        GennCusPanel = new javax.swing.JPanel();
        lbl_searchGeneralCustomer = new javax.swing.JLabel();
        SrchByFnameR = new javax.swing.JRadioButton();
        SrchByNICR = new javax.swing.JRadioButton();
        SrchByAddR = new javax.swing.JRadioButton();
        SrchByGen = new javax.swing.JTextField();
        CusSrchGenB = new javax.swing.JButton();
        SrchGenCusScrollPane = new javax.swing.JScrollPane();
        SrchRGenTable = new javax.swing.JTable();
        CompanyPanel = new javax.swing.JPanel();
        SrchByCNameR = new javax.swing.JRadioButton();
        SrchByCType = new javax.swing.JRadioButton();
        SrchByCnameT = new javax.swing.JTextField();
        lbl_searchCompanies = new javax.swing.JLabel();
        SrchCmpanyScrollPane = new javax.swing.JScrollPane();
        SrchRCmpanyTable = new javax.swing.JTable();
        SrchCmpanyB = new javax.swing.JButton();
        CTypeSearchCombo = new javax.swing.JComboBox();
        OnlineCusPanel = new javax.swing.JPanel();
        Customer_SOnilineL = new javax.swing.JLabel();
        OnlineSbyNameRB = new javax.swing.JRadioButton();
        OnlineSByUnameRB = new javax.swing.JRadioButton();
        OnlineSByNICRB = new javax.swing.JRadioButton();
        SrchonlineparmT = new javax.swing.JTextField();
        srchOnlineScrollPane = new javax.swing.JScrollPane();
        SrchROnlineTable = new javax.swing.JTable();
        SrchOnlineB = new javax.swing.JButton();
        Customer_SearchCusL = new javax.swing.JLabel();
        Customer_AddB = new javax.swing.JButton();
        ReservationMgetSystem = new javax.swing.JPanel();
        jsp = new javax.swing.JScrollPane();
        tableReservation = new javax.swing.JTable();
        LBL_orderBy = new javax.swing.JLabel();
        cmb_resSearchOrderBy = new javax.swing.JComboBox();
        txt_reservation_serarch = new javax.swing.JTextField();
        jpanAdvancedSearch = new javax.swing.JPanel();
        CHKB_customer = new javax.swing.JCheckBox();
        CMB_noOfVehicals = new javax.swing.JCheckBox();
        CHKB_startDate = new javax.swing.JCheckBox();
        LBL_between1 = new javax.swing.JLabel();
        CHKB_endDate = new javax.swing.JCheckBox();
        CHKB_reservationID = new javax.swing.JCheckBox();
        TXTB_customerName = new javax.swing.JTextField();
        TXTB_noOfVehicals = new javax.swing.JTextField();
        TXTB_reservationID = new javax.swing.JTextField();
        BTN_ADsearch = new javax.swing.JButton();
        CHKB_status = new javax.swing.JCheckBox();
        CMBO_searchStatus = new javax.swing.JComboBox();
        DATEPICKER_searchStartDate1 = new com.toedter.calendar.JDateChooser();
        DATEPICKER_searchStarttDate2 = new com.toedter.calendar.JDateChooser();
        LBL_between2 = new javax.swing.JLabel();
        DATEPICKER_searchEndDate1 = new com.toedter.calendar.JDateChooser();
        DATEPICKER_searchEndDate2 = new com.toedter.calendar.JDateChooser();
        LBL_between3 = new javax.swing.JLabel();
        btn_add_reservaion = new javax.swing.JButton();
        btn_edit_reservation = new javax.swing.JButton();
        LBL_searchType = new javax.swing.JLabel();
        com_SearchType = new javax.swing.JComboBox();
        LBL_tableFilter = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        promotionAndDealsMainJpanel = new javax.swing.JPanel();
        promotionAndDealsSubJpanel_01 = new javax.swing.JPanel();
        PjLableID = new javax.swing.JLabel();
        PjBUpload = new javax.swing.JButton();
        PDiscount = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        PDescriotionText = new javax.swing.JTextPane();
        PjBDelete = new javax.swing.JButton();
        PDescription = new javax.swing.JLabel();
        PName = new javax.swing.JLabel();
        PPicture = new javax.swing.JTextField();
        PjBEdit = new javax.swing.JButton();
        PValidDateTo = new javax.swing.JLabel();
        PName4 = new javax.swing.JLabel();
        PjBAdd = new javax.swing.JButton();
        PDiscountText = new javax.swing.JTextField();
        PNameText = new javax.swing.JTextField();
        PjBReset = new javax.swing.JButton();
        PValidDate2 = new javax.swing.JLabel();
        PJDateChooserTo = new com.toedter.calendar.JDateChooser();
        PJDateChooser = new com.toedter.calendar.JDateChooser();
        promotionAndDealsSubJpanel_02 = new javax.swing.JPanel();
        PSearchName = new javax.swing.JLabel();
        PSearchNameText = new javax.swing.JTextField();
        PSearchBtn = new javax.swing.JButton();
        promotionAndDealsSubJpanel_03 = new javax.swing.JPanel();
        promotionAndDealsSubJScrollpanel_03 = new javax.swing.JScrollPane();
        PjTable = new javax.swing.JTable();
        Marketing = new javax.swing.JPanel();
        Marketing_main_Jpanel = new javax.swing.JPanel();
        Marketing_sub_Jpanel_1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        MICostText = new javax.swing.JTextField();
        MIQtyText = new javax.swing.JTextField();
        MIComboBox = new javax.swing.JComboBox();
        MIAddBtn = new javax.swing.JButton();
        MIEditBtn = new javax.swing.JButton();
        MIDeleteBtn = new javax.swing.JButton();
        Marketing_JscrollPane_1 = new javax.swing.JScrollPane();
        MItemsDesc = new javax.swing.JTextArea();
        Marketing_JscrollPane_2 = new javax.swing.JScrollPane();
        MIjTable = new javax.swing.JTable();
        PjBReset1 = new javax.swing.JButton();
        MIID = new javax.swing.JLabel();
        Marketing_sub_Jpanel2 = new javax.swing.JPanel();
        MCNameText = new javax.swing.JTextField();
        Marketing_JscrollPane_3 = new javax.swing.JScrollPane();
        MCDescriptionText = new javax.swing.JTextPane();
        MVenueText = new javax.swing.JTextField();
        MCCost = new javax.swing.JTextField();
        PDiscount2 = new javax.swing.JLabel();
        PValidDate1 = new javax.swing.JLabel();
        PDiscount1 = new javax.swing.JLabel();
        PDescription1 = new javax.swing.JLabel();
        PName1 = new javax.swing.JLabel();
        Marketing_JscrollPane_4 = new javax.swing.JScrollPane();
        MCampTable = new javax.swing.JTable();
        MCAddBtn = new javax.swing.JButton();
        MCEditBtn = new javax.swing.JButton();
        MCDeleteBtn = new javax.swing.JButton();
        MCjBReset = new javax.swing.JButton();
        PDiscount3 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        MCID = new javax.swing.JLabel();
        MCSearchText = new javax.swing.JTextField();
        MCSearchLable = new javax.swing.JLabel();
        MCSearchBtn = new javax.swing.JButton();
        Marketing_JscrollPane_5 = new javax.swing.JScrollPane();
        MCampTable1 = new javax.swing.JTable();
        Marketing_JscrollPane_6 = new javax.swing.JScrollPane();
        MCampTable2 = new javax.swing.JTable();
        MCDateChooser = new com.toedter.calendar.JDateChooser();
        Driver_MgtSystem = new javax.swing.JPanel();
        jTabbedPane2_DRIVER = new javax.swing.JTabbedPane();
        jPanel13_driver = new javax.swing.JPanel();
        jPanel14_driver = new javax.swing.JPanel();
        driver_add_jbutton = new javax.swing.JButton();
        driver_picture = new javax.swing.JTextField();
        driver_availalibity = new javax.swing.JComboBox();
        driver_phone = new javax.swing.JTextField();
        driver_nic = new javax.swing.JTextField();
        driver_licenceNO = new javax.swing.JTextField();
        driver_address = new javax.swing.JTextField();
        jLabel27_driver = new javax.swing.JLabel();
        jLabel28_driver = new javax.swing.JLabel();
        jLabel25_driver = new javax.swing.JLabel();
        jLabel26_driver = new javax.swing.JLabel();
        jLabel29_driver = new javax.swing.JLabel();
        jLabel30_driver = new javax.swing.JLabel();
        driver_update_jbutton = new javax.swing.JButton();
        driver_remove_jbutton = new javax.swing.JButton();
        driver_reset_fields = new javax.swing.JButton();
        jLabel23_driver = new javax.swing.JLabel();
        driver_fname = new javax.swing.JTextField();
        driver_lname = new javax.swing.JTextField();
        jLabel24_driver = new javax.swing.JLabel();
        jScrollPane3_driver = new javax.swing.JScrollPane();
        driver_view_jtable = new javax.swing.JTable();
        driver_search = new javax.swing.JTextField();
        jLabel31_driver = new javax.swing.JLabel();
        driver_view_all = new javax.swing.JCheckBox();
        jPanel11_DRIVER = new javax.swing.JPanel();
        jPanel15_driver = new javax.swing.JPanel();
        jLabel32_driver = new javax.swing.JLabel();
        jTextField8_driver = new javax.swing.JTextField();
        jLabel33_driver = new javax.swing.JLabel();
        jTextField9_driver = new javax.swing.JTextField();
        jLabel34_driver = new javax.swing.JLabel();
        jLabel35_driver = new javax.swing.JLabel();
        jLabel36_driver = new javax.swing.JLabel();
        jScrollPane2_driver = new javax.swing.JScrollPane();
        jTextArea1_driver = new javax.swing.JTextArea();
        jButton5_driver = new javax.swing.JButton();
        jPanel17_driver = new javax.swing.JPanel();
        jScrollPane4_driver = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12_driver = new javax.swing.JPanel();
        jTabbedPane3_driver = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        JUtilityPanel_main = new javax.swing.JPanel();
        jtblno = new javax.swing.JTextField();
        jlbillNo = new javax.swing.JLabel();
        uIDlbl = new javax.swing.JLabel();
        jbBillSearch = new javax.swing.JButton();
        ibBillDelete = new javax.swing.JButton();
        JUtilityScorellPanel = new javax.swing.JScrollPane();
        jtutility = new javax.swing.JTable();
        jbBillAdd = new javax.swing.JButton();
        jPanel_Chque = new javax.swing.JPanel();
        jbchequeEdit = new javax.swing.JButton();
        Jscorellcheoue = new javax.swing.JScrollPane();
        jtcheque = new javax.swing.JTable();
        jbchequeSearch = new javax.swing.JButton();
        jbchequeDelete = new javax.swing.JButton();
        jtche = new javax.swing.JTextField();
        jlchequeNo = new javax.swing.JLabel();
        jbchequeAdd = new javax.swing.JButton();
        EmployeeMgtSystem = new javax.swing.JPanel();
        jp_Employee = new javax.swing.JPanel();
        lbl_eid_Employee = new javax.swing.JLabel();
        lbl_NIC_Employee = new javax.swing.JLabel();
        lbl_fname_Employee = new javax.swing.JLabel();
        lbl_lname_Employee = new javax.swing.JLabel();
        lbl_add_Employee = new javax.swing.JLabel();
        lbl_phn_Employee = new javax.swing.JLabel();
        lbl_acc_Employee = new javax.swing.JLabel();
        lbl_description_Employee = new javax.swing.JLabel();
        lbl_post_Employee = new javax.swing.JLabel();
        lbl_pic_Employee = new javax.swing.JLabel();
        txtf_NIC_Employee = new javax.swing.JTextField();
        txtf_fname_Employee = new javax.swing.JTextField();
        txtf_lname_Employee = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txta_add_Employee = new javax.swing.JTextArea();
        txtf_phn_Employee = new javax.swing.JTextField();
        txtf_acc_Employee = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txta_des_Employee = new javax.swing.JTextArea();
        txtf_post_Empolyee = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        btn_Add_Employee = new javax.swing.JButton();
        btn_Upadate_Employee = new javax.swing.JButton();
        btn_Remove_Employee = new javax.swing.JButton();
        btn_Search_Employee = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_Employee = new javax.swing.JTable();
        search_Employee = new javax.swing.JTextField();
        lbl_Eid_Employee = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings_bt_deafult.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 673, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help_bt_deafult.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 634, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin_bt_deafult.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout_bt_deafult.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(83, 634, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reservations_bt_deafult.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 338, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/employee_bt_deafult.png"))); // NOI18N
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 389, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home_bt_deafult.png"))); // NOI18N
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 65, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/packages_bt_deafult.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 236, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/finance_bt_deafult.fw.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/driver_bt_deafult.fw.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 491, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customers_bt_deafult.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 287, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/min_bt.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1285, 15, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vehi_bt_deafult.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 195, -1));

        Marketing_mgt.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        Marketing_mgt.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        HOME.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout HOMELayout = new javax.swing.GroupLayout(HOME);
        HOME.setLayout(HOMELayout);
        HOMELayout.setHorizontalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1165, Short.MAX_VALUE)
        );
        HOMELayout.setVerticalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );

        Marketing_mgt.addTab("HOME", HOME);

        VehicleMgtSystem.setBackground(new java.awt.Color(255, 255, 255));

        JpanalVMS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vehicle Management System", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        lbl_VehicleID.setText("Vehicle ID");

        lbl_VehicleType.setText("Vehicle Type");

        lbl_LicenceNo_Vehiclel.setText("Licence Number");

        lbl_vehicleMode_Vehiclel.setText("Vehicle Model");

        lbl_TransmitType_Vehiclel.setText("Transmission Type");

        lbl_FuelType_Vehiclel.setText("Fuel Type");

        lbl_NoOfSeats_Vehiclel.setText("Number Of Seats");

        lbl_VehicleStatus_Vehiclel.setText("Availability Status");

        lbl_VehicleColor_Vehiclel.setText("Colour");

        cmb_vehicle_Status_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Availability Status", "Available", "Reserved", "Crash", "Malfunction" }));

        cmb_transmit_Type_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Transmission Type", "Auto gear", "Manual" }));

        lbl_Vehicle_ID_Vehicle.setText("Vehicle ID");

        btn_vehicleAdd_Vehicle.setText("Add");
        btn_vehicleAdd_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleAdd_VehicleActionPerformed(evt);
            }
        });

        btn_vehicleEdit_Vehicle.setText("Edit");
        btn_vehicleEdit_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleEdit_VehicleActionPerformed(evt);
            }
        });

        btn_vehicleRemove_Vehicle.setText("Remove");
        btn_vehicleRemove_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleRemove_VehicleActionPerformed(evt);
            }
        });

        cmb_fuel_Type_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Fuel Type", "Diesel", "patrol" }));

        tbl_OutVehicleTable_Vehicle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "vehicleID", "Type", "Model", "LicenceNo", "Transmission", "Fuel", "Colour", "Seats", "Availability", "Owner", "Name", "Address", "Phone"
            }
        ));
        tbl_OutVehicleTable_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_OutVehicleTable_VehicleMouseClicked(evt);
            }
        });
        VehicleScrollPane2.setViewportView(tbl_OutVehicleTable_Vehicle);

        btn_vehicleMaintain_Vehicle.setText("Maintenance");
        btn_vehicleMaintain_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleMaintain_VehicleActionPerformed(evt);
            }
        });

        lbl_OwnerAddress_Vehiclel.setText("Address");

        txt_owner_Address_Vehicle.setColumns(20);
        txt_owner_Address_Vehicle.setRows(5);
        VehicleScrollPane3.setViewportView(txt_owner_Address_Vehicle);

        lbl_OwnerPhone_Vehiclel.setText("Phone Number");

        lbl_OwnerName_Vehiclel.setText("Owner name");

        txt_owner_Name_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_owner_Name_VehicleActionPerformed(evt);
            }
        });

        cmb_Vehicle_Type_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Vehicle Type", "Car", "Van_FAC", "Van_DAC", "Van_NAC", "Lorry_AC", "Jeep(Safari)" }));

        buttonGroup4.add(rdb_companyVehicle);
        rdb_companyVehicle.setText("Company Vehicle");
        rdb_companyVehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_companyVehicleMouseClicked(evt);
            }
        });
        rdb_companyVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_companyVehicleActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdb_OutSourceVehicle);
        rdb_OutSourceVehicle.setText("Out Source Vehicle");
        rdb_OutSourceVehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_OutSourceVehicleMouseClicked(evt);
            }
        });

        tbl_CompanyVehiTable_Vehicle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "vehicleID", "Type", "Model", "LicenceNo", "Transmission ", "Fuel", "Colour", "Seats", "Availability", "Owner", "Name", "Address", "Phone"
            }
        ));
        tbl_CompanyVehiTable_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CompanyVehiTable_VehicleMouseClicked(evt);
            }
        });
        Vehicle_ScrollPane4.setViewportView(tbl_CompanyVehiTable_Vehicle);

        lbl_outVehicleTable.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbl_outVehicleTable.setText("Outsource Vehicles");

        lbl_companyVehicleTable.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lbl_companyVehicleTable.setText("Company Vehicles");

        lbl_wner_ID_Vehicle.setText("Owner ID");

        btn_vehicleSearch_Vehicle.setText("Search");
        btn_vehicleSearch_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_vehicleSearch_VehicleActionPerformed(evt);
            }
        });

        btn_Reset_Vehicle.setText("Reset");
        btn_Reset_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Reset_VehicleActionPerformed(evt);
            }
        });

        lbl_Vehicle_Vehicle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lbl_Vehicle_Vehicle.setText("Vehicle");

        javax.swing.GroupLayout JpanalVMSLayout = new javax.swing.GroupLayout(JpanalVMS);
        JpanalVMS.setLayout(JpanalVMSLayout);
        JpanalVMSLayout.setHorizontalGroup(
            JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanalVMSLayout.createSequentialGroup()
                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanalVMSLayout.createSequentialGroup()
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanalVMSLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(lbl_Vehicle_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(JpanalVMSLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JpanalVMSLayout.createSequentialGroup()
                                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_FuelType_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_TransmitType_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_LicenceNo_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_VehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_VehicleID, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_Vehicle_ID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_licence_No_Vehicle, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                                .addComponent(txt_Vehicle_Model_Vehicle))
                                            .addComponent(cmb_transmit_Type_Vehicle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_Vehicle_Type_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_fuel_Type_Vehicle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_vehicle_Colour_Vehicle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(134, 134, 134)
                                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_OwnerPhone_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_OwnerAddress_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_VehicleStatus_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_NoOfSeats_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_wner_ID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_OwnerName_Vehiclel)))
                                    .addComponent(lbl_vehicleMode_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_owner_PhoneNo_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanalVMSLayout.createSequentialGroup()
                                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cmb_vehicle_Status_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_no_Of_Seats_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbl_ownerID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_owner_Name_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(VehicleScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_vehicleSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_vehicleRemove_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_vehicleAdd_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_vehicleEdit_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_Reset_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_vehicleMaintain_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(122, 122, 122))
                    .addGroup(JpanalVMSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Vehicle_ScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(VehicleScrollPane2)
                            .addGroup(JpanalVMSLayout.createSequentialGroup()
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_outVehicleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_companyVehicleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JpanalVMSLayout.createSequentialGroup()
                                        .addGap(319, 319, 319)
                                        .addComponent(rdb_companyVehicle)
                                        .addGap(87, 87, 87)
                                        .addComponent(rdb_OutSourceVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(JpanalVMSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_VehicleColor_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JpanalVMSLayout.setVerticalGroup(
            JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanalVMSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Vehicle_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanalVMSLayout.createSequentialGroup()
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdb_companyVehicle)
                            .addComponent(rdb_OutSourceVehicle))
                        .addGap(18, 18, 18)
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_VehicleID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Vehicle_ID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_NoOfSeats_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_no_Of_Seats_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_vehicleAdd_Vehicle))
                        .addGap(18, 18, 18)
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_VehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_VehicleStatus_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_vehicle_Status_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_vehicleEdit_Vehicle)
                            .addComponent(cmb_Vehicle_Type_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_vehicleMode_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_Vehicle_Model_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_wner_ID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_ownerID_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_vehicleRemove_Vehicle))
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JpanalVMSLayout.createSequentialGroup()
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_LicenceNo_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_licence_No_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_TransmitType_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_transmit_Type_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_FuelType_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_fuel_Type_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_VehicleColor_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_vehicle_Colour_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_OwnerPhone_Vehiclel)
                                    .addComponent(txt_owner_PhoneNo_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JpanalVMSLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_OwnerName_Vehiclel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_owner_Name_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(lbl_OwnerAddress_Vehiclel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpanalVMSLayout.createSequentialGroup()
                        .addComponent(btn_Reset_Vehicle)
                        .addGap(18, 18, 18)
                        .addGroup(JpanalVMSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(VehicleScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JpanalVMSLayout.createSequentialGroup()
                                .addComponent(btn_vehicleSearch_Vehicle)
                                .addGap(18, 18, 18)
                                .addComponent(btn_vehicleMaintain_Vehicle)))
                        .addGap(49, 49, 49)))
                .addComponent(lbl_companyVehicleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Vehicle_ScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_outVehicleTable, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VehicleScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout VehicleMgtSystemLayout = new javax.swing.GroupLayout(VehicleMgtSystem);
        VehicleMgtSystem.setLayout(VehicleMgtSystemLayout);
        VehicleMgtSystemLayout.setHorizontalGroup(
            VehicleMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JpanalVMS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        VehicleMgtSystemLayout.setVerticalGroup(
            VehicleMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VehicleMgtSystemLayout.createSequentialGroup()
                .addComponent(JpanalVMS, javax.swing.GroupLayout.PREFERRED_SIZE, 685, Short.MAX_VALUE)
                .addContainerGap())
        );

        Marketing_mgt.addTab("VEHI", VehicleMgtSystem);

        Packages.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9_package.setText("PAK");

        jPanel19_package.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Package Management System", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jButton6_package.setText("Add Pacakge");
        jButton6_package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6_packageActionPerformed(evt);
            }
        });

        jButton5_package.setText("Edit Packge");
        jButton5_package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5_packageActionPerformed(evt);
            }
        });

        packageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        packageTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                packageTableMouseClicked(evt);
            }
        });
        jScrollPane4_package.setViewportView(packageTable);

        jLabel25__package.setText("Search Type");

        stdPakAdvanceSearchJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Advance Search"));

        jLabel28.setText("Search By : ");

        chk_StdPakAdvSearchPackName.setText("Package Name :");

        chk_StdPakAdvSearchVehiType.setText("Vehicle Type :");

        chk_StdPakAdvSearchPakPrice.setText("Price :");

        btn_StdPakAdvSearch.setText("Search");
        btn_StdPakAdvSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StdPakAdvSearchActionPerformed(evt);
            }
        });

        chk_StdPakAdvSearchKmLimit.setText("Km Limit :");

        p_cmb_vehicleType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Select Vehicle Type-", "Car", "Van(Front Ac)", "Van(Duel Ac)", "Van(Non Ac)", "Lorry AC", "Lorry Non-Ac " }));

        javax.swing.GroupLayout stdPakAdvanceSearchJPanelLayout = new javax.swing.GroupLayout(stdPakAdvanceSearchJPanel);
        stdPakAdvanceSearchJPanel.setLayout(stdPakAdvanceSearchJPanelLayout);
        stdPakAdvanceSearchJPanelLayout.setHorizontalGroup(
            stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btn_StdPakAdvSearch))
                    .addGroup(stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk_StdPakAdvSearchPackName)
                                    .addComponent(chk_StdPakAdvSearchVehiType)
                                    .addComponent(chk_StdPakAdvSearchPakPrice))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(chk_StdPakAdvSearchKmLimit, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_StdPakAdvSearchKmLimit, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_StdPakAdvSearchPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_StdPakAdvSearchPakName, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(p_cmb_vehicleType, 0, 157, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel28)
                        .addGap(81, 81, 81)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stdPakAdvanceSearchJPanelLayout.setVerticalGroup(
            stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdPakAdvanceSearchJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGap(49, 49, 49)
                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_StdPakAdvSearchPackName)
                    .addComponent(txt_StdPakAdvSearchPakName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_StdPakAdvSearchVehiType)
                    .addComponent(p_cmb_vehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_StdPakAdvSearchPakPrice)
                    .addComponent(txt_StdPakAdvSearchPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(stdPakAdvanceSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_StdPakAdvSearchKmLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_StdPakAdvSearchKmLimit))
                .addGap(18, 18, 18)
                .addComponent(btn_StdPakAdvSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmb_stdPakSearchType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please Select", "Quick", "Advance" }));
        cmb_stdPakSearchType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_stdPakSearchTypeItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout stdPakQuickSearchJPanelLayout = new javax.swing.GroupLayout(stdPakQuickSearchJPanel);
        stdPakQuickSearchJPanel.setLayout(stdPakQuickSearchJPanelLayout);
        stdPakQuickSearchJPanelLayout.setHorizontalGroup(
            stdPakQuickSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdPakQuickSearchJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_StdPakQuickSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );
        stdPakQuickSearchJPanelLayout.setVerticalGroup(
            stdPakQuickSearchJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stdPakQuickSearchJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_StdPakQuickSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton7__package.setText("Delete");
        jButton7__package.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7__packageActionPerformed(evt);
            }
        });

        btn_PakReset.setText("Reset");
        btn_PakReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PakResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19_packageLayout = new javax.swing.GroupLayout(jPanel19_package);
        jPanel19_package.setLayout(jPanel19_packageLayout);
        jPanel19_packageLayout.setHorizontalGroup(
            jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19_packageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19_packageLayout.createSequentialGroup()
                        .addComponent(stdPakQuickSearchJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel25__package)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_stdPakSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4_package, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stdPakAdvanceSearchJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19_packageLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5_package, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6_package, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7__package, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_PakReset, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19_packageLayout.setVerticalGroup(
            jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19_packageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25__package)
                        .addComponent(cmb_stdPakSearchType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stdPakAdvanceSearchJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton6_package, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5_package, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7__package, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_PakReset, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel19_packageLayout.createSequentialGroup()
                .addComponent(stdPakQuickSearchJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4_package, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 415, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11_packageLayout = new javax.swing.GroupLayout(jPanel11_package);
        jPanel11_package.setLayout(jPanel11_packageLayout);
        jPanel11_packageLayout.setHorizontalGroup(
            jPanel11_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11_packageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19_package, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );
        jPanel11_packageLayout.setVerticalGroup(
            jPanel11_packageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11_packageLayout.createSequentialGroup()
                .addComponent(jPanel19_package, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PackagesLayout = new javax.swing.GroupLayout(Packages);
        Packages.setLayout(PackagesLayout);
        PackagesLayout.setHorizontalGroup(
            PackagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PackagesLayout.createSequentialGroup()
                .addContainerGap(925, Short.MAX_VALUE)
                .addComponent(jLabel9_package)
                .addGap(219, 219, 219))
            .addComponent(jPanel11_package, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PackagesLayout.setVerticalGroup(
            PackagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PackagesLayout.createSequentialGroup()
                .addComponent(jPanel11_package, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9_package)
                .addGap(178, 178, 178))
        );

        Marketing_mgt.addTab("PACK", Packages);

        GennCusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search General Customers"));

        lbl_searchGeneralCustomer.setText("Search General Customers");

        SearchGenCusR.add(SrchByFnameR);
        SrchByFnameR.setSelected(true);
        SrchByFnameR.setText("Search by name");

        SearchGenCusR.add(SrchByNICR);
        SrchByNICR.setText("Search by NIC");

        SearchGenCusR.add(SrchByAddR);
        SrchByAddR.setText("Search by Address");

        CusSrchGenB.setText("Search");
        CusSrchGenB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CusSrchGenBActionPerformed(evt);
            }
        });

        SrchRGenTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "First Name", "Second Name", "NIC", "E mail", "Address", "Phone 1", "Phone 2"
            }
        ));
        SrchGenCusScrollPane.setViewportView(SrchRGenTable);

        javax.swing.GroupLayout GennCusPanelLayout = new javax.swing.GroupLayout(GennCusPanel);
        GennCusPanel.setLayout(GennCusPanelLayout);
        GennCusPanelLayout.setHorizontalGroup(
            GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GennCusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GennCusPanelLayout.createSequentialGroup()
                        .addGroup(GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GennCusPanelLayout.createSequentialGroup()
                                .addComponent(SrchByFnameR)
                                .addGap(54, 54, 54))
                            .addGroup(GennCusPanelLayout.createSequentialGroup()
                                .addGroup(GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SrchByNICR)
                                    .addComponent(SrchByAddR))
                                .addGap(42, 42, 42)))
                        .addGap(1, 1, 1)
                        .addComponent(SrchByGen, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_searchGeneralCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CusSrchGenB))
                .addGap(18, 18, 18)
                .addComponent(SrchGenCusScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
        );
        GennCusPanelLayout.setVerticalGroup(
            GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GennCusPanelLayout.createSequentialGroup()
                .addGroup(GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(GennCusPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lbl_searchGeneralCustomer)
                        .addGap(18, 18, 18)
                        .addComponent(SrchByFnameR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GennCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SrchByGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SrchByNICR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SrchByAddR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CusSrchGenB)
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(GennCusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SrchGenCusScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        CompanyPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Companies"));

        SearchCmpany.add(SrchByCNameR);
        SrchByCNameR.setSelected(true);
        SrchByCNameR.setText("Search by Company name");

        SearchCmpany.add(SrchByCType);
        SrchByCType.setText("Search by company type");

        lbl_searchCompanies.setText("Search Companies");

        SrchRCmpanyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Company Name", "Contact person", "Type", "Address", "Email", "Phone 1", "Phone 2", "Total Revenue"
            }
        ));
        SrchCmpanyScrollPane.setViewportView(SrchRCmpanyTable);

        SrchCmpanyB.setText("Search");
        SrchCmpanyB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SrchCmpanyBActionPerformed(evt);
            }
        });

        CTypeSearchCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select", "Government", "Private", " " }));

        javax.swing.GroupLayout CompanyPanelLayout = new javax.swing.GroupLayout(CompanyPanel);
        CompanyPanel.setLayout(CompanyPanelLayout);
        CompanyPanelLayout.setHorizontalGroup(
            CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CompanyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_searchCompanies)
                    .addGroup(CompanyPanelLayout.createSequentialGroup()
                        .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CompanyPanelLayout.createSequentialGroup()
                                .addComponent(SrchByCNameR)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(CompanyPanelLayout.createSequentialGroup()
                                .addComponent(SrchByCType)
                                .addGap(8, 8, 8)))
                        .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CTypeSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SrchByCnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(SrchCmpanyB))
                .addGap(18, 18, 18)
                .addComponent(SrchCmpanyScrollPane)
                .addContainerGap())
        );
        CompanyPanelLayout.setVerticalGroup(
            CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CompanyPanelLayout.createSequentialGroup()
                .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CompanyPanelLayout.createSequentialGroup()
                        .addComponent(lbl_searchCompanies, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addGap(1, 1, 1)
                        .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SrchByCNameR)
                            .addComponent(SrchByCnameT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(CompanyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SrchByCType)
                            .addComponent(CTypeSearchCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SrchCmpanyB)
                        .addGap(12, 12, 12))
                    .addGroup(CompanyPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(SrchCmpanyScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        OnlineCusPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Online Customers"));

        Customer_SOnilineL.setText("Search Online Customer");

        SearchOnlineCus.add(OnlineSbyNameRB);
        OnlineSbyNameRB.setSelected(true);
        OnlineSbyNameRB.setText("Search By name");

        SearchOnlineCus.add(OnlineSByUnameRB);
        OnlineSByUnameRB.setText("Search by username");

        SearchOnlineCus.add(OnlineSByNICRB);
        OnlineSByNICRB.setText("Search by NIC");

        SrchROnlineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        srchOnlineScrollPane.setViewportView(SrchROnlineTable);

        SrchOnlineB.setText("Search");
        SrchOnlineB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SrchOnlineBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OnlineCusPanelLayout = new javax.swing.GroupLayout(OnlineCusPanel);
        OnlineCusPanel.setLayout(OnlineCusPanelLayout);
        OnlineCusPanelLayout.setHorizontalGroup(
            OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OnlineCusPanelLayout.createSequentialGroup()
                .addGroup(OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OnlineCusPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(Customer_SOnilineL))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OnlineCusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(OnlineSByUnameRB)
                            .addComponent(OnlineSByNICRB)
                            .addComponent(OnlineSbyNameRB)
                            .addComponent(SrchOnlineB))))
                .addGap(5, 5, 5)
                .addComponent(SrchonlineparmT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 51, Short.MAX_VALUE)
                .addComponent(srchOnlineScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        OnlineCusPanelLayout.setVerticalGroup(
            OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OnlineCusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(srchOnlineScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(OnlineCusPanelLayout.createSequentialGroup()
                        .addComponent(Customer_SOnilineL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OnlineSbyNameRB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(OnlineCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(OnlineCusPanelLayout.createSequentialGroup()
                                .addComponent(OnlineSByUnameRB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(OnlineSByNICRB))
                            .addComponent(SrchonlineparmT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SrchOnlineB)
                        .addGap(26, 26, 26)))
                .addGap(5, 5, 5))
        );

        Customer_SearchCusL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Customer_SearchCusL.setText("Search Customers");

        Customer_AddB.setText("Register/Update Customer");
        Customer_AddB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Customer_AddBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CustomerMgtSystemLayout = new javax.swing.GroupLayout(CustomerMgtSystem);
        CustomerMgtSystem.setLayout(CustomerMgtSystemLayout);
        CustomerMgtSystemLayout.setHorizontalGroup(
            CustomerMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerMgtSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Customer_SearchCusL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Customer_AddB, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(CustomerMgtSystemLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(CustomerMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CompanyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OnlineCusPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GennCusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        CustomerMgtSystemLayout.setVerticalGroup(
            CustomerMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerMgtSystemLayout.createSequentialGroup()
                .addGroup(CustomerMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CustomerMgtSystemLayout.createSequentialGroup()
                        .addComponent(Customer_SearchCusL)
                        .addGap(25, 25, 25))
                    .addComponent(Customer_AddB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GennCusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(OnlineCusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CompanyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );

        Marketing_mgt.addTab("CUSTOMERS", CustomerMgtSystem);

        ReservationMgetSystem.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jsp.setBorder(javax.swing.BorderFactory.createTitledBorder("Reservations"));

        tableReservation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableReservation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReservationMouseClicked(evt);
            }
        });
        jsp.setViewportView(tableReservation);

        ReservationMgetSystem.add(jsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 960, 520));
        jsp.getAccessibleContext().setAccessibleName("");

        LBL_orderBy.setText("Order By :");
        ReservationMgetSystem.add(LBL_orderBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmb_resSearchOrderBy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Reservation ID", "Reservation Start Date", "Reservation End Date", "Customer Name", "Package Type ID", "Vehicle Count", " " }));
        cmb_resSearchOrderBy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmb_resSearchOrderBy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_resSearchOrderByItemStateChanged(evt);
            }
        });
        ReservationMgetSystem.add(cmb_resSearchOrderBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 18, 151, -1));

        txt_reservation_serarch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_reservation_serarchActionPerformed(evt);
            }
        });
        ReservationMgetSystem.add(txt_reservation_serarch, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 18, 280, -1));

        jpanAdvancedSearch.setBorder(javax.swing.BorderFactory.createTitledBorder("Advance Search"));

        CHKB_customer.setText("Customer Name");

        CMB_noOfVehicals.setText("No Of Vehicals");
        CMB_noOfVehicals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CMB_noOfVehicalsActionPerformed(evt);
            }
        });

        CHKB_startDate.setText(" Start Date :");

        CHKB_endDate.setText("End Date   :");
        CHKB_endDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CHKB_endDateActionPerformed(evt);
            }
        });

        CHKB_reservationID.setText("Reservation ID :");

        BTN_ADsearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Search-icon.png"))); // NOI18N
        BTN_ADsearch.setText("Search");
        BTN_ADsearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_ADsearch.setPreferredSize(new java.awt.Dimension(123, 27));
        BTN_ADsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ADsearchActionPerformed(evt);
            }
        });

        CHKB_status.setText("Status :");

        CMBO_searchStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Pending", "Overdue", "Complete", "Canceled" }));

        DATEPICKER_searchStartDate1.setDateFormatString("yyyy-MM-dd");

        DATEPICKER_searchStarttDate2.setDateFormatString("yyyy-MM-dd");

        LBL_between2.setText("BETWEEN");

        DATEPICKER_searchEndDate1.setDateFormatString("yyyy-MM-dd");

        DATEPICKER_searchEndDate2.setDateFormatString("yyyy-MM-dd");

        LBL_between3.setText("BETWEEN");

        javax.swing.GroupLayout jpanAdvancedSearchLayout = new javax.swing.GroupLayout(jpanAdvancedSearch);
        jpanAdvancedSearch.setLayout(jpanAdvancedSearchLayout);
        jpanAdvancedSearchLayout.setHorizontalGroup(
            jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addComponent(CHKB_customer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTB_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addComponent(CHKB_reservationID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTB_reservationID)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addComponent(CMB_noOfVehicals)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TXTB_noOfVehicals, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addComponent(CHKB_status)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CMBO_searchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CHKB_startDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CHKB_endDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DATEPICKER_searchStartDate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DATEPICKER_searchEndDate1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addComponent(LBL_between1)
                        .addGap(8, 8, 8)
                        .addComponent(LBL_between2))
                    .addComponent(LBL_between3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DATEPICKER_searchEndDate2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(DATEPICKER_searchStarttDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(BTN_ADsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        jpanAdvancedSearchLayout.setVerticalGroup(
            jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DATEPICKER_searchStartDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(CHKB_customer)
                                        .addComponent(TXTB_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CMB_noOfVehicals)
                                        .addComponent(TXTB_noOfVehicals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CHKB_startDate)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanAdvancedSearchLayout.createSequentialGroup()
                                .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(DATEPICKER_searchStarttDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jpanAdvancedSearchLayout.createSequentialGroup()
                                        .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LBL_between2)
                                            .addComponent(LBL_between1))
                                        .addGap(4, 4, 4)))
                                .addGap(10, 10, 10)))
                        .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DATEPICKER_searchEndDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LBL_between3)
                            .addGroup(jpanAdvancedSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(CHKB_reservationID)
                                .addComponent(TXTB_reservationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CHKB_status)
                                .addComponent(CMBO_searchStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CHKB_endDate)
                                .addComponent(DATEPICKER_searchEndDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(BTN_ADsearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        ReservationMgetSystem.add(jpanAdvancedSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 45, 1120, 100));

        btn_add_reservaion.setText("Add Reservation");
        btn_add_reservaion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add_reservaion.setPreferredSize(new java.awt.Dimension(140, 25));
        btn_add_reservaion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_reservaionActionPerformed(evt);
            }
        });
        ReservationMgetSystem.add(btn_add_reservaion, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 170, -1, 50));

        btn_edit_reservation.setText("Edit Reservation");
        btn_edit_reservation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit_reservation.setPreferredSize(new java.awt.Dimension(140, 25));
        btn_edit_reservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_edit_reservationActionPerformed(evt);
            }
        });
        ReservationMgetSystem.add(btn_edit_reservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 230, 140, 50));

        LBL_searchType.setText("Search Type :");
        ReservationMgetSystem.add(LBL_searchType, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 20, -1, -1));

        com_SearchType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quick", "Advaned" }));
        com_SearchType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        com_SearchType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                com_SearchTypeItemStateChanged(evt);
            }
        });
        ReservationMgetSystem.add(com_SearchType, new org.netbeans.lib.awtextra.AbsoluteConstraints(305, 18, 87, -1));

        LBL_tableFilter.setText("Table Fast Filter ->");
        ReservationMgetSystem.add(LBL_tableFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 20, -1, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setText("RESERVATIONS");
        ReservationMgetSystem.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 350, -1));

        Marketing_mgt.addTab("RESERVATIONS", ReservationMgetSystem);

        promotionAndDealsSubJpanel_01.setBorder(javax.swing.BorderFactory.createTitledBorder("Promotions and Deals"));

        PjBUpload.setText("Upload");
        PjBUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBUploadActionPerformed(evt);
            }
        });

        PDiscount.setText("Discount (%) :");

        PDescriotionText.setDragEnabled(true);
        jScrollPane2.setViewportView(PDescriotionText);

        PjBDelete.setText("Delete");
        PjBDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBDeleteActionPerformed(evt);
            }
        });

        PDescription.setText("Description :");

        PName.setText("Name :");

        PjBEdit.setText("Update");
        PjBEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBEditActionPerformed(evt);
            }
        });

        PValidDateTo.setText("From Date :");

        PName4.setText("Picture :");

        PjBAdd.setText("Add");
        PjBAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBAddActionPerformed(evt);
            }
        });

        PNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameTextActionPerformed(evt);
            }
        });

        PjBReset.setText("Reset");
        PjBReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBResetActionPerformed(evt);
            }
        });

        PValidDate2.setText("To Date :");

        javax.swing.GroupLayout promotionAndDealsSubJpanel_01Layout = new javax.swing.GroupLayout(promotionAndDealsSubJpanel_01);
        promotionAndDealsSubJpanel_01.setLayout(promotionAndDealsSubJpanel_01Layout);
        promotionAndDealsSubJpanel_01Layout.setHorizontalGroup(
            promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PjLableID)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                            .addComponent(PValidDate2)
                            .addContainerGap(387, Short.MAX_VALUE))
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                            .addComponent(PValidDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                                .addComponent(PName)
                                .addGap(61, 61, 61)
                                .addComponent(PNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(PName4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(PPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PjBUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                                    .addComponent(PDescription)
                                    .addGap(35, 35, 35)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                                    .addComponent(PDiscount)
                                    .addGap(25, 25, 25)
                                    .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(PJDateChooserTo, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                                        .addComponent(PJDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(PDiscountText, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))))
                            .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(PjBAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PjBEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PjBDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PjBReset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        promotionAndDealsSubJpanel_01Layout.setVerticalGroup(
            promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PName)
                    .addComponent(PNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PDescription)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(PjLableID))
                    .addGroup(promotionAndDealsSubJpanel_01Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PDiscount)
                            .addComponent(PDiscountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PValidDateTo)
                            .addComponent(PJDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PValidDate2)
                            .addComponent(PJDateChooserTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PPicture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PName4)
                            .addComponent(PjBUpload))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(promotionAndDealsSubJpanel_01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PjBAdd)
                    .addComponent(PjBEdit)
                    .addComponent(PjBDelete)
                    .addComponent(PjBReset))
                .addGap(20, 20, 20))
        );

        promotionAndDealsSubJpanel_02.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Promotion and Deals"));

        PSearchName.setText("Promition Name :");

        PSearchBtn.setText("Search Promotion");
        PSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout promotionAndDealsSubJpanel_02Layout = new javax.swing.GroupLayout(promotionAndDealsSubJpanel_02);
        promotionAndDealsSubJpanel_02.setLayout(promotionAndDealsSubJpanel_02Layout);
        promotionAndDealsSubJpanel_02Layout.setHorizontalGroup(
            promotionAndDealsSubJpanel_02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, promotionAndDealsSubJpanel_02Layout.createSequentialGroup()
                .addGroup(promotionAndDealsSubJpanel_02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(promotionAndDealsSubJpanel_02Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PSearchBtn))
                    .addGroup(promotionAndDealsSubJpanel_02Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(PSearchName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(PSearchNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60))
        );
        promotionAndDealsSubJpanel_02Layout.setVerticalGroup(
            promotionAndDealsSubJpanel_02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promotionAndDealsSubJpanel_02Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(promotionAndDealsSubJpanel_02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PSearchName)
                    .addComponent(PSearchNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(PSearchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        promotionAndDealsSubJpanel_03.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));

        PjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PjTableMouseClicked(evt);
            }
        });
        promotionAndDealsSubJScrollpanel_03.setViewportView(PjTable);

        javax.swing.GroupLayout promotionAndDealsSubJpanel_03Layout = new javax.swing.GroupLayout(promotionAndDealsSubJpanel_03);
        promotionAndDealsSubJpanel_03.setLayout(promotionAndDealsSubJpanel_03Layout);
        promotionAndDealsSubJpanel_03Layout.setHorizontalGroup(
            promotionAndDealsSubJpanel_03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, promotionAndDealsSubJpanel_03Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(promotionAndDealsSubJScrollpanel_03)
                .addContainerGap())
        );
        promotionAndDealsSubJpanel_03Layout.setVerticalGroup(
            promotionAndDealsSubJpanel_03Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, promotionAndDealsSubJpanel_03Layout.createSequentialGroup()
                .addComponent(promotionAndDealsSubJScrollpanel_03, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout promotionAndDealsMainJpanelLayout = new javax.swing.GroupLayout(promotionAndDealsMainJpanel);
        promotionAndDealsMainJpanel.setLayout(promotionAndDealsMainJpanelLayout);
        promotionAndDealsMainJpanelLayout.setHorizontalGroup(
            promotionAndDealsMainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promotionAndDealsMainJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(promotionAndDealsMainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(promotionAndDealsSubJpanel_03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(promotionAndDealsMainJpanelLayout.createSequentialGroup()
                        .addComponent(promotionAndDealsSubJpanel_01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(promotionAndDealsSubJpanel_02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        promotionAndDealsMainJpanelLayout.setVerticalGroup(
            promotionAndDealsMainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(promotionAndDealsMainJpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(promotionAndDealsMainJpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(promotionAndDealsSubJpanel_01, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(promotionAndDealsSubJpanel_02, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(promotionAndDealsSubJpanel_03, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Promitions & Deals", promotionAndDealsMainJpanel);

        Marketing.setToolTipText("");

        Marketing_main_Jpanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Marketing Campaigns"));

        Marketing_sub_Jpanel_1.setBorder(javax.swing.BorderFactory.createTitledBorder("Marketing Items"));

        jLabel24.setText("Description :");

        jLabel25.setText("Type :");

        jLabel26.setText("Cost (Rs.) :");

        jLabel27.setText("Quantity at hand :");

        MICostText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MICostTextActionPerformed(evt);
            }
        });

        MIComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please Select", "Umbrellas", "Pen", "Diaries", "Flyers", "Keytags", "Posters", "Banners", "Handouts" }));
        MIComboBox.setToolTipText("");
        MIComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        MIAddBtn.setText("Add");
        MIAddBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MIAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIAddBtnActionPerformed(evt);
            }
        });

        MIEditBtn.setText("Edit");
        MIEditBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MIEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIEditBtnActionPerformed(evt);
            }
        });

        MIDeleteBtn.setText("Delete");
        MIDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MIDeleteBtnActionPerformed(evt);
            }
        });

        MItemsDesc.setColumns(20);
        MItemsDesc.setLineWrap(true);
        MItemsDesc.setRows(5);
        Marketing_JscrollPane_1.setViewportView(MItemsDesc);

        Marketing_JscrollPane_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Marketing_JscrollPane_2MouseClicked(evt);
            }
        });

        MIjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, ""},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MIjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MIjTableMouseClicked(evt);
            }
        });
        Marketing_JscrollPane_2.setViewportView(MIjTable);

        PjBReset1.setText("Reset");
        PjBReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PjBReset1ActionPerformed(evt);
            }
        });

        MIID.setText("jL");

        javax.swing.GroupLayout Marketing_sub_Jpanel_1Layout = new javax.swing.GroupLayout(Marketing_sub_Jpanel_1);
        Marketing_sub_Jpanel_1.setLayout(Marketing_sub_Jpanel_1Layout);
        Marketing_sub_Jpanel_1Layout.setHorizontalGroup(
            Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(MIID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 326, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addGap(0, 3, Short.MAX_VALUE)
                        .addComponent(Marketing_JscrollPane_2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(47, 47, 47)
                                .addComponent(MICostText, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(18, 18, 18)
                                .addComponent(MIQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(77, 77, 77)
                        .addComponent(MIComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(47, 47, 47)
                        .addComponent(Marketing_JscrollPane_1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                        .addComponent(MIAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MIEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(MIDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(PjBReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Marketing_sub_Jpanel_1Layout.setVerticalGroup(
            Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_sub_Jpanel_1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(MIComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Marketing_JscrollPane_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(43, 43, 43)
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(MICostText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(MIQtyText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(Marketing_sub_Jpanel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MIAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MIEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MIDeleteBtn)
                    .addComponent(PjBReset1))
                .addGap(18, 18, 18)
                .addComponent(MIID)
                .addGap(18, 18, 18)
                .addComponent(Marketing_JscrollPane_2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        Marketing_sub_Jpanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Campaigns"));

        MCNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCNameTextActionPerformed(evt);
            }
        });

        MCDescriptionText.setDragEnabled(true);
        Marketing_JscrollPane_3.setViewportView(MCDescriptionText);

        PDiscount2.setText("Cost (Rs)  :");

        PValidDate1.setText("Date :");

        PDiscount1.setText("Venue :");

        PDescription1.setText("Description :");

        PName1.setText("Name :");

        Marketing_JscrollPane_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Marketing_JscrollPane_4MouseClicked(evt);
            }
        });

        MCampTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MCampTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MCampTableMouseClicked(evt);
            }
        });
        Marketing_JscrollPane_4.setViewportView(MCampTable);

        MCAddBtn.setText("Add");
        MCAddBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MCAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCAddBtnActionPerformed(evt);
            }
        });

        MCEditBtn.setText("Edit");
        MCEditBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MCEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCEditBtnActionPerformed(evt);
            }
        });

        MCDeleteBtn.setText("Delete");
        MCDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCDeleteBtnActionPerformed(evt);
            }
        });

        MCjBReset.setText("Reset");
        MCjBReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCjBResetActionPerformed(evt);
            }
        });

        PDiscount3.setText("Select Items : ");

        jButton8.setText("Select");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        MCSearchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCSearchTextActionPerformed(evt);
            }
        });

        MCSearchLable.setText("Search : ");

        MCSearchBtn.setText("Search");
        MCSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCSearchBtnActionPerformed(evt);
            }
        });

        Marketing_JscrollPane_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Marketing_JscrollPane_5MouseClicked(evt);
            }
        });

        MCampTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MCampTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MCampTable1MouseClicked(evt);
            }
        });
        Marketing_JscrollPane_5.setViewportView(MCampTable1);

        Marketing_JscrollPane_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Marketing_JscrollPane_6MouseClicked(evt);
            }
        });

        MCampTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        MCampTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MCampTable2MouseClicked(evt);
            }
        });
        Marketing_JscrollPane_6.setViewportView(MCampTable2);

        javax.swing.GroupLayout Marketing_sub_Jpanel2Layout = new javax.swing.GroupLayout(Marketing_sub_Jpanel2);
        Marketing_sub_Jpanel2.setLayout(Marketing_sub_Jpanel2Layout);
        Marketing_sub_Jpanel2Layout.setHorizontalGroup(
            Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                                .addComponent(PDiscount2)
                                                .addGap(40, 40, 40)
                                                .addComponent(MCCost, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(PDiscount3)
                                                    .addComponent(PValidDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(26, 26, 26)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(68, 68, 68))
                                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(MCDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(PDiscount1)
                                                    .addComponent(PDescription1)
                                                    .addComponent(PName1))
                                                .addGap(35, 35, 35)
                                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                                        .addComponent(MCNameText)
                                                        .addGap(1, 1, 1))
                                                    .addComponent(Marketing_JscrollPane_3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(MVenueText, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(24, 24, 24)
                                        .addComponent(MCSearchLable, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(MCSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MCSearchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(Marketing_JscrollPane_4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                                    .addComponent(Marketing_JscrollPane_6, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Marketing_JscrollPane_5, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(MCAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MCEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MCDeleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MCjBReset, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Marketing_sub_Jpanel2Layout.setVerticalGroup(
            Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MCNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PName1)
                    .addComponent(MCSearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MCSearchLable))
                .addGap(20, 20, 20)
                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PDescription1)
                            .addComponent(Marketing_JscrollPane_3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(PDiscount1))
                            .addComponent(MVenueText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PValidDate1)
                            .addComponent(MCDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(PDiscount3))
                            .addComponent(jButton8)))
                    .addComponent(MCSearchBtn))
                .addGap(18, 18, 18)
                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(PDiscount2))
                    .addComponent(MCCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Marketing_JscrollPane_5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Marketing_sub_Jpanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(Marketing_sub_Jpanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MCAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MCEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MCDeleteBtn)
                            .addComponent(MCjBReset))
                        .addGap(36, 36, 36)
                        .addComponent(Marketing_JscrollPane_4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(Marketing_JscrollPane_6, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout Marketing_main_JpanelLayout = new javax.swing.GroupLayout(Marketing_main_Jpanel);
        Marketing_main_Jpanel.setLayout(Marketing_main_JpanelLayout);
        Marketing_main_JpanelLayout.setHorizontalGroup(
            Marketing_main_JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_main_JpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Marketing_sub_Jpanel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Marketing_sub_Jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 695, Short.MAX_VALUE))
        );
        Marketing_main_JpanelLayout.setVerticalGroup(
            Marketing_main_JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Marketing_main_JpanelLayout.createSequentialGroup()
                .addComponent(Marketing_sub_Jpanel_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Marketing_main_JpanelLayout.createSequentialGroup()
                .addComponent(Marketing_sub_Jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout MarketingLayout = new javax.swing.GroupLayout(Marketing);
        Marketing.setLayout(MarketingLayout);
        MarketingLayout.setHorizontalGroup(
            MarketingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MarketingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Marketing_main_Jpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MarketingLayout.setVerticalGroup(
            MarketingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MarketingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Marketing_main_Jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Marketing Campaings", Marketing);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        Marketing_mgt.addTab("MARKETING", jPanel9);

        jPanel13_driver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13_driverMouseClicked(evt);
            }
        });

        jPanel14_driver.setBorder(javax.swing.BorderFactory.createTitledBorder("Details"));
        jPanel14_driver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14_driverMouseClicked(evt);
            }
        });
        jPanel14_driver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        driver_add_jbutton.setText("Add");
        driver_add_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_add_jbuttonActionPerformed(evt);
            }
        });
        jPanel14_driver.add(driver_add_jbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 85, -1));

        driver_picture.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driver_pictureMouseClicked(evt);
            }
        });
        jPanel14_driver.add(driver_picture, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 145, -1));

        driver_availalibity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Not Available" }));
        jPanel14_driver.add(driver_availalibity, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 145, -1));

        driver_phone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                driver_phoneFocusLost(evt);
            }
        });
        jPanel14_driver.add(driver_phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 145, -1));

        driver_nic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                driver_nicFocusLost(evt);
            }
        });
        driver_nic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                driver_nicMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                driver_nicMouseReleased(evt);
            }
        });
        driver_nic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_nicActionPerformed(evt);
            }
        });
        jPanel14_driver.add(driver_nic, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 145, -1));
        jPanel14_driver.add(driver_licenceNO, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 145, -1));
        jPanel14_driver.add(driver_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 145, -1));

        jLabel27_driver.setText("Address");
        jPanel14_driver.add(jLabel27_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 118, 50, -1));

        jLabel28_driver.setText("Driving licence Number");
        jPanel14_driver.add(jLabel28_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 165, -1, -1));

        jLabel25_driver.setText("NIC");
        jPanel14_driver.add(jLabel25_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 220, 35, -1));

        jLabel26_driver.setText("Phone");
        jPanel14_driver.add(jLabel26_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 269, 50, -1));

        jLabel29_driver.setText("Avalibility");
        jPanel14_driver.add(jLabel29_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 326, 63, -1));

        jLabel30_driver.setText("picture");
        jPanel14_driver.add(jLabel30_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 399, -1, -1));

        driver_update_jbutton.setText("Update");
        driver_update_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_update_jbuttonActionPerformed(evt);
            }
        });
        jPanel14_driver.add(driver_update_jbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 85, -1));

        driver_remove_jbutton.setText("Remove");
        driver_remove_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_remove_jbuttonActionPerformed(evt);
            }
        });
        jPanel14_driver.add(driver_remove_jbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 560, 85, -1));

        driver_reset_fields.setText("Refresh");
        driver_reset_fields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_reset_fieldsActionPerformed(evt);
            }
        });
        jPanel14_driver.add(driver_reset_fields, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        jLabel23_driver.setText("First Name");
        jPanel14_driver.add(jLabel23_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 30, -1, -1));
        jPanel14_driver.add(driver_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 145, -1));
        jPanel14_driver.add(driver_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 145, -1));

        jLabel24_driver.setText("Last Name");
        jPanel14_driver.add(jLabel24_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 71, -1, -1));

        driver_view_jtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Driver Id", "First Name", "Last Name", "NIC", "Address", "Phone", "Driving Licence Number", "Availability", "Joined Date"
            }
        ));
        driver_view_jtable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                driver_view_jtableFocusLost(evt);
            }
        });
        driver_view_jtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driver_view_jtableMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                driver_view_jtableMouseExited(evt);
            }
        });
        jScrollPane3_driver.setViewportView(driver_view_jtable);

        driver_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                driver_searchKeyReleased(evt);
            }
        });

        jLabel31_driver.setText("NIC");

        driver_view_all.setText("View All");
        driver_view_all.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driver_view_allActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13_driverLayout = new javax.swing.GroupLayout(jPanel13_driver);
        jPanel13_driver.setLayout(jPanel13_driverLayout);
        jPanel13_driverLayout.setHorizontalGroup(
            jPanel13_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13_driverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14_driver, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel13_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13_driverLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel31_driver, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(driver_search, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(driver_view_all, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))
                    .addGroup(jPanel13_driverLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3_driver, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))))
        );
        jPanel13_driverLayout.setVerticalGroup(
            jPanel13_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13_driverLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel13_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13_driverLayout.createSequentialGroup()
                        .addComponent(jPanel14_driver, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(jPanel13_driverLayout.createSequentialGroup()
                        .addGroup(jPanel13_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(driver_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31_driver)
                            .addComponent(driver_view_all))
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane3_driver, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane2_DRIVER.addTab("Overview", jPanel13_driver);

        jPanel11_DRIVER.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15_driver.setBorder(javax.swing.BorderFactory.createTitledBorder("leave details"));
        jPanel15_driver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32_driver.setText("NIC");
        jPanel15_driver.add(jLabel32_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 55, 32, -1));
        jPanel15_driver.add(jTextField8_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 52, 169, -1));

        jLabel33_driver.setText("Name");
        jPanel15_driver.add(jLabel33_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 112, -1, -1));
        jPanel15_driver.add(jTextField9_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 109, 169, -1));

        jLabel34_driver.setText("From");
        jPanel15_driver.add(jLabel34_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 185, -1, -1));

        jLabel35_driver.setText("To");
        jPanel15_driver.add(jLabel35_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 257, 32, -1));

        jLabel36_driver.setText("Description");
        jPanel15_driver.add(jLabel36_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 330, -1, -1));

        jTextArea1_driver.setColumns(20);
        jTextArea1_driver.setRows(5);
        jScrollPane2_driver.setViewportView(jTextArea1_driver);

        jPanel15_driver.add(jScrollPane2_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 330, -1, -1));

        jButton5_driver.setText("Submit");
        jButton5_driver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5_driverActionPerformed(evt);
            }
        });
        jPanel15_driver.add(jButton5_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 510, -1, -1));

        jPanel11_DRIVER.add(jPanel15_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 300, 560));

        jPanel17_driver.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4_driver.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel17_driverLayout = new javax.swing.GroupLayout(jPanel17_driver);
        jPanel17_driver.setLayout(jPanel17_driverLayout);
        jPanel17_driverLayout.setHorizontalGroup(
            jPanel17_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17_driverLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane4_driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel17_driverLayout.setVerticalGroup(
            jPanel17_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17_driverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4_driver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jPanel11_DRIVER.add(jPanel17_driver, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 19, -1, -1));

        jTabbedPane2_DRIVER.addTab("Manage Leaves", jPanel11_DRIVER);

        javax.swing.GroupLayout jPanel12_driverLayout = new javax.swing.GroupLayout(jPanel12_driver);
        jPanel12_driver.setLayout(jPanel12_driverLayout);
        jPanel12_driverLayout.setHorizontalGroup(
            jPanel12_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        jPanel12_driverLayout.setVerticalGroup(
            jPanel12_driverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        jTabbedPane2_DRIVER.addTab("Salary", jPanel12_driver);
        jTabbedPane2_DRIVER.addTab("view profile", jTabbedPane3_driver);

        javax.swing.GroupLayout Driver_MgtSystemLayout = new javax.swing.GroupLayout(Driver_MgtSystem);
        Driver_MgtSystem.setLayout(Driver_MgtSystemLayout);
        Driver_MgtSystemLayout.setHorizontalGroup(
            Driver_MgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2_DRIVER)
        );
        Driver_MgtSystemLayout.setVerticalGroup(
            Driver_MgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2_DRIVER)
        );

        Marketing_mgt.addTab("Drivers", Driver_MgtSystem);

        JUtilityPanel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        JUtilityPanel_main.add(jtblno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 320, -1));

        jlbillNo.setText(" Bill No");
        JUtilityPanel_main.add(jlbillNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 64, 50, 20));

        uIDlbl.setText("UtiliyID");
        JUtilityPanel_main.add(uIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 50, -1));

        jbBillSearch.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbBillSearch.setText("Search");
        jbBillSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBillSearchActionPerformed(evt);
            }
        });
        JUtilityPanel_main.add(jbBillSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 80, -1));

        ibBillDelete.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ibBillDelete.setText("Delete");
        ibBillDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ibBillDeleteActionPerformed(evt);
            }
        });
        JUtilityPanel_main.add(ibBillDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 80, -1));

        jtutility.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BillName", "Location", "Utility", "Unit", "Period", "Amount"
            }
        ));
        JUtilityScorellPanel.setViewportView(jtutility);

        JUtilityPanel_main.add(JUtilityScorellPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 1080, 530));

        jbBillAdd.setText("Add");
        jbBillAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbBillAddActionPerformed(evt);
            }
        });
        JUtilityPanel_main.add(jbBillAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 80, -1));

        jTabbedPane2.addTab("UTILITY BILL HANDLING", JUtilityPanel_main);

        jPanel_Chque.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbchequeEdit.setText(" Edit");
        jbchequeEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbchequeEditActionPerformed(evt);
            }
        });
        jPanel_Chque.add(jbchequeEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 110, 20));

        jtcheque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Jscorellcheoue.setViewportView(jtcheque);

        jPanel_Chque.add(Jscorellcheoue, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 1070, 530));

        jbchequeSearch.setText("Search");
        jbchequeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbchequeSearchActionPerformed(evt);
            }
        });
        jPanel_Chque.add(jbchequeSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 82, 20));

        jbchequeDelete.setText("Delete");
        jbchequeDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbchequeDeleteActionPerformed(evt);
            }
        });
        jPanel_Chque.add(jbchequeDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 85, 20));

        jtche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtcheActionPerformed(evt);
            }
        });
        jtche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtcheKeyReleased(evt);
            }
        });
        jPanel_Chque.add(jtche, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 200, -1));

        jlchequeNo.setText("Cheque No : ");
        jPanel_Chque.add(jlchequeNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        jbchequeAdd.setText("Add");
        jbchequeAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbchequeAddActionPerformed(evt);
            }
        });
        jPanel_Chque.add(jbchequeAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 120, -1));

        jTabbedPane2.addTab("CHEQUE HANDLING", jPanel_Chque);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );

        Marketing_mgt.addTab("Finance ", jPanel1);

        lbl_eid_Employee.setText("Employee ID");

        lbl_NIC_Employee.setText("NIC");

        lbl_fname_Employee.setText("FName");

        lbl_lname_Employee.setText("LName");

        lbl_add_Employee.setText("Address");

        lbl_phn_Employee.setText("Phone");

        lbl_acc_Employee.setText("Bank Account No");

        lbl_description_Employee.setText("Description");

        lbl_post_Employee.setText("Post");

        lbl_pic_Employee.setText("Picture");

        txtf_NIC_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_NIC_EmployeeActionPerformed(evt);
            }
        });
        txtf_NIC_Employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_NIC_EmployeeKeyReleased(evt);
            }
        });

        txtf_fname_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_fname_EmployeeActionPerformed(evt);
            }
        });
        txtf_fname_Employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_fname_EmployeeKeyReleased(evt);
            }
        });

        txtf_lname_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_lname_EmployeeActionPerformed(evt);
            }
        });
        txtf_lname_Employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_lname_EmployeeKeyReleased(evt);
            }
        });

        txta_add_Employee.setColumns(20);
        txta_add_Employee.setRows(5);
        jScrollPane3.setViewportView(txta_add_Employee);

        txtf_phn_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_phn_EmployeeActionPerformed(evt);
            }
        });
        txtf_phn_Employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtf_phn_EmployeeKeyReleased(evt);
            }
        });

        txtf_acc_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_acc_EmployeeActionPerformed(evt);
            }
        });

        txta_des_Employee.setColumns(20);
        txta_des_Employee.setRows(5);
        jScrollPane4.setViewportView(txta_des_Employee);

        txtf_post_Empolyee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtf_post_EmpolyeeActionPerformed(evt);
            }
        });

        jLabel33.setText("jLabel33");

        btn_Add_Employee.setText("ADD");
        btn_Add_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Add_EmployeeActionPerformed(evt);
            }
        });

        btn_Upadate_Employee.setText("UPDATE");
        btn_Upadate_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Upadate_EmployeeActionPerformed(evt);
            }
        });

        btn_Remove_Employee.setText("REMOVE");
        btn_Remove_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Remove_EmployeeActionPerformed(evt);
            }
        });

        btn_Search_Employee.setText("SEARCH");
        btn_Search_Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Search_EmployeeActionPerformed(evt);
            }
        });

        tbl_Employee.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "employeeID", "NIC", "Fname", "Lname", "Address", "Phone", "Bankaccount", "Description", "post"
            }
        ));
        tbl_Employee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EmployeeMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_Employee);

        lbl_Eid_Employee.setText("Employee ID");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel34.setText("Employee");

        javax.swing.GroupLayout jp_EmployeeLayout = new javax.swing.GroupLayout(jp_Employee);
        jp_Employee.setLayout(jp_EmployeeLayout);
        jp_EmployeeLayout.setHorizontalGroup(
            jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_EmployeeLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                    .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_description_Employee)
                                        .addComponent(lbl_NIC_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_fname_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_lname_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_add_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                                    .addGap(183, 183, 183))
                                .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                    .addComponent(lbl_pic_Employee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(162, 162, 162)))
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbl_post_Employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_acc_Employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_phn_Employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(lbl_eid_Employee))
                                .addGap(162, 162, 162)))
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtf_phn_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtf_post_Empolyee, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtf_lname_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtf_NIC_Employee, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbl_Eid_Employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                        .addComponent(txtf_fname_Employee, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(txtf_acc_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(btn_Search_Employee)
                                .addGap(59, 59, 59)
                                .addComponent(search_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jp_EmployeeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addComponent(btn_Add_Employee)
                                .addGap(68, 68, 68)
                                .addComponent(btn_Upadate_Employee)
                                .addGap(82, 82, 82)
                                .addComponent(btn_Remove_Employee))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jp_EmployeeLayout.setVerticalGroup(
            jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel34)
                .addGap(3, 3, 3)
                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Search_Employee)
                    .addComponent(search_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_EmployeeLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_fname_Employee)
                            .addComponent(txtf_fname_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_lname_Employee)
                            .addComponent(txtf_lname_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addComponent(lbl_add_Employee)
                                .addGap(56, 56, 56)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_phn_Employee)
                                    .addComponent(txtf_phn_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_acc_Employee)
                                    .addComponent(txtf_acc_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_description_Employee)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lbl_post_Employee))
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtf_post_Empolyee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_pic_Employee)
                        .addGap(48, 48, 48)
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Add_Employee)
                            .addComponent(btn_Upadate_Employee)
                            .addComponent(btn_Remove_Employee))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jp_EmployeeLayout.createSequentialGroup()
                        .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(426, 426, 426)
                                .addComponent(jLabel33))
                            .addGroup(jp_EmployeeLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbl_eid_Employee)
                                    .addComponent(lbl_Eid_Employee))
                                .addGap(18, 18, 18)
                                .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp_EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbl_NIC_Employee)
                                        .addComponent(txtf_NIC_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(77, 163, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout EmployeeMgtSystemLayout = new javax.swing.GroupLayout(EmployeeMgtSystem);
        EmployeeMgtSystem.setLayout(EmployeeMgtSystemLayout);
        EmployeeMgtSystemLayout.setHorizontalGroup(
            EmployeeMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeMgtSystemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EmployeeMgtSystemLayout.setVerticalGroup(
            EmployeeMgtSystemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeMgtSystemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jp_Employee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Marketing_mgt.addTab("EMPLOYEE", EmployeeMgtSystem);

        getContentPane().add(Marketing_mgt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 1170, 710));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close_bt.png"))); // NOI18N
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 15, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_2.fw.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Marketing_mgt.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Marketing_mgt.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Marketing_mgt.setSelectedIndex(3);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        Marketing_mgt.setSelectedIndex(4);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Marketing_mgt.setSelectedIndex(5);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        Marketing_mgt.setSelectedIndex(6);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        Marketing_mgt.setSelectedIndex(7);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        Marketing_mgt.setSelectedIndex(8);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        Marketing_mgt.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        this.setState(main.ICONIFIED);
    }//GEN-LAST:event_jLabel17MouseClicked

    private void tbl_EmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EmployeeMouseClicked
        // TODO add your handling code here:
        int row = tbl_Employee.getSelectedRow();

        String jeid = tbl_Employee.getValueAt(row, 0).toString();
        String jnic = tbl_Employee.getValueAt(row, 1).toString();
        String jfname = tbl_Employee.getValueAt(row, 2).toString();
        String jlname = tbl_Employee.getValueAt(row, 3).toString();
        String jadd = tbl_Employee.getValueAt(row, 4).toString();
        String jphn = tbl_Employee.getValueAt(row, 5).toString();
        String jacc = tbl_Employee.getValueAt(row, 6).toString();
        String jdes = tbl_Employee.getValueAt(row, 7).toString();
        String jpost = tbl_Employee.getValueAt(row, 7).toString();

        lbl_Eid_Employee.setText(jeid);
        txtf_NIC_Employee.setText(jnic);
        txtf_fname_Employee.setText(jfname);
        txtf_lname_Employee.setText(jlname);
        txta_add_Employee.setText(jadd);
        txtf_phn_Employee.setText(jphn);
        txtf_acc_Employee.setText(jacc);
        txta_des_Employee.setText(jdes);
        txtf_post_Empolyee.setText(jpost);
    }//GEN-LAST:event_tbl_EmployeeMouseClicked

    private void btn_Search_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Search_EmployeeActionPerformed
        // TODO add your handling code here:
        String name =search_Employee.getText();
        String sql = "SELECT employee_ID,NIC,fname,lname,address,phone,bankAccountno,description,post from employee where fname like  '%"+ name +"%'";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            tbl_Employee.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
        }
        //   employeeTableLoad();
    }//GEN-LAST:event_btn_Search_EmployeeActionPerformed

    private void btn_Remove_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Remove_EmployeeActionPerformed
        // delete ,make var as x
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete....?");

        if(x==0)
        {
            String jeid_Employee = lbl_Eid_Employee.getText();
            String jnic_Employee = txtf_NIC_Employee.getText();
            String jfname_Employee = txtf_fname_Employee.getText();
            String jlname_Employee = txtf_lname_Employee.getText();
            String jadd_Employee = txta_add_Employee.getText();
            String jphn_Employee = txtf_phn_Employee.getText();
            String jacc_Employee = txtf_acc_Employee.getText();
            String jdes_Employee = txta_des_Employee.getText();
            String jpost_Employee = txtf_post_Empolyee.getText();

            Employee emp = new Employee(jeid_Employee,jnic_Employee,jfname_Employee,jlname_Employee,jadd_Employee,jphn_Employee,jacc_Employee,jdes_Employee,jpost_Employee);
            emp.removeEmployee();
            employeeTableLoad();
            JOptionPane.showMessageDialog(null, "This Empoyee is succfully Removed!!!");

            //delete only select id

        }
    }//GEN-LAST:event_btn_Remove_EmployeeActionPerformed

    private void btn_Upadate_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Upadate_EmployeeActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update....?");

        if(x==0)
        {
            String jeid_Employee = lbl_Eid_Employee.getText();
            String jnic_Employee = txtf_NIC_Employee.getText();
            String jfname_Employee = txtf_fname_Employee.getText();
            String jlname_Employee = txtf_lname_Employee.getText();
            String jadd_Employee = txta_add_Employee.getText();
            String jphn_Employee = txtf_phn_Employee.getText();
            String jacc_Employee = txtf_acc_Employee.getText();
            String jdes_Employee = txta_des_Employee.getText();
            String jpost_Employee = txtf_post_Empolyee.getText();

            if (jeid_Employee.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Enter Employee ID...!!!","error",0);
            }

            else if (jnic_Employee.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Enter Nic...!!!","error",0);
            }

            else if (jfname_Employee.isEmpty())
            {

                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee First Name field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else if (jlname_Employee.isEmpty())
            {

                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee Last Name field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else if (jphn_Employee.isEmpty())
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee Phone NO field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }

            else if (jadd_Employee.isEmpty())
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee Address field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else if (jacc_Employee.isEmpty())
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee AccountNo field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else if (jdes_Employee.isEmpty())
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee Description field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else if (jpost_Employee.isEmpty())
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Please Fill the Empoyee Post field  ","Field is empty",JOptionPane.WARNING_MESSAGE);

            }
            else{
                try
                {

                    // tableload
                    Employee emp = new Employee(jeid_Employee,jnic_Employee,jfname_Employee,jlname_Employee,jadd_Employee,jphn_Employee,jacc_Employee,jdes_Employee,jpost_Employee);
                    emp.updateEmployee();
                    employeeTableLoad();
                    JOptionPane.showMessageDialog(null, "This Empoyee is succfully Updated!!!");
                    lbl_Eid_Employee.setText(null);
                    txtf_NIC_Employee.setText(null);
                    txtf_fname_Employee.setText(null);
                    txtf_lname_Employee.setText(null);
                    txta_add_Employee.setText(null);
                    txtf_phn_Employee.setText(null);
                    txtf_acc_Employee.setText(null);
                    txta_des_Employee.setText(null);
                    txtf_post_Empolyee.setText(null);

                }
                catch (Exception e)
                {
                    System.out.print(e);

                }
            }
        }
    }//GEN-LAST:event_btn_Upadate_EmployeeActionPerformed

    private void btn_Add_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Add_EmployeeActionPerformed
        //int index = jgen.getSelectedIndex();

        String jeid_Employee = lbl_Eid_Employee.getText();
        String jnic_Employee = txtf_NIC_Employee.getText();
        String jfname_Employee = txtf_fname_Employee.getText();
        String jlname_Employee = txtf_lname_Employee.getText();
        String jadd_Employee = txta_add_Employee.getText();
        String jphn_Employee = txtf_phn_Employee.getText();
        String jacc_Employee = txtf_acc_Employee.getText();
        String jdes_Employee = txta_des_Employee.getText();
        String jpost_Employee = txtf_post_Empolyee.getText();

        String RegexNIC="^[0-9vV]+$";
        int NIClength =jnic_Employee.length();

        /* if (jeid_Employee.isEmpty())
        {

            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(this, "Enter Employee ID...!!!","error",0);
        }*/
        if (jnic_Employee.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Enter Nic...!!!","error",0);
        }

        else if (NIClength > 10) {

            JOptionPane.showMessageDialog(null, "please enter a valid  NIC to the NIC field ","Data format mismatch", JOptionPane.WARNING_MESSAGE);
            txtf_NIC_Employee.setText("");

        }
        else if (!jnic_Employee.matches(RegexNIC ))
        {
            JOptionPane.showMessageDialog(null, " please enter a valid  NIC to the NIC field ", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
        }
        else if (jfname_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(null, "Please Fill the Empoyee First Name field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else if (jlname_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee Last Name field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else if (jphn_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee phone field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }

        else if (jadd_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee Address field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else if (jacc_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee AccountNo field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else if (jdes_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee Description field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else if (jpost_Employee.isEmpty())
        {

            JOptionPane.showMessageDialog(this, "Please Fill the Empoyee post field  ","Field is empty",JOptionPane.WARNING_MESSAGE);
        }
        else{
            try
            {

                // tableload
                Employee emp = new Employee(jeid_Employee,jnic_Employee,jfname_Employee,jlname_Employee,jadd_Employee,jphn_Employee,jacc_Employee,jdes_Employee,jpost_Employee);
                emp.insertEmployee();
                JOptionPane.showMessageDialog(null, "This Empoyee is succfully added!!!");
                employeeTableLoad();
                lbl_Eid_Employee.setText(null);
                txtf_NIC_Employee.setText(null);
                txtf_fname_Employee.setText(null);
                txtf_lname_Employee.setText(null);
                txta_add_Employee.setText(null);
                txtf_phn_Employee.setText(null);
                txtf_acc_Employee.setText(null);
                txta_des_Employee.setText(null);
                txtf_post_Empolyee.setText(null);

            }
            catch (Exception e)
            {
                System.out.print(e);

            }
        }

        employeeTableLoad();
    }//GEN-LAST:event_btn_Add_EmployeeActionPerformed

    private void txtf_post_EmpolyeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_post_EmpolyeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_post_EmpolyeeActionPerformed

    private void txtf_acc_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_acc_EmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_acc_EmployeeActionPerformed

    private void txtf_phn_EmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_phn_EmployeeKeyReleased
        int count = 0;
        String checkname = txtf_phn_Employee.getText();

        for (int i = 0; i < checkname.length(); i++) {
            char c = checkname.charAt(i);
            if (Character.isDigit(c)) {
                if (txtf_phn_Employee.getText().length() > 10) {

                    JOptionPane.showMessageDialog(null, "Cann't Have more than 10", "Error", WIDTH);
                    txtf_phn_Employee.setText("");
                }
            }
            else if(Character.isLetter(c))
            {
                JOptionPane.showMessageDialog(null, "cannot have letters", "Error", WIDTH);
                txtf_phn_Employee.setText(null);

            }
            else
            {
                JOptionPane.showMessageDialog(null, "cannot have Special Characters", "Error", WIDTH);
                txtf_phn_Employee.setText(null);

            }

        }
    }//GEN-LAST:event_txtf_phn_EmployeeKeyReleased

    private void txtf_phn_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_phn_EmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_phn_EmployeeActionPerformed

    private void txtf_lname_EmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_lname_EmployeeKeyReleased
        int count = 0;
        String checkname = txtf_lname_Employee.getText();

        for (int i = 0; i < checkname.length(); i++) {
            char c = checkname.charAt(i);
            if (Character.isDigit(c)) {

                JOptionPane.showMessageDialog(null, "Please enter a valid  name ", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                txtf_lname_Employee.setText("");

            }
            else if(Character.isLetter(c))
            {

            }
            else
            {
                JOptionPane.showMessageDialog(null, "cannot have Special Characters", "Error", WIDTH);
                txtf_lname_Employee.setText("");

            }

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtf_lname_EmployeeKeyReleased

    private void txtf_lname_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_lname_EmployeeActionPerformed
        txta_add_Employee.grabFocus();// TODO add your handling code here:
    }//GEN-LAST:event_txtf_lname_EmployeeActionPerformed

    private void txtf_fname_EmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_fname_EmployeeKeyReleased
        int count = 0;
        String checkname = txtf_fname_Employee.getText();

        for (int i = 0; i < checkname.length(); i++) {
            char c = checkname.charAt(i);
            if (Character.isDigit(c)) {

                JOptionPane.showMessageDialog(null, "cannot have numbers", "Error", WIDTH);
                txtf_fname_Employee.setText("");

            }
            else if(Character.isLetter(c))
            {

            }
            else
            {
                JOptionPane.showMessageDialog(null, "cannot have Special Characters", "Error", WIDTH);
                txtf_fname_Employee.setText("");

            }

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtf_fname_EmployeeKeyReleased

    private void txtf_fname_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_fname_EmployeeActionPerformed
        txtf_lname_Employee.grabFocus();      // TODO add your handling code here:
    }//GEN-LAST:event_txtf_fname_EmployeeActionPerformed

    private void txtf_NIC_EmployeeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtf_NIC_EmployeeKeyReleased

        // TODO add your handling code here:
    }//GEN-LAST:event_txtf_NIC_EmployeeKeyReleased

    private void txtf_NIC_EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtf_NIC_EmployeeActionPerformed
        // TODO add your handling code here:
        /* String jnic=nic.getText();
        a(nic);
        fname.grabFocus();*/
    }//GEN-LAST:event_txtf_NIC_EmployeeActionPerformed

    private void jbchequeAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbchequeAddActionPerformed
        new cheque().setVisible(true);
    }//GEN-LAST:event_jbchequeAddActionPerformed

    private void jtcheKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtcheKeyReleased
        int count = 0;
        String checkname = jtche.getText();

        for (int i = 0; i < checkname.length(); i++) {
            char c = checkname.charAt(i);
            if (Character.isDigit(c)) {

            }
            else if(Character.isLetter(c))
            {
                JOptionPane.showMessageDialog(null, "cannot have letters", "Error", WIDTH);
                jtche.setText("");

            }
            else
            {
                JOptionPane.showMessageDialog(null, "cannot have Special Characters", "Error", WIDTH);
                jtche.setText("");

            }

        }
    }//GEN-LAST:event_jtcheKeyReleased

    private void jtcheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtcheActionPerformed

    }//GEN-LAST:event_jtcheActionPerformed

    private void jbchequeDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbchequeDeleteActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete....?");

        if(x==0)
        {

            String che = jtche.getText();

            String sql = "DELETE from cheque where ChequeNo LIKE '"+ che +"'";

            try
            {

                pst = conn.prepareStatement(sql);
                pst.execute();

                tableload2();
            }
            catch (Exception e)
            {
                System.out.print(e);
            }

        }
    }//GEN-LAST:event_jbchequeDeleteActionPerformed

    private void jbchequeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbchequeSearchActionPerformed
        String cid = jtche.getText();
        String sql = "SELECT BankName,Branch,Information,TransitCode,RoutingNo,AccountNo,ChequeNo,No,Amount,Date,Description,Status from cheque where ChequeNo like  '"+ cid +"'";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jtcheque.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
        }
    }//GEN-LAST:event_jbchequeSearchActionPerformed

    private void jbchequeEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbchequeEditActionPerformed

        //chh=false;

        int r=jtcheque.getSelectedRow();
        cheque c1 =new cheque(jtcheque.getValueAt(r, 0).toString(),
            jtcheque.getValueAt(r, 1).toString(),
            jtcheque.getValueAt(r, 2).toString(),
            jtcheque.getValueAt(r, 3).toString(),
            jtcheque.getValueAt(r, 4).toString(),
            jtcheque.getValueAt(r, 5).toString(),
            jtcheque.getValueAt(r, 6).toString(),
            jtcheque.getValueAt(r, 7).toString(),
            jtcheque.getValueAt(r, 8).toString(),
            jtcheque.getValueAt(r, 9).toString(),
            jtcheque.getValueAt(r, 10).toString(),
            jtcheque.getValueAt(r, 11).toString());
        c1.setVisible(true);
    }//GEN-LAST:event_jbchequeEditActionPerformed

    private void jbBillAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBillAddActionPerformed
        new utility().setVisible(true);
    }//GEN-LAST:event_jbBillAddActionPerformed

    private void ibBillDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ibBillDeleteActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete....?");

        if(x==0)
        {

            String bno = jtblno.getText();

            String sql = "DELETE from utility where BillNo LIKE '"+ bno +"'";

            try
            {

                pst = conn.prepareStatement(sql);
                pst.execute();

                tableload1();
            }
            catch (Exception e)
            {
                System.out.print(e);
            }

        }
    }//GEN-LAST:event_ibBillDeleteActionPerformed

    private void jbBillSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbBillSearchActionPerformed
        String blno = jtblno.getText();
        String sql = "SELECT UtilityID,BillNo,Location,Utility,Unit,Period,Amount from utility where BIllNo like  '"+ blno +"'";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jtutility.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
        }
    }//GEN-LAST:event_jbBillSearchActionPerformed

    private void jButton5_driverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5_driverActionPerformed
        // TODO add your handling code here:

        System.out.println(" to is high");
    }//GEN-LAST:event_jButton5_driverActionPerformed

    private void jPanel13_driverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13_driverMouseClicked
        // TODO add your handling code here:
        driver_add_jbutton.setEnabled(true);
        driver_remove_jbutton.setEnabled(false);
        driver_update_jbutton.setEnabled(false);
    }//GEN-LAST:event_jPanel13_driverMouseClicked

    private void driver_view_allActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_view_allActionPerformed
        // TODO add your handling code here:

        driver_search.setText("");
        d1.Driver_jtable_view(driver_view_all);
    }//GEN-LAST:event_driver_view_allActionPerformed

    private void driver_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_driver_searchKeyReleased
        // TODO add your handling code here:
        // int n=Integer.parseInt(driver_search.getText());
        d1.driver_search(driver_search.getText(),driver_view_all);
    }//GEN-LAST:event_driver_searchKeyReleased

    private void driver_view_jtableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_view_jtableMouseExited
        // TODO add your handling code here:

        //driver_view_jtable.getSelectionModel().clearSelection();
    }//GEN-LAST:event_driver_view_jtableMouseExited

    private void driver_view_jtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_view_jtableMouseClicked
        // TODO add your handling code here:

        int row=driver_view_jtable.getSelectedRow();
        d1.driver_id=driver_view_jtable.getValueAt(row,0).toString();

        driver_fname.setText(driver_view_jtable.getValueAt(row,1).toString());
        driver_lname.setText(driver_view_jtable.getValueAt(row,2).toString());
        driver_nic.setText(driver_view_jtable.getValueAt(row,3).toString());
        driver_address.setText(driver_view_jtable.getValueAt(row,4).toString());
        driver_phone.setText(driver_view_jtable.getValueAt(row,5).toString());
        driver_licenceNO.setText(driver_view_jtable.getValueAt(row,6).toString());
        if(driver_view_jtable.getValueAt(row,7).toString().equals("available"))
        {
            driver_availalibity.setSelectedIndex(0);
        }
        else
        {
            driver_availalibity.setSelectedIndex(1);
        }

        driver_add_jbutton.setEnabled(false);
        driver_remove_jbutton.setEnabled(true);
        driver_update_jbutton.setEnabled(true);
    }//GEN-LAST:event_driver_view_jtableMouseClicked

    private void driver_view_jtableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_driver_view_jtableFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_view_jtableFocusLost

    private void jPanel14_driverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14_driverMouseClicked
        // TODO add your handling code here:
        driver_add_jbutton.setEnabled(true);
        driver_remove_jbutton.setEnabled(false);
        driver_update_jbutton.setEnabled(false);
    }//GEN-LAST:event_jPanel14_driverMouseClicked

    private void driver_reset_fieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_reset_fieldsActionPerformed
        // TODO add your handling code here:
        // d1.view_pic();
        d1.driver_reset_fields(driver_search,driver_fname,driver_lname,driver_address,driver_nic,driver_phone,driver_picture,driver_licenceNO,driver_availalibity);
        driver_add_jbutton.setEnabled(true);
        driver_remove_jbutton.setEnabled(false);
        driver_update_jbutton.setEnabled(false);
        d1.Driver_jtable_view(driver_view_all);
    }//GEN-LAST:event_driver_reset_fieldsActionPerformed

    private void driver_remove_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_remove_jbuttonActionPerformed
        // TODO add your handling code here:
        int row=driver_view_jtable.getSelectedRow();

        d1.remove_driver(driver_view_jtable.getValueAt(row,0).toString());
        d1.Driver_jtable_view(driver_view_all);
    }//GEN-LAST:event_driver_remove_jbuttonActionPerformed

    private void driver_update_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_update_jbuttonActionPerformed
        // TODO add your handling code here:
        String id="";
        int row=driver_view_jtable.getSelectedRow();
        id=driver_view_jtable.getValueAt(row,0).toString();

        //System.out.println("id is : "+id);

        String path=driver_picture.getText();
        path = path.replace("\\", "/");

            if(d1.driver_validate_update(id,driver_fname, driver_lname, driver_licenceNO,driver_nic,driver_phone))
            {

                d1.update_driver(id,driver_fname.getText(), driver_lname.getText(), driver_nic.getText(),driver_address.getText(),driver_phone.getText(), driver_licenceNO.getText(),path,driver_availalibity.getSelectedIndex());
                d1.Driver_jtable_view(driver_view_all);
                d1.driver_reset_fields(driver_search,driver_fname,driver_lname,driver_address,driver_nic,driver_phone,driver_picture,driver_licenceNO,driver_availalibity);

            }
    }//GEN-LAST:event_driver_update_jbuttonActionPerformed

    private void driver_nicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_nicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_nicActionPerformed

    private void driver_nicMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_nicMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_nicMouseReleased

    private void driver_nicMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_nicMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_nicMouseExited

    private void driver_nicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_driver_nicFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_nicFocusLost

    private void driver_phoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_driver_phoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_driver_phoneFocusLost

    private void driver_pictureMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driver_pictureMouseClicked
        // TODO add your handling code here:

        String path=d1.get_pic();
        driver_picture.setText(path);
        //System.out.println(d1.get_pic());
    }//GEN-LAST:event_driver_pictureMouseClicked

    private void driver_add_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driver_add_jbuttonActionPerformed
        // TODO add your handling code here:
        String path=driver_picture.getText();
        path = path.replace("\\", "/");

            if(d1.driver_validate(driver_fname, driver_lname, driver_licenceNO,driver_nic,driver_phone))
            {
                d1.add_driver(driver_fname.getText(), driver_lname.getText(), driver_nic.getText(),driver_address.getText(),driver_phone.getText(), driver_licenceNO.getText(),path,driver_availalibity.getSelectedIndex());
                d1.Driver_jtable_view(driver_view_all);
                d1.driver_reset_fields(driver_search,driver_fname,driver_lname,driver_address,driver_nic,driver_phone,driver_picture,driver_licenceNO,driver_availalibity);

            }
    }//GEN-LAST:event_driver_add_jbuttonActionPerformed

    private void Marketing_JscrollPane_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Marketing_JscrollPane_6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Marketing_JscrollPane_6MouseClicked

    private void MCampTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MCampTable2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MCampTable2MouseClicked

    private void Marketing_JscrollPane_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Marketing_JscrollPane_5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Marketing_JscrollPane_5MouseClicked

    private void MCampTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MCampTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_MCampTable1MouseClicked

    private void MCSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCSearchBtnActionPerformed

        if(!StringUtils.isEmptyOrWhitespaceOnly(MCSearchText.getText()))
        {

            try {
                //Search Marketing Campaign
                String name = MCSearchText.getText();

                String sql = "SELECT * FROM mcamp WHERE name LIKE'%"+ name +"%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                MCampTable.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "Please enter a Campaign name to search", "Field Empty", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MCSearchBtnActionPerformed

    private void MCSearchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCSearchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MCSearchTextActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Interfaces.MarketingCampaignAddItems M = new Interfaces.MarketingCampaignAddItems();
        M.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void MCjBResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCjBResetActionPerformed
        ClearPromotionFeilds();
        ClearDataArrays();
        MCampTable.clearSelection();
        MCID.setText("");
    }//GEN-LAST:event_MCjBResetActionPerformed

    private void MCDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCDeleteBtnActionPerformed

        int row = MCampTable.getSelectedRow();

        try
        {
            String tempID = MCampTable.getValueAt(row, 0).toString();

            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?");

            if(x==0)
            {
                int ID = Integer.parseInt(MCID.getText());

                MarketingCampaign MC = new MarketingCampaign();
                DeleteDataBeforeEdit(ID);
                MC.deleteCampaing(ID);

                MarketingCampgaignTableLoad();
            }
        }
        catch(ArrayIndexOutOfBoundsException AE)
        {
            JOptionPane.showMessageDialog(rootPane, "Please select an entry to delete", "Entry not selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MCDeleteBtnActionPerformed

    private void MCEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCEditBtnActionPerformed

        MarValidator MAR = new MarValidator(MCNameText.getText(), MCDescriptionText.getText(), MVenueText.getText(), MCDateChooser.getDate(), MCCost.getText());

        if(MAR.MCampValidate())
        {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to update ?");

            if(x==0)
            {
                int ID = Integer.parseInt(MCID.getText());
                String name = MCNameText.getText();
                String desc = MCDescriptionText.getText();
                String venue = MVenueText.getText();
                double cost = Double.parseDouble(MCCost.getText());

                Date date = MCDateChooser.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                String strDate = sdf.format(date);

                MarketingCampaign MC = new MarketingCampaign(ID, name, desc, strDate, venue, cost);
                MC.editCampaign(ID);
                DeleteDataBeforeEdit(ID);
                MC.Editmitems_has_mcampTable(ID);
                ClearPromotionFeilds();

            }
            MarketingCampgaignTableLoad();
        }
    }//GEN-LAST:event_MCEditBtnActionPerformed

    private void MCAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCAddBtnActionPerformed
        MarValidator MAR = new MarValidator(MCNameText.getText(), MCDescriptionText.getText(), MVenueText.getText(), MCDateChooser.getDate(), MCCost.getText());

        if(MAR.MCampValidate())
        {

            String name = MCNameText.getText();
            String desc = MCDescriptionText.getText();
            String venue = MVenueText.getText();
            double cost = Double.parseDouble(MCCost.getText());

            Date date = MCDateChooser.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = sdf.format(date);

            MarketingCampaign MC = new MarketingCampaign(name, desc, strDate, venue, cost);
            MC.addCampaign();
            MC.addTomitems_has_mcampTable();

            /**
            * @algorithm Rainbow Algorithm
            */
            //Insert in to ManyToMany Table
            //RainbowAlgorith to pass the values to the Database from Array

            //       int ItemID=0,Qty=0,rem=0,rem2=0,strtval=0,endval=0;
            //        strtval=0;
            //        endval=1;
            //
            //        for(ArrayList<Integer> r : md )
            //        {
                //            rem=r.size()/2;
                //            rem2=r.size();
                //
                //            while(rem>=0)
                //            {
                    //               while(strtval<endval && endval<=rem2)
                    //                {
                        //                   try {
                            //                       ItemID=r.get(strtval);
                            //                       Qty=r.get(endval);
                            //
                            //                       String Sql = "CALL insertMitemsHasMcamp('"+ItemID+"','"+Qty+"')";
                            //                       pst = con.prepareStatement(Sql);
                            //                       pst.execute();
                            //
                            //                       strtval+=2;
                            //                       endval+=2;
                            //
                            //
                            //                   } catch (SQLException ex) {
                            //                       Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                            //                   }
                        //                }
                    //               rem--;
                    //            }
                //        }

            // Clear all tracers of data relevent to the above transaction
            ClearPromotionFeilds();
             JOptionPane.showMessageDialog(rootPane, "Succefully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
            MC.ClearData();
            ClearDataArrays();
            MCampTable.clearSelection();
            MCID.setText("");
        }
        //JOptionPane.showMessageDialog(null, "Item ID : "+ItemID+"\n"+"Qty Orderd :"+Qty);

        MarketingCampgaignTableLoad();
    }//GEN-LAST:event_MCAddBtnActionPerformed

    private void Marketing_JscrollPane_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Marketing_JscrollPane_4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Marketing_JscrollPane_4MouseClicked

    private void MCampTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MCampTableMouseClicked
        int row = MCampTable.getSelectedRow();

        String ID = MCampTable.getValueAt(row, 0).toString();
        String name = MCampTable.getValueAt(row, 1).toString();
        String desc = MCampTable.getValueAt(row, 2).toString();
        Date date = (Date) MCampTable.getValueAt(row, 3);
        String venue = MCampTable.getValueAt(row, 4).toString();
        String cost = MCampTable.getValueAt(row, 5).toString();

        MCID.setText(ID);
        MCNameText.setText(name);
        MCDescriptionText.setText(desc);
        MVenueText.setText(venue);
        MCDateChooser.setDate(date);
        MCCost.setText(cost);
    }//GEN-LAST:event_MCampTableMouseClicked

    private void MCNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MCNameTextActionPerformed

    private void PjBReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBReset1ActionPerformed
        ClearPromotionFeilds();
    }//GEN-LAST:event_PjBReset1ActionPerformed

    private void Marketing_JscrollPane_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Marketing_JscrollPane_2MouseClicked

    }//GEN-LAST:event_Marketing_JscrollPane_2MouseClicked

    private void MIjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MIjTableMouseClicked
        //Add data from table to the variables

        int row = MIjTable.getSelectedRow();

        String id = MIjTable.getValueAt(row, 0).toString();
        String type = MIjTable.getValueAt(row, 1).toString();
        String description = MIjTable.getValueAt(row, 2).toString();
        String cost = MIjTable.getValueAt(row, 3).toString();
        String Qty = MIjTable.getValueAt(row, 4).toString();

        MItemsDesc.setText((description));
        MIComboBox.setSelectedItem(type);
        MICostText.setText(cost);
        MIQtyText.setText(Qty);
        MIID.setText(id);
    }//GEN-LAST:event_MIjTableMouseClicked

    private void MIDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIDeleteBtnActionPerformed

        int row = MIjTable.getSelectedRow();

        try
        {

            String checkID = MIjTable.getValueAt(row, 0).toString();

            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ? ");

            if (x == 0) {

                int ID = Integer.parseInt(MIID.getText());

                MarketingItems MI = new MarketingItems();
                MI.deleteItem(ID);

                MarketingItemsTableLoad();
            }
        }
        catch (ArrayIndexOutOfBoundsException AE)
        {
            JOptionPane.showMessageDialog(rootPane, "Please select an entry to delete", "Entry not selected ", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_MIDeleteBtnActionPerformed

    private void MIEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIEditBtnActionPerformed

        MarValidator MRV = new MarValidator(MIComboBox.getSelectedItem().toString(), MItemsDesc.getText(),MICostText.getText(),MIQtyText.getText());

        if(MRV.MItemsValidate())
        {

            int confirm = JOptionPane.showConfirmDialog(rootPane, "Do you really want to update ? ");
            if (confirm == 0) {
                int ID = Integer.parseInt(MIID.getText());

                String Desc = MItemsDesc.getText();
                String Type = MIComboBox.getSelectedItem().toString();
                double Cost = Double.parseDouble(MICostText.getText());
                int Qty = Integer.parseInt(MIQtyText.getText());

                MarketingItems MI = new MarketingItems(ID, Type, Desc, Cost, Qty);
                MI.editItems(ID);

                MarketingItemsTableLoad();
                
                ClearPromotionFeilds();
                    
                    JOptionPane.showMessageDialog(rootPane, "Succefully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_MIEditBtnActionPerformed

    private void MIAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MIAddBtnActionPerformed

        MarValidator MRV = new MarValidator(MIComboBox.getSelectedItem().toString(), MItemsDesc.getText(),MICostText.getText(),MIQtyText.getText());

        if(MRV.MItemsValidate())
        {

            String Type = MIComboBox.getSelectedItem().toString();
            String Desc = MItemsDesc.getText();
            double Cost = Double.parseDouble(MICostText.getText());
            int Qty = Integer.parseInt(MIQtyText.getText());
            //            JOptionPane.showMessageDialog(rootPane, Type);
            //            JOptionPane.showMessageDialog(rootPane, Desc);

            MarketingItems MI = new MarketingItems(Type, Desc,  Cost, Qty);
            MI.addItems();

            MarketingItemsTableLoad();
            ClearPromotionFeilds();
            
                    
                    JOptionPane.showMessageDialog(rootPane, "Succefully Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_MIAddBtnActionPerformed

    private void MICostTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MICostTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MICostTextActionPerformed

    private void PjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PjTableMouseClicked
        // get values from table to variables
        int row = PjTable.getSelectedRow();

        String id = PjTable.getValueAt(row, 0).toString();
        String name = PjTable.getValueAt(row, 1).toString();
        String description = PjTable.getValueAt(row, 2).toString();
        String discount = PjTable.getValueAt(row, 3).toString();
        Date date = (Date) PjTable.getValueAt(row, 4);
        Date date1 = (Date) PjTable.getValueAt(row, 5);
        String picture = PjTable.getValueAt(row, 6).toString();

        PNameText.setText(name);
        PDescriotionText.setText(description);
        PDiscountText.setText(discount);
        PJDateChooser.setDate(date);
        PJDateChooserTo.setDate(date1);
        PPicture.setText(picture);
        PjLableID.setText(id);
    }//GEN-LAST:event_PjTableMouseClicked

    private void PSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PSearchBtnActionPerformed
        //Search the Table for a name
        try {
            String name = PSearchNameText.getText();

            String Sql = "SELECT * FROM promotionsanddeals WHERE name LIKE '%" + name + "%'";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        PjTable.setModel(DbUtils.resultSetToTableModel(rs));
    }//GEN-LAST:event_PSearchBtnActionPerformed

    private void PjBResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBResetActionPerformed
        ClearPromotionFeilds();
        promotionTableLoad();
    }//GEN-LAST:event_PjBResetActionPerformed

    private void PNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameTextActionPerformed

    private void PjBAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBAddActionPerformed

        /**
        *
        * @author Basuru Kusal[ LUCIFER ]
        */
        MarValidator MAR = new MarValidator(PNameText.getText(), PDescriotionText.getText(), PDiscountText.getText(), PJDateChooser.getDate(), PJDateChooserTo.getDate(), PPicture.getText());
        if(MAR.PDValidate())
        {
            try {
                String name = PNameText.getText();
                String description = PDescriotionText.getText();

                Date date = PJDateChooser.getDate();
                Date date1 = PJDateChooserTo.getDate();

                SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfTo = new SimpleDateFormat("yyyy-MM-dd");

                String strDateFrom = sdfFrom.format(date);
                String strDateTo = sdfTo.format(date1);

                double discount = Double.parseDouble(PDiscountText.getText());
                String picturePath = PPicture.getText().replaceAll("\\\\", "\\\\\\\\");

                    //put data to the databse
                    PromotionsAndDeals PD = new PromotionsAndDeals(name, description, strDateFrom, strDateTo, discount, picturePath);
                    PD.addPromotion();
                    promotionTableLoad();
                    ClearPromotionFeilds();
                    
                    JOptionPane.showMessageDialog(rootPane, "Succefully Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException N) {
                    System.out.println(N);
                }
            }
    }//GEN-LAST:event_PjBAddActionPerformed

    private void PjBEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBEditActionPerformed

        MarValidator MAR = new MarValidator(PNameText.getText(), PDescriotionText.getText(), PDiscountText.getText(), PJDateChooser.getDate(), PJDateChooserTo.getDate(), PPicture.getText());

        if(MAR.PDValidate())
        {
            int confirm = JOptionPane.showConfirmDialog(rootPane, "Do you really want to update ? ");
            if (confirm == 0)
            {
                int ID = Integer.parseInt(PjLableID.getText());
                String name = PNameText.getText();
                String description = PDescriotionText.getText();
                double discount = Double.parseDouble(PDiscountText.getText());

                Date date = PJDateChooser.getDate();
                Date date1 = PJDateChooserTo.getDate();

                SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfTo = new SimpleDateFormat("yyyy-MM-dd");

                String strDateFrom = sdfFrom.format(date);
                String strDateTo = sdfTo.format(date1);

                String picture = PPicture.getText().replaceAll("\\\\", "\\\\\\\\");

                    PromotionsAndDeals PD = new PromotionsAndDeals(ID, name, description, strDateFrom, strDateTo, discount, picture);
                    PD.editPromotion();
                    promotionTableLoad();
                    ClearPromotionFeilds();
                    
                    JOptionPane.showMessageDialog(rootPane, "Succefully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            }
    }//GEN-LAST:event_PjBEditActionPerformed

    private void PjBDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBDeleteActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ? ");

        if (x == 0) {
            int ID = Integer.parseInt(PjLableID.getText());
            PromotionsAndDeals PD = new PromotionsAndDeals();
            PD.deletePromotion(ID);

            //Load the table here also
            promotionTableLoad();
        }
    }//GEN-LAST:event_PjBDeleteActionPerformed

    private void PjBUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PjBUploadActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getPath();

            PPicture.setText(path);

        }
    }//GEN-LAST:event_PjBUploadActionPerformed

    private void com_SearchTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_com_SearchTypeItemStateChanged

        if(evt.getStateChange() == ItemEvent.SELECTED) {
            if(com_SearchType.getSelectedIndex() == 1){
                jpanAdvancedSearch.setVisible(true);
                //tableFilter(null, null);
            }
            else
            jpanAdvancedSearch.setVisible(false);

        }
    }//GEN-LAST:event_com_SearchTypeItemStateChanged

    private void btn_edit_reservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_edit_reservationActionPerformed

        if(tableReservation.getSelectedRow() >-1){
            try {
                String id = tableReservation.getValueAt(tableReservation.getSelectedRow(),0 ).toString();

                AddNewReservation er = new AddNewReservation(id);
                er.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        JOptionPane.showMessageDialog(GennCusPanel, "Please Select a row from reservetion table in order to edit a reservation");
    }//GEN-LAST:event_btn_edit_reservationActionPerformed

    private void btn_add_reservaionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_reservaionActionPerformed
        AddNewReservation addReserVationForm = new AddNewReservation(){};
        addReserVationForm.setLocationRelativeTo(null);
        addReserVationForm.setVisible(true);
    }//GEN-LAST:event_btn_add_reservaionActionPerformed

    private void BTN_ADsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ADsearchActionPerformed
        advanceSearch();
    }//GEN-LAST:event_BTN_ADsearchActionPerformed

    private void CHKB_endDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CHKB_endDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CHKB_endDateActionPerformed

    private void CMB_noOfVehicalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CMB_noOfVehicalsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CMB_noOfVehicalsActionPerformed

    private void txt_reservation_serarchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_reservation_serarchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_reservation_serarchActionPerformed

    private void cmb_resSearchOrderByItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_resSearchOrderByItemStateChanged

        if(evt.getStateChange() == ItemEvent.SELECTED){
            advanceSearch();
        }
    }//GEN-LAST:event_cmb_resSearchOrderByItemStateChanged

    private void tableReservationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableReservationMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tableReservationMouseClicked

    private void Customer_AddBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_AddBActionPerformed
        AddCustomer1 Customer1= new AddCustomer1();
        Customer1.setVisible(true);
    }//GEN-LAST:event_Customer_AddBActionPerformed

    private void SrchOnlineBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SrchOnlineBActionPerformed
        String Sparam=SrchonlineparmT.getText();

        if(OnlineSbyNameRB.isSelected()&&!Sparam.isEmpty())
        {
            try {
                String sql="SELECT o.onlineCus_ID,o.fame,o.lname,o.email,o.username,o.password,o.NIC,o.phone,o.address FROM onlinecustomer o inner join customer c on o.Customer_customer_ID=c.customer_ID where o.fame LIKE '%"+Sparam+"%' OR o.lname LIKE '%"+Sparam+"%' AND  c.status=1";
                pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                SrchROnlineTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(OnlineSByUnameRB.isSelected()&&!Sparam.isEmpty())
        {
            try {
                String sql="SELECT o.onlineCus_ID,o.fame,o.lname,o.email,o.username,o.password,o.NIC,o.phone,o.address FROM onlinecustomer o inner join customer c on o.Customer_customer_ID=c.customer_ID where o.username LIKE '%"+Sparam+"%' AND  c.status=1";
                pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                SrchROnlineTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(OnlineSByNICRB.isSelected()&&!Sparam.isEmpty())
        {
            try {
                String sql="SELECT o.onlineCus_ID,o.fame,o.lname,o.email,o.username,o.password,o.NIC,o.phone,o.address FROM onlinecustomer o inner join customer c on o.Customer_customer_ID=c.customer_ID where o.NIC='"+Sparam+"' AND  c.status=1";
                pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                SrchROnlineTable.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Please Fill the search parameter  ", "Field is empty",JOptionPane.WARNING_MESSAGE);

        }
        SrchonlineparmT.setText(null);
    }//GEN-LAST:event_SrchOnlineBActionPerformed

    private void SrchCmpanyBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SrchCmpanyBActionPerformed

        if(SrchByCNameR.isSelected())
        {
            String CompanyName=SrchByCnameT.getText();
            if(CompanyName.isEmpty())
            JOptionPane.showMessageDialog(null,  "Please Fill the company name field", "Field is empty",JOptionPane.WARNING_MESSAGE);

            else
            {
                try {
                    CompanySearch1(CompanyName);
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        else if(SrchByCType.isSelected())
        {
            String type=CTypeSearchCombo.getSelectedItem().toString();

            if(type=="Please select")
            {
                JOptionPane.showMessageDialog(null, "Please select a company type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            else
            {
                try {
                    CompanySearch2(type);
                } catch (SQLException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        SrchByCnameT.setText(null);
        SrchByCNameR.setSelected(false);
        SrchByCType.setSelected(false);
    }//GEN-LAST:event_SrchCmpanyBActionPerformed

    private void CusSrchGenBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CusSrchGenBActionPerformed

        if (SrchByFnameR.isSelected())
        {
            String GenCusNSrch=SrchByGen.getText();

            if(GenCusNSrch.isEmpty())
            JOptionPane.showMessageDialog(null, "Please Fill the customer name field", "Field is empty",JOptionPane.WARNING_MESSAGE);
            else
            GenCusSearch1(GenCusNSrch );
        }

        else if(SrchByNICR.isSelected())
        {
            String GenCusNIC=SrchByGen.getText();
            if(GenCusNIC.isEmpty())
            JOptionPane.showMessageDialog(null,  "Please Fill the customer NIC field", "Field is empty",JOptionPane.WARNING_MESSAGE);
            else
            GenCusSearch2(GenCusNIC);
        }

        else if(SrchByAddR.isSelected())
        {
            String GenCusAddr=SrchByGen.getText();
            if(GenCusAddr.isEmpty())
            JOptionPane.showMessageDialog(null, "Please Fill the customer address field", "Field is empty",JOptionPane.WARNING_MESSAGE);
            else
            GenCusSearch3(GenCusAddr);
        }
        SrchByGen.setText(null);
        SrchByFnameR.setSelected(false);
        SrchByNICR.setSelected(false);
        SrchByAddR.setSelected(false);
    }//GEN-LAST:event_CusSrchGenBActionPerformed

    private void jButton7__packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7__packageActionPerformed

        if(0==JOptionPane.showConfirmDialog(rootPane, "This opration will remove the selected Package from the table !", "Alert", JOptionPane.OK_CANCEL_OPTION)){

            StandardPackage2 sp1 = new StandardPackage2(packageTable.getValueAt(packageTable.getSelectedRow(), 1).toString());
            sp1.RemovePackages();
        }

        //StandardPackage2 rm = new StandardPackage2(std_package_type_ID,description_pak_type,kmLimit,Status));
        // rm.RemovePackages();
        //  tabelLoadRes(q, packageTable);
    }//GEN-LAST:event_jButton7__packageActionPerformed

    private void cmb_stdPakSearchTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_stdPakSearchTypeItemStateChanged

        //if(cmb_stdPakSearchType.getSelectedIndex() == 1)
        //{
            //stdPakQuickSearchJPanel.setVisible(true);

            if(cmb_stdPakSearchType.getSelectedIndex() == 2)
            {
                stdPakAdvanceSearchJPanel.setVisible(true);
            }
            else
            {
                stdPakAdvanceSearchJPanel.setVisible(false);
            }
    }//GEN-LAST:event_cmb_stdPakSearchTypeItemStateChanged

    private void btn_StdPakAdvSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StdPakAdvSearchActionPerformed
        StringBuilder sql = new StringBuilder("SELECT* FROM standardpackage,standardpackagetype WHERE  std_package_type_ID_ref = std_package_type_ID AND standardpackage.deleteStatus = 1  ");
        String SQL_tableLoad = "";
        String temp;
        tabelLoad(SQL_tableLoad, packageTable);

        if(chk_StdPakAdvSearchPackName.isSelected()){
            temp = txt_StdPakAdvSearchPakName.getText();
            sql.append(" AND description_pak_type = '" +  temp +"' ");

        }

        if(chk_StdPakAdvSearchVehiType.isSelected()){

            if(p_cmb_vehicleType.getSelectedIndex() == 0){
                sql.append(" AND vehicle_type = 'Car' ");

            }

            if(p_cmb_vehicleType.getSelectedIndex() == 1){
                sql.append(" AND vehicle_type = 'Van_FAC' ");

            }

            if(p_cmb_vehicleType.getSelectedIndex() == 2){
                sql.append(" AND vehicle_type = 'Van_DAC' ");

            }

            if(p_cmb_vehicleType.getSelectedIndex() == 3){
                sql.append(" AND vehicle_type = 'Van_NAC' ");

            }

            if(p_cmb_vehicleType.getSelectedIndex() == 4){
                sql.append(" AND vehicle_type = 'Lorry_AC' ");

            }

            if(p_cmb_vehicleType.getSelectedIndex() == 5){
                sql.append(" AND vehicle_type = 'Lorry_NAC' ");

            }

            //temp = txt_StdPakAdvSearchVehiType.getText();
            //sql.append("AND vehicle_type = 'Car' ,");

        }





        if(chk_StdPakAdvSearchPakPrice.isSelected()){
            temp = txt_StdPakAdvSearchPrice.getText();
            sql.append(" AND initial_price = '" +  temp +"' ");
            
        }

        if(chk_StdPakAdvSearchKmLimit.isSelected()){
            temp = txt_StdPakAdvSearchKmLimit.getText();
            sql.append(" AND kmLimit = '" +  temp +"' ");
        }

        System.out.println(sql);
        String q = sql.substring(0, sql.length()-1);

        System.out.println(q);
        tabelLoad(q, packageTable);
        tableFilter(lbl_StdPakQuickSearch,packageTable );
    }//GEN-LAST:event_btn_StdPakAdvSearchActionPerformed

    private void packageTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packageTableMouseClicked
        r = packageTable.getSelectedRow();
        System.out.println(r);
    }//GEN-LAST:event_packageTableMouseClicked

    private void jButton5_packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5_packageActionPerformed
        if(r > -1 ){
            AddNewPackage ap = new AddNewPackage(Integer.parseInt(packageTable.getValueAt(r, 1).toString()));
            ap.setVisible(true);
        }
        else{
            System.out.println("wrong row");
        }
    }//GEN-LAST:event_jButton5_packageActionPerformed

    private void jButton6_packageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6_packageActionPerformed
        AddNewPackage ap = new AddNewPackage();
        ap.setVisible(true);
    }//GEN-LAST:event_jButton6_packageActionPerformed

    private void btn_Reset_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Reset_VehicleActionPerformed
        lbl_Vehicle_ID_Vehicle.setText(null);
        txt_Vehicle_Model_Vehicle.setText(null);
        txt_licence_No_Vehicle.setText(null);
        cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
        txt_vehicle_Colour_Vehicle.setText(null);
        txt_no_Of_Seats_Vehicle.setText(null);
        cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
        lbl_ownerID_Vehicle.setText(null);
        txt_owner_Name_Vehicle.setText(null);
        txt_owner_Address_Vehicle.setText(null);
        txt_owner_PhoneNo_Vehicle.setText(null);
    }//GEN-LAST:event_btn_Reset_VehicleActionPerformed

    private void btn_vehicleSearch_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleSearch_VehicleActionPerformed
        search_Vehicle_Vehicle1 sv;
        try {
            sv = new search_Vehicle_Vehicle1();
            sv.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_vehicleSearch_VehicleActionPerformed

    private void tbl_CompanyVehiTable_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CompanyVehiTable_VehicleMouseClicked

        // btn_vehicleAdd_Vehicle.setEnabled(false);
        //btn_vehicleEdit_Vehicle.setEnabled(true);
        //btn_vehicleRemove_Vehicle.setEnabled(true);

        int row = tbl_CompanyVehiTable_Vehicle.getSelectedRow();

        String vehicleID = tbl_CompanyVehiTable_Vehicle.getValueAt(row,0).toString();
        String vehicleType = tbl_CompanyVehiTable_Vehicle.getValueAt(row, 1).toString();
        String vehicleModel = tbl_CompanyVehiTable_Vehicle.getValueAt(row, 2).toString();
        String licenceNo = tbl_CompanyVehiTable_Vehicle.getValueAt(row,3).toString() ;
        String transmitType = tbl_CompanyVehiTable_Vehicle.getValueAt(row,4).toString() ;
        String fuelType = tbl_CompanyVehiTable_Vehicle.getValueAt(row,5).toString() ;
        String colour = tbl_CompanyVehiTable_Vehicle.getValueAt(row,6).toString() ;
        String noOfSeats = tbl_CompanyVehiTable_Vehicle.getValueAt(row,7).toString() ;
        String vStatus = tbl_CompanyVehiTable_Vehicle.getValueAt(row,8).toString() ;

        lbl_Vehicle_ID_Vehicle.setText(vehicleID);
        txt_Vehicle_Model_Vehicle.setText(vehicleModel);
        txt_licence_No_Vehicle.setText(licenceNo);
        cmb_Vehicle_Type_Vehicle.setSelectedItem(vehicleType);
        cmb_transmit_Type_Vehicle.setSelectedItem(transmitType);
        cmb_fuel_Type_Vehicle.setSelectedItem(fuelType);
        txt_vehicle_Colour_Vehicle.setText(colour);
        txt_no_Of_Seats_Vehicle.setText(noOfSeats);
        cmb_vehicle_Status_Vehicle.setSelectedItem(vStatus);
    }//GEN-LAST:event_tbl_CompanyVehiTable_VehicleMouseClicked

    private void rdb_OutSourceVehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_OutSourceVehicleMouseClicked

        lbl_Vehicle_ID_Vehicle.setText(null);
        txt_Vehicle_Model_Vehicle.setText(null);
        txt_licence_No_Vehicle.setText(null);
        cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
        txt_vehicle_Colour_Vehicle.setText(null);
        txt_no_Of_Seats_Vehicle.setText(null);
        cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
        lbl_ownerID_Vehicle.setText(null);
        txt_owner_Name_Vehicle.setText(null);
        txt_owner_Address_Vehicle.setText(null);
        txt_owner_PhoneNo_Vehicle.setText(null);

        lbl_VehicleID.setEnabled(true);
        lbl_Vehicle_ID_Vehicle.setEnabled(true);
        lbl_VehicleType.setEnabled(true);
        lbl_vehicleMode_Vehiclel.setEnabled(true);
        txt_Vehicle_Model_Vehicle.setEnabled(true);
        lbl_LicenceNo_Vehiclel.setEnabled(true);
        txt_licence_No_Vehicle.setEnabled(true);
        cmb_Vehicle_Type_Vehicle.setEnabled(true);
        lbl_TransmitType_Vehiclel.setEnabled(true);
        cmb_transmit_Type_Vehicle.setEnabled(true);
        lbl_FuelType_Vehiclel.setEnabled(true);
        cmb_fuel_Type_Vehicle.setEnabled(true);
        lbl_VehicleColor_Vehiclel.setEnabled(true);
        txt_vehicle_Colour_Vehicle.setEnabled(true);
        lbl_NoOfSeats_Vehiclel.setEnabled(true);
        txt_no_Of_Seats_Vehicle.setEnabled(true);
        lbl_VehicleStatus_Vehiclel.setEnabled(true);
        cmb_vehicle_Status_Vehicle.setEnabled(true);
        txt_owner_Name_Vehicle.setEnabled(true);
        txt_owner_Address_Vehicle.setEnabled(true);
        txt_owner_PhoneNo_Vehicle.setEnabled(true);
        lbl_OwnerName_Vehiclel.setEnabled(true);
        lbl_OwnerAddress_Vehiclel.setEnabled(true);
        lbl_OwnerPhone_Vehiclel.setEnabled(true);
        tbl_CompanyVehiTable_Vehicle.setVisible(false);
        lbl_companyVehicleTable.setEnabled(false);
        tbl_OutVehicleTable_Vehicle.setVisible(true);
        lbl_outVehicleTable.setEnabled(true);
        lbl_ownerID_Vehicle.setEnabled(true);
        lbl_wner_ID_Vehicle.setVisible(true);

        btn_vehicleAdd_Vehicle.setEnabled(true);
        btn_vehicleEdit_Vehicle.setEnabled(true);
        btn_vehicleRemove_Vehicle.setEnabled(true);
        btn_Reset_Vehicle.setEnabled(true);
        btn_vehicleSearch_Vehicle.setEnabled(true);
        btn_vehicleMaintain_Vehicle.setEnabled(true);

        outSourceVehicleTableLoad();
    }//GEN-LAST:event_rdb_OutSourceVehicleMouseClicked

    private void rdb_companyVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_companyVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_companyVehicleActionPerformed

    private void rdb_companyVehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_companyVehicleMouseClicked

        lbl_Vehicle_ID_Vehicle.setText(null);
        txt_Vehicle_Model_Vehicle.setText(null);
        txt_licence_No_Vehicle.setText(null);
        cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
        txt_vehicle_Colour_Vehicle.setText(null);
        txt_no_Of_Seats_Vehicle.setText(null);
        cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
        lbl_ownerID_Vehicle.setText(null);
        txt_owner_Name_Vehicle.setText(null);
        txt_owner_Address_Vehicle.setText(null);
        txt_owner_PhoneNo_Vehicle.setText(null);

        lbl_VehicleID.setEnabled(true);
        lbl_Vehicle_ID_Vehicle.setEnabled(true);
        lbl_VehicleType.setEnabled(true);
        lbl_vehicleMode_Vehiclel.setEnabled(true);
        txt_Vehicle_Model_Vehicle.setEnabled(true);
        lbl_LicenceNo_Vehiclel.setEnabled(true);
        txt_licence_No_Vehicle.setEnabled(true);
        cmb_Vehicle_Type_Vehicle.setEnabled(true);
        lbl_TransmitType_Vehiclel.setEnabled(true);
        cmb_transmit_Type_Vehicle.setEnabled(true);
        lbl_FuelType_Vehiclel.setEnabled(true);
        cmb_fuel_Type_Vehicle.setEnabled(true);
        lbl_VehicleColor_Vehiclel.setEnabled(true);
        txt_vehicle_Colour_Vehicle.setEnabled(true);
        lbl_NoOfSeats_Vehiclel.setEnabled(true);
        txt_no_Of_Seats_Vehicle.setEnabled(true);
        lbl_VehicleStatus_Vehiclel.setEnabled(true);
        cmb_vehicle_Status_Vehicle.setEnabled(true);

        txt_owner_Name_Vehicle.setEnabled(false);
        txt_owner_Address_Vehicle.setEnabled(false);
        txt_owner_PhoneNo_Vehicle.setEnabled(false);
        lbl_OwnerName_Vehiclel.setEnabled(false);
        lbl_OwnerAddress_Vehiclel.setEnabled(false);
        lbl_OwnerPhone_Vehiclel.setEnabled(false);
        tbl_OutVehicleTable_Vehicle.setVisible(false);
        lbl_outVehicleTable.setEnabled(false);
        tbl_CompanyVehiTable_Vehicle.setVisible(true);
        lbl_companyVehicleTable.setEnabled(true);

        lbl_ownerID_Vehicle.setEnabled(false);
        lbl_wner_ID_Vehicle.setVisible(false);

        btn_vehicleAdd_Vehicle.setEnabled(true);
        btn_vehicleEdit_Vehicle.setEnabled(true);
        btn_vehicleRemove_Vehicle.setEnabled(true);
        btn_Reset_Vehicle.setEnabled(true);
        btn_vehicleSearch_Vehicle.setEnabled(true);
        btn_vehicleMaintain_Vehicle.setEnabled(true);

        companyVehicleTableLoad();
    }//GEN-LAST:event_rdb_companyVehicleMouseClicked

    private void txt_owner_Name_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_owner_Name_VehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_owner_Name_VehicleActionPerformed

    private void btn_vehicleMaintain_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleMaintain_VehicleActionPerformed
        maintenance_Vehicle_Vehicle sv;
        sv = new maintenance_Vehicle_Vehicle();
        sv.setVisible(true);
    }//GEN-LAST:event_btn_vehicleMaintain_VehicleActionPerformed

    private void tbl_OutVehicleTable_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_OutVehicleTable_VehicleMouseClicked
        int row = tbl_OutVehicleTable_Vehicle.getSelectedRow();

        String vehicleID = tbl_OutVehicleTable_Vehicle.getValueAt(row,0).toString();
        String vehicleType = tbl_OutVehicleTable_Vehicle.getValueAt(row, 1).toString();
        String vehicleModel = tbl_OutVehicleTable_Vehicle.getValueAt(row, 2).toString();
        String licenceNo = tbl_OutVehicleTable_Vehicle.getValueAt(row,3).toString() ;
        String transmitType = tbl_OutVehicleTable_Vehicle.getValueAt(row,4).toString() ;
        String fuelType = tbl_OutVehicleTable_Vehicle.getValueAt(row,5).toString() ;
        String colour = tbl_OutVehicleTable_Vehicle.getValueAt(row,6).toString() ;
        String noOfSeats = tbl_OutVehicleTable_Vehicle.getValueAt(row,7).toString() ;
        String vStatus = tbl_OutVehicleTable_Vehicle.getValueAt(row,8).toString() ;
        String owner_ID = tbl_OutVehicleTable_Vehicle.getValueAt(row, 9).toString();
        String ownerName = tbl_OutVehicleTable_Vehicle.getValueAt(row,10).toString();
        String ownerAddress = tbl_OutVehicleTable_Vehicle.getValueAt(row,11).toString();
        String ownerPhone = tbl_OutVehicleTable_Vehicle.getValueAt(row,12).toString();

        lbl_Vehicle_ID_Vehicle.setText(vehicleID);
        txt_Vehicle_Model_Vehicle.setText(vehicleModel);
        txt_licence_No_Vehicle.setText(licenceNo);
        cmb_Vehicle_Type_Vehicle.setSelectedItem(vehicleType);
        cmb_transmit_Type_Vehicle.setSelectedItem(transmitType);
        cmb_fuel_Type_Vehicle.setSelectedItem(fuelType);
        txt_vehicle_Colour_Vehicle.setText(colour);
        txt_no_Of_Seats_Vehicle.setText(noOfSeats);
        cmb_vehicle_Status_Vehicle.setSelectedItem(vStatus);
        lbl_ownerID_Vehicle.setText(owner_ID);
        txt_owner_Name_Vehicle.setText(ownerName);
        txt_owner_Address_Vehicle.setText(ownerAddress);
        txt_owner_PhoneNo_Vehicle.setText(ownerPhone);
    }//GEN-LAST:event_tbl_OutVehicleTable_VehicleMouseClicked

    private void btn_vehicleRemove_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleRemove_VehicleActionPerformed
        int yes = JOptionPane.showConfirmDialog(null, "Do you really want to delete the record?");

        if(yes==0)
        {
            String vehicleID = lbl_Vehicle_ID_Vehicle.getText();
            String vehicleType = cmb_Vehicle_Type_Vehicle.getSelectedItem().toString();
            String vehicleModel = txt_Vehicle_Model_Vehicle.getText();
            String licenceNo = txt_licence_No_Vehicle.getText();
            String transmitType = cmb_transmit_Type_Vehicle.getSelectedItem().toString();
            String fuelType = cmb_fuel_Type_Vehicle.getSelectedItem().toString();
            String colour = txt_vehicle_Colour_Vehicle.getText();
            String noOfSeats = txt_no_Of_Seats_Vehicle.getText();
            String vStatus = cmb_vehicle_Status_Vehicle.getSelectedItem().toString();

            if(rdb_companyVehicle.isSelected())
            {
                companyVehicle cv = new companyVehicle(vehicleID,vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"IN");
                cv.RemoveVehicles();
                companyVehicleTableLoad();

                lbl_Vehicle_ID_Vehicle.setText(null);
                txt_Vehicle_Model_Vehicle.setText(null);
                txt_licence_No_Vehicle.setText(null);
                cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                txt_vehicle_Colour_Vehicle.setText(null);
                txt_no_Of_Seats_Vehicle.setText(null);
                cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                //lbl_vehicleOwnerType_Vehicle.setText(null);

            }
            else if(rdb_OutSourceVehicle.isSelected())
            {
                int owner_ID = Integer.parseInt(lbl_ownerID_Vehicle.getText());
                String ownerName = txt_owner_Name_Vehicle.getText();
                String ownerAddress = txt_owner_Address_Vehicle.getText();
                String ownerPhone = txt_owner_PhoneNo_Vehicle.getText();

                outsourceVehicle ov = new outsourceVehicle(vehicleID,vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"OUT",owner_ID,ownerName,ownerAddress,Integer.parseInt(ownerPhone));
                ov.RemoveVehicles();
                outSourceVehicleTableLoad();

                lbl_Vehicle_ID_Vehicle.setText(null);
                txt_Vehicle_Model_Vehicle.setText(null);
                txt_licence_No_Vehicle.setText(null);
                cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                txt_vehicle_Colour_Vehicle.setText(null);
                txt_no_Of_Seats_Vehicle.setText(null);
                cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                //lbl_vehicleOwnerType_Vehicle.setText(null);
                lbl_ownerID_Vehicle.setText(null);
                txt_owner_Name_Vehicle.setText(null);
                txt_owner_Address_Vehicle.setText(null);
                txt_owner_PhoneNo_Vehicle.setText(null);

            }//end of out vehi radio

        }//end of yes
    }//GEN-LAST:event_btn_vehicleRemove_VehicleActionPerformed

    private void btn_vehicleEdit_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleEdit_VehicleActionPerformed
        //Updating vehicles(Admin)
        int yes = JOptionPane.showConfirmDialog(null, "Do you really want to edit the record?");

        if (yes==0)
        {
            try
            {
                String vehicleID = lbl_Vehicle_ID_Vehicle.getText();
                String vehicleType = cmb_Vehicle_Type_Vehicle.getSelectedItem().toString();
                String vehicleModel = txt_Vehicle_Model_Vehicle.getText();
                String licenceNo = txt_licence_No_Vehicle.getText();
                String transmitType = cmb_transmit_Type_Vehicle.getSelectedItem().toString();
                String fuelType = cmb_fuel_Type_Vehicle.getSelectedItem().toString();
                String colour = txt_vehicle_Colour_Vehicle.getText();
                String noOfSeats = txt_no_Of_Seats_Vehicle.getText();
                String vStatus = cmb_vehicle_Status_Vehicle.getSelectedItem().toString();

                String phoneNum_Vehicle = "^[0-9{10}]+$";
                String address_Vehicle = "^[a-zA-Z0-9,/ ]+$";
                String licenceNo_Vehicle = "^[A-Z0-9]+$";
                String NoOfSeats_Vehicle = "^[0-9]+$";

                if("Select Vehicle Type".equals(vehicleType))
                {
                    JOptionPane.showMessageDialog(null, "Please select a Vehicle Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
                }

                else if(vehicleModel.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill the Vehicle Model field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                }

                else if(licenceNo.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill the Licence Number field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                }

                else if("Select Transmission Type".equals(transmitType))
                {
                    JOptionPane.showMessageDialog(null, "Please select a Transmission Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
                }

                else if("Select Fuel Type".equals(fuelType))
                {
                    JOptionPane.showMessageDialog(null, "Please select a Fuel Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
                }

                else if(colour.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill the Colour field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                }

                else if(noOfSeats.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please Fill the Number of Seats field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                }

                else if("Select Availability Status".equals(vStatus))
                {
                    JOptionPane.showMessageDialog(null, "Please select the Availability Status", "Field not selected",JOptionPane.WARNING_MESSAGE);
                }

                else if(!licenceNo.matches(licenceNo_Vehicle))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Licence Number ");
                }

                else if(!noOfSeats.matches(NoOfSeats_Vehicle))
                {
                    JOptionPane.showMessageDialog(null, "Enter a valid Licence Number", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }

                else
                {
                    if(rdb_companyVehicle.isSelected())
                    {

                        companyVehicle cv = new companyVehicle(vehicleID,vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"IN");
                        cv.UpdateVehicles();

                        companyVehicleTableLoad();

                        lbl_Vehicle_ID_Vehicle.setText(null);
                        txt_Vehicle_Model_Vehicle.setText(null);
                        txt_licence_No_Vehicle.setText(null);
                        cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                        cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                        cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                        txt_vehicle_Colour_Vehicle.setText(null);
                        txt_no_Of_Seats_Vehicle.setText(null);
                        cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                        lbl_ownerID_Vehicle.setText(null);
                        txt_owner_Name_Vehicle.setText(null);
                        txt_owner_Address_Vehicle.setText(null);
                        txt_owner_PhoneNo_Vehicle.setText(null);

                    }
                    else if(rdb_OutSourceVehicle.isSelected())
                    {

                        int owner_ID = Integer.parseInt(lbl_ownerID_Vehicle.getText());
                        String ownerName = txt_owner_Name_Vehicle.getText();
                        String ownerAddress = txt_owner_Address_Vehicle.getText();
                        String ownerPhone = txt_owner_PhoneNo_Vehicle.getText();

                        if(ownerName.isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "Please Fill the Owner Name field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                        }

                        else if(ownerAddress.isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "Please Fill the Owner Address field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                        }

                        else if(!ownerAddress.matches(address_Vehicle))
                        {
                            JOptionPane.showMessageDialog(null, "Enter a valid Address", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                        }

                        else if(ownerPhone.isEmpty())
                        {
                            JOptionPane.showMessageDialog(null, "Please Fill the Phone Number field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                        }

                        else if(ownerPhone.length()<10)
                        {
                            JOptionPane.showMessageDialog(null, "Phone Number cannot have less than 10 numbers", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                        }

                        else if(!ownerPhone.matches(phoneNum_Vehicle))
                        {
                            JOptionPane.showMessageDialog(null, "Only numbers can be inserted for Phone Number field", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                        }

                        else
                        {
                            outsourceVehicle ov = new outsourceVehicle(vehicleID,vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"OUT",owner_ID,ownerName,ownerAddress,Integer.parseInt(ownerPhone));
                            ov.UpdateVehicles();
                            outSourceVehicleTableLoad();

                            lbl_Vehicle_ID_Vehicle.setText(null);
                            txt_Vehicle_Model_Vehicle.setText(null);
                            txt_licence_No_Vehicle.setText(null);
                            cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                            cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                            cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                            txt_vehicle_Colour_Vehicle.setText(null);
                            txt_no_Of_Seats_Vehicle.setText(null);
                            cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                            //lbl_vehicleOwnerType_Vehicle.setText(null);
                            lbl_ownerID_Vehicle.setText(null);
                            txt_owner_Name_Vehicle.setText(null);
                            txt_owner_Address_Vehicle.setText(null);
                            txt_owner_PhoneNo_Vehicle.setText(null);

                        }//end of second else
                    }//end of out vehi radio check
                }//end of first else
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Phone Number cannot exceed 10 numbers", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            }
        }//end of yes
    }//GEN-LAST:event_btn_vehicleEdit_VehicleActionPerformed

    private void btn_vehicleAdd_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_vehicleAdd_VehicleActionPerformed

        // btn_vehicleEdit_Vehicle.setEnabled(false);
        //btn_vehicleRemove_Vehicle.setEnabled(false);
        try
        {
            String vehicleType = cmb_Vehicle_Type_Vehicle.getSelectedItem().toString();
            String vehicleModel = txt_Vehicle_Model_Vehicle.getText();
            String licenceNo = txt_licence_No_Vehicle.getText();
            String transmitType = cmb_transmit_Type_Vehicle.getSelectedItem().toString();
            String fuelType = cmb_fuel_Type_Vehicle.getSelectedItem().toString();
            String colour = txt_vehicle_Colour_Vehicle.getText();
            String noOfSeats = txt_no_Of_Seats_Vehicle.getText();
            String vStatus = cmb_vehicle_Status_Vehicle.getSelectedItem().toString();
            //String vOwnerType = lbl_vehicleOwnerType_Vehicle.getText();

            String phoneNum_Vehicle = "^[0-9{10}]+$";
            String address_Vehicle = "^[a-zA-Z0-9,/ ]+$";
            String licenceNo_Vehicle = "^[A-Z0-9]+$";
            String NoOfSeats_Vehicle = "^[0-9]+$";

            if("Select Vehicle Type".equals(vehicleType))
            {
                JOptionPane.showMessageDialog(null, "Please select a Vehicle Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            else if(vehicleModel.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Fill the Vehicle Model field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if(licenceNo.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Fill the Licence Number field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if("Select Transmission Type".equals(transmitType))
            {
                JOptionPane.showMessageDialog(null, "Please select a Transmission Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            else if("Select Fuel Type".equals(fuelType))
            {
                JOptionPane.showMessageDialog(null, "Please select a Fuel Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            else if(colour.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Fill the Colour field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if(noOfSeats.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please Fill the Number of Seats field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if("Select Availability Status".equals(vStatus))
            {
                JOptionPane.showMessageDialog(null, "Please select the Availability Status", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            else if(!licenceNo.matches(licenceNo_Vehicle))
            {
                JOptionPane.showMessageDialog(null, "Please Enter a valid Licence Number ");
            }

            else if(!noOfSeats.matches(NoOfSeats_Vehicle))
            {
                JOptionPane.showMessageDialog(null, "Enter a valid Licence Number", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            }

            else
            {
                if(rdb_companyVehicle.isSelected())
                {

                    companyVehicle cv = new companyVehicle("NULL",vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"IN");
                    cv.insertVehicles();

                    companyVehicleTableLoad();
                    lbl_Vehicle_ID_Vehicle.setText(null);
                    txt_Vehicle_Model_Vehicle.setText(null);
                    txt_licence_No_Vehicle.setText(null);
                    cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                    cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                    cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                    txt_vehicle_Colour_Vehicle.setText(null);
                    txt_no_Of_Seats_Vehicle.setText(null);
                    cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                    //lbl_vehicleOwnerType_Vehicle.setText(null);

                }

                else if(rdb_OutSourceVehicle.isSelected())
                {

                    String vOwnerName = txt_owner_Name_Vehicle.getText();
                    String vOwnerAddress = txt_owner_Address_Vehicle.getText();
                    String vOwnerPhone = txt_owner_PhoneNo_Vehicle.getText();

                    if(vOwnerName.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please Fill the Owner Name field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(vOwnerAddress.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please Fill the Owner Address field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(!vOwnerAddress.matches(address_Vehicle))
                    {
                        JOptionPane.showMessageDialog(null, "Enter a valid Address", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(vOwnerPhone.length()<10)
                    {
                        JOptionPane.showMessageDialog(null, "Phone Number cannot have less than 10 numbers", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(vOwnerPhone.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please Fill the Phone Number field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(!vOwnerPhone.matches(phoneNum_Vehicle))
                    {
                        JOptionPane.showMessageDialog(null, "Only numbers can be inserted for Phone Number field", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                    }

                    else
                    {
                        outsourceVehicle ov = new outsourceVehicle("NULL",vehicleType,vehicleModel,licenceNo,transmitType,fuelType,colour,noOfSeats,vStatus,"OUT",0,vOwnerName,vOwnerAddress,Integer.parseInt(vOwnerPhone));
                        ov.insertVehicles();

                        outSourceVehicleTableLoad();

                        lbl_Vehicle_ID_Vehicle.setText(null);
                        txt_Vehicle_Model_Vehicle.setText(null);
                        txt_licence_No_Vehicle.setText(null);
                        cmb_Vehicle_Type_Vehicle.setSelectedItem("Select Vehicle Type");
                        cmb_transmit_Type_Vehicle.setSelectedItem("Select Transmission Type");
                        cmb_fuel_Type_Vehicle.setSelectedItem("Select Fuel Type");
                        txt_vehicle_Colour_Vehicle.setText(null);
                        txt_no_Of_Seats_Vehicle.setText(null);
                        cmb_vehicle_Status_Vehicle.setSelectedItem("Select Availability Status");
                        //lbl_vehicleOwnerType_Vehicle.setText(null);
                        lbl_ownerID_Vehicle.setText(null);
                        txt_owner_Name_Vehicle.setText(null);
                        txt_owner_Address_Vehicle.setText(null);
                        txt_owner_PhoneNo_Vehicle.setText(null);
                    }//end of second else

                }//end of first else
            }
        }

        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Phone Number cannot exceed 10 numbers", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_vehicleAdd_VehicleActionPerformed

    private void btn_PakResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PakResetActionPerformed
        lbl_StdPakQuickSearch.setText(null);
        txt_StdPakAdvSearchPakName.setText(null);
        p_cmb_vehicleType.setSelectedItem("Select Vehicle Type");
        txt_StdPakAdvSearchPrice.setText(null);
        txt_StdPakAdvSearchKmLimit.setText(null);
        
    }//GEN-LAST:event_btn_PakResetActionPerformed

       
    
    
    
    
    public void advanceSearch() {
        //Adcanced Search Reservation
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder sql = new StringBuilder("SELECT * FROM createtours.reservation_main_view WHERE ");
        String temp;
        StringBuilder orderBy = new StringBuilder(" ORDER BY ");
        int count = 0;
        
        int index = cmb_resSearchOrderBy.getSelectedIndex();
        
        switch(index){
            case 0: orderBy.append("1");
            break;
                
            case 1: orderBy.append("2");
            break;
                
            case 2: orderBy.append("3");
            break;
                
            case 3: orderBy.append("4");
            break;
                
            case 4: orderBy.append("5");
            break;
                
            case 5: orderBy.append("7");
            break;
                
            default: break;
                
        }
        
        
        
        
        
        if(CHKB_customer.isSelected()){
            temp = TXTB_customerName.getText();
            sql.append(" CUSTOMER_NAME LIKE '%" + temp + "%' AND ");
            count++;
        }
        
        if(CMB_noOfVehicals.isSelected()){
            temp = TXTB_noOfVehicals.getText();
            sql.append(" NO_OF_VEHICALS = " + temp + " AND ");
            count++;
            
        }
        
        
        if(CHKB_startDate.isSelected()){
            
            String date1 = dateFormat.format(DATEPICKER_searchStartDate1.getDate());
            String date2 = dateFormat.format(DATEPICKER_searchStarttDate2.getDate());
            
            sql.append( " RESEVATION_START_DATE BETWEEN '"+date1+"' AND '"+date2+"' AND ");
            count++;
            
        }
        
        if(CHKB_endDate.isSelected()){
            String date1 = dateFormat.format(DATEPICKER_searchEndDate1.getDate());
            String date2 = dateFormat.format(DATEPICKER_searchEndDate2.getDate());
            
            sql.append( " RESERVATION_END_DATE BETWEEN '"+date1+"' AND '"+date2+"' AND ");
            count++;
            
        }
        
        if(CHKB_reservationID.isSelected()){
            temp = TXTB_reservationID.getText();
            sql.append(" RESERVATION_ID ='" + temp + "' AND ");
            count++;
        }
        
        if(CHKB_status.isSelected()){
            String status = null;
            int selectedIndex = CMBO_searchStatus.getSelectedIndex();
            switch(selectedIndex){
                case 0: status = "Active";
                break;
                    
                case 1: status = "Pending";
                break;
                    
                case 2: status = "Overdue";
                break;
                    
                case 3: status = "Complete";
                break;
                    
                case 4: status = "Cancled";
                break;
                    
                default: break;
                    
                    
                    
            }
            
            sql.append(" RESERVATION_STATUS = '" + status + "' AND ");
            count++;
        }
        
        String q = null;
        
        if(count > 0){
            
            q = sql.substring(0, sql.length()-4);
            q = q + orderBy;
            
        }else{
            
            q = sql.substring(0, sql.length()-7);
            q = q + orderBy;
            
        }
        
        
        System.out.println(q);
        tabelLoad(q,tableReservation);
        tableFilter(txt_reservation_serarch, tableReservation);
        
        if(tableReservation.getRowCount() == 0)
            JOptionPane.showMessageDialog(rootPane, "Your search not found in Database ");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(3000);
                }
                catch(Exception e){
                    
                }
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_ADsearch;
    private javax.swing.JCheckBox CHKB_customer;
    private javax.swing.JCheckBox CHKB_endDate;
    private javax.swing.JCheckBox CHKB_reservationID;
    private javax.swing.JCheckBox CHKB_startDate;
    private javax.swing.JCheckBox CHKB_status;
    private javax.swing.JComboBox CMBO_searchStatus;
    private javax.swing.JCheckBox CMB_noOfVehicals;
    private javax.swing.JComboBox CTypeSearchCombo;
    private javax.swing.JPanel CompanyPanel;
    private javax.swing.JButton CusSrchGenB;
    private javax.swing.JPanel CustomerMgtSystem;
    private javax.swing.JButton Customer_AddB;
    private javax.swing.JLabel Customer_SOnilineL;
    private javax.swing.JLabel Customer_SearchCusL;
    private com.toedter.calendar.JDateChooser DATEPICKER_searchEndDate1;
    private com.toedter.calendar.JDateChooser DATEPICKER_searchEndDate2;
    private com.toedter.calendar.JDateChooser DATEPICKER_searchStartDate1;
    private com.toedter.calendar.JDateChooser DATEPICKER_searchStarttDate2;
    private javax.swing.JPanel Driver_MgtSystem;
    private javax.swing.JPanel EmployeeMgtSystem;
    private javax.swing.JPanel GennCusPanel;
    private javax.swing.JPanel HOME;
    private javax.swing.JPanel JUtilityPanel_main;
    private javax.swing.JScrollPane JUtilityScorellPanel;
    private javax.swing.JPanel JpanalVMS;
    private javax.swing.JScrollPane Jscorellcheoue;
    private javax.swing.JLabel LBL_between1;
    private javax.swing.JLabel LBL_between2;
    private javax.swing.JLabel LBL_between3;
    private javax.swing.JLabel LBL_orderBy;
    private javax.swing.JLabel LBL_searchType;
    private javax.swing.JLabel LBL_tableFilter;
    private javax.swing.JButton MCAddBtn;
    public static javax.swing.JTextField MCCost;
    private com.toedter.calendar.JDateChooser MCDateChooser;
    private javax.swing.JButton MCDeleteBtn;
    private javax.swing.JTextPane MCDescriptionText;
    private javax.swing.JButton MCEditBtn;
    public static javax.swing.JLabel MCID;
    private javax.swing.JTextField MCNameText;
    private javax.swing.JButton MCSearchBtn;
    private javax.swing.JLabel MCSearchLable;
    private javax.swing.JTextField MCSearchText;
    private javax.swing.JTable MCampTable;
    private javax.swing.JTable MCampTable1;
    private javax.swing.JTable MCampTable2;
    private javax.swing.JButton MCjBReset;
    private javax.swing.JButton MIAddBtn;
    private javax.swing.JComboBox MIComboBox;
    private javax.swing.JTextField MICostText;
    private javax.swing.JButton MIDeleteBtn;
    private javax.swing.JButton MIEditBtn;
    private javax.swing.JLabel MIID;
    private javax.swing.JTextField MIQtyText;
    private javax.swing.JTable MIjTable;
    private javax.swing.JTextArea MItemsDesc;
    private javax.swing.JTextField MVenueText;
    private javax.swing.JPanel Marketing;
    private javax.swing.JScrollPane Marketing_JscrollPane_1;
    private javax.swing.JScrollPane Marketing_JscrollPane_2;
    private javax.swing.JScrollPane Marketing_JscrollPane_3;
    private javax.swing.JScrollPane Marketing_JscrollPane_4;
    private javax.swing.JScrollPane Marketing_JscrollPane_5;
    private javax.swing.JScrollPane Marketing_JscrollPane_6;
    private javax.swing.JPanel Marketing_main_Jpanel;
    private javax.swing.JTabbedPane Marketing_mgt;
    private javax.swing.JPanel Marketing_sub_Jpanel2;
    private javax.swing.JPanel Marketing_sub_Jpanel_1;
    private javax.swing.JPanel OnlineCusPanel;
    private javax.swing.JRadioButton OnlineSByNICRB;
    private javax.swing.JRadioButton OnlineSByUnameRB;
    private javax.swing.JRadioButton OnlineSbyNameRB;
    private javax.swing.JTextPane PDescriotionText;
    private javax.swing.JLabel PDescription;
    private javax.swing.JLabel PDescription1;
    private javax.swing.JLabel PDiscount;
    private javax.swing.JLabel PDiscount1;
    private javax.swing.JLabel PDiscount2;
    private javax.swing.JLabel PDiscount3;
    private javax.swing.JTextField PDiscountText;
    private com.toedter.calendar.JDateChooser PJDateChooser;
    private com.toedter.calendar.JDateChooser PJDateChooserTo;
    private javax.swing.JLabel PName;
    private javax.swing.JLabel PName1;
    private javax.swing.JLabel PName4;
    private javax.swing.JTextField PNameText;
    private javax.swing.JTextField PPicture;
    private javax.swing.JButton PSearchBtn;
    private javax.swing.JLabel PSearchName;
    private javax.swing.JTextField PSearchNameText;
    private javax.swing.JLabel PValidDate1;
    private javax.swing.JLabel PValidDate2;
    private javax.swing.JLabel PValidDateTo;
    private javax.swing.JPanel Packages;
    private javax.swing.JButton PjBAdd;
    private javax.swing.JButton PjBDelete;
    private javax.swing.JButton PjBEdit;
    private javax.swing.JButton PjBReset;
    private javax.swing.JButton PjBReset1;
    private javax.swing.JButton PjBUpload;
    private javax.swing.JLabel PjLableID;
    private javax.swing.JTable PjTable;
    private javax.swing.JPanel ReservationMgetSystem;
    private javax.swing.ButtonGroup SearchCmpany;
    private javax.swing.ButtonGroup SearchGenCusR;
    private javax.swing.ButtonGroup SearchOnlineCus;
    private javax.swing.JRadioButton SrchByAddR;
    private javax.swing.JRadioButton SrchByCNameR;
    private javax.swing.JRadioButton SrchByCType;
    private javax.swing.JTextField SrchByCnameT;
    private javax.swing.JRadioButton SrchByFnameR;
    private javax.swing.JTextField SrchByGen;
    private javax.swing.JRadioButton SrchByNICR;
    private javax.swing.JButton SrchCmpanyB;
    private javax.swing.JScrollPane SrchCmpanyScrollPane;
    private javax.swing.JScrollPane SrchGenCusScrollPane;
    private javax.swing.JButton SrchOnlineB;
    private javax.swing.JTable SrchRCmpanyTable;
    private javax.swing.JTable SrchRGenTable;
    private javax.swing.JTable SrchROnlineTable;
    private javax.swing.JTextField SrchonlineparmT;
    private javax.swing.JTextField TXTB_customerName;
    private javax.swing.JTextField TXTB_noOfVehicals;
    private javax.swing.JTextField TXTB_reservationID;
    private javax.swing.JPanel VehicleMgtSystem;
    private javax.swing.JScrollPane VehicleScrollPane2;
    private javax.swing.JScrollPane VehicleScrollPane3;
    private javax.swing.JScrollPane Vehicle_ScrollPane4;
    private javax.swing.JButton btn_Add_Employee;
    private javax.swing.JButton btn_PakReset;
    private javax.swing.JButton btn_Remove_Employee;
    private javax.swing.JButton btn_Reset_Vehicle;
    private javax.swing.JButton btn_Search_Employee;
    private javax.swing.JButton btn_StdPakAdvSearch;
    private javax.swing.JButton btn_Upadate_Employee;
    private javax.swing.JButton btn_add_reservaion;
    private javax.swing.JButton btn_edit_reservation;
    private javax.swing.JButton btn_vehicleAdd_Vehicle;
    private javax.swing.JButton btn_vehicleEdit_Vehicle;
    private javax.swing.JButton btn_vehicleMaintain_Vehicle;
    private javax.swing.JButton btn_vehicleRemove_Vehicle;
    private javax.swing.JButton btn_vehicleSearch_Vehicle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JCheckBox chk_StdPakAdvSearchKmLimit;
    private javax.swing.JCheckBox chk_StdPakAdvSearchPackName;
    private javax.swing.JCheckBox chk_StdPakAdvSearchPakPrice;
    private javax.swing.JCheckBox chk_StdPakAdvSearchVehiType;
    private javax.swing.JComboBox cmb_Vehicle_Type_Vehicle;
    private javax.swing.JComboBox cmb_fuel_Type_Vehicle;
    private javax.swing.JComboBox cmb_resSearchOrderBy;
    private javax.swing.JComboBox cmb_stdPakSearchType;
    private javax.swing.JComboBox cmb_transmit_Type_Vehicle;
    private javax.swing.JComboBox cmb_vehicle_Status_Vehicle;
    private javax.swing.JComboBox com_SearchType;
    private javax.swing.ButtonGroup companyType;
    private javax.swing.ButtonGroup cusType;
    private javax.swing.JButton driver_add_jbutton;
    private javax.swing.JTextField driver_address;
    private javax.swing.JComboBox driver_availalibity;
    private javax.swing.JTextField driver_fname;
    private javax.swing.JTextField driver_licenceNO;
    private javax.swing.JTextField driver_lname;
    private javax.swing.JTextField driver_nic;
    private javax.swing.JTextField driver_phone;
    public static javax.swing.JTextField driver_picture;
    private javax.swing.JButton driver_remove_jbutton;
    private javax.swing.JButton driver_reset_fields;
    private javax.swing.JTextField driver_search;
    private javax.swing.JButton driver_update_jbutton;
    public static javax.swing.JCheckBox driver_view_all;
    public static javax.swing.JTable driver_view_jtable;
    private javax.swing.JButton ibBillDelete;
    private javax.swing.JButton jButton5_driver;
    private javax.swing.JButton jButton5_package;
    private javax.swing.JButton jButton6_package;
    private javax.swing.JButton jButton7__package;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23_driver;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel24_driver;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel25__package;
    private javax.swing.JLabel jLabel25_driver;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel26_driver;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel27_driver;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel28_driver;
    private javax.swing.JLabel jLabel29_driver;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30_driver;
    private javax.swing.JLabel jLabel31_driver;
    private javax.swing.JLabel jLabel32_driver;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel33_driver;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel34_driver;
    private javax.swing.JLabel jLabel35_driver;
    private javax.swing.JLabel jLabel36_driver;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9_package;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11_DRIVER;
    private javax.swing.JPanel jPanel11_package;
    private javax.swing.JPanel jPanel12_driver;
    private javax.swing.JPanel jPanel13_driver;
    private javax.swing.JPanel jPanel14_driver;
    private javax.swing.JPanel jPanel15_driver;
    private javax.swing.JPanel jPanel17_driver;
    private javax.swing.JPanel jPanel19_package;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_Chque;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane2_driver;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane3_driver;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane4_driver;
    private javax.swing.JScrollPane jScrollPane4_package;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane2_DRIVER;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane3_driver;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1_driver;
    private javax.swing.JTextField jTextField8_driver;
    private javax.swing.JTextField jTextField9_driver;
    private javax.swing.JButton jbBillAdd;
    private javax.swing.JButton jbBillSearch;
    private javax.swing.JButton jbchequeAdd;
    private javax.swing.JButton jbchequeDelete;
    private javax.swing.JButton jbchequeEdit;
    private javax.swing.JButton jbchequeSearch;
    private javax.swing.JLabel jlbillNo;
    private javax.swing.JLabel jlchequeNo;
    private javax.swing.JPanel jp_Employee;
    private javax.swing.JPanel jpanAdvancedSearch;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JTextField jtblno;
    private javax.swing.JTextField jtche;
    public static javax.swing.JTable jtcheque;
    private javax.swing.JTable jtutility;
    private javax.swing.JLabel lbl_Eid_Employee;
    private javax.swing.JLabel lbl_FuelType_Vehiclel;
    private javax.swing.JLabel lbl_LicenceNo_Vehiclel;
    private javax.swing.JLabel lbl_NIC_Employee;
    private javax.swing.JLabel lbl_NoOfSeats_Vehiclel;
    private javax.swing.JLabel lbl_OwnerAddress_Vehiclel;
    private javax.swing.JLabel lbl_OwnerName_Vehiclel;
    private javax.swing.JLabel lbl_OwnerPhone_Vehiclel;
    private javax.swing.JTextField lbl_StdPakQuickSearch;
    private javax.swing.JLabel lbl_TransmitType_Vehiclel;
    private javax.swing.JLabel lbl_VehicleColor_Vehiclel;
    private javax.swing.JLabel lbl_VehicleID;
    private javax.swing.JLabel lbl_VehicleStatus_Vehiclel;
    private javax.swing.JLabel lbl_VehicleType;
    private javax.swing.JLabel lbl_Vehicle_ID_Vehicle;
    private javax.swing.JLabel lbl_Vehicle_Vehicle;
    private javax.swing.JLabel lbl_acc_Employee;
    private javax.swing.JLabel lbl_add_Employee;
    private javax.swing.JLabel lbl_companyVehicleTable;
    private javax.swing.JLabel lbl_description_Employee;
    private javax.swing.JLabel lbl_eid_Employee;
    private javax.swing.JLabel lbl_fname_Employee;
    private javax.swing.JLabel lbl_lname_Employee;
    private javax.swing.JLabel lbl_outVehicleTable;
    private javax.swing.JLabel lbl_ownerID_Vehicle;
    private javax.swing.JLabel lbl_phn_Employee;
    private javax.swing.JLabel lbl_pic_Employee;
    private javax.swing.JLabel lbl_post_Employee;
    private javax.swing.JLabel lbl_searchCompanies;
    private javax.swing.JLabel lbl_searchGeneralCustomer;
    private javax.swing.JLabel lbl_vehicleMode_Vehiclel;
    private javax.swing.JLabel lbl_wner_ID_Vehicle;
    private javax.swing.JComboBox p_cmb_vehicleType;
    private javax.swing.JTable packageTable;
    private javax.swing.JPanel promotionAndDealsMainJpanel;
    private javax.swing.JScrollPane promotionAndDealsSubJScrollpanel_03;
    private javax.swing.JPanel promotionAndDealsSubJpanel_01;
    private javax.swing.JPanel promotionAndDealsSubJpanel_02;
    private javax.swing.JPanel promotionAndDealsSubJpanel_03;
    private javax.swing.JRadioButton rdb_OutSourceVehicle;
    private javax.swing.JRadioButton rdb_companyVehicle;
    private javax.swing.JTextField search_Employee;
    private javax.swing.JScrollPane srchOnlineScrollPane;
    private javax.swing.JPanel stdPakAdvanceSearchJPanel;
    private javax.swing.JPanel stdPakQuickSearchJPanel;
    private javax.swing.JTable tableReservation;
    private javax.swing.JTable tbl_CompanyVehiTable_Vehicle;
    private javax.swing.JTable tbl_Employee;
    private javax.swing.JTable tbl_OutVehicleTable_Vehicle;
    private javax.swing.JTextField txt_StdPakAdvSearchKmLimit;
    private javax.swing.JTextField txt_StdPakAdvSearchPakName;
    private javax.swing.JTextField txt_StdPakAdvSearchPrice;
    private javax.swing.JTextField txt_Vehicle_Model_Vehicle;
    private javax.swing.JTextField txt_licence_No_Vehicle;
    private javax.swing.JTextField txt_no_Of_Seats_Vehicle;
    private javax.swing.JTextArea txt_owner_Address_Vehicle;
    private javax.swing.JTextField txt_owner_Name_Vehicle;
    private javax.swing.JTextField txt_owner_PhoneNo_Vehicle;
    private javax.swing.JTextField txt_reservation_serarch;
    private javax.swing.JTextField txt_vehicle_Colour_Vehicle;
    private javax.swing.JTextArea txta_add_Employee;
    private javax.swing.JTextArea txta_des_Employee;
    private javax.swing.JTextField txtf_NIC_Employee;
    private javax.swing.JTextField txtf_acc_Employee;
    private javax.swing.JTextField txtf_fname_Employee;
    private javax.swing.JTextField txtf_lname_Employee;
    private javax.swing.JTextField txtf_phn_Employee;
    private javax.swing.JTextField txtf_post_Empolyee;
    private javax.swing.JLabel uIDlbl;
    // End of variables declaration//GEN-END:variables
}
