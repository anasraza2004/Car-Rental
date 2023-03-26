package org.carRental.mapper;

import org.carRental.domain.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking> {

    private static final String ID = "id";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String VEHICLE_ID = "vehicle_id";
    private static final String BOOKING_DATE = "booking_date";
    private static final String AMOUNT = "amount";

    @Override
    public List<Booking> resultToList(ResultSet resultSet) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .customer_id(resultSet.getInt(CUSTOMER_ID))
                    .vehicle_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getString(BOOKING_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .build();
            bookingList.add(booking);
            System.out.println(booking);
        }
        return bookingList;
    }

    @Override
    public Booking resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .customer_id(resultSet.getInt(CUSTOMER_ID))
                    .vehicle_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getString(BOOKING_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .build();
            return booking;
        }
        return null;
    }
}
