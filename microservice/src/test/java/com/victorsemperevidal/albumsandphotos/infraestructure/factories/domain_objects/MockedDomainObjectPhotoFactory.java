package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.RandomTextService;

@Service
public class MockedDomainObjectPhotoFactory {

    private RandomTextService randomTextService;
    private MockedDataService mockedDataService;

    @Autowired
    public MockedDomainObjectPhotoFactory(RandomTextService randomTextService, MockedDataService mockedDataService) {
        super();
        this.randomTextService = randomTextService;
        this.mockedDataService = mockedDataService;

    }

    public List<Photo> getMockedPhotos(int albums, int photosPerAlbums) {
        List<Photo> listOfPhotos = new ArrayList<>();
        for (int currentAlbum = 0; currentAlbum < albums; currentAlbum++) {
            listOfPhotos.addAll(getAlbumMockedPhotos(currentAlbum + 1, photosPerAlbums));
        }
        return listOfPhotos;
    }

    public List<Photo> getMockedPhotosFromJsonFile(String resourceTestFile) {
        try {
            return mockedDataService.getMockedDataFromJsonFile(resourceTestFile, new TypeReference<List<Photo>>() {
            });

        } catch (Exception e) {
            return List.of(getInstance(e));
        }
    }

    private List<Photo> getAlbumMockedPhotos(int albumId, int photosPerAlbum) {
        List<Photo> listOfPhotos = new ArrayList<>();
        int photoIdOffset = (albumId - 1) * photosPerAlbum;
        for (int currentPhoto = 0; currentPhoto < photosPerAlbum; currentPhoto++) {
            listOfPhotos.add(getInstance(albumId, photoIdOffset + currentPhoto + 1));
        }
        return listOfPhotos;
    }

    public Photo getInstance(Exception e) {
        return new Photo(-1l, -1l, MessageFormat.format("No se han cargado los mocks: {0}", e.getMessage()), "Sin url",
                "Sin thumbnail url");
    }

    private Photo getInstance(int albumId, int photoId) {
        return new Photo(Integer.valueOf(albumId).longValue(), Integer.valueOf(photoId).longValue(),
                randomTextService.getRandomText("title"), randomTextService.getRandomText("url"),
                randomTextService.getRandomText("thumbnailUrl"));
    }

}
