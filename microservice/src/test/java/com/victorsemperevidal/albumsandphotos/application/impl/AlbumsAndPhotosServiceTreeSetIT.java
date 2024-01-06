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
public class AlbumsAndPhotosServiceTreeSetIT {

        private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet;
        private AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSet;

        @Autowired
        public AlbumsAndPhotosServiceTreeSetIT(
                        @Qualifier("albumsAndPhotosServiceDatabaseTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseTreeSet,
                        @Qualifier("albumsAndPhotosServiceMemoryTreeSet") AlbumsAndPhotosService albumsAndPhotosServiceMemoryTreeSet) {
                this.albumsAndPhotosServiceDatabaseTreeSet = albumsAndPhotosServiceDatabaseTreeSet;
                this.albumsAndPhotosServiceMemoryTreeSet = albumsAndPhotosServiceMemoryTreeSet;
        }

        @Test
        void givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
                givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                                albumsAndPhotosServiceDatabaseTreeSet);
                givenRealInputDataFromExternalClientWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                                albumsAndPhotosServiceMemoryTreeSet);
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
