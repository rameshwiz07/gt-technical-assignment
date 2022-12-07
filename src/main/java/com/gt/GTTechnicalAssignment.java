package com.gt;


import com.gt.model.Customer;
import com.gt.parser.AddressBookParserImpl;
import com.gt.parser.BookParser;
import com.gt.service.CustomerManagement;
import com.gt.service.CustomerManagementImpl;
import com.gt.validator.AddressBookValidator;
import com.gt.validator.AddressBookValidatorImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GTTechnicalAssignment {


    public static void main(String[] args)  {

        String decorator = "##################################################################";
        log.info(decorator);
        BookParser parser = new AddressBookParserImpl();
        List<Customer> customers = parser.parse("AddressBook");
        log.info("Address Book Parsed");
        AddressBookValidator validator = new AddressBookValidatorImpl();
        validator.validate(customers);
        log.info("Address Book validated");
        log.info(decorator);
        CustomerManagement manager = new CustomerManagementImpl();
        log.info("Count of Male Customer: " + manager.genderCounter("male", customers));
        log.info("Oldest Customer: " + manager.findOldestCustomer(customers));
        log.info("Age Difference:  " + manager.findAgeDifference(customers.get(0),customers.get(1)));
        log.info(decorator);


    }
}