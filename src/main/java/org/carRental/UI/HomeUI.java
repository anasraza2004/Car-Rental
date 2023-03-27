package org.carRental.UI;

import javax.swing.*;
import java.awt.*;

public class HomeUI {

    public HomeUI() {
        JFrame frame = new JFrame("Rental Car App | Home");

        frame.setSize(1000, 800);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JButton buttonCustomer = new JButton("Customer");
        addIamgeOnButton(buttonCustomer, "src/main/resources/customer-icon.png", 100, 100);

        JButton buttonBooking = new JButton("Booking");
        addIamgeOnButton(buttonBooking, "src/main/resources/booking-icon.png", 100, 100);

        JButton buttonOwner = new JButton("Owner");
        addIamgeOnButton(buttonOwner, "src/main/resources/owner-icon.png", 100, 100);

        JButton buttonVehicle = new JButton("Vehicle");
        addIamgeOnButton(buttonVehicle, "src/main/resources/vehicle-icon.png", 100, 100);

        JButton buttonUser = new JButton("User");
        addIamgeOnButton(buttonUser, "src/main/resources/user-icon.png", 100, 100);

        JButton buttonLogOut = new JButton("Log out");
        addIamgeOnButton(buttonLogOut, "src/main/resources/logout-icon.png", 100, 100);

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

    private static void addIamgeOnButton(JButton button, String imgPath, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(imgPath);
        Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
    }
}