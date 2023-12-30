package com.victorsemperevidal.albumsandfotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;
import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;

@SpringBootTest
@ActiveProfiles("test")
class AlbumsAndPhotosServiceMemImplTestIT {
        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        @Qualifier("albumsServiceInMemory")
        private AlbumsAndPhotosService albumsAndPhotosService;

        @MockBean
        private AlbumsApi albumsApi;
        @MockBean
        private PhotosApi photosApi;

        @Test
        void givenResponseFromExternalClient_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos() {
                //
                // given
                //
                try {
                        List<Album> albums = objectMapper.readValue(
                                        new File(
                                                        "givenDatabaseWithData_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_albums.json"),
                                        new TypeReference<List<Album>>() {
                                        });
                        List<Photo> photos = objectMapper.readValue(
                                        new File(
                                                        "givenDatabaseWithData_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_photos.json"),
                                        new TypeReference<List<Photo>>() {
                                        });
                        Mockito.when(albumsApi.getAlbums()).thenReturn(albums);
                        Mockito.when(photosApi.getPhotos()).thenReturn(photos);

                } catch (Exception e) {
                        fail("Error cargando los datos de prueba", e);
                }

                //
                // when
                //
                List<AlbumPhotosDto> albumsAndPhotos = albumsAndPhotosService.processAlbumsAndPhotos();

                //
                // then
                //
                List<AlbumPhotosDto> expectedResponse = null;
                try {
                        expectedResponse = objectMapper.readValue(
                                        new File(
                                                        "givenDatabaseWithData_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/expected_response.json"),
                                        new TypeReference<List<AlbumPhotosDto>>() {
                                        });

                } catch (Exception e) {
                        fail("Error cargando los datos esperados como respuesta", e);
                }
                assertEquals(expectedResponse, albumsAndPhotos);
        }

        @Test
        void given204FromExternalClient_whenGetAlbumsAndPhotos_thenEmptyList() {
                //
                // given
                //
                try {
                        Mockito.when(albumsApi.getAlbums())
                                        .thenThrow(new ApiException(HttpStatus.NO_CONTENT.value(), "Empty"));
                        Mockito.when(photosApi.getPhotos())
                                        .thenThrow(new ApiException(HttpStatus.NO_CONTENT.value(), "Empty"));

                } catch (Exception e) {
                        fail("Error cargando los datos de prueba", e);
                }

                //
                // when
                //
                List<AlbumPhotosDto> albumsAndPhotos = albumsAndPhotosService.processAlbumsAndPhotos();

                //
                // then
                //
                assertEquals(List.of(), albumsAndPhotos);
        }

        @Test
        void givenEmptyListsFromExternalClient_whenGetAlbumsAndPhotos_thenEmptyList() {
                //
                // given
                //
                try {
                        List<Album> albums = List.of();
                        List<Photo> photos = List.of();
                        Mockito.when(albumsApi.getAlbums()).thenReturn(albums);
                        Mockito.when(photosApi.getPhotos()).thenReturn(photos);

                } catch (Exception e) {
                        fail("Error cargando los datos de prueba", e);
                }

                //
                // when
                //
                List<AlbumPhotosDto> albumsAndPhotos = albumsAndPhotosService.processAlbumsAndPhotos();

                //
                // then
                //
                assertEquals(List.of(), albumsAndPhotos);
        }
}
