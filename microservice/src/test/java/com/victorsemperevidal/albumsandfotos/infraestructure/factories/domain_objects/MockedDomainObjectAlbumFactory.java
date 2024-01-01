package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.RandomTextService;

@Service
public class MockedDomainObjectAlbumFactory {

    private RandomTextService randomTextService;
    private MockedDataService mockedDataService;

    @Autowired
    public MockedDomainObjectAlbumFactory(RandomTextService randomTextService, MockedDataService mockedDataService) {
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

    public Album getInstance(Exception e) {
        return new Album(-1l, -1l, MessageFormat.format("No se han cargado los mocks: {0}", e.getMessage()));
    }

    private Album getInstance(int albumId) {
        RandomGenerator rg = RandomGenerator.getDefault();
        return new Album(rg.nextLong(1, Long.MAX_VALUE), Integer.valueOf(albumId).longValue(),
                randomTextService.getRandomText("title"));
    }

}
