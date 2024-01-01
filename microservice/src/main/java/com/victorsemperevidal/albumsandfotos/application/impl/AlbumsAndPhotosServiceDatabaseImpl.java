package com.victorsemperevidal.albumsandfotos.application.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandfotos.application.AlbumsAndPhotosService;
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
    private ExternalDataService externalDataService;

    @Autowired
    public AlbumsAndPhotosServiceDatabaseImpl(
            ExternalDataService externalDataService,
            @Qualifier("populateDatabaseService") PopulateService populateService,
            @Qualifier("processAlbumsServiceFromDatabase") ProcessAlbumsService processAlbumsService) {
        super();
        this.externalDataService = externalDataService;
        this.populateService = populateService;
        this.processAlbumsService = processAlbumsService;
    }

    @Override
    public Collection<AlbumPhotos> processAlbumsAndPhotos() {
        ExternalData externalData = this.externalDataService.fetchExternalData();
        this.populateService.populate(externalData);
        return this.processAlbumsService.processAlbumsAndPhotos();
    }

}
