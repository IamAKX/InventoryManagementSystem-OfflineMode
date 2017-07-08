/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AkashApplications.src;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

/**
 *
 * @author akash
 */
public class GenerateInvoice {
    String buyerAddress, invoiceNote, date, paymentType;
    JTable table;
    DefaultTableModel dtm;
    String filePath = "Challan";
    String copyType;
    public GenerateInvoice(String buyerAddress, String invoiceNote, String date, JTable table, String paymentType, String copyType) {
        this.buyerAddress = buyerAddress;
        this.invoiceNote = invoiceNote;
        this.date = date;
        this.copyType = copyType; 
        this.paymentType = paymentType;
        this.table = table;
        dtm = (DefaultTableModel) table.getModel();
    }
    
    
    public boolean printInvoice(boolean printCommand) throws FileNotFoundException, InvalidFormatException, IOException
    {
        
        XWPFDocument document = new XWPFDocument();
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(720L));
        pageMar.setTop(BigInteger.valueOf(230L));
        pageMar.setRight(BigInteger.valueOf(720L));
        pageMar.setBottom(BigInteger.valueOf(460L));
        
        XWPFParagraph dateP = document.createParagraph();
        XWPFRun dateRun = dateP.createRun();
        dateRun.setText(copyType);
        dateRun.setFontSize(8);
        dateRun.setItalic(true);
        dateP.setAlignment(ParagraphAlignment.RIGHT);
        
        
        XWPFParagraph title = document.createParagraph();
        XWPFRun titleRun = title.createRun();
        titleRun.addBreak();
        titleRun.setText("Delivery Challan");
        titleRun.setBold(true);
        title.setAlignment(ParagraphAlignment.CENTER);
        titleRun.addBreak();
        
        
        //header table
        XWPFTable productTable = document.createTable();
        productTable.setCellMargins(50, 50, 50, 50);
        productTable.getCTTbl().getTblPr().unsetTblBorders();
        productTable.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(11000));
        
        XWPFTableRow row1 = productTable.getRow(0);
        
        XWPFTableCell cellSellerBuyer = row1.getCell(0);
        cellSellerBuyer.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2750));
        
        CTTc ctTc = cellSellerBuyer.getCTTc();
        CTTcPr tcPr = ctTc.getTcPr();
        CTTcBorders border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        
        XWPFParagraph seller = cellSellerBuyer.addParagraph();
        XWPFRun sellerRun = seller.createRun();
        seller.setAlignment(ParagraphAlignment.LEFT);
        sellerRun.setText("   PLYWOOD MANAOR");
        sellerRun.addBreak();
        sellerRun.setText("   L.R. KHATIAN 40/5,");
        sellerRun.addBreak();
        sellerRun.setText("   MOUZA KARIMPUR,");
        sellerRun.addBreak();
        sellerRun.setText("   KOLKATA - 700150.");
        
        
        
        XWPFTableCell cellbuyer = row1.createCell();
        cellbuyer.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3750+600));
        
        ctTc = cellbuyer.getCTTc();
        tcPr = ctTc.getTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        XWPFParagraph cellbuyerPara = cellbuyer.addParagraph();
        cellbuyerPara.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun cellbuyerRun = cellbuyerPara.createRun();
        cellbuyerRun.setText("   Buyer :");
        String[] ar = buyerAddress.split("\n");
        for(String s : ar)
        {
            cellbuyerRun.addBreak();
            cellbuyerRun.setText("   "+s);
            
        }
        
        
        
        XWPFTableCell cellProductDesc = row1.createCell();
        cellProductDesc.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(3500+400));

        ctTc = cellProductDesc.getCTTc();
        tcPr = ctTc.getTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 

        XWPFParagraph productDesc = cellProductDesc.addParagraph();
        productDesc.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun runInvoice = productDesc.createRun();
        
        
