package com.victorsemperevidal.albumsandphotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import java.util.Collection;
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
import com.victorsemperevidal.albumsandphotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandphotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects.MockedDomainObjectExternalDataFactory;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumsAndPhotosServiceDatabaseImplArrayListTest {
    @MockBean
    @Qualifier("externalDataServiceArrayList")
    private ExternalDataService externalDataService;
    private MockedDataService mockedDataService;
    private MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory;
    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList;

    @Autowired
    public AlbumsAndPhotosServiceDatabaseImplArrayListTest(MockedDataService mockedDataService,
            MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory,
            @Qualifier("albumsAndPhotosServiceDatabaseArrayList") AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList,
            @Qualifier("albumsAndPhotosServiceMemoryArrayList") AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList

    ) {
        super();
        this.mockedDataService = mockedDataService;
        this.mockedDomainObjectExternalDataFactory = mockedDomainObjectExternalDataFactory;
        this.albumsAndPhotosServiceDatabaseArrayList = albumsAndPhotosServiceDatabaseArrayList;
        this.albumsAndPhotosServiceMemoryArrayList = albumsAndPhotosServiceMemoryArrayList;
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(externalDataService);
    }

    @Test
    void givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
        givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceDatabaseArrayList);
        givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceMemoryArrayList);
    }

    private void givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
            AlbumsAndPhotosService serviceToTest) {
        //
        // given
        //
        String givenExternalData = "./givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos/given_external_data.json";
        ExternalData externalData = mockedDomainObjectExternalDataFactory
                .getMockedExternalDataFromJson(givenExternalData);
        Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

        //
        // when
        //
        Collection<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

        //
        // then
        //
        Collection<AlbumPhotos> expectedResponse = null;
        try {
            String expectedResponseFile = "givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos/expected_response.json";
            expectedResponse = mockedDataService.getMockedDataFromJsonFile(expectedResponseFile,
                    new TypeReference<List<AlbumPhotos>>() {
                    });

        } catch (Exception e) {
            fail("Error cargando los datos esperados como respuesta", e);
        }
        assertEquals(expectedResponse, albumsAndPhotos);

    }

    @Test
    void givenEmptyInputFromExternalDataWhenGetAlbumsAndPhotosThenEmptyList() {
        givenEmptyInputFromExternalDataWhenGetAlbumsAndPhotosThenEmptyList(albumsAndPhotosServiceDatabaseArrayList);
        givenEmptyInputFromExternalDataWhenGetAlbumsAndPhotosThenEmptyList(albumsAndPhotosServiceMemoryArrayList);
    }

    private void givenEmptyInputFromExternalDataWhenGetAlbumsAndPhotosThenEmptyList(
            AlbumsAndPhotosService serviceToTest) {
        //
        // given
        //
        Mockito.when(externalDataService.fetchExternalData())
                .thenReturn(mockedDomainObjectExternalDataFactory.getMockedEmptyExternalData());

        //
        // when
        //
        Collection<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

        //
        // then
        //
        assertEquals(List.of(), albumsAndPhotos);
    }

    @Test
    void givenExternalClientExceptionWhenGetAlbumsAndPhotosThenExternalClientException() {
        givenExternalClientExceptionWhenGetAlbumsAndPhotosThenExternalClientException(
                albumsAndPhotosServiceDatabaseArrayList);
        givenExternalClientExceptionWhenGetAlbumsAndPhotosThenExternalClientException(
                albumsAndPhotosServiceMemoryArrayList);
    }

    private void givenExternalClientExceptionWhenGetAlbumsAndPhotosThenExternalClientException(
            AlbumsAndPhotosService serviceToTest) {
        //
        // given
        //
        int apiErrorCode = 4;
        String apiErrorMessage = "Error en API externa";
        try {
            Mockito.when(externalDataService.fetchExternalData())
                    .thenThrow(new ExternalClientException(apiErrorCode, apiErrorMessage,
                            new Exception()));
        } catch (ExternalClientException e) {
            // Esta excepción no se va a lanzar nunca
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
    void givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
        long executionTimeoutInMillis = 7000l;
        int mockedAlbums = 100;
        int mockedPhotosPerAlbum = 100;
        givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceDatabaseArrayList, executionTimeoutInMillis, mockedAlbums, mockedPhotosPerAlbum);

        executionTimeoutInMillis = 1000l;
        mockedAlbums = 1000;
        mockedPhotosPerAlbum = 1000;
        givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
                albumsAndPhotosServiceMemoryArrayList, executionTimeoutInMillis, mockedAlbums, mockedPhotosPerAlbum);
    }

    private void givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos(
            AlbumsAndPhotosService serviceToTest, long executionTimeoutInMillis, int mockedAlbums,
            int mockedPhotosPerAlbum) {
        //
        // given
        //
        ExternalData externalData = mockedDomainObjectExternalDataFactory
                .getMockedExternalData(mockedAlbums, mockedPhotosPerAlbum);
        Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

        assertTimeout(Duration.ofMillis(executionTimeoutInMillis), () -> {
            //
            // when
            //
            serviceToTest.processAlbumsAndPhotos();
        });
    }

}
