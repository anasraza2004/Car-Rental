package org.carRental.mapper;

import org.carRental.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMapper implements IMapper<User> {

    private final static String ID = "id";
    private final static String USERNAME = "username";
    private final static String PASSCODE = "passcode";

    @Override
    public List<User> resultToList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public User resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return User.builder()
                    .id(resultSet.getInt(ID))
                    .username(resultSet.getString(USERNAME))
                    .passcode(resultSet.getString(PASSCODE))
                    .build();
        }
        return null;
    }
}
