package com.victorsemperevidal.albumsandphotos.application.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AlbumsAndPhotosServiceArrayListTest {
    private Logger log = LoggerFactory.getLogger(getClass());
    @MockBean
    @Qualifier("externalDataServiceArrayList")
    private ExternalDataService externalDataService;
    private MockedDataService mockedDataService;
    private MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory;
    private AlbumsAndPhotosService albumsAndPhotosServiceDatabaseArrayList;
    private AlbumsAndPhotosService albumsAndPhotosServiceMemoryArrayList;

    @Autowired
    public AlbumsAndPhotosServiceArrayListTest(MockedDataService mockedDataService,
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

    @Test()
    void givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenLogProcessingTimeInNanos() {
        int mockedAlbums = 200;
        int mockedPhotosPerAlbum = 200;
        long nanosInExecution = givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenLogProcessingTimeInNanos(
                albumsAndPhotosServiceDatabaseArrayList, mockedAlbums, mockedPhotosPerAlbum);
        log.info(MessageFormat.format(
                "Instancia: {0} -> Procesados {1} albums con {2} fotos por álbum en {3} ns. {4} ns/album y {5} ns/elemento",
                albumsAndPhotosServiceDatabaseArrayList.getClass().getSimpleName(), mockedAlbums,
                mockedPhotosPerAlbum, nanosInExecution, (nanosInExecution / mockedAlbums),
                (nanosInExecution / (mockedAlbums * mockedPhotosPerAlbum))));

        mockedAlbums = 2000;
        mockedPhotosPerAlbum = 2000;
        nanosInExecution = givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenLogProcessingTimeInNanos(
                albumsAndPhotosServiceMemoryArrayList, mockedAlbums, mockedPhotosPerAlbum);
        log.info(MessageFormat.format(
                "Instancia: {0} -> Procesados {1} albums con {2} fotos por álbum en {3} ns. {4} ns/album y {5} ns/elemento",
                albumsAndPhotosServiceMemoryArrayList.getClass().getSimpleName(), mockedAlbums,
                mockedPhotosPerAlbum, nanosInExecution, (nanosInExecution / mockedAlbums),
                (nanosInExecution / (mockedAlbums * mockedPhotosPerAlbum))));

        assertTrue(nanosInExecution > 1000000, "Ejecución de menos de 1ms. Revisar si es un error");
    }

    private long givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenLogProcessingTimeInNanos(
            AlbumsAndPhotosService serviceToTest, int mockedAlbums,
            int mockedPhotosPerAlbum) {
        //
        // given
        //
        ExternalData externalData = mockedDomainObjectExternalDataFactory
                .getMockedExternalData(mockedAlbums, mockedPhotosPerAlbum);
        Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

        //
        // when
        //
        long start = System.nanoTime();
        serviceToTest.processAlbumsAndPhotos();
        long end = System.nanoTime();

        return end - start;
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
