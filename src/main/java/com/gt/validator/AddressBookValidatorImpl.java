package com.gt.validator;

import com.gt.exception.GTException;
import com.gt.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class AddressBookValidatorImpl implements AddressBookValidator {

    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
    private static final String DATE_PATTERN = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{2}$";


    @Override
    public void validate(List<Customer> customers) {
        final List<CustomerParameter> addressParameters = getAddressBookValidatorParameters(customers);
        validateParamsOnEmpty(addressParameters);
        validateParamsByPattern(addressParameters);
        validateParamsByLength(addressParameters);
    }

    private static void validateParamsOnEmpty(final List<CustomerParameter> parameters) {
        parameters.stream()
                .filter(CustomerParameter::isInvalidIfEmpty)
                .filter(addressParam -> isEmpty(addressParam.getParamValue()))
                .forEach(addressParam -> {throw new GTException("15","Value is Empty");});
    }

    private static void validateParamsByPattern(final List<CustomerParameter> parameters) {
        parameters.stream()
                .filter(addressParam -> isNotEmpty(addressParam.getParamValue()) && isNotEmpty(addressParam.getPatternForParam()))
                .filter(addressParam -> !Pattern.compile(addressParam.getPatternForParam()).matcher(addressParam.getParamValue()).matches())
                .forEach(addressParam -> {throw new GTException("16","Pattern Mismatch");});
    }

    private static void validateParamsByLength(final List<CustomerParameter> parameters) {
        parameters.stream()
                .filter(addressParam -> isNotEmpty(addressParam.getParamValue()))
                .filter(addressParam -> addressParam.getParamValue().length() > addressParam.getMaxParamLength())
                .forEach(addressParam -> {throw new GTException("17","Length of the data is more");});
    }


    private List<CustomerParameter> getAddressBookValidatorParameters(List<Customer> customers) {
        return
                customers
                        .stream()
                        .map(customer ->
                                Arrays.asList(CustomerParameter.builder().paramName("Name").paramValue(customer.getName()).maxParamLength(50).invalidIfEmpty(true).build(),
                                        CustomerParameter.builder().paramName("Gender").paramValue(customer.getGender()).maxParamLength(10).invalidIfEmpty(true).build(),
                                        CustomerParameter.builder().paramName("DateOfBirth").paramValue(formatter.format(customer.getBirthDate())).maxParamLength(8).patternForParam(DATE_PATTERN).invalidIfEmpty(true).build()))
                        .flatMap(List::stream).
                        collect(Collectors.toList());


    }

    @Builder
    @AllArgsConstructor
    @Data
    private static class CustomerParameter {
        private final String paramName;
        private final String paramValue;
        private String patternForParam;
        private final int maxParamLength;
        private boolean invalidIfEmpty;

    }



}
