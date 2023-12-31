package com.victorsemperevidal.albumsandfotos.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandfotos.application.dtos.AlbumPhotosDto;
import com.victorsemperevidal.albumsandfotos.application.factories.AlbumPhotosDtoFactory;
import com.victorsemperevidal.albumsandfotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandfotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandfotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandfotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandfotos.domain.services.ProcessAlbumsService;

@Service
@Qualifier("albumsServiceInDatabase")
public class AlbumsAndPhotosServiceDatabaseImpl implements AlbumsAndPhotosService {

    private PopulateService populateService;
    private ProcessAlbumsService processAlbumsService;
    private AlbumPhotosDtoFactory albumPhotosDtoFactory;
    private ExternalDataService externalDataService;

    @Autowired
    public AlbumsAndPhotosServiceDatabaseImpl(
            ExternalDataService externalDataService,
            @Qualifier("populateDatabaseService") PopulateService populateService,
            @Qualifier("processAlbumsServiceFromDatabase") ProcessAlbumsService processAlbumsService,
            AlbumPhotosDtoFactory albumPhotosDtoFactory) {
        super();
        this.externalDataService = externalDataService;
        this.populateService = populateService;
        this.processAlbumsService = processAlbumsService;
        this.albumPhotosDtoFactory = albumPhotosDtoFactory;
    }

    @Override
    public List<AlbumPhotosDto> processAlbumsAndPhotos() {
        ExternalData externalData = this.externalDataService.fetchExternalData();
        this.populateService.populate(externalData);
        List<AlbumPhotos> albumsAndPhotos = this.processAlbumsService.processAlbumsAndPhotos();
        return albumPhotosDtoFactory.getListFromAlbumPhotos(albumsAndPhotos);
    }

}
