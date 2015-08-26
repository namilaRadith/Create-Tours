/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import ApplicationClasses.DbConnect;
import ApplicationClasses.DbConnect;
import ApplicationClasses.Marketing.MarketingCampaign;
import ApplicationClasses.Marketing.MarValidator;
import ApplicationClasses.Marketing.MarketingCampaign;
import Interfaces.main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Basuru
 */
public class MarketingCampaignAddItems extends javax.swing.JFrame {

    Connection con = DbConnect.connect();
    PreparedStatement pst = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    int rowCount = 0;
    
    /**
     * @AR array is used to Hold the data until the user press the done button to pass the values to the  Marketing Campaign.Java , AR is the main Array and TempHoldThis Array
     * is only used to temporary hold the values till the user confirms and to display purposes only. Through the AR array we Add the items to the Database.
     * @TempHoldThis Array is used to hold the values and also reload the values to the table once the user selects the Row, And all the data will be inside this
     * array until the user press done and confirm the data.
     * @row Array is used to pass each row to the TempHoldThis and AR arrays.
     */
    
     public static ArrayList<ArrayList<Integer>> AR = new  ArrayList<ArrayList<Integer>>();
     public static ArrayList<Integer> TempHoldThis = new ArrayList<Integer>();  
     public static ArrayList<Integer> row = new ArrayList<Integer>();
     
     
 
    /**
     *@variable TotalPrice will get the total price of all the items
     */
    public static double TotalPrice;
     
    /**
     * Creates new form MarketingCampaignAddItems
     */
    public MarketingCampaignAddItems() {
        initComponents();
        
        if(Interfaces.main.MCID.getText().equals(""))
        {
            //do nothing
        }
        else
        {
            TableLoadWhenEdit(Integer.parseInt(Interfaces.main.MCID.getText()));
        }
        ItemsComboBoxLoad();
        TempTableLoad();
        
    }
    
