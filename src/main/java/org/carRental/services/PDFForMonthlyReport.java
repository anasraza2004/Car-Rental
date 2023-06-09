package org.carRental.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.carRental.domain.Booking;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class PDFForMonthlyReport {

    public PDFForMonthlyReport(String repName, JTable jTable, String pdfName, Integer totalAmount, List<Booking> commissionList, Date startDate, Date endDate) throws Exception {
        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
        document.open();

        Paragraph title = new Paragraph(repName);
        Paragraph sDate = new Paragraph("From = " + startDate);
        Paragraph eDate = new Paragraph("To = " + endDate);

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

        Paragraph total = new Paragraph("Total amount = " + totalAmount);

        Paragraph commission = new Paragraph("Total commission = " + commissionList.get(0).getCommission());

        Integer profitAmount = totalAmount - commissionList.get(0).getCommission();
        Paragraph profit = new Paragraph("Total profit = " + profitAmount);

        Paragraph space = new Paragraph(" ");

        document.add(title);
        document.add(sDate);
        document.add(eDate);
        document.add(space);
        document.add(table);
        document.add(total);
        document.add(commission);
        document.add(profit);
        document.close();
    }
}