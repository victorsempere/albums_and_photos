package com.victorsemperevidal.albumsandphotos.infraestructure.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.victorsemperevidal.albumsandphotos.application.impl.AlbumsAndPhotosServiceBase;
import com.victorsemperevidal.albumsandphotos.domain.services.ExternalDataService;
import com.victorsemperevidal.albumsandphotos.domain.services.PopulateService;
import com.victorsemperevidal.albumsandphotos.domain.services.ProcessAlbumsService;

@Service
@Qualifier("albumsAndPhotosServiceMemoryArrayList")
public class AlbumsAndPhotosServiceMemoryArrayList extends AlbumsAndPhotosServiceBase {

    @Autowired
    public AlbumsAndPhotosServiceMemoryArrayList(
            @Qualifier("externalDataServiceArrayList") ExternalDataService externalDataService,
            @Qualifier("populateServiceBaseMemoryArrayList") PopulateService populateService,
            @Qualifier("processAlbumsServiceMemoryArrayList") ProcessAlbumsService processAlbumsService) {
        super(externalDataService, populateService, processAlbumsService);
    }

}
