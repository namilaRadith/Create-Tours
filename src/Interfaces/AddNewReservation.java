/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import sandbox.GetCustomerID;
import ApplicationClasses.DbConnect;
import ApplicationClasses.Reservation.Reservation;
import ApplicationClasses.Reservation.ReservationCustomer;
import ApplicationClasses.Reservation.ReservationInvoice;
import ApplicationClasses.Reservation.ReservationPackage;
import ApplicationClasses.Reservation.ReservationVehical;
import ApplicationClasses.Reservation.ReservationStandardPackage;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import sandbox.test2;

/**
 *
 * @author Namila Radith
 */
public class AddNewReservation extends javax.swing.JFrame {

    /**
     * Creates new form AddNewReservation
     */
    
    
    private  ReservationCustomer resCustomer = new ReservationCustomer();
    ReservationPackage resPackage = new ReservationPackage();
    private  ArrayList<ReservationVehical> resVehicals = new ArrayList<>();
    private Reservation  reservation = new Reservation();
    
    
    
    DefaultTableModel tableModel; 
    
    private Object[] obj = new Object[12];
    int row = 0;
    JTextField txtMilageIn = new JTextField();
    ArrayList<Integer>  packageTypeIDS = new ArrayList<>();
    String custID;
    ReservationStandardPackage ResStandedPackage = new ReservationStandardPackage();
    int paktypeID;
    ArrayList<Integer> reservationCost = new ArrayList<>();
    ReservationInvoice invoiceForThisReservation = new ReservationInvoice();
    //ArrayList<invoiceDetails> invoiceDataArray = new ArrayList<>();   
    ReservationCustomer res = new ReservationCustomer();
    String dueDate = "";
    Object [][] OBJ_vehicalTableLoad;
    String formMode = "ADD";
    int countRemoveButtonPress =0; 
    JComboBox CMB_reasonForRemoving = new JComboBox();
    int count = 0;
   
   
   // customeDate.add(this);        
    
    //add new reservation form load 
    public AddNewReservation() {
        initComponents();
          String col[] = {"Vehicle ID","Type","Made", "Registation No", "Current Millage"};  
          tableModel = new DefaultTableModel(col, 0);  
          customDate.setVisible(false);
          
            
            
            
            tableSelectedVehicals.setModel(tableModel);
            tableSelectedVehicals.setRowHeight(30);
            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
//            
            
            
            
            TableColumn milageColumn = tableSelectedVehicals.getColumnModel().getColumn(4);     
            milageColumn.setCellEditor(new DefaultCellEditor(txtMilageIn));
            
                       
            
            
            
            String sql = "SELECT * FROM createtours.vehicle;";
            tabelLoad(sql, tableVehicle);
    }
    
