package org.carRental.UI;

import com.toedter.calendar.JCalendar;
import org.carRental.dao.BookingDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Book;

public class CompleteBookingDate {
    BookingDAO dao = new BookingDAO();

    public CompleteBookingDate(String id) {

        JFrame frame = new JFrame("Rental Car App | Complete Booking");
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 2, 5, 3));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel datePanel = new JPanel();

        JCalendar completionDate = new JCalendar();

        datePanel.add(completionDate);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 1, 0, 0));

        JButton done = new JButton("Done");
        JButton back = new JButton("Back");

        buttonsPanel.add(done);
        buttonsPanel.add(back);

        frame.add(datePanel);
        frame.add(buttonsPanel);

        done.addActionListener(e -> {
            dao.completeBooking(id, completionDate.getDate());
            frame.dispose();
            new BookingUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });
    }
}