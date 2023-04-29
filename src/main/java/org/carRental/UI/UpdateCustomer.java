package org.carRental.UI;

import org.carRental.services.CustomerService;

import javax.swing.*;
import java.awt.*;

public class UpdateCustomer {
    CustomerService service = new CustomerService();

    public UpdateCustomer(String id, String name, String phoneNo, String cnic, String address, String refNo, String cusStatus) {
        JFrame frame = new JFrame("Rental Car App | Update Customer");
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel phoneLb = new JLabel("PHONE");
        JTextField phoneTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel refLb = new JLabel("REF_PHONE");
        JTextField refTf = new JTextField(20);

        JLabel status = new JLabel("Status");
        String[] dropdownOptions = {"Active", "Inactive"};
        JComboBox<String> statusdd = new JComboBox<>(dropdownOptions);

        JButton back = new JButton("BACK");
        JButton update = new JButton("UPDATE");

        nameTf.setText(name);
        phoneTf.setText(phoneNo);
        cnicTf.setText(cnic);
        addressTf.setText(address);
        refTf.setText(refNo);
        statusdd.setSelectedItem(cusStatus);

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(refLb);
        frame.add(refTf);
//        if (cusStatus == "Inactive") {
        frame.add(status);
        frame.add(statusdd);
//        }
        frame.add(update);
        frame.add(back);

        back.addActionListener(e -> {
            frame.dispose();
            new CustomerUI();
        });

        update.addActionListener(e -> {
            service.updateCustomer(id, nameTf.getText(), phoneTf.getText(), cnicTf.getText(), addressTf.getText(), refTf.getText(), (String) statusdd.getSelectedItem());
            frame.dispose();
            new CustomerUI();
        });
    }
}