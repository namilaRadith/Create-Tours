/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import ApplicationClasses.DbConnect;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Malruchi
 */
public class search_Vehicle_Vehicle1 extends javax.swing.JFrame {

    Connection conn = (Connection) DbConnect.connect();
    PreparedStatement pst = null;
    ResultSet rs = null;
 
    public search_Vehicle_Vehicle1() throws SQLException {
        initComponents();
        conn = (Connection) DbConnect.connect();
        getValuesToVehicleCombo_Vehicle();
        
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
    }
    
    public void getValuesToVehicleCombo_Vehicle() throws SQLException
    {
        
        ArrayList<String> sql = new ArrayList<>();
        sql.add("SELECT DISTINCT vehicleType FROM vehicle WHERE deleteStatus = 1");
        sql.add("SELECT DISTINCT vehicalModel FROM vehicle WHERE deleteStatus = 1");
        sql.add("SELECT DISTINCT color FROM vehicle WHERE deleteStatus = 1");
        sql.add("SELECT DISTINCT transmisionType FROM vehicle WHERE deleteStatus = 1");
        sql.add("SELECT DISTINCT noOfSeats FROM vehicle WHERE deleteStatus = 1");
        sql.add("SELECT DISTINCT fuelType FROM vehicle WHERE deleteStatus = 1");        
        sql.add("SELECT DISTINCT type FROM vehicle WHERE deleteStatus = 1");
        
        
        pst = conn.prepareStatement(sql.get(0));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            
            cmb_VehicleTypeSearch_Vehicle.addItem(rs.getString("vehicleType"));
        }
        
