package com.victorsemperevidal.albumsandphotos.infraestructure.factories.repos.projections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumAndPhotoProjectionFactoryHashMapAndArrayListTest {
    private AlbumAndPhotoProjectionFactory toTest;
    private MockedDataService mockedDataService;

    @Autowired
    public AlbumAndPhotoProjectionFactoryHashMapAndArrayListTest(@Qualifier("albumAndPhotoProjectionFactoryHashMapAndArrayList") AlbumAndPhotoProjectionFactory toTest,
            MockedDataService mockedDataService) {
        super();
        this.toTest = toTest;
        this.mockedDataService = mockedDataService;
    }

    @Test
    void givenExternalAPIRestDataWhengetInstancesFromListOfAlbumsAndPhotosThenDomainAlbumAndPhotoProjection() {
        //
        // given
        //
        List<Album> listOfAlbums = null;
        List<Photo> listOfPhotos = null;
        try {
            listOfAlbums = mockedDataService.getMockedDataFromJsonFile(
                    "givenExternalAPIRestDataWhengetInstancesFromListOfAlbumsAndPhotosThenDomainAlbumAndPhotoProjection/given_albums.json",
                    new TypeReference<List<Album>>() {
                    });
            listOfPhotos = mockedDataService.getMockedDataFromJsonFile(
                    "givenExternalAPIRestDataWhengetInstancesFromListOfAlbumsAndPhotosThenDomainAlbumAndPhotoProjection/given_photos.json",
                    new TypeReference<List<Photo>>() {
                    });
        } catch (Exception e) {
            fail("Error cargando datos given", e);
        }

        //
        // then
        //
        Collection<AlbumAndPhotoProjection> domainProjection = toTest.getInstancesFromListOfAlbumsAndPhotos(
                listOfAlbums,
                listOfPhotos);

        //
        // when
        //
        List<AlbumAndPhotoProjection> expectedDomainAlbumAndPhotosProjection = null;
        try {
            expectedDomainAlbumAndPhotosProjection = mockedDataService
                    .getMockedDataFromJsonFile(
                            "givenExternalAPIRestDataWhengetInstancesFromListOfAlbumsAndPhotosThenDomainAlbumAndPhotoProjection/expected_domainAlbumAndPhotosProjection.json",
                            new TypeReference<List<AlbumAndPhotoProjection>>() {

                            });
        } catch (Exception e) {
            fail("Error cargando datos expected", e);
        }
        assertEquals(expectedDomainAlbumAndPhotosProjection, domainProjection);
    }
}
