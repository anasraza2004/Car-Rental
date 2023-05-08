package org.carRental.mapper;

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
    private static final String VEHICLE_NAME = "vehicle_name";
    private static final String BOOKING_DATE = "booking_date";
    private static final String COMPLETE_DATE = "complete_date";
    private static final String TOTAL_COMMSION = "commission";
    private static final String COMMSION_PER_DAY = "commission_per_day";
    private static final String DAYS = "days";
    private static final String AMOUNT = "amount";
    private static final String PROFIT = "profit";
    private static final String TOTAL_AMOUNT = "total_amount";

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
//            System.out.println(owner);
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

    public List<Owner> resultToListForMaxOwnerCommission(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .owner_name(resultSet.getString(NAME))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }

    public List<Owner> resultToListForCommissionReport(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .owner_name(resultSet.getString(NAME))
                    .owner_comission(resultSet.getInt(COMISSION))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }

    public List<Owner> commisioinPercentConversion(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .owner_comission(resultSet.getInt(COMISSION))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }

    public List<Owner> yearlyRepOwner(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .owner_name(resultSet.getString(NAME))
                    .owner_comission(resultSet.getInt(COMISSION))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .complete_date(resultSet.getString(COMPLETE_DATE))
                    .commission(resultSet.getInt(TOTAL_COMMSION))
                    .commission_per_day(resultSet.getInt(COMMSION_PER_DAY))
                    .days(resultSet.getInt(DAYS))
                    .amount(resultSet.getInt(AMOUNT))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }

    public List<Owner> yearlyRepVehicle(ResultSet resultSet) throws SQLException {
        List<Owner> ownerList = new ArrayList<>();
        while (resultSet.next()) {
            Owner owner = Owner.builder()
                    .owner_name(resultSet.getString(NAME))
                    .owner_comission(resultSet.getInt(COMISSION))
                    .vehicle_name(resultSet.getString(VEHICLE_NAME))
                    .booking_date(resultSet.getDate(BOOKING_DATE))
                    .complete_date(resultSet.getString(COMPLETE_DATE))
                    .commission(resultSet.getInt(TOTAL_COMMSION))
                    .days(resultSet.getInt(DAYS))
                    .amount(resultSet.getInt(AMOUNT))
                    .profit(resultSet.getInt(PROFIT))
                    .total_amount(resultSet.getInt(TOTAL_AMOUNT))
                    .build();
            ownerList.add(owner);
        }
        return ownerList;
    }
}