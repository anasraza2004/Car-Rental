package org.carRental.domain;

import lombok.*;

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
}