package com.victorsemperevidal.albumsandphotos.application.impl;

import java.util.Collection;

import com.victorsemperevidal.albumsandphotos.application.AlbumsAndPhotosService;
import com.victorsemperevidal.albumsandphotos.domain.objects.AlbumPhotos;
import com.victorsemperevidal.albumsandphotos.domain.objects.ExternalData;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandphotos.domain.services.ProcessAlbumsService;

public class AlbumsAndPhotosServiceBase implements AlbumsAndPhotosService {
    private PopulateService populateService;
    private ProcessAlbumsService processAlbumsService;
    private ExternalDataService externalDataService;

    public AlbumsAndPhotosServiceBase(
            ExternalDataService externalDataService,
            PopulateService populateService,
            ProcessAlbumsService processAlbumsService) {
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
