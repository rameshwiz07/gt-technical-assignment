package com.gt.validator;

import com.gt.model.Customer;

import java.util.List;

public interface AddressBookValidator {
    void validate(List<Customer> customers);
}
