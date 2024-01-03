package com.victorsemperevidal.albumsandfotos.infraestructure.factories.external_data;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.RandomTextService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;

@Service
public class MockedExternalDataAlbumFactory {

    private RandomTextService randomTextService;
    private MockedDataService mockedDataService;

    @Autowired
    public MockedExternalDataAlbumFactory(RandomTextService randomTextService, MockedDataService mockedDataService) {
        super();
        this.randomTextService = randomTextService;
        this.mockedDataService = mockedDataService;
    }

    public List<Album> getMockedAlbums(int albums) {
        List<Album> listOfAlbums = new ArrayList<>(albums);
        for (int currentAlbum = 0; currentAlbum < albums; currentAlbum++) {
            listOfAlbums.add(getInstance(currentAlbum + 1));
        }
        return listOfAlbums;
    }

    public List<Album> getMockedAlbumsFromJsonFile(String resourceTestFile) {
        try {
            return mockedDataService.getMockedDataFromJsonFile(resourceTestFile, new TypeReference<List<Album>>() {
            });

        } catch (Exception e) {
            return List.of(getInstance(e));
        }
    }

    private Album getInstance(Exception e) {
        Album errorAlbum = new Album();
        errorAlbum.setId(-1l);
        errorAlbum.setTitle("No se han cargado los mocks: " + e.getMessage());
        errorAlbum.setUserId(-1l);
        return errorAlbum;
    }

    private Album getInstance(int albumId) {
        RandomGenerator rg = RandomGenerator.getDefault();
        Album album = new Album();
        album.setId(Integer.valueOf(albumId).longValue());
        album.setUserId(rg.nextLong(1, Long.MAX_VALUE));
        album.setTitle(randomTextService.getRandomText("title"));
        return album;
    }

}
