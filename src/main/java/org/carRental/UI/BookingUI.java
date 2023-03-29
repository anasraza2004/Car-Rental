package org.carRental.UI;

import org.carRental.services.BookingService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BookingUI {
    private final BookingService service = new BookingService();

    public BookingUI() {
        JFrame frame = new JFrame("Rental Car App | Booking");

        frame.setSize(1500, 1000);
        frame.setLayout(new GridLayout(1, 2, 150, 5));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel tableAndSearchPanel = new JPanel();
        tableAndSearchPanel.setBackground(Color.GRAY);
        tableAndSearchPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JTextField search = new JTextField(30);

        String[][] data = service.getAllBookngs();
        String[] headers = {"Customer-id", "Vehicle-id", "Booking-date", "Amount'"};
        DefaultTableModel dtm = new DefaultTableModel(data, headers);
        JTable jt = new JTable(dtm);
        JScrollPane sp = new JScrollPane(jt);

        tableAndSearchPanel.add(search);
        tableAndSearchPanel.add(sp);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton add = new JButton("ADD");
        JButton edit = new JButton("EDIT");
        JButton delete = new JButton("DELETE");
        JButton back = new JButton("BACK");

        buttonsPanel.add(add);
        buttonsPanel.add(edit);
        buttonsPanel.add(delete);
        buttonsPanel.add(back);

        frame.add(tableAndSearchPanel);
        frame.add(buttonsPanel);


        back.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });

        search.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data = service.searchByDate(search.getText());
                DefaultTableModel dtm = new DefaultTableModel(data, headers);
                jt.setModel(dtm);
            }
        });

        add.addActionListener(e -> {
            frame.dispose();
            new AddBookingUI();
        });

    }
}
