package com.example.teachersservice.DATA.Converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayListConverter<T> implements AttributeConverter<ArrayList<T>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(ArrayListConverter.class);

    @Override
    public String convertToDatabaseColumn(ArrayList<T> list) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(list);
        } catch (final JsonProcessingException e) {
            logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public ArrayList<T> convertToEntityAttribute(String jsonList) {

        ArrayList<T> customerInfo = new ArrayList<>();
        ArrayList<T> temp;
        try {
            temp = objectMapper.readValue(jsonList, ArrayList.class);
            if (temp != null) {
                customerInfo.addAll(temp);
            }
        } catch (final IOException e) {
            logger.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
