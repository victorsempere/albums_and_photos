package com.victorsemperevidal.albumsandfotos.application.impl;

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
import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.domain.exceptions.ExternalClientException;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandfotos.infraestructure.factories.domain_objects.MockedDomainObjectExternalDataFactory;
import com.victorsemperevidal.albumsandfotos.infraestructure.services.MockedDataService;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumsAndPhotosServiceMemoryImplTest {
        private MockedDataService mockedDataService;
        private AlbumsAndPhotosService serviceToTest;
        private MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory;

        @MockBean
        private ExternalDataService externalDataService;

        @Autowired
        public AlbumsAndPhotosServiceMemoryImplTest(MockedDataService mockedDataService,
                        MockedDomainObjectExternalDataFactory mockedDomainObjectExternalDataFactory,
                        @Qualifier("albumsServiceInMemory") AlbumsAndPhotosService serviceToTest) {
                super();
                this.mockedDataService = mockedDataService;
                this.mockedDomainObjectExternalDataFactory = mockedDomainObjectExternalDataFactory;
                this.serviceToTest = serviceToTest;
        }

        @BeforeEach
        void setUp() {
                Mockito.reset(externalDataService);
        }

        @Test
        void givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
                //
                // given
                //
                ExternalData externalData = mockedDomainObjectExternalDataFactory.getMockedExternalDataFromJson(
                                "givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos/given_external_data.json");
                Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

                //
                // when
                //
                Collection<AlbumPhotos> albumsAndPhotos = serviceToTest.processAlbumsAndPhotos();

                //
                // then
                //
                List<AlbumPhotos> expectedResponse = null;
                try {
                        String expected_response_file = "./givenMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos/expected_response.json";
                        expectedResponse = mockedDataService.getMockedDataFromJsonFile(expected_response_file,
                                        new TypeReference<List<AlbumPhotos>>() {
                                        });

                } catch (Exception e) {
                        fail("Error cargando los datos esperados como respuesta", e);
                }
                assertEquals(expectedResponse, albumsAndPhotos);
        }

        @Test
        void givenEmptyInputFromExternalDataWhenGetAlbumsAndPhotosThenEmptyList() {
                //
                // given
                //
                ExternalData externalData = mockedDomainObjectExternalDataFactory.getMockedEmptyExternalData();
                Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

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
        void givenLargeMockedInputFromExternalDataServiceWhenProcessAlbumsAndPhotosThenListOfAlbumsWithPhotos() {
                //
                // given
                //
                long executionTimeout = 800l;
                int mockedAlbums = 1000;
                int mockedPhotosPerAlbum = 1000;

                ExternalData externalData = mockedDomainObjectExternalDataFactory
                                .getMockedExternalData(mockedAlbums, mockedPhotosPerAlbum);
                Mockito.when(externalDataService.fetchExternalData()).thenReturn(externalData);

                assertTimeout(Duration.ofMillis(executionTimeout), () -> {
                        //
                        // when
                        //
                        serviceToTest.processAlbumsAndPhotos();
                });
        }
}
