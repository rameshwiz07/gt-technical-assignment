package com.gt.service;

import com.gt.exception.GTException;
import com.gt.model.Customer;
import com.gt.utils.DateConvertor;
import lombok.extern.slf4j.Slf4j;

import java.time.Period;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class CustomerManagementImpl implements CustomerManagement {

    @Override
    public long genderCounter(String gender, List<Customer> customers) {
        return customers.stream()
                .filter(c -> c.getGender().equalsIgnoreCase(gender))
                .count();
    }

    @Override
    public String findOldestCustomer(List<Customer> customers) {
        return customers.stream()
                .min(Comparator.comparing(Customer::getBirthDate))
                .map(Customer::getName)
                .orElse("");
    }

    @Override
    public String findAgeDifference(Customer customer1, Customer customer2) {
        if (customer1.getBirthDate().equals(customer2.getBirthDate())) {
            return customer1.getName() + " & " + customer2 + " have same date of birth";
        } else if (customer1.getBirthDate().after(customer2.getBirthDate())) {
            Period p = Period.between(DateConvertor.convertToLocalDateTimeViaInstant(customer1.getBirthDate()),
                    DateConvertor.convertToLocalDateTimeViaInstant(customer2.getBirthDate()));
            return customer2.getName() + " is older than " + customer1.getName() + " by "
                    + p.getYears() + " Years, "
                    + p.getMonths() + " Months, "
                    + p.getDays() + " Days";

        } else if (customer1.getBirthDate().before(customer2.getBirthDate())) {
            Period p = Period.between(DateConvertor.convertToLocalDateTimeViaInstant(customer1.getBirthDate()),
                    DateConvertor.convertToLocalDateTimeViaInstant(customer2.getBirthDate()));
            return customer1.getName() + " is older than " + customer2.getName() + " by "
                    + p.getYears() + " Years, "
                    + p.getMonths() + " Months, "
                    + p.getDays() + " Days";
        } else {
            log.error("Age Difference - Invalid dates" + customer1.getBirthDate() + customer2.getBirthDate());
            throw new GTException("13", "Age Difference - Invalid dates");

        }
    }
}
