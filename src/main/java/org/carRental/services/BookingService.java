package org.carRental.services;

import org.carRental.dao.BookingDAO;
import org.carRental.domain.Booking;

import java.util.List;

public class BookingService {
    private final BookingDAO dao = new BookingDAO();

    public void add(String customerId, String vehicleId, String bookingDate, String amount) {
        Booking booking = Booking.builder()
                .customer_id(Integer.valueOf(customerId))
                .vehicle_id(Integer.valueOf(vehicleId))
                .booking_date(bookingDate)
                .amount(Integer.valueOf(amount))
                .build();
        dao.post(booking);
    }

    public String[][] getAllBookngs() {
        List<Booking> bookingList = dao.getAll();
        return convertListTo2DArray(bookingList, 4);
    }

    public String[][] searchByDate(String date) {
        List<Booking> bookingList = dao.getByDate(date);
        return convertListTo2DArray(bookingList, 4);
    }

    private String[][] convertListTo2DArray(List<Booking> bookingList, Integer columnSize) {
        String[][] data = new String[bookingList.size()][columnSize];
        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getCustomer_id());
            data[i][1] = String.valueOf(bookingList.get(i).getVehicle_id());
            data[i][2] = String.valueOf(bookingList.get(i).getBooking_date());
            data[i][3] = String.valueOf(bookingList.get(i).getAmount());
        }
        return data;
    }
}
