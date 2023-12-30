package com.victorsemperevidal.albumsandfotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
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
import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.AlbumsApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.api.PhotosApi;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.invoker.ApiException;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Album;
import com.victorsemperevidal.albumsandphotos.externalclients.jsonplaceholdertypicode.model.Photo;

@SpringBootTest
@ActiveProfiles("test")
class AlbumsAndPhotosServiceDatabaseImplTestIT {
    @Autowired
    @Qualifier("albumsServiceInDatabase")
    private AlbumsAndPhotosService albumsAndPhotosService;
    @Autowired
    private ObjectMapper objectMapper;

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
            String given_albums_file = "./givenResponseFromExternalClient_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_albums.json";
            InputStream given_albums_file_src = getClass().getClassLoader().getResourceAsStream(
                    given_albums_file);
            List<Album> albums = objectMapper.readValue(
                    given_albums_file_src,
                    new TypeReference<List<Album>>() {
                    });
            Mockito.when(albumsApi.getAlbums()).thenReturn(albums);

            String given_photos_file = "./givenResponseFromExternalClient_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/given_photos.json";
            InputStream given_photos_file_src = getClass().getClassLoader().getResourceAsStream(
                    given_photos_file);
            List<Photo> photos = objectMapper.readValue(
                    given_photos_file_src,
                    new TypeReference<List<Photo>>() {
                    });
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
            String expected_response_file = "givenResponseFromExternalClient_whenGetAlbumsAndPhotos_thenListOfAlbumsWithPhotos/expected_response.json";
            InputStream expected_response_file_src = getClass().getClassLoader().getResourceAsStream(
                    expected_response_file);
            expectedResponse = objectMapper.readValue(
                    expected_response_file_src,
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
