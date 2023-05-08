package org.carRental.domain;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Owner {
    private Integer id;
    private String owner_name;
    private Integer owner_cnic;
    private Integer owner_comission;
    private String owner_address;
    private String vehicle_name;
    private Integer commission;
    private Date booking_date;
    private String complete_date;
    private Integer commission_per_day;
    private Integer amount;
    private Integer days;
    private Integer total_amount;
    private Integer profit;
}