/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import ApplicationClasses.DbConnect;
import ApplicationClasses.Packages.Pricing;
import ApplicationClasses.Packages.SpecialPackage;
import ApplicationClasses.Packages.StandardPackage2;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import sandbox.GetCustomerID;

/**
 *
 * @author Ayesh Dilan
 */
public class AddNewPackage extends javax.swing.JFrame {

List <JTextField> JTFLIST = new ArrayList <>();
StandardPackage2 stdPack = new StandardPackage2();
String sql;
List <JTextField> JTFLIST2 = new ArrayList <>();
//SpecialPackage spclPack = new SpecialPackage();


// Add form load
    public AddNewPackage() {
        initComponents();
        lbl_SpclPack_VehiNo_msg.setVisible(false);
        txt_SpclPakNoOfVehicles.setEnabled(false);
        
        textFieldsToArray();
        //btn_Package_Edit.setVisible(false);
        
        
        
        
        
        
                
    }
    
    // edit form load
    public AddNewPackage( Integer temp_PAK_id ){
        initComponents();
        jTabbedPane1.setTitleAt(0, "Edit Standed Package");
        jTabbedPane1.setTitleAt(0, "Edit Special Package");
        btn_Package_ADD.setVisible(false);
        stdPack.setValues(temp_PAK_id);
        stdPack.print();
        
        textFieldsToArray();
        
        ArrayList <Pricing> tempVehicleP= new ArrayList();
        tempVehicleP = stdPack.getVehicleTypes();
        lbl_stdPakName.setText(stdPack.getPackageName());
        lbl_stdPakKmLimit.setText(stdPack.getKmLimit().toString());
        if(stdPack.getCustID().equalsIgnoreCase("CUS00001"))
        {
            chk_stdPakDefaultCust.setSelected(true);
            chk_stdPakDefaultCust.setEnabled( false);
            lbl_stdPakCustID.setEnabled(false);
        }else
        {
            System.out.println("Can't change CUST ID");
        }
        
        for(int i=0 ; i < tempVehicleP.size() ; i++)
        {
        
        JTFLIST.get(i).setText( (tempVehicleP.get(i).getInitialPrice().toString()));
        JTFLIST.get(i+6).setText( (tempVehicleP.get(i).getAdditionalRatePerKm().toString()));
        JTFLIST.get(i+12).setText( (tempVehicleP.get(i).getAdditionRatePerHour().toString()));
        }
        
        for( JTextField JT : JTFLIST)
        {
        if(JT.getText().toString().equalsIgnoreCase("0"))
            {
                JT.setText("");
            }
        
        }
        
        
        
        
        
        
    }
    
