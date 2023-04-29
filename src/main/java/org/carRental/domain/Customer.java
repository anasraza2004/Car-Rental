package org.carRental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {
    private Integer id;
    private String customer_name;
    private String phone_number;
    private String cnic;
    private String address;
    private String reference_no;
    private String status;
}