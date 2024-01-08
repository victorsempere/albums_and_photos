package com.victorsemperevidal.albumsandphotos.infraestructure.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.application.impl.AlbumsAndPhotosServiceBase;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandphotos.domain.services.ProcessAlbumsService;

@Service
@Qualifier("albumsAndPhotosServiceDatabaseArrayList")
public class AlbumsAndPhotosServiceDatabaseArrayList extends AlbumsAndPhotosServiceBase {

    @Autowired
    public AlbumsAndPhotosServiceDatabaseArrayList(
            @Qualifier("externalDataServiceArrayList") ExternalDataService externalDataService,
            @Qualifier("populateServiceDatabaseArrayList") PopulateService populateService,
            @Qualifier("processAlbumsServiceDatabaseArrayList") ProcessAlbumsService processAlbumsService) {
        super(externalDataService, populateService, processAlbumsService);
    }

}