    //edit reservaton form load 
    public AddNewReservation( String resvation ) throws ParseException{
        
       
        initComponents();
        
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        
        String col[] = {"Vehicle ID","Type","Made", "Registation No",  "Current Millage","Reason"};
        tableModel = new DefaultTableModel(col, 0);  
        tableSelectedVehicals.setModel(tableModel);
        tableSelectedVehicals.setRowHeight(30);
        TableColumn milageColumn = tableSelectedVehicals.getColumnModel().getColumn(4);     
        milageColumn.setCellEditor(new DefaultCellEditor(txtMilageIn));
        
        CMB_reasonForRemoving.addItem("CUSTOMER REQUEST");
        CMB_reasonForRemoving.addItem("CRASH");
        CMB_reasonForRemoving.addItem("MALFUNCTION");
        
        TableColumn resonForDelete = tableSelectedVehicals.getColumnModel().getColumn(5);     
        resonForDelete.setCellEditor(new DefaultCellEditor(CMB_reasonForRemoving));
        
        
        
        this.formMode = "EDIT";
        this.setTitle("Edit Reservation");
        
        
        
        String sql = "SELECT * FROM createtours.vehicle;";
        tabelLoad(sql, tableVehicle);
        
        System.out.println(resvation);
        Reservation r1 = new Reservation(Integer.parseInt(resvation));
        //r1.print();
        this.reservation.setReservationID(r1.getReservationID());

        
        //load Customer Details 
        this.resCustomer = r1.getCoustomer();
        lblCustomerID.setText(resCustomer.getCustomerID());
        lblCustomerName.setText(resCustomer.getCustomerName());
        lblCustomerType.setText(resCustomer.getCustomerType());
        
        //load selected vehicle table 
        OBJ_vehicalTableLoad = new Object[r1.getVehicals().size()][7];      
        for(int i = 0; i < r1.getVehicals().size() ; i++){
          OBJ_vehicalTableLoad[i][0] = r1.getVehicals().get(i).getVehicleID(); 
          OBJ_vehicalTableLoad[i][1] = r1.getVehicals().get(i).getVehicleType();   
          OBJ_vehicalTableLoad[i][2] = r1.getVehicals().get(i).getVehicleModel();   
          OBJ_vehicalTableLoad[i][3] = r1.getVehicals().get(i).getVehicleRegNo();   
          OBJ_vehicalTableLoad[i][4] = r1.getVehicals().get(i).getCurrentMilage();   
           
          tableModel.insertRow(i, OBJ_vehicalTableLoad[i]);
           
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        
        txtDeposit.setText(r1.getDeposit().toString());
        DATEPICKER_reservationStartDate.setDate(dateFormat.parse(r1.getReservationDate()));
        COMBO_dateDurationChoose.setSelectedIndex(5);
        DATEPICKER_customReservationEndDate.setDate(dateFormat.parse(r1.getReservationDueDate()));
        tareaDescription.setText(r1.getDescription());
        
      
        
        r1.selectedPackageTableLoad(tablePackage);
        this.paktypeID = r1.getPackageTypeID();
        BTN_addResvation.setText("Update this reservation");
        System.out.println(this.paktypeID);
        //r1.currentVehicalTableLoad(tableSelectedVehicals);
        
        
        
       
        
        
        
        
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        generalCustomerRadioB = new javax.swing.JRadioButton();
        txtFname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textLname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        govAndPvtRb = new javax.swing.JRadioButton();
        txtCompanyName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblCustomerID = new javax.swing.JLabel();
        lblCustomerName = new javax.swing.JLabel();
        lblCustomerType = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablePackage = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cmb_package_type = new javax.swing.JComboBox();
        cmb_packages = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cmb_basis = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        cmb_day_night = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        LBL_packageTypeID = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        LBL_packageID = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        LBL_KmLimit = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        LBL_intialprice = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        LBL_additionalPerKm = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        LBL_additionalPerHour = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LBL_description = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        LBL_vehicalType = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        searchByRegNo = new javax.swing.JRadioButton();
        txtRegno = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableVehicle = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableSelectedVehicals = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        BTN_vehicleTabNext = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtDeposit = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tareaDescription = new javax.swing.JTextArea();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        DATEPICKER_reservationStartDate = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        COMBO_dateDurationChoose = new javax.swing.JComboBox();
        customDate = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        DATEPICKER_customReservationEndDate = new com.toedter.calendar.JDateChooser();
        BTN_addResvation = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Reservation");

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jTabbedPane1.setInheritsPopupMenu(true);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Result"));

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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 240, 490, 349));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Search"));

        buttonGroup1.add(generalCustomerRadioB);
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

        jLabel2.setText("First Name :");

        jLabel3.setText("Last Name :");

        buttonGroup1.add(govAndPvtRb);
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

