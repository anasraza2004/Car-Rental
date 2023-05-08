package org.carRental.UI;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import org.carRental.services.PDFGenerator;
import org.carRental.services.ReportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class AnalyticsReport {
    private final ReportService reportService = new ReportService();

    public AnalyticsReport() {
        JFrame frame = new JFrame("Rental Car App | Monthly Report");

        frame.setSize(1000, 700);
        frame.setLayout(new GridLayout(1, 2, 40, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tablePanel = new JPanel();

        String[][] rows = {};
        String[] headers = {"Most-booked Cars", "Least-booked Cars", "Most-Commission", "Most profitable"};
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
            String[][] data = reportService.analyticsRep(startDate.getDate(), endDate.getDate());
            DefaultTableModel tableModel = new DefaultTableModel(data, headers);
            jt.setModel(tableModel);
            try {
                new PDFGenerator("Analytics Report", jt, "Analytics.pdf", startDate.getDate(), endDate.getDate());
                File file = new File("Analytics.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "PDF not found");
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
