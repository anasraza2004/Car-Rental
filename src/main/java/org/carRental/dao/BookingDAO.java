package org.carRental.dao;

import org.carRental.domain.Booking;
import org.carRental.domain.Customer;
import org.carRental.domain.Vehicle;
import org.carRental.mapper.BookingMapper;
import org.carRental.mapper.CustomerMapper;
import org.carRental.mapper.VehicleMapper;

import java.sql.*;
import java.util.List;

import static org.carRental.dao.BookingSqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking> {
    private final BookingMapper mapper = new BookingMapper();
    private final VehicleMapper vehicleMapper = new VehicleMapper();
    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void post(Booking obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(POST);
            ps.setInt(1, obj.getCustomer_id());
            ps.setInt(2, obj.getVehicle_id());
            ps.setDate(3, obj.getBooking_date());
            ps.setInt(4, obj.getAmount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void updateVehicleStatus(Integer vehicleId) {
//        PreparedStatement statement= conn.prepareStatement();
//    }

    public List<Vehicle> getAllVehicles() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle where status= 'Active'");
            return vehicleMapper.resultToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomer() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from customer where status ='Active'");
            return customerMapper.resultToList(resultSet);
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

    public void completeBooking(String id, java.util.Date date) {
        try {
            PreparedStatement statement = conn.prepareStatement(COMPLETE_BOOKING);
            statement.setDate(1, new java.sql.Date(date.getTime()));
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(String id) {
        try {
            PreparedStatement statement = conn.prepareStatement(SOFT_DELETE);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getByDate(String date) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from booking where booking_date like '%" + date + "%' and status != 'Inactive'");
            ResultSet resultSet = ps.executeQuery();
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

    @Override
    public void update(Booking obj, Integer id) {
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setInt(1, obj.getCustomer_id());
            statement.setInt(2, obj.getVehicle_id());
            statement.setDate(3, obj.getBooking_date());
            statement.setInt(4, obj.getAmount());
            statement.setString(5, obj.getStatus());
            statement.setInt(6, id);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> monthlyReports(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(MONTHLY_REPORT);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return mapper.resultToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> totalCommission(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(GET_COMMISSION);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return mapper.ResultSetToListOfCommission(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override

    public void delete(Integer id) {

    }
}
