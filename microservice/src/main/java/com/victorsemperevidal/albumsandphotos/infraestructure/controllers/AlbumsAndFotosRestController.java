package com.victorsemperevidal.albumsandphotos.infraestructure.controllers;

import java.util.Collection;

import javax.ws.rs.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victorsemperevidal.albumsandphotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.infraestructure.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.dtos.AlbumPhotosDtoFactory;

@RestController
public class AlbumsAndFotosRestController {

    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSet;
    private AlbumPhotosDtoFactory albumPhotosDtoFactory;

    @Autowired
    public AlbumsAndFotosRestController(
            @Qualifier("albumsAndPhotosServiceDatabaseArrayList") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList,
            @Qualifier("albumsAndPhotosServiceDatabaseTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet,
            @Qualifier("albumsAndPhotosServiceMemoryArrayList") AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList,
            @Qualifier("albumsAndPhotosServiceMemoryTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSet,
            AlbumPhotosDtoFactory albumPhotosDtoFactory) {
        super();
        this.albumsAndPhotosServiceDatabaseArrayList = albumsAndPhotosServiceDatabaseArrayList;
        this.albumsAndPhotosServiceDatabaseTreeSet = albumsAndPhotosServiceDatabaseTreeSet;
        this.albumsAndPhotosServiceMemoryArrayList = albumsAndPhotosServiceMemoryArrayList;
        this.albumsAndPhotosServiceMemoryTreeSet = albumsAndPhotosServiceMemoryTreeSet;
        this.albumPhotosDtoFactory = albumPhotosDtoFactory;
    }

    @GetMapping("/albums-and-photos/db")
    public ResponseEntity<Collection<AlbumPhotosDto>> processAlbumsAndPhotosIntoDatabase(
            @RequestParam(value = "useType", defaultValue = "arrayList") String useType) {
        boolean useDatabase = true;
        AlbumsAndPhotosService albumsAndPhotosService = getAlbumsAndPhotosServiceInstance(useDatabase, useType);

        Collection<AlbumPhotosDto> albumsAndPhotosDtos = processAlbumsAndPhotosWithInstance(albumsAndPhotosService);

        return buildResponseEntity(albumsAndPhotosDtos);
    }

    @GetMapping("/albums-and-photos/mem")
    public ResponseEntity<Collection<AlbumPhotosDto>> processAlbumsAndPhotosIntoMemory(
            @RequestParam(value = "useType", defaultValue = "arrayList") String useType) {
        boolean useDatabase = false;
        AlbumsAndPhotosService albumsAndPhotosService = getAlbumsAndPhotosServiceInstance(useDatabase, useType);

        Collection<AlbumPhotosDto> albumsAndPhotosDtos = processAlbumsAndPhotosWithInstance(albumsAndPhotosService);

        return buildResponseEntity(albumsAndPhotosDtos);
    }

    // Los puntos de entrada para devolver los datos de la base de datos H2 se
    // autogeneran a partir de la anotación @RespositoryRestResource
    // Tendremos los puntos de entrada:
    // * GET /db-photos
    // * GET /db-albums

    private AlbumsAndPhotosService getAlbumsAndPhotosServiceInstance(boolean useDatabase, String useType) {
        if ("arrayList".equalsIgnoreCase(useType)) {
            if (useDatabase) {
                return this.albumsAndPhotosServiceDatabaseArrayList;
            } else {
                return this.albumsAndPhotosServiceMemoryArrayList;
            }

        } else if ("treeSet".equalsIgnoreCase(useType)) {
            if (useDatabase) {
                return this.albumsAndPhotosServiceDatabaseTreeSet;
            } else {
                return this.albumsAndPhotosServiceMemoryTreeSet;
            }
        }

        throw new BadRequestException("Debe indicar el tipo: arrayList o treeSet");
    }

    private Collection<AlbumPhotosDto> processAlbumsAndPhotosWithInstance(
            AlbumsAndPhotosService albumsAndPhotosService) {
        Collection<AlbumPhotos> albumsAndPhotosProcessedData = albumsAndPhotosService.processAlbumsAndPhotos();
        return albumPhotosDtoFactory
                .getListFromAlbumPhotos(albumsAndPhotosProcessedData);
    }

    private ResponseEntity<Collection<AlbumPhotosDto>> buildResponseEntity(
            Collection<AlbumPhotosDto> albumsAndPhotosDtos) {
        if (albumsAndPhotosDtos == null || albumsAndPhotosDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(albumsAndPhotosDtos);
        }

    }

}
