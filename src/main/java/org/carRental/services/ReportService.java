package org.carRental.services;

import org.carRental.dao.BookingDAO;
import org.carRental.dao.ReportsDAO;
import org.carRental.domain.Booking;
import org.carRental.domain.Vehicle;

import java.util.Date;
import java.util.List;

public class ReportService {

    private final BookingDAO bookingDAO = new BookingDAO();
    private final ReportsDAO reportsDAO = new ReportsDAO();

    public String[][] monthlyReport(Date startDate, Date endDate) {
        List<Booking> bookingList = bookingDAO.monthlyReports(startDate, endDate);
        return convertListTo2DArray(bookingList, 7);
    }

    public Integer totalProfit(Date startDate, Date endDate) {
        List<Booking> bookingList = bookingDAO.monthlyReports(startDate, endDate);
        Integer profit = 0;
        for (int i = 0; i < bookingList.size(); i++) {
            profit += bookingList.get(i).getAmount();
        }
        return profit;
    }

    public String[][] getAvailablevehicles() {
        List<Vehicle> vehicleList = reportsDAO.getVehicles();
        String[][] data = new String[vehicleList.size()][3];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = vehicleList.get(i).getVehicle_name();
            data[i][1] = vehicleList.get(i).getVehicle_color();
            data[i][2] = vehicleList.get(i).getOwner_name();
        }
        return data;
    }

    private String[][] convertListTo2DArray(List<Booking> bookingList, Integer columnSize) {
        String[][] data = new String[bookingList.size()][columnSize];
        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCustomer_id());
            data[i][1] += ", " + bookingList.get(i).getCustomer_name();
            data[i][2] = String.valueOf(bookingList.get(i).getVehicle_id());
            data[i][2] += ", " + bookingList.get(i).getVehicle_name();
            data[i][3] = String.valueOf(bookingList.get(i).getBooking_date());
            data[i][4] = String.valueOf(bookingList.get(i).getComplete_date());
            data[i][5] = String.valueOf(bookingList.get(i).getAmount());
            data[i][6] = bookingList.get(i).getStatus();
        }
        return data;
    }


}