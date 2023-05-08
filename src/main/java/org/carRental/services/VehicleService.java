package org.carRental.services;

import org.carRental.dao.OwnerDao;
import org.carRental.dao.VehicleDAO;
import org.carRental.domain.Owner;
import org.carRental.domain.Vehicle;

import java.util.List;

public class VehicleService {
    private final VehicleDAO dao = new VehicleDAO();
    private final OwnerDao ownerDao = new OwnerDao();

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
        return convertListTo2dArray(vehicleList, 6);
    }

    public String[][] getByName(String name) {
        List<Vehicle> vehicleList = dao.getByName(name);
        return convertListTo2dArray(vehicleList, 6);
    }

    public void update(String id, String name, String color, String price, String status, String ownerID) {
        Vehicle vehicle = Vehicle.builder()
                .vehicle_name(name)
                .vehicle_color(color)
                .vehicle_price(Integer.valueOf(price))
                .status(status)
                .owner_id(Integer.valueOf(ownerID))
                .build();
        dao.update(vehicle, Integer.valueOf(id));
    }

    public String[] getAllOwners() {
        List<Owner> ownerList = ownerDao.getAll();
        String[] owners = new String[ownerList.size()];
        for (int i = 0; i < ownerList.size(); i++) {
            owners[i] = String.valueOf(ownerList.get(i).getId());
            owners[i] += ", " + ownerList.get(i).getOwner_name();
        }
        return owners;
    }

    private static String[][] convertListTo2dArray(List<Vehicle> vehicleList, Integer columnSize) {
        String[][] data = new String[vehicleList.size()][columnSize];
        for (int i = 0; i < vehicleList.size(); i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getVehicle_name();
            data[i][2] = vehicleList.get(i).getVehicle_color();
            data[i][3] = String.valueOf(vehicleList.get(i).getVehicle_price());
            data[i][4] = String.valueOf(vehicleList.get(i).getStatus());
            data[i][5] = String.valueOf(vehicleList.get(i).getOwner_id());
        }
        return data;
    }
}