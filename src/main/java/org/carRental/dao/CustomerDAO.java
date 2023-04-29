package org.carRental.dao;

import org.carRental.domain.Customer;
import org.carRental.mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carRental.dao.CustomerSqlQueryConstant.*;

public class CustomerDAO extends BaseDAO implements ICrud<Customer> {
    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public void post(Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(POST_INTO_CUSTOMER);
            ps.setString(1, obj.getCustomer_name());
            ps.setString(2, obj.getPhone_number());
            ps.setString(3, obj.getCnic());
            ps.setString(4, obj.getAddress());
            ps.setString(5, obj.getReference_no());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        if (true) {
            try {
                Statement statement = conn.createStatement();
                ResultSet rs = statement.executeQuery(GET_ALL);
                return customerMapper.resultToList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Customer getById(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_ID);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();
            return customerMapper.resultToObject(res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from customer where customer_name like '%" + name + "%'");
            ResultSet res = ps.executeQuery();
            return customerMapper.resultToList(res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Customer obj, Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_BY_ID);
            ps.setString(1, obj.getCustomer_name());
            ps.setString(2, obj.getPhone_number());
            ps.setString(3, obj.getCnic());
            ps.setString(4, obj.getAddress());
            ps.setString(5, obj.getReference_no());
            ps.setString(6, obj.getStatus());
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(SOFT_DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
