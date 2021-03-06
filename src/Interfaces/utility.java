/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import ApplicationClasses.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author HaNi
 */
public class utility extends javax.swing.JFrame {
     Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    /**
     * Creates new form utility
     */
    public utility() {
        initComponents();
        
         con = DbConnect.connect();
         tableload1();
          hideFields();
    }

         public void tableload1()
    {
        try
        {
            String sql = "SELECT * from utility";
            pst = con.prepareStatement(sql); 
            rs = pst.executeQuery();
            
            jtutility.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } 
        catch (Exception e)
        {
        }
        
    }
         
         public void hideFields()
        {
            uIDlbl.setVisible(false);
        }
     
     public void clearFields()
     {
        jtblno.setText(null);
        jtloc.setText(null);
        jcuti.setSelectedItem("Select");
        jcuni.setSelectedItem("Select");
        dcper.setDate(null);
        jtuamo.setText(null);
        uIDlbl.setText(null);
        
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel27 = new javax.swing.JLabel();
        jtblno = new javax.swing.JTextField();
        jtloc = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jcuti = new javax.swing.JComboBox();
        jcuni = new javax.swing.JComboBox();
        jLabel26 = new javax.swing.JLabel();
        jusave = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        dcper = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jtuamo = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        uIDlbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtutility = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setText(" Bill No");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 50, 20));

        jtblno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtblnoActionPerformed(evt);
            }
        });
        getContentPane().add(jtblno, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 320, -1));

        jtloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtlocActionPerformed(evt);
            }
        });
        getContentPane().add(jtloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 320, -1));

        jLabel24.setText("Locations");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel25.setText("Utility");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jcuti.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Electricity", "Water", "Telephone", "Other", "" }));
        jcuti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcutiActionPerformed(evt);
            }
        });
        getContentPane().add(jcuti, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 320, -1));

        jcuni.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "KiloWatt Hour", "Liter", "unit", "" }));
        jcuni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcuniActionPerformed(evt);
            }
        });
        getContentPane().add(jcuni, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 320, -1));

        jLabel26.setText("Unit");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jusave.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jusave.setText("Save");
        jusave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jusaveActionPerformed(evt);
            }
        });
        getContentPane().add(jusave, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 80, -1));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 400, 80, -1));
        getContentPane().add(dcper, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 320, -1));

        jLabel30.setText("Date");
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel31.setText("Amount");
        getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));

        jtuamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtuamoActionPerformed(evt);
            }
        });
        jtuamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jtuamoKeyReleased(evt);
            }
        });
        getContentPane().add(jtuamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 110, -1));

        jLabel32.setText("/=");
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, -1, -1));

        uIDlbl.setText("UtiliyID");
        getContentPane().add(uIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 50, -1));

        jtutility.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BillName", "Location", "Utility", "Unit", "Period", "Amount"
            }
        ));
        jtutility.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtutilityMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtutility);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 580, 271));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Utilty Bill Handling");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 400, 70, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtblnoActionPerformed
        jtloc.grabFocus();
    }//GEN-LAST:event_jtblnoActionPerformed

    private void jtlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtlocActionPerformed
        jcuti.grabFocus();
    }//GEN-LAST:event_jtlocActionPerformed

    private void jcutiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcutiActionPerformed
        jcuni.grabFocus();
    }//GEN-LAST:event_jcutiActionPerformed

    private void jcuniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcuniActionPerformed
        dcper.grabFocus();
    }//GEN-LAST:event_jcuniActionPerformed

    private void jusaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jusaveActionPerformed
        int index1 = jcuti.getSelectedIndex();
        int index2= jcuni.getSelectedIndex();

        String bno = jtblno.getText();
        String loc = jtloc.getText();
        String uti = jcuti.getSelectedItem().toString();
        String uni = jcuni.getSelectedItem().toString();
        Date per = dcper.getDate();
        String amo = jtuamo.getText();

        SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");
        try
        {

            String strDate = sdfFrom.format(per);

            if (bno.isEmpty())
            {

               JOptionPane.showMessageDialog(null, "Please Fill the Bill No field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if (loc.isEmpty())
            {

                JOptionPane.showMessageDialog(null, "Please Fill the Location field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else if (index1==0)
            {

                JOptionPane.showMessageDialog(null, "Please select a Utility Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
                
            }

            else if (index2==0)
            {

               JOptionPane.showMessageDialog(null, "Please select a Unit Type", "Field not selected",JOptionPane.WARNING_MESSAGE);
            }

            /*else if (StringUtils.isEmptyOrWhitespaceOnly(per.toString()))
            {

                java.awt.Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(this, "Select Period...!!!","error",0);
            }*/
            else if (amo.isEmpty())
            {

                JOptionPane.showMessageDialog(null, "Please Fill the Amount field  ", "Field is empty",JOptionPane.WARNING_MESSAGE);
            }

            else{
                try
                {

                    String q = "INSERT INTO utility (BillNo,Location,Utility,Unit,Period,Amount) values ('"+ bno +"' , '"+ loc +"' , '"+ uti +"' , '"+ uni +"' , '"+ strDate +"' , '"+ amo +"')";
                    pst = con.prepareStatement(q);
                    pst.execute();

                    tableload1();

                    tableload1();
                    JOptionPane.showMessageDialog(null, "Saved");
                }
                
                catch (Exception e)
                {
                    System.out.print(e);

                }
                clearFields();
            }
        }

        catch(NullPointerException E)
        {
            JOptionPane.showMessageDialog(null, "Date field cannot be empty and \nshould be in date format", "Field Empty or Input mismatch",JOptionPane.WARNING_MESSAGE);
            
        }

    }//GEN-LAST:event_jusaveActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update....?");

        if(x==0)
        {

            String bno = jtblno.getText();

            String loc = jtloc.getText();
            String uti = jcuti.getSelectedItem().toString();
            String uni = jcuni.getSelectedItem().toString();
            Date date = dcper.getDate();

            SimpleDateFormat sdfFrom = new SimpleDateFormat("yyyy-MM-dd");

            String strDate = sdfFrom.format(date);

            String amo = jtuamo.getText();

            String sql = "UPDATE utility SET Location = '"+ loc +"' , Utility = '"+ uti +"' , Unit = '"+ uni +"' , Period = '"+ strDate +"',Amount = '"+ amo +"' where BillNo = '"+ bno +"'";

            try
            {

                pst = con.prepareStatement(sql);
                pst.execute();

                tableload1();
                clearFields();

            }
            catch (Exception e)
            {
                System.out.print(e);
            }

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jtuamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtuamoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtuamoActionPerformed

    private void jtuamoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtuamoKeyReleased
        int count = 0;
        String checkname = jtuamo.getText();

        for (int i = 0; i < checkname.length(); i++) {
            char c = checkname.charAt(i);
            if (Character.isDigit(c)) {

            }
            else if(Character.isLetter(c))
            {
                JOptionPane.showMessageDialog(null, "cannot have letters", "Error", WIDTH);
                jtuamo.setText("");

            }
            else
            {
                JOptionPane.showMessageDialog(null, "cannot have Special Characters", "Error", WIDTH);
                jtuamo.setText("");

            }

        }
    }//GEN-LAST:event_jtuamoKeyReleased

    private void jtutilityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtutilityMouseClicked
        int row = jtutility.getSelectedRow();

        String bno = jtutility.getValueAt(row, 0).toString();
        String bname = jtutility.getValueAt(row, 1).toString();
        String loc = jtutility.getValueAt(row, 2).toString();
        String uti = jtutility.getValueAt(row, 3).toString();
        String uni = jtutility.getValueAt(row, 4).toString();
        //  JOptionPane.showMessageDialog(rootPane,jtutility.getValueAt(row, 5) );
        Date d = (Date) jtutility.getValueAt(row, 5);
        String amo = jtutility.getValueAt(row, 6).toString();

        jtblno.setText(bname);

        jtloc.setText(loc);
        jcuti.setSelectedItem(uti);
        jcuni.setSelectedItem(uni);

        jtuamo.setText(amo);
        uIDlbl.setText(bno);

        dcper.setDate(d);

    }//GEN-LAST:event_jtutilityMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
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
            java.util.logging.Logger.getLogger(utility.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(utility.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(utility.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(utility.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new utility().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dcper;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcuni;
    private javax.swing.JComboBox jcuti;
    private javax.swing.JTextField jtblno;
    private javax.swing.JTextField jtloc;
    private javax.swing.JTextField jtuamo;
    private javax.swing.JTable jtutility;
    private javax.swing.JButton jusave;
    private javax.swing.JLabel uIDlbl;
    // End of variables declaration//GEN-END:variables
}
