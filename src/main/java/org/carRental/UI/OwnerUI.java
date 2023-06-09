package org.carRental.UI;

import org.carRental.services.OwnerService;
import org.carRental.services.PDFGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class OwnerUI {
    OwnerService service = new OwnerService();

    public OwnerUI() {

        JFrame frame = new JFrame("Rental Car App | Owner");

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
        String column[] = {"Name", "CNIC", "Commission", "Address"};
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
        JButton pdf = new JButton("GENERATE PDF");
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
                String[][] data = service.getByName(search.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, column);
                jt.setModel(dtm);
            }
        });

        add.addActionListener(e -> {
            frame.dispose();
            new AddOwnerUI();
        });

        pdf.addActionListener(e -> {
            try {
                new PDFGenerator("Owner", jt, "Owner.pdf");
                File file = new File("Owner.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("File Not Found");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}