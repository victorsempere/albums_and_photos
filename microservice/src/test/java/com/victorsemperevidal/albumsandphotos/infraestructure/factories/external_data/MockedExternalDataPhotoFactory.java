package com.victorsemperevidal.albumsandphotos.infraestructure.factories.external_data;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.RandomTextService;

@Service
public class MockedExternalDataPhotoFactory {

    private RandomTextService randomTextService;
    private MockedDataService mockedDataService;

    @Autowired
    public MockedExternalDataPhotoFactory(RandomTextService randomTextService, MockedDataService mockedDataService) {
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

    private Photo getInstance(Exception e) {
        Photo photo = new Photo();
        photo.setId(-1l);
        photo.setAlbumId(-1l);
        photo.setTitle(MessageFormat.format("No se han cargado los mocks: {0}", e.getMessage()));
        photo.setUrl("Sin url");
        photo.setThumbnailUrl("Sin thumbnail url");
        return photo;
    }

    private Photo getInstance(int albumId, int photoId) {
        Photo photo = new Photo();
        photo.setId(Integer.valueOf(photoId).longValue());
        photo.setAlbumId(Integer.valueOf(albumId).longValue());
        photo.setTitle(randomTextService.getRandomText("title"));
        photo.setUrl(randomTextService.getRandomText("url"));
        photo.setThumbnailUrl(randomTextService.getRandomText("thumbnailUrl"));
        return photo;
    }

}
