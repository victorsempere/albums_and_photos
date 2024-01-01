package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import java.util.List;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;

public interface PhotosApiMockedDataService {
    public List<Photo> getMockedPhotos(int albums, int photosPerAlbum);

    public List<Photo> getMockedPhotosFromJsonFile(String resourceTestFile);
}
