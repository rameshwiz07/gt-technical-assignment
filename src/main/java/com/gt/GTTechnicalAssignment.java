package com.gt;


import com.gt.model.Customer;
import com.gt.parser.AddressBookParserImpl;
import com.gt.parser.BookParser;
import com.gt.validator.AddressBookValidator;
import com.gt.validator.AddressBookValidatorImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GTTechnicalAssignment {
    public static void main(String[] args)  {


        log.info("##################################################################");
        BookParser parser = new AddressBookParserImpl();
        List<Customer> customers = parser.parse("AddressBook");
        log.info("Address Book Parsed");
        AddressBookValidator validator = new AddressBookValidatorImpl();
        validator.validate(customers);
        log.info("Address Book validated");


    }
}