package com.victorsemperevidal.albumsandfotos.infraestructure.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.infraestructure.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.dtos.AlbumPhotosDtoFactory;

@RestController
public class AlbumsAndFotosController {
    private AlbumsAndPhotosService albumsAndPhotosServiceInDatabase;
    private AlbumsAndPhotosService albumsAndPhotosServiceInMemory;
    private AlbumPhotosDtoFactory albumPhotosDtoFactory;

    @Autowired
    public AlbumsAndFotosController(
            @Qualifier("albumsServiceInDatabase") AlbumsAndPhotosService albumsAndPhotosServiceInDatabase,
            @Qualifier("albumsServiceInMemory") AlbumsAndPhotosService albumsAndPhotosServiceInMemory,
            AlbumPhotosDtoFactory albumPhotosDtoFactory) {
        super();
        this.albumsAndPhotosServiceInDatabase = albumsAndPhotosServiceInDatabase;
        this.albumsAndPhotosServiceInMemory = albumsAndPhotosServiceInMemory;
        this.albumPhotosDtoFactory = albumPhotosDtoFactory;
    }

    @GetMapping("/albums-and-photos/db")
    public Collection<AlbumPhotosDto> processAlbumsAndPhotosIntoDatabase() {
        return albumPhotosDtoFactory
                .getListFromAlbumPhotos(this.albumsAndPhotosServiceInDatabase.processAlbumsAndPhotos());
    }

    @GetMapping("/albums-and-photos/mem")
    public Collection<AlbumPhotosDto> processAlbumsAndPhotosIntoMemory() {
        return albumPhotosDtoFactory
                .getListFromAlbumPhotos(this.albumsAndPhotosServiceInMemory.processAlbumsAndPhotos());
    }

    // Los puntos de entrada para devolver los datos de la base de datos H2 se
    // autogeneran a partir de la anotación @RespositoryRestResource
    // Tendremos los puntos de entrada:
    // * GET /db-photos
    // * GET /db-albums
}
