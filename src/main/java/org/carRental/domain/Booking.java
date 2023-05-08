package org.carRental.domain;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Booking {
    private Integer id;
    private Integer customer_id;
    private Integer vehicle_id;
    private Date booking_date;
    private String complete_date;
    private Integer amount;
    private String status;
    private Integer commission;
    private String customer_name;
    private String vehicle_name;
    private Integer total_days;
    private Integer total_amount;

}