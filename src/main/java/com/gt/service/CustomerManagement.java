package com.gt.service;

import com.gt.model.Customer;

import java.util.List;

public interface CustomerManagement {
    long genderCounter(String gender, List<Customer> customers);

}
