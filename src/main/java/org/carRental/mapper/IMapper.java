package org.carRental.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface IMapper<T> {
    List<T> resultToList(ResultSet resultSet) throws SQLException;

    T resultToObject(ResultSet resultSet) throws SQLException;

}
