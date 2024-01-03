package com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.repos.projections.AlbumAndPhotoProjection;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumPhotosFactoryTest {

    private AlbumPhotosFactory toTest;
    private MockedDataService mockedDataService;

    @Autowired
    public AlbumPhotosFactoryTest(AlbumPhotosFactory toTest, MockedDataService mockedDataService) {
        super();
        this.toTest = toTest;
        this.mockedDataService = mockedDataService;
    }

    @Test
    void givenProjectionWithAlbumsWhenGetListOfAlbumPhotosFromProjectionsThenReturnDomainAlbumPhotos() {
        //
        // given
        //
        List<AlbumAndPhotoProjection> givenProjections = null;
        try {
            givenProjections = mockedDataService.getMockedDataFromJsonFile(
                    "givenProjectionWithAlbumsWhenGetListOfAlbumPhotosFromProjectionsThenReturnDomainAlbumPhotos/given_projections.json",
                    new TypeReference<List<AlbumAndPhotoProjection>>() {

                    });
        } catch (Exception e) {
            fail("Error cargando datos del test", e);
        }

        //
        // when
        //
        Collection<AlbumPhotos> albumPhotos = toTest.getListOfAlbumPhotosFromProjections(givenProjections);

        //
        // then
        //
        List<AlbumPhotos> expectedResponse = null;
        try {
            expectedResponse = mockedDataService.getMockedDataFromJsonFile(
                    "givenProjectionWithAlbumsWhenGetListOfAlbumPhotosFromProjectionsThenReturnDomainAlbumPhotos/then_listOfAlbumsWithPhotos.json",
                    new TypeReference<List<AlbumPhotos>>() {

                    });
        } catch (Exception e) {
            fail("Error cargando datos esperados de respuesta", e);
        }
        assertEquals(expectedResponse, albumPhotos);
    }
}