        //table filter with 2 inputs 
    public void tableFilter(JTextField tfild1,JTextField tfild2, JTable jt , int index1, int index2) {
        TableRowSorter<TableModel> sorter  = new TableRowSorter<>(jt.getModel());
        jt.setRowSorter(sorter);
        
        
        tfild1.getDocument().addDocumentListener(new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tfild1.getText();
              
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index1));
                    
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tfild1.getText();
                
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index1));
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        
        
        tfild2.getDocument().addDocumentListener(new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tfild2.getText();
              
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index2));
                    
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tfild2.getText();
                
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index2));
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
    
        //table filter 
    public void tableFilter(JTextField tfild,JTable jt , int index) {
        TableRowSorter<TableModel> sorter  = new TableRowSorter<>(jt.getModel());
        jt.setRowSorter(sorter);
        
        
        tfild.getDocument().addDocumentListener(new DocumentListener(){
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tfild.getText();
              
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index));
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tfild.getText();
                
                
                if (text.trim().length() == 0) {
                    sorter .setRowFilter(null);
                } else {
                    sorter .setRowFilter(RowFilter.regexFilter("(?i)" + text,index));
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
    }
    
    //tableload for this class
    public void tabelLoad(String q,JTable jt) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null; 
        
        con = DbConnect.connect();
        
        try {
            pst = con.prepareStatement(q);
            rst = pst.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rst));
            
        } catch (SQLException ex) {
            Logger.getLogger(GetCustomerID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void textFieldsToArray() {
        JTFLIST.add(lbl_stdPakCarPrice);
        JTFLIST.add(lbl_stdPakVanFACPrice);
        JTFLIST.add(lbl_stdPakVanDACPrice);
        JTFLIST.add(lbl_stdPakVanPrice);
        JTFLIST.add(lbl_stdPakLorryACPrice);
        JTFLIST.add(lbl_stdPakLorryPrice);
        
        JTFLIST.add(lbl_stdPakCarAddRatePerKm);
        JTFLIST.add(lbl_stdPakVanFACAddRatePerKm);
        JTFLIST.add(lbl_stdPakVanDACAddRatePerKm);
        JTFLIST.add(lbl_stdPakVanAddRatePerKm);
        JTFLIST.add(lbl_stdPakLorryACAddRatePerKm);
        JTFLIST.add(lbl_stdPakLorryAddRatePerKm);
        
        JTFLIST.add(lbl_stdPakCarAddRatePerHour);
        JTFLIST.add(lbl_stdPakVanFACAddRatePerHour);
        JTFLIST.add(lbl_stdPakVanDACAddRatePerHour);
        JTFLIST.add(lbl_stdPakVanAddRatePerHour);
        JTFLIST.add(lbl_stdPakLorryACAddRatePerHour);
        JTFLIST.add(lbl_stdPakLorryAddRatePerHour);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup_Speacial_Package = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_stdPakName = new javax.swing.JTextField();
        lbl_stdPakKmLimit = new javax.swing.JTextField();
        lbl_stdPakCustID = new javax.swing.JTextField();
        chk_stdPakDefaultCust = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbl_stdPakCarPrice = new javax.swing.JTextField();
        lbl_stdPakVanFACPrice = new javax.swing.JTextField();
        lbl_stdPakVanDACPrice = new javax.swing.JTextField();
        lbl_stdPakVanPrice = new javax.swing.JTextField();
        lbl_stdPakLorryACPrice = new javax.swing.JTextField();
        lbl_stdPakLorryPrice = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lbl_stdPakCarAddRatePerKm = new javax.swing.JTextField();
        lbl_stdPakVanFACAddRatePerKm = new javax.swing.JTextField();
        lbl_stdPakVanDACAddRatePerKm = new javax.swing.JTextField();
        lbl_stdPakVanAddRatePerKm = new javax.swing.JTextField();
        lbl_stdPakLorryACAddRatePerKm = new javax.swing.JTextField();
        lbl_stdPakLorryAddRatePerKm = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        lbl_stdPakCarAddRatePerHour = new javax.swing.JTextField();
        lbl_stdPakVanFACAddRatePerHour = new javax.swing.JTextField();
        lbl_stdPakVanDACAddRatePerHour = new javax.swing.JTextField();
        lbl_stdPakVanAddRatePerHour = new javax.swing.JTextField();
        lbl_stdPakLorryACAddRatePerHour = new javax.swing.JTextField();
        lbl_stdPakLorryAddRatePerHour = new javax.swing.JTextField();
        btn_Package_ADD = new javax.swing.JButton();
        btn_StdPakSave = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtabl_stdpakcustsearch = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        generalCustomerRadioB = new javax.swing.JRadioButton();
        txtFname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        textLname = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        govAndPvtRb = new javax.swing.JRadioButton();
        txtCompanyName = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        JPan_SpclPack = new javax.swing.JPanel();
        jPanel_SpclSafariDetails = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_SpclPakNoOfDays = new javax.swing.JTextField();
        txt_SpclPakNoOfPassengers = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_SpclPakNoOfVehicles = new javax.swing.JTextField();
        lbl_SpclPack_VehiNo_msg = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPan_CostCalc = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPan_Safari = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txt_SpclPakJeepPrice = new javax.swing.JTextField();
        txt_SpclPakJeepCostPerDay = new javax.swing.JTextField();
        txt_SpclPakTotPrice = new javax.swing.JTextField();
        txt_SpclPakJeepCostPerVehicle = new javax.swing.JTextField();
        btn_SpclPakAdd = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jPanel_Airport = new javax.swing.JPanel();
        txt_SpclPakVanDACTotPrice = new javax.swing.JTextField();
        txt_SpclPakVanFACTotPrice = new javax.swing.JTextField();
        txt_SpclPakVanTotPrice = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        rdb_SpclPakAirportPick = new javax.swing.JRadioButton();
        rdb_SpclPakSafari = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        txt_SpclPakCustID = new javax.swing.JTextField();
        lbl_spclpakDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtarea_spclPackDescription = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Package Name");

        jLabel2.setText("Km Limit");

        jLabel3.setText("Customer ID");

        chk_stdPakDefaultCust.setText("Default");
        chk_stdPakDefaultCust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chk_stdPakDefaultCustMouseClicked(evt);
            }
        });
        chk_stdPakDefaultCust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_stdPakDefaultCustActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pricing"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Vehicle Type");

        jLabel6.setText("Car");

        jLabel7.setText("Van (Front A/C)");

        jLabel8.setText("Van (Dual A/C)");

        jLabel9.setText("Van (Non A/C)");

        jLabel10.setText("Lorry (A/C)");

        jLabel11.setText("Lorry (Non A/C)");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Initial Price");

        lbl_stdPakCarPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_stdPakCarPriceMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Additional Rate Per KM");

        lbl_stdPakCarAddRatePerKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_stdPakCarAddRatePerKmActionPerformed(evt);
            }
        });

        lbl_stdPakLorryACAddRatePerKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_stdPakLorryACAddRatePerKmActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Additional Rate Per Hour");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addComponent(lbl_stdPakVanFACPrice)
                    .addComponent(lbl_stdPakVanDACPrice)
                    .addComponent(lbl_stdPakCarPrice)
                    .addComponent(lbl_stdPakLorryPrice)
                    .addComponent(lbl_stdPakLorryACPrice)
                    .addComponent(lbl_stdPakVanPrice))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_stdPakVanFACAddRatePerKm, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addComponent(lbl_stdPakLorryAddRatePerKm)
                            .addComponent(lbl_stdPakCarAddRatePerKm)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_stdPakVanDACAddRatePerKm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                                .addComponent(lbl_stdPakVanAddRatePerKm, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(lbl_stdPakLorryACAddRatePerKm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, 45)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14)
                    .addComponent(lbl_stdPakCarAddRatePerHour)
                    .addComponent(lbl_stdPakVanFACAddRatePerHour)
                    .addComponent(lbl_stdPakVanDACAddRatePerHour)
                    .addComponent(lbl_stdPakVanAddRatePerHour)
                    .addComponent(lbl_stdPakLorryACAddRatePerHour)
                    .addComponent(lbl_stdPakLorryAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_stdPakCarAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_stdPakVanFACAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lbl_stdPakCarPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakCarAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbl_stdPakVanFACPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakVanFACAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lbl_stdPakVanDACPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakVanDACAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakVanDACAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(lbl_stdPakVanPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakVanAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakVanAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbl_stdPakLorryACPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakLorryACAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakLorryACAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(lbl_stdPakLorryPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakLorryAddRatePerKm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_stdPakLorryAddRatePerHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_Package_ADD.setText("Add");
        btn_Package_ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Package_ADDActionPerformed(evt);
            }
        });

        btn_StdPakSave.setText("Save");
        btn_StdPakSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_StdPakSaveActionPerformed(evt);
            }
        });

        jtabl_stdpakcustsearch.setModel(new javax.swing.table.DefaultTableModel(
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
        jtabl_stdpakcustsearch.setToolTipText("");
        jScrollPane2.setViewportView(jtabl_stdpakcustsearch);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        generalCustomerRadioB.setText("Individual / General Customer");
        generalCustomerRadioB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                generalCustomerRadioBMouseClicked(evt);
            }
        });
        generalCustomerRadioB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generalCustomerRadioBActionPerformed(evt);
            }
        });

        jLabel24.setText("First Name :");

        jLabel25.setText("Last Name :");

        govAndPvtRb.setText(" Company");
        govAndPvtRb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                govAndPvtRbMouseClicked(evt);
            }
        });
        govAndPvtRb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                govAndPvtRbActionPerformed(evt);
            }
        });

        jLabel26.setText(" Name :");

        jButton1.setText("Choose Selected ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textLname))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(govAndPvtRb)
                    .addComponent(generalCustomerRadioB)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalCustomerRadioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(govAndPvtRb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_stdPakName, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(lbl_stdPakKmLimit)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_stdPakCustID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chk_stdPakDefaultCust)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_StdPakSave, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Package_ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_stdPakName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_stdPakKmLimit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_stdPakCustID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_stdPakDefaultCust)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_Package_ADD, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_StdPakSave, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Standard package", jPanel1);

        jPanel_SpclSafariDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel17.setText("No of Passengers");

        jLabel16.setText("No Of Days");

        txt_SpclPakNoOfPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SpclPakNoOfPassengersActionPerformed(evt);
            }
        });
        txt_SpclPakNoOfPassengers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_SpclPakNoOfPassengersKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_SpclPakNoOfPassengersKeyTyped(evt);
            }
        });

        jLabel22.setText("No Of Vehicles");

        lbl_SpclPack_VehiNo_msg.setForeground(new java.awt.Color(255, 0, 0));
        lbl_SpclPack_VehiNo_msg.setText("Maximum Number of passengers per vehicle is 8");

        javax.swing.GroupLayout jPanel_SpclSafariDetailsLayout = new javax.swing.GroupLayout(jPanel_SpclSafariDetails);
        jPanel_SpclSafariDetails.setLayout(jPanel_SpclSafariDetailsLayout);
        jPanel_SpclSafariDetailsLayout.setHorizontalGroup(
            jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SpclSafariDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addGap(31, 31, 31)
                .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_SpclPakNoOfDays)
                    .addComponent(txt_SpclPakNoOfPassengers)
                    .addComponent(txt_SpclPakNoOfVehicles, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_SpclPack_VehiNo_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_SpclSafariDetailsLayout.setVerticalGroup(
            jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_SpclSafariDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txt_SpclPakNoOfDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txt_SpclPakNoOfPassengers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_SpclPack_VehiNo_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_SpclSafariDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txt_SpclPakNoOfVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel15.setText("Package type");

        jPan_CostCalc.setBorder(javax.swing.BorderFactory.createTitledBorder("Cost Calculation"));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Initial Price");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Vehicle Type");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Total Price");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setText("Cost Per  Day ");

        jPan_Safari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Jeep(Safari)");

        txt_SpclPakJeepPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_SpclPakJeepPriceMouseClicked(evt);
            }
        });

        txt_SpclPakJeepCostPerDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SpclPakJeepCostPerDayActionPerformed(evt);
            }
        });

        txt_SpclPakTotPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_SpclPakTotPriceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPan_SafariLayout = new javax.swing.GroupLayout(jPan_Safari);
        jPan_Safari.setLayout(jPan_SafariLayout);
        jPan_SafariLayout.setHorizontalGroup(
            jPan_SafariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_SafariLayout.createSequentialGroup()
                .addComponent(jLabel20)
                .addGap(72, 72, 72)
                .addComponent(txt_SpclPakJeepPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(txt_SpclPakJeepCostPerDay, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txt_SpclPakJeepCostPerVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(txt_SpclPakTotPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPan_SafariLayout.setVerticalGroup(
            jPan_SafariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_SafariLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPan_SafariLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_SpclPakJeepPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SpclPakJeepCostPerDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SpclPakTotPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_SpclPakJeepCostPerVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_SpclPakAdd.setText("Add");
        btn_SpclPakAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SpclPakAddActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("Cost Per Vehicle ");

        jPanel_Airport.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel_Airport.add(txt_SpclPakVanDACTotPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, 139, -1));
        jPanel_Airport.add(txt_SpclPakVanFACTotPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 139, -1));
        jPanel_Airport.add(txt_SpclPakVanTotPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 139, -1));

        jLabel21.setText("Van (Front A/C)");
        jPanel_Airport.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 14, -1, -1));

        jLabel43.setText("Van (Dual A/C)");
        jPanel_Airport.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 52, -1, -1));

        jLabel44.setText("Van (Non A/C)");
        jPanel_Airport.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, -1, -1));

        javax.swing.GroupLayout jPan_CostCalcLayout = new javax.swing.GroupLayout(jPan_CostCalc);
        jPan_CostCalc.setLayout(jPan_CostCalcLayout);
        jPan_CostCalcLayout.setHorizontalGroup(
            jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_CostCalcLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPan_CostCalcLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel18)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel46)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(jPan_CostCalcLayout.createSequentialGroup()
                        .addComponent(jPan_Safari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPan_CostCalcLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_SpclPakAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel_Airport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPan_CostCalcLayout.setVerticalGroup(
            jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPan_CostCalcLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(jLabel18))
                    .addGroup(jPan_CostCalcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(jLabel23)
                        .addComponent(jLabel45)))
                .addGap(18, 18, 18)
                .addComponent(jPan_Safari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Airport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btn_SpclPakAdd))
        );

        btnGroup_Speacial_Package.add(rdb_SpclPakAirportPick);
        rdb_SpclPakAirportPick.setText("Airport Pickup");
        rdb_SpclPakAirportPick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_SpclPakAirportPickMouseClicked(evt);
            }
        });

        btnGroup_Speacial_Package.add(rdb_SpclPakSafari);
        rdb_SpclPakSafari.setText("Safari");
        rdb_SpclPakSafari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_SpclPakSafariMouseClicked(evt);
            }
        });

        jLabel47.setText("Customer ID");

        lbl_spclpakDescription.setText("Description");

        txtarea_spclPackDescription.setColumns(20);
        txtarea_spclPackDescription.setRows(5);
        jScrollPane1.setViewportView(txtarea_spclPackDescription);

        javax.swing.GroupLayout JPan_SpclPackLayout = new javax.swing.GroupLayout(JPan_SpclPack);
        JPan_SpclPack.setLayout(JPan_SpclPackLayout);
        JPan_SpclPackLayout.setHorizontalGroup(
            JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel47))
                        .addGap(51, 51, 51)
                        .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                                .addComponent(rdb_SpclPakAirportPick)
                                .addGap(35, 35, 35)
                                .addComponent(rdb_SpclPakSafari))
                            .addComponent(txt_SpclPakCustID)))
                    .addComponent(jPan_CostCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbl_spclpakDescription)
                                .addGap(63, 63, 63)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel_SpclSafariDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        JPan_SpclPackLayout.setVerticalGroup(
            JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPan_SpclPackLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(rdb_SpclPakAirportPick)
                    .addComponent(rdb_SpclPakSafari))
                .addGap(18, 18, 18)
                .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txt_SpclPakCustID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel_SpclSafariDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPan_SpclPackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_spclpakDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jPan_CostCalc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Special Package", JPan_SpclPack);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_stdPakLorryACAddRatePerKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_stdPakLorryACAddRatePerKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_stdPakLorryACAddRatePerKmActionPerformed

    private void lbl_stdPakCarAddRatePerKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_stdPakCarAddRatePerKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_stdPakCarAddRatePerKmActionPerformed

    private void btn_Package_ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Package_ADDActionPerformed
       String regx="-?\\d+(\\.\\d+)?";
       //ArrayList <Integer> index = new ArrayList();
       int count =0;
       
      //Defining Pricing Array List
      ArrayList <Pricing> tempVehiclePricing = new ArrayList();
      
       // Creating Standard package object
      StandardPackage2 stdPack = new StandardPackage2();
      
      //Vehicle Array
      String []vehiTypes = {"Car" , "Van_FAC" , "Van_DAC" , "Van_NAC" , "Lorry_AC" , "Lorry_NAC"};
      
      //Initialing CustID
      String custID = null;
        
       //Checking Loop
        for (JTextField JT : JTFLIST){
            System.out.println(JT.getText());
        }
        //Iterating Loop
        for(int i=0 ; i < JTFLIST.size() ; i++)
        {
           //if(i<6){
            
            //Check empty fields
            if(! JTFLIST.get(i).getText().equalsIgnoreCase("")){
                
                //Check Numeric
                if(JTFLIST.get(i).getText().matches(regx)){
                    System.out.println("Succecful"+i);
                   count++; 
                }else{
                    System.out.println("Number failed.Textbox At"+i);
                    break;
                }
            }else{
                System.out.println("Field Empty failed.Textbox At"+i);
                    break;
            }
        
           
           
           
           /*else{
               if((JTFLIST.get(i).getText().equalsIgnoreCase("")  ))
               {
                   if(!(JTFLIST.get(i+6).getText().equalsIgnoreCase("")  ))
                   {
                       
                       if(JTFLIST.get(i+6).getText().matches(regx)){
                         System.out.println("Succesful 10");
                         count++;
                         //JTFLIST.get(i).setText("0");
                         index.add(i);
                        }else{
                          System.out.println("Number failed.Textbox At"+i);
                           break;
                        }
                       
                   }else{
                   
                       System.out.println("Can't have both fields empty");
                       System.out.println("Value of i"+i);
                       break;
                   }
                   
               }
               
               if(!(JTFLIST.get(i).getText().equalsIgnoreCase("")  ))
               {
                   if((JTFLIST.get(i+6).getText().equalsIgnoreCase("")  ))
                   {
                       
                       if(JTFLIST.get(i).getText().matches(regx)){
                       System.out.println("Succesful 20");
                       count++;
                       //JTFLIST.get(i+6).setText("0");
                       index.add(i+6);
                       }else{
                          System.out.println("Number failed.Textbox At"+i);
                           break;
                        }
                       
                       
                   }else{
                   
                       System.out.println("Can't have both fields filled"); 
                       System.out.println("Value of i"+i);
                       break;
                   }
                   
               }
               
               
        }*/
        }
        if(count==18){
       
       
       for(int i=0 ; i<6 ; i++ )
       {
       //tempVehiclePricing.add(new Pricing("test",Double.parseDouble(JTFLIST.get(i).toString()) , Double.parseDouble(JTFLIST.get(i+6).toString()), Double.parseDouble(JTFLIST.get(i+12).toString())));
          tempVehiclePricing.add(new Pricing(vehiTypes[i],Integer.parseInt(JTFLIST.get(i).getText()) ,
                Integer.parseInt(JTFLIST.get(i+6).getText()) ,
                 Integer.parseInt(JTFLIST.get(i+12).getText())));
       }
       if(chk_stdPakDefaultCust.isSelected())
           custID="CUS00001";
       
         else
           custID = lbl_stdPakCustID.getText();
                   
       stdPack.setCustID(custID);
       stdPack.setPackageName(lbl_stdPakName.getText());
       stdPack.setKmLimit(Integer.parseInt(lbl_stdPakKmLimit.getText()));
       
       stdPack.setVehicleTypes(tempVehiclePricing);
       stdPack.print();
       
       stdPack.insertPackage();
       
       
       
        }
        
        
    }//GEN-LAST:event_btn_Package_ADDActionPerformed

    private void chk_stdPakDefaultCustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_stdPakDefaultCustActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_stdPakDefaultCustActionPerformed

    private void chk_stdPakDefaultCustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chk_stdPakDefaultCustMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_stdPakDefaultCustMouseClicked

    private void btn_StdPakSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_StdPakSaveActionPerformed
        
        
        String regx="-?\\d+(\\.\\d+)?";
        ArrayList <Integer> index = new ArrayList();
        int count =0;
        ArrayList <Pricing> tempVehiclePricing = new ArrayList();
        //StandardPackage2 stdPack = new StandardPackage2();
        String []vehiTypes = {"Car" , "Van_FAC" , "Van_DAC" , "Van_NAC" , "Lorry_AC" , "Lorry_NAC"};
        String custID = null;
        
       
        for (JTextField JT : JTFLIST){
            System.out.println(JT.getText());
        }
        
        for(int i=0 ; i < JTFLIST.size() ; i++)
        {
          //if(i<6){
            if(! JTFLIST.get(i).getText().equalsIgnoreCase("")){
                if(JTFLIST.get(i).getText().matches(regx)){
                    //JTFLIST.get(i).setBackground(Color.GREEN);
                    System.out.println("Succecful : "+i);
                   count++; 
                }else{
                    System.out.println("Number failed.Textbox At"+i);
                  //  JTFLIST.get(i).setBackground(Color.RED);
                    JOptionPane.showMessageDialog(rootPane, "You can't have a non-numaric value in highlighted filed ");
                    break;
                }
            }else{
                System.out.println("Field Empty failed.Textbox At"+i);
                //JTFLIST.get(i).setBackground(Color.RED);
                JOptionPane.showMessageDialog(rootPane, "You can't have highlighted filed empty ");
                break;
            }
        
           
           
           
           //}
           
        /*   
           else{
               if((JTFLIST.get(i).getText().equalsIgnoreCase("")  ))
               {
                   if(!(JTFLIST.get(i+6).getText().equalsIgnoreCase("")  ))
                   {
                       
                       if(JTFLIST.get(i+6).getText().matches(regx)){
                         System.out.println("Succesful 10");
                         count++;
                         //JTFLIST.get(i).setText("0");
                         index.add(i);
                        }else{
                          System.out.println("Number failed.Textbox At"+i);
                           break;
                        }
                       
                   }else{
                   
                       System.out.println("Can't have both fields empty");
                       System.out.println("Value of i"+i);
                       break;
                   }
                   
               }
               
          /*   if(!(JTFLIST.get(i).getText().equalsIgnoreCase("")  ))
               {
                   if((JTFLIST.get(i+6).getText().equalsIgnoreCase("")  ))
                   {
                       
                       if(JTFLIST.get(i).getText().matches(regx)){
                       System.out.println("Succesful 20");
                       count++;
                       //JTFLIST.get(i+6).setText("0");
                       index.add(i+6);
                       }else{
                          System.out.println("Number failed.Textbox At"+i);
                           break;
                        }
                       
                       
                   }else{
                   
                       System.out.println("Can't have both fields filled"); 
                       System.out.println("Value of i"+i);
                       break;
                   }
                   
               }
               
               
        }*/
           
        }
        
        System.out.println("Count" + count);
        if(count==18){
//       for (Integer i : index){
//       JTFLIST.get(i).setText("0");
//       JTFLIST.get(i).setEnabled(false);
//       } 
//       
       for(int i=0 ; i<6 ; i++ )
       {
       //tempVehiclePricing.add(new Pricing("test",Double.parseDouble(JTFLIST.get(i).toString()) , Double.parseDouble(JTFLIST.get(i+6).toString()), Double.parseDouble(JTFLIST.get(i+12).toString())));
          tempVehiclePricing.add(new Pricing(vehiTypes[i],Integer.parseInt(JTFLIST.get(i).getText()) ,
                Integer.parseInt(JTFLIST.get(i+6).getText()) ,
                 Integer.parseInt(JTFLIST.get(i+12).getText())));
       }
       if(chk_stdPakDefaultCust.isSelected())
           custID="CUS00001";
       
                   
                   
       stdPack.setCustID(custID);
       stdPack.setPackageName(lbl_stdPakName.getText());
       stdPack.setKmLimit(Integer.parseInt(lbl_stdPakKmLimit.getText()));
       
       stdPack.setVehicleTypes(tempVehiclePricing);
       stdPack.print();
       
      // stdPack.insertPackage();
        stdPack.updatePackages();
       
       
       
        }
    }//GEN-LAST:event_btn_StdPakSaveActionPerformed

    private void lbl_stdPakCarPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_stdPakCarPriceMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_stdPakCarPriceMouseClicked

    private void txt_SpclPakJeepPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_SpclPakJeepPriceMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SpclPakJeepPriceMouseClicked

    private void txt_SpclPakJeepCostPerDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SpclPakJeepCostPerDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SpclPakJeepCostPerDayActionPerformed

    private void btn_SpclPakAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SpclPakAddActionPerformed
       String regx="-?\\d+(\\.\\d+)?";
        int count =0;
       ArrayList <Pricing> tempSpclCostCalc = new ArrayList();
       
       //SpecialPackage spclPack = new SpecialPackage();
        
       String [] spclVehiTypes = {"Jeep(Safari)","Van_FAC" , "Van_DAC" , "Van_NAC"};
       
       String custID = null;
       
       for(int i=0 ; i < JTFLIST2.size() ; i++)
        {
           //if(i<6){
            
            //Check empty fields
            if(! JTFLIST2.get(i).getText().equalsIgnoreCase("")){
                
                //Check Numeric
                if(JTFLIST2.get(i).getText().matches(regx)){
                    System.out.println("Succecful"+i);
                   count++; 
                }else{
                    System.out.println("Number failed.Textbox At"+i);
                    break;
                }
            }else{
                System.out.println("Field Empty failed.Textbox At"+i);
                    break;
            }  
   
        }
       
       
       if(rdb_SpclPakSafari.isSelected())
       {
        String packageName = rdb_SpclPakSafari.getText();
        String customerID = txt_SpclPakCustID.getText();
        int noOfDays = Integer.parseInt(txt_SpclPakNoOfDays.getText());
        int noOfPassengers = Integer.parseInt(txt_SpclPakNoOfPassengers.getText());
        int noOfVehi = Integer.parseInt(txt_SpclPakNoOfVehicles.getText());
        double jeepInitprice = Double.parseDouble(txt_SpclPakJeepPrice.getText());
        double jeepCostPerDay = Double.parseDouble(txt_SpclPakJeepCostPerDay.getText());
        double jeepTotCost = Double.parseDouble(txt_SpclPakJeepCostPerDay.getText());
        double jeepCostPerVehi = Double.parseDouble( txt_SpclPakJeepCostPerVehicle.getText());
        String desc = txtarea_spclPackDescription.getText();
        
           
           
           
           SpecialPackage safari = new SpecialPackage(0,packageName,"Special",jeepInitprice,noOfPassengers,noOfDays,jeepCostPerDay,noOfVehi,jeepCostPerVehi,customerID,desc,jeepTotCost);
           
          safari.insertPackage();

       }
       else if(rdb_SpclPakAirportPick.isSelected())
       {
        String packageName = rdb_SpclPakAirportPick.getText();
        String customerID = txt_SpclPakCustID.getText();
        double jeepCostPerDay = Double.parseDouble(txt_SpclPakJeepCostPerDay.getText());
        double jeepTotCost = Double.parseDouble(txt_SpclPakJeepCostPerDay.getText());
        
        String desc = txtarea_spclPackDescription.getText();
        
           
           
           
           //SpecialPackage safari = new SpecialPackage(0,packageName,"Special",jeepInitprice,noOfPassengers,noOfDays,jeepCostPerDay,noOfVehi,jeepCostPerVehi,customerID,desc,jeepTotCost);
           
         // safari.insertPackage();    
       }
        
    }//GEN-LAST:event_btn_SpclPakAddActionPerformed

    private void txt_SpclPakNoOfPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SpclPakNoOfPassengersActionPerformed
        String noOfPassenger=txt_SpclPakNoOfPassengers.getText();
        
        
        
        if(Integer.parseInt(noOfPassenger)>8)
        {
            lbl_SpclPack_VehiNo_msg.setVisible(true);
                    txt_SpclPakNoOfVehicles.setEnabled(true);

        }
        else
        {
            lbl_SpclPack_VehiNo_msg.setVisible(false);
            txt_SpclPakNoOfVehicles.setEnabled(true);
        }
    }//GEN-LAST:event_txt_SpclPakNoOfPassengersActionPerformed

    private void rdb_SpclPakAirportPickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_SpclPakAirportPickMouseClicked
    jPanel_SpclSafariDetails.setVisible(false);     
    jPan_Safari.setVisible(false);
    jPanel_Airport.setVisible(true);
    }//GEN-LAST:event_rdb_SpclPakAirportPickMouseClicked

    private void rdb_SpclPakSafariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_SpclPakSafariMouseClicked
        jPanel_Airport.setVisible(false);
        jPanel_SpclSafariDetails.setVisible(true);     
        jPan_Safari.setVisible(true);
    }//GEN-LAST:event_rdb_SpclPakSafariMouseClicked

    private void txt_SpclPakNoOfPassengersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SpclPakNoOfPassengersKeyTyped
       
    }//GEN-LAST:event_txt_SpclPakNoOfPassengersKeyTyped

    private void txt_SpclPakNoOfPassengersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SpclPakNoOfPassengersKeyPressed
        
    }//GEN-LAST:event_txt_SpclPakNoOfPassengersKeyPressed

    private void txt_SpclPakTotPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_SpclPakTotPriceMouseClicked
      String vehiPrice = txt_SpclPakJeepCostPerVehicle.getText();
      String dayPrice = txt_SpclPakJeepCostPerDay.getText();
      String initialPrice = txt_SpclPakJeepPrice.getText();
      String noOfDays = txt_SpclPakNoOfDays.getText();
      String noOfVehicles =txt_SpclPakNoOfVehicles.getText();
      
      double totPrice = (Double.parseDouble(dayPrice) * Double.parseDouble(noOfDays)) + (Double.parseDouble(vehiPrice) * Double.parseDouble(noOfVehicles)) + Double.parseDouble(initialPrice);
      
      String strDouble = String.valueOf(totPrice);



      txt_SpclPakTotPrice.setText(strDouble);
    }//GEN-LAST:event_txt_SpclPakTotPriceMouseClicked

    private void generalCustomerRadioBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalCustomerRadioBMouseClicked
       
        sql = "SELECT * FROM createtours.general_or_online_customer_view;";
        tabelLoad(sql,jtabl_stdpakcustsearch);
        tableFilter(txtFname, textLname, jtabl_stdpakcustsearch, 1, 2);
    }//GEN-LAST:event_generalCustomerRadioBMouseClicked

    private void generalCustomerRadioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalCustomerRadioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generalCustomerRadioBActionPerformed

    private void govAndPvtRbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_govAndPvtRbMouseClicked
        sql = "SELECT* FROM govandprivatecompanies;";
        tabelLoad(sql,jtabl_stdpakcustsearch);

        tableFilter(txtCompanyName,jtabl_stdpakcustsearch,1);
    }//GEN-LAST:event_govAndPvtRbMouseClicked

    private void govAndPvtRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_govAndPvtRbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_govAndPvtRbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int r = jtabl_stdpakcustsearch.getSelectedRow();

        if (r>=0){
            String name = null;
            String type = null;

            if(generalCustomerRadioB.isSelected()){
                type = "General/Online";
                name = jtabl_stdpakcustsearch.getValueAt(r, 1).toString()+" "+jtabl_stdpakcustsearch.getValueAt(r, 2).toString();
            }

            if(govAndPvtRb.isSelected()){
                type = "Company";
                name = jtabl_stdpakcustsearch.getValueAt(r, 1).toString();
            }
                
                
//            resCustomer.setter(jTable2.getValueAt(r, 0).toString(), name, type);
           lbl_stdPakCustID.setText(jtabl_stdpakcustsearch.getValueAt(jtabl_stdpakcustsearch.getSelectedRow(), 0).toString());
//            lblCustomerName.setText(resCustomer.getCustomerName());
//            lblCustomerType.setText(resCustomer.getCustomerType());

        }else
        JOptionPane.showMessageDialog(rootPane, "Please Select a Customer");
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AddNewPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewPackage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewPackage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPan_SpclPack;
    private javax.swing.ButtonGroup btnGroup_Speacial_Package;
    private javax.swing.JButton btn_Package_ADD;
    private javax.swing.JButton btn_SpclPakAdd;
    private javax.swing.JButton btn_StdPakSave;
    private javax.swing.JCheckBox chk_stdPakDefaultCust;
    private javax.swing.JRadioButton generalCustomerRadioB;
    private javax.swing.JRadioButton govAndPvtRb;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPan_CostCalc;
    private javax.swing.JPanel jPan_Safari;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel_Airport;
    private javax.swing.JPanel jPanel_SpclSafariDetails;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jtabl_stdpakcustsearch;
    private javax.swing.JLabel lbl_SpclPack_VehiNo_msg;
    private javax.swing.JLabel lbl_spclpakDescription;
    private javax.swing.JTextField lbl_stdPakCarAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakCarAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakCarPrice;
    private javax.swing.JTextField lbl_stdPakCustID;
    private javax.swing.JTextField lbl_stdPakKmLimit;
    private javax.swing.JTextField lbl_stdPakLorryACAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakLorryACAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakLorryACPrice;
    private javax.swing.JTextField lbl_stdPakLorryAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakLorryAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakLorryPrice;
    private javax.swing.JTextField lbl_stdPakName;
    private javax.swing.JTextField lbl_stdPakVanAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakVanAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakVanDACAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakVanDACAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakVanDACPrice;
    private javax.swing.JTextField lbl_stdPakVanFACAddRatePerHour;
    private javax.swing.JTextField lbl_stdPakVanFACAddRatePerKm;
    private javax.swing.JTextField lbl_stdPakVanFACPrice;
    private javax.swing.JTextField lbl_stdPakVanPrice;
    private javax.swing.JRadioButton rdb_SpclPakAirportPick;
    private javax.swing.JRadioButton rdb_SpclPakSafari;
    private javax.swing.JTextField textLname;
    public javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txt_SpclPakCustID;
    private javax.swing.JTextField txt_SpclPakJeepCostPerDay;
    private javax.swing.JTextField txt_SpclPakJeepCostPerVehicle;
    private javax.swing.JTextField txt_SpclPakJeepPrice;
    private javax.swing.JTextField txt_SpclPakNoOfDays;
    private javax.swing.JTextField txt_SpclPakNoOfPassengers;
    private javax.swing.JTextField txt_SpclPakNoOfVehicles;
    private javax.swing.JTextField txt_SpclPakTotPrice;
    private javax.swing.JTextField txt_SpclPakVanDACTotPrice;
    private javax.swing.JTextField txt_SpclPakVanFACTotPrice;
    private javax.swing.JTextField txt_SpclPakVanTotPrice;
    private javax.swing.JTextArea txtarea_spclPackDescription;
    // End of variables declaration//GEN-END:variables
}
