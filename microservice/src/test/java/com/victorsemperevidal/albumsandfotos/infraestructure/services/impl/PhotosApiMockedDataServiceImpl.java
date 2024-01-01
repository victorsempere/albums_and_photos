package com.victorsemperevidal.albumsandfotos.infraestructure.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.external_data.ExternalDataPhotoFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.PhotosApiMockedDataService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;

@Service
public class PhotosApiMockedDataServiceImpl implements PhotosApiMockedDataService {

    private ExternalDataPhotoFactory externalDataPhotoFactory;
    private MockedDataService mockedDataService;

    @Autowired
    public PhotosApiMockedDataServiceImpl(MockedDataService mockedDataService,
            ExternalDataPhotoFactory externalDataPhotoFactory) {
        super();
        this.mockedDataService = mockedDataService;
        this.externalDataPhotoFactory = externalDataPhotoFactory;
    }

    @Override
    public List<Photo> getMockedPhotos(int albums, int photosPerAlbums) {
        List<Photo> listOfPhotos = new ArrayList<>();
        for (int currentAlbum = 0; currentAlbum < albums; currentAlbum++) {
            listOfPhotos.addAll(getAlbumMockedPhotos(currentAlbum + 1, photosPerAlbums));
        }
        return listOfPhotos;
    }

    private List<Photo> getAlbumMockedPhotos(int albumId, int photosPerAlbum) {
        List<Photo> listOfPhotos = new ArrayList<>();
        int photoIdOffset = (albumId - 1) * photosPerAlbum;
        for (int currentPhoto = 0; currentPhoto < photosPerAlbum; currentPhoto++) {
            listOfPhotos.add(externalDataPhotoFactory.getInstance(albumId, photoIdOffset + currentPhoto + 1));
        }
        return listOfPhotos;
    }

    @Override
    public List<Photo> getMockedPhotosFromJsonFile(String resourceTestFile) {
        try {
            return mockedDataService.getMockedDataFromJsonFile(resourceTestFile, new TypeReference<List<Photo>>() {
            });

        } catch (Exception e) {
            return List.of(externalDataPhotoFactory.getInstance(e));
        }
    }

}
