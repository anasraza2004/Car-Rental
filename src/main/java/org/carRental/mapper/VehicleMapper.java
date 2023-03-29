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
    private final String OWNER_ID = "owner_id";

    @Override
    public List<Vehicle> resultToList(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while (resultSet.next()) {
            Vehicle vehicle = Vehicle.builder()
                    .id(resultSet.getInt(ID))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .vehicle_color(resultSet.getString(VEHICLE_COLOR))
                    .vehicle_price(resultSet.getInt(VEHICLE_PRICE))
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
                    .owner_id(resultSet.getInt(OWNER_ID))
                    .build();
            return vehicle;
        }
        return null;
    }
}