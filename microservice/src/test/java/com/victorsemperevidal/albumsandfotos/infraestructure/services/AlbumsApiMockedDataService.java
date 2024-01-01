package com.victorsemperevidal.albumsandfotos.infraestructure.services;

import java.util.List;

import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;

public interface AlbumsApiMockedDataService {
    public List<Album> getMockedAlbums(int albums);

    public List<Album> getMockedAlbumsFromJsonFile(String resourceTestFile);
}
