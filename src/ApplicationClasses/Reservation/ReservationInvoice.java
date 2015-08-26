/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationClasses.Reservation;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Namila Radith
 */
public class ReservationInvoice {
    private String invoiceNo;
    private Double deposit;
    private String reservationStartDate;
    private String reservationEndDate;
    private Double totalPrice;


    
    //invoiceDetails inner class
    /*----------------------------------------------------------------------------------------------------------*/
    
    public class invoiceDetails{
        
        private String description;
        private String regNo;
        private Double price;

        public  invoiceDetails( String description, String regNo, Double price) {
            
            this.description = description;
            this.regNo = regNo;
            this.price = price;
        }

        public Double getTotalPrice() {
        return totalPrice;
         }

        public String getDescription() {
            return description;
        }

        public String getRegNo() {
            return regNo;
        }

        public Double getPrice() {
            return price;
        }
        
        public void print(){
            System.out.print( this.description + "  "+ this.regNo + "  "+this.price );
            System.out.println("");
        }
        
    }
    
    /*----------------------------------------------------------------------------------------------------------*/
    
   private  ArrayList<invoiceDetails> invoiceDataArray = new ArrayList<invoiceDetails>();

    public ReservationInvoice(String invoiceNo, Double deposit, String reservationStartDate, String reservationEndDate) {
        this.invoiceNo = invoiceNo;
        this.deposit = deposit;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
    }
    
     public ReservationInvoice(){
         
     }
     
        public Double getTotalPrice() {
        return totalPrice;
    }
     
     public void getTotalCost(){
      Double tempPrice = 0.0;    
      for(invoiceDetails i : invoiceDataArray){
           tempPrice = tempPrice +i.price;
      }
      
       this.totalPrice = tempPrice;   
       
     }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public void setReservationStartDate(String reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public void setReservationEndDate(String reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
     
     

    public void setInvoiceDataArray(String description,String regNo, Double price) {
        this.invoiceDataArray.add(new invoiceDetails( description, regNo, price));
     
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public Double getDeposit() {
        return deposit;
    }

    public String getReservationStartDate() {
        return reservationStartDate;
    }

    public String getReservationEndDate() {
        return reservationEndDate;
    }

    public ArrayList<invoiceDetails> getInvoiceTable() {
        return invoiceDataArray;
    }
   
   public  void print(){
       System.out.println("invoice NO  "+this.invoiceNo);
       System.out.println("Deposit   " +this.deposit);
       System.out.println("Start Date  " +this.reservationStartDate);
       System.out.println("End Date  "+this.reservationEndDate);
       
       for(invoiceDetails i : invoiceDataArray)
           i.print();
       
       System.out.println("Total Cost : " + this.totalPrice);
          
       }
   
       
      public  void clear(){
        this.invoiceNo = null;
        this.deposit = null;
        this.reservationStartDate = null;
        this.reservationEndDate = null;
       
       this.invoiceDataArray.clear();
         
       
       this.totalPrice=null;
        
       }
      
      public void generatePDFinvoice(ReservationCustomer cus ) throws Exception{
          
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, 0);
        String date2 = dateFormat.format(c.getTime());
        
        
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(this.invoiceNo+".pdf"));
        doc.open();
        doc.add(new Paragraph("CREATE TOURS PVT LTD",FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.BOLD,BaseColor.RED)));
        
        Paragraph p = new Paragraph();
        
        p.add("Reservation Invoice");
        p.add("\n INVOICE DATE : " +date2);
        
        p.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(p);
        doc.add(new Paragraph(cus.getCustomerName()));
        doc.add(new Paragraph("-------------------------------------------------------------------------------------------------------------------------------"));
        //doc.add(new Paragraph("Deposit : " + this.deposit.toString() + "\t Resvation Start Date : " + this.reservationEndDate + "\t Reservation End Date : "+this.reservationEndDate  ));
        PdfPTable table = new PdfPTable(4);
        table.addCell("No");
        table.addCell("Description");
        table.addCell("VehicalRegNo");
        table.addCell("Price");
        for(int i = 0; i< invoiceDataArray.size();i++){
            Integer no =i+1;
            
            table.addCell(no.toString()); 
            table.addCell(invoiceDataArray.get(i).getDescription());
            table.addCell(invoiceDataArray.get(i).getRegNo());
            table.addCell(invoiceDataArray.get(i).getPrice().toString());
        }
        doc.add(table);
        
        doc.add(new Paragraph("Total Cost :" + this.totalPrice.toString()));
        doc.add(new Paragraph("Deposit : " + this.deposit.toString()));
        doc.add(new Paragraph("Resvation Start Date : " + this.reservationStartDate));
        doc.add(new Paragraph("Reservation End Date : "+this.reservationEndDate));
        
        
        doc.close();
      }
}
