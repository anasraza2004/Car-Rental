package org.carRental.dao;

public class CustomerSqlQueryConstant {
    public final static String POST_INTO_CUSTOMER = "insert into customer(customer_name,phone_number,cnic,address,reference_no) values(?,?,?,?,?)";
    public final static String GET_ALL = "select * from customer";
    public final static String GET_BY_ID = "select * from customer where id=?";
    public final static String UPDATE_NAME_BY_ID="update customer set customer_name = ? where id = ?";
    public final static String DELETE = "delete from customer where id = ?";
}
