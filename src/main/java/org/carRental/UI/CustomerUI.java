package org.carRental.UI;

import org.carRental.services.CustomerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomerUI {
    private final CustomerService service = new CustomerService();

    public CustomerUI() {
        JFrame frame = new JFrame("Rental Car App | Customer");

        frame.setSize(1500, 1000);
        frame.setLayout(new GridLayout(1, 2, 150, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tableAndSearchPanel = new JPanel();
        tableAndSearchPanel.setBackground(Color.GRAY);
        tableAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField search = new JTextField(30);


        String data[][] = service.getAllCustomer();
        String column[] = {"Name", "Phone Number", "CNIC", "Address", "Ref_Phone_No"};

        DefaultTableModel dtm = new DefaultTableModel(data, column);
        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tableAndSearchPanel.add(search);
        tableAndSearchPanel.add(sp);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton add = new JButton("ADD");
        JButton update = new JButton("UPDATE");
        JButton delete = new JButton("DELETE");
        JButton back = new JButton("BACK");

        buttonsPanel.add(add);
        buttonsPanel.add(update);
        buttonsPanel.add(delete);
        buttonsPanel.add(back);

        frame.add(tableAndSearchPanel);
        frame.add(buttonsPanel);

        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = service.searchByName(search.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, column);
                jt.setModel(dtm);
            }
        });

        add.addActionListener(e -> {
            frame.dispose();
            new AddCustomerUI();
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}
