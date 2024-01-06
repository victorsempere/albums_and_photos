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
public class AlbumsAndPhotosServiceArrayListIT {

        private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList;
        private AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList;

        @Autowired
        public AlbumsAndPhotosServiceArrayListIT(
                        @Qualifier("albumsAndPhotosServiceDatabaseArrayList") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList,
                        @Qualifier("albumsAndPhotosServiceMemoryArrayList") AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList) {
                this.albumsAndPhotosServiceDatabaseArrayList = albumsAndPhotosServiceDatabaseArrayList;
                this.albumsAndPhotosServiceMemoryArrayList = albumsAndPhotosServiceMemoryArrayList;
        }

        @Test
        void givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
                givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                                albumsAndPhotosServiceDatabaseArrayList);
                givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                                albumsAndPhotosServiceMemoryArrayList);
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
