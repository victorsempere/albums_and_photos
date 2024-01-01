package com.victorsemperevidal.albumsandfotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.AlbumsApiMockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.PhotosApiMockedDataService;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumsAndPhotosServiceMemoryImplTest {
        @Autowired
        private MockedDataService mockedDataService;
        @Autowired
        private AlbumsApiMockedDataService albumsApiMockedDataService;
        @Autowired
        private PhotosApiMockedDataService photosApiMockedDataService;
        @MockBean
        private AlbumsApi albumsApi;
        @MockBean
        private PhotosApi photosApi;

        @Autowired
        @Qualifier("albumsServiceInMemory")
        private AlbumsAndPhotosService serviceToTest;

        @BeforeEach
        void setUp() {
                Mockito.reset(albumsApi);
                Mockito.reset(photosApi);
        }

        @Test
        void givenMockedResponseFromExternalClient_whenProcessAlbumsAndPhotos_thenListOfAlbumsWithPhotos() {
                //
                // given
                //
                try {
                        String given_albums_file = "./givenMockedResponseFromExternalClient_whenProcessAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_albums.json";
                        Mockito.when(albumsApi.getAlbums()).thenReturn(
                                        albumsApiMockedDataService.getMockedAlbumsFromJsonFile(given_albums_file));

                        String given_photos_file = "./givenMockedResponseFromExternalClient_whenProcessAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_photos.json";
                        Mockito.when(photosApi.getPhotos()).thenReturn(
                                        photosApiMockedDataService.getMockedPhotosFromJsonFile(given_photos_file));

                } catch (ApiException e) {
                        // Esta excepción no se puede dar aquí
                }

                //
                // when
                //
                List<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

                //
                // then
                //
                List<AlbumPhotos> expectedResponse = null;
                try {
                        String expected_response_file = "./givenMockedResponseFromExternalClient_whenProcessAlbumsAndPhotos_thenListOfAlbumsWithPhotos/expected_response.json";
                        expectedResponse = mockedDataService.getMockedDataFromJsonFile(expected_response_file,
                                        new TypeReference<List<AlbumPhotos>>() {
                                        });

                } catch (Exception e) {
                        fail("Error cargando los datos esperados como respuesta", e);
                }
                assertEquals(expectedResponse, albumsAndPhotos);
        }

        @Test
        void given204OrEmptyListFromExternalClient_whenGetAlbumsAndPhotos_thenEmptyList() {
                //
                // given
                //
                try {
                        Mockito.when(albumsApi.getAlbums()).thenReturn(List.of());
                        Mockito.when(photosApi.getPhotos()).thenReturn(List.of());

                } catch (Exception e) {
                        fail("Error cargando los datos de prueba", e);
                }

                //
                // when
                //
                List<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

                //
                // then
                //
                assertEquals(List.of(), albumsAndPhotos);
        }

        @Test
        void givenAlbumsApiException_whenGetAlbumsAndPhotos_thenExternalClientException() {
                //
                // given
                //
                int apiErrorCode = 4;
                String apiErrorMessage = "Error en API externa";
                try {
                        Mockito.when(albumsApi.getAlbums()).thenThrow(new ApiException(apiErrorCode, apiErrorMessage));
                        Mockito.when(photosApi.getPhotos()).thenReturn(List.of());

                } catch (Exception e) {
                        fail("Error cargando los datos de prueba", e);
                }

                //
                // when
                //
                try {
                        serviceToTest.processAlbumsAndPhotos();
                        fail("Esperábamos una excepción del tipo ExternalClientException");

                } catch (ExternalClientException e) {
                        //
                        // then
                        //
                        assertEquals(apiErrorCode, e.getCode());
                        assertTrue(e.getMessage().contains(apiErrorMessage));
                }
        }

        @Test()
        void givenHugeMockedResponseFromExternalClient_whenProcessAlbumsAndPhotos_thenCompleteExecutionBeforeTimeout() {
                //
                // given
                //
                long executionTimeout = 800l;
                int mockedAlbums = 1000;
                int mockedPhotosPerAlbum = 1000;

                try {
                        List<Album> albums = albumsApiMockedDataService.getMockedAlbums(mockedAlbums);
                        Mockito.when(albumsApi.getAlbums()).thenReturn(albums);

                        List<Photo> photos = photosApiMockedDataService.getMockedPhotos(mockedAlbums,
                                        mockedPhotosPerAlbum);
                        Mockito.when(photosApi.getPhotos()).thenReturn(photos);

                } catch (ApiException e) {
                        // Esta excepción no se lanzará nunca porque estamos mockeando
                }

                assertTimeout(Duration.ofMillis(executionTimeout), () -> {
                        //
                        // when
                        //
                        List<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

                        //
                        // then
                        //
                        assertEquals(mockedAlbums, albumsAndPhotos.size());
                        for (AlbumPhotos album : albumsAndPhotos) {
                                assertEquals(mockedPhotosPerAlbum, album.getPhotos().size());
                        }
                });
        }
}
