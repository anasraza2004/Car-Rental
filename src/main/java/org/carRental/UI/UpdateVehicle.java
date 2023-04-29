package org.carRental.UI;

import org.carRental.services.VehicleService;

import javax.swing.*;
import java.awt.*;

public class UpdateVehicle {
    private final VehicleService service = new VehicleService();

    public UpdateVehicle(String id, String vehicleName, String vehicleColor, String vehiclePrice, String vehicleStatus, String ownerID) {


        JFrame frame = new JFrame("Rental Car App | Update Vehicle");
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setSize(500, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel name = new JLabel("Vehicle name");
        JTextField nameTf = new JTextField(20);

        JLabel color = new JLabel("Vehicle Color");
        JTextField colorTf = new JTextField(20);

        JLabel price = new JLabel("Vehicle Price");
        JTextField priceTf = new JTextField(20);

        JLabel status = new JLabel("Stauts");
        String[] dropdownOptions = {"Active", "Inactive"};
        JComboBox<String> statusdd = new JComboBox<>(dropdownOptions);

        JLabel ownerId = new JLabel("Owner ID");
        JTextField ownerIdTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        frame.add(name);
        frame.add(nameTf);
        frame.add(color);
        frame.add(colorTf);
        frame.add(price);
        frame.add(priceTf);
        frame.add(status);
        frame.add(statusdd);
        frame.add(ownerId);
        frame.add(ownerIdTf);
        frame.add(update);
        frame.add(back);

        nameTf.setText(vehicleName);
        colorTf.setText(vehicleColor);
        priceTf.setText(vehiclePrice);
        statusdd.setSelectedItem(vehicleStatus);
        ownerIdTf.setText(ownerID);

        update.addActionListener(e -> {
            service.update(id, nameTf.getText(), colorTf.getText(), priceTf.getText(), String.valueOf(statusdd.getSelectedItem()), ownerIdTf.getText());
            frame.dispose();
            new VehicleUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new VehicleUI();
        });
    }
}
