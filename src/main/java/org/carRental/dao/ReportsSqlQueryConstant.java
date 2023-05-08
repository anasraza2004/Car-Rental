package org.carRental.dao;

public class ReportsSqlQueryConstant {
    protected final static String GET_AVAILABLE_VEHICLES = "select v.vehicle_name, v.vehicle_color, o.owner_name from vehicle v inner join vehicle_owner o on v.owner_id = o.id where v.status='Active'";
    public final static String MOST_BOOKED_CAR = " SELECT v.vehicle_name,v.id FROM booking b INNER JOIN vehicle v ON b.vehicle_id = v.id" +
            "WHERE b.complete_date BETWEEN ? AND ? AND b.status = 'Completed' GROUP BY v.id, v.vehicle_name ORDER BY COUNT(*) DESC LIMIT 1";
    public final static String LEAST_BOOKED_CAR = "SELECT v.vehicle_name, v.id FROM booking b INNER JOIN vehicle v ON b.vehicle_id = v.id" +
            "WHERE b.complete_date BETWEEN ? AND ? AND b.status = 'Completed' GROUP BY v.id, v.vehicle_name HAVING COUNT(*) = (SELECT MIN(cnt)" +
            "FROM (SELECT COUNT(*) AS cnt FROM booking b INNER JOIN vehicle v ON b.vehicle_id = v.id WHERE b.booking_date BETWEEN ? AND ?" +
            " GROUP BY v.id, v.vehicle_name)t)";
    public final static String MAX_COMMISON_OF_THE_MONTH = "SELECT vo.owner_name,(b.amount * (DATEDIFF(b.complete_date, b.booking_date)))" +
            " * (vo.owner_comission / 100) as owner_commission FROM vehicle_owner vo INNER JOIN vehicle v ON vo.id= v.owner_id INNER JOIN" +
            " booking b ON v.id= b.vehicle_id where b.complete_date BETWEEN ? AND ? AND b.status = 'Completed' ORDER BY vo.owner_comission" +
            " DESC LIMIT 1;";
    public final static String GET_HIGHEST_REVENUE_CAR_OF_THE_MONTH = "SELECT v.vehicle_name,(b.amount*datediff(b.complete_date," +
            "b.booking_date))-(((b.amount*datediff(b.complete_date,b.booking_date))*o.owner_comission)/100) as profit FROM booking b" +
            " INNER JOIN vehicle v ON b.vehicle_id = v.id INNER JOIN vehicle_owner o on v.owner_id = o.id WHERE b.complete_date BETWEEN" +
            " ? AND ? AND b.status = 'Completed' ORDER BY b.amount DESC LIMIT 1;";
    public final static String COMMISSION_REPORT = "select o.owner_name, o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)" +
            "*b.amount)/100 as owner_comission, v.vehicle_name from vehicle_owner o inner join vehicle v on o.id = v.owner_id inner join booking b on " +
            "v.id = b.vehicle_id where b.complete_date between ? and ? and b.status = 'Completed'";
    public final static String COMMISSION_PERCENT = "select o.owner_comission from vehicle_owner o inner join vehicle v on o.id = v.owner_id" +
            " inner join booking b on v.id = b.vehicle_id where b.booking_date between ? and ? and b.status = 'Completed'";
    public final static String YEARLY_REP_OWNER = "SELECT o.owner_name, o.owner_comission, v.vehicle_name,b.booking_date, b.complete_date," +
            " (o.owner_comission*b.amount)/100 as commission_per_day, DATEDIFF(b.complete_date , b.booking_date) as days, " +
            "o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)/100 as commission, b.amount FROM  vehicle_owner o" +
            " inner join vehicle v on o.id = v.owner_id inner join booking b on b.vehicle_id = v.id where b.complete_date between ? and ?" +
            " and b.status = 'Completed' and o.owner_name = ?";
    public final static String YEARLY_REP_VEHICLE = "select v.vehicle_name, o.owner_name, b.amount, o.owner_comission, " +
            "b.booking_date, b.complete_date, DATEDIFF(b.complete_date , b.booking_date) as days, b.amount*(datediff(b.complete_date , " +
            "b.booking_date)) as total_amount, o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)/100 as commission," +
            "b.amount*(datediff(b.complete_date , b.booking_date)) - o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)" +
            "/100 as profit from vehicle v inner join vehicle_owner o on v.owner_id=o.id inner join booking b on v.id=b.vehicle_id where " +
            "v.vehicle_name = ? and b.complete_date between ? and ? and b.status = 'Completed'";

}
