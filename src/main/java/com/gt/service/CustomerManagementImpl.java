package com.gt.service;

import com.gt.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CustomerManagementImpl implements CustomerManagement {

    @Override
    public long genderCounter(String gender, List<Customer> customers) {
        return customers.stream()
                .filter(c -> c.getGender().equalsIgnoreCase(gender))
                .count();
    }
}
