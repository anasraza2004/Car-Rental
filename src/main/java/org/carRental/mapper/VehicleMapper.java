package org.carRental.mapper;

import org.carRental.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle> {

    private final String ID = "id";
    private final String VEHICLE_NAME = "vehicle_name";
    private final String VEHICLE_COLOR = "vehicle_color";
    private final String VEHICLE_PRICE = "vehicle_price";
    private final String STATUS = "status";
    private final String OWNER_ID = "owner_id";
    private final String OWNER_NAME = "owner_name";

    @Override
    public List<Vehicle> resultToList(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(resultSet.getInt(ID))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .vehicle_color(resultSet.getString(VEHICLE_COLOR))
                    .vehicle_price(resultSet.getInt(VEHICLE_PRICE))
                    .status(resultSet.getString(STATUS))
                    .owner_id(resultSet.getInt(OWNER_ID))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    @Override
    public Vehicle resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(resultSet.getInt(ID))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .vehicle_color(resultSet.getString(VEHICLE_COLOR))
                    .vehicle_price(resultSet.getInt(VEHICLE_PRICE))
                    .status(resultSet.getString(STATUS))
                    .owner_id(resultSet.getInt(OWNER_ID))
                    .build();
            return vehicle;
        }
        return null;
    }

    public List<Vehicle> resultToListForAvailabilityReport(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .vehicle_color(resultSet.getString(VEHICLE_COLOR))
                    .owner_name(resultSet.getString(OWNER_NAME))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public List<Vehicle> resultToListForMostBookedCars(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }
}