package org.carRental.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Booking {
    private Integer id;
    private Integer customer_id;
    private Integer vehicle_id;
    private String booking_date;
    private Integer amount;
}
