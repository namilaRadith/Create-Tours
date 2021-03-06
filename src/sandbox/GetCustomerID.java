/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import ApplicationClasses.DbConnect;
import ApplicationClasses.Reservation.ReservationCustomer;
import ApplicationClasses.customFunctions;
import static sandbox.AddReservation.setReservCustomer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Namila Radith
 */
public class GetCustomerID extends javax.swing.JInternalFrame {

    /**
     * Creates new form GetCustomerID
     */
    
    customFunctions cf = new customFunctions();
    public static mainsand m1 = new mainsand();
    JLabel jl1,jl2;
    JTextField jt1;
    
    public static ReservationCustomer resCustomer = new ReservationCustomer();
    
    public GetCustomerID(JLabel jl1,JTextField jt , JLabel jl2) {
         this.jl1 = jl1;
         this.jt1 = jt;
         this.jl2 = jl2;
        initComponents();
       
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectCustomerBgroup = new javax.swing.ButtonGroup();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        generalCustomerRadioB = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        govAndPvtRb = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Get a customer");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Done");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Customer ID :");

        jLabel6.setText("--");

        selectCustomerBgroup.add(generalCustomerRadioB);
        generalCustomerRadioB.setText("Get Individual / General Customer");
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

        selectCustomerBgroup.add(govAndPvtRb);
        govAndPvtRb.setText("Get Company");
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

        jLabel3.setText("Last Name :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(generalCustomerRadioB)
                    .addComponent(govAndPvtRb)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generalCustomerRadioB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(govAndPvtRb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddReservation aR = new AddReservation();
        
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
            
            setReservCustomer(new ReservationCustomer(jTable2.getValueAt(r, 0).toString(), name, type));
            this.jt1.setText(aR.reservCustomer.getCustomerID());
            this.jl1.setText(aR.reservCustomer.getCustomerName());
            this.jl2.setText(aR.reservCustomer.getCustomerType());
            this.dispose();
        }else
            JOptionPane.showMessageDialog(rootPane, "Please Select a Customer");
           
        
       
        
        
//        this.addPropertyChangeListener(a);
//        firePropertyChange("textchange", "", m1.getCustomerID());
        
       // System.out.println(m1.getCustomerID());
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void govAndPvtRbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_govAndPvtRbMouseClicked
       sql = "SELECT* FROM govandprivatecompanies;";
       customerTabelLoad(sql);
       
       tableFilter(jTextField3,jTable2,1);
    }//GEN-LAST:event_govAndPvtRbMouseClicked
    
    //table filter 
    public void tableFilter(JTextField tfild,JTable jt , int index) {
        TableRowSorter<TableModel> sorter  = new TableRowSorter<>(jTable2.getModel());
        jTable2.setRowSorter(sorter);
        
        
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
        TableRowSorter<TableModel> sorter  = new TableRowSorter<>(jTable2.getModel());
        jTable2.setRowSorter(sorter);
        
        
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

    private void generalCustomerRadioBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generalCustomerRadioBMouseClicked
        sql = "SELECT * FROM createtours.general_or_online_customer_view;";
        customerTabelLoad(sql);
        tableFilter(jTextField1, jTextField2, jTable2, 1, 2);
       
        
        
        
    }//GEN-LAST:event_generalCustomerRadioBMouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int r = jTable2.getSelectedRow();
        jLabel6.setText(jTable2.getValueAt(r, 0).toString());
        TableColumn col = null;
                col = jTable2.getColumnModel().getColumn(2);
                
               // System.out.println(col.getHeaderValue());
    }//GEN-LAST:event_jTable2MouseClicked

    private void govAndPvtRbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_govAndPvtRbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_govAndPvtRbActionPerformed

    private void generalCustomerRadioBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generalCustomerRadioBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generalCustomerRadioBActionPerformed
    
    //tableload for this class
    public void customerTabelLoad(String q) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rst = null; 
        
        con = DbConnect.connect();
        
        try {
            pst = con.prepareStatement(q);
            rst = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rst));
            
        } catch (SQLException ex) {
            Logger.getLogger(GetCustomerID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton generalCustomerRadioB;
    private javax.swing.JRadioButton govAndPvtRb;
    private javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public javax.swing.JTextField jTextField3;
    private javax.swing.ButtonGroup selectCustomerBgroup;
    // End of variables declaration//GEN-END:variables
    
  
    String sql = null;
    
   

   
}
