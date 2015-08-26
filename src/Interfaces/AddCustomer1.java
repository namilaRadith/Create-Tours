/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import ApplicationClasses.DbConnect;
import ApplicationClasses.Customer.GeneralCustomer;
import ApplicationClasses.Customer.GovPrivCompany;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author hp
 */
public class AddCustomer1 extends javax.swing.JFrame {

    /**
     * Creates new form AddCustomer
     */
    Connection conn=DbConnect.connect();
     PreparedStatement pst=null;
//method to update the general customer Table
    public void GeneralCustomertableupdate()
    {
        try {
            String sql="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID  where c.status=1 ";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            generalCusViewTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //method to update the general customer history Table
    public void GeneralCustomertableupdateHistory()
    {
        try {
            String sql="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID  where c.status=1 ";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            generalCusHistoryT.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //method to update the Company customer Table
    public void Companytableupdate()
    {
        try {
            String sql="SELECT t.customer_ID,t.companyName,t.contactPerson,t.type,c.address,c.email,p.phone1,p.phone2,t.totalAmount FROM govandprivatecompanies t inner join customer c on t.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where c.status=1  ";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            govPrivViewTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  //method to update the Company customer history Table
    public void CompanytableupdateHistory()
    {
        try {
            String sql="SELECT t.customer_ID,t.companyName,t.contactPerson,t.type,c.address,c.email,p.phone1,p.phone2,t.totalAmount FROM govandprivatecompanies t inner join customer c on t.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where c.status=1  ";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            govPrivViewHistoryTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
    //method to update online customer table
   public void OnlineCustomerTableUpdate() throws SQLException
        {
            
            String sql="SELECT o.onlineCus_ID,o.fame,o.lname,o.email,o.username,o.password,o.NIC,o.phone,o.address FROM onlinecustomer o inner join customer c on o.Customer_customer_ID=c.customer_ID where c.status=1";
            pst=conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            OnlineCusTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        }  
           //method to checkfor existing cus
public boolean checkIfGenCustomerExist(String NIC) throws SQLException {
    
   
    int count = 0;
    Statement stmt = conn.createStatement();
    ResultSet rset = stmt.executeQuery("SELECT Count(NIC) from genaralcustomer WHERE NIC='"+ NIC + "'");
    if (rset.next())
        count = rset.getInt(1);
    
     if (count == 0)
        return false;
     else
         return true;
   
}
//method to checkfor existing compcus
public boolean checkIfCompCustomerExist(String Cname) throws SQLException {
    
   
    int count = 0;
    Statement stmt = conn.createStatement();
    ResultSet rset = stmt.executeQuery("SELECT Count(companyName) from govandprivatecompanies WHERE companyName='"+ Cname + "'");
    if (rset.next())
        count = rset.getInt(1);
    
     if (count == 0)
        return false;
     else
         return true;
   
} 

            //Method to update the Company customer edited table for searchresults
        public void CompanyCustomerEdittableupdateSearch(String CCus)
    {
        try {
           String sql="SELECT t.customer_ID,t.companyName,t.contactPerson,t.type,c.address,c.email,p.phone1,p.phone2,t.totalAmount FROM govandprivatecompanies t inner join customer c on t.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where t.companyName LIKE '%"+CCus+"%' AND t.status=1";
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            govPrivViewTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
                //Method to update the general customer searchresults table
        public void GeneralCustomerEdittableupdateForSearch(String GenCusN)
    {
        try {
                String resultGenCus="SELECT g.customer_ID,g.fname,g.lname,g.NIC,g.address,c.email,p.phone1,p.phone2 FROM genaralcustomer g inner join customer c on g.customer_ID=c.customer_ID inner join customerphone p on c.customer_ID=p.Customer_customer_ID where g.fname LIKE '%"+GenCusN+"%' OR g.lname LIKE '%"+GenCusN+"%' and g.status=1";
                pst=conn.prepareStatement(resultGenCus);
                ResultSet rs=pst.executeQuery();
               
              
            generalCusViewTable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    //Method to Reset the addform fields
    public void Reset()
    {
        CusIDL.setText(null);
        companyNameL.setEnabled(true);
        companyTypeL.setEnabled(true);
        contactPersonL.setEnabled(true);
         NICL.setEnabled(false);
        fNameL.setEnabled(false);
        sNameL.setEnabled(false);
        NICT.setEnabled(false);
        fNameT.setEnabled(true);
        sNameT.setEnabled(true);
        companyNameT.setEnabled(true);
        contactPersonT.setEnabled(true);
        CmTypeCombo.setEnabled(true);
        
        
        NICT.setText(null);
        fNameT.setText(null);
        sNameT.setText(null);
        companyNameT.setText(null);
        contactPersonT.setText(null);
        addressT.setText(null);
        emailT.setText(null);
        phone1T.setText(null);
        phone2T.setText(null);
        
       govPrivCompanyR.setSelected(true);
       companyType.clearSelection();
        
    }
    
            //method to get the selected values from the Company table 
    public void GetSelectedCusCompany()
    {
        generalCusViewTable.clearSelection();
        fNameL.setEnabled(false);
        fNameT.setEnabled(false);       
        sNameL.setEnabled(false);
        sNameT.setEnabled(false);
        NICL.setEnabled(false);
        NICT.setEnabled(false);
        int r=govPrivViewTable.getSelectedRow();
        
        String cusID=govPrivViewTable.getValueAt(r, 0).toString();
        String EDcname=govPrivViewTable.getValueAt(r, 1).toString();
        String EDcontactP=govPrivViewTable.getValueAt(r, 2).toString();
        String EDaddress=govPrivViewTable.getValueAt(r, 4).toString();
        String EDemail=govPrivViewTable.getValueAt(r, 5).toString();
        String EDphone1=govPrivViewTable.getValueAt(r, 6).toString();
        String EDphone2=govPrivViewTable.getValueAt(r, 7).toString();
        String EDCtype=govPrivViewTable.getValueAt(r, 3).toString();
        
        CmTypeCombo.setEnabled(true);
        companyNameL.setEnabled(true);        
        companyNameT.setEnabled(true);    
        contactPersonL.setEnabled(true);
        contactPersonT.setEnabled(true);
        
        CusIDL.setText(cusID);
        companyNameT.setText(EDcname);
        contactPersonT.setText(EDcontactP);
        addressT.setText(EDaddress);
        emailT.setText(EDemail);
        phone1T.setText(EDphone1);
        phone2T.setText(EDphone2);
        CmTypeCombo.setSelectedItem(EDCtype);
       
        fNameT.setText(null);       
       
        sNameT.setText(null);
        
       NICT.setText(null);
        

        
        
    }
    //method to get the company type to combo box
public void GetValtoCombo() throws SQLException
{
 String CusID=CusIDL.getText();
 String q1="select type from govandprivatecompanies where customer_ID='"+CusID+"'";
 pst = conn.prepareStatement(q1);
 ResultSet rs = pst.executeQuery();
 CmTypeCombo.setSelectedItem(rs.toString());
}

    //method to get the selected values from the general customer table 
    public void GetSelectedCusGeneral()
    {
        govPrivViewTable.clearSelection();
        companyNameL.setEnabled(false);        
        companyNameT.setEnabled(false);    
        contactPersonL.setEnabled(false);
        contactPersonT.setEnabled(false);
        CmTypeCombo.setEnabled(false);
        int r=generalCusViewTable.getSelectedRow();
        
        String cusID=generalCusViewTable.getValueAt(r, 0).toString();
        String EDfname=generalCusViewTable.getValueAt(r, 1).toString();
        String EDsname=generalCusViewTable.getValueAt(r, 2).toString();
        String EDNIC=generalCusViewTable.getValueAt(r, 3).toString();
        String EDaddress=generalCusViewTable.getValueAt(r, 4).toString();
        String EDemail=generalCusViewTable.getValueAt(r, 5).toString();
        String EDphone1=generalCusViewTable.getValueAt(r, 6).toString();
        String EDphone2=generalCusViewTable.getValueAt(r, 7).toString();
       
        
        fNameL.setEnabled(true);
        fNameT.setEnabled(true);       
        sNameL.setEnabled(true);
        sNameT.setEnabled(true);
        NICL.setEnabled(true);
        NICT.setEnabled(true);
        
        
        CusIDL.setText(cusID);
        fNameT.setText(EDfname);
        sNameT.setText(EDsname);
        NICT.setText(EDNIC);
        addressT.setText(EDaddress);
        emailT.setText(EDemail);
        phone1T.setText(EDphone1);
        phone2T.setText(EDphone2);
      
        companyNameT.setText(null);    
         
        contactPersonT.setText(null);    
        
      
       
    }
    
    
    //Method to get CustomerHistory
    void GetCustomerHistory(String cusID,String CusName) throws SQLException 
    {
           DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
           java.util.Date date = new java.util.Date();
           String Today=dateFormat.format(date);
           DateL.setText(Today);
            
           CusNameHistL.setText(CusName);
           
             CusIDHistL.setText(cusID);
           
           System.out.println(CusName);
           System.out.println(cusID);
           
            String Query ="SELECT * FROM reservation WHERE customer_ID='"+cusID+"' AND Reservation_ID=(SELECT MAX(Reservation_ID) FROM  reservation WHERE customer_ID='"+cusID+"' GROUP BY customer_ID) ;";
            Statement st=conn.createStatement();
            ResultSet   rs = st.executeQuery(Query);
            
            
            
            String Query2="SELECT COUNT(Reservation_ID) FROM reservation WHERE customer_ID='"+cusID+"' GROUP BY customer_ID";
            Statement st2=conn.createStatement();
            ResultSet rs2 = st2.executeQuery(Query2);
           
            while(rs.next())
            {
             
             
             String lastTrdDate=rs.getString("dueDate");
             LastTrDateL.setText(lastTrdDate);
             
             Float LastTrAmt=rs.getFloat("amount");
             LastTrAmtL.setText(LastTrAmt.toString());
             
            }
              
       
        
        
            while(rs2.next())
            {
                int count =rs2.getInt("COUNT(Reservation_ID)");
                
                if(count==0)
                {
                    NoOfReservationsL.setText(null);
                }
                else
                {
                  int TotTr= rs2.getInt("COUNT(Reservation_ID)");
                  NoOfReservationsL.setText(String.valueOf(TotTr));  
                }
                 
            }       
           
            
                  String Query3= "SELECT SUM(o.amount)  FROM outstanding o inner join reservation r WHERE o.Reservation_Reservation_ID=r.Reservation_ID AND customer_ID='"+cusID+"' GROUP BY customer_ID ";                 
                  Statement st3=conn.createStatement();
                  ResultSet rs3 = st3.executeQuery(Query3);
                  while(rs3.next())
                  {
                        float totOutStdAmt=rs3.getFloat("SUM(o.amount)");
                        TotOutstandingAmtL.setText(String.valueOf(totOutStdAmt));
             
                   } 
               
               
               
               
               
            
       
       
          
    }


    public AddCustomer1() {
        initComponents();
        fNameL.setEnabled(false);
        fNameT.setEnabled(false);
                
        sNameL.setEnabled(false);        
        sNameT.setEnabled(false);
                
        NICL.setEnabled(false);        
        NICT.setEnabled(false);        
                
                
        Companytableupdate();
        GeneralCustomertableupdate();
        CompanytableupdateHistory();
        GeneralCustomertableupdateHistory();
         try {
             OnlineCustomerTableUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cusType = new javax.swing.ButtonGroup();
        companyType = new javax.swing.ButtonGroup();
        searchcusType = new javax.swing.ButtonGroup();
        EditCusTabbedPane = new javax.swing.JTabbedPane();
        AddEditRemoveCusPanel = new javax.swing.JPanel();
        fNameL = new javax.swing.JLabel();
        sNameL = new javax.swing.JLabel();
        companyNameL = new javax.swing.JLabel();
        NICL = new javax.swing.JLabel();
        addressL = new javax.swing.JLabel();
        emailL = new javax.swing.JLabel();
        phone1L = new javax.swing.JLabel();
        generalCusR = new javax.swing.JRadioButton();
        govPrivCompanyR = new javax.swing.JRadioButton();
        phone2L = new javax.swing.JLabel();
        companyTypeL = new javax.swing.JLabel();
        contactPersonL = new javax.swing.JLabel();
        fNameT = new javax.swing.JTextField();
        sNameT = new javax.swing.JTextField();
        NICT = new javax.swing.JTextField();
        addressT = new javax.swing.JTextField();
        emailT = new javax.swing.JTextField();
        phone1T = new javax.swing.JTextField();
        phone2T = new javax.swing.JTextField();
        companyNameT = new javax.swing.JTextField();
        contactPersonT = new javax.swing.JTextField();
        addCusB = new javax.swing.JButton();
        cancelAddB = new javax.swing.JButton();
        GenCusViewScrollP = new javax.swing.JScrollPane();
        generalCusViewTable = new javax.swing.JTable();
        CompanyViewScrollP = new javax.swing.JScrollPane();
        govPrivViewTable = new javax.swing.JTable();
        deleteEB = new javax.swing.JButton();
        searchL = new javax.swing.JLabel();
        gencuSR = new javax.swing.JRadioButton();
        companySR = new javax.swing.JRadioButton();
        CusCompNmaeforSearchL = new javax.swing.JLabel();
        SearchT = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CmTypeCombo = new javax.swing.JComboBox();
        ChangeCusDetailsB = new javax.swing.JButton();
        CusIDL = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        OnlineCusViewScrollP = new javax.swing.JScrollPane();
        OnlineCusTable = new javax.swing.JTable();
        ViewCusHistPanel = new javax.swing.JPanel();
        CompanyCusHistScrollP = new javax.swing.JScrollPane();
        govPrivViewHistoryTable = new javax.swing.JTable();
        SelectCompHistL = new javax.swing.JLabel();
        CusHistoryPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        LastTrDateL1 = new javax.swing.JLabel();
        LastTrDateL = new javax.swing.JLabel();
        TotRsrvationsL1 = new javax.swing.JLabel();
        LastTrAmtL = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TotOutstandingAmtL = new javax.swing.JLabel();
        LastTrAmtL1 = new javax.swing.JLabel();
        NoOfReservationsL = new javax.swing.JLabel();
        PrintReportB = new javax.swing.JButton();
        HistoryReportL = new javax.swing.JLabel();
        DatsHistL = new javax.swing.JLabel();
        DateL = new javax.swing.JLabel();
        CusNameHistL2 = new javax.swing.JLabel();
        CusNameHistL = new javax.swing.JLabel();
        CusIDHistL1 = new javax.swing.JLabel();
        CusIDHistL = new javax.swing.JLabel();
        GenCusHistScrollP = new javax.swing.JScrollPane();
        generalCusHistoryT = new javax.swing.JTable();
        SelectGenCusHistL = new javax.swing.JLabel();
        OnlineCusHistScrollP = new javax.swing.JScrollPane();
        OnlineCusHistoryTable = new javax.swing.JTable();
        SelectOnlineCusHistL = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        EditCusTabbedPane.setPreferredSize(new java.awt.Dimension(1300, 1100));

        AddEditRemoveCusPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AddEditRemoveCusPanelKeyPressed(evt);
            }
        });

        fNameL.setText("First Name");

        sNameL.setText("Second Name");

        companyNameL.setText("Company Name");

        NICL.setText("NIC Number");

        addressL.setText("Address");

        emailL.setText("Email Address");

        phone1L.setText("Phone Number1");

        cusType.add(generalCusR);
        generalCusR.setText("General Customer");
        generalCusR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalCusRActionPerformed(evt);
            }
        });

        cusType.add(govPrivCompanyR);
        govPrivCompanyR.setSelected(true);
        govPrivCompanyR.setText("Government/Private Company");
        govPrivCompanyR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                govPrivCompanyRActionPerformed(evt);
            }
        });

        phone2L.setText("Phone Number2(optional)");

        companyTypeL.setText("Company Type");

        contactPersonL.setText("Contact Person");

        addCusB.setText("Add Customer");
        addCusB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusBActionPerformed(evt);
            }
        });
        addCusB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addCusBKeyPressed(evt);
            }
        });

        cancelAddB.setText("Cancel");
        cancelAddB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAddBActionPerformed(evt);
            }
        });

        generalCusViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "first name", "Second Name", "NIC", "Address", "Email", "Phone 1", "Phone2"
            }
        ));
        generalCusViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generalCusViewTableMouseClicked(evt);
            }
        });
        GenCusViewScrollP.setViewportView(generalCusViewTable);

        govPrivViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Company Name", "Contact Person", "Type", "Address", "Email", "Phone 1", "Phone 2", "Total revenue"
            }
        ));
        govPrivViewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                govPrivViewTableMouseClicked(evt);
            }
        });
        CompanyViewScrollP.setViewportView(govPrivViewTable);

        deleteEB.setText("Delete");
        deleteEB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEBActionPerformed(evt);
            }
        });

        searchL.setText("Search Customer to Delete or edit");

        searchcusType.add(gencuSR);
        gencuSR.setText("general Customer");

        searchcusType.add(companySR);
        companySR.setSelected(true);
        companySR.setText("Company");

        CusCompNmaeforSearchL.setText("Customer /company name");

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel1.setText("General Customers");

        jLabel2.setText("Government/Private Companies");

        CmTypeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please select", "Private", "Government" }));
        CmTypeCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmTypeComboActionPerformed(evt);
            }
        });

        ChangeCusDetailsB.setText("Apply Changes");
        ChangeCusDetailsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeCusDetailsBActionPerformed(evt);
            }
        });

        CusIDL.setText("Customer ID");

        jLabel29.setText("Online Customers");

        OnlineCusTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Online customer ID", "first name", "last name", "email", "usernme", "password", "NIC", "phone number", "address"
            }
        ));
        OnlineCusViewScrollP.setViewportView(OnlineCusTable);

        javax.swing.GroupLayout AddEditRemoveCusPanelLayout = new javax.swing.GroupLayout(AddEditRemoveCusPanel);
        AddEditRemoveCusPanel.setLayout(AddEditRemoveCusPanelLayout);
        AddEditRemoveCusPanelLayout.setHorizontalGroup(
            AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generalCusR)
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addComponent(CusCompNmaeforSearchL)
                        .addGap(18, 18, 18)
                        .addComponent(SearchT, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton5))
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(govPrivCompanyR))
                    .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(searchL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AddEditRemoveCusPanelLayout.createSequentialGroup()
                            .addComponent(gencuSR)
                            .addGap(18, 18, 18)
                            .addComponent(companySR)))
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addComponent(addCusB)
                        .addGap(12, 12, 12)
                        .addComponent(ChangeCusDetailsB)
                        .addGap(12, 12, 12)
                        .addComponent(deleteEB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelAddB))
                    .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                            .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(fNameL)
                                .addComponent(CusIDL))
                            .addGap(72, 72, 72)
                            .addComponent(fNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                            .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(NICL)
                                .addComponent(sNameL)
                                .addComponent(companyTypeL)
                                .addComponent(phone2L)
                                .addComponent(phone1L)
                                .addComponent(emailL)
                                .addComponent(addressL)
                                .addComponent(contactPersonL)
                                .addComponent(companyNameL))
                            .addGap(10, 10, 10)
                            .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(companyNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addressT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phone1T, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(phone2T, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(contactPersonT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(NICT, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CmTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(CompanyViewScrollP, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addComponent(OnlineCusViewScrollP)
                    .addComponent(GenCusViewScrollP))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddEditRemoveCusPanelLayout.setVerticalGroup(
            AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenCusViewScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addComponent(searchL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gencuSR)
                            .addComponent(companySR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CusCompNmaeforSearchL, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(SearchT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton5)))
                        .addGap(44, 44, 44)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(generalCusR)
                            .addComponent(govPrivCompanyR))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CusIDL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fNameL))
                        .addGap(15, 15, 15)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sNameL))))
                .addGap(20, 20, 20)
                .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NICT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NICL))
                        .addGap(22, 22, 22)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(companyNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(companyNameL))
                        .addGap(22, 22, 22)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressL)
                            .addComponent(addressT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailL)
                            .addComponent(emailT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phone1L)
                            .addComponent(phone1T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CompanyViewScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phone2L)
                            .addComponent(phone2T, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(companyTypeL)
                            .addComponent(CmTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(contactPersonL)
                            .addComponent(contactPersonT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(AddEditRemoveCusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addCusB)
                            .addComponent(cancelAddB)
                            .addComponent(deleteEB)
                            .addComponent(ChangeCusDetailsB)))
                    .addGroup(AddEditRemoveCusPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OnlineCusViewScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 382, Short.MAX_VALUE))
        );

        EditCusTabbedPane.addTab("Register Customer / Edit Customer", AddEditRemoveCusPanel);

        CompanyCusHistScrollP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CompanyCusHistScrollPMouseClicked(evt);
            }
        });

        govPrivViewHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Company Name", "Contact Person", "Type", "Address", "Email", "Phone 1", "Phone 2", "Total revenue"
            }
        ));
        govPrivViewHistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                govPrivViewHistoryTableMouseClicked(evt);
            }
        });
        CompanyCusHistScrollP.setViewportView(govPrivViewHistoryTable);

        SelectCompHistL.setText("Select company to view history");

        CusHistoryPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        LastTrDateL1.setText("Last transaction Date");

        TotRsrvationsL1.setText("No. Of total Reseravations made");

        jLabel9.setText("Last transaction amount");

        LastTrAmtL1.setText("Total Outstanding amount");

        PrintReportB.setText("Print Report");
        PrintReportB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintReportBActionPerformed(evt);
            }
        });

        HistoryReportL.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        HistoryReportL.setText("History Report");

        DatsHistL.setText("Date");

        CusNameHistL2.setText("Customer Name");

        CusIDHistL1.setText("Customer ID");

        javax.swing.GroupLayout CusHistoryPanelLayout = new javax.swing.GroupLayout(CusHistoryPanel);
        CusHistoryPanel.setLayout(CusHistoryPanelLayout);
        CusHistoryPanelLayout.setHorizontalGroup(
            CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(HistoryReportL)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CusHistoryPanelLayout.createSequentialGroup()
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LastTrDateL1)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(51, 51, 51)
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LastTrAmtL, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(LastTrDateL, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CusHistoryPanelLayout.createSequentialGroup()
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TotRsrvationsL1)
                                        .addComponent(LastTrAmtL1))
                                    .addGap(18, 18, 18)
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TotOutstandingAmtL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(NoOfReservationsL, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(CusNameHistL2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CusHistoryPanelLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(CusIDHistL1)
                                            .addGap(116, 116, 116)))
                                    .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(CusNameHistL, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                                        .addComponent(CusIDHistL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                                .addGap(182, 182, 182)
                                .addComponent(PrintReportB, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(DatsHistL, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DateL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        CusHistoryPanelLayout.setVerticalGroup(
            CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DatsHistL)
                    .addComponent(DateL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(HistoryReportL)
                .addGap(11, 11, 11)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CusIDHistL1)
                    .addComponent(CusIDHistL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CusNameHistL2)
                    .addComponent(CusNameHistL, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LastTrDateL1)
                    .addComponent(LastTrDateL, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CusHistoryPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel9))
                    .addComponent(LastTrAmtL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LastTrAmtL1)
                    .addComponent(TotOutstandingAmtL, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CusHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotRsrvationsL1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NoOfReservationsL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(PrintReportB)
                .addGap(52, 52, 52))
        );

        GenCusHistScrollP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenCusHistScrollPMouseClicked(evt);
            }
        });

        generalCusHistoryT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "first name", "Second Name", "NIC", "Address", "Email", "Phone 1", "Phone2"
            }
        ));
        generalCusHistoryT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generalCusHistoryTMouseClicked(evt);
            }
        });
        GenCusHistScrollP.setViewportView(generalCusHistoryT);

        SelectGenCusHistL.setText("Select General Customer to view History");

        OnlineCusHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Online customer ID", "first name", "last name", "email", "usernme", "password", "NIC", "phone number", "address"
            }
        ));
        OnlineCusHistoryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OnlineCusHistoryTableMouseClicked(evt);
            }
        });
        OnlineCusHistScrollP.setViewportView(OnlineCusHistoryTable);

        SelectOnlineCusHistL.setText("Select Online Customer to view History");

        jLabel3.setText("Please select a record from the table to check history.");

        javax.swing.GroupLayout ViewCusHistPanelLayout = new javax.swing.GroupLayout(ViewCusHistPanel);
        ViewCusHistPanel.setLayout(ViewCusHistPanelLayout);
        ViewCusHistPanelLayout.setHorizontalGroup(
            ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewCusHistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewCusHistPanelLayout.createSequentialGroup()
                        .addComponent(SelectCompHistL, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ViewCusHistPanelLayout.createSequentialGroup()
                        .addGroup(ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(OnlineCusHistScrollP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                                .addComponent(CompanyCusHistScrollP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(GenCusHistScrollP, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(SelectGenCusHistL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SelectOnlineCusHistL))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                        .addGroup(ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CusHistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81))))
        );
        ViewCusHistPanelLayout.setVerticalGroup(
            ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewCusHistPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewCusHistPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewCusHistPanelLayout.createSequentialGroup()
                        .addComponent(SelectCompHistL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CompanyCusHistScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(SelectGenCusHistL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GenCusHistScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(SelectOnlineCusHistL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OnlineCusHistScrollP, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewCusHistPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(28, 28, 28)
                        .addComponent(CusHistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)))
                .addContainerGap(377, Short.MAX_VALUE))
        );

        EditCusTabbedPane.addTab("View Customer history", ViewCusHistPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EditCusTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EditCusTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generalCusRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalCusRActionPerformed
        
        companyNameL.setEnabled(false);
        companyTypeL.setEnabled(false);
        contactPersonL.setEnabled(false);
        NICL.setEnabled(true);
        fNameL.setEnabled(true);
        sNameL.setEnabled(true);

        NICT.setEnabled(true);
        fNameT.setEnabled(true);
        sNameT.setEnabled(true);
        companyNameT.setEnabled(false);

        contactPersonT.setEnabled(false);
        CmTypeCombo.setEnabled(false);
    }//GEN-LAST:event_generalCusRActionPerformed

    private void govPrivCompanyRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_govPrivCompanyRActionPerformed
        companyNameL.setEnabled(true);
        companyTypeL.setEnabled(true);
        contactPersonL.setEnabled(true);

        companyNameT.setEnabled(true);
        contactPersonT.setEnabled(true);
        CmTypeCombo.setEnabled(true);
        

        NICL.setEnabled(false);
        fNameL.setEnabled(false);
        sNameL.setEnabled(false);

        NICT.setEnabled(false);
        fNameT.setEnabled(false);
        sNameT.setEnabled(false);
    }//GEN-LAST:event_govPrivCompanyRActionPerformed

    private void addCusBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusBActionPerformed
        String NICF= NICT.getText();
        String fNameF= fNameT.getText();
        String sNameF =sNameT.getText();
        String addressF=addressT.getText();
        String emailF=emailT.getText();
        String contactPersonF= contactPersonT.getText();
        String companyNameF= companyNameT.getText();
        String CtypeF = null;

        

        String  phone1SF=phone1T.getText();
        String    phone2SF=phone2T.getText();

        String regex = "^[a-zA-Z,.() ]+$";
        String regexNo = "^[0-9]+$";
        String regexNIC = "^[0-9vV]+$";
        String regexAddress = "^[a-zA-Z0-9,/ ]+$";
        String regexEmail="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        int x=phone1SF.length();

        if(generalCusR.isSelected())
        {
             int NICFlength=NICF.length();
            
             if(!fNameF.matches(regex)||fNameF.isEmpty())
                {
                   JOptionPane.showMessageDialog(null,"Please enter a text value to the first name field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }

             else if(!sNameF.matches(regex)||sNameF.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "please enter a text value to the Second name field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }
           

            else if(!NICF.matches(regexNIC)||NICF.isEmpty()||NICFlength>10||NICFlength<10)
                {
                JOptionPane.showMessageDialog(null, "please enter a valid NIC number to the NIC field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }
            else if(!addressF.matches(regexAddress)||addressF.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "please enter a text value to the address field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }

            else if(!phone1SF.matches(regexNo)||phone1SF.isEmpty()||x>10)
                {
                    JOptionPane.showMessageDialog(null,"Only numbers can be inserted for phone 1 field", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }  

            else
            {

                try {
                    if(checkIfGenCustomerExist(NICF)==true)
                    {
                        JOptionPane.showMessageDialog(null, "This customer already exists in the system");
                        // addReservation r1=new addReservation();
                        // r1.setVisible(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                }


                if((phone2SF.isEmpty())&&(emailF.isEmpty()))
                {

                    GeneralCustomer cus3=new GeneralCustomer(addressF,"General",fNameF,sNameF,NICF,Integer.parseInt(phone1SF));
                    cus3.AddGeneralCustomer();
                    Reset();
                    JOptionPane.showMessageDialog(null, "The customer has been successfully registered");

                }
                else if(emailF.isEmpty())
                {

                    GeneralCustomer cus2=new GeneralCustomer(addressF,"General",fNameF,sNameF,NICF,Integer.parseInt(phone1SF),Integer.parseInt(phone2SF));
                    cus2.AddGeneralCustomer();
                    Reset();
                    JOptionPane.showMessageDialog(null, "The customer has been successfully registered");
                }

                else if(phone2SF.isEmpty())
                {
                    if(!emailF.matches(regexEmail))
                    {
                        JOptionPane.showMessageDialog(null, "please enter a valid email address  to the email field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {

                        GeneralCustomer cus1=new GeneralCustomer(emailF,addressF,"General",fNameF,sNameF,NICF,Integer.parseInt(phone1SF));
                        cus1.AddGeneralCustomer();
                        Reset();
                        JOptionPane.showMessageDialog(null, "The customer has been successfully registered");
                    }
                }

                else
                {

                    if(!emailF.matches(regexEmail))
                    {
                        JOptionPane.showMessageDialog(null, "please enter a valid email address to the email field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {

                        GeneralCustomer cus4=new GeneralCustomer(emailF,addressF,"General",fNameF,sNameF,NICF,Integer.parseInt(phone1SF),Integer.parseInt(phone2SF));
                        cus4.AddGeneralCustomer();
                        Reset();
                        JOptionPane.showMessageDialog(null, "The customer has been successfully registered");
                    }

                }

            }
        }

        else if(govPrivCompanyR.isSelected())

        {
           CtypeF=CmTypeCombo.getSelectedItem().toString();
            
            if(!companyNameF.matches(regex)||companyNameF.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "please enter a text value to the Company name field","Invalid Name",JOptionPane.WARNING_MESSAGE);
            }
            else if(!phone1SF.matches(regexNo)||phone1SF.isEmpty()||x>10)
            {
                JOptionPane.showMessageDialog(null,"Only numbers can be inserted for phone 1 field", "Data format mismatch",JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                try {
                    if(checkIfCompCustomerExist( companyNameF)==true)
                    {
                        JOptionPane.showMessageDialog(null, "This customer already exists in the system");
                                                AddNewReservation r1=new AddNewReservation();
                                             r1.setVisible(true);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            if(!addressF.matches(regexAddress)||addressF.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "please fill the address textbox","Data format mismatch",JOptionPane.WARNING_MESSAGE);
            }
            else if(!contactPersonF.matches(regex)||contactPersonF.isEmpty())
            {
                JOptionPane.showMessageDialog(null, "please fill the contact person field","Field is empty",JOptionPane.WARNING_MESSAGE);
            }
          
            else if(CtypeF=="Please select")
            {
                JOptionPane.showMessageDialog(null, "Please select a company type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }
            else if(phone2SF.isEmpty()&&emailF.isEmpty())
            {

                GovPrivCompany cus7=new GovPrivCompany(addressF,"Company",companyNameF,contactPersonF,CtypeF,Integer.parseInt(phone1SF));

                try {
                    cus7.AddGovPrivCompany();
                    Reset();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(null, "The customer has been successfully registered");

            }

            else if(phone2SF.isEmpty())
            {

                if(!emailF.matches(regexEmail))
                {
                    JOptionPane.showMessageDialog(null, "please enter a valid email address to the email field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }
                else
                {

                    GovPrivCompany cus5=new GovPrivCompany(emailF,addressF,"Company",companyNameF,contactPersonF,CtypeF,Integer.parseInt(phone1SF));
                    try {
                        cus5.AddGovPrivCompany();
                        Reset();
                    } catch (SQLException ex) {
                        Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "The customer has been successfully registered");

                }
            }

            else if(emailF.isEmpty())
            {

                GovPrivCompany cus6=new GovPrivCompany(addressF,"Company",companyNameF,contactPersonF,CtypeF,Integer.parseInt(phone1SF),Integer.parseInt(phone2SF));
                try {
                    cus6.AddGovPrivCompany();
                    Reset();
                } catch (SQLException ex) {
                    Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "The customer has been successfully registered");
            }

            else{

                if(!emailF.matches(regexEmail))
                {
                    JOptionPane.showMessageDialog(null, "please enter a valid email address to the email field","Data format mismatch",JOptionPane.WARNING_MESSAGE);
                }

                else
                {

                    GovPrivCompany cus8=new GovPrivCompany(emailF,addressF,"Company",companyNameF,contactPersonF,CtypeF,Integer.parseInt(phone1SF),Integer.parseInt(phone2SF));
                    try {
                        cus8.AddGovPrivCompany();
                        Reset();
                    } catch (SQLException ex) {
                        Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "The customer has been successfully registered");
                }

            }
        }
      
        Companytableupdate();
        GeneralCustomertableupdate();
        CompanytableupdateHistory();
        GeneralCustomertableupdateHistory();
    }//GEN-LAST:event_addCusBActionPerformed

    private void deleteEBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEBActionPerformed

        String fNameD= fNameT.getText();
        String sNameD=sNameT.getText();
        String CusIDD=CusIDL.getText();
        String cNameD=companyNameT.getText();
        String contactPersonD=contactPersonT.getText();
        String addressD=addressT.getText();
        String emailD=emailT.getText();
        String phone1D=phone1T.getText();
        String phone2D=phone2T.getText();
        String NICD=NICT.getText();

        if(fNameD.isEmpty()&&fNameD.equals("")&&sNameD.isEmpty()&&sNameD.equals("")&&cNameD.isEmpty()&&cNameD.equals("")&&phone1D.isEmpty()&&phone1D.equals(""))
        JOptionPane.showMessageDialog(null, "Please chose a record to delete from the tables");

        else
        {

            int response=JOptionPane.showConfirmDialog(null, "Do you really want to delete this record?","Confirm Deletion",JOptionPane.WARNING_MESSAGE);

            if (response==0)
            {
                int phI1=Integer.parseInt(phone1D);
                int phI2=Integer.parseInt(phone2D);

                if(fNameT.isEnabled())
                {

                    GeneralCustomer g1=new GeneralCustomer(emailD,addressD,"general",fNameD,sNameD,NICD,phI1,phI2);
                    try {
                        g1.DeleteGeneralCustomer(CusIDD);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "The customer record has been successfully deleted");

                }
                else if(companyNameL.isEnabled())
                {
                    String CmTypeD=CmTypeCombo.getSelectedItem().toString();
                    GovPrivCompany c1=new GovPrivCompany(emailD,addressD,"Company",cNameD,contactPersonD,CmTypeD,phI1,phI2);
                    try {
                        c1.DeletePrivGovCompany(CusIDD);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "The customer record has been successfully deleted");

                }

            }

           

            GeneralCustomertableupdate();
            Companytableupdate();
            CompanytableupdateHistory();
        GeneralCustomertableupdateHistory();

        }

        Reset();
    }//GEN-LAST:event_deleteEBActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String Cusname=SearchT.getText();
        if(Cusname.isEmpty())
        {
         JOptionPane.showMessageDialog(null, "Please Fill the Customer name field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);   
        }
        
        else
        {
            
        if(gencuSR.isSelected())
        {

            GeneralCustomerEdittableupdateForSearch( Cusname);

        }
        else if(companySR.isSelected())
        {
            CompanyCustomerEdittableupdateSearch(Cusname);
        }
        
        
        }

        SearchT.setText(null);
        gencuSR.setSelected(false);
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void CmTypeComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmTypeComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmTypeComboActionPerformed

    private void addCusBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addCusBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_addCusBKeyPressed

    private void AddEditRemoveCusPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddEditRemoveCusPanelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddEditRemoveCusPanelKeyPressed

    private void ChangeCusDetailsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeCusDetailsBActionPerformed

        String fNameEDT= fNameT.getText();
        String sNameEDT=sNameT.getText();
        String CusIDEDT=CusIDL.getText();
        String cNameEDT=companyNameT.getText();
        String contactPersonEDT=contactPersonT.getText();
        String addressEDT=addressT.getText();
        String emailEDT=emailT.getText();
        String phone1EDT=phone1T.getText();
        String phone2EDT=phone2T.getText();
        String NICEDT=NICT.getText();

        if(fNameEDT.isEmpty()&&fNameEDT.equals("")&&sNameEDT.isEmpty()&&sNameEDT.equals("")&&cNameEDT.isEmpty()&&cNameEDT.equals("")&&phone1EDT.isEmpty()&&phone1EDT.equals(""))
        JOptionPane.showMessageDialog(null, "Please chose a record to edit from the tables");

        else
        {
            int response=JOptionPane.showConfirmDialog(null, "Do you really want to alter this record?","Confirm Altering",JOptionPane.WARNING_MESSAGE);

            if(response==0)
            {
                int phI1=Integer.parseInt(phone1EDT);
                int phI2=Integer.parseInt(phone2EDT);

                if(fNameT.isEnabled())
                {
                    if(fNameEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the first name field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(sNameEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the second name field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(addressEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the address field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(phone1EDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,  "Please fill the phone1 field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(NICEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the NIC field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else
                    {
                        GeneralCustomer g1=new GeneralCustomer(emailEDT,addressEDT,"general",fNameEDT,sNameEDT,NICEDT,phI1,phI2);
                        try {
                            g1.EditGeneralCustomer(CusIDEDT);
                            Reset();
                        } catch (SQLException ex) {
                            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        JOptionPane.showMessageDialog(null, "The customer record has been successfully edited");
                       GeneralCustomertableupdate();

                    }
                }
                else
                {
                    if(cNameEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the company name field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }
                    else if(addressEDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the address field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else if(phone1EDT.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Please fill the phone 1 field" ,"Field is empty",JOptionPane.WARNING_MESSAGE);
                    }

                    else
                    {

                        String CmType=CmTypeCombo.getSelectedItem().toString();
                        GovPrivCompany c2=new GovPrivCompany(emailEDT,addressEDT,"Company",cNameEDT,contactPersonEDT,CmType,phI1,phI2);
                        try {
                            c2.EditPrivGovCompany(CusIDEDT);
                            JOptionPane.showMessageDialog(null, "The customer record has been successfully edited");
                            Companytableupdate();
                            Reset();

                        }
                        catch (SQLException ex) {
                            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }

        }

        fNameL.setEnabled(false);
        fNameT.setEnabled(false);
                
        sNameL.setEnabled(false);        
        sNameT.setEnabled(false);
                
        NICL.setEnabled(false);        
        NICT.setEnabled(false); 
        CompanytableupdateHistory();
        GeneralCustomertableupdateHistory();
    }//GEN-LAST:event_ChangeCusDetailsBActionPerformed

    private void generalCusViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalCusViewTableMouseClicked
         GetSelectedCusGeneral();
    }//GEN-LAST:event_generalCusViewTableMouseClicked

    private void govPrivViewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_govPrivViewTableMouseClicked
       GetSelectedCusCompany();
    }//GEN-LAST:event_govPrivViewTableMouseClicked

    private void cancelAddBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAddBActionPerformed
        
        Reset();
        generalCusViewTable.clearSelection();
        govPrivViewTable.clearSelection();
    }//GEN-LAST:event_cancelAddBActionPerformed

    private void govPrivViewHistoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_govPrivViewHistoryTableMouseClicked
        LastTrDateL.setText(null);
       LastTrAmtL.setText(null); 
       TotOutstandingAmtL.setText(null);
       NoOfReservationsL.setText(null); 
       
         int r=govPrivViewHistoryTable.getSelectedRow();
         String cusID=govPrivViewHistoryTable.getValueAt(r, 0).toString();
         String CusName=govPrivViewHistoryTable.getValueAt(r, 1).toString();
         
         
        try {
            GetCustomerHistory(cusID,CusName);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }//GEN-LAST:event_govPrivViewHistoryTableMouseClicked

    private void generalCusHistoryTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalCusHistoryTMouseClicked
       LastTrDateL.setText(null);
       LastTrAmtL.setText(null); 
       TotOutstandingAmtL.setText(null);
       NoOfReservationsL.setText(null);        
        int r=generalCusHistoryT.getSelectedRow();
            
            String cusID=generalCusHistoryT.getValueAt(r, 0).toString();
            String CusFName=generalCusHistoryT.getValueAt(r, 1).toString();
          
        try {
            GetCustomerHistory(cusID,CusFName);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       
    }//GEN-LAST:event_generalCusHistoryTMouseClicked

    private void CompanyCusHistScrollPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CompanyCusHistScrollPMouseClicked
       
           
       
    }//GEN-LAST:event_CompanyCusHistScrollPMouseClicked

    private void GenCusHistScrollPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenCusHistScrollPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GenCusHistScrollPMouseClicked

    private void OnlineCusHistoryTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OnlineCusHistoryTableMouseClicked
        LastTrDateL.setText(null);
       LastTrAmtL.setText(null); 
       TotOutstandingAmtL.setText(null);
       NoOfReservationsL.setText(null);  
        int r=OnlineCusHistoryTable.getSelectedRow();
       String cusID=OnlineCusHistoryTable.getValueAt(r, 0).toString();
       String CusName=OnlineCusHistoryTable.getValueAt(r, 1).toString();
        try {
            GetCustomerHistory(cusID,CusName);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomer1.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_OnlineCusHistoryTableMouseClicked

    private void PrintReportBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintReportBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintReportBActionPerformed

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
            java.util.logging.Logger.getLogger(AddCustomer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddCustomer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddCustomer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddCustomer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddCustomer1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddEditRemoveCusPanel;
    private javax.swing.JButton ChangeCusDetailsB;
    private javax.swing.JComboBox CmTypeCombo;
    private javax.swing.JScrollPane CompanyCusHistScrollP;
    private javax.swing.JScrollPane CompanyViewScrollP;
    private javax.swing.JLabel CusCompNmaeforSearchL;
    private javax.swing.JPanel CusHistoryPanel;
    private javax.swing.JLabel CusIDHistL;
    private javax.swing.JLabel CusIDHistL1;
    private javax.swing.JLabel CusIDL;
    private javax.swing.JLabel CusNameHistL;
    private javax.swing.JLabel CusNameHistL2;
    private javax.swing.JLabel DateL;
    private javax.swing.JLabel DatsHistL;
    private javax.swing.JTabbedPane EditCusTabbedPane;
    private javax.swing.JScrollPane GenCusHistScrollP;
    private javax.swing.JScrollPane GenCusViewScrollP;
    private javax.swing.JLabel HistoryReportL;
    private javax.swing.JLabel LastTrAmtL;
    private javax.swing.JLabel LastTrAmtL1;
    private javax.swing.JLabel LastTrDateL;
    private javax.swing.JLabel LastTrDateL1;
    private javax.swing.JLabel NICL;
    private javax.swing.JTextField NICT;
    private javax.swing.JLabel NoOfReservationsL;
    private javax.swing.JScrollPane OnlineCusHistScrollP;
    private javax.swing.JTable OnlineCusHistoryTable;
    private javax.swing.JTable OnlineCusTable;
    private javax.swing.JScrollPane OnlineCusViewScrollP;
    private javax.swing.JButton PrintReportB;
    private javax.swing.JTextField SearchT;
    private javax.swing.JLabel SelectCompHistL;
    private javax.swing.JLabel SelectGenCusHistL;
    private javax.swing.JLabel SelectOnlineCusHistL;
    private javax.swing.JLabel TotOutstandingAmtL;
    private javax.swing.JLabel TotRsrvationsL1;
    private javax.swing.JPanel ViewCusHistPanel;
    private javax.swing.JButton addCusB;
    private javax.swing.JLabel addressL;
    private javax.swing.JTextField addressT;
    private javax.swing.JButton cancelAddB;
    private javax.swing.JLabel companyNameL;
    private javax.swing.JTextField companyNameT;
    private javax.swing.JRadioButton companySR;
    private javax.swing.ButtonGroup companyType;
    private javax.swing.JLabel companyTypeL;
    private javax.swing.JLabel contactPersonL;
    private javax.swing.JTextField contactPersonT;
    private javax.swing.ButtonGroup cusType;
    private javax.swing.JButton deleteEB;
    private javax.swing.JLabel emailL;
    private javax.swing.JTextField emailT;
    private javax.swing.JLabel fNameL;
    private javax.swing.JTextField fNameT;
    private javax.swing.JRadioButton gencuSR;
    private javax.swing.JTable generalCusHistoryT;
    private javax.swing.JRadioButton generalCusR;
    private javax.swing.JTable generalCusViewTable;
    private javax.swing.JRadioButton govPrivCompanyR;
    private javax.swing.JTable govPrivViewHistoryTable;
    private javax.swing.JTable govPrivViewTable;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel phone1L;
    private javax.swing.JTextField phone1T;
    private javax.swing.JLabel phone2L;
    private javax.swing.JTextField phone2T;
    private javax.swing.JLabel sNameL;
    private javax.swing.JTextField sNameT;
    private javax.swing.JLabel searchL;
    private javax.swing.ButtonGroup searchcusType;
    // End of variables declaration//GEN-END:variables
}
