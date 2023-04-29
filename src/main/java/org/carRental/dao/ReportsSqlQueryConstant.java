package org.carRental.dao;

public class ReportsSqlQueryConstant {
    protected final static String GET_AVAILABLE_VEHICLES = "select v.vehicle_name, v.vehicle_color, o.owner_name from vehicle v inner join vehicle_owner o on v.owner_id = o.id where v.status='Active'";
}
