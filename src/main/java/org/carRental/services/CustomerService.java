package org.carRental.services;

import org.carRental.dao.CustomerDAO;
import org.carRental.domain.Customer;

import java.util.List;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();


    public void add(String name, String phone, String cnic, String address, String refNo) {
        Customer customer = Customer.builder()
                .customer_name(name)
                .phone_number(phone)
                .cnic(cnic)
                .address(address)
                .reference_no(refNo)
                .build();
        dao.post(customer);
    }


    public String[][] getAllCustomer() {
        List<Customer> customerList = dao.getAll();
        return convertListTo2DArray(customerList, 7);
    }

    public String[][] searchByName(String name) {
        List<Customer> customerList = dao.getByName(name);
        return convertListTo2DArray(customerList, 7);
    }

    public void updateCustomer(String id, String name, String phoneNo, String cnic, String address, String refNo, String status) {
        Customer customer = Customer.builder()
                .customer_name(name)
                .phone_number(phoneNo)
                .cnic(cnic)
                .address(address)
                .reference_no(refNo)
                .status(status)
                .build();

        dao.update(customer, Integer.valueOf(id));
    }

    public void delete(String id) {
        dao.delete(Integer.valueOf(id));
    }

    private String[][] convertListTo2DArray(List<Customer> customerList, Integer columnSize) {
        String[][] data = new String[customerList.size()][columnSize];
        for (int i = 0; i < customerList.size(); i++) {
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getCustomer_name();
            data[i][2] = customerList.get(i).getPhone_number();
            data[i][3] = customerList.get(i).getCnic();
            data[i][4] = customerList.get(i).getAddress();
            data[i][5] = customerList.get(i).getReference_no();
            data[i][6] = customerList.get(i).getStatus();
        }
        return data;
    }

}
