package org.carRental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Vehicle {
    private Integer id;
    private String vehicle_name;
    private String vehicle_color;
    private Integer vehicle_price;
    private Integer owner_id;
}