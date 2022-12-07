package com.gt.service;

import com.gt.model.Customer;

import java.util.List;

public interface CustomerManagement {
    long genderCounter(String gender, List<Customer> customers);
    String findOldestCustomer(List<Customer> customers);
    String findAgeDifference(Customer customer1, Customer customer2);

}
