package org.carRental.UI;


import com.itextpdf.text.DocumentException;
import lombok.SneakyThrows;
import org.carRental.services.PDFGenerator;
import org.carRental.services.ReportService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.stream.IntStream;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class YearlyReport {
    private final ReportService reportService = new ReportService();
    private JRadioButton radioOption1;
    private JRadioButton radioOption2;
    private JComboBox<Integer> yearDropdown;
    private JComboBox<String> radioDropdown;
    private JButton submitButton;
    private JButton backButton;
    private Boolean flag;


    public YearlyReport() {
        JFrame frame = new JFrame();
        frame.setTitle("Rental Car App | Yearly Report");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        // create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // create radio button panel
        JPanel radioPanel = new JPanel(new GridLayout(2, 1));
        radioPanel.setBorder(BorderFactory.createTitledBorder("Select Report Type"));

        radioOption1 = new JRadioButton("VEHICLE REPORT");
        radioOption2 = new JRadioButton("OWNER REPORT");

        radioOption1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioOption1.isSelected()) {
                    radioDropdown.setModel(new DefaultComboBoxModel<>(reportService.getVehicle()));
                    flag = true;
                }
            }
        });
        radioOption2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (radioOption2.isSelected()) {
                    radioDropdown.setModel(new DefaultComboBoxModel<>(reportService.getownersForDropDown()));
                    flag = false;
                }
            }
        });

        // create button group so that only one radio button can be selected at a time
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioOption1);
        buttonGroup.add(radioOption2);

        radioPanel.add(radioOption1);
        radioPanel.add(radioOption2);

        mainPanel.add(radioPanel);

        // create year dropdown panel
        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        yearPanel.setBorder(BorderFactory.createTitledBorder("Select Year"));

        int currentYear = Year.now().getValue();
        Integer[] yearOptions = IntStream.range(currentYear - 5, currentYear + 1).boxed().toArray(Integer[]::new);
        yearDropdown = new JComboBox<>(yearOptions);
        yearPanel.add(yearDropdown);

        mainPanel.add(yearPanel);


        // create radio dropdown panel
        JPanel radioDropdownPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioDropdownPanel.setBorder(BorderFactory.createTitledBorder("Select"));

        String[] radioOptions = {};
        radioDropdown = new JComboBox<>(radioOptions);
        radioDropdownPanel.add(radioDropdown);

        mainPanel.add(radioDropdownPanel);

        // create submit button panel
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        submitPanel.add(submitButton);
        submitPanel.add(backButton);

        mainPanel.add(submitPanel);

        // add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (flag) {
                        //vehicle
                        JTable tableVehicle = new JTable();
                        String[][] dataVeh = reportService.yearlyRepVehicle((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());
                        String[] headersVeh = {"Vehicle-Name", "Owner-Name", "Amount-per-day", "Commission-percent", "Booking-date", "Complete-date", "Days", "Total-amount", "Commision-price", "Profit"};
                        DefaultTableModel dtmVeh = new DefaultTableModel(dataVeh, headersVeh);
                        JTable jtVeh = new JTable(dtmVeh);
                        JScrollPane spVeh = new JScrollPane(jtVeh);

                        DefaultTableModel tableModelVehicle = new DefaultTableModel(dataVeh, headersVeh);
                        tableVehicle.setModel(tableModelVehicle);

                        Integer totalProfit = reportService.totalProfitByVehicle((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());

                        PDFGenerator.yearlyRep("Yearly Vehicle Report", tableVehicle, "yearly_vehicle_report.pdf", totalProfit, "Total Profit : ");
                        File file = new File("yearly_vehicle_report.pdf");
                        if (file.exists()) {
                            Desktop.getDesktop().open(file);
                        } else {
                            JOptionPane.showMessageDialog(frame, "File not Found");
                        }
                    } else {
                        //owner
                        JTable tableOwner = new JTable();
                        String[][] data = reportService.yearlyRepOwner((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());
                        String[] headers = {"Owner-Name", "Vehicle-Name", "Booking-Amount", "Commission-percent", "Commission-per-day", "Booking-date", "Complete-date", "Days", "Commision-price"};
                        DefaultTableModel dtm = new DefaultTableModel(data, headers);
                        JTable jt = new JTable(dtm);
                        JScrollPane sp = new JScrollPane(jt);

                        DefaultTableModel tableModelOwner = new DefaultTableModel(data, headers);
                        tableOwner.setModel(tableModelOwner);

                        Integer totalCommssion = reportService.totalCommssionOfOwner((Integer) yearDropdown.getSelectedItem(), (String) radioDropdown.getSelectedItem());

                        PDFGenerator.yearlyRep("Owner Yearly Report", tableOwner, "yearly_owner_report.pdf", totalCommssion, "Total Commision : ");
                        File file = new File("yearly_owner_report.pdf");
                        if (file.exists()) {
                            Desktop.getDesktop().open(file);
                        } else {
                            JOptionPane.showMessageDialog(frame, "File not Found");
                        }
                    }
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "Please select a field first");
                    throw (exception);
                }
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            new ReportsUI();
        });

        // set main panel as content pane
        frame.setContentPane(mainPanel);

        // set window size and center on screen
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}