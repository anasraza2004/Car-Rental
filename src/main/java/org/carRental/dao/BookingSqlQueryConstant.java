package org.carRental.dao;

public class BookingSqlQueryConstant {
    public final static String POST = "insert into booking (customer_id,vehicle_id,booking_date,amount) values(?,?,?,?)";
    //            " inner join vehicle v on b.vehicle_id = v.id inner join vehicle_owner o on v.owner_id = o.id " +
//            "UPDATE vehicle SET status = 'Booked' WHERE id = ?";
    public final static String GET_ALL = "select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date, b.amount, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id inner join vehicle v on b.vehicle_id = v.id where b.status in ('Open','Completed')";
    public final static String GET_BY_ID = "select * from booking where id = ?";
    public final static String COMPLETE_BOOKING = "UPDATE booking set status = 'Completed' , complete_date = ? WHERE id = ?";
    public final static String SOFT_DELETE = "UPDATE booking set status = 'Inactive' WHERE id = ?";
    public final static String UPDATE = "update booking set customer_id = ?, vehicle_id = ?, booking_date=?, amount=?, status=? where id = ?";
//    public final static String MONTHLY_REPORT = "SELECT id, customer_id, vehicle_id, booking_date, complete_date," +
//            "amount * DATEDIFF(complete_date,booking_date) as amount, status" +
//            " FROM booking WHERE booking_date BETWEEN ? AND ?";
    public final static String MONTHLY_REPORT = "select b.id, b.customer_id, b.vehicle_id, b.booking_date, b.complete_date, b.amount * DATEDIFF(complete_date,booking_date) as amount, b.status, c.customer_name, v.vehicle_name from booking b inner join customer c on b.customer_id = c.id inner join vehicle v on b.vehicle_id = v.id where b.booking_date BETWEEN ? AND ?";
    public final static String GET_COMMISSION = "select Sum(o.owner_comission*(DATEDIFF(b.complete_date , b.booking_date)*b.amount)/100)" +
            " as total_commission from booking b inner join vehicle v on v.id=b.vehicle_id inner join " +
            "vehicle_owner o on o.id = v.owner_id where (b.booking_date Between ? And ?)";

}