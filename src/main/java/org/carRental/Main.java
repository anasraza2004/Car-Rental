package org.carRental;

import org.carRental.UI.BasicUI;
import org.carRental.UI.LoginForm;
import org.carRental.dao.CustomerDAO;
import org.carRental.dao.OwnerDao;

public class Main {
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        OwnerDao ownerDao = new OwnerDao();

//                                     Customer post
//        Customer customer = Customer.builder()
//                .customer_name("Stepway")
//                .phone_number("0210000")
//                .cnic("1111")
//                .address("Karachi")
//                .reference_no("1212")
//                .build();
//        customerDAO.post(customer);

//                                        Customer get
//        customerDAO.getAll().forEach(System.out::println);

//                                        Customer getById
//        System.out.println(customerDAO.getById(2));

//                                       Customer delete
//        customerDAO.delete(7);

//                                      Customer update
//        Customer customerForUpdate = customerDAO.getById(2);
//        customerForUpdate.setCustomer_name("Ahsan");
//        customerDAO.update(customerForUpdate, 2);
//  X========================================================X=========================================================X

//                                          Owner post
//        Owner ownerPost = Owner.builder()
//                .owner_name("Ahsan")
//                .owner_cnic(000)
//                .owner_comission(20000)
//                .owner_address("Islamabad")
//                .build();
//        ownerDao.post(ownerPost);

//                                          Owner Get
//        ownerDao.getAll().forEach(System.out::println);

//                                          Owner getById
//        System.out.println(ownerDao.getById(2));

//                                          Owner update
//        Owner ownerUpdate = ownerDao.getById(3);
//        ownerUpdate.setOwner_name("ahsan");
//        ownerDao.update(ownerUpdate, 3);

//                                      Owner delete
//        ownerDao.delete(3);
//        ownerDao.getAll().forEach(System.out::println);
//  X========================================================X=========================================================X
//                                      Swing

//        new BasicUI();
        new LoginForm();
    }
}