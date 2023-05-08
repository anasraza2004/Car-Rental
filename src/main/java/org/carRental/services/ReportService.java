package org.carRental.services;

import org.carRental.dao.BookingDAO;
import org.carRental.dao.ReportsDAO;
import org.carRental.domain.Booking;
import org.carRental.domain.Owner;
import org.carRental.domain.Vehicle;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {

    private final BookingDAO bookingDAO = new BookingDAO();
    private final ReportsDAO reportsDAO = new ReportsDAO();

    public String[][] monthlyReport(Date startDate, Date endDate) {
        List<Booking> bookingList = bookingDAO.monthlyReports(startDate, endDate);
        return convertListTo2DArray(bookingList, 9);
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

    public String[][] analyticsRep(java.util.Date startDate, java.util.Date endDate) {
        List<Vehicle> mostBookedCars = reportsDAO.mostBookedCars(startDate, endDate);
        List<Vehicle> leastBookedCars = reportsDAO.leastBookedCars(startDate, endDate);
        List<Owner> maxcommissionOfOwner = reportsDAO.maxcommissionOfOwner(startDate, endDate);
        List<Vehicle> maxProfitByCar = reportsDAO.maxProfitByCar(startDate, endDate);
        String[][] data = new String[1][4];
        data[0][0] = mostBookedCars.get(0).getVehicle_name();
        data[0][1] = leastBookedCars.get(0).getVehicle_name();
        data[0][2] = maxcommissionOfOwner.get(0).getOwner_name();
        data[0][3] = maxProfitByCar.get(0).getVehicle_name();
        return data;
    }

    public String[][] commissionRep(Date startDate, Date endDate) {
        List<Owner> ownerList = reportsDAO.commissionReport(startDate, endDate);
        List<Owner> ownerListCommissionPercent = reportsDAO.commissionPercent(startDate, endDate);
        String[][] data = new String[ownerList.size()][4];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i][0] = ownerList.get(i).getOwner_name();
            data[i][2] = String.valueOf(ownerList.get(i).getOwner_comission());
            data[i][3] = ownerList.get(i).getVehicle_name();
        }
        for (int i = 0; i < ownerListCommissionPercent.size(); i++) {
            data[i][1] = ownerListCommissionPercent.get(i).getOwner_comission() + "%";
        }
        return data;
    }

    public String[] getVehicle() {
        List<Vehicle> vehicleList = bookingDAO.getAllVehicles();
        String[] data = new String[vehicleList.size()];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = vehicleList.get(i).getVehicle_name();
        }
        return data;
    }

    public String[] getownersForDropDown() {
        List<Owner> ownerList = reportsDAO.getAllOwnersForDropDown();
        String[] data = new String[ownerList.size()];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i] = ownerList.get(i).getOwner_name();
        }
        return data;
    }

    public String[][] yearlyRepOwner(Integer date, String name) {
        List<Owner> ownerList = reportsDAO.yearlyRepOwner(date, name);
        String[][] data = new String[ownerList.size()][9];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i][0] = ownerList.get(i).getOwner_name();
            data[i][1] = ownerList.get(i).getVehicle_name();
            data[i][2] = "Rs." + ownerList.get(i).getAmount();
            data[i][3] = ownerList.get(i).getOwner_comission() + "%";
            data[i][4] = "Rs." + ownerList.get(i).getCommission_per_day();
            data[i][5] = String.valueOf(ownerList.get(i).getBooking_date());
            data[i][6] = ownerList.get(i).getComplete_date();
            data[i][7] = "Rs." + ownerList.get(i).getDays();
            data[i][8] = "Rs." + ownerList.get(i).getCommission();
        }
        return data;
    }

    public Integer totalCommssionOfOwner(Integer date, String name) {
        List<Owner> ownerList = reportsDAO.yearlyRepOwner(date, name);
        Integer totalCommission = 0;
        for (int i = 0; i < ownerList.size(); i++) {
            totalCommission += ownerList.get(i).getCommission();
        }
        return totalCommission;
    }

    public String[][] yearlyRepVehicle(Integer date, String name){
        List<Owner> vehicleList = reportsDAO.yearlyRepVehicle(date, name);
        String[][] data = new String[vehicleList.size()][10];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = vehicleList.get(i).getVehicle_name();
            data[i][1] = vehicleList.get(i).getOwner_name();
            data[i][2] = "Rs." + vehicleList.get(i).getAmount();
            data[i][3] = vehicleList.get(i).getOwner_comission() + "%";
            data[i][4] = String.valueOf(vehicleList.get(i).getBooking_date());
            data[i][5] = vehicleList.get(i).getComplete_date();
            data[i][6] = String.valueOf(vehicleList.get(i).getDays());
            data[i][7] = "Rs." + vehicleList.get(i).getTotal_amount();
            data[i][8] = "Rs." + vehicleList.get(i).getCommission();
            data[i][9] = "Rs." + vehicleList.get(i).getProfit();
        }
        return data;
    }

    public Integer totalProfitByVehicle(Integer date, String name) {
        List<Owner> vehicleList = reportsDAO.yearlyRepVehicle(date, name);
        Integer totalProfit = 0;
        for (int i = 0; i < vehicleList.size(); i++) {
            totalProfit += vehicleList.get(i).getProfit();
        }
        return totalProfit;
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
            data[i][6] = String.valueOf(bookingList.get(i).getTotal_days());
            data[i][7] = String.valueOf(bookingList.get(i).getTotal_amount());
            data[i][8] = bookingList.get(i).getStatus();
        }
        return data;
    }
}