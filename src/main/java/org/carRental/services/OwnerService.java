package org.carRental.services;

import org.carRental.dao.OwnerDao;
import org.carRental.domain.Owner;

import java.util.List;

public class OwnerService {

    OwnerDao dao = new OwnerDao();

    public void add(String name, String cnic, String commission, String address) {
        Owner owner = Owner.builder()
                .owner_name(name)
                .owner_cnic(Integer.valueOf(cnic))
                .owner_comission(Integer.valueOf(commission))
                .owner_address(address)
                .build();
        dao.post(owner);
    }

    public String[][] getAll() {
        List<Owner> ownerList = dao.getAll();
        return convertListTo2dArray(ownerList, 4);
    }

    public String[][] getByName(String name) {
        List<Owner> ownerList = dao.getByName(name);
        return convertListTo2dArray(ownerList, 4);
    }

    private static String[][] convertListTo2dArray(List<Owner> ownerList, Integer columnSize) {
        String[][] data = new String[ownerList.size()][columnSize];
        for (int i = 0; i < ownerList.size(); i++) {
            data[i][0] = ownerList.get(i).getOwner_name();
            data[i][1] = String.valueOf(ownerList.get(i).getOwner_cnic());
            data[i][2] = String.valueOf(ownerList.get(i).getOwner_comission());
            data[i][3] = ownerList.get(i).getOwner_address();
        }
        return data;
    }
}