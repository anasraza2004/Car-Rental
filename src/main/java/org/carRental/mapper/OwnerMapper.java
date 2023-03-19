package org.carRental.mapper;

import org.carRental.domain.Customer;
import org.carRental.domain.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerMapper implements IMapper<Owner> {

    private static final String ID = "id";
    private static final String NAME = "owner_name";
    private static final String CNIC = "owner_cnic";
    private static final String COMISSION = "owner_comission";
    private static final String ADDRESS = "owner_address";

    @Override
    public List<Owner> resultToList(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .id(resultSet.getInt(ID))
                    .owner_name(resultSet.getString(NAME))
                    .owner_cnic(resultSet.getInt(CNIC))
                    .owner_comission(resultSet.getInt(COMISSION))
                    .owner_address(resultSet.getString(ADDRESS))
                    .build();
            ownerList.add(owner);
            System.out.println(owner);
        }
        return ownerList;
    }

    @Override
    public Owner resultToObject(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Owner owner = Owner.builder()
                    .id(resultSet.getInt(ID))
                    .owner_name(resultSet.getString(NAME))
                    .owner_cnic(resultSet.getInt(CNIC))
                    .owner_comission(resultSet.getInt(COMISSION))
                    .owner_address(resultSet.getString(ADDRESS))
                    .build();
            return owner;
        }
        return null;
    }
}
