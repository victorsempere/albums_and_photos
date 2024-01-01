package com.victorsemperevidal.albumsandfotos.infraestructure.factories.external_data;

import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.infraestructure.services.RandomTextService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;

@Service
public class ExternalDataAlbumFactory {

    private RandomTextService randomTextService;

    @Autowired
    public ExternalDataAlbumFactory(RandomTextService randomTextService) {
        super();
        this.randomTextService = randomTextService;
    }

    public Album getInstance(Exception e) {
        Album errorAlbum = new Album();
        errorAlbum.setId(-1l);
        errorAlbum.setTitle("No se han cargado los mocks: " + e.getMessage());
        errorAlbum.setUserId(-1l);
        return errorAlbum;
    }

    public Album getInstance(int albumId) {
        RandomGenerator rg = RandomGenerator.getDefault();
        Album album = new Album();
        album.setId(Integer.valueOf(albumId).longValue());
        album.setUserId(rg.nextLong(1, Long.MAX_VALUE));
        album.setTitle(randomTextService.getRandomText("title"));
        return album;
    }

}
