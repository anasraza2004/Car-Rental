package org.carRental.dao;

import org.carRental.domain.Booking;
import org.carRental.mapper.BookingMapper;

import java.sql.*;
import java.util.List;

import static org.carRental.dao.BookingSqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking> {
    private final BookingMapper mapper = new BookingMapper();

    @Override
    public void post(Booking obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(POST);
            ps.setInt(1, obj.getCustomer_id());
            ps.setInt(2, obj.getVehicle_id());
            ps.setString(3, obj.getBooking_date());
            ps.setInt(4, obj.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL);
            return mapper.resultToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking getById(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            return mapper.resultToObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getByDate(String date) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from booking where booking_date like '%" + date + "%'");
            ResultSet resultSet = ps.executeQuery();
            return mapper.resultToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking obj, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