        jLabel4.setText(" Name :");

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
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(textLname))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(govAndPvtRb)
                            .addComponent(generalCustomerRadioB))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalCustomerRadioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(govAndPvtRb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCompanyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 72, -1, -1));

        jLabel1.setText("Customer ID :");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 27, -1, -1));

        jLabel5.setText("Customer Name :");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 52, -1, -1));

        jLabel6.setText("Customer Type :");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 27, -1, -1));

        lblCustomerID.setText("--");
        jPanel5.add(lblCustomerID, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 27, -1, -1));

        lblCustomerName.setText("--");
        jPanel5.add(lblCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, -1, -1));

        lblCustomerType.setText("--");
        jPanel5.add(lblCustomerType, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jButton2.setText("Cancle");

        jButton3.setText("Next");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Package"));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Packages"));

        tablePackage.setModel(new javax.swing.table.DefaultTableModel(
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
        tablePackage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePackageMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablePackage);

        jLabel12.setText("Packages :");

        jLabel19.setText("Package Type :");

        cmb_package_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Select One -", "Standed Deafult", "Standed Custom", "Special", " " }));
        cmb_package_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_package_typeItemStateChanged(evt);
            }
        });
        cmb_package_type.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_package_typeMouseClicked(evt);
            }
        });

        cmb_packages.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_packagesItemStateChanged(evt);
            }
        });

        jLabel10.setText("Package Basis :");

        cmb_basis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kilometers", "Hours" }));

        jLabel22.setText("Day / Night:");

        cmb_day_night.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Day", "Over Night" }));
        cmb_day_night.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_day_nightItemStateChanged(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Package Details"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("Package Type ID :");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 27, -1, -1));

        LBL_packageTypeID.setForeground(new java.awt.Color(0, 102, 153));
        LBL_packageTypeID.setText("--");
        jPanel4.add(LBL_packageTypeID, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 27, -1, -1));

        jLabel23.setText("Package ID :");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 47, -1, -1));

        LBL_packageID.setForeground(new java.awt.Color(0, 102, 153));
        LBL_packageID.setText("--");
        jPanel4.add(LBL_packageID, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 47, -1, -1));

        jLabel24.setText("KM Limit :");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 67, -1, -1));

        LBL_KmLimit.setForeground(new java.awt.Color(0, 102, 153));
        LBL_KmLimit.setText("--");
        jPanel4.add(LBL_KmLimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 67, -1, -1));

        jLabel25.setText("Initial Price :");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 87, -1, -1));

        LBL_intialprice.setForeground(new java.awt.Color(0, 102, 153));
        LBL_intialprice.setText("--");
        jPanel4.add(LBL_intialprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 87, -1, -1));

        jLabel26.setText("Additional per KM :");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(253, 27, -1, -1));

        LBL_additionalPerKm.setForeground(new java.awt.Color(0, 102, 153));
        LBL_additionalPerKm.setText("--");
        jPanel4.add(LBL_additionalPerKm, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 27, -1, -1));

        jLabel27.setText("Addition per Hour :");
        jPanel4.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, 47, -1, -1));

        LBL_additionalPerHour.setForeground(new java.awt.Color(0, 102, 153));
        LBL_additionalPerHour.setText("--");
        jPanel4.add(LBL_additionalPerHour, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 47, -1, -1));

        jLabel28.setText("Description :");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 107, -1, -1));

        LBL_description.setForeground(new java.awt.Color(0, 102, 153));
        LBL_description.setText("--");
        jPanel4.add(LBL_description, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 107, -1, -1));

        jLabel29.setText("Vehical Type :");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 67, -1, -1));

        LBL_vehicalType.setForeground(new java.awt.Color(0, 102, 153));
        LBL_vehicalType.setText("--");
        jPanel4.add(LBL_vehicalType, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 67, -1, -1));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmb_package_type, 0, 160, Short.MAX_VALUE)
                    .addComponent(cmb_day_night, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmb_packages, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmb_basis, 0, 160, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(cmb_package_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cmb_packages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_day_night, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cmb_basis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CUSTOMER & PACKAGE", jPanel1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehicals"));

        searchByRegNo.setText("Search By Registation NO : ");
        searchByRegNo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchByRegNoMouseClicked(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Result"));

        tableVehicle.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tableVehicle);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/icons/addsmall.png"))); // NOI18N
        jButton5.setText(" Add");
        jButton5.setIconTextGap(5);
        jButton5.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interfaces/icons/removeSmall.png"))); // NOI18N
        jButton6.setText("Remove");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Vehicls"));

        tableSelectedVehicals.setModel(new javax.swing.table.DefaultTableModel(
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
        tableSelectedVehicals.setIntercellSpacing(new java.awt.Dimension(2, 2));
        tableSelectedVehicals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSelectedVehicalsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableSelectedVehicals);

        jButton9.setText("Clear Table");
        jButton9.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(searchByRegNo)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtRegno, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(624, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchByRegNo)
                        .addComponent(txtRegno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(586, 586, 586)))
        );

        BTN_vehicleTabNext.setText("Next");
        BTN_vehicleTabNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_vehicleTabNextActionPerformed(evt);
            }
        });

        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BTN_vehicleTabNext, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_vehicleTabNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("VEHICLES", jPanel2);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Other Details"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setText("Deposit :");
        jPanel10.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, -1, -1));
        jPanel10.add(txtDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 27, 340, -1));

        jLabel18.setText("Description :");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 210, -1, -1));

        tareaDescription.setColumns(20);
        tareaDescription.setRows(5);
        jScrollPane5.setViewportView(tareaDescription);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 210, 333, 182));

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Date"));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setText("Start Date :");
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        DATEPICKER_reservationStartDate.setDateFormatString("yyyy-MM-dd");
        jPanel12.add(DATEPICKER_reservationStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 20, 290, -1));

        jLabel20.setText("Duration :");
        jPanel12.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        COMBO_dateDurationChoose.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select One", "1 day", "2 day", "Week", "Month", "Custom" }));
        COMBO_dateDurationChoose.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                COMBO_dateDurationChooseItemStateChanged(evt);
            }
        });
        COMBO_dateDurationChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COMBO_dateDurationChooseMouseClicked(evt);
            }
        });
        jPanel12.add(COMBO_dateDurationChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 50, 290, -1));

        jLabel21.setText("Custom Date:");

        DATEPICKER_customReservationEndDate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout customDateLayout = new javax.swing.GroupLayout(customDate);
        customDate.setLayout(customDateLayout);
        customDateLayout.setHorizontalGroup(
            customDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customDateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DATEPICKER_customReservationEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        customDateLayout.setVerticalGroup(
            customDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customDateLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(customDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(DATEPICKER_customReservationEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel12.add(customDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 50));

        jPanel10.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 65, 400, 140));

        BTN_addResvation.setText("Add This Reservation");
        BTN_addResvation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_addResvationActionPerformed(evt);
            }
        });

        jButton8.setText("Cancle");

        jButton12.setText("Done");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addComponent(BTN_addResvation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(680, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(BTN_addResvation, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("OTHER DETAILS", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_addResvationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_addResvationActionPerformed

        Double deposit = -1.0;
        String description = "" ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        //getting the start date of the reservation
        String StartDate = null;

        //if customer type is COMPANY then deposit set to 0 else get the deposit value
        if(resCustomer.getCustomerType().equalsIgnoreCase("Company"))
        deposit = 0.0;

        else if (! txtDeposit.getText().isEmpty())
        deposit = Double.parseDouble(txtDeposit.getText());

        else
        JOptionPane.showMessageDialog(rootPane,"Plese Enter Deposit Amount ");

        //check if user select a start date
        if(DATEPICKER_reservationStartDate.getDate() != null){
            StartDate = dateFormat.format(DATEPICKER_reservationStartDate.getDate());
            //check if user choose a valid date option
            if(COMBO_dateDurationChoose.getSelectedIndex() != 0) {
                int index =  COMBO_dateDurationChoose.getSelectedIndex();

                switch(index){
                    //user choose 1day
                    case 1 : dueDate = generateDate(StartDate, 1);
                    break;
                    //user choose 2days
                    case 2 : dueDate = generateDate(StartDate, 2);
                    break;
                    //user choose 1 week
                    case 3 : dueDate = generateDate(StartDate, 7);
                    break;
                    //user choose 1month
                    case 4 : dueDate = generateDate(StartDate, 30);
                    break;
                    //user choose custom date option
                    case 5 : if(DATEPICKER_customReservationEndDate.getDate() != null){
                        dueDate = dateFormat.format(DATEPICKER_customReservationEndDate.getDate());

                    }else{
                        dueDate = "";
                        JOptionPane.showMessageDialog(rootPane,"End date requires ");

                    }
                    break;

                    default: break;

                }

            }
            else {
                dueDate = "";
                JOptionPane.showMessageDialog(rootPane, "Plese select a date option from combo box");
            }
        }
        else {
            StartDate = "";
            JOptionPane.showMessageDialog(rootPane,"Start date requires ");
        }

        if (!tareaDescription.getText().equalsIgnoreCase("")){
            description = tareaDescription.getText() ;
        }
        else {
            JOptionPane.showMessageDialog(rootPane,"You cant have desctiption filed empty ");
        }

        //System.out.println(StartDate);
        // System.out.println(dueDate);

        int test = 0;
        if( !StartDate.equalsIgnoreCase("") && !dueDate.equalsIgnoreCase("") &&  !description.equalsIgnoreCase("") &&  deposit != -1.0){
            fillingTheVehicleArrayandIncoiceObject();
            if(formMode.equalsIgnoreCase("ADD")){
                reservation.mainSetter(resCustomer, ResStandedPackage, resVehicals, StartDate, dueDate, description, deposit,invoiceForThisReservation);
                reservation.insertThisReservationToDB();
                reservation.print();
            }

            if(formMode.equalsIgnoreCase("EDIT")){

                reservation.mainSetter(resCustomer, ResStandedPackage, resVehicals, StartDate, dueDate, description, deposit,invoiceForThisReservation);

                reservation.updateReservation();
                reservation.print();

            }

            //reservation.print();
            try {
                reservation.invoicePrint();
            } catch (Exception ex) {
                Logger.getLogger(AddNewReservation.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else{
            System.out.println("FAIL");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_addResvationActionPerformed

    private void COMBO_dateDurationChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COMBO_dateDurationChooseMouseClicked

    }//GEN-LAST:event_COMBO_dateDurationChooseMouseClicked

    private void COMBO_dateDurationChooseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_COMBO_dateDurationChooseItemStateChanged

        //limit item listner event just for item select
        if(evt.getStateChange() == ItemEvent.SELECTED){

            if(COMBO_dateDurationChoose.getSelectedIndex() == 5){
                customDate.setVisible(true);
            }
            else
            customDate.setVisible(false);
        }
    }//GEN-LAST:event_COMBO_dateDurationChooseItemStateChanged

    private void BTN_vehicleTabNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_vehicleTabNextActionPerformed

        int r = tableSelectedVehicals.getSelectedRow();

        // System.out.println(tableSelectedVehicals.getRowCount());

        //clear both resVehicals and invoiceForThisReservation arraylists
        //resVehicals.clear();
        //invoiceForThisReservation.clear();

        //check if user add vehicles
        if(tableSelectedVehicals.getRowCount() > 0){
            //loop through tableSelectedVehicals
            for(int i = 0; i<tableSelectedVehicals.getRowCount();i++){
                Integer a = i ;

                //check does user enter a value for millage colom
                if(tableSelectedVehicals.getValueAt(i, 4) != null){

                    //if user enter a value for millage coloms then check that value is a numaric one
                    //if these  both conditions are true then increse the count
                    if( tableSelectedVehicals.getValueAt(i, 4).toString().matches("\\d+") ){

                        count++;

                    } else{

                        JOptionPane.showMessageDialog(rootPane, "Milage colom at " + a.toString() + " Containing Non Numaric Value ");
                        break;
                    }

                } else{

                    JOptionPane.showMessageDialog(rootPane, "Milage colom at " + a.toString() + " is Empty ");
                    break;
                }

            }

            //fillingTheVehicleArrayandIncoiceObject();

            if(count == tableSelectedVehicals.getRowCount() && count != 0){
                JOptionPane.showMessageDialog(rootPane, "Vehicles added to the list sucssfully");
                if(resCustomer.getCustomerType().equalsIgnoreCase("Company"))

                txtDeposit.setEnabled(false);

                jTabbedPane1.setSelectedIndex(2);
                jTabbedPane1.setEnabledAt(1, false);
            }
        }
        else
        JOptionPane.showMessageDialog(rootPane, "Can't proceed Vehicle Table is empty ");

    }//GEN-LAST:event_BTN_vehicleTabNextActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(0 == JOptionPane.showConfirmDialog(rootPane, "This opration will remove the all data in Selected Vehicals table !", "Alert", JOptionPane.OK_CANCEL_OPTION)){

            for(int j =  tableSelectedVehicals.getRowCount() -1 ; j >= 0;j--){
                tableModel.removeRow(j);

            }
            row = 0;
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tableSelectedVehicalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSelectedVehicalsMouseClicked
        // System.out.println(tableSelectedVehicals.getSelectedRow());

        int r = tableSelectedVehicals.getSelectedRow();

        System.out.println("CM EDITED: " + tableSelectedVehicals.getValueAt(r, 4));
        // System.out.println("Reason " + tableSelectedVehicals.getValueAt(r, 5));
    }//GEN-LAST:event_tableSelectedVehicalsMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int r = tableSelectedVehicals.getSelectedRow();

        if(r >=0){
            if(0 == JOptionPane.showConfirmDialog(rootPane, "This opration will remove the selected row from the table !", "Alert", JOptionPane.OK_CANCEL_OPTION)){

                if(formMode.equalsIgnoreCase("EDIT")){
                    if(tableSelectedVehicals.getValueAt(r, 5) != null){
                        // resVehicals.clear();

                        resVehicals.add(new ReservationVehical(tableSelectedVehicals.getValueAt(r, 0).toString(),
                            tableSelectedVehicals.getValueAt(r, 3).toString(),
                            tableSelectedVehicals.getValueAt(r, 2).toString(),
                            tableSelectedVehicals.getValueAt(r, 1).toString(),
                            tableSelectedVehicals.getValueAt(r, 4).toString(),
                            "DELETE", tableSelectedVehicals.getValueAt(r, 5).toString()));

                    tableModel.removeRow(r);
                    // countRemoveButtonPress++;
                }else
                JOptionPane.showMessageDialog(rootPane, "Please Select a reason for remove this vehicle");
            }

            if(formMode.equalsIgnoreCase("ADD")) tableModel.removeRow(r);
            //row--;
        }
        }
        else
        JOptionPane.showMessageDialog(rootPane,  "Please select a row from SELECTED VEHICLE table !", "Messaage", WIDTH);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //get selectd row
        int r = tableVehicle.getSelectedRow();
        boolean isIn = false;
        //check weather user select a row
        if(r>=0){
            //loop throught the all coloms in tableSelectedVehicals
            for(int j = 0 ; j < tableSelectedVehicals.getRowCount();j++){

                if(tableSelectedVehicals.getValueAt(j, 0).toString().equalsIgnoreCase(tableVehicle.getValueAt(r, 0).toString()))
                isIn = true;

            }
            if(isIn == false){

                //if(resPackage.getAc().equalsIgnoreCase(tableVehicle.getValueAt(r, 4).toString())){

                    for(int i = 0 ; i <tableVehicle.getColumnCount()-7;i++){
                        obj[i] = tableVehicle.getValueAt(r, i);

                    }

                    tableModel.addRow(obj);
                    //row++;
                    // }
                // else
                // JOptionPane.showMessageDialog(rootPane, "Paclage Ac Type did not match with the Selected Vehicle ", "Messaage", WIDTH);

            }

            else{
                JOptionPane.showMessageDialog(rootPane, "Vehicle already selected ", "Messaage", WIDTH);
            }
        }

        else
        JOptionPane.showMessageDialog(rootPane,  "Please select a row from  VEHICLE SEARCH RESULT table !", "Messaage", WIDTH);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void searchByRegNoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchByRegNoMouseClicked
        if(searchByRegNo.isSelected()){

        }
    }//GEN-LAST:event_searchByRegNoMouseClicked

    private void cmb_day_nightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_day_nightItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            if(cmb_day_night.getSelectedIndex() == 1){
                cmb_basis.setSelectedIndex(0);
                cmb_basis.setEnabled(false);
            }
            else
            cmb_basis.setEnabled(true);
        }
    }//GEN-LAST:event_cmb_day_nightItemStateChanged

    private void cmb_packagesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_packagesItemStateChanged
        String dayNight;
        String kmOrHour;

        if(evt.getStateChange() == ItemEvent.SELECTED){

            int index = cmb_packages.getSelectedIndex();

            String sql = "SELECT * FROM standardpackage,standardpackagetype WHERE std_package_type_ID_ref = std_package_type_ID AND  std_package_type_ID = '"+packageTypeIDS.get(index) +"' AND cust_ID_ref = '"+custID+"'";
            tabelLoad(sql, tablePackage);
            paktypeID =Integer.parseInt(tablePackage.getValueAt(1, 1).toString());

            System.out.println("PTID : "+ paktypeID);
        }

    }//GEN-LAST:event_cmb_packagesItemStateChanged

    private void cmb_package_typeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_package_typeMouseClicked

    }//GEN-LAST:event_cmb_package_typeMouseClicked

    private void cmb_package_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_package_typeItemStateChanged

        Connection con =null;
        String sql = null;
        PreparedStatement pst = null;
        ResultSet rst = null;

        //ArrayList<Integer>  packageTypeIDS = new ArrayList<>();
        if(evt.getStateChange() == ItemEvent.SELECTED){

            cmb_packages.removeAllItems();
            try{

                con  =  DbConnect.connect();

                if(cmb_package_type.getSelectedIndex() == 0){
                    System.out.println("0");

                }

                //loading Deafult packages combo
                else if(cmb_package_type.getSelectedIndex() == 1){

                    packageTypeIDS.clear();

                    custID = "CUS00001";

                    sql = "SELECT DISTINCT std_package_type_ID,description_pak_type FROM standardpackage,standardpackagetype WHERE std_package_type_ID_ref = std_package_type_ID AND  cust_ID_ref= 'CUS00001'";
                    pst = con.prepareStatement(sql);
                    rst = pst.executeQuery();

                    while(rst.next()){
                        packageTypeIDS.add(rst.getInt("std_package_type_ID"));
                        cmb_packages.addItem(rst.getString("description_pak_type"));

                    }

                    System.out.println(packageTypeIDS);
                }

                //loading custom packages combo
                else if(cmb_package_type.getSelectedIndex() == 2){
                    packageTypeIDS.clear();

                    if(!lblCustomerID.getText().equalsIgnoreCase("--")){
                        custID = lblCustomerID.getText();
                        //cmb_packages.removeAllItems();
                        sql = "SELECT DISTINCT std_package_type_ID,description_pak_type FROM standardpackage,standardpackagetype WHERE std_package_type_ID_ref = std_package_type_ID AND  cust_ID_ref= '"+custID+"'";
                        pst = con.prepareStatement(sql);
                        rst = pst.executeQuery();

                        if(rst.next()){
                            while(rst.next()){
                                packageTypeIDS.add(rst.getInt("std_package_type_ID"));
                                cmb_packages.addItem(rst.getString("description_pak_type"));

                            }
                        }

                        else {
                            cmb_package_type.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(rootPane, "This customer doesn't have custome packages");

                        }
                    }
                    else{
                        cmb_package_type.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Please Select a customer");
                    }
                }

                else{
                    packageTypeIDS.clear();

                }

            } catch( SQLException e){
                System.out.println(e);

            }

        }

    }//GEN-LAST:event_cmb_package_typeItemStateChanged

    private void tablePackageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePackageMouseClicked
        int r = tablePackage.getSelectedRow();
        LBL_packageTypeID.setText(tablePackage.getValueAt(r, 1).toString());
        LBL_packageID.setText(tablePackage.getValueAt(r, 0).toString());
        LBL_vehicalType.setText(tablePackage.getValueAt(r, 3).toString());
        LBL_intialprice.setText("Rs " + tablePackage.getValueAt(r, 4).toString());
        LBL_additionalPerKm.setText("Rs " + tablePackage.getValueAt(r, 5).toString());
        LBL_additionalPerHour.setText("Rs " + tablePackage.getValueAt(r, 6).toString());
        LBL_KmLimit.setText(tablePackage.getValueAt(r, 8).toString() + " KM");
        LBL_description.setText(tablePackage.getValueAt(r, 9).toString());
    }//GEN-LAST:event_tablePackageMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        selectPackage();
        //System.out.println("PTDI :" +ResStandedPackage.getStdPackageTypeID());

        if(resCustomer.isEmpty()){
            if(! ResStandedPackage.isEmpty()){
                jTabbedPane1.setEnabledAt(1, true);
                jTabbedPane1.setEnabledAt(0, false);
                jTabbedPane1.setSelectedIndex(1);
            }else
            JOptionPane.showMessageDialog(rootPane, "Please Select a Package");
        } else
        JOptionPane.showMessageDialog(rootPane, "Please Select a Customer");

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int r = jTable2.getSelectedRow();

        if (r>=0){
            String name = null;
            String type = null;

            if(generalCustomerRadioB.isSelected()){
                type = "General/Online";
                name = jTable2.getValueAt(r, 1).toString()+" "+jTable2.getValueAt(r, 2).toString();
            }

            if(govAndPvtRb.isSelected()){
                type = "Company";
                name = jTable2.getValueAt(r, 1).toString();
            }

            resCustomer.setter(jTable2.getValueAt(r, 0).toString(), name, type);
            lblCustomerID.setText(resCustomer.getCustomerID());
            lblCustomerName.setText(resCustomer.getCustomerName());
            lblCustomerType.setText(resCustomer.getCustomerType());

        }else
        JOptionPane.showMessageDialog(rootPane, "Please Select a Customer");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void govAndPvtRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_govAndPvtRbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_govAndPvtRbActionPerformed

    private void govAndPvtRbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_govAndPvtRbMouseClicked
        sql = "SELECT* FROM govandprivatecompanies;";
        tabelLoad(sql,jTable2);

        tableFilter(txtCompanyName,jTable2,1);
    }//GEN-LAST:event_govAndPvtRbMouseClicked

    private void generalCustomerRadioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalCustomerRadioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generalCustomerRadioBActionPerformed

    private void generalCustomerRadioBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalCustomerRadioBMouseClicked
        sql = "SELECT * FROM createtours.general_or_online_customer_view;";
        tabelLoad(sql,jTable2);
        tableFilter(txtFname, textLname, jTable2, 1, 2);
    }//GEN-LAST:event_generalCustomerRadioBMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        //        int r = jTable2.getSelectedRow();
        //        jLabel6.setText(jTable2.getValueAt(r, 0).toString());
        //        TableColumn col = null;
        //        col = jTable2.getColumnModel().getColumn(2);

        // System.out.println(col.getHeaderValue());
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(1, false);
    }//GEN-LAST:event_jButton4ActionPerformed

       public static String generateDate(String selectedDate,int period){
       String nowdate = null;
        try {

           
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");          
            Calendar c = Calendar.getInstance();
            
            c.setTime(dateFormat.parse(selectedDate));
            c.add(Calendar.DATE, period);
            
            nowdate  = dateFormat.format(c.getTime());
            
        } catch (ParseException ex) {
            Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nowdate;
    }
    
    
    public void selectPackage() {
        ResStandedPackage.clear();
        ResStandedPackage.setValues(paktypeID);
        String dayNight = null;
        String kmOrHour = null;
        

        
        if(cmb_day_night.getSelectedIndex() == 0)
            dayNight = "DAY";
        
        else
            dayNight = "NIGHT";
        
        
        if(cmb_basis.getSelectedIndex() == 0)
            kmOrHour = "KM";
        else
            kmOrHour = "HOUR";
        
        ResStandedPackage.setDayOrNight(dayNight);
        ResStandedPackage.setKmOrHour(kmOrHour);
        
        //stdp.print();
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
    
    
    
    public void fillingTheVehicleArrayandIncoiceObject() throws HeadlessException, NumberFormatException {
        
        //check count variable value equal to row count of the  tableSelectedVehicals
        if(count == tableSelectedVehicals.getRowCount() && count != 0){
            //looping through the tableSelectedVehicals
            for(int i =0; i <count ; i++ ){
                
                 if(formMode.equalsIgnoreCase("ADD")){
                //get values from  tableSelectedVehicals and add them to the resVehicals objects case ADD
                resVehicals.add(new ReservationVehical(tableSelectedVehicals.getValueAt(i, 0).toString(),
                        tableSelectedVehicals.getValueAt(i, 3).toString(), tableSelectedVehicals.getValueAt(i, 2).toString(),
                        tableSelectedVehicals.getValueAt(i, 1).toString(), tableSelectedVehicals.getValueAt(i, 4).toString()));
                 }
                 
                 if(formMode.equalsIgnoreCase("EDIT")){
                 //get values from  tableSelectedVehicals and add them to the resVehicals objects case EDIT
                 resVehicals.add(new ReservationVehical(tableSelectedVehicals.getValueAt(i, 0).toString(), 
                               tableSelectedVehicals.getValueAt(i, 3).toString(), 
                               tableSelectedVehicals.getValueAt(i, 2).toString(), 
                               tableSelectedVehicals.getValueAt(i, 1).toString(), 
                               tableSelectedVehicals.getValueAt(i, 4).toString(), 
                               "UPDATE", "NONE"));
                 
                 }
            }
            
            for(int i =0; i <count ; i++ ){
                //resVehicals.get(i).print();
                
                
            }
            
            
            //calculate the cost
            
            //loop through resVehicals arraylist
            for(int i = 0 ; i<resVehicals.size();i++){
                //loop through resVehicals ResStandedPackage
                for (int j = 0; j <ResStandedPackage.getVehicleTypes().size(); j++) {
                    
                    //getting prices of each vehical and add into the invoice object
                    if (resVehicals.get(i).getVehicleType().equalsIgnoreCase(ResStandedPackage.getVehicleTypes(j).getVehicleType()) && !resVehicals.get(i).getOparation().equalsIgnoreCase("DELETE")){
                        
                        invoiceForThisReservation.setInvoiceDataArray(resVehicals.get(i).getVehicleType(),
                                resVehicals.get(i).getVehicleRegNo(),
                                Double.parseDouble(ResStandedPackage.getVehicleTypes(j).getInitialPrice().toString()));
                        
                        
                        
                        
                    }
                }
                
                
                
                
                
            }
            
            invoiceForThisReservation.print();
            // System.out.println("COST : " +reservationCost);
            
            
            
        }
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
            java.util.logging.Logger.getLogger(AddNewReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewReservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddNewReservation().setVisible(true);
            }
        });
    }
    
    String sql;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_addResvation;
    private javax.swing.JButton BTN_vehicleTabNext;
    private javax.swing.JComboBox COMBO_dateDurationChoose;
    private com.toedter.calendar.JDateChooser DATEPICKER_customReservationEndDate;
    private com.toedter.calendar.JDateChooser DATEPICKER_reservationStartDate;
    private javax.swing.JLabel LBL_KmLimit;
    private javax.swing.JLabel LBL_additionalPerHour;
    private javax.swing.JLabel LBL_additionalPerKm;
    private javax.swing.JLabel LBL_description;
    private javax.swing.JLabel LBL_intialprice;
    private javax.swing.JLabel LBL_packageID;
    private javax.swing.JLabel LBL_packageTypeID;
    private javax.swing.JLabel LBL_vehicalType;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmb_basis;
    private javax.swing.JComboBox cmb_day_night;
    private javax.swing.JComboBox cmb_package_type;
    private javax.swing.JComboBox cmb_packages;
    private javax.swing.JPanel customDate;
    private javax.swing.JRadioButton generalCustomerRadioB;
    private javax.swing.JRadioButton govAndPvtRb;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblCustomerID;
    private javax.swing.JLabel lblCustomerName;
    private javax.swing.JLabel lblCustomerType;
    private javax.swing.JRadioButton searchByRegNo;
    private javax.swing.JTable tablePackage;
    private javax.swing.JTable tableSelectedVehicals;
    private javax.swing.JTable tableVehicle;
    private javax.swing.JTextArea tareaDescription;
    private javax.swing.JTextField textLname;
    public javax.swing.JTextField txtCompanyName;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtRegno;
    // End of variables declaration//GEN-END:variables
}
