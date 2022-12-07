package com.gt.parser;

import com.gt.exception.GTException;
import com.gt.model.Customer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class AddressBookParserImpl implements BookParser {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);

    public List<Customer> parse(String fileName) throws GTException {

        try {
            Path addressBookURL = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            String addressBookFileContent = Files.readString(addressBookURL);
            return getAddressBookParameters(addressBookFileContent);

        } catch (IOException | URISyntaxException e) {
            log.error("Unable to read the address book file " + e.getMessage());
            throw new GTException("10","Unable to fetch the file");

        }
    }

    private List<Customer> getAddressBookParameters(String addressBookContent) {

        return addressBookContent
                .lines()
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isEmpty))
                .map(line -> line.split(","))
                .map(data -> {
                    try {
                        if (data.length == 3) {
                            return Customer.builder().name(data[0].trim())
                                    .gender(data[1].trim())
                                    .birthDate(formatter.parse(data[2].trim()))
                                    .build();
                        } else {
                            log.error("Data is invalid in the file" + data);
                            throw new GTException("11","Invalid Data");
                        }

                    } catch (ParseException e) {
                        log.error("Date format is invalid" + data);
                        throw new GTException("12","Invalid Date Format");
                    }
                })
                .collect(Collectors.toList());
    }

}
