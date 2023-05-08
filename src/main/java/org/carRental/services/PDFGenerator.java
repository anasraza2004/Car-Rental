package org.carRental.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;

import javax.swing.JTable;

public class PDFGenerator {

    public PDFGenerator(String repName, JTable jTable, String pdfName, Date startDate, Date endDate) throws Exception {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
        document.open();

        Paragraph title = new Paragraph(repName);

        Paragraph sDate = new Paragraph("From = " + startDate);
        Paragraph eDate = new Paragraph("To = " + endDate);

        Paragraph space = new Paragraph(" ");

        PdfPTable table = new PdfPTable(jTable.getColumnCount());
        table.setWidthPercentage(100);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            table.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                table.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }
        document.add(title);
        document.add(sDate);
        document.add(eDate);
        document.add(space);
        document.add(table);
        document.close();
    }

    public PDFGenerator(String repName, JTable jTable, String pdfName) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
        document.open();

        Paragraph title = new Paragraph(repName);

        Paragraph space = new Paragraph(" ");

        PdfPTable table = new PdfPTable(jTable.getColumnCount());
        table.setWidthPercentage(100);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            table.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                table.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }
        document.add(title);
        document.add(space);
        document.add(table);
        document.close();
    }

    public static void yearlyRep(String repName, JTable jTable, String pdfName, Integer totalAmount, String total) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
        document.open();

        Paragraph title = new Paragraph(repName);

        Paragraph space = new Paragraph(" ");

        PdfPTable table = new PdfPTable(jTable.getColumnCount());
        table.setWidthPercentage(100);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            table.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }

        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                table.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }

        Paragraph commision = new Paragraph(total + totalAmount);
        document.add(title);
        document.add(space);
        document.add(table);
        document.add(commision);
        document.close();
    }
}