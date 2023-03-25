package org.carRental.UI;

import javax.swing.*;
import java.awt.*;

public class HomeUI {

    public HomeUI() {
        JFrame frame = new JFrame("Rental Car App | Home");
        frame.setSize(1000, 800);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JButton buttonCustomer = new JButton("Customer");
        ImageIcon imageIcon = new ImageIcon("src/main/resources/customer-icon.png");
        
        buttonCustomer.setIcon(imageIcon);

        JButton buttonBooking = new JButton("Booking");
        JButton buttonOwner = new JButton("Owner");
        JButton buttonVehicle = new JButton("Vehicle");
        JButton buttonUser = new JButton("User");
        JButton buttonLogOut = new JButton("Log out");

        frame.add(buttonCustomer);
        frame.add(buttonBooking);
        frame.add(buttonOwner);
        frame.add(buttonVehicle);
        frame.add(buttonUser);
        frame.add(buttonLogOut);

        buttonCustomer.addActionListener((event) -> {
            frame.dispose();
            new CustomerUI();
        });

        buttonBooking.addActionListener((event) -> {
            frame.dispose();
            new BookingUI();
        });

        buttonOwner.addActionListener((event) -> {
            frame.dispose();
            new OwnerUI();
        });

        buttonVehicle.addActionListener((event) -> {
            frame.dispose();
            new VehicleUI();
        });

        buttonUser.addActionListener((event) -> {
            frame.dispose();
            new UserUI();
        });

        buttonLogOut.addActionListener((event) -> {
            frame.dispose();
            new LoginForm();
        });

    }
}
