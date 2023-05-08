package org.carRental.UI;

import org.carRental.dao.CustomerDAO;
import org.carRental.services.CustomerService;
import org.carRental.services.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class CustomerUI {
    private final CustomerService service = new CustomerService();
    private final CustomerDAO dao = new CustomerDAO();

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
        String column[] = {"ID", "Name", "Phone Number", "CNIC", "Address", "Ref_Phone_No", "Status"};

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
        JButton pdf = new JButton("Generate PDF");
        JButton back = new JButton("BACK");

        buttonsPanel.add(add);
        buttonsPanel.add(update);
        buttonsPanel.add(delete);
        buttonsPanel.add(pdf);
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

        update.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String name = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String phoneNo = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String cnic = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String address = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String refNo = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                String status = (String) jt.getValueAt(jt.getSelectedRow(), 6);
                frame.dispose();
                new UpdateCustomer(id, name, phoneNo, cnic, address, refNo, status);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the field");
            }
        });

        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                dao.softDelete(Integer.valueOf((String) jt.getValueAt(jt.getSelectedRow(), 0)));
                DefaultTableModel dtmdelte = new DefaultTableModel(service.getAllCustomer(), column);
                jt.setModel(dtmdelte);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });

        pdf.addActionListener(e -> {
            try {
                new PDFGenerator("Customer", jt, "Customer.pdf");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            try {
                File file = new File("Customer.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("File not Found");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}