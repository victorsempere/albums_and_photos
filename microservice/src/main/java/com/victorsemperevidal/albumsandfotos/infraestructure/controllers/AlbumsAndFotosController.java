package com.victorsemperevidal.albumsandfotos.infraestructure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;

@RestController
public class AlbumsAndFotosController {
    private AlbumsAndPhotosService albumsAndPhotosServiceInDatabase;
    private AlbumsAndPhotosService albumsAndPhotosServiceInMemory;

    @Autowired
    public AlbumsAndFotosController(
            @Qualifier("albumsServiceInDatabase") AlbumsAndPhotosService albumsAndPhotosServiceInDatabase,
            @Qualifier("albumsServiceInMemory") AlbumsAndPhotosService albumsAndPhotosServiceInMemory) {
        super();
        this.albumsAndPhotosServiceInDatabase = albumsAndPhotosServiceInDatabase;
        this.albumsAndPhotosServiceInMemory = albumsAndPhotosServiceInMemory;
    }

    @GetMapping("/albums-and-photos/db")
    public List<AlbumPhotosDto> processAlbumsAndPhotosIntoDatabase() {
        return this.albumsAndPhotosServiceInDatabase.processAlbumsAndPhotos();
    }

    @GetMapping("/albums-and-photos/mem")
    public List<AlbumPhotosDto> processAlbumsAndPhotosIntoMemory() {
        return this.albumsAndPhotosServiceInMemory.processAlbumsAndPhotos();
    }

    // Los puntos de entrada para devolver los datos de la base de datos H2 se autogeneran a partir de la anotación @RespositoryRestResource
    // Tendremos los puntos de entrada:
    // * GET /db-photos
    // * GET /db-albums
}
