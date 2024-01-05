package com.victorsemperevidal.albumsandphotos.infraestructure.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.repos.AlbumRepository;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class MemoryAlbumRepositoryTreeSetTest {

    private AlbumRepository memoryAlbumRepositoryTreeSet;
    private AlbumRepository databaseAlbumRepositoryTreeSet;
    private MockedDataService mockedDataService;

    @Autowired
    public MemoryAlbumRepositoryTreeSetTest(
            @Qualifier("memoryAlbumRepositoryTreeSet") AlbumRepository memoryAlbumRepositoryTreeSet,
            @Qualifier("databaseAlbumRepositoryTreeSet") AlbumRepository databaseAlbumRepositoryTreeSet,
            MockedDataService mockedDataService) {
        super();
        this.memoryAlbumRepositoryTreeSet = memoryAlbumRepositoryTreeSet;
        this.databaseAlbumRepositoryTreeSet = databaseAlbumRepositoryTreeSet;
        this.mockedDataService = mockedDataService;
    }

    @Test
    void givenDeletedAllRegistersWhenFindAllThenEmptyListReturned() {
        givenDeletedAllRegistersWhenFindAllThenEmptyListReturned(memoryAlbumRepositoryTreeSet);
        givenDeletedAllRegistersWhenFindAllThenEmptyListReturned(databaseAlbumRepositoryTreeSet);
    }

    private void givenDeletedAllRegistersWhenFindAllThenEmptyListReturned(AlbumRepository serviceToTest) {
        //
        // given
        //
        serviceToTest.deleteAll();

        //
        // when
        //
        Collection<Album> albums = serviceToTest.findAll();

        //
        // then
        //
        assertNotNull(albums);
        assertTrue(albums.isEmpty());
    }

    @Test
    void givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned() {
        givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned(
                memoryAlbumRepositoryTreeSet);
        givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned(
                databaseAlbumRepositoryTreeSet);
    }

    private void givenListOfAlbumsWithoutPhotosWhenGetAlbumsAndPhotosThenListOfAlbumsWithPhotosReturned(
            AlbumRepository serviceToTest) {
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
                    new TypeReference<TreeSet<AlbumAndPhotoProjection>>() {

                    });
        } catch (Exception e) {
            fail("Error cargando los datos esperados", e);
        }
        assertEquals(expectedAlbums, albumsAndPhotos);
    }

    @Test
    void givenListOfAlbumsWhenSaveAllThenInstanceReferenceIsDifferent() {
        givenListOfAlbumsWhenSaveAllThenInstanceReferenceIsDifferent(memoryAlbumRepositoryTreeSet);
        givenListOfAlbumsWhenSaveAllThenInstanceReferenceIsDifferent(databaseAlbumRepositoryTreeSet);
    }

    private void givenListOfAlbumsWhenSaveAllThenInstanceReferenceIsDifferent(
            AlbumRepository serviceToTest) {
        //
        // given
        //
        Collection<Album> albums = new TreeSet<>();

        //
        // when
        //
        serviceToTest.saveAll(albums);

        //
        // then
        //
        Collection<Album> albumsInService = serviceToTest.findAll();
        assertNotSame(albums, albumsInService);
        assertEquals(albums, albumsInService);
    }
}
