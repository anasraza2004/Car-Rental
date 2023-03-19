package org.carRental.UI;

import javax.swing.*;
import java.awt.*;

public class BasicUI extends JFrame {
    public BasicUI() {
//        JFrame screen = new JFrame();
        JButton button = new JButton("Hello world");
        button.setBounds(100, 100, 150, 50);

        JLabel label = new JLabel();
        label.setBounds(100, 200, 150, 50);

        JTextField textField = new JTextField();
        textField.setBounds(100, 10, 150, 50);


        //adding on screen
        add(button);
        add(label);
        add(textField);


        //basic properties
        setSize(500, 500);
        setLayout(null);                            //frame p cheezen stretch kr k nhi dalta
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close button p program execute hojaega
        setLocationRelativeTo(null);                //frame centre ma aaegi screen k


        //methods
        button.addActionListener((b) -> {
//            System.out.println("Hello Stepway");
            label.setText(textField.getText());
            label.setForeground(Color.RED);
        });

    }
}
