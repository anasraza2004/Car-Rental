package org.carRental.UI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarAvailabilityReport {
    public CarAvailabilityReport() {
        JFrame frame = new JFrame("Rental Car App | Reports");

        frame.setSize(1000, 700);
        frame.setLayout(new GridLayout(1, 2, 40, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tablePanel = new JPanel();

        String[][] data = {};
        String[] headers = {"Id", "Customer", "Vehicle", "Booking-date", "Amount", "Status"};
        DefaultTableModel dtm = new DefaultTableModel(data, headers);
        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tablePanel.add(sp);

        JPanel datePanel = new JPanel(new GridLayout(2, 2, 10, 10));

        JDateChooser startDate = new JDateChooser();
        startDate.setDateFormatString("yyyy-MM-dd");

        JDateChooser endDate = new JDateChooser();
        endDate.setDateFormatString("yyyy-MM-dd");

        JButton done = new JButton("Done");
        JButton back = new JButton("Back");

        datePanel.add(startDate);
        datePanel.add(endDate);
        datePanel.add(done);
        datePanel.add(back);

        frame.add(datePanel);
        frame.add(tablePanel);
    }
}
