package org.carRental.mapper;

import org.carRental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements IMapper<Customer> {

    private static final String ID = "id";
    private static final String NAME = "customer_name";
    private static final String PHONE_NO = "phone_number";
    private static final String CNIC = "cnic";
    private static final String ADDRESS = "address";
    private static final String REF_NO = "reference_no";

    @Override

    public List<Customer> resultToList(ResultSet resultSet) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = Customer.builder()
                    .id(resultSet.getInt(ID))
                    .customer_name(resultSet.getString(NAME))
                    .phone_number(resultSet.getString(PHONE_NO))
                    .cnic(resultSet.getString(CNIC))
                    .address(resultSet.getString(ADDRESS))
                    .reference_no(resultSet.getString(REF_NO))
                    .build();
            customerList.add(customer);
            System.out.println(customer);
        }
        return customerList;
    }

    @Override
    public Customer resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Customer customer = Customer.builder()
                    .id(resultSet.getInt(ID))
                    .customer_name(resultSet.getString(NAME))
                    .phone_number(resultSet.getString(PHONE_NO))
                    .cnic(resultSet.getString(CNIC))
                    .address(resultSet.getString(ADDRESS))
                    .reference_no(resultSet.getString(REF_NO))
                    .build();
            return customer;
        }
        return null;
    }
}