//        if(table.getRowCount() == 1)
//        {
//            runInvoice.addTab();
//            runInvoice.addTab();
//            runInvoice.addTab();
//            String imgLoc = "Barcodes/"+ dtm.getValueAt(0, 6) +".jpg";
//            FileInputStream image = new FileInputStream(imgLoc);
//            runInvoice.addPicture(image, XWPFDocument.PICTURE_TYPE_JPEG,imgLoc, Units.toEMU(200), Units.toEMU(50));
//            runInvoice.addBreak();
//            runInvoice.addBreak();
//        }
        
        runInvoice.setText(" Delivery Note : ");
        runInvoice.addTab();
        runInvoice.setText("PM/"+invoiceNote);
        runInvoice.addBreak();
        runInvoice.addBreak();
        runInvoice.addBreak();
        runInvoice.setText(" Date : ");
        runInvoice.addTab();
        runInvoice.setText(date);
        runInvoice.addBreak();
        runInvoice.addBreak();
        //document.createParagraph().createRun().addBreak();
        
        XWPFTable productDetails = document.createTable();
        productDetails.setCellMargins(50,50,50,50);
        //productDetails.getCTTbl().getTblPr().addNewJc().setVal(STJc.RIGHT);
        productDetails.getCTTbl().getTblPr().unsetTblBorders();
        productDetails.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(11000));
        
        XWPFTableRow pDetailHeader = productDetails.getRow(0);
        
        XWPFTableCell header0 = pDetailHeader.getCell(0);
        header0.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
        XWPFParagraph headerText = header0.addParagraph();
        XWPFRun run = headerText.createRun();
        run.setText("Sl. No.");
        run.setBold(true);
        ctTc = header0.getCTTc();
        tcPr = ctTc.addNewTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        
        
        XWPFTableCell header1 = pDetailHeader.createCell();
        header1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        XWPFParagraph headerText1 = header1.addParagraph();
        XWPFRun run1 = headerText1.createRun();
        run1.setText("Description of Goods");
        run1.setBold(true);
        ctTc = header1.getCTTc();
        tcPr = ctTc.addNewTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 

        XWPFTableCell header4 = pDetailHeader.createCell();
        header4.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
        XWPFParagraph headerText4 = header4.addParagraph();
        XWPFRun run4 = headerText4.createRun();
        run4.setText("Quantity");
        run4.setBold(true);
        ctTc = header4.getCTTc();
        tcPr = ctTc.addNewTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        
       
        
        for(int i=0; i<1; i++)
        {
            XWPFTableRow fTableRow = productDetails.createRow();
            for(int j=0; j<4; j++)
            {
                XWPFTableCell cell = fTableRow.getCell(j);
                try{
                    ctTc = cell.getCTTc();
                    tcPr = ctTc.addNewTcPr();
                    border = tcPr.addNewTcBorders();
                    //border.addNewBottom().setVal(STBorder.SINGLE);
                    border.addNewRight().setVal(STBorder.SINGLE);
                    border.addNewLeft().setVal(STBorder.SINGLE);
                    if(i==0)
                        border.addNewTop().setVal(STBorder.SINGLE); 
                }
                catch(Exception e)
                {
                    System.err.println(e.getMessage());
                }
                
                switch(j)
                {
                    case 0:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                        break;
                    case 1:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
                        break;
                    case 2:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
                        break;
                    
                }
                
            }
        }
        
        int qty = 0;
        
        for(int i=0; i<table.getRowCount(); i++)
        {
            XWPFTableRow fTableRow = productDetails.createRow();
            for(int j=0; j<table.getColumnCount()-1; j++)
            {
                XWPFTableCell cell = fTableRow.getCell(j);
                try{
                    ctTc = cell.getCTTc();
                    tcPr = ctTc.addNewTcPr();
                    border = tcPr.addNewTcBorders();
                    if(i == table.getRowCount()-1)
                    border.addNewBottom().setVal(STBorder.SINGLE);
                    border.addNewRight().setVal(STBorder.SINGLE);
                    border.addNewLeft().setVal(STBorder.SINGLE);
//                    border.addNewTop().setVal(STBorder.SINGLE); 
                }
                catch(Exception e)
                {
                    System.err.println(e.getMessage());
                }
                
                switch(j)
                {
                    case 0:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
                        cell.setText(String.valueOf(dtm.getValueAt(i, j)));
                        break;
                    case 1:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
                        cell.setText(String.valueOf(dtm.getValueAt(i, j)));
                        break;
                    case 2:
                        cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
                        cell.setText(String.valueOf(dtm.getValueAt(i, j)));
                        qty += Integer.parseInt(String.valueOf(dtm.getValueAt(i, j))); 
                        break;
                    
                }
                
            }
        }
        
        XWPFTableRow finalRow = productDetails.createRow();
        XWPFTableCell fc0 = finalRow.getCell(0);
        fc0.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
        
        
        XWPFTableCell fc3 = finalRow.getCell(1);
        fc3.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(8000));
        XWPFParagraph fp1= fc3.addParagraph();
        XWPFRun fRun1 = fp1.createRun();
        fRun1.setBold(true);
        fRun1.setText("TOTAL");
        
        XWPFTableCell fc4 = finalRow.getCell(2);
        fc4.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(2000));
        XWPFParagraph fp2= fc4.addParagraph();
        XWPFRun fRun2 = fp2.createRun();
        fRun2.setBold(true);
        fRun2.setText(String.valueOf(qty));
        
        
        ctTc = fc0.getCTTc();
        tcPr = ctTc.getTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        //border.addNewRight().setVal(STBorder.SINGLE);
         border.addNewLeft().setVal(STBorder.SINGLE);
         border.addNewTop().setVal(STBorder.SINGLE);
        
        
        
        ctTc = fc3.getCTTc();
        tcPr = ctTc.getTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        //border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        
        ctTc = fc4.getCTTc();
        tcPr = ctTc.getTcPr();
        border = tcPr.addNewTcBorders();
        border.addNewBottom().setVal(STBorder.SINGLE);
        border.addNewRight().setVal(STBorder.SINGLE);
        border.addNewLeft().setVal(STBorder.SINGLE);
        border.addNewTop().setVal(STBorder.SINGLE); 
        
        
        XWPFParagraph netPMethod = document.createParagraph();
        XWPFRun pMethodRun = netPMethod.createRun();
        pMethodRun.addBreak();
        pMethodRun.setItalic(true);
        pMethodRun.setFontSize(8);
        pMethodRun.setText("NB. - Goods sold to the above buyer is in "+paymentType+".");
        //pMethodRun.addBreak();
        
        
        XWPFTable FooterTable = document.createTable();
        FooterTable.setCellMargins(50, 50, 50, 50);
        FooterTable.getCTTbl().getTblPr().unsetTblBorders();
        FooterTable.getCTTbl().addNewTblPr().addNewTblW().setW(BigInteger.valueOf(11000));
        
        XWPFTableRow FooterTablerow1 = FooterTable.getRow(0);
        
        XWPFTableCell FooterTablerowcell1 = FooterTablerow1.getCell(0);
        FooterTablerowcell1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5500));
        
        
        
        XWPFParagraph custSign = FooterTablerowcell1.addParagraph();
        XWPFRun custSignRun = custSign.createRun();
        custSignRun.addBreak();
        custSignRun.setText("_________________________");
        custSignRun.addBreak();
        custSignRun.setText("  Customer's Signature");
        custSignRun.setFontSize(10);
        custSign.setAlignment(ParagraphAlignment.LEFT);
        
        XWPFTableCell FooterTablerowcell2 = FooterTablerow1.createCell();
        FooterTablerowcell2.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(5500));
        
        XWPFParagraph pmSign = FooterTablerowcell2.addParagraph();
        XWPFRun pmSignRun = pmSign.createRun();
        pmSignRun.setText("For PLYWOOD MANOR");
        pmSignRun.addBreak();
        pmSignRun.setText("_________________________");
        pmSignRun.addBreak();
        pmSignRun.setText("Authorized Signature");
        pmSignRun.setFontSize(10);
        pmSign.setAlignment(ParagraphAlignment.RIGHT);
        
        try{
            
            File f = new File(filePath);
            if(!f.exists())
            {
                f.mkdir();
            }
            FileOutputStream outputStream = new FileOutputStream(filePath + File.separator + "Challan" + invoiceNote.replace("/","_") + copyType.replace("/","_")+".docx");
            document.write(outputStream);
            outputStream.close();
            
            if(printCommand)
                printDocument();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        return true;
    }

    private void printDocument() {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.print(new File(filePath + File.separator + "Challan" + invoiceNote.replace("/","_") + copyType.replace("/","_")+".docx"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Printing Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
