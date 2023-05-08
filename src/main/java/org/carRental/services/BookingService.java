package org.carRental.services;

import org.carRental.dao.BookingDAO;
import org.carRental.domain.Booking;
import org.carRental.domain.Customer;
import org.carRental.domain.Vehicle;

import java.util.Date;
import java.util.List;

public class BookingService {
    private final BookingDAO dao = new BookingDAO();

    public void add(Integer customerId, Integer vehicleId, Date bookingDate, Integer amount) {
        Booking booking = Booking.builder()
                .customer_id(customerId)
                .vehicle_id(vehicleId)
                .booking_date(new java.sql.Date(bookingDate.getTime()))
                .amount(amount)
                .build();
        dao.post(booking);
        System.out.println(vehicleId);
        dao.updateVehicleStatusAdd(vehicleId);
    }

    public String[] getVehicle() {
        List<Vehicle> vehicleList = dao.getAllVehicles();
        String[] data = new String[vehicleList.size()];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = String.valueOf(vehicleList.get(i).getId());
            data[i] += "," + vehicleList.get(i).getVehicle_name();
        }
        return data;
    }

    public String[] getCustomer() {
        List<Customer> customerList = dao.getAllCustomer();
        String[] data = new String[customerList.size()];
        for (int i = 0; i < customerList.size(); i++) {
            data[i] = String.valueOf(customerList.get(i).getId());
            data[i] += "," + customerList.get(i).getCustomer_name();
        }
        return data;
    }

    public String[][] getAllBookings() {
        List<Booking> bookingList = dao.getAll();
        return convertListTo2DArray(bookingList, 7);
    }

    public String[][] searchByDate(String date) {
        List<Booking> bookingList = dao.getByDate(date);
        return convertListTo2DArray(bookingList, 7);
    }

    public void completeBooking(String id, Date completeDate, Integer vehicleId) {
        dao.completeBooking(id, completeDate);
        dao.updateVehicleStatusComplete(vehicleId);
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

    public void updateBooking(String id, Integer cusId, Integer vehId, java.util.Date bookingDate, String amount, String status) {
        Booking booking = Booking.builder()
                .customer_id(cusId)
                .vehicle_id(vehId)
                .booking_date(new java.sql.Date(bookingDate.getTime()))
                .amount(Integer.valueOf(amount))
                .status(status)
                .build();
        dao.update(booking, Integer.valueOf(id));
    }
}