package org.carRental.dao;

import org.carRental.domain.Vehicle;
import org.carRental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carRental.dao.ReportsSqlQueryConstant.*;

public class ReportsDAO extends BaseDAO {
    private final VehicleMapper vehicleMapper = new VehicleMapper();

    public List<Vehicle> getVehicles() {
        try {
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(GET_AVAILABLE_VEHICLES);
            return vehicleMapper.resultToListForAvailabilityReport(set);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
