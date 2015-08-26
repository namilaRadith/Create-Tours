/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sandbox;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.scene.paint.Color;

/**
 *
 * @author Namila Radith
 */
public class test2 {
    public static void main(String[] args) throws ParseException, DocumentException, FileNotFoundException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
         Calendar c = Calendar.getInstance();
         c.add(Calendar.DAY_OF_YEAR, 0);
        String date2 = dateFormat.format(c.getTime());
        
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("lol.pdf"));
        doc.open();
        doc.add(new Paragraph("CREATE TOURS PVT LTD",FontFactory.getFont(FontFactory.TIMES_BOLD,16,Font.BOLD,BaseColor.RED)));
        
        Paragraph p = new Paragraph();
        
        p.add("Reservation Invoice");
        p.add("\n INVOICE DATE : " +date2);
        
        p.setAlignment(Paragraph.ALIGN_CENTER);
        doc.add(p);
        doc.add(new Paragraph("-----------------------------------------------------------------------------------------------"));
        doc.add(new Paragraph(""));
        PdfPTable table = new PdfPTable(4);
        table.addCell("no");
        table.addCell("Description");
        table.addCell("VehicalRegNo");
        table.addCell("Price");
        
        doc.add(table);
        doc.close();
       
    }

    
}
