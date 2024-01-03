package com.victorsemperevidal.albumsandfotos.infraestructure.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.domain.objects.Album;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.repositories.memory.MemoryAlbumRepository;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class MemoryAlbumRepositoryTest {
    private MemoryAlbumRepository serviceToTest;
    private MockedDataService mockedDataService;

    @Autowired
    public MemoryAlbumRepositoryTest(MemoryAlbumRepository serviceToTest, MockedDataService mockedDataService) {
        super();
        this.serviceToTest = serviceToTest;
        this.mockedDataService = mockedDataService;
    }

    @Test
    void givenNoAlbumsSavedWhenFindAllThenNullReturned() {
        //
        // when
        //
        List<Album> albums = serviceToTest.findAll();

        //
        // then
        //
        assertNull(albums);
    }

    @Test
    void givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned() {
        //
        // given
        //
        List<Album> albums = null;
        try {
            albums = mockedDataService.getMockedDataFromJsonFile(
                    "./givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned/given_albums.json",
                    new TypeReference<List<Album>>() {

                    });
            serviceToTest.saveAll(albums);

        } catch (Exception e) {
            fail("Error cargando los datos de entrada", e);
        }

        //
        // when
        //
        Collection<AlbumAndPhotoProjection> albumsAndPhotos = serviceToTest.getAlbumsAndPhotos();

        //
        // then
        //
        Collection<AlbumAndPhotoProjection> expectedAlbums = null;
        try {
            expectedAlbums = mockedDataService.getMockedDataFromJsonFile(
                    "./givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned/expected_response.json",
                    new TypeReference<Collection<AlbumAndPhotoProjection>>() {

                    });
        } catch (Exception e) {
            fail("Error cargando los datos esperados", e);
        }
        assertEquals(expectedAlbums, albumsAndPhotos);
    }

    @Test
    void givenListOfAlbumsWhenSaveAllThenInstanceReferenceStored() {
        //
        // given
        //
        List<Album> albums = List.of();

        //
        // when
        //
        serviceToTest.saveAll(albums);

        //
        // then
        //
        List<Album> albumsInService = serviceToTest.findAll();
        assertSame(albums, albumsInService);
    }
}
