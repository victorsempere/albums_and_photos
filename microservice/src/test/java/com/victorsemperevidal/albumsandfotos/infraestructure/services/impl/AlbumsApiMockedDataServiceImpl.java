package com.victorsemperevidal.albumsandfotos.infraestructure.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.external_data.ExternalDataAlbumFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.AlbumsApiMockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;

@Service
public class AlbumsApiMockedDataServiceImpl implements AlbumsApiMockedDataService {

    private ExternalDataAlbumFactory externalDataAlbumFactory;
    private MockedDataService mockedDataService;

    @Autowired
    public AlbumsApiMockedDataServiceImpl(MockedDataService mockedDataService,
            ExternalDataAlbumFactory externalDataAlbumFactory) {
        super();
        this.mockedDataService = mockedDataService;
        this.externalDataAlbumFactory = externalDataAlbumFactory;
    }

    @Override
    public List<Album> getMockedAlbums(int albums) {
        List<Album> listOfAlbums = new ArrayList<>(albums);
        for (int currentAlbum = 0; currentAlbum < albums; currentAlbum++) {
            listOfAlbums.add(externalDataAlbumFactory.getInstance(currentAlbum + 1));
        }
        return listOfAlbums;
    }

    @Override
    public List<Album> getMockedAlbumsFromJsonFile(String resourceTestFile) {
        try {
            return mockedDataService.getMockedDataFromJsonFile(resourceTestFile, new TypeReference<List<Album>>() {
            });

        } catch (Exception e) {
            return List.of(externalDataAlbumFactory.getInstance(e));
        }
    }
}
