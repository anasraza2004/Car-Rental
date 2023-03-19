package org.carRental.UI;

import javax.swing.*;
import java.awt.*;

public class HomeUI {

    public HomeUI() {
        JFrame frame = new JFrame("Rental Car App | Home");
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(40, 30));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel panelBtns = new JPanel();
        panelBtns.setLayout(new FlowLayout());

        JButton buttonCustomer = new JButton("Customer");
        buttonCustomer.setBounds(1, 1, 200, 150);

        JButton buttonBooking = new JButton("Booking");
        buttonCustomer.setBounds(1, 1, 200, 150);

        JButton buttonOwner = new JButton("Owner");
        buttonCustomer.setBounds(1, 1, 200, 150);

        JButton buttonVehicle = new JButton("Vehicle");
        buttonCustomer.setBounds(1, 1, 200, 150);

        JButton buttonUser = new JButton("User");
        buttonCustomer.setBounds(1, 1, 200, 150);

        JButton buttonLogOut = new JButton("Log out");
        buttonCustomer.setBounds(1, 1, 200, 150);


        frame.add(panelBtns, BorderLayout.CENTER);

        panelBtns.add(buttonCustomer);
        panelBtns.add(buttonBooking);
        panelBtns.add(buttonOwner);
        panelBtns.add(buttonVehicle);
        panelBtns.add(buttonUser);
        panelBtns.add(buttonLogOut);

        buttonLogOut.addActionListener((event) -> {
            frame.dispose();
            new LoginForm();
        });
    }


}
