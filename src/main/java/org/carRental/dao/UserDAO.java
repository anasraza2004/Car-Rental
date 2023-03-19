package org.carRental.dao;

import org.carRental.domain.User;
import org.carRental.mapper.OwnerMapper;
import org.carRental.mapper.UserMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDAO extends BaseDAO {
    UserMapper userMapper = new UserMapper();
    private final static String GET_BY_USERNAME_AND_PASSWORD = "select * from user where username = ? and passcode = ?";

    public User getUserByUserNameAndPassword(String username, String passcode) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_BY_USERNAME_AND_PASSWORD);
            ps.setString(1, username);
            ps.setString(2, passcode);
            ResultSet rs = ps.executeQuery();
            return userMapper.resultToObject(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
