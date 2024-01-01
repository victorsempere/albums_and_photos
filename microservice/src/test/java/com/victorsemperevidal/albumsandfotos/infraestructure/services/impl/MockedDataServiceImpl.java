package com.victorsemperevidal.albumsandfotos.infraestructure.services.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;

@Service
public class MockedDataServiceImpl implements MockedDataService {

    protected ObjectMapper objectMapper;

    @Autowired
    public MockedDataServiceImpl(ObjectMapper objectMapper) {
        super();
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T getMockedDataFromJsonFile(String jsonResourceTestFile, TypeReference<T> typeReference)
            throws Exception {
        InputStream jsonFileInputStream = getClass().getClassLoader().getResourceAsStream(
                jsonResourceTestFile);
        return objectMapper.readValue(
                jsonFileInputStream, typeReference);
    }
}
