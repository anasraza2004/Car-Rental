package org.carRental.dao;

public class BookingSqlQueryConstant {
    public final static String POST = "insert into booking (customer_id,vehicle_id,booking_date,amount) values(?,?,?,?)";
    //            " inner join vehicle v on b.vehicle_id = v.id inner join vehicle_owner o on v.owner_id = o.id " +
    public final static String UPDATE_VEHICLE_STATUS_AFTER_ADD = "UPDATE vehicle SET status = 'Booked' WHERE id = ?";
    public final static String GET_ALL = "select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date, b.amount, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id inner join vehicle v on b.vehicle_id = v.id where b.status in ('Open','Completed')";
    public final static String GET_BY_ID = "select * from booking where id = ?";
    public final static String COMPLETE_BOOKING = "UPDATE booking set status = 'Completed' , complete_date = ? WHERE id = ?";
    public final static String UPDATE_VEHICLE_STATUS_AFTER_COMPLETE = "UPDATE vehicle SET status = 'Active' WHERE id = ?";
    public final static String SOFT_DELETE = "UPDATE booking set status = 'Inactive' WHERE id = ?";
    public final static String UPDATE = "update booking set customer_id = ?, vehicle_id = ?, booking_date=?, amount=?, status=? where id = ?";
    public final static String MONTHLY_REPORT = "select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date," +
            " b.amount * DATEDIFF(complete_date,booking_date) as total_amount, b.amount, DATEDIFF(b.complete_date,b.booking_date) " +
            "as total_days, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id " +
            "inner join vehicle v on b.vehicle_id = v.id where b.complete_date BETWEEN ? AND ? AND b.status='Completed'";
    public final static String GET_COMMISSION = "select Sum(o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)/100)" +
            " as total_commission from booking b inner join vehicle v on v.id=b.vehicle_id inner join " +
            "vehicle_owner o on o.id = v.owner_id where (b.complete_date Between ? And ?)";

}