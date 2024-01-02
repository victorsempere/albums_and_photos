package com.victorsemperevidal.albumsandfotos.infraestructure.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
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
    public ResponseEntity<Collection<AlbumPhotosDto>> processAlbumsAndPhotosIntoDatabase() {
        Collection<AlbumPhotosDto> processAlbumsAndPhotos = albumPhotosDtoFactory
                .getListFromAlbumPhotos(this.albumsAndPhotosServiceInDatabase.processAlbumsAndPhotos());
        if (processAlbumsAndPhotos == null || processAlbumsAndPhotos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(processAlbumsAndPhotos);
        }
    }

    @GetMapping("/albums-and-photos/mem")
    public ResponseEntity<Collection<AlbumPhotosDto>> processAlbumsAndPhotosIntoMemory() {
        Collection<AlbumPhotos> processAlbumsAndPhotos = this.albumsAndPhotosServiceInMemory.processAlbumsAndPhotos();
        if (processAlbumsAndPhotos == null || processAlbumsAndPhotos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(albumPhotosDtoFactory
                    .getListFromAlbumPhotos(processAlbumsAndPhotos));
        }
    }

    // Los puntos de entrada para devolver los datos de la base de datos H2 se
    // autogeneran a partir de la anotación @RespositoryRestResource
    // Tendremos los puntos de entrada:
    // * GET /db-photos
    // * GET /db-albums
}
