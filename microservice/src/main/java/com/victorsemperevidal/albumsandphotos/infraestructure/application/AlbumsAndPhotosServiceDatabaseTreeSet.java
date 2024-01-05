package com.victorsemperevidal.albumsandphotos.infraestructure.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.application.impl.AlbumsAndPhotosServiceBase;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandphotos.domain.services.ProcessAlbumsService;

@Service
@Qualifier("albumsAndPhotosServiceDatabaseTreeSet")
public class AlbumsAndPhotosServiceDatabaseTreeSet extends AlbumsAndPhotosServiceBase {

    @Autowired
    public AlbumsAndPhotosServiceDatabaseTreeSet(
            @Qualifier("externalDataServiceTreeSet") ExternalDataService externalDataService,
            @Qualifier("populateServiceDatabaseTreeSet") PopulateService populateService,
            @Qualifier("processAlbumsServiceDatabaseTreeSet") ProcessAlbumsService processAlbumsService) {
        super(externalDataService, populateService, processAlbumsService);
    }

}
