package org.carRental.UI;

import org.carRental.services.VehicleService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleUI {
    private final VehicleService service = new VehicleService();

    public AddVehicleUI() {
        JFrame frame = new JFrame("Rental Car App | Add Vehicle");
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel name = new JLabel("Vehicle name");
        JTextField nameTf = new JTextField(20);

        JLabel color = new JLabel("Vehicle Color");
        JTextField colorTf = new JTextField(20);

        JLabel price = new JLabel("Vehicle Price");
        JTextField priceTf = new JTextField(20);

        JLabel ownerId = new JLabel("Owner ID");
        JTextField ownerIdTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(name);
        frame.add(nameTf);
        frame.add(color);
        frame.add(colorTf);
        frame.add(price);
        frame.add(priceTf);
        frame.add(ownerId);
        frame.add(ownerIdTf);
        frame.add(save);
        frame.add(back);

        save.addActionListener(e -> {
            service.add(nameTf.getText(), colorTf.getText(), priceTf.getText(), ownerIdTf.getText());
            frame.dispose();
            new VehicleUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new VehicleUI();
        });
    }
}
