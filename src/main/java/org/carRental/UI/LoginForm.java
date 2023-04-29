package org.carRental.UI;

import org.carRental.services.AuthenticationService;

import javax.swing.*;

public class LoginForm {
    AuthenticationService authenticationService = new AuthenticationService();

    public LoginForm() {
        JFrame frame = new JFrame("Rental Car App");

        JLabel username = new JLabel("User name");
        username.setBounds(50, 50, 100, 50);

        JTextField userText = new JTextField();
        userText.setBounds(150, 60, 200, 20);

        JLabel password = new JLabel("Password");
        password.setBounds(50, 100, 100, 50);

        JTextField passwordText = new JTextField();
        passwordText.setBounds(150, 110, 200, 20);

        JButton button = new JButton("Submit");
        button.setBounds(200, 200, 100, 30);

        frame.add(username);
        frame.add(userText);
        frame.add(password);
        frame.add(passwordText);
        frame.add(button);

        //Basic frame properties
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        button.addActionListener((event) -> {
            if (authenticationService.checkResult(userText.getText(), passwordText.getText())) {
                frame.dispose();
                new HomeUI();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Credentials");
                userText.setText("");
                passwordText.setText("");
            }
        });
    }
}