        pst = conn.prepareStatement(sql.get(1));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            cmb_VehicleModelSearch_Vehicle.addItem(rs.getString("vehicalModel"));
        
        }
        
        pst = conn.prepareStatement(sql.get(2));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            cmb_VehicleColorSearch_Vehicle.addItem(rs.getString("color"));
        
        }
        
        pst = conn.prepareStatement(sql.get(3));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            cmb_VehicleTransmitTypeSearch_Vehicle.addItem(rs.getString("transmisionType"));
        
        }
        
        pst = conn.prepareStatement(sql.get(4));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            cmb_VehicleNoOfSeatsSearch_Vehicle.addItem(rs.getString("noOfSeats"));
        
        }
        
        pst = conn.prepareStatement(sql.get(5));
        rs  = pst.executeQuery();
        
        while(rs.next())
        {
            cmb_VehicleFuelTypeSearch_Vehicle.addItem(rs.getString("fuelType"));
        
        }
        
        pst = conn.prepareStatement(sql.get(6));
        rs  = pst.executeQuery();
                        
        while(rs.next())
        {
            cmb_VehicleOwnerTypeSearch_Vehicle.addItem(rs.getString("type"));
        
        }
        
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rdb_licenceNoSearch_Vehicle = new javax.swing.JRadioButton();
        txt_LicenceNoSearch_Vehicle = new javax.swing.JTextField();
        rdb_vehicleModelSearch_Vehicle = new javax.swing.JRadioButton();
        rdb_vehicleTypeSearch_Vehicle = new javax.swing.JRadioButton();
        rdb_vehicleColorSearch_Vehicle = new javax.swing.JRadioButton();
        rdb_vehicleNoOfSeatsSearch_Vehicle = new javax.swing.JRadioButton();
        rdb_vehicleTransmissionTypeSearch_Vehicle = new javax.swing.JRadioButton();
        rdb_regiNoSearch_Vehicle = new javax.swing.JRadioButton();
        txt_RegiNoSearch_Vehicle = new javax.swing.JTextField();
        rdb_vehicleFuelTypeSearch_Vehicle = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_VehicleSearchTable_Vehicle = new javax.swing.JTable();
        btn_Search_Vehicle = new javax.swing.JButton();
        cmb_VehicleTypeSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleModelSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleColorSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleTransmitTypeSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleNoOfSeatsSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleFuelTypeSearch_Vehicle = new javax.swing.JComboBox();
        cmb_VehicleACTypeSearch_Vehicle = new javax.swing.JComboBox();
        rdb_vehicleOwnerTypeSearch_Vehicle = new javax.swing.JRadioButton();
        cmb_VehicleOwnerTypeSearch_Vehicle = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1145, 682));

        buttonGroup1.add(rdb_licenceNoSearch_Vehicle);
        rdb_licenceNoSearch_Vehicle.setText("Search by Licence Number ");
        rdb_licenceNoSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_licenceNoSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleModelSearch_Vehicle);
        rdb_vehicleModelSearch_Vehicle.setText("Search by Vehicle Model");
        rdb_vehicleModelSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleModelSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleTypeSearch_Vehicle);
        rdb_vehicleTypeSearch_Vehicle.setText("Search by Vehicle Type");
        rdb_vehicleTypeSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleTypeSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleColorSearch_Vehicle);
        rdb_vehicleColorSearch_Vehicle.setText("Search by Colour");
        rdb_vehicleColorSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleColorSearch_VehicleMouseClicked(evt);
            }
        });
        rdb_vehicleColorSearch_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdb_vehicleColorSearch_VehicleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleNoOfSeatsSearch_Vehicle);
        rdb_vehicleNoOfSeatsSearch_Vehicle.setText("Search by Number Of Seats");
        rdb_vehicleNoOfSeatsSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleNoOfSeatsSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleTransmissionTypeSearch_Vehicle);
        rdb_vehicleTransmissionTypeSearch_Vehicle.setText("Search by Transmission Type");
        rdb_vehicleTransmissionTypeSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleTransmissionTypeSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_regiNoSearch_Vehicle);
        rdb_regiNoSearch_Vehicle.setText("Srearch by Registration Number");
        rdb_regiNoSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_regiNoSearch_VehicleMouseClicked(evt);
            }
        });

        buttonGroup1.add(rdb_vehicleFuelTypeSearch_Vehicle);
        rdb_vehicleFuelTypeSearch_Vehicle.setText("Search by Fuel Type");
        rdb_vehicleFuelTypeSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleFuelTypeSearch_VehicleMouseClicked(evt);
            }
        });

        tbl_VehicleSearchTable_Vehicle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "vehicleID", "Type", "Model", "LicenceNo", "ACType", "Transmission", "Fuel", "Colour", "Seats", "Availability", "Owner"
            }
        ));
        jScrollPane1.setViewportView(tbl_VehicleSearchTable_Vehicle);

        btn_Search_Vehicle.setText("Search");
        btn_Search_Vehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Search_VehicleActionPerformed(evt);
            }
        });

        cmb_VehicleTypeSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Vehicle Type" }));

        cmb_VehicleModelSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Vehicle Model" }));

        cmb_VehicleColorSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Colour" }));

        cmb_VehicleTransmitTypeSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Transmission Type" }));

        cmb_VehicleNoOfSeatsSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Number Of Seats" }));

        cmb_VehicleFuelTypeSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Fuel Type" }));

        cmb_VehicleACTypeSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select A/C Type" }));

        buttonGroup1.add(rdb_vehicleOwnerTypeSearch_Vehicle);
        rdb_vehicleOwnerTypeSearch_Vehicle.setText("Search by Owner Type");
        rdb_vehicleOwnerTypeSearch_Vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdb_vehicleOwnerTypeSearch_VehicleMouseClicked(evt);
            }
        });

        cmb_VehicleOwnerTypeSearch_Vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Owner Type" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdb_vehicleTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdb_licenceNoSearch_Vehicle)
                                    .addComponent(cmb_VehicleTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txt_LicenceNoSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(265, 265, 265)
                                                .addComponent(rdb_regiNoSearch_Vehicle))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdb_vehicleModelSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmb_VehicleModelSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdb_vehicleColorSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmb_VehicleColorSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(rdb_vehicleTransmissionTypeSearch_Vehicle)
                                                    .addComponent(cmb_VehicleTransmitTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(29, 29, 29)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(rdb_vehicleNoOfSeatsSearch_Vehicle)
                                                    .addComponent(cmb_VehicleNoOfSeatsSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_Search_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(92, 92, 92)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmb_VehicleFuelTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdb_vehicleFuelTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addComponent(cmb_VehicleACTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_RegiNoSearch_Vehicle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_VehicleOwnerTypeSearch_Vehicle, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdb_vehicleOwnerTypeSearch_Vehicle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_RegiNoSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdb_regiNoSearch_Vehicle)
                    .addComponent(txt_LicenceNoSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdb_licenceNoSearch_Vehicle))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdb_vehicleModelSearch_Vehicle)
                    .addComponent(rdb_vehicleColorSearch_Vehicle)
                    .addComponent(rdb_vehicleTransmissionTypeSearch_Vehicle)
                    .addComponent(rdb_vehicleNoOfSeatsSearch_Vehicle)
                    .addComponent(rdb_vehicleTypeSearch_Vehicle)
                    .addComponent(rdb_vehicleFuelTypeSearch_Vehicle))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_VehicleTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleModelSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleColorSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleTransmitTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleNoOfSeatsSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleFuelTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_VehicleACTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(rdb_vehicleOwnerTypeSearch_Vehicle)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cmb_VehicleOwnerTypeSearch_Vehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                        .addComponent(btn_Search_Vehicle)
                        .addGap(40, 40, 40)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1341, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Search_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Search_VehicleActionPerformed
       
        String vehiLicence_No = txt_LicenceNoSearch_Vehicle.getText();
        String vehi_ID = txt_RegiNoSearch_Vehicle.getText();
        String vehiType = cmb_VehicleTypeSearch_Vehicle.getSelectedItem().toString();
        String vehi_Model = cmb_VehicleModelSearch_Vehicle.getSelectedItem().toString();
        String vehi_Color = cmb_VehicleColorSearch_Vehicle.getSelectedItem().toString();
        String vehi_TransmissionType = cmb_VehicleTransmitTypeSearch_Vehicle.getSelectedItem().toString();
        String vehi_NoOfSeats = cmb_VehicleNoOfSeatsSearch_Vehicle.getSelectedItem().toString();
        String vehi_FuelType = cmb_VehicleFuelTypeSearch_Vehicle.getSelectedItem().toString();
        String vehi_ACType = cmb_VehicleACTypeSearch_Vehicle.getSelectedItem().toString();
        String vehi_OwnerType = cmb_VehicleOwnerTypeSearch_Vehicle.getSelectedItem().toString();
        
        if(rdb_licenceNoSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE licenceNo='"+vehiLicence_No+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_regiNoSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE vehicle_ID='"+vehi_ID+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleTypeSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE vehicleType='"+vehiType+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleModelSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE vehicalModel='"+vehi_Model+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleColorSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE color='"+vehi_Color+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleTransmissionTypeSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE transmisionType='"+vehi_TransmissionType+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleNoOfSeatsSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE noOfSeats='"+vehi_NoOfSeats+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(rdb_vehicleFuelTypeSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE fuelType='"+vehi_FuelType+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                        
        else if(rdb_vehicleOwnerTypeSearch_Vehicle.isSelected())
        {
           String vehiSearch_Query = "SELECT vehicle_ID AS VehicleID,vehicleType AS Type,vehicalModel AS Model,licenceNo AS LicenceNo,ACType AS ACType,transmisionType AS Transmission,fuelType AS Fuel,color AS Colour, noOfSeats AS Seats,status AS Availability,type AS Owner FROM vehicle WHERE type='"+vehi_OwnerType+"' AND deleteStatus = 1";
            try 
            {
                pst = conn.prepareStatement(vehiSearch_Query);
                rs = pst.executeQuery();
                tbl_VehicleSearchTable_Vehicle.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_Search_VehicleActionPerformed

    private void rdb_vehicleColorSearch_VehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdb_vehicleColorSearch_VehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdb_vehicleColorSearch_VehicleActionPerformed

    private void rdb_licenceNoSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_licenceNoSearch_VehicleMouseClicked
        
        txt_LicenceNoSearch_Vehicle.setEnabled(true);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
        
    }//GEN-LAST:event_rdb_licenceNoSearch_VehicleMouseClicked

    private void rdb_regiNoSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_regiNoSearch_VehicleMouseClicked
        
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(true);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_regiNoSearch_VehicleMouseClicked

    private void rdb_vehicleTypeSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleTypeSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(true);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleTypeSearch_VehicleMouseClicked

    private void rdb_vehicleModelSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleModelSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(true);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleModelSearch_VehicleMouseClicked

    private void rdb_vehicleColorSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleColorSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(true);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleColorSearch_VehicleMouseClicked

    private void rdb_vehicleTransmissionTypeSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleTransmissionTypeSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(true);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
        
    }//GEN-LAST:event_rdb_vehicleTransmissionTypeSearch_VehicleMouseClicked

    private void rdb_vehicleNoOfSeatsSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleNoOfSeatsSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(true);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleNoOfSeatsSearch_VehicleMouseClicked

    private void rdb_vehicleFuelTypeSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleFuelTypeSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(true);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(false);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleFuelTypeSearch_VehicleMouseClicked

    private void rdb_vehicleOwnerTypeSearch_VehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdb_vehicleOwnerTypeSearch_VehicleMouseClicked
        txt_LicenceNoSearch_Vehicle.setEnabled(false);
        txt_RegiNoSearch_Vehicle.setEnabled(false);
        cmb_VehicleTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleModelSearch_Vehicle.setEnabled(false);
        cmb_VehicleColorSearch_Vehicle.setEnabled(false);
        cmb_VehicleTransmitTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleNoOfSeatsSearch_Vehicle.setEnabled(false);
        cmb_VehicleFuelTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleACTypeSearch_Vehicle.setEnabled(false);
        cmb_VehicleOwnerTypeSearch_Vehicle.setEnabled(true);
        
        txt_LicenceNoSearch_Vehicle.setText(" ");
        txt_RegiNoSearch_Vehicle.setText(" ");
        cmb_VehicleTypeSearch_Vehicle.setSelectedItem("Select Vehicle Type");
        cmb_VehicleModelSearch_Vehicle.setSelectedItem("Select Vehicle Model");
        cmb_VehicleColorSearch_Vehicle.setSelectedItem("Select Colour");
        cmb_VehicleTransmitTypeSearch_Vehicle.setSelectedItem("Select Transmission Type");
        cmb_VehicleNoOfSeatsSearch_Vehicle.setSelectedItem("Select Number Of Seats");
        cmb_VehicleFuelTypeSearch_Vehicle.setSelectedItem("Select Fuel Type");
        cmb_VehicleACTypeSearch_Vehicle.setSelectedItem("Select A/C Type");
        cmb_VehicleOwnerTypeSearch_Vehicle.setSelectedItem("Select Owner Type");
        
    }//GEN-LAST:event_rdb_vehicleOwnerTypeSearch_VehicleMouseClicked

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
            java.util.logging.Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new search_Vehicle_Vehicle1().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(search_Vehicle_Vehicle1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Search_Vehicle;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmb_VehicleACTypeSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleColorSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleFuelTypeSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleModelSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleNoOfSeatsSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleOwnerTypeSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleTransmitTypeSearch_Vehicle;
    private javax.swing.JComboBox cmb_VehicleTypeSearch_Vehicle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdb_licenceNoSearch_Vehicle;
    private javax.swing.JRadioButton rdb_regiNoSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleColorSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleFuelTypeSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleModelSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleNoOfSeatsSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleOwnerTypeSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleTransmissionTypeSearch_Vehicle;
    private javax.swing.JRadioButton rdb_vehicleTypeSearch_Vehicle;
    private javax.swing.JTable tbl_VehicleSearchTable_Vehicle;
    private javax.swing.JTextField txt_LicenceNoSearch_Vehicle;
    private javax.swing.JTextField txt_RegiNoSearch_Vehicle;
    // End of variables declaration//GEN-END:variables
}
