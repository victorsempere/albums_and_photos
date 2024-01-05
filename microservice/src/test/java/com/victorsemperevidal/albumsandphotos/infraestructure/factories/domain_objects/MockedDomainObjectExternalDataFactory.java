package com.victorsemperevidal.albumsandphotos.infraestructure.factories.domain_objects;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.victorsemperevidal.albumsandphotos.domain.objects.Album;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.objects.Photo;
import com.victorsemperevidal.albumsandphotos.infraestructure.services.MockedDataService;

@Service
public class MockedDomainObjectExternalDataFactory {

    private MockedDomainObjectAlbumFactory mockedDomainObjectAlbumFactory;
    private MockedDomainObjectPhotoFactory mockedDomainObjectPhotoFactory;
    private MockedDataService mockedDataService;

    @Autowired
    public MockedDomainObjectExternalDataFactory(MockedDomainObjectAlbumFactory mockedDomainObjectAlbumFactory,
            MockedDomainObjectPhotoFactory mockedDomainObjectPhotoFactory, MockedDataService mockedDataService) {
        super();
        this.mockedDomainObjectAlbumFactory = mockedDomainObjectAlbumFactory;
        this.mockedDomainObjectPhotoFactory = mockedDomainObjectPhotoFactory;
        this.mockedDataService = mockedDataService;
    }

    public ExternalData getMockedExternalData(int mockedAlbums, int mockedPhotosPerAlbum) {
        List<Album> albums = mockedDomainObjectAlbumFactory.getMockedAlbums(mockedAlbums);
        List<Photo> photos = mockedDomainObjectPhotoFactory.getMockedPhotos(mockedAlbums, mockedPhotosPerAlbum);
        return new ExternalData(albums, photos);
    }

    public ExternalData getMockedExternalDataFromJson(String jsonFile) {
        try {
            return mockedDataService.getMockedDataFromJsonFile(jsonFile, new TypeReference<ExternalData>() {
            });
        } catch (Exception e) {
            return getInstance(e);
        }
    }

    private ExternalData getInstance(Exception e) {
        return new ExternalData(List.of(mockedDomainObjectAlbumFactory.getInstance(e)),
                List.of(mockedDomainObjectPhotoFactory.getInstance(e)));
    }

    public ExternalData getMockedEmptyExternalData() {
        return new ExternalData(List.of(), List.of());
    }

}
