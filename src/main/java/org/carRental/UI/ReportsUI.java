package org.carRental.UI;

import org.carRental.dao.ReportsDAO;
import org.carRental.services.PDFGenerator;
import org.carRental.services.ReportService;
import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class ReportsUI {
    private final ReportService reportService = new ReportService();

    public ReportsUI() {
        JFrame frame = new JFrame("Rental Car App | Reports");

        frame.setSize(900, 700);
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton monthlyRep = new JButton("Monthly Report");
        JButton commisionRep = new JButton("Commision Report");
        JButton carAvaRep = new JButton("Car Availability Report");
        JButton analyticsRep = new JButton("Analytics Report");
        JButton back = new JButton("Back");

        frame.add(monthlyRep);
        frame.add(commisionRep);
        frame.add(carAvaRep);
        frame.add(analyticsRep);
        frame.add(back);

        monthlyRep.addActionListener(e -> {
            frame.dispose();
            new MonthlyReport();
        });

        commisionRep.addActionListener(e -> {
            frame.dispose();
            new CommisionReport();
        });

        carAvaRep.addActionListener(e -> {
            JTable jt = new JTable();
            String[][] data = reportService.getAvailablevehicles();
            String[] headers = {"Vehicle name", "Vehicle color", "Owner name"};
            DefaultTableModel tableModel = new DefaultTableModel(data, headers);
            jt.setModel(tableModel);
            try {
                new PDFGenerator(jt, "Car Availability.pdf");
                File file = new File("Car Availability.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(frame, "File not found");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        analyticsRep.addActionListener(e -> {
            frame.dispose();
            new AnalyticsReport();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}