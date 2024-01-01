package com.victorsemperevidal.albumsandfotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumsAndPhotosServiceMemoryImplIT {
        private AlbumsAndPhotosService serviceToTest;

        @Autowired
        public AlbumsAndPhotosServiceMemoryImplIT(
                        @Qualifier("albumsServiceInMemory") AlbumsAndPhotosService serviceToTest) {
                super();
                this.serviceToTest = serviceToTest;
        }

        @Test
        void givenRealInputDataFromExternalClient_whenProcessAlbumsAndPhotos_thenListOfAlbumsWithPhotos() {
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
