package org.carRental.dao;

public class VehicleSqlQueryConstant {
    public final static String GET_ALL = "select * from vehicle";
    public final static String POST = "insert into vehicle (vehicle_name, vehicle_color, vehicle_price, owner_id) values(?,?,?,?)";
    public final static String GET_BY_ID = "select * from vehicle where id = ?";
    public final static String UPDATE_BY_ID = "update vehicle set vehicle_name = ? where id = ?";
    public final static String DELETE = "delete from vehicle where id = ?";
    public final static String SOFT_DELETE = "update vehicle set status = 'Inactive' where id = ?";
}