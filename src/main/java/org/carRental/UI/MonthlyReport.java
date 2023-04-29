package org.carRental.UI;

import com.toedter.calendar.JCalendar;
import org.carRental.dao.BookingDAO;
import org.carRental.domain.Booking;
import org.carRental.services.PDFForMonthlyReport;
import org.carRental.services.PDFGenerator;
import org.carRental.services.ReportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MonthlyReport {
    private final ReportService service = new ReportService();
    private final BookingDAO dao = new BookingDAO();

    public MonthlyReport() {
        JFrame frame = new JFrame("Rental Car App | Monthly Report");

        frame.setSize(1000, 700);
        frame.setLayout(new GridLayout(1, 2, 40, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tablePanel = new JPanel();

        String[][] rows = {};
        String[] headers = {"Id", "Customer", "Vehicle", "Booking-date", "Complete-date", "Amount", "Status"};
        DefaultTableModel dtm = new DefaultTableModel(rows, headers);
        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tablePanel.add(sp);

        JPanel datePanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JCalendar startDate = new JCalendar();
        JCalendar endDate = new JCalendar();

        JButton done = new JButton("Done");
        JButton back = new JButton("Back");

        datePanel.add(startDate);
        datePanel.add(endDate);
        datePanel.add(done);
        datePanel.add(back);

        frame.add(datePanel);
        frame.add(tablePanel);

        done.addActionListener(e -> {
            String[][] data = service.monthlyReport(startDate.getDate(), endDate.getDate());
            DefaultTableModel tableModel = new DefaultTableModel(data, headers);
            jt.setModel(tableModel);
            Integer profit = service.totalProfit(startDate.getDate(), endDate.getDate());
            List<Booking> commission = dao.totalCommission(startDate.getDate(), endDate.getDate());
            try {
                new PDFForMonthlyReport(jt, "Monthly-Report.pdf", profit, commission);
                File file = new File("Monthly-Report.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "File not Found");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
            new ReportsUI();
        });

    }
}