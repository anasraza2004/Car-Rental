package org.carRental.dao;

public class BookingSqlQueryConstant {
    public final static String POST = "insert into booking(customer_id,vehicle_id,booking_date,amount) values(?,?,?,?)";
    public final static String GET_ALL = "select * from booking";
    public final static String GET_BY_ID = "select * from booking where id = ?";

}