    /**
     * Loads Data to the AddItemsToCombo combo box
     */
    public void ItemsComboBoxLoad()
    {
        try {
            String Sql ="SELECT type FROM mitems";
            stmt = con.createStatement();
            rs = stmt.executeQuery(Sql);
            
            while(rs.next())
            {
                AddItemsToCombo.addItem(rs.getString("type"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * 
     * @GETITEMS this function will get the Name of the item as a parameter from the table and then get the equal 
     * ID for the type and pass it.
     * 
     */
    public int getMItemsId(String Name)
    {
        try {
            
            String sql = "SELECT MItems_ID FROM mitems WHERE type= '"+Name+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                int ID = rs.getInt("MItems_ID");
                return ID;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
   
    
    
    
   /**
    * 
    * @param ID
    * This function get the ID of an ITEM as an input and then converts its back to its Name format and returns the Name
    * @usage , Used to get the ID from database and set the name back to the jTable when user wants to edit or view
    * 
    */
    public String getMNamesViaID(int ID)
    {
        try {
            String Sql = "SELECT type FROM mitems WHERE MItems_ID='"+ID+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(Sql);
            
            if(rs.next())
            {
                return rs.getString("type");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    /**
     * 
     * @param type
     * @return Get the Type of an Item as an input , then get the available quantity of that item and return
     * @called inside the DoneButton click event
     */
    
    public String getAvailableQty(String type)
    {
        try {
            String Sql = "SELECT qtyAtHand FROM mitems WHERE type='"+type+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(Sql);
            
            if(rs.next())
            {
                String qty = rs.getString("qtyAtHand");
                return qty;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    /**
     * @rainbowAlgorithm
     * 
     * Used the Algorithm to Load the Data from the AR array to the r Array so that r array will load the data to Table 
     * when user wants to edit or view.
     */
    public void TempTableLoad()
    {
        int startval=0,endval=1,row=0,column=0,size=0,sizeDivided=0;
        DefaultTableModel model = (DefaultTableModel) MarketingCampAddItemTable.getModel();
        
        for(ArrayList<Integer> r : AR)
        {
            size=r.size();
            
            sizeDivided=size/2;  
            
            while(sizeDivided>0)
            {
                while(startval<endval && endval<=size)
                {
                    System.out.println(startval+" "+endval);
                    row = r.get(startval);
                    
                    
                    column = r.get(endval);

                    model.addRow(new Object[]{getMNamesViaID(row), column});
                    
                    startval+=2;
                    endval+=2;

                }
                sizeDivided--;
            }
        }
    }
    
    /**
     * @param ID 
     * When user wants to edit, this function loads Item type and Available Quantity from Database and displays it in the table 
     */
    public void TableLoadWhenEdit(int ID)
    {
        try {
            MarketingCampaignAddItems.TempHoldThis.clear();
            
            String Sql = "SELECT mitems.type AS 'Item Name' , qty AS 'Available Quantity' FROM createtours.mitems_has_mcamp,mitems WHERE MItems_MItems_ID=mitems.MItems_ID AND mitems_has_mcamp.MCamp_MCamp_ID ='"+ID+"'";
            pst = con.prepareStatement(Sql);
            rs = pst.executeQuery();
            
            MarketingCampAddItemTable.setModel(DbUtils.resultSetToTableModel(rs));

            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     /**
     * @param type
     * @return UnitPrice
     * 
     * @description this function will return the unit price for the selected items by calculating the sum from unit price and the Quantity and returns the
     * total price
     */
    public double getUnitPrice(String type)
    {
        try {
            double unitPrice=0;
            String sql = "SELECT cost FROM mitems WHERE type='"+type+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.next())
            {
                unitPrice = rs.getDouble("cost");
            }
            return unitPrice;
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    
    /**
     * @param type
     * This method will Validate the actual quantity and the quantity selected
     * @return Available Quantity
     */
    
    public int ValidateQuantity(String type)
    {
        try {
            String Sql = "SELECT qtyAtHand FROM mitems WHERE type='"+type+"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(Sql);
            
            if(rs.next())
            {
                return rs.getInt("qtyAtHand");
            }
         
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return -1;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AddItemsToQty = new javax.swing.JTextField();
        AddItemsToCombo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MarketingCampAddItemTable = new javax.swing.JTable();
        MCAddBtn = new javax.swing.JButton();
        MCDeleteBtn = new javax.swing.JButton();
        MCDoneBtn = new javax.swing.JButton();
        MCEditBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        QtyAtHandShow = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add Items");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Items"));

        AddItemsToCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please Select" }));
        AddItemsToCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemsToComboActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Items :");

        jLabel2.setText("Select Quantity : ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Added Items Details"));

        MarketingCampAddItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Name", "Quantity need"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        MarketingCampAddItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MarketingCampAddItemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(MarketingCampAddItemTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MCAddBtn.setText("Add");
        MCAddBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MCAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCAddBtnActionPerformed(evt);
            }
        });

        MCDeleteBtn.setText("Delete");
        MCDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCDeleteBtnActionPerformed(evt);
            }
        });

        MCDoneBtn.setText("Done");
        MCDoneBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MCDoneBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCDoneBtnActionPerformed(evt);
            }
        });

        MCEditBtn.setText("Edit");
        MCEditBtn.setPreferredSize(new java.awt.Dimension(83, 25));
        MCEditBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MCEditBtnActionPerformed(evt);
            }
        });

        jLabel3.setText("Quantity At Hand : ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(AddItemsToQty, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(QtyAtHandShow))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(AddItemsToCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(MCAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(MCEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(MCDeleteBtn))))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(MCDoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddItemsToCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(MCAddBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MCDeleteBtn)
                    .addComponent(MCEditBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(AddItemsToQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(QtyAtHandShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MCDoneBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MCAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCAddBtnActionPerformed

        
        
        if(!AddItemsToCombo.getSelectedItem().equals("Please Select") && !AddItemsToQty.getText().equals(""))
        {
            
            
            if(AddItemsToQty.getText().matches("[0-9]+"))
            {
                

                String type = AddItemsToCombo.getSelectedItem().toString();

                String qty = AddItemsToQty.getText().toString();

                 if(Integer.parseInt(qty) > ValidateQuantity(AddItemsToCombo.getSelectedItem().toString()))
                {
                    JOptionPane.showMessageDialog(rootPane, "Quantity Selected Invalid.\nMaximum selectable quantity of this item is "+ValidateQuantity(AddItemsToCombo.getSelectedItem().toString())+"");
                }
                else
                {
                int rowID=0,Qty = 0;
                DefaultTableModel model = (DefaultTableModel) MarketingCampAddItemTable.getModel();

                model.addRow(new Object[]{type, qty});

                rowID = getMItemsId(model.getValueAt(0, 0).toString());
                Qty =  Integer.parseInt(model.getValueAt(0, 1).toString());


                AddItemsToQty.setText("");
                AddItemsToCombo.setSelectedIndex(0);
                }
            }
            else
            {
                 JOptionPane.showMessageDialog(rootPane, "Quantity can only be Numeric","Input Missmatch",JOptionPane.WARNING_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "All fields must be filled","Field Empty",JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_MCAddBtnActionPerformed

    private void MCDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCDeleteBtnActionPerformed

        try
        {
            
        DefaultTableModel model = (DefaultTableModel) MarketingCampAddItemTable.getModel();
        model.removeRow(MarketingCampAddItemTable.getSelectedRow());
        
        }catch(ArrayIndexOutOfBoundsException EX)
        {
            //Do nothing if No items to delete
        }
                
             
        
    }//GEN-LAST:event_MCDeleteBtnActionPerformed

    private void MCDoneBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCDoneBtnActionPerformed
        
        

        // Pass all the values to Main form
        MarketingCampaign.FinalArray.clear();
        //Interfaces.main.md.clear();
        TempHoldThis.clear();
        row.clear();
        TotalPrice=0;
        
        rowCount = MarketingCampAddItemTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel) MarketingCampAddItemTable.getModel();
        int rowID, Qty=0;
        
        for(int i=0;i<=rowCount-1;i++)
        {
            
            rowID = getMItemsId(model.getValueAt(i, 0).toString());
            Qty = Integer.parseInt(model.getValueAt(i, 1).toString());
            
            /**
             * @variable TotalPrice
             * Total price will be calculated using getUnitprice function * quantity
             * 
             */
            TotalPrice += getUnitPrice(model.getValueAt(i, 0).toString()) * Qty;
            
            
            row.add(rowID);
            row.add(Qty);
            
            TempHoldThis.add(rowID);
            TempHoldThis.add(Qty);
            
        }
        
        main.MCCost.setText(Double.toString(TotalPrice));
        MarketingCampaign.FinalArray.add(row);
        boolean add = AR.add(TempHoldThis);
        
        this.dispose();
        
        
        
    }//GEN-LAST:event_MCDoneBtnActionPerformed

    private void MCEditBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MCEditBtnActionPerformed
       //Perform the Edit
        try
        {

            String type = AddItemsToCombo.getSelectedItem().toString();
            String qty = AddItemsToQty.getText();
            if(Integer.parseInt(qty) > ValidateQuantity(AddItemsToCombo.getSelectedItem().toString()))
            {
                JOptionPane.showMessageDialog(rootPane, "Quantity Selected Invalid.\nMaximum selectable quantity of this item is "+ValidateQuantity(AddItemsToCombo.getSelectedItem().toString())+"");
            }
            else
            {
                DefaultTableModel model = (DefaultTableModel) MarketingCampAddItemTable.getModel();

                int row = MarketingCampAddItemTable.getSelectedRow();

                model.setValueAt(type, row, 0);
                model.setValueAt(qty, row, 1);
                }
        
        }catch(ArrayIndexOutOfBoundsException EX)
        {
            //Do nothing if No items to delete
        }
        
        //model.addRow(new Object[]{type, qty});
        
        
    }//GEN-LAST:event_MCEditBtnActionPerformed

    private void MarketingCampAddItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MarketingCampAddItemTableMouseClicked
        int row = MarketingCampAddItemTable.getSelectedRow();
        AddItemsToCombo.setSelectedItem(MarketingCampAddItemTable.getValueAt(row, 0));
        AddItemsToQty.setText(MarketingCampAddItemTable.getValueAt(row, 1).toString());
        
        
        
        
    }//GEN-LAST:event_MarketingCampAddItemTableMouseClicked

    private void AddItemsToComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemsToComboActionPerformed
        
        String type = AddItemsToCombo.getSelectedItem().toString();
        QtyAtHandShow.setText(getAvailableQty(type));
        
    }//GEN-LAST:event_AddItemsToComboActionPerformed

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
            java.util.logging.Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MarketingCampaignAddItems.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MarketingCampaignAddItems().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox AddItemsToCombo;
    private javax.swing.JTextField AddItemsToQty;
    private javax.swing.JButton MCAddBtn;
    private javax.swing.JButton MCDeleteBtn;
    private javax.swing.JButton MCDoneBtn;
    private javax.swing.JButton MCEditBtn;
    private javax.swing.JTable MarketingCampAddItemTable;
    private javax.swing.JLabel QtyAtHandShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
