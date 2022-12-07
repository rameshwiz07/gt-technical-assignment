package com.gt.service;

import com.gt.model.Customer;
import com.gt.parser.AddressBookParserImpl;
import com.gt.parser.BookParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CustomerManagementImplTest {

    CustomerManagement manager = new CustomerManagementImpl();
    BookParser parser = new AddressBookParserImpl();

    List<Customer> customers = parser.parse("AddressBook");

    @Test
    public void genderCounterTest(){
        Assert.assertEquals(3,manager.genderCounter("female",  customers));
        Assert.assertEquals(2,manager.genderCounter("male",  customers));
    }

    @Test
    public void findOldestCustomerTest(){
        Assert.assertEquals("Wes Jackson",manager.findOldestCustomer( customers));
    }
}
