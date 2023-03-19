package org.carRental.dao;

public class OwnerSqlQueryConstant {
    public final static String POST = "insert into vehicle_owner (owner_name,owner_cnic,owner_comission,owner_address) values(?,?,?,?)";
    public final static String GET_ALL_FROM_OWNER = "select * from vehicle_owner";
    public final static String GET_BY_ID = "select * from vehicle_owner where id = ?";
    public final static String UPDATE_BY_ID = "update vehicle_owner set owner_name = ? where id = ?";
    public final static String DELETE = "delete from vehicle_owner where id = ?";
}
