package com.victorsemperevidal.albumsandphotos.infraestructure.repositories.database;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.PhotoRepository;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.MockedDomainObjectExternalDataFactory;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseAlbumRepositoryTest {

        private Logger log = LoggerFactory.getLogger(getClass());

        private AlbumRepository databaseAlbumRepositoryArrayList;
        private AlbumRepository databaseAlbumRepositoryTreeSet;
        private PhotoRepository databasePhotoRepositoryArrayList;
        private PhotoRepository databasePhotoRepositoryTreeSet;
        private MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory;

        @Autowired
        public DatabaseAlbumRepositoryTest(
                        @Qualifier("databaseAlbumRepositoryArrayList") AlbumRepository databaseAlbumRepositoryArrayList,
                        @Qualifier("databaseAlbumRepositoryTreeSet") AlbumRepository databaseAlbumRepositoryTreeSet,
                        @Qualifier("databasePhotoRepositoryArrayList") PhotoRepository databasePhotoRepositoryArrayList,
                        @Qualifier("databasePhotoRepositoryTreeSet") PhotoRepository databasePhotoRepositoryTreeSet,
                        MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory) {
                super();
                this.databaseAlbumRepositoryArrayList = databaseAlbumRepositoryArrayList;
                this.databaseAlbumRepositoryTreeSet = databaseAlbumRepositoryTreeSet;
                this.databasePhotoRepositoryArrayList = databasePhotoRepositoryArrayList;
                this.databasePhotoRepositoryTreeSet = databasePhotoRepositoryTreeSet;
                this.mockedDomainObjectExternalDataFactory = mockedDomainObjectExternalDataFactory;
        }

        @Test
        void givenLargeAlbumsList_whenGetAlbumsAndPhotos_thenCheckTimeout() {
                //
                // given
                //
                int mockedAlbums = 500;
                int mockedPhotosPerAlbum = 500;
                ExternalData externalData = mockedDomainObjectExternalDataFactory.getMockedExternalData(mockedAlbums,
                                mockedPhotosPerAlbum);
                databaseAlbumRepositoryArrayList.saveAll(externalData.getAlbums());
                databasePhotoRepositoryArrayList.saveAll(externalData.getPhotos());

                long executionTimeoutInNanos = 10l * 1000l * 1000000l; // 10 segundos en nano segundos
                givenLargeAlbumsList_whenGetAlbumsAndPhotos_thenCheckTimeout(databaseAlbumRepositoryArrayList,
                                databasePhotoRepositoryArrayList, executionTimeoutInNanos);

                // 15 segundos en nano segundos -> TreeSet es más lento que la lista
                executionTimeoutInNanos = 15l * 1000l * 1000000l;
                givenLargeAlbumsList_whenGetAlbumsAndPhotos_thenCheckTimeout(databaseAlbumRepositoryTreeSet,
                                databasePhotoRepositoryTreeSet, executionTimeoutInNanos);
        }

        private void givenLargeAlbumsList_whenGetAlbumsAndPhotos_thenCheckTimeout(
                        AlbumRepository albumRepository, PhotoRepository photoRepository,
                        long executionTimeoutInNanos) {
                assertTimeout(Duration.ofNanos(executionTimeoutInNanos), () -> {
                        //
                        // when
                        //
                        long start = System.nanoTime();
                        albumRepository.getAlbumsAndPhotos();
                        long end = System.nanoTime();

                        log.info("Ejecutado en " + (end - start) + "ns");
                });
        }
}
