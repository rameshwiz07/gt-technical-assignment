package com.gt.parser;

import com.gt.exception.GTException;
import com.gt.model.Customer;

import java.util.List;

public interface BookParser {
    List<Customer> parse(String fileName) throws GTException;
}
