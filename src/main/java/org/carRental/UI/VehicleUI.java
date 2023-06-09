package org.carRental.UI;

import org.carRental.dao.VehicleDAO;
import org.carRental.services.VehicleService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VehicleUI {
    private final VehicleDAO dao = new VehicleDAO();
    private final VehicleService service = new VehicleService();

    public VehicleUI() {
        JFrame frame = new JFrame("Rental Car App | Vehicle");
        frame.setSize(1500, 1000);
        frame.setLayout(new GridLayout(1, 2, 150, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel tableAndSearchPanel = new JPanel();
        tableAndSearchPanel.setBackground(Color.GRAY);
        tableAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField search = new JTextField(30);

        String data[][] = service.getAll();
        String column[] = {"ID", "Name", "Color", "Price", "Status", "Owner Id"};
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
                String data[][] = service.getByName(search.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, column);
                jt.setModel(dtm);
            }
        });

        add.addActionListener(e -> {
            frame.dispose();
            new AddVehicleUI();
        });

        update.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                String id = (String) jt.getValueAt(jt.getSelectedRow(), 0);
                String name = (String) jt.getValueAt(jt.getSelectedRow(), 1);
                String color = (String) jt.getValueAt(jt.getSelectedRow(), 2);
                String price = (String) jt.getValueAt(jt.getSelectedRow(), 3);
                String status = (String) jt.getValueAt(jt.getSelectedRow(), 4);
                String ownerID = (String) jt.getValueAt(jt.getSelectedRow(), 5);
                frame.dispose();
                new UpdateVehicle(id, name, color, price, status, ownerID);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a field");
            }
        });

        delete.addActionListener(e -> {
            if (jt.getSelectedRow() >= 0) {
                dao.softDelete(Integer.valueOf((String) jt.getValueAt(jt.getSelectedRow(), 0)));
                DefaultTableModel dtmdelte = new DefaultTableModel(service.getAll(), column);
                jt.setModel(dtmdelte);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select the row");
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}