package org.carRental.dao;

import org.carRental.domain.Booking;
import org.carRental.domain.Owner;
import org.carRental.domain.Vehicle;
import org.carRental.mapper.OwnerMapper;
import org.carRental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carRental.dao.ReportsSqlQueryConstant.*;

public class ReportsDAO extends BaseDAO {
    private final VehicleMapper vehicleMapper = new VehicleMapper();
    private final OwnerMapper ownerMapper = new OwnerMapper();

    public List<Vehicle> getVehicles() {
        try {
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(GET_AVAILABLE_VEHICLES);
            return vehicleMapper.resultToListForAvailabilityReport(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> mostBookedCars(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(MOST_BOOKED_CAR);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return vehicleMapper.resultToListForMostBookedCars(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> leastBookedCars(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(LEAST_BOOKED_CAR);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            statement.setDate(3, new java.sql.Date(startDate.getTime()));
            statement.setDate(4, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return vehicleMapper.resultToListForMostBookedCars(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> maxcommissionOfOwner(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(MAX_COMMISON_OF_THE_MONTH);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.resultToListForMaxOwnerCommission(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vehicle> maxProfitByCar(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(GET_HIGHEST_REVENUE_CAR_OF_THE_MONTH);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return vehicleMapper.resultToListForMostBookedCars(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> commissionReport(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(COMMISSION_REPORT);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.resultToListForCommissionReport(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> commissionPercent(java.util.Date startDate, java.util.Date endDate) {
        try {
            PreparedStatement statement = conn.prepareStatement(COMMISSION_PERCENT);
            statement.setDate(1, new java.sql.Date(startDate.getTime()));
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.commisioinPercentConversion(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> getAllOwnersForDropDown() {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from vehicle_owner where status!= 'Inactive'");
            return ownerMapper.resultToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> yearlyRepOwner(Integer date, String ownerName) {
        try {
            PreparedStatement statement = conn.prepareStatement(YEARLY_REP_OWNER);
            statement.setString(1, date + "-01-01");
            statement.setString(2, date + "-12-31");
            statement.setString(3, ownerName);
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.yearlyRepOwner(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> yearlyRepVehicle(Integer date, String vehicleName){
        try {
            PreparedStatement statement = conn.prepareStatement(YEARLY_REP_VEHICLE);
            statement.setString(1, vehicleName);
            statement.setString(2, date + "-01-01");
            statement.setString(3, date + "-12-31");
            ResultSet resultSet = statement.executeQuery();
            return ownerMapper.yearlyRepVehicle(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
