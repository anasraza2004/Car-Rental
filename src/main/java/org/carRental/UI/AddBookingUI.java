package org.carRental.UI;

import org.carRental.services.BookingService;

import javax.swing.*;
import java.awt.*;

public class AddBookingUI {
    BookingService service = new BookingService();

    public AddBookingUI() {
        JFrame frame = new JFrame("Rental Car App | Add Customer");
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel customerId = new JLabel("Customer ID");
        JTextField customerIdTf = new JTextField(20);

        JLabel vehicleId = new JLabel("Vehicle ID");
        JTextField vehicleIdTf = new JTextField(20);

        JLabel date = new JLabel("Booking date");
        JTextField dateTf = new JTextField(20);

        JLabel amount = new JLabel("Amount");
        JTextField amountTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(customerId);
        frame.add(customerIdTf);
        frame.add(vehicleId);
        frame.add(vehicleIdTf);
        frame.add(date);
        frame.add(dateTf);
        frame.add(amount);
        frame.add(amountTf);
        frame.add(save);
        frame.add(back);


        back.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });

        save.addActionListener(e -> {
            service.add(customerIdTf.getText(), vehicleIdTf.getText(), dateTf.getText(), amountTf.getText());
            frame.dispose();
            new BookingUI();
        });
    }
}
