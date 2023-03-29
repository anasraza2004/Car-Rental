package org.carRental.UI;

import org.carRental.services.OwnerService;

import javax.swing.*;
import java.awt.*;

public class AddOwnerUI {
    private final OwnerService service = new OwnerService();

    public AddOwnerUI() {
        JFrame frame = new JFrame("Rental Car App | Add Owner");
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel name = new JLabel("Owner name");
        JTextField nameTf = new JTextField(20);

        JLabel cnic = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel commission = new JLabel("Comission");
        JTextField commissionTf = new JTextField(20);

        JLabel address = new JLabel("Address");
        JTextField addressTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(name);
        frame.add(nameTf);
        frame.add(cnic);
        frame.add(cnicTf);
        frame.add(commission);
        frame.add(commissionTf);
        frame.add(address);
        frame.add(addressTf);
        frame.add(save);
        frame.add(back);

        save.addActionListener(e -> {
            service.add(nameTf.getText(), cnicTf.getText(), commissionTf.getText(), addressTf.getText());
            frame.dispose();
            new OwnerUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new OwnerUI();
        });
    }
}
