package org.carRental.UI;

import javax.swing.*;
import java.awt.*;

public class CustomerUI {
    public CustomerUI() {

        JFrame frame = new JFrame("Rental Car App | Customer");
        frame.setSize(1000, 800);
        frame.setLayout(new BorderLayout(10, 50));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel panelSearch = new JPanel();

        JTextField searchTF = new JTextField();
        searchTF.setBounds(0, 20, 500, 10);

        panelSearch.add(searchTF);


        JPanel panelBtns = new JPanel();
        panelBtns.setLayout(new GridLayout(10, 1, 10, 10));

        JButton add_button = new JButton("Add");
        add_button.setSize(150, 50);

        JButton edit_button = new JButton("Edit");
        edit_button.setSize(150, 50);

        JButton delete_button = new JButton("Delete");
        delete_button.setSize(150, 50);

        JButton back_button = new JButton("Back");
        back_button.setSize(150, 50);

        panelBtns.add(add_button);
        panelBtns.add(edit_button);
        panelBtns.add(delete_button);
        panelBtns.add(back_button);


        JPanel panelTable = new JPanel();

        String data[][] = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};

        String column[] = {"ID", "NAME", "SALARY"};

        JTable table = new JTable(data, column);
        table.setSize(500, 700);
        panelTable.add(table);


        frame.add(panelSearch, BorderLayout.NORTH);
        frame.add(panelBtns, BorderLayout.EAST);
        frame.add(panelTable, BorderLayout.CENTER);

        back_button.addActionListener(e -> {
            frame.dispose();
            new HomeUI();
        });
    }
}
