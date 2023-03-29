package org.carRental.services;

import org.carRental.dao.VehicleDAO;
import org.carRental.domain.Vehicle;

import java.util.List;

public class VehicleService {
    private final VehicleDAO dao = new VehicleDAO();

    public void add(String name, String color, String price, String ownerId) {
        Vehicle vehicle = Vehicle.builder()
                .vehicle_name(name)
                .vehicle_color((color))
                .vehicle_price(Integer.valueOf(price))
                .owner_id(Integer.valueOf(ownerId))
                .build();
        dao.post(vehicle);
    }

    public String[][] getAll() {
        List<Vehicle> vehicleList = dao.getAll();
        return convertListTo2dArray(vehicleList, 4);
    }

    public String[][] getByName(String name) {
        List<Vehicle> vehicleList = dao.getByName(name);
        return convertListTo2dArray(vehicleList, 4);
    }

    private static String[][] convertListTo2dArray(List<Vehicle> vehicleList, Integer columnSize) {
        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = vehicleList.get(i).getVehicle_name();
            data[i][1] = vehicleList.get(i).getVehicle_color();
            data[i][2] = String.valueOf(vehicleList.get(i).getVehicle_price());
            data[i][3] = String.valueOf(vehicleList.get(i).getOwner_id());
        }
        return data;
    }
}