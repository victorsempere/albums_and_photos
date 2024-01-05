package com.victorsemperevidal.albumsandphotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.victorsemperevidal.albumsandphotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumsAndPhotosServiceDatabaseImplIT {

    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSett;

    @Autowired
    public AlbumsAndPhotosServiceDatabaseImplIT(
            @Qualifier("albumsAndPhotosServiceDatabaseArrayList") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList,
            @Qualifier("albumsAndPhotosServiceDatabaseTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet,
            @Qualifier("albumsAndPhotosServiceMemoryArrayList") AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList,
            @Qualifier("albumsAndPhotosServiceMemoryTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSett) {
        super();
        this.albumsAndPhotosServiceDatabaseArrayList = albumsAndPhotosServiceDatabaseArrayList;
        this.albumsAndPhotosServiceDatabaseTreeSet = albumsAndPhotosServiceDatabaseTreeSet;
        this.albumsAndPhotosServiceMemoryArrayList = albumsAndPhotosServiceMemoryArrayList;
        this.albumsAndPhotosServiceMemoryTreeSett = albumsAndPhotosServiceMemoryTreeSett;
    }

    @Test
    void givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
        givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceDatabaseArrayList);
        givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceDatabaseTreeSet);
        givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceMemoryArrayList);
        givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceMemoryTreeSett);

    }

    private void givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
            AlbumsAndPhotosService serviceToTest) {
        //
        // given
        //

        //
        // when
        //
        Collection<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

        //
        // then
        //
        int expectedAlbums = 100;
        assertEquals(expectedAlbums, albumsAndPhotos.size());

        int totalPhotos = 0;
        for (AlbumPhotos album : albumsAndPhotos) {
            totalPhotos += album.getPhotos() == null ? 0 : album.getPhotos().size();
        }
        int exepectedPhotos = 5000;
        assertEquals(exepectedPhotos, totalPhotos);
    }

}
