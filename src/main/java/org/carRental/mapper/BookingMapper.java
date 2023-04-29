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
    private static final String COMPLETE_DATE = "complete_date";
    private static final String AMOUNT = "amount";
    private static final String STATUS = "status";
    private static final String COMMISSION = "total_commission";
    private static final String CUSTOMER_NAME = "customer_name";
    private static final String VEHICLE_NAME = "vehicle_name";

    @Override
    public List<Booking> resultToList(ResultSet resultSet) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .customer_id(resultSet.getInt(CUSTOMER_ID))
                    .vehicle_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .complete_date(resultSet.getString(COMPLETE_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .status(resultSet.getString(STATUS))
                    .customer_name(resultSet.getString(CUSTOMER_NAME))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public List<Booking> ResultSetToListOfCommission(ResultSet rs) throws SQLException {
        List<Booking> commissionList = new ArrayList<>();
        while (rs.next()) {
            Booking booking = Booking.builder()
                    .commission(rs.getInt(COMMISSION))
                    .build();
            commissionList.add(booking);
        }
        return commissionList;
    }

    @Override
    public Booking resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Booking booking = Booking.builder()
                    .id(resultSet.getInt(ID))
                    .customer_id(resultSet.getInt(CUSTOMER_ID))
                    .vehicle_id(resultSet.getInt(VEHICLE_ID))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .complete_date(resultSet.getString(COMPLETE_DATE))
                    .amount(resultSet.getInt(AMOUNT))
                    .status(resultSet.getString(STATUS))
                    .customer_name(resultSet.getString(CUSTOMER_NAME))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            return booking;
        }
        return null;
    }
}