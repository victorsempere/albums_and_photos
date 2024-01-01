package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import com.fasterxml.jackson.core.type.TypeReference;

public interface MockedDataService {

    public <T> T getMockedDataFromJsonFile(String jsonResourceTestFile, TypeReference<T> typeReference) throws Exception;

}